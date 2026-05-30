package com.rahul.controller;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahul.entity.Role;
import com.rahul.entity.User;
import com.rahul.repository.RoleRepository;
import com.rahul.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {
	
	 private final UserRepository userRepository;
	    private final RoleRepository roleRepository;
	    private final PasswordEncoder passwordEncoder;

	    @GetMapping("/users")
	    public List<User> getAllUsers() {
	        return userRepository.findAll();
	    }

	    @PostMapping("/users")
	    public User createUser(@RequestBody User user) {

	        if (userRepository.existsByEmail(user.getEmail())) {
	            throw new RuntimeException("Email already exists");
	        }

	        user.setPassword(passwordEncoder.encode(user.getPassword()));

	        return userRepository.save(user);
	    }

	    @PutMapping("/users/{id}")
	    public User updateUser(@PathVariable Long id, @RequestBody User newUser) {

	        User user = userRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        user.setName(newUser.getName());
	        user.setEmail(newUser.getEmail());

	        return userRepository.save(user);
	    }

	    @DeleteMapping("/users/{id}")
	    public String deleteUser(@PathVariable Long id) {

	        userRepository.deleteById(id);

	        return "User deleted successfully";
	    }

	    @PutMapping("/users/{userId}/roles/{roleId}")
	    public User assignRole(
	            @PathVariable Long userId,
	            @PathVariable Long roleId
	    ) {

	        User user = userRepository.findById(userId)
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        Role role = roleRepository.findById(roleId)
	                .orElseThrow(() -> new RuntimeException("Role not found"));

	        user.getRoles().add(role);

	        return userRepository.save(user);
	    }

}
