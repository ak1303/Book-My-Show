package Book.My.Show.controller;

import Book.My.Show.dto.request.UserLoginRequest;
import Book.My.Show.dto.request.UserSignupRequest;
import Book.My.Show.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/login")
    private ResponseEntity<String> login(@RequestBody UserLoginRequest userLoginRequest){
        try{
            String result = userService.login(userLoginRequest);
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/signup")
    private ResponseEntity<String> signup(@RequestBody UserSignupRequest userSignupRequest){
        try{
            String result = userService.signup(userSignupRequest);
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
