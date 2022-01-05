package com.springproject.imdb.movie.service;

import com.springproject.imdb.movie.model.Actor;
import com.springproject.imdb.movie.dao.ActorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ActorService {
    private final ActorDao actorDao;

    @Autowired
    public ActorService(ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    public List<Actor> getAllActors() {
        return actorDao.getAllActors() ;
    }

    public void addNewActor(Actor actor) {
        actorDao.addNewActor(UUID.randomUUID(), actor);
    }
}
