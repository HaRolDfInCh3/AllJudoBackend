package com.example.demo.security;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.SharedLib;
import com.example.demo.entities.Admin;
import com.example.demo.repositories.AdminRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	private AuthenticationManager authenticationManager;
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// sera utilisee quand l'utilisateur va tempter de s'authentifier
		System.out.println("tentative d'authentification");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		try {
			if(username==null || password==null) {
			throw new ParamsNotFoundException("pas de parametres envoyes ");
		}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(username, password);
		return authenticationManager.authenticate(authToken);
	}
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// sera utilisee quand l'autentification reussie
		System.out.println("autentification reussie");
		User user=(User) authResult.getPrincipal();
		Algorithm algorithm =Algorithm.HMAC256(SharedLib.secret);
		String jwtAccessToken=JWT.create().withSubject(user.getUsername()).
				withExpiresAt(new Date(System.currentTimeMillis()+(SharedLib.expirationAccessToken)))
				.withIssuer(request.getRequestURL().toString())
				.withClaim("roles",SharedLib.role )
				.sign(algorithm);
		String jwtRefreshToken=JWT.create().withSubject(user.getUsername()).
				withExpiresAt(new Date(System.currentTimeMillis()+(SharedLib.expirationRefreshToken)))
				.withIssuer(request.getRequestURL().toString())
				.sign(algorithm);
		Map<String,String>idtoken=new HashMap<>();
		idtoken.put("jwtAccessToken", jwtAccessToken);
		idtoken.put("jwtRefreshToken", jwtRefreshToken);
		response.setContentType("application/json");
		new ObjectMapper().writeValue(response.getOutputStream(), idtoken);
		super.successfulAuthentication(request, response, chain, authResult);
	}
}
