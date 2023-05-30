package com.example.demo.task;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.SampleDao;
import com.example.demo.entity.EntForm;
import com.example.demo.entity.UserEntForm;

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

	@RequestMapping("/signup")
	public String signup(Model model, UserInput userinput) {
		model.addAttribute("title", "todoリスト");
		return "signup";
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
		entform.setComment(input.getComment());
		entform.setTaskType(input.getTaskType());
		entform.setDueDate(input.getDueDate());
		sampledao.insertDb(entform);
		return "complete";
	}

	@RequestMapping("/register")
	public String complete(Model model, UserInput userinput) {
		UserEntForm userentform = new UserEntForm();
		userentform.setLogId(userinput.getLogId());
		userentform.setPass(userinput.getPass());
		sampledao.insertDb2(userentform);
		return "register";
	}
	
	@PostMapping("/login")
    public String login(Model model, @RequestParam("logId") String logId, @RequestParam("pass") String pass) {
        UserEntForm user = SampleDao.findByUsername(logId);
        model.addAttribute("title", "ログイン");

        if (user != null) {
            if (user.getPass().equals(pass)) {
                // ログイン成功の処理
                model.addAttribute("result", "ログイン成功");
                return "redirect:/form"; // タスクviewにリダイレクト
            } else {
                // パスワードが間違っている場合の処理
                model.addAttribute("result", "パスワードが違います");
                return "login";
            }
        } else {
            // ユーザー名が間違っている場合の処理
            model.addAttribute("result", "ユーザー名が登録されていません");
            return "login";
        }
	}

	//	全件表示
	@RequestMapping("/view")
	public String view(Model model) {
		List<EntForm> list = sampledao.getAll();
		LocalDate nowDate = LocalDate.now();
		for (EntForm entForm : list) {
			LocalDate dueDate = entForm.getDueDate();

			if (nowDate.isEqual(dueDate)) {
				System.out.println("黄色表示");
			} else if (nowDate.isAfter(dueDate)) {
				System.out.println("赤色表示");
			} else {
				System.out.println("何もしない");
			}
		}
		model.addAttribute("nowDate", nowDate);
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

	//編集画面の表示
	@RequestMapping("/edit/{id}")
	public String editView(@PathVariable Long id, Model model) {
		List<EntForm> list = sampledao.getOne(id);
		EntForm entform = list.get(0);
		model.addAttribute("entform", entform);
		model.addAttribute("title", "編集ページ");
		return "edit";
	}

	//日付ソート
	@RequestMapping("/sort")
	public String Sort(@RequestParam("sort") String sort, Model model) {
		System.out.println(sort);
		List<EntForm> list = sampledao.getSort(sort);
		model.addAttribute("dbList", list);
		model.addAttribute("title", "一覧ページ");
		return "view";
	}

	//更新
	@RequestMapping("/edit/{id}/exe")
	public String editExe(@PathVariable Long id, Model model, Input input) {
		EntForm entform = new EntForm();
		System.out.println(input.getTaskName());
		System.out.println(input.getTaskType());
		System.out.println(input.getDueDate());
		System.out.println(input.getComment());

		entform.setTaskName(input.getTaskName());
		entform.setTaskType(input.getTaskType());
		entform.setDueDate(input.getDueDate());
		entform.setComment(input.getComment());

		sampledao.updateSample(id, entform);

		return "redirect:/view";
	}

	//検索
	@RequestMapping("/search")
	public String Search(@RequestParam("search") String searchTerm, Model model) {
		List<EntForm> list = sampledao.getSearch(searchTerm);
		model.addAttribute("search", searchTerm);
		model.addAttribute("dbList", list);
		model.addAttribute("title", "タスク検索結果");
		model.addAttribute("word", "検索タスク「" + searchTerm + "」");
		return "search";
	}

}
