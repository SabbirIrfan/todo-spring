package org.example.todo.service;

import org.example.todo.model.Task;
import org.example.todo.repository.TaskRepo;
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
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDetails(updatedTask.getDetails());
            taskRepo.save(existingTask);
        } else {
            System.out.println("task not found");
            throw new IllegalArgumentException("Task not found with ID: " + updatedTask.getId());
        }
    }
}
