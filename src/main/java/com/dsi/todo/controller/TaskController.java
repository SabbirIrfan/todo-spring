package com.dsi.todo.controller;

import com.dsi.todo.model.Task;
import com.dsi.todo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(value = "addTask")
    public ModelAndView addTask(@Valid @ModelAttribute Task task, BindingResult result, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            System.out.println(task);
        modelAndView.addObject("valid_error", "the length of the title or the description of your task is not appropriate!");
            modelAndView.setViewName("redirect:/");
        }
        else {
            taskService.addTasks(task);
            modelAndView.addObject("tasks", taskService.getAllTasks());
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }

    @PostMapping(value = "markComplete")
    public ModelAndView markComplete(Long taskId) {
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        Task task = taskService.getTask(taskId);
        task.setIsCompleted(!task.getIsCompleted());
        taskService.updateTask(task);
        return modelAndView;

    }

    @GetMapping(value = "editTask")
    public ModelAndView editTask(Long taskId, Model model) {
        ModelAndView modelAndView = new ModelAndView("editTaskForm");
        if(model.getAttribute("task") == null) {
            Task task1 = taskService.getTask(taskId);
            modelAndView.addObject("task", task1);
        }

        return modelAndView;
    }

    @PostMapping(value = "editTask")
    public ModelAndView editTaskForm(@Valid @ModelAttribute("task") Task task, BindingResult result, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(task);

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.class.getName() + ".task", result);
            redirectAttributes.addFlashAttribute("task", task);
            modelAndView.setViewName("redirect:/editTask?taskId=" + task.getId());
            return modelAndView;
        }

        // If there are no validation errors, proceed with task update
        modelAndView.setViewName("redirect:/");
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

