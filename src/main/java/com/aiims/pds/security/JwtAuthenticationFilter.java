package com.aiims.pds.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private final UserDetailsService userDetailsService;
	
	@Autowired
	private final JwtTokenService jwtTokenService;
	
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
			throws ServletException, IOException 
	{
		// 1.get token
		final String authHeader = request.getHeader("Authorization");
		
		//Bearer
		final String username;
		final String jwtToken;
		
		if(authHeader == null || !authHeader.startsWith("Bearer")) 
		{
			filterChain.doFilter(request, response);
			return;
		}
		
		jwtToken = authHeader.substring(7);
		username = jwtTokenService.extractUsername(jwtToken);
		
		// once we get the token, now validate
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) 
		{	
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);	
			if(this.jwtTokenService.isTokenValid(jwtToken, userDetails)) 
			{	
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}	
		}	
		filterChain.doFilter(request, response);
	}

}
