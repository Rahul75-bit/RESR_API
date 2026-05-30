package com.rahul.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahul.entity.Task;
import com.rahul.entity.User;

public interface TaskRepository extends JpaRepository<Task,Long> {

	  List<Task> findByUser(User user);
}
