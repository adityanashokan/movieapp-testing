package com.stackroute.MovieApp.controller;

import com.stackroute.MovieApp.domain.Movie;
import com.stackroute.MovieApp.exceptions.MovieAlreadyExistsException;
import com.stackroute.MovieApp.exceptions.MovieNotFoundException;
import com.stackroute.MovieApp.repository.MovieRepository;
import com.stackroute.MovieApp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1")
public class MovieController {

    @Autowired
    MovieService movieService;

    MovieRepository movieRepository;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @PostMapping("movie")
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie) { ResponseEntity responseEntity;
        try {
            movieService.saveMovie(movie);
            responseEntity = new ResponseEntity<String>("Successfully Created", HttpStatus.CREATED);
        }catch (Exception e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @DeleteMapping("movie/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable int id){
        ResponseEntity responseEntity;
        try {
            movieService.deleteMovie(id);
            responseEntity = new ResponseEntity<String>("Successfully Deleted",HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PutMapping("movie/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable int id, @RequestBody Movie upMovie) {
        ResponseEntity responseEntity;
        try {
            movieService.updateMovie(id, upMovie);
            responseEntity = new ResponseEntity<String>("Successfully Updated",HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("movie/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable int id){
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<Movie>(movieService.getMovieById(id) ,HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return null;
    }

    @GetMapping("movie")
    public ResponseEntity<?> getAllMovies(){
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<List<Movie>>(movieService.getAllMovies(), HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }



    @GetMapping("movie/moviename={name}")
    public ResponseEntity<?> getMovieByName(@PathVariable String name) throws MovieNotFoundException {
        ResponseEntity responseEntity;
        responseEntity = new ResponseEntity<List<Movie>>(movieService.findByName(name), HttpStatus.OK);
        return responseEntity;
    }



}
