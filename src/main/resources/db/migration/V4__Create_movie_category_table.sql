CREATE TABLE movie_category (
    movie_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    PRIMARY KEY (movie_id, category_id),
    FOREIGN KEY (movie_id) REFERENCES movie(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE
);

CREATE INDEX idx_movie_category_movie ON movie_category(movie_id);
CREATE INDEX idx_movie_category_category ON movie_category(category_id);

