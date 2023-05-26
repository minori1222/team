package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EntForm;

@Repository
public class SampleDao {
	private final JdbcTemplate db;

	@Autowired
	public SampleDao(JdbcTemplate db) {
		this.db = db;
	}

	public void insertDb(EntForm entform) {
//		db.update("INSERT INTO task(taskName,comment) VALUES(?,?)", entform.getTaskName(), entform.getComment());
		db.update("INSERT INTO task(taskType,taskName, dueDate,comment) VALUES(?,?,?,?)",
				entform.getTaskType(), entform.getTaskName(),entform.getDueDate(), entform.getComment());
	}


	public List<EntForm> getAll() {

		List<Map<String, Object>> queryResult = db.queryForList("SELECT * FROM task");
		List<EntForm> dataList = new ArrayList<EntForm>();

		for (Map<String, Object> record : queryResult) {
			EntForm entformItem = new EntForm();

			entformItem.setId((int) record.get("id"));
			entformItem.setTaskType((String) record.get("taskType"));
			entformItem.setTaskName((String) record.get("taskName"));
			entformItem.setComment((String) record.get("comment"));

			dataList.add(entformItem);
		}
		return dataList;
	}

	//削除
	public void deleteSample(Long id) {
		System.out.println("削除しました");
		db.update("delete from task where id=?", id);
	}

	//編集
	public List<EntForm> getOne(Long id) {
		System.out.println("編集画面を出します");

		List<Map<String, Object>> queryResult = db.queryForList("SELECT * FROM task where id=?", id);
		List<EntForm> dataList = new ArrayList<EntForm>();

		for (Map<String, Object> record : queryResult) {
			EntForm entformItem = new EntForm();
			entformItem.setId((int) record.get("id"));
			entformItem.setTaskName((String) record.get("taskName"));
			entformItem.setComment((String) record.get("comment"));
			dataList.add(entformItem);
		}
		return dataList;
	}

	public void updateSample(Long id, EntForm entform) {
		System.out.println("編集の実行");
		db.update("UPDATE task SET taskName = ?,comment = ? WHERE id = ?",entform.getTaskName(),entform.getComment(),  id);
	}
	
	
	//検索
	public List<EntForm> getSearch(String taskType) {

		List<Map<String, Object>> queryResult = db.queryForList("SELECT * FROM task WHERE taskType =?",taskType);
		List<EntForm> dataList = new ArrayList<EntForm>();

		for (Map<String, Object> record : queryResult) {
			EntForm entformItem = new EntForm();

			entformItem.setId((int) record.get("id"));
			entformItem.setTaskName((String) record.get("taskName"));
			entformItem.setComment((String) record.get("comment"));

			dataList.add(entformItem);
		}
		return dataList;
	}

}
