package Book.My.Show.dto.request;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String emailId;
    private String password;
}
