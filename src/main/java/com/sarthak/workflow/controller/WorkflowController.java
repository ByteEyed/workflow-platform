package com.sarthak.workflow.controller;

import com.sarthak.workflow.domain.entity.Workflow;
import com.sarthak.workflow.dto.CreateWorkflowRequest;
import com.sarthak.workflow.service.WorkflowService;
import com.sarthak.workflow.dto.WorkflowResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/workflows")
@RequiredArgsConstructor
public class WorkflowController {

    private final WorkflowService workflowService;

    @PostMapping
    public ResponseEntity<WorkflowResponse> create(@RequestBody CreateWorkflowRequest request) {
        return ResponseEntity.ok(workflowService.createWorkflow(request));
    }

    @GetMapping
    public ResponseEntity<List<WorkflowResponse>> getAll() {
        return ResponseEntity.ok(workflowService.getAllWorkflows());
    }

    @GetMapping("/{id}")
public ResponseEntity<WorkflowResponse> getById(@PathVariable Long id) {
    return ResponseEntity.ok(workflowService.getWorkflowById(id));
}
}