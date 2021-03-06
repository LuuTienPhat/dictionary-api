package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.filter.CustomAuthenticationFilter;
import com.example.demo.filter.CustomAuthorizationFilter;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	private final UserDetailsService userDetailsService;
	private final BCryptPasswordEncoder btBCryptPasswordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(btBCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		CustomAuthenticationFilter customAuthenticationFilter = 
				new CustomAuthenticationFilter(authenticationManagerBean());
		customAuthenticationFilter.setFilterProcessesUrl("/users/login");
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/users/login/**", "/users/token/refresh/**").permitAll();
//		http.authorizeRequests().antMatchers(HttpMethod.GET,"/users/**").hasAnyAuthority("ROLE_MANAGER");
//		http.authorizeRequests().antMatchers(HttpMethod.POST,"/users/save/**").hasAnyAuthority("ROLE_MANAGER");
		http.authorizeRequests().antMatchers("/users/**").hasAnyAuthority("ROLE_MANAGER");
		
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/enwords/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/enwords/simplified/**").permitAll();
		http.authorizeRequests().antMatchers("/enwords/**").hasAnyAuthority("ROLE_ADMIN_WORD", "ROLE_MANAGER");
		http.authorizeRequests().antMatchers("/partofspeeches/**").hasAnyAuthority("ROLE_ADMIN_WORD", "ROLE_MANAGER");
		http.authorizeRequests().antMatchers("/meanings/**").hasAnyAuthority("ROLE_ADMIN_WORD", "ROLE_MANAGER");
		http.authorizeRequests().antMatchers("/examples/**").hasAnyAuthority("ROLE_ADMIN_WORD", "ROLE_MANAGER");
		
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/orders").hasAnyAuthority("ROLE_ADMIN_SALE", "ROLE_MANAGER", "ROLE_CUSTOMER");
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/products").hasAnyAuthority("ROLE_ADMIN_SALE", "ROLE_MANAGER", "ROLE_CUSTOMER");
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/products/**").hasAnyAuthority("ROLE_ADMIN_SALE", "ROLE_MANAGER", "ROLE_CUSTOMER");
		http.authorizeRequests().antMatchers("/categories/**").hasAnyAuthority("ROLE_ADMIN_SALE", "ROLE_MANAGER");
		http.authorizeRequests().antMatchers("/orders/**").hasAnyAuthority("ROLE_ADMIN_SALE", "ROLE_MANAGER");
		http.authorizeRequests().antMatchers("/products/**").hasAnyAuthority("ROLE_ADMIN_SALE", "ROLE_MANAGER");
		http.authorizeRequests().antMatchers("/invoices/**").hasAnyAuthority("ROLE_ADMIN_SALE", "ROLE_MANAGER");
		http.authorizeRequests().antMatchers("/uploads/**").permitAll();
		
		http.authorizeRequests().antMatchers("/feedbacks").permitAll();
		
		http.authorizeRequests().anyRequest().permitAll();
		http.addFilter(customAuthenticationFilter);
		http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
}
