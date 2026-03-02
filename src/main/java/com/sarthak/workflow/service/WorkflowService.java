package com.sarthak.workflow.service;

import com.sarthak.workflow.domain.entity.Workflow;
import com.sarthak.workflow.domain.enums.WorkflowStatus;
import com.sarthak.workflow.dto.CreateWorkflowRequest;
import com.sarthak.workflow.dto.WorkflowResponse;
import com.sarthak.workflow.repository.WorkflowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class WorkflowService {

    private final WorkflowRepository workflowRepository;

    public WorkflowResponse createWorkflow(CreateWorkflowRequest request) {
        Workflow workflow = Workflow.builder()
                .name(request.name())
                .description(request.description())
                .status(WorkflowStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .build();
        Workflow saved = workflowRepository.save(workflow);
        return mapToResponse(saved);
    }

    public List<WorkflowResponse> getAllWorkflows() {
        return workflowRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public WorkflowResponse getWorkflowById(Long id) {
        Workflow workflow = workflowRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workflow not found"));
        return mapToResponse(workflow);
    }

    private WorkflowResponse mapToResponse(Workflow workflow) {
        return new WorkflowResponse(
                workflow.getId(),
                workflow.getName(),
                workflow.getDescription(),
                workflow.getStatus(),
                workflow.getCreatedAt()
        );
    }
}