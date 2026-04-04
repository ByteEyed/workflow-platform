package com.sarthak.workflow.repository;

import com.sarthak.workflow.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByWorkflowId(Long workflowId);
    long countByWorkflowId(Long workflowId);
}
