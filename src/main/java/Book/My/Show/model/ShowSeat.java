package Book.My.Show.model;

import Book.My.Show.enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "show_seats")
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer price;
    private boolean availability;
    private boolean foodCoupon;
    private String seatNumber;
    private SeatType seatType;

    @JoinColumn
    @ManyToOne
    private Show show;
}
