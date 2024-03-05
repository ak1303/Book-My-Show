package Book.My.Show.transformer;

import Book.My.Show.dto.request.AddMovieRequest;
import Book.My.Show.model.Movie;

public class MovieTransformers {
    public static Movie convertRequestToEntity(AddMovieRequest addMovieRequest){
        Movie movie = Movie.builder()
                .artistName(addMovieRequest.getArtistName())
                .movieName(addMovieRequest.getMovieName())
                .genre(addMovieRequest.getGenre())
                .duration(addMovieRequest.getDuration())
                .language(addMovieRequest.getLanguage())
                .releaseDate(addMovieRequest.getReleaseDate())
                .build();
        return movie;
    }
}
