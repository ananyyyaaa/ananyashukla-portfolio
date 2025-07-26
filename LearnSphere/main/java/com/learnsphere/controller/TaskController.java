package com.learnsphere.controller;

import com.learnsphere.model.Task;
import com.learnsphere.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<?> addTask(@RequestBody Task task) {
        Task savedTask = taskService.addTask(task);
        return ResponseEntity.ok(savedTask);
    }

    @PutMapping("/{taskId}/complete")
    public ResponseEntity<?> markTaskComplete(@PathVariable Long taskId) {
        Optional<Task> taskOpt = taskService.markTaskComplete(taskId);
        if (taskOpt.isPresent()) {
            return ResponseEntity.ok(taskOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/my/{userId}")
    public ResponseEntity<?> getUserTasks(@PathVariable Long userId) {
        List<Task> tasks = taskService.getTasksByUserId(userId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/my/{userId}/progress")
    public ResponseEntity<?> getUserTaskProgress(@PathVariable Long userId) {
        double progress = taskService.getTaskCompletionProgress(userId);
        return ResponseEntity.ok(Map.of("progressPercentage", progress));
    }
}
