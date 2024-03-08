package Book.My.Show.dto.request;

import lombok.Data;

@Data
public class AddTheatreSeatsRequest {
    private Integer noOfClassicSeats;
    private Integer noOfPremiumSeats;
    private Integer id;
}
