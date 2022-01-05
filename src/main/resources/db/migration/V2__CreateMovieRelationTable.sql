CREATE TABLE IF NOT EXISTS actor (
    actor_id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL ,
    sex VARCHAR(10) NOT NULL ,
    dob VARCHAR(255) ,
    bio VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS producer (
    producer_id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL ,
    sex VARCHAR(10) NOT NULL ,
    dob VARCHAR(255) ,
    bio VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS movie_relation (
    movie_id UUID NOT NULL REFERENCES movie (movie_id),
    actor_id UUID NOT NULL REFERENCES actor (actor_id),
    producer_id UUID NOT NULL REFERENCES producer (producer_id),
    status VARCHAR(10)
);