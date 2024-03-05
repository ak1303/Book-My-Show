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
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;
    private String address;
    private Integer noOfScreen;

    @OneToMany(mappedBy = "theatre")
    private Show show;

    @OneToMany(mappedBy = "theatre")
    private TheatreSeat theatreSeat;
}
