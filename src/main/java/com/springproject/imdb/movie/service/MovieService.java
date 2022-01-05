package com.springproject.imdb.movie.service;

import com.springproject.imdb.movie.model.Movie;
import com.springproject.imdb.movie.dao.MovieDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieService {

    private final MovieDataAccessService movieDataAccessService;

    @Autowired
    public MovieService(MovieDataAccessService movieDataAccessService) {
        this.movieDataAccessService = movieDataAccessService;
    }

    public List<Movie> getAllMovies() {
        return movieDataAccessService.selectAllMovies();
    }


    public void addNewMovie( Movie movie) {

        addNewMovie(null, movie);
    }

    public void addNewMovie( UUID movieId, Movie movie) {
        UUID newMovieId = Optional.ofNullable(movieId).orElse(UUID.randomUUID());

        movieDataAccessService.insertMovie(newMovieId, movie);
    }



    public void deleteMovie(UUID movieId) {
        movieDataAccessService.deleteMovieById(movieId);
    }



    public void updateMovie(UUID movieId, Movie movie) {
        if(movie.getName() != "") {
            movieDataAccessService.updateName(movieId, movie.getName());
        }
        if( movie.getYear() !=  null ) {
            movieDataAccessService.updateYear(movieId, movie.getYear().toString());
        }
        if(movie.getPlot() != "") {
            movieDataAccessService.updatePlot(movieId, movie.getPlot());
        }
        if(movie.getPoster() != "") {
            movieDataAccessService.updatePoster(movieId, movie.getPoster());
        }
        if(! movie.getActorsList().isEmpty()) {
            movieDataAccessService.updateActorList(movieId, movie);
        }
        if(movie.getProducer().getProducer_id().toString() != null ) {
            movieDataAccessService.updateProducer(movieId, movie);
        }

    }
}
