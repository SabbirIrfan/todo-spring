package com.dsi.todo.service;

import com.dsi.todo.model.Task;
import com.dsi.todo.repository.TaskRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TaskService {

    private final TaskRepo taskRepo;

    public  TaskService(TaskRepo taskRepo){
        this.taskRepo = taskRepo;
    }
    public List<Task> getAllTasks(){

        return taskRepo.findAll();
    }
    public void addTasks(Task task){
        taskRepo.save(task);
    }
    public void deleteTask(Long id){
        taskRepo.deleteById(id);
    }
    public Task getTask(Long id){

        Optional<Task> optionalTask =  taskRepo.findById(id);
        return optionalTask.orElse(null);
    }
    public void updateTask(Task updatedTask) {
        Task existingTask = taskRepo.findById(updatedTask.getId()).orElse(null);

        if (existingTask != null) {
            updatedTask.setCreatedAt(existingTask.getCreatedAt());
            taskRepo.save(updatedTask);
        } else {
            System.out.println("task not found");
            throw new IllegalArgumentException("Task not found with ID: " + updatedTask.getId());
        }
    }
}
