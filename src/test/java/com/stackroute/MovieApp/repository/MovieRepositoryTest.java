package com.stackroute.MovieApp.repository;

import com.stackroute.MovieApp.domain.Movie;
import com.stackroute.MovieApp.exceptions.MovieNotFoundException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;
    Movie movie;

    @Before
    public void setUp()
    {
        movie = new Movie();
        movie.setMovieId(1);
        movie.setMovieName("Joker");
        movie.setOverview("Synopsis");
        movie.setTagline("Tagline of movie");
        movie.setVote_average(9.99);
    }

    @After
    public void tearDown()
    {
        movieRepository.deleteAll();
    }

    @Test
    public void testSaveMovie(){
        movieRepository.save(movie);
        Movie fetchMovie = movieRepository.findById(movie.getMovieId()).get();
        Assert.assertEquals(1,fetchMovie.getMovieId());
    }

    @Test
    public void testSaveMovieFailure(){
        Movie testMovie = new Movie(2,"Batman","Synopsis","Tagline",9.99);
        movieRepository.save(movie);
        Movie fetchMovie = movieRepository.findById(movie.getMovieId()).get();
        Assert.assertNotSame(testMovie,fetchMovie);
    }

    @Test
    public void testDeleteMovie(){
        movieRepository.save(movie);
        movieRepository.deleteById(movie.getMovieId());
        Assert.assertEquals(false,movieRepository.existsById(movie.getMovieId()));
    }

    @Test
    public void testDeleteMovieFailure(){
        movieRepository.save(movie);
        movieRepository.deleteById(movie.getMovieId());
        Assert.assertNotEquals(true,movieRepository.existsById(movie.getMovieId()));
    }

    @Test
    public void testfindByName() throws MovieNotFoundException {
        movieRepository.save(movie);
        List<Movie> fetchMovie = movieRepository.findByName(movie.getMovieName());
        Assert.assertEquals(1,fetchMovie.get(0).getMovieId());
    }

    @Test
    public void testfindByNameFailure() throws MovieNotFoundException {
        movieRepository.save(movie);
        List<Movie> fetchMovie = movieRepository.findByName(movie.getMovieName());
        Assert.assertNotEquals(2,fetchMovie.get(0).getMovieId());
    }
    @Test
    public void testGetAllMovie(){
        Movie m = new Movie(3,"Robin","Synopsis","Tagline",9.99);
        Movie m1 = new Movie(4,"Bane","Synopsis","Tagline",9.99);
        movieRepository.save(m);
        movieRepository.save(m1);

        List<Movie> list = movieRepository.findAll();
        Assert.assertEquals("Robin",list.get(0).getMovieName());
    }


}
