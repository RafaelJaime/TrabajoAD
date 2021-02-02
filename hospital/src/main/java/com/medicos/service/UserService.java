package com.medicos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.medicos.interfaces.IUser;
import com.medicos.model.User;

@Service("userService")
public class UserService implements UserDetailsService{
	
	@Autowired
	@Qualifier("userRepository")
	private IUser userRepository;
	
	private BCryptPasswordEncoder passwordEncoder;
	
	public com.medicos.model.User register(com.medicos.model.User user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEnabled(true);
		user.setRole("ROLE_USER");
		return userRepository.save(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.medicos.model.User user=userRepository.findByUsername(username);
		
		UserBuilder builder=null;
		
		if(user!=null) {
			builder=User.withUsername(username);
			builder.disabled(false);
			builder.password(user.getPassword());
			builder.authorities(new SimpleGrantedAuthority(user.getRole()));
		}else {
			throw new UsernameNotFoundException("User not found");
		}
		return builder.build();
	}

}
