package Book.My.Show.service;

import Book.My.Show.dto.request.AddTheatreRequest;
import Book.My.Show.dto.request.AddTheatreSeatsRequest;
import Book.My.Show.enums.SeatType;
import Book.My.Show.model.Theatre;
import Book.My.Show.model.TheatreSeat;
import Book.My.Show.repository.TheatreRepository;
import Book.My.Show.transformer.TheatreTransformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreService {
    @Autowired
    TheatreRepository theatreRepository;

    public String addTheatre(AddTheatreRequest addTheatreRequest){
        Theatre theatre = TheatreTransformers.convertRequestToEntity(addTheatreRequest);
        theatre = theatreRepository.save(theatre);
        return "theatre "+ theatre.getName()+" with theatre id "+theatre.getId()+" added successfully";
    }
    public String addTheatreSeats(AddTheatreSeatsRequest addTheatreSeatsRequest){

        int noOfClassicSeats = addTheatreSeatsRequest.getNoOfClassicSeats();
        int noOfPremiumSeats = addTheatreSeatsRequest.getNoOfPremiumSeats();
        List<TheatreSeat> seatList = new ArrayList<>();
        Theatre theatre = theatreRepository.findById(addTheatreSeatsRequest.getId()).get();

        int seatsInaRow=5;
        int countClassic=0;
        for(int i=0;i<noOfClassicSeats;i++){
            char col = (char)((i%seatsInaRow)+'A');
            int row = i/seatsInaRow+1;
            String seatNumber = row+""+col;//1A,2B
            countClassic++;
            TheatreSeat theatreSeat = TheatreSeat.builder()
                    .seatNo(seatNumber)
                    .seatType(SeatType.CLASSIC)
                    .theatre(theatre)
                    .build();
            seatList.add(theatreSeat);
            if(countClassic==noOfClassicSeats)break;
        }
        int countPremium=0;
        int startRow = (int)Math.ceil(noOfClassicSeats/seatsInaRow);
        for(int i=0;i<noOfClassicSeats;i++){
            char col = (char)((i%seatsInaRow)+'A');
            int row = (i/seatsInaRow+1)+startRow;
            String seatNumber = row+""+col;//1A,2B
            countPremium++;
            TheatreSeat theatreSeat = TheatreSeat.builder()
                    .seatNo(seatNumber)
                    .seatType(SeatType.PREMIUM)
                    .theatre(theatre)            //save parent to child
                    .build();
            seatList.add(theatreSeat);
            if(countPremium==noOfPremiumSeats)break;
        }
        theatre.setTheatreSeatList(seatList);//save child to parent
        theatreRepository.save(theatre);//save parent , child table will automatically update
        return "Seats added Successfully";
    }
}
