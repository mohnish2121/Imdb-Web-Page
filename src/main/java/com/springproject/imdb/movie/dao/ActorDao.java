package com.springproject.imdb.movie.dao;

import com.springproject.imdb.movie.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ActorDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ActorDao( JdbcTemplate jdbcTemplate ) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Actor> getAllActors() {
        String sql = "SELECT * FROM actor";

        return jdbcTemplate.query(sql, mapActorFromDb());

    }

    private RowMapper<Actor> mapActorFromDb() {
        return (resultSet, i ) -> {
            String actorIdStr = resultSet.getString("actor_id");
            UUID actorId = UUID.fromString(actorIdStr);

            String name = resultSet.getString("name");
            String sex = resultSet.getString("sex");
            String dob = resultSet.getString("dob");
            String bio = resultSet.getString("bio");
            return new Actor(actorId, name, sex, dob, bio);
        };
    }

    public int addNewActor(UUID actorId, Actor actor) {
        String sql = "INSERT INTO actor VALUES (?,?,?,?,?)";
        return jdbcTemplate.update(sql,actorId , actor.getName(), actor.getSex(), actor.getDob(), actor.getBio() );
    }
}

