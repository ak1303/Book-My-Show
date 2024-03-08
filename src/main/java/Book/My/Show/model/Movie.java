package Book.My.Show.model;

import Book.My.Show.enums.Genres;
import Book.My.Show.enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String movieName;

    @Enumerated(value = EnumType.STRING)
    private Genres genre;

    @Enumerated(value = EnumType.STRING)
    private Language language;

    private String artistName;
    private LocalDate releaseDate;
    private double duration;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Show> shows = new ArrayList<>();
}
