package Book.My.Show.dto.request;

import Book.My.Show.enums.Genres;
import Book.My.Show.enums.Language;
import lombok.Data;
import java.time.LocalDate;


@Data
public class AddMovieRequest {
    private String movieName;
    private Genres genre;
    private Language language;
    private String artistName;
    private LocalDate releaseDate;
    private double duration;
}
