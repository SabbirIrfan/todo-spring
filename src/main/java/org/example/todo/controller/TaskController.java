package org.example.todo.controller;

import org.example.todo.model.Task;
import org.example.todo.service.TaskService;
import org.springframework.stereotype.Controller;
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
    @PostMapping(value = "editTask")
    public ModelAndView editTask(Long id){

        ModelAndView modelAndView = new ModelAndView("redirect:/");


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

