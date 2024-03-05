package Book.My.Show.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate showDate;
    private LocalDate showTime;

    @JoinColumn
    @ManyToOne
    private Movie movie;

    @OneToMany(mappedBy = "show")
    private List<ShowSeat> seats;

    @JoinColumn
    @ManyToOne
    private Theatre theatre;

    @OneToMany(mappedBy = "show")
    private List<Movie> movies;
}
