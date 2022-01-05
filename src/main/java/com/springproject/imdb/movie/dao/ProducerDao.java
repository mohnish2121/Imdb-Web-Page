package com.springproject.imdb.movie.dao;

import com.springproject.imdb.movie.model.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ProducerDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProducerDao( JdbcTemplate jdbcTemplate ) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Producer> getAllProducers() {
        String sql = "SELECT * FROM producer";

        return jdbcTemplate.query(sql, mapProducerFromDb());

    }

    private RowMapper<Producer> mapProducerFromDb() {
        return (resultSet, i ) -> {
            String producerIdStr = resultSet.getString("producer_id");
            UUID producerId = UUID.fromString(producerIdStr);

            String name = resultSet.getString("name");
            String sex = resultSet.getString("sex");
            String dob = resultSet.getString("dob");
            String bio = resultSet.getString("bio");
            return new Producer(producerId, name, sex, dob, bio);
        };
    }

    public int addNewProducer(UUID producerId, Producer producer) {
        String sql = "INSERT INTO producer VALUES (?,?,?,?,?)";
        return jdbcTemplate.update(sql,producerId , producer.getName(), producer.getSex(), producer.getDob(), producer.getBio() );
    }
}
