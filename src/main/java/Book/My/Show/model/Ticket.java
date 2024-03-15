package Book.My.Show.model;

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
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String seatNumbers;//list<booked seats> in string
    private Integer totalAmountPaid;

    @JoinColumn
    @ManyToOne
    private Show show;

    @JoinColumn
    @ManyToOne
    private User user;
}
