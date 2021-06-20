package com.centime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class CentimeConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
