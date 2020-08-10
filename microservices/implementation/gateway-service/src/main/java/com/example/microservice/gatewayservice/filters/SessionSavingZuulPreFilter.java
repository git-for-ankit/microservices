package com.example.microservice.gatewayservice.filters;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Component;
import com.netflix.zuul.ZuulFilter;

@Component
public class SessionSavingZuulPreFilter extends ZuulFilter {
	private final Logger LOGGER = LoggerFactory.getLogger(SessionSavingZuulPreFilter.class);
	
	@Autowired
	private SessionRepository sessionRepository;

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		LOGGER.info("EXECFLOW -> SessionSavingZuulPreFilter -> run()");

		RequestContext context = RequestContext.getCurrentContext();
		HttpSession httpSession = context.getRequest().getSession(false);
		if (httpSession != null) {

			Session session = sessionRepository.findById(httpSession.getId());
			session.setAttribute("msg","Ankit"); 
			String cookieValue = "";
			if (context.getRequest().getCookies() != null) {
				for (Cookie cookie : context.getRequest().getCookies()) {
					if (cookie.getName().equals("SESSION")) {
						cookieValue += cookie.getName() + "=" + cookie.getValue() + ";";
					}
				}
			}

			cookieValue += "SESSION=" + httpSession.getId();
			context.addZuulRequestHeader("Cookie", cookieValue);
			LOGGER.info("ZuulPreFilter session proxy: {}", cookieValue);
		}

		return null;

	}

}
