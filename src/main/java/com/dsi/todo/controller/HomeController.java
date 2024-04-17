package com.dsi.todo.controller;

import com.dsi.todo.model.Task;
import com.dsi.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private final TaskService taskService;
    @Value("${spring.application.name}")
    private String appName;

    public HomeController(TaskService taskService) {
        this.taskService = taskService;
    }
    @ModelAttribute
    public List<Task> getTasks(Model model){
        List<Task> allTasks =  taskService.getAllTasks();
        model.addAttribute("tasks", allTasks);
        model.addAttribute("appName", appName);
        return allTasks;
    }
    @GetMapping
    public String index() {
        return "home";
    }
}
