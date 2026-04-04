package com.sarthak.workflow.repository;

import com.sarthak.workflow.domain.entity.TaskDependency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskDependencyRepository extends JpaRepository<TaskDependency, Long> {

    @Query("SELECT td FROM TaskDependency td WHERE td.task.workflow.id = :workflowId")
    List<TaskDependency> findByWorkflowId(Long workflowId);

    List<TaskDependency> findByTaskId(Long taskId);
}
