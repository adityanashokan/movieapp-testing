package com.stackroute.MovieApp.components;

import com.stackroute.MovieApp.domain.Movie;
import com.stackroute.MovieApp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:db.properties")
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    MovieRepository movieRepository;
    Movie movie;
    @Value("${M_ID}")
    int id;
    @Value("${M_TITLE}")
    String title;
    @Value("${M_OVERVIEW}")
    String overview;
    @Value("${M_TAGLINE}")
    String tagline;
    @Value("${M_VOTE}")
    double vote;

    @Autowired
    Environment env;

    @Autowired
    public StartupApplicationListener(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        movie = new Movie(id, title, overview, tagline, vote);
        movieRepository.save(movie);


        id = Integer.parseInt(env.getProperty("M2_ID"));
        title = env.getProperty("M2_TITLE");
        overview = env.getProperty("M2_OVERVIEW");
        tagline = env.getProperty("M2_TAGLINE");
        vote = Double.parseDouble(env.getProperty("M2_VOTE"));

        movie = new Movie(id, title, overview, tagline, vote);
        movieRepository.save(movie);


    }
}
