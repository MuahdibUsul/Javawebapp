package com.veracode.verademo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import dev.aikido.agent_api.SetUser;

@Component
@Order(0)
public class SetUserFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession(false);
		if (session != null) {
			String username = (String) session.getAttribute("username");
			if (username != null) {
				SetUser.setUser(new SetUser.UserObject(username, username));
			}
		}
		chain.doFilter(request, response);
	}
}
