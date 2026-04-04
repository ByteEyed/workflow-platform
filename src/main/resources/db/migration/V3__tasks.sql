CREATE TABLE tasks (
    id BIGSERIAL PRIMARY KEY,
    workflow_id BIGINT NOT NULL REFERENCES workflows(id) ON DELETE CASCADE,
    name VARCHAR(200) NOT NULL,
    type VARCHAR(20) NOT NULL,
    retry_count INT NOT NULL DEFAULT 0,
    timeout_seconds INT NOT NULL DEFAULT 60
);
