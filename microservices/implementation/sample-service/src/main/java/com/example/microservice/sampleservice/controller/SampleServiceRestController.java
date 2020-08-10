package com.example.microservice.sampleservice.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SampleServiceRestController {
	private final Logger LOGGER = LoggerFactory.getLogger(SampleServiceRestController.class);
	
	@Autowired
    private SessionRepository sessionRepository;
	
	@RequestMapping(value="/welcome")
	public ModelAndView welcomePage(HttpServletRequest request){
		LOGGER.info("EXECFLOW -> SampleServiceRestController -> welcomePage()");
		
		ModelAndView modelAndView = new ModelAndView("welcome");
		String sessionID = null;
		for(Cookie cookie : request.getCookies()) {
			if(cookie.getName().equals("SESSION"))
				sessionID = cookie.getValue();
		}
		
		Session session = sessionRepository.findById(request.getSession(false).getId());
		String sessionDATA = session.getAttribute("msg");
		String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		modelAndView.addObject("USERNAME", currentUserName);
		modelAndView.addObject("SESSION", sessionID);
		return modelAndView;
	}
    
}
