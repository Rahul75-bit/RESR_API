package com.rahul.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.rahul.entity.Role;
import com.rahul.entity.User;
import com.rahul.repository.RoleRepository;
import com.rahul.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
	
	  private final RoleRepository roleRepository;
	    private final UserRepository userRepository;
	    private final PasswordEncoder passwordEncoder;

	    @Override
	    public void run(String... args) {

	        Role adminRole = roleRepository.save(new Role(null, "Admin"));
	        Role managerRole = roleRepository.save(new Role(null, "Manager"));
	        Role userRole = roleRepository.save(new Role(null, "User"));

	        User admin = new User();
	        admin.setName("Admin");
	        admin.setEmail("admin@gmail.com");
	        admin.setPassword(passwordEncoder.encode("admin123"));
	        admin.setRoles(List.of(adminRole));

	        User manager = new User();
	        manager.setName("Manager");
	        manager.setEmail("manager@gmail.com");
	        manager.setPassword(passwordEncoder.encode("manager123"));
	        manager.setRoles(List.of(managerRole));

	        User user = new User();
	        user.setName("Rahul");
	        user.setEmail("user@gmail.com");
	        user.setPassword(passwordEncoder.encode("user123"));
	        user.setRoles(List.of(userRole));

	        userRepository.save(admin);
	        userRepository.save(manager);
	        userRepository.save(user);
	    }
	
	

}
