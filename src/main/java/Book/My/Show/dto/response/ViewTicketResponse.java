package Book.My.Show.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViewTicketResponse {
    private String seatNumbers;
    private Integer totalAmountPaid;
    private String address;//theatre name + show name
    private LocalDate showDate;
    private LocalTime showTime;
    private String movieName;
}
