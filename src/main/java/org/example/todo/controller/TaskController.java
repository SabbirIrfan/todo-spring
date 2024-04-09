package org.example.todo.controller;

import org.example.todo.model.Task;
import org.example.todo.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Controller
public class TaskController {

    private final TaskService taskService;

    public  TaskController(TaskService taskService){
        this.taskService = taskService;
    }
    @PostMapping(value = "addTask")
    public ModelAndView addTask(@ModelAttribute Task task){

        ModelAndView modelAndView = new ModelAndView("redirect:/");
       modelAndView.addObject("tasks" , taskService.getAllTasks());
        task.setCreatedAt(LocalDateTime.now());
        taskService.addTasks(task);

        return modelAndView;

    }
    @GetMapping(value = "editTask")
    public ModelAndView editTask(Long taskId){
        ModelAndView modelAndView = new ModelAndView("editTaskForm");
        Task task = taskService.getTask(taskId);
        if(task != null){
            modelAndView.addObject("task", task);
        }
        return modelAndView;
    }
    @PostMapping(value = "editTask")
    public ModelAndView editTaskForm(@ModelAttribute Task task){
        System.out.println(task);
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        taskService.updateTask(task);

        return modelAndView;
    }
    @PostMapping(value = "deleteTask")
    public ModelAndView deleteTask(Long taskId){

        ModelAndView modelAndView = new ModelAndView("redirect:/");
        System.out.println(taskId);
        taskService.deleteTask(taskId);
        return modelAndView;

    }
}

