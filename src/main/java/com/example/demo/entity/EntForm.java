package com.example.demo.entity;

import java.time.LocalDate;

public class EntForm {

	private int id;
	private String taskName;
	private String taskType;
	//	private String dueDate;
	private LocalDate dueDate;
	private String comment;

	public EntForm() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	//	public String getDueDate() {
	//		return dueDate;
	//	}
	//
	//	public void setDueDate(String dueDate) {
	//		this.dueDate = dueDate;
	//	}

	public LocalDate getDueDate() {
		return this.dueDate;
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
