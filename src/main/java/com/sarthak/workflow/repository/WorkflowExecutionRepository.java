package com.sarthak.workflow.repository;

import com.sarthak.workflow.domain.WorkflowExecution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface WorkflowExecutionRepository extends JpaRepository<WorkflowExecution, Long> {

    List<WorkflowExecution> findByWorkflowId(Long workflowId);

    List<WorkflowExecution> findAllByOrderByStartedAtDesc();

    long countByStatus(String status);

    long countByStatusAndStartedAtBetween(String status, Instant start, Instant end);
}