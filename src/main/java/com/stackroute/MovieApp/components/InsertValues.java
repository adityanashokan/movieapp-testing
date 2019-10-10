package com.stackroute.MovieApp.components;

import com.stackroute.MovieApp.domain.Movie;
import com.stackroute.MovieApp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InsertValues {

    MovieRepository movieRepository;

    @Autowired
    public InsertValues(@Value("${movie.id}") int id,
                        @Value("${movie.title}") String title,
                        @Value("${movie.overview}") String overview,
                        @Value("${movie.tagline}") String tagline,
                        @Value("${movie.vote_average}") double vote_average, MovieRepository movieRepository) {
        movieRepository.save(new Movie(id,title,overview,tagline,vote_average));
    }
}
