package com.someonesmarter.todo.task;

import com.someonesmarter.todo.utility.ExcelGenerator;
import com.someonesmarter.todo.utility.PDFGenerator;
import com.someonesmarter.todo.security.User;
import com.someonesmarter.todo.security.UserService;
import com.someonesmarter.todo.security.UserUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Controller
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private UserService userService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}


	@GetMapping("/list")
	public ModelAndView listTasks() {
		Set<Task> tasks = taskService.findAllByUser();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("tasks/list-tasks");
		modelAndView.addObject("tasks", tasks);
		
		return modelAndView;

	}
	
	@GetMapping("/showFormForAdd")
	public ModelAndView showFormForAdd() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("tasks/task-form");
		Task theTask = new Task();
		modelAndView.addObject("task", theTask);
		
		return modelAndView;
	}
	
	@PostMapping("/save")
	public ModelAndView saveTask(@ModelAttribute("task") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Task theTask) {
		String username = UserUtilities.getLoggedUser();
		User user = userService.findUserByEmail(username);
		theTask.setUser(user);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/tasks/list");
		taskService.save(theTask);
		
		return modelAndView;
	}

	@GetMapping("/showFormForUpdate")
	public ModelAndView showFormForUpdate(@RequestParam("taskId") int theId) {
		ModelAndView modelAndView = new ModelAndView();

		Task theTask = taskService.findById(theId);
		if (taskService.belongToUser(theTask)) {
			modelAndView.addObject("task", theTask);
			modelAndView.setViewName("tasks/task-form");
		} else
		modelAndView.setViewName("errors/access_denied");
		return modelAndView;
	}

	@GetMapping("/delete")
	public ModelAndView delete(@RequestParam("taskId") int theId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/tasks/list");
		taskService.deleteById(theId);

		return modelAndView;
	}

	@GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> taskReport() throws IOException {
		Set<Task> customers = taskService.findAllByUser();

		ByteArrayInputStream bis = PDFGenerator.taskPDFReport(customers);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=tasks.pdf");

		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

	@GetMapping(value = "/xlsx")
	public ResponseEntity<InputStreamResource> excelTaskReport() throws IOException {
		Set<Task> tasks = taskService.findAllByUser();

		ByteArrayInputStream in = ExcelGenerator.tasksToExcel(tasks);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=tasks.xlsx");

		return ResponseEntity
				.ok()
				.headers(headers)
				.body(new InputStreamResource(in));
	}

}












