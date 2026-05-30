package com.rahul.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahul.dto.TaskRequest;
import com.rahul.entity.Task;
import com.rahul.entity.User;
import com.rahul.repository.TaskRepository;
import com.rahul.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
@CrossOrigin(origins =  "http://localhost:5173")
public class ManagerController {
	
	 private final UserRepository userRepository;
	    private final TaskRepository taskRepository;

	    @GetMapping("/users")
	    public List<User> viewUsers() {
	        return userRepository.findAll();
	    }

	    @PostMapping("/tasks")
	    public Task assignTask(@RequestBody TaskRequest request) {

	        User user = userRepository.findById(request.getUserId())
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        Task task = new Task();
	        task.setTitle(request.getTitle());
	        task.setDescription(request.getDescription());
	        task.setUser(user);

	        return taskRepository.save(task);
	    }

}
