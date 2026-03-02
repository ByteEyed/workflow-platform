package com.sarthak.workflow.repository;

import com.sarthak.workflow.domain.entity.Workflow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkflowRepository extends JpaRepository<Workflow, Long> {
}