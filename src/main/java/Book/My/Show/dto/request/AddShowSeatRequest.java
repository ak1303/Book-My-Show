package Book.My.Show.dto.request;

import lombok.Data;

@Data
public class AddShowSeatRequest {
    private Integer classicSeatPrice;
    private Integer premiumSeatPrice;
    private Integer showId;
}
