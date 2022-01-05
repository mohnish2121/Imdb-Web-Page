package com.springproject.imdb.movie.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.UUID;


public class Producer implements Serializable {
    private UUID producer_id;
    private String name;
    private String sex;
    private String dob;
    private String bio;



    public UUID getProducer_id() {
        return producer_id;
    }

    public void setProducer_id(UUID producer_id) {
        this.producer_id = producer_id;
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

    public Producer(@JsonProperty UUID producer_id, @JsonProperty String name, @JsonProperty String sex, @JsonProperty String dob, @JsonProperty String bio) {
        this.producer_id = producer_id;
        this.name = name;
        this.sex = sex;
        this.dob = dob;
        this.bio = bio;
    }
}
