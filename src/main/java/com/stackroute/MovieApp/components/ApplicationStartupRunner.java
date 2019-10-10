package com.stackroute.MovieApp.components;

import com.stackroute.MovieApp.domain.Movie;
import com.stackroute.MovieApp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupRunner implements CommandLineRunner {
    MovieRepository movieRepository;
    Movie movie;

    @Autowired
    public ApplicationStartupRunner(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }
    @Override
    public void run(String... args) throws Exception {

    }
}
