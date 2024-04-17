package com.dsi.todo.controller;

import com.dsi.todo.model.Task;
import com.dsi.todo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(value = "addTask")
    public ModelAndView addTask(@ModelAttribute Task task) {

        ModelAndView modelAndView = new ModelAndView("redirect:/");
        taskService.addTasks(task);
        modelAndView.addObject("tasks", taskService.getAllTasks());

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
    public ModelAndView editTaskForm(@Valid @ModelAttribute("task") Task task, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {
            modelAndView.setViewName("editTaskForm");
            modelAndView.addObject("task", task);
            modelAndView.addObject("errorTitle", result.getFieldError("title").getDefaultMessage().toString());
//            modelAndView.addObject("errorDetails", result.getFieldError("details").getDefaultMessage().toString());
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

