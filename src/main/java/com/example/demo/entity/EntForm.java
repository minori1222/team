package com.example.demo.entity;

import java.time.LocalDate;

public class EntForm {

	private int id;
	private String taskName;
	private String taskType;
	private String comment;
	private LocalDate dueDate;

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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
//	public int getNowDate() {
//		return nowDate;
//	}
//	
//	public void setNowDate(int nowDate) {
//		this.nowDate = nowDate;
//	}
	
	public LocalDate getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
}