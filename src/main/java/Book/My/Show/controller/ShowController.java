package Book.My.Show.controller;

import Book.My.Show.dto.request.AddShowRequest;
import Book.My.Show.dto.request.AddShowSeatRequest;
import Book.My.Show.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("show")
public class ShowController {
    @Autowired
    ShowService showService;
    @PostMapping("/addShow")
    private ResponseEntity<String> addShow(@RequestBody AddShowRequest addShowRequest){
        String result = showService.addShow(addShowRequest);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    @PostMapping("/addShowSeats")
    private ResponseEntity<String> addShowSeats(@RequestBody AddShowSeatRequest addShowSeatRequest){
        String result = showService.addShowSeats(addShowSeatRequest);
        return  new ResponseEntity<>(result,HttpStatus.CREATED);
    }
}
