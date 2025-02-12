package com.microservices.user_service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    @LoadBalanced
    // loadBalanced is used for calling the services that are connected with Eureka server by there name,
    // so there is no need to call them with the host. eg: localhost:8080 X || RATING-SERVICE or HOTEL-SERVICE
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
