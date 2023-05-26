package com.example.demo.task;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;


public class Input {
	@NotBlank(message ="文字を入力してください")
	private String taskName;
	
	private String taskType;
	private LocalDate dueDate;
	@NotBlank(message ="文字を入力してください")
	private String comment;

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

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


	
}
