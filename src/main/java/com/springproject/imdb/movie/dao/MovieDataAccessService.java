package com.springproject.imdb.movie.dao;

import com.springproject.imdb.movie.model.Actor;
import com.springproject.imdb.movie.model.Movie;
import com.springproject.imdb.movie.model.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class MovieDataAccessService {

    private final JdbcTemplate jdbcTemplate;



    @Autowired
    public MovieDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    public List<Movie> selectAllMovies() {

        String sql = "SELECT movie_id, movie_name, movie_poster, movie_year, movie_plot FROM movie ORDER BY movie_name";

        return jdbcTemplate.query(sql , mapMovieFromDb());

    }



    public int insertMovie(UUID movieId, Movie movie) {
        String sql = "INSERT INTO movie (movie_id, movie_name, movie_poster, movie_year, movie_plot) VALUES (?, ?, ?, ?, ?)";
        String sql2 = "INSERT INTO movie_relation (movie_id, actor_id, producer_id , status) VALUES (?,?,?,?)";

        jdbcTemplate.update(sql, movieId, movie.getName(), movie.getPoster(), movie.getYear(), movie.getPlot());
        for(int i = 0 ;i< movie.getActorsList().size();i++ ) {
            jdbcTemplate.update(sql2, movieId, movie.getActorsList().get(i).getActor_id(), movie.getProducer().getProducer_id(), "hit");
        }


        return 1;
    }

    private RowMapper<Movie> mapMovieFromDb() {
        return (resultSet , i ) -> {
            String movieIdStr = resultSet.getString("movie_id");
            UUID movieId = UUID.fromString(movieIdStr);
            String movieName = resultSet.getString("movie_name");
            String moviePoster = resultSet.getString("movie_poster");
            String movieYearStr = resultSet.getString("movie_year");
            Integer movieYear = Integer.parseInt(movieYearStr);

            String moviePlot = resultSet.getString("movie_plot");

            List<Actor> actors = getActorsOfMovie(movieId);

            Producer producer = getProducer(movieId);

            Movie a = new Movie(movieId, movieName, movieYear, moviePlot, moviePoster);
            a.setProducer(producer);
            a.setActorsList(actors);

            return a;
        };
    }

    private List<Actor> getActorsOfMovie(UUID movieId) {
        String sql = "SELECT  actor.actor_id, actor.name, actor.sex, actor.dob, actor.bio FROM movie JOIN movie_relation USING (movie_id) JOIN actor USING (actor_id) WHERE movie.movie_id = '"+movieId+"';";

        try {
           return jdbcTemplate.query(sql,actorFromDb());
       }
       catch (Exception e) {
           System.out.println(e);
       }
        return null;
    }

    private RowMapper<Actor> actorFromDb() {
        return (resultSet , i) -> {
            String actorIdStr = resultSet.getString("actor_id");
            UUID actorId = UUID.fromString(actorIdStr);
            String actorName = resultSet.getString("name");
            String actorSex = resultSet.getString("sex");
            String actorDob = resultSet.getString("dob");
            String actorBio = resultSet.getString("bio");
            System.out.println(resultSet);
            return new Actor(actorId,actorName,actorSex,actorDob,actorBio);
        };
    }

    private Producer getProducer(UUID movieId) {
        String sql = "SELECT  producer.producer_id, producer.name, producer.sex, producer.dob, producer.bio FROM movie JOIN movie_relation USING (movie_id) JOIN producer USING (producer_id) WHERE movie.movie_id = ? LIMIT 1 " ;


        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{movieId},(resultSet, i)->
                 new Producer(
                        UUID.fromString(resultSet.getString("producer_id")),
                        resultSet.getString("name"),
                        resultSet.getString("sex"),
                        resultSet.getString("dob"),
                        resultSet.getString("bio")
                )
            );
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return new Producer(UUID.randomUUID(),"imtiaz","male","1 nov", "story teller");
    }


    public int deleteMovieById(UUID movieId) {
        String sql = "DELETE FROM movie WHERE movie_id = ?";
        String sql2 = "DELETE FROM movie_relation WHERE movie_id = ?";
        jdbcTemplate.update(sql2, movieId);
        return jdbcTemplate.update(sql, movieId);

    }

    public int updateName(UUID movieId, String name) {
        String sql = "UPDATE movie SET movie_name = ? WHERE movie_id = ?";
        return jdbcTemplate.update(sql, name, movieId);
    }

    public int updateYear(UUID movieId, String year) {
        Integer a = Integer.parseInt(year);
        String sql = "UPDATE movie SET movie_year = ? WHERE movie_id = ?";
        return jdbcTemplate.update(sql, a, movieId);

    }

    public int updatePlot(UUID movieId, String plot) {
        String sql = "UPDATE movie SET movie_plot = ? WHERE movie_id = ?";
        return jdbcTemplate.update(sql, plot, movieId);
    }

    public int updatePoster(UUID movieId, String poster) {
        String sql = "UPDATE movie SET movie_poster = ? WHERE movie_id = ?";
        return jdbcTemplate.update(sql, poster, movieId);
    }

    public int updateProducer(UUID movieId, Movie movie) {
        String sql = "UPDATE movie_relation SET producer_id = ? WHERE movie_id = ?";
        return jdbcTemplate.update(sql, movie.getProducer().getProducer_id() ,movieId);
    }

    public void updateActorList(UUID movieId, Movie movie) {
//        UUID prevId = UUID.randomUUID();
        String sql = "DELETE FROM movie_relation WHERE movie_id = ?";
        jdbcTemplate.update(sql, movieId);

        String sql2 = "INSERT INTO movie_relation (movie_id, actor_id, producer_id , status) VALUES (?,?,?,?)";

        for(int i = 0 ;i< movie.getActorsList().size();i++ ) {
            jdbcTemplate.update(sql2, movieId, movie.getActorsList().get(i).getActor_id(), movie.getProducer().getProducer_id(), "hit");
        }

//        for(int i = 0; i<movie.getActorsList().size(); i++) {
//            String sql = "UPDATE movie_relation SET actor_id = ? WHERE movie_id = ? AND NOT actor_id = '"+prevId+"';";
//            jdbcTemplate.update(sql,movie.getActorsList().get(i).getActor_id(),movieId);
//            prevId = movie.getActorsList().get(i).getActor_id();
//        }
    }
}
