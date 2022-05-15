package com.example.demo.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.demo.SharedLib;
import com.example.demo.entities.Admin;
import com.example.demo.repositories.AdminRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RefreshTokenController {
	private AdminRepository adminRepo;
	
	public RefreshTokenController(AdminRepository userRepo) {
		this.adminRepo = userRepo;
	}

	@GetMapping(path = "/refreshToken")
	public void token(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String token=request.getHeader(SharedLib.header);
		if(token!=null && token.startsWith(SharedLib.prefix)) {
			
			try {
				String refreshToken=token.substring(SharedLib.prefixlength);
				Algorithm algorithm =Algorithm.HMAC256(SharedLib.secret);
				JWTVerifier verifier=JWT.require(algorithm).build();
				DecodedJWT decoded= verifier.verify(refreshToken);
				String username=decoded.getSubject();
				Admin admin=adminRepo.findByLogin(username);
				String jwtAccessToken=JWT.create().withSubject(admin.getLogin()).
						withExpiresAt(new Date(System.currentTimeMillis()+(SharedLib.expirationAccessToken)))
						.withIssuer(request.getRequestURL().toString())
						.withClaim("roles",admin.getPermission().name())
						.sign(algorithm);
				Map<String,String>idtoken=new HashMap<>();
				idtoken.put("jwtAccessToken", jwtAccessToken);
				idtoken.put("jwtRefreshToken", refreshToken);
				response.setContentType("application/json");
				new ObjectMapper().writeValue(response.getOutputStream(), idtoken);
				System.out.println("Votre refresh token est valide !");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				// TODO: handle exception
				response.setHeader("error message", e.getMessage());
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
			}
			
		}else {
			System.out.println("Votre token est invalide...");
		}
	}
}
