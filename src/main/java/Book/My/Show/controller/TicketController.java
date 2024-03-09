package Book.My.Show.controller;

import Book.My.Show.dto.request.BookTicketRequest;
import Book.My.Show.dto.response.ViewTicketResponse;
import Book.My.Show.exceptions.SeatNotAvailableException;
import Book.My.Show.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;
    @PostMapping("/bookTicket")
    private ResponseEntity<String> bookTicket(BookTicketRequest bookTicketRequest){
        try {
            String res = ticketService.bookTicket(bookTicketRequest);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }catch (SeatNotAvailableException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/cancelTicket/{ticketId}")
    private ResponseEntity<String> cancelTicket(@PathVariable(value = "ticketId") Integer ticketId){
        try {
            String res = ticketService.cancelTicket(ticketId);
            return new ResponseEntity<>(res,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/viewTicket/{ticketId}")
    private  ResponseEntity<ViewTicketResponse> viewTicket(@PathVariable Integer ticketId){
        try{
            ViewTicketResponse viewTicketResponse = ticketService.viewTicket(ticketId);
            return new ResponseEntity<>(viewTicketResponse,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
