package Book.My.Show.service;

import Book.My.Show.dto.request.AddShowRequest;
import Book.My.Show.dto.request.AddShowSeatRequest;
import Book.My.Show.enums.SeatType;
import Book.My.Show.model.*;
import Book.My.Show.repository.MovieRepository;
import Book.My.Show.repository.ShowRepository;
import Book.My.Show.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TheatreRepository theatreRepository;
    public String addShow(AddShowRequest addShowRequest){
        Movie movie = movieRepository.findMovieByMovieName(addShowRequest.getMovieName());
        Theatre theatre = theatreRepository.findById(addShowRequest.getTheaterId()).get();

        Show show = Show.builder()
                .showDate(addShowRequest.getShowDate())
                .showTime(addShowRequest.getShowTime())
                .theatre(theatre)
                .movie(movie)
                .build();

        movie.getShows().add(show);
        theatre.getShowList().add(show);
        show = showRepository.save(show);
        return "show "+show.getId()+"added successfully";
    }

    public  String addShowSeats(AddShowSeatRequest addShowSeatRequest){
        int classicSeatPrice = addShowSeatRequest.getClassicSeatPrice();
        int premiumSeatPrice = addShowSeatRequest.getPremiumSeatPrice();
        int showId = addShowSeatRequest.getShowId();

        Show show = showRepository.findById(showId).get();
        List<TheatreSeat> seats = show.getTheatre().getTheatreSeatList();

        List<ShowSeat> showSeatList = new ArrayList<>();

        for(TheatreSeat seat : seats){
            ShowSeat showSeat = ShowSeat.builder()
                    .show(show)
                    .availability(false)
                    .foodCoupon(false)
                    .seatNumber(seat.getSeatNo())
                    .seatType(seat.getSeatType())
                    .build();
            //add price to showSeat
            if(seat.getSeatType().equals(SeatType.CLASSIC))
                showSeat.setPrice(classicSeatPrice);
            else showSeat.setPrice(premiumSeatPrice);
            showSeatList.add(showSeat);
        }
        show.setSeats(showSeatList);
        showRepository.save(show);
        return "Show seats added successfully";
    }
}
