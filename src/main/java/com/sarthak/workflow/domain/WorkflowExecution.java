package com.sarthak.workflow.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "workflow_executions")
public class WorkflowExecution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "workflow_id")
    private Long workflowId;

    private String status;

    @Column(name = "started_at")
    private Instant startedAt;

    @Column(name = "finished_at")
    private Instant finishedAt;

    public WorkflowExecution() {}

    public Long getId() { return id; }

    public Long getWorkflowId() { return workflowId; }

    public void setWorkflowId(Long workflowId) { this.workflowId = workflowId; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public Instant getStartedAt() { return startedAt; }

    public void setStartedAt(Instant startedAt) { this.startedAt = startedAt; }

    public Instant getFinishedAt() { return finishedAt; }

    public void setFinishedAt(Instant finishedAt) { this.finishedAt = finishedAt; }
}