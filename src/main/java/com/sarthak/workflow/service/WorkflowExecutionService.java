package com.sarthak.workflow.service;

import com.sarthak.workflow.domain.WorkflowExecution;
import com.sarthak.workflow.domain.entity.Workflow;
import com.sarthak.workflow.domain.entity.TaskExecution;
import com.sarthak.workflow.dto.WorkflowExecutionResponse;
import com.sarthak.workflow.dto.TaskExecutionResponse;
import com.sarthak.workflow.repository.WorkflowExecutionRepository;
import com.sarthak.workflow.repository.WorkflowRepository;
import com.sarthak.workflow.repository.TaskExecutionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkflowExecutionService {

    private final WorkflowExecutionRepository executionRepository;
    private final WorkflowRepository workflowRepository;
    private final TaskExecutionRepository taskExecutionRepository;

    public WorkflowExecutionResponse executeWorkflow(Long workflowId) {
        Workflow workflow = workflowRepository.findById(workflowId)
                .orElseThrow(() -> new RuntimeException("Workflow not found"));

        WorkflowExecution execution = new WorkflowExecution();
        execution.setWorkflowId(workflowId);
        execution.setStatus("RUNNING");
        execution.setStartedAt(Instant.now());
        execution = executionRepository.save(execution);

        // Run the actual work asynchronously
        runExecutionAsync(execution.getId());

        return mapToResponse(execution, workflow.getName());
    }

    @Async
    public void runExecutionAsync(Long executionId) {
        WorkflowExecution execution = executionRepository.findById(executionId)
                .orElseThrow(() -> new RuntimeException("Execution not found"));
        try {
            Thread.sleep(2000); // simulate work
            execution.setStatus("SUCCESS");
        } catch (Exception e) {
            execution.setStatus("FAILED");
            log.error("Workflow execution {} failed", executionId, e);
        }
        execution.setFinishedAt(Instant.now());
        executionRepository.save(execution);
    }

    public List<WorkflowExecutionResponse> getAllExecutions() {
        return executionRepository.findAllByOrderByStartedAtDesc()
                .stream()
                .map(this::mapToResponseWithName)
                .toList();
    }

    public WorkflowExecutionResponse getExecutionById(Long id) {
        WorkflowExecution execution = executionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Execution not found"));
        return mapToResponseWithName(execution);
    }

    public List<WorkflowExecutionResponse> getExecutionsByWorkflow(Long workflowId) {
        return executionRepository.findByWorkflowId(workflowId)
                .stream()
                .map(this::mapToResponseWithName)
                .toList();
    }

    public List<TaskExecutionResponse> getTaskExecutions(Long executionId) {
        return taskExecutionRepository.findByWorkflowExecutionId(executionId)
                .stream()
                .map(this::mapTaskExecToResponse)
                .toList();
    }

    private WorkflowExecutionResponse mapToResponseWithName(WorkflowExecution execution) {
        String workflowName = workflowRepository.findById(execution.getWorkflowId())
                .map(Workflow::getName)
                .orElse("Unknown");
        return mapToResponse(execution, workflowName);
    }

    private WorkflowExecutionResponse mapToResponse(WorkflowExecution execution, String workflowName) {
        return new WorkflowExecutionResponse(
                execution.getId(),
                execution.getWorkflowId(),
                workflowName,
                execution.getStatus(),
                toLocalDateTime(execution.getStartedAt()),
                toLocalDateTime(execution.getFinishedAt())
        );
    }

    private TaskExecutionResponse mapTaskExecToResponse(TaskExecution te) {
        return new TaskExecutionResponse(
                te.getId(),
                te.getTask().getId(),
                te.getTask().getName(),
                te.getWorkflowExecution().getId(),
                te.getStatus(),
                te.getAttemptNumber(),
                te.getLogOutput(),
                te.getStartedAt(),
                te.getFinishedAt()
        );
    }

    private LocalDateTime toLocalDateTime(Instant instant) {
        return instant != null
                ? LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
                : null;
    }
}