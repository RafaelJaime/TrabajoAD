package com.medicos.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.medicos.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.antMatchers("/","/photos/**","/auth/**","/webjars/**","/css/**","/patient/**","/medicine/**","/medic/**","/files/**").permitAll()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/auth/login")
			.defaultSuccessUrl("/patient/",true)
			.loginProcessingUrl("/auth/login-post")
			.permitAll()
			.and()
		.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/auth/login?logout")
			.permitAll();
	}
}
