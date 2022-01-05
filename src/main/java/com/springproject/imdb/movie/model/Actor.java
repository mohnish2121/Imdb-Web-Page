package com.springproject.imdb.movie.model;

import java.io.Serializable;
import java.util.UUID;


public class Actor implements Serializable {
    private UUID actor_id;
    private String name;
    private String sex;
    private String dob;
    private String bio;

    public UUID getActor_id() {
        return actor_id;
    }

    public void setActor_id(UUID actor_id) {
        this.actor_id = actor_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Actor(UUID actor_id, String name, String sex, String dob, String bio) {
        this.actor_id = actor_id;
        this.name = name;
        this.sex = sex;
        this.dob = dob;
        this.bio = bio;
    }
}
