package Book.My.Show.dto.request;


import lombok.Data;

@Data
public class UserSignupRequest {
    private String emailId;
    private String password;
    private String username;
    private String name;
}
