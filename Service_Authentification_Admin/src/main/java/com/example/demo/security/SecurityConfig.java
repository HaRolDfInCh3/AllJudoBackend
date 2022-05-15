package com.example.demo.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.example.demo.SharedLib;
import com.example.demo.entities.Admin;
import com.example.demo.repositories.AdminRepository;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AdminRepository adminRepo;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//le methode suivante va etre executée au moment de l'authentifiction pour
		//chercher l'utilisateur 
		auth.userDetailsService(new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				Admin admin=adminRepo.findByLogin(username);
				SharedLib.role=admin.getPermission().name();
				Collection<GrantedAuthority> authorities=new ArrayList<>();
				if(admin!=null && admin.getPermission()!=null) {
					authorities.add(new SimpleGrantedAuthority(admin.getPermission().name()));			
					return new org.springframework.security.core.userdetails.User(admin.getLogin(), admin.getPassword(), authorities);

				}
				 throw new UsernameNotFoundException("Admin not found");
				
			}
		});
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
		//http.formLogin();//activer un formulaire d'authentifcation seulement pour statefull
		//http.authorizeRequests().anyRequest().permitAll();//aucune requette ne necessite une authentification prealable
		//je ne peux pas envoyer de requette post 
		http.csrf().disable();// spring security ne va pas generer le synchronizer token pour le placer dans la session utiliser le en cas de stateless
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.headers().frameOptions().disable(); //desactiver la protection contre les frames pour un site qui les utilise
		http.authorizeRequests().antMatchers("/refreshToken/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/users/**").hasAnyAuthority("Super_Admin");
		http.authorizeRequests().anyRequest().authenticated();//toutes les requettes necessitent une authentifiation

		//installer le filtre qui va traiter le formulaire
		http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));
		http.addFilterBefore(new JwtAutorisationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override @Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
	
}
