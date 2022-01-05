package com.springproject.imdb.movie.service;

import com.springproject.imdb.movie.model.MovieDetails;
import com.springproject.imdb.movie.controller.ActorController;
import com.springproject.imdb.movie.controller.MovieController;
import com.springproject.imdb.movie.controller.ProducerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieDetailsService {
    private MovieController movieController;
    private ActorController actorController;
    private ProducerController producerController;

    @Autowired
    public MovieDetailsService(MovieController movieController, ActorController actorController, ProducerController producerController) {
        this.movieController = movieController;
        this.actorController = actorController;
        this.producerController = producerController;
    }

    public MovieDetails getAllDetails() {
        return new MovieDetails(movieController.getAllMovie(),
                actorController.getAllActors(),
                producerController.getAllProducers());

    }
}
