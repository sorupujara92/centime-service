package com.centime.controller;

import com.centime.ValidateRequestBody;
import com.centime.model.ServiceRequestBody;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api/v1/s1")
public class Service1 {

    String s2Url;
    String s3Url;

    @Autowired
    RestTemplate restTemplate;
    public static final Logger LOGGER = LoggerFactory.getLogger(Service1.class);

    Service1(@Value("${s2.url}") String s2Url,
             @Value("${s3.url}") String s3Url) {
        this.s2Url = s2Url;
        this.s3Url = s3Url;
    }

    @ApiOperation(value = "Checks if service is up or not")
    @GetMapping("/check-service")
    public String checkService() {
        return "Up";
    }

    @ApiOperation(value = "Concatenate the fial result")
    @PostMapping("/final-result")
    public ResponseEntity<String> getConcatinatedString(@RequestBody ServiceRequestBody serviceRequestBody) {
        ValidateRequestBody.validateServiceRequest(serviceRequestBody);
        LOGGER.info("Hitting s2");
        ResponseEntity<String> exchange =
                restTemplate.exchange(s2Url, HttpMethod.GET, null, String.class);
        String s2response = exchange.getBody();
        LOGGER.info("Response from s2 "+s2response);
        HttpEntity<ServiceRequestBody> requestPayload = new HttpEntity<>(serviceRequestBody, null);
        LOGGER.info("Hitting s3 with"+serviceRequestBody.getName()+","+serviceRequestBody.getSurName());
        exchange = restTemplate.exchange(s3Url, HttpMethod.POST, requestPayload, String.class);
        String s3response = exchange.getBody();
        LOGGER.info("Response from s3 "+s3response);
        return ResponseEntity.ok(s2response+" "+s3response);
    }

}
