package com.example.demo.task;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;


public class Input {
	@NotBlank(message ="文字を入力してください")
	private String taskName;
	
	private String taskType;
  
	@NotBlank(message ="文字を入力してください")
	private String comment;
	
	private static LocalDate dueDate;

	public Input() {
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public static  LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}


	
}
