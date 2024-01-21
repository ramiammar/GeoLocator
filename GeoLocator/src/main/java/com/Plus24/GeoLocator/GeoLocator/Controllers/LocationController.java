package com.Plus24.GeoLocator.GeoLocator.Controllers;

import com.Plus24.GeoLocator.GeoLocator.Dtos.*;
import com.Plus24.GeoLocator.GeoLocator.Services.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/Locations")
@CrossOrigin(origins = "http://localhost:4200")
public class LocationController {

    private final LocationService service;
    @Value("${apiKey}")
    private String apiKey;

    private static final Logger log = LoggerFactory.getLogger(LocationController.class);
    private static final String GEOCODING_URI = "https://maps.googleapis.com/maps/api/geocode/json";
    private static final String OK_Response = "OK";
    private static final String Subject = "GeoLocator";

    private final JavaMailSender javaMailSender;

    public LocationController(LocationService _service, JavaMailSender _javaMailSender)
    {
        this.service = _service;
        this.javaMailSender = _javaMailSender;
    }

    @GetMapping(value = "/{address}")
    public ResponseEntity<LocationDto> get(
            @PathVariable(name = "address") String address
    )
    {
        LocationDto location = service.getLocationByAddress(address);
        if(location ==null)
        {
            RestTemplate restTemplate = new RestTemplate();
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(GEOCODING_URI).
                    queryParam("address", address)
                    .queryParam("key", apiKey);

            log.info("Calling geocoding api with: " + builder.toUriString());

            Geocode geoCoding = restTemplate.getForObject(builder.toUriString(), Geocode.class);
            log.info(geoCoding.toString());
            if(Objects.equals(geoCoding.getStatus(), OK_Response)){
                GeoCodingResult result= Arrays.stream(geoCoding.getGeoCodingResults()).findFirst().get();
                LocationDto locationDto =new LocationDto(address, result.getGeometry().getLocation().getLat(),
                        result.getGeometry().getLocation().getLng());
                service.save(locationDto);
                return new ResponseEntity<>(locationDto, HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(null, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(location, HttpStatus.BAD_REQUEST);

    }
    @PostMapping(value = "/sendEmail", consumes = { "multipart/form-data" })
    public void sendEmail(
            @ModelAttribute("teacher") SendEmailDto sendEmailDto
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(sendEmailDto.getEmail());
        message.setSubject(Subject);

        String body = String.format("The address: %s is has a location (%s,%s)",
                sendEmailDto.getAddress(), sendEmailDto.getLat(), sendEmailDto.getLng());
        message.setText(body);

        javaMailSender.send(message);
    }
}
