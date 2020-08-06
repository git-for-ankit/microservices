package com.example.microservice.gatewayservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("sample-service")
public interface SampleServiceClient {
	@RequestMapping(value="/hello")
    public String getGreeting();
}
