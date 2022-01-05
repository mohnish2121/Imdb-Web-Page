package com.springproject.imdb.movie.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class MovieDetails implements Serializable {
    private List<Movie> movies ;
    private List<Actor> actors ;
    private List<Producer> producers;

    public MovieDetails() {

    }

    public MovieDetails(@JsonProperty List<Movie> movies,
                        @JsonProperty List<Actor> actors,
                        @JsonProperty List<Producer> producers ) {
        this.movies = movies;
        this.actors = actors;
        this.producers = producers;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Producer> getProducer() {
        return producers;
    }

    public void setProducer(List<Producer> producer) {
        this.producers = producer;
    }
}
