CREATE TABLE task_executions (
    id BIGSERIAL PRIMARY KEY,
    task_id BIGINT NOT NULL REFERENCES tasks(id) ON DELETE CASCADE,
    workflow_execution_id BIGINT NOT NULL REFERENCES workflow_executions(id) ON DELETE CASCADE,
    status VARCHAR(20) NOT NULL,
    attempt_number INT NOT NULL DEFAULT 1,
    log_output TEXT,
    started_at TIMESTAMP,
    finished_at TIMESTAMP
);
