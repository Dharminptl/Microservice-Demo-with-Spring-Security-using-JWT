package com.example.ApiGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    JwtRequestFilter filter;
    
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        System.out.println("//////////////////////////////");
        return builder.routes()
                .route("Movie-Catalog-Service",r -> r.path("/movie/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://Movie-Catalog-Service"))
                .build();
    }

}
