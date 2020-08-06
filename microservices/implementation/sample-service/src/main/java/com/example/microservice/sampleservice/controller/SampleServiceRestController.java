package com.example.microservice.sampleservice.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



@RestController
public class SampleServiceRestController {
	
	@RequestMapping(value="/welcome")
    public ModelAndView welcomePage(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("welcome");
		Object sessionData = request.getSession().getAttribute("MY_SESSION_MESSAGES");
        return modelAndView;
    }
    
}
