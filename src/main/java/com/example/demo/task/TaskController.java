package com.example.demo.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.SampleDao;
import com.example.demo.entity.EntForm;

@Controller
public class TaskController {

	@RequestMapping("/form")
	public String form(Model model, Input input) {
		model.addAttribute("title", "todoリスト");
		return "form";
	}

	@RequestMapping("/confirm")
	public String confirm(@Validated Input input, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("title", "入力ページ");
			return "form";
		}

		model.addAttribute("title", "確認ページ");
		return "confirm";
	}

	private final SampleDao sampledao;

	@Autowired
	public TaskController(SampleDao sampledao) {
		this.sampledao = sampledao;
	}

	@RequestMapping("/complete")
	public String complete(Model model, Input input) {
		EntForm entform = new EntForm();
		entform.setTaskName(input.getTaskName());
		entform.setTaskType(input.getTaskType());
		entform.setDueDate(input.getDueDate());
		entform.setComment(input.getComment());
		sampledao.insertDb(entform);
		return "complete";
	}

	//	全件表示
	@RequestMapping("/view")
	public String view(Model model) {
		List<EntForm> list = sampledao.getAll();
		model.addAttribute("dbList", list);
		model.addAttribute("title", "一覧ページ");
		return "view";
	}

	//削除
	@RequestMapping("/del/{id}")
	public String destory(@PathVariable Long id) {
		sampledao.deleteSample(id);
		return "redirect:/view";
	}
	
	//削除
	@RequestMapping("/comp/{id}")
	public String comp(@PathVariable Long id) {
		sampledao.compSample(id);
		return "redirect:/view";
	}

	//編集画面の表示
	@RequestMapping("/edit/{id}")
	public String editView(@PathVariable Long id, Model model) {
		List<EntForm> list = sampledao.getOne(id);
		EntForm entform = list.get(0);
		model.addAttribute("entform", entform);
		model.addAttribute("title", "編集ページ");
		return "edit";
	}

	//更新
	@RequestMapping("/edit/{id}/exe")
	public String editExe(@PathVariable Long id, Model model, Input input) {
		EntForm entform = new EntForm();
		System.out.println(input.getTaskType());
		System.out.println(input.getDueDate());
		System.out.println(input.getTaskName());
		System.out.println(input.getComment());
		
		entform.setTaskType(input.getTaskType());
		entform.setDueDate(input.getDueDate());
		entform.setTaskName(input.getTaskName());
		entform.setComment(input.getComment());
		
		sampledao.updateSample(id, entform);

		return "redirect:/view";
	}

}
