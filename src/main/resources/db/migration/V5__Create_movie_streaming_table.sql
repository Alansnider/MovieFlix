CREATE TABLE movie_streaming (
    movie_id BIGINT NOT NULL,
    streaming_id BIGINT NOT NULL,
    PRIMARY KEY (movie_id, streaming_id),
    FOREIGN KEY (movie_id) REFERENCES movie(id) ON DELETE CASCADE,
    FOREIGN KEY (streaming_id) REFERENCES streaming(id) ON DELETE CASCADE
);

CREATE INDEX idx_movie_streaming_movie ON movie_streaming(movie_id);
CREATE INDEX idx_movie_streaming_streaming ON movie_streaming(streaming_id);

