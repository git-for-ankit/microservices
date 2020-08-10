package com.example.microservice.gatewayservice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.microservice.gatewayservice.clients.SampleServiceClient;
import com.example.microservice.gatewayservice.filters.SessionSavingZuulPreFilter;

@Controller
public class LoginController {
	private final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	SampleServiceClient serviceCall;		//in future for internal calls.
	
	@RequestMapping(value="/login")
    public String showLoginPage(HttpServletRequest request, HttpSession session){
		LOGGER.info("EXECFLOW -> LoginController -> showLoginPage()");
		return "login";
    }
	
}
