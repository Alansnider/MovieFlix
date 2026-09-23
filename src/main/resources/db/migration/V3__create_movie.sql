CREATE TABLE movie (
    id serial PRIMARY KEY,
    name varchar(255) NOT NULL,
    description text,
    release_date date,
    rating numeric,
    created_at timestamp,
    updated_at timestamp
);

CREATE TABLE movie_category (
    movie_id INTEGER NOT NULL,
    category_id INTEGER NOT NULL,
    PRIMARY KEY (movie_id, category_id),
    CONSTRAINT fk_movie_category_movie FOREIGN KEY (movie_id) REFERENCES movie (id),
    CONSTRAINT fk_movie_category_category FOREIGN KEY (category_id) REFERENCES category (id)
);

CREATE TABLE movie_streaming (
    movie_id INTEGER NOT NULL,
    streaming_id INTEGER NOT NULL,
    PRIMARY KEY (movie_id, streaming_id),
    CONSTRAINT fk_movie_streaming_movie FOREIGN KEY (movie_id) REFERENCES movie (id),
    CONSTRAINT fk_movie_streaming_streaming FOREIGN KEY (streaming_id) REFERENCES streaming (id)
);
