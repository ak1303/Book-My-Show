package Book.My.Show.controller;

import Book.My.Show.dto.request.AddMovieRequest;
import Book.My.Show.model.Movie;
import Book.My.Show.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movie")
public class MovieController {

    @Autowired
    MovieService movieService;
    @PostMapping("/addMovie")
    private ResponseEntity<String> addMovie(@RequestBody AddMovieRequest addMovieRequest){
        Movie movie = movieService.addMovie(addMovieRequest);
        return new ResponseEntity("Movie added successfully", HttpStatus.CREATED);
    }
}
