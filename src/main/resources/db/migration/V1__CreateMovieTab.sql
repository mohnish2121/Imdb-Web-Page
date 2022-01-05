CREATE TABLE IF NOT EXISTS movie (
    movie_id UUID PRIMARY KEY NOT NULL,
    movie_name VARCHAR(100) NOT NULL,
    movie_poster VARCHAR(200) NOT NULL,
    movie_year INT NOT NULL,
    movie_plot VARCHAR(200) NOT NULL
);