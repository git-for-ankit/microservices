package com.example.microservice.sampleservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.session.FlushMode;

@Configuration
@EnableRedisHttpSession()
public class SessionConfig extends AbstractHttpSessionApplicationInitializer {
	
}
