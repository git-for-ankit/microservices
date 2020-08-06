package com.example.microservice.gatewayservice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.microservice.gatewayservice.clients.SampleServiceClient;


@Controller
public class LoginController {
	@Autowired
	SampleServiceClient serviceCall;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/login")
    public String showLoginPage(HttpServletRequest request){
		List<String> msgs = (List<String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
		if (msgs == null) {
			msgs = new ArrayList<>();
			request.getSession().setAttribute("MY_SESSION_MESSAGES", msgs);
		}
		msgs.add("session message");
		request.getSession().setAttribute("MY_SESSION_MESSAGES", msgs);
		
        return "login";
    }
    
}
