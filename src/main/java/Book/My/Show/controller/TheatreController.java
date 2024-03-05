package Book.My.Show.controller;

import Book.My.Show.dto.request.AddTheatreRequest;
import Book.My.Show.service.TheatreService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("theatre")
public class TheatreController {

    @Autowired
    TheatreService theatreService;
    @PostMapping("/addTheatre")
    private ResponseEntity<String> addTheatre(@RequestBody AddTheatreRequest addTheatreRequest){
        String result = theatreService.addTheatre(addTheatreRequest);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
