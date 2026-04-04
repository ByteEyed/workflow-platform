package com.sarthak.workflow.controller;

import com.sarthak.workflow.dto.TaskExecutionResponse;
import com.sarthak.workflow.dto.WorkflowExecutionResponse;
import com.sarthak.workflow.service.WorkflowExecutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/executions")
@RequiredArgsConstructor
public class ExecutionController {

    private final WorkflowExecutionService executionService;

    @GetMapping
    public List<WorkflowExecutionResponse> getAllExecutions() {
        return executionService.getAllExecutions();
    }

    @GetMapping("/{id}")
    public WorkflowExecutionResponse getExecution(@PathVariable Long id) {
        return executionService.getExecutionById(id);
    }

    @GetMapping("/{id}/tasks")
    public List<TaskExecutionResponse> getTaskExecutions(@PathVariable Long id) {
        return executionService.getTaskExecutions(id);
    }
}
