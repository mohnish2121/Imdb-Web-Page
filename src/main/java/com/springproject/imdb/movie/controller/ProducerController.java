package com.springproject.imdb.movie.controller;

import com.springproject.imdb.movie.service.ProducerService;
import com.springproject.imdb.movie.model.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("producers")
public class ProducerController {

    private final ProducerService producerService;

    @Autowired
    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }


    @Cacheable(value = "producerCacheCopy")
    public List<Producer> getAllProducers() {
        return producerService.getAllProducers();
    }

    @PostMapping
    @CacheEvict(value = "producerCacheCopy",allEntries = true,key = "#producer.producer_id")
    public void addNewProducer(@RequestBody Producer producer ) {
        producerService.addNewProducer(producer);
    }
}
