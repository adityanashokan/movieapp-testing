package com.stackroute.MovieApp.service;

import com.stackroute.MovieApp.domain.Movie;
import com.stackroute.MovieApp.exceptions.MovieAlreadyExistsException;
import com.stackroute.MovieApp.exceptions.MovieNotFoundException;
import com.stackroute.MovieApp.repository.MovieRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MovieServiceTest {

    Movie movie;

    @Mock
    MovieRepository movieRepository;

    @InjectMocks
    MovieServiceImpl movieService;
    List<Movie> list = null;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        movie = new Movie(1,"Joker","Synopsis","Tagline of movie",9.99);
        list = new ArrayList<Movie>();
        list.add(movie);
    }

    @Test
    public void saveMovieTestSuccess() throws MovieAlreadyExistsException {

        when(movieRepository.save((Movie) any())).thenReturn(movie);
        Movie savedMovie = movieService.saveMovie(movie);
        Assert.assertEquals(movie,savedMovie);
        verify(movieRepository,times(1)).save(movie);

    }

    @Test(expected = MovieAlreadyExistsException.class)
    public void saveMovieTestFailure() throws MovieAlreadyExistsException {
        when(movieRepository.save((Movie)any())).thenReturn(null);
        Movie savedMovie = movieService.saveMovie(movie);
        System.out.println("savedMovie" + savedMovie);
    }

    @Test
    public void deleteMovieTestSuccess() throws MovieNotFoundException {
        movieService.deleteMovie(1);
        verify(movieRepository,times(1)).deleteById(1);
    }




    @Test
    public void updateMovieTestSuccess() throws MovieNotFoundException {
        when(movieRepository.save(any())).thenReturn(movie);
        Movie updatedMovie = movieService.updateMovie(123,movie);
        verify(movieRepository,times(1)).deleteById(123);
        verify(movieRepository,times(1)).save(movie);
    }


    @Test
    public void findByNameTestSuccess() throws MovieNotFoundException {
        when(movieRepository.findByName(anyString())).thenReturn(list);
        List<Movie> l = movieService.findByName("Joker");
        verify(movieRepository,times(2)).findByName("Joker");
    }

    @Test(expected = MovieNotFoundException.class)
    public void findByNameTestFailure() throws MovieNotFoundException {
        when(movieRepository.findByName(anyString())).thenThrow(MovieNotFoundException.class);
        List<Movie> ls = movieService.findByName("Joker");
    }


    @Test
    public void getAllMovie(){

        movieRepository.save(movie);
        //stubbing the mock to return specific data
        when(movieRepository.findAll()).thenReturn(list);
        List<Movie> movielist = movieService.getAllMovies();
        Assert.assertEquals(list,movielist);
    }
}
