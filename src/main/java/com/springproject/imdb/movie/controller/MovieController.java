package com.springproject.imdb.movie.controller;

import com.springproject.imdb.movie.model.Movie;
import com.springproject.imdb.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Cacheable(value = "usersCopy")
    public List<Movie> getAllMovie() {

        return movieService.getAllMovies();
    }


    @PostMapping
    @CacheEvict(value = "usersCopy",allEntries = true, key = "#movie.movieId")
    public void addNewMovie( @RequestBody Movie movie ) {
        movieService.addNewMovie( movie );
    }

    @DeleteMapping("{movieId}")
    @CacheEvict(value = "usersCopy", allEntries = true, key = "#movieId")
    public void deleteStudent(@PathVariable("movieId") UUID movieId) {
        movieService.deleteMovie(movieId);
    }

    @PutMapping(path = "{movieId}")
    @CacheEvict(value = "usersCopy",allEntries = true, key = "#movieId")
    public void updateMovie(@PathVariable("movieId") UUID movieId,
                              @RequestBody Movie movie) {
        movieService.updateMovie(movieId, movie);
    }
}