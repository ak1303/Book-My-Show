package Book.My.Show.service;

import Book.My.Show.dto.request.UserLoginRequest;
import Book.My.Show.dto.request.UserSignupRequest;
import Book.My.Show.model.User;
import Book.My.Show.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public String login(UserLoginRequest userLoginRequest) throws Exception{
        String email = userLoginRequest.getEmailId();
        String password = userLoginRequest.getPassword();

        User user = userRepository.findByEmailId(email);
        if(user==null) throw new Exception("User email id not present in database");
        if(!user.getPassword().equals(password)) throw new Exception("Password Entered is wrong");
        return "Logged in successfully";
    }
    public String signup(UserSignupRequest userSignupRequest){
        String email = userSignupRequest.getEmailId();
        String password = userSignupRequest.getPassword();
        String name = userSignupRequest.getName();
        String userName = userSignupRequest.getUsername();

        User user = User.builder()
                .emailId(email)
                .password(password)
                .name(name)
                .userName(userName)
                .build();
        userRepository.save(user);
        return "Account created Successfully";
    }
}
