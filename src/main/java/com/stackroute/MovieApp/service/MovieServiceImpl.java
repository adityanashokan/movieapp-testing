package com.stackroute.MovieApp.service;

import com.stackroute.MovieApp.domain.Movie;
import com.stackroute.MovieApp.exceptions.MovieAlreadyExistsException;
import com.stackroute.MovieApp.exceptions.MovieNotFoundException;
import com.stackroute.MovieApp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile({"service1","default"})
public class MovieServiceImpl implements MovieService {

    MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException {
        if(movieRepository.existsById(movie.getMovieId())){
            throw new MovieAlreadyExistsException("Movie Already Exists");
        }
        Movie savedMovie = movieRepository.save(movie);
        if(savedMovie == null){
            throw new MovieAlreadyExistsException("Movie Already Exists");
        }
        return savedMovie;
    }

    @Override
    public Boolean deleteMovie(int id) throws MovieNotFoundException{
      try{
          movieRepository.deleteById(id);
          if(!movieRepository.existsById(id))
              return true;
          else
              return false;
      }catch (Exception e){
          throw new MovieNotFoundException("Movie Not Found for Deleting");
      }
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(int id) {
        return  movieRepository.findById(id).get();
    }

    @Override
    public Movie updateMovie(int id, Movie movie) throws MovieNotFoundException{
        try {
            movieRepository.deleteById(id);
            movieRepository.save(movie);
            return movie;
        }catch (Exception e){
            throw new MovieNotFoundException("Movie Not Found");
        }
    }

    @Override
    public List<Movie> findByName(String name) throws MovieNotFoundException {
        if(movieRepository.findByName(name).isEmpty()){
            throw new MovieNotFoundException("Movie Not Found");
        }
        return movieRepository.findByName(name);    }


}
