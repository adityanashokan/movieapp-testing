package com.stackroute.MovieApp.repository;

import com.stackroute.MovieApp.domain.Movie;
import com.stackroute.MovieApp.exceptions.MovieNotFoundException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, Integer> {

    @Query("{'movieName' : ?0 }")
    public List<Movie> findByName(@Param("searchName") String searchName) throws MovieNotFoundException;

}
