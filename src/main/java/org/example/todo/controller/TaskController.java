package org.example.todo.controller;

import jakarta.validation.Valid;
import org.apache.logging.log4j.message.Message;
import org.example.todo.model.Task;
import org.example.todo.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(value = "addTask")
    public ModelAndView addTask(@ModelAttribute Task task, BindingResult result) {


        ModelAndView modelAndView = new ModelAndView("redirect:/");
        modelAndView.addObject("tasks", taskService.getAllTasks());
//        task.setCreatedAt(LocalDateTime.now());
        taskService.addTasks(task);
        return modelAndView;
    }

    @GetMapping(value = "editTask")
    public ModelAndView editTask(Long taskId) {
        ModelAndView modelAndView = new ModelAndView("editTaskForm");
        Task task = taskService.getTask(taskId);

        modelAndView.addObject("task",task);
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

