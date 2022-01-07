package com.springproject.imdb.movie.service;

import com.springproject.imdb.movie.model.MovieDetails;
import com.springproject.imdb.movie.controller.ActorController;
import com.springproject.imdb.movie.controller.MovieController;
import com.springproject.imdb.movie.controller.ProducerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieDetailsService {
    private MovieService movieService;
    private ActorService actorService;
    private ProducerService producerService;

    @Autowired
    public MovieDetailsService( MovieService movieService,
                                ActorService actorService,
                                ProducerService producerService ) {

        this.movieService = movieService;
        this.actorService = actorService;
        this.producerService = producerService;
    }

    public MovieDetails getAllDetails() {
        return new MovieDetails(movieService.getAllMovies(),
                actorService.getAllActors(),
                producerService.getAllProducers());

    }
}
