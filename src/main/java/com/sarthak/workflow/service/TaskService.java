package com.sarthak.workflow.service;

import com.sarthak.workflow.domain.entity.Task;
import com.sarthak.workflow.domain.entity.TaskDependency;
import com.sarthak.workflow.dto.TaskDependencyResponse;
import com.sarthak.workflow.dto.TaskResponse;
import com.sarthak.workflow.repository.TaskDependencyRepository;
import com.sarthak.workflow.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskDependencyRepository taskDependencyRepository;

    public List<TaskResponse> getTasksByWorkflow(Long workflowId) {
        return taskRepository.findByWorkflowId(workflowId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<TaskDependencyResponse> getDependenciesByWorkflow(Long workflowId) {
        return taskDependencyRepository.findByWorkflowId(workflowId)
                .stream()
                .map(this::mapDepToResponse)
                .toList();
    }

    private TaskResponse mapToResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getWorkflow().getId(),
                task.getName(),
                task.getType(),
                task.getRetryCount(),
                task.getTimeoutSeconds()
        );
    }

    private TaskDependencyResponse mapDepToResponse(TaskDependency dep) {
        return new TaskDependencyResponse(
                dep.getId(),
                dep.getTask().getId(),
                dep.getDependsOnTask().getId()
        );
    }
}
