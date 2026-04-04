CREATE TABLE workflow_executions (
    id BIGSERIAL PRIMARY KEY,
    workflow_id BIGINT NOT NULL,
    status VARCHAR(50),
    started_at TIMESTAMP,
    finished_at TIMESTAMP
);