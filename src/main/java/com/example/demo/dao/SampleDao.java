package com.example.demo.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EntForm;
import com.example.demo.entity.UserEntForm;

@Repository
public class SampleDao {
	private static JdbcTemplate db = new JdbcTemplate();

	@Autowired
	public SampleDao(JdbcTemplate db) {
		SampleDao.db = db;
	}

	public void insertDb(EntForm entform) {
		db.update("INSERT INTO task(taskType,dueDate,taskName,comment) VALUES(?,?,?,?)", entform.getTaskType(),
				entform.getDueDate(), entform.getTaskName(), entform.getComment());
	}

	public void insertDb2(UserEntForm userentform) {
		db.update("INSERT INTO login(logId,pass) VALUES(?,?)", userentform.getLogId(), userentform.getPass());
	}

	public List<EntForm> getAll() {

		List<Map<String, Object>> queryResult = db.queryForList("SELECT * FROM task");
		List<EntForm> dataList = new ArrayList<EntForm>();

		for (Map<String, Object> record : queryResult) {
			EntForm entformItem = new EntForm();

			entformItem.setId((int) record.get("id"));

			Date sqlDate = (Date) record.get("dueDate");
			LocalDate localdate = sqlDate.toLocalDate();
			entformItem.setDueDate(localdate);
			entformItem.setTaskType((String) record.get("taskType"));
			entformItem.setTaskName((String) record.get("taskName"));
			entformItem.setComment((String) record.get("comment"));

			dataList.add(entformItem);
		}
		return dataList;
	}

	public List<UserEntForm> getAll2() {

		List<Map<String, Object>> userqueryResult = db.queryForList("SELECT * FROM login");
		List<UserEntForm> userdataList = new ArrayList<UserEntForm>();

		for (Map<String, Object> record : userqueryResult) {
			UserEntForm userentformItem = new UserEntForm();

			userentformItem.setId((int) record.get("id"));

			userentformItem.setLogId((String) record.get("logId"));
			userentformItem.setPass((String) record.get("pass"));

			userdataList.add(userentformItem);
		}
		return userdataList;
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

			entformItem.setDueDate((LocalDate) record.get("dueDate"));
			entformItem.setTaskType((String) record.get("taskType"));
			entformItem.setTaskName((String) record.get("taskName"));
			entformItem.setComment((String) record.get("comment"));
			dataList.add(entformItem);
		}
		return dataList;
	}

	public void updateSample(Long id, EntForm entform) {
		System.out.println("編集の実行");
		db.update("UPDATE task SET taskType=?,dueDate=?,taskName = ?,comment = ? WHERE id = ?", entform.getTaskType(),
				entform.getDueDate(), entform.getTaskName(), entform.getComment(), id);
	}

	//検索
	public List<EntForm> getSearch(String taskType) {
		String searchTerm = "%" + taskType + "%";
		List<Map<String, Object>> queryResult = db.queryForList("SELECT * FROM task WHERE taskType LIKE ?", searchTerm);
		List<EntForm> dataList = new ArrayList<EntForm>();

		for (Map<String, Object> record : queryResult) {
			EntForm entformItem = new EntForm();

			entformItem.setId((int) record.get("id"));
			Date sqlDate = (Date) record.get("dueDate");
			LocalDate localDate = sqlDate.toLocalDate();
			entformItem.setDueDate(localDate);
			entformItem.setTaskType((String) record.get("taskType"));
			entformItem.setTaskName((String) record.get("taskName"));
			entformItem.setComment((String) record.get("comment"));

			dataList.add(entformItem);
		}
		return dataList;
	}

	public List<EntForm> getSort(String sort) {
		List<Map<String, Object>> queryResult = db.queryForList("SELECT * FROM task");
		List<EntForm> dataList = new ArrayList<EntForm>();
		if (sort.equals("ASC")) {
			queryResult = db.queryForList("SELECT * FROM task ORDER BY dueDate ASC");
			//			List<EntForm> dataList = new ArrayList<EntForm>();
			System.out.println("昇順ソート");
		}
		if (sort.equals("DESC")) {
			queryResult = db.queryForList("SELECT * FROM task ORDER BY dueDate DESC");
			System.out.println("降順ソート");
		}

		for (Map<String, Object> record : queryResult) {
			EntForm entformItem = new EntForm();

			entformItem.setId((int) record.get("id"));
			Date sqlDate = (Date) record.get("dueDate");
			LocalDate localDate = sqlDate.toLocalDate();
			entformItem.setDueDate(localDate);
			entformItem.setTaskType((String) record.get("taskType"));
			entformItem.setTaskName((String) record.get("taskName"));
			entformItem.setComment((String) record.get("comment"));

			dataList.add(entformItem);
		}
		return dataList;
	}
	
	public List<EntForm> getShSort(String sort, String taskType) {

		List<Map<String, Object>> queryResult = db.queryForList("SELECT * FROM task");
		List<EntForm> dataList = new ArrayList<EntForm>();
		String searchTerm = "%" + taskType + "%";
		if (sort.equals("ASC")) {
			queryResult = db.queryForList("SELECT * FROM task WHERE taskType LIKE ? ORDER BY dueDate ASC", searchTerm);
			System.out.println("昇順ソート");
		}
		if (sort.equals("DESC")) {
			queryResult = db.queryForList("SELECT * FROM task WHERE taskType LIKE ? ORDER BY dueDate DESC", searchTerm);
			System.out.println("降順ソート");
		}
		for (Map<String, Object> record : queryResult) {
			EntForm entformItem = new EntForm();

			entformItem.setId((int) record.get("id"));
			Date sqlDate = (Date) record.get("dueDate");
			LocalDate localDate = sqlDate.toLocalDate();
			entformItem.setDueDate(localDate);
			entformItem.setTaskType((String) record.get("taskType"));
			entformItem.setTaskName((String) record.get("taskName"));
			entformItem.setComment((String) record.get("comment"));

			dataList.add(entformItem);
		}
		return dataList;
	}

	
	public static UserEntForm findByUsername(String logId) {
	    String sql = "SELECT * FROM login WHERE logId = ?";
	    List<Map<String, Object>> rows = db.queryForList(sql, logId);

	    if (!rows.isEmpty()) {
	        Map<String, Object> userData = rows.get(0);
	        UserEntForm user = new UserEntForm();
	        user.setId((int) userData.get("id"));
	        user.setLogId((String) userData.get("logId"));
	        user.setPass((String) userData.get("pass"));
	        return user;
	    }

	    return null;
	}

}
