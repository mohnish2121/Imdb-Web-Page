package com.springproject.imdb.movie.controller;

import com.springproject.imdb.movie.model.Actor;
import com.springproject.imdb.movie.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("actors")
public class ActorController {

    private final ActorService actorService;

    @Autowired
    public ActorController (ActorService actorService) {
        this.actorService = actorService;
    }

    @Cacheable(value = "actorCacheCopy")
    public List<Actor> getAllActors() {
        System.out.println("actors printed");
        return actorService.getAllActors();
    }

    @PostMapping
    @CacheEvict(value = "actorCacheCopy",allEntries = true,key = "#actor.actor_id")
    public void addNewActor(@RequestBody Actor actor ) {
        actorService.addNewActor(actor);
    }
}
