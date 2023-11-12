package com.osa.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.osa.service.JwtService;
import com.osa.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	
	public static String CURRENT_USER="";
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String header=request.getHeader("Authorization");
		String jwtToken=null;
		String userName=null;
		if(header!=null && header.startsWith("Bearer ")) {
			jwtToken=header.substring(7);
			try {
				userName=jwtUtil.getUserNameFromToken(jwtToken);
				CURRENT_USER=userName;
			}catch(IllegalArgumentException e) {
				System.out.println("jwt token illegal arguments");
				
			}catch(ExpiredJwtException e) {
				System.out.println("jwt token expired");
				
			}
			
		}else {
			System.out.println("jwt token doesn't start with bearer");
		}
		if(userName!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
			
			UserDetails userDetails= jwtService.loadUserByUsername(userName);
			if(jwtUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		
		filterChain.doFilter(request, response);
		
		
	}
	

}
