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

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserRepository userRepo;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//le methode suivante va etre executée au moment de l'authentifiction pour
		//chercher l'utilisateur 
		auth.userDetailsService(new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				List<User> user=userRepo.findByUsername(username);
				Collection<GrantedAuthority> authorities=new ArrayList<>();
				if(user.size()>0 && user.get(0).getRoles()!=null) {
					user.get(0).getRoles().forEach(
						role->{
							authorities.add(new SimpleGrantedAuthority(role.getDesignation().name()));
						}
						);				
					return new org.springframework.security.core.userdetails.User(user.get(0).getUsername(), user.get(0).getPassword(), authorities);

				}
				 throw new UsernameNotFoundException("User not found");
				
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
		http.authorizeRequests().antMatchers("/addUser/**").permitAll();
		//http.authorizeRequests().antMatchers(HttpMethod.POST,"/users/**").hasAnyAuthority("Super_Admin");
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/**").permitAll().anyRequest().authenticated();
//toutes les requettes necessitent une authentifiation

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
