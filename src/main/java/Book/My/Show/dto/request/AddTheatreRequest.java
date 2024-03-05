package Book.My.Show.dto.request;

import lombok.Data;

@Data
public class AddTheatreRequest {
    private String name;
    private String address;
    private Integer noOfScreen;
}
