package com.example.demo.task;

import jakarta.validation.constraints.NotBlank;


public class Input {
	@NotBlank(message ="文字を入力してください")
	private String taskName;
  
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


	
}
