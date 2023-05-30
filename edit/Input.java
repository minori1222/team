package com.example.demo.task;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class Input {
	
	private String taskType;
	//下記フィールド変数のデータ型を修正したことにより、データ型の変換を随所に行いました。
	@NotNull(message="期日を選択してください")
	private LocalDate dueDate;

	@NotBlank(message ="文字を入力してください")
	private String taskName;
	@NotBlank(message ="文字を入力してください")
	private String comment;
//	private String search;
//
//	public String getSearch() {
//		return search;
//	}
//
//	public void setSearch(String search) {
//		this.search = search;
//	}

	public Input() {
	}
	
	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	
	
	
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
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
