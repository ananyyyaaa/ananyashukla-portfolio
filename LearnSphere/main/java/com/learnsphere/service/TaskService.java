package com.learnsphere.service;

import com.learnsphere.model.Task;
import com.learnsphere.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public Optional<Task> markTaskComplete(Long taskId) {
        Optional<Task> taskOpt = taskRepository.findById(taskId);
        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            task.setCompleted(true);
            taskRepository.save(task);
            return Optional.of(task);
        }
        return Optional.empty();
    }

    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    public double getTaskCompletionProgress(Long userId) {
        List<Task> tasks = taskRepository.findByUserId(userId);
        if (tasks.isEmpty()) {
            return 0.0;
        }
        long completedCount = tasks.stream().filter(Task::isCompleted).count();
        return (completedCount * 100.0) / tasks.size();
    }
}
