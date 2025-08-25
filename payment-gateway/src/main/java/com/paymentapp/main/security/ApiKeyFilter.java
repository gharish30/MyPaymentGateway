package com.paymentapp.main.security;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ApiKeyFilter extends OncePerRequestFilter {

	private final String header, value;
	private final boolean enabled;

	public ApiKeyFilter(String header, String value, boolean enabled) {
		this.header = header;
		this.value = value;
		this.enabled = enabled;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (!enabled) {
			filterChain.doFilter(request, response);
			return;
		}
		String provided = request.getHeader(header);
		if (provided == null || !provided.equals(value)) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().write("{\"error\":\"Invalid or missing API key\"}");
			return;
		}
		filterChain.doFilter(request, response);
	}

}
