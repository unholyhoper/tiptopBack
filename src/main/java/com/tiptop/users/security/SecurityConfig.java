package com.tiptop.users.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService).
		      passwordEncoder(bCryptPasswordEncoder);
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/add").permitAll();
		http.authorizeRequests().antMatchers("/all").hasAuthority("ADMIN");
		http.authorizeRequests().antMatchers("/users/{username}").hasAuthority("ADMIN");
		http.authorizeRequests().antMatchers("/getTicketByUserId/{userId}").hasAuthority("ADMIN");
		http.authorizeRequests().antMatchers("/jeux-concours").hasAuthority("ADMIN");
		http.authorizeRequests().antMatchers("/tickets").permitAll();
		http.authorizeRequests().antMatchers("/addTicket").hasAuthority("ADMIN");
		http.authorizeRequests().antMatchers("/getTickets").permitAll();
		http.authorizeRequests().antMatchers("/getYearWinner").hasAuthority("ADMIN");
		http.authorizeRequests().antMatchers("/asignTicket/{userId}").hasAuthority("USER");
		http.authorizeRequests().antMatchers("/updateTicket/{ticketNumber}").hasAuthority("USER");
		http.authorizeRequests().antMatchers("/getAllTickets").permitAll();
		http.authorizeRequests().antMatchers("/getUserId/{username}").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter (authenticationManager())) ;
		http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);



	}
	
	

}
