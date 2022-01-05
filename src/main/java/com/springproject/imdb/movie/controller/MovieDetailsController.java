package com.springproject.imdb.movie.controller;

import com.springproject.imdb.movie.model.MovieDetails;
import com.springproject.imdb.movie.service.MovieDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movies")
public class MovieDetailsController {

    private MovieDetailsService movieDetailsService;

    @Autowired
    public MovieDetailsController(MovieDetailsService movieDetailsService) {
        this.movieDetailsService = movieDetailsService;
    }

    @GetMapping
    public MovieDetails getAllDetails() {
        return movieDetailsService.getAllDetails();
    }
}
