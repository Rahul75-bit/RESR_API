package com.rahul.security;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rahul.entity.User;
import com.rahul.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	 private final UserRepository userRepository;

	    @Override
	    public UserDetails loadUserByUsername(String email) 
	            throws UsernameNotFoundException {

	        User user = userRepository.findByEmail(email)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

	        return new org.springframework.security.core.userdetails.User(
	                user.getEmail(),
	                user.getPassword(),
	                user.getRoles()
	                        .stream()
	                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
	                        .collect(Collectors.toList())
	        );
	    }

}
