package com.sarthak.workflow.repository;

import com.sarthak.workflow.domain.entity.Workflow;
import com.sarthak.workflow.domain.enums.WorkflowStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkflowRepository extends JpaRepository<Workflow, Long> {
    long countByStatus(WorkflowStatus status);
}