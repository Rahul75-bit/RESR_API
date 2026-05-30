package com.rahul.dto;

import lombok.Data;

@Data
public class TaskRequest {
	
	private String title;
	private String description;
	private Long userId;

}
