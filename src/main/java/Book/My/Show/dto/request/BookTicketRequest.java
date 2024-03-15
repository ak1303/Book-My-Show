package Book.My.Show.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class BookTicketRequest {
    private Integer showId;
    private List<String> selectedSeats;
    private String emailId;
}
