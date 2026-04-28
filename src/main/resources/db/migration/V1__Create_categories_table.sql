-- Create category table
CREATE TABLE IF NOT EXISTS category (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- Create index for better performance
CREATE INDEX IF NOT EXISTS idx_category_name ON category(name);
