package com.example.demo.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private JwtTokensProvider jwtTokensProvider;
	private UserDetailsService detailsService;

	public JwtAuthenticationFilter(JwtTokensProvider jwtTokensProvider, UserDetailsService detailsService) {
		super();
		this.jwtTokensProvider = jwtTokensProvider;
		this.detailsService = detailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// get Jwt token from http request

		String token = getTokenFromRequest(request);

		// validate token
		if (StringUtils.hasText(token) && jwtTokensProvider.validateToken(token)) {
			String username = jwtTokensProvider.getUsername(token);

			// load the user associated with token
			UserDetails userDetails = detailsService.loadUserByUsername(username);

			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities());
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		    filterChain.doFilter(request, response);

	}

	private String getTokenFromRequest(HttpServletRequest httpServletRequest) {
		String bearerToken = httpServletRequest.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

}
