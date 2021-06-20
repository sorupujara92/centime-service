package com.centime.controller;

import com.centime.ValidateRequestBody;
import com.centime.exceptions.BadRequestException;
import com.centime.model.ServiceRequestBody;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/s3")
public class Service3 {
    public static final Logger LOGGER = LoggerFactory.getLogger(Service3.class);

    @ApiOperation(value = "Returns joined names")
    @PostMapping("/concatenate-names")
    public String concatenateElements(@RequestBody ServiceRequestBody serviceRequestBody) {
        LOGGER.info("reached s3");
        ValidateRequestBody.validateServiceRequest(serviceRequestBody);
        return serviceRequestBody.getName()+" "+ serviceRequestBody.getSurName();
    }
}
