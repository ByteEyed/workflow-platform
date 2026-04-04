package com.sarthak.workflow.repository;

import com.sarthak.workflow.domain.entity.TaskExecution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskExecutionRepository extends JpaRepository<TaskExecution, Long> {
    List<TaskExecution> findByWorkflowExecutionId(Long workflowExecutionId);
}
