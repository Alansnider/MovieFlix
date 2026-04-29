CREATE TABLE streaming (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,

);

CREATE INDEX idx_streaming_name ON streaming(name);

