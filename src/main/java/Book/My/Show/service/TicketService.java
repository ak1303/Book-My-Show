package Book.My.Show.service;

import Book.My.Show.dto.request.BookTicketRequest;
import Book.My.Show.dto.response.ViewTicketResponse;
import Book.My.Show.exceptions.SeatNotAvailableException;
import Book.My.Show.model.*;
import Book.My.Show.repository.ShowRepository;
import Book.My.Show.repository.TicketRepository;
import Book.My.Show.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    ShowRepository showRepository;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    MailService mailService;

    public String bookTicket(BookTicketRequest bookTicketRequest) throws Exception{

        List<String> selectedSeats = bookTicketRequest.getSelectedSeats();
        int showId = bookTicketRequest.getShowId();
        Optional<Show> optionalShow= showRepository.findById(showId);
        if(optionalShow.isPresent()){
            Show show = showRepository.findById(showId).get();
            int totalAmount = getTotalAmount(show, selectedSeats);
            //All tickets can be booked now
            //implement food coupon feature later
            User user = userRepository.findByEmailId(bookTicketRequest.getEmailId());
            Ticket ticket = Ticket.builder()
                    .show(show)
                    .seatNumbers(selectedSeats.toString())
                    .totalAmountPaid(totalAmount)
                    .user(user)
                    .build();

            show.getTickets().add(ticket);
            user.getTicketList().add(ticket);
            ticket = ticketRepository.save(ticket);
            return "Ticket with id:"+ticket.getId()+"booked successfully";
        }
        else throw new Exception("Show with given id is not present in database");
    }

    private static int getTotalAmount(Show show, List<String> selectedSeats) throws SeatNotAvailableException {
        List<ShowSeat> showSeats = show.getSeats();
        int totalAmount=0;
        //calculate total amount if all selected seats available
        for(String selectedSeat : selectedSeats){//iterate over all seats
            for(ShowSeat showSeat : showSeats){//iterate over all show seat and find matching seat
                if(showSeat.getSeatNumber().equals(selectedSeat)){//selected seat found
                    if(!showSeat.isAvailability())
                        throw new SeatNotAvailableException("Seat selected is not available");
                    totalAmount += showSeat.getPrice();
                }
            }
        }
        return totalAmount;
    }

    public String cancelTicket(int ticketId) throws Exception{
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if(optionalTicket.isPresent()){
            Ticket ticket = optionalTicket.get();
            List<String> seatNos = new ArrayList<>(Arrays.asList(ticket.getSeatNumbers().split(",")));
            Show show = ticket.getShow();
            List<ShowSeat> showSeats = show.getSeats();

            for(ShowSeat showSeat : showSeats){
                String showSeatNumber = showSeat.getSeatNumber();
                if(seatNos.contains(showSeatNumber)){//make this seat available
                    showSeat.setAvailability(true);
                    showSeat.setFoodCoupon(false);
                }
            }
            showRepository.save(show);//updated show seats also saved
            ticketRepository.delete(ticket);
            return "Ticket with ticket id:"+ticketId+" cancelled successfully";
        }
        else throw new Exception("Ticked with given Id not present in Database");
    }

    public ViewTicketResponse viewTicket(Integer ticketId) throws  Exception{
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if(optionalTicket.isPresent()){
            Ticket ticket = optionalTicket.get();
            Theatre theatre =  ticket.getShow().getTheatre();
            String address = theatre.getName()+", "+theatre.getAddress();
            ViewTicketResponse  viewTicketResponse= ViewTicketResponse.builder()
                    .seatNumbers(ticket.getSeatNumbers())
                    .totalAmountPaid(ticket.getTotalAmountPaid())
                    .showDate(ticket.getShow().getShowDate())
                    .showTime(ticket.getShow().getShowTime())
                    .movieName(ticket.getShow().getMovie().getMovieName())
                    .address(address)
                    .build();
            //send ticket mail
            User user = ticket.getUser();
            Show show = ticket.getShow();
            String to= user.getEmailId();
            String sub = "Movie Ticket";
            String text = String.format("""
                        Hi %s, Check Your Ticket, below are your details
                         1. Movie Name : %s
                         2. Address : %s
                         3. Date : %s
                         4. Time: %s
                         5. Seats: %s
                         6. Amount Paid: %d
                         """,
                    user.getName(),
                    viewTicketResponse.getMovieName(),
                    viewTicketResponse.getAddress(),
                    viewTicketResponse.getShowDate(),
                    viewTicketResponse.getShowTime(),
                    viewTicketResponse.getSeatNumbers(),
                    viewTicketResponse.getTotalAmountPaid()
            );
            mailService.generateMail(to,sub,text);

            return viewTicketResponse;

        }else{
            throw new Exception("Given ticket id is not available in database. Enter Correct ticket id");
        }
    }
}
