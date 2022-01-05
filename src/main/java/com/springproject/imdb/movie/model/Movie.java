package com.springproject.imdb.movie.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;


public class Movie implements Serializable {

    private UUID movieId;
    private String name ;

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", plot='" + plot + '\'' +
                ", poster='" + poster + '\'' +
                ", actorsList=" + actorsList +
                ", producer=" + producer +
                '}';
    }

    private Integer year;
    private String plot;
    private String poster;
    private List<Actor> actorsList;
    public Producer producer;

    public Movie( @JsonProperty("movie_id") UUID movieId,
                  @JsonProperty("name") String name,
                  @JsonProperty("year") Integer year,
                  @JsonProperty("plot") String plot,
                  @JsonProperty("poster") String poster) {

        this.movieId = movieId;
        this.name = name;
        this.year = year;
        this.plot = plot;
        this.poster = poster;

    }

    public List<Actor> getActorsList() {
        return actorsList;
    }

    public UUID getMovieId() {return this.movieId;}
    public String getName() {
        return this.name;
    }
    public Integer getYear() {
        return this.year;
    }
    public String getPlot() {
        return this.plot;
    }
    public String getPoster() {
        return this.poster;
    }
    public Producer getProducer() {
        return producer;
    }

    public void setMovieId(UUID movieId) { this.movieId = movieId;}
    public void setName(String name ) {
        this.name = name;
    }
    public void setYear( Integer year ) {
        this.year = year;
    }
    public void setPlot( String plot ) {
        this.plot = plot;
    }
    public void setPoster( String poster ) {
        this.poster = poster;
    }
    public void setProducer( Producer producer ) {
        this.producer = producer;
    }
    public void setActorsList( List<Actor> actorsList ) {
        this.actorsList = actorsList;
    }


}
