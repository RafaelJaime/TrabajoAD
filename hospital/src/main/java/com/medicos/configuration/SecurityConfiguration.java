package com.medicos.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.medicos.service.LoginService;
import com.medicos.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private LoginService loginDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
    	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    	auth.userDetailsService(loginDetailsService).passwordEncoder(bcrypt);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    		.authorizeRequests()
    		.antMatchers("/","/contact", "/about", "/build/**", "/dist/**", "/images/**", "/docs/**", "/logo/**",
                    "/pages/**", "/plugins/**", "/register/**","/process_register/**", "/api/**")
            .permitAll()
            .antMatchers("/medic/**").hasAnyRole("ADMIN")
            .antMatchers("/medicine/**").hasAnyRole("ADMIN")
            .antMatchers("/patient/**").hasAnyRole("ADMIN")
            .antMatchers("/patients/**").hasAnyRole("PATIENT")
            .antMatchers("/medicalAppointment/**").hasAnyRole("MEDIC")
            .anyRequest().authenticated()
    		.and()    		
    		.formLogin()
    		.loginPage("/login").permitAll()
    		.and()
    		.httpBasic();
    }
}