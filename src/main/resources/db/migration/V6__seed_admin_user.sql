-- Insert default admin user
-- Password: admin123  (BCrypt hash)
INSERT INTO users (username, email, password_hash, role, created_at)
VALUES (
    'admin',
    'admin@orchestraflow.io',
    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
    'ADMIN',
    CURRENT_TIMESTAMP
)
ON CONFLICT (username) DO NOTHING;
