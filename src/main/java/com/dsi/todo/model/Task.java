package com.dsi.todo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "Title is required")
    @Column
    private String title;

    @NotBlank(message = "Details are required")
    @Column
    private String details;

    @Column(name = "is_completed")
    private Boolean isCompleted = false;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "Task{" + ", title='" + title + '\'' + ", details='" + details + '\'' + ", isCompleted=" + isCompleted + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
}