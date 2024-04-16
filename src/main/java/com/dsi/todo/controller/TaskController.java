package com.dsi.todo.controller;

import com.dsi.todo.model.Task;
import com.dsi.todo.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(value = "addTask")
    public ModelAndView addTask(@ModelAttribute Task task) {


        ModelAndView modelAndView = new ModelAndView("redirect:/");
        modelAndView.addObject("tasks", taskService.getAllTasks());
//        task.setCreatedAt(LocalDateTime.now());
        taskService.addTasks(task);
        return modelAndView;
    }

    @PostMapping(value = "markComplete")
    public ModelAndView markComplete(Long taskId) {
        System.out.println(taskId);
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        Task task = taskService.getTask(taskId);
        task.setIsCompleted(!task.getIsCompleted());
        taskService.updateTask(task);
        return modelAndView;

    }

    @GetMapping(value = "editTask")
    public ModelAndView editTask(Long taskId) {
        ModelAndView modelAndView = new ModelAndView("editTaskForm");
        Task task = taskService.getTask(taskId);
        modelAndView.addObject("task", task);
        return modelAndView;
    }

    @PostMapping(value = "editTask")
    public ModelAndView editTaskForm(@ModelAttribute Task task) {
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        taskService.updateTask(task);
        return modelAndView;
    }

    @PostMapping(value = "deleteTask")
    public ModelAndView deleteTask(Long taskId) {

        ModelAndView modelAndView = new ModelAndView("redirect:/");
        taskService.deleteTask(taskId);
        return modelAndView;

    }
}
