package com.nandemokaki.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.nandemokaki.service.UserService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

     @Autowired
     UserService userService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter, CsrfFilter.class);

		http
               .csrf().disable()
               .authorizeRequests()
               		.antMatchers("/").permitAll()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/login/getmail").permitAll()
                    .antMatchers("/js/*").permitAll()
                    .antMatchers("/css/*").permitAll()
                    .antMatchers("/user").hasAuthority("1")
                    .antMatchers("/admin").hasAuthority("0")
//                    .anyRequest().authenticated()
                    .and()
//               .formLogin()
//                    .and()
               .logout();
     }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(userService.passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}