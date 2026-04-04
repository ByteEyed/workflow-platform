package com.sarthak.workflow.controller;

import com.sarthak.workflow.service.WorkflowService;
import com.sarthak.workflow.service.WorkflowExecutionService;
import com.sarthak.workflow.service.TaskService;
import com.sarthak.workflow.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workflows")
@RequiredArgsConstructor
public class WorkflowController {

    private final WorkflowService workflowService;
    private final WorkflowExecutionService executionService;
    private final TaskService taskService;

    @GetMapping
    public List<WorkflowResponse> getWorkflows() {
        return workflowService.getAllWorkflows();
    }

    @GetMapping("/{id}")
    public WorkflowResponse getWorkflow(@PathVariable Long id) {
        return workflowService.getWorkflowById(id);
    }

    @PostMapping
    public WorkflowResponse createWorkflow(@RequestBody CreateWorkflowRequest request) {
        return workflowService.createWorkflow(request);
    }

    @PutMapping("/{id}/toggle")
    public WorkflowResponse toggleWorkflowStatus(@PathVariable Long id) {
        return workflowService.toggleWorkflowStatus(id);
    }

    @PostMapping("/{id}/execute")
    public WorkflowExecutionResponse executeWorkflow(@PathVariable Long id) {
        return executionService.executeWorkflow(id);
    }

    @GetMapping("/{id}/executions")
    public List<WorkflowExecutionResponse> getWorkflowExecutions(@PathVariable Long id) {
        return executionService.getExecutionsByWorkflow(id);
    }

    @GetMapping("/{id}/tasks")
    public List<TaskResponse> getTasksByWorkflow(@PathVariable Long id) {
        return taskService.getTasksByWorkflow(id);
    }

    @GetMapping("/{id}/dependencies")
    public List<TaskDependencyResponse> getDependenciesByWorkflow(@PathVariable Long id) {
        return taskService.getDependenciesByWorkflow(id);
    }
}