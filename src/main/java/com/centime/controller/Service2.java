package com.centime.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/s2")
public class Service2 {
    public static final Logger LOGGER = LoggerFactory.getLogger(Service2.class);

    @ApiOperation(value = "Returns hello")
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        LOGGER.info("reached s2");
        return ResponseEntity.ok("Hello");
    }

}
