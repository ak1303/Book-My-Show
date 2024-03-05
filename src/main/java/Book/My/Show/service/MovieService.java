package Book.My.Show.service;

import Book.My.Show.dto.request.AddMovieRequest;
import Book.My.Show.model.Movie;
import Book.My.Show.repository.MovieRepository;
import Book.My.Show.transformer.MovieTransformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;
    public Movie addMovie(AddMovieRequest addMovieRequest){
        Movie movie = MovieTransformers.convertRequestToEntity(addMovieRequest);
        movie = movieRepository.save(movie);
        return movie;
    }
}
