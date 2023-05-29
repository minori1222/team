package com.example.demo.entity;

import java.time.LocalDate;

public class DateTimeComparison {

	public DateTimeComparison() {

		LocalDate nowDate = LocalDate.now();

		if (nowDate.isEqual(EntForm.getDueDate())) {
			System.out.println("黄色表示");
		} else if (nowDate.isAfter(EntForm.getDueDate())) {
			System.out.println("赤色表示");
		} else {
			System.out.println("何もしない");
		}
	}
}
