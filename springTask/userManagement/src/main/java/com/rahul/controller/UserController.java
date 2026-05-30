package com.rahul.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahul.entity.Task;
import com.rahul.entity.User;
import com.rahul.repository.TaskRepository;
import com.rahul.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins =  "http://localhost:5173")
public class UserController {
	
	  private final UserRepository userRepository;
	    private final TaskRepository taskRepository;

	    @GetMapping("/profile")
	    public User profile(Authentication authentication) {

	        String email = authentication.getName();

	        return userRepository.findByEmail(email)
	                .orElseThrow(() -> new RuntimeException("User not found"));
	    }

	    @GetMapping("/tasks")
	    public List<Task> myTasks(Authentication authentication) {

	        String email = authentication.getName();

	        User user = userRepository.findByEmail(email)
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        return taskRepository.findByUser(user);
	    }

}
