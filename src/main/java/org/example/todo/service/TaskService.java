package org.example.todo.service;

import org.example.todo.model.Task;
import org.example.todo.repository.TaskRepo;
import org.springframework.stereotype.Service;

import java.util.List;


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
}
