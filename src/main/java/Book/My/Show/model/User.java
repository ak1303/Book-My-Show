package Book.My.Show.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column( unique = true)
    private String emailId;
    private String password;
    private String userName;
    private String name;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Ticket> ticketList = new ArrayList<>();
}
