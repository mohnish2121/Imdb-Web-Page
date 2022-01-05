package com.springproject.imdb.movie.service;

import com.springproject.imdb.movie.model.Producer;
import com.springproject.imdb.movie.dao.ProducerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProducerService {
    private final ProducerDao producerDao;

    @Autowired
    public ProducerService(ProducerDao producerDao) {
        this.producerDao = producerDao;
    }

    public List<Producer> getAllProducers() {
        return producerDao.getAllProducers();
    }

    public void addNewProducer(Producer producer) {
        producerDao.addNewProducer(UUID.randomUUID(), producer);
    }
}
