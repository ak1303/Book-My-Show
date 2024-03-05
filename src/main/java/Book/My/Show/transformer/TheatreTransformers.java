package Book.My.Show.transformer;

import Book.My.Show.dto.request.AddTheatreRequest;
import Book.My.Show.model.Theatre;
import Book.My.Show.repository.TheatreRepository;

public class TheatreTransformers {

    public static Theatre convertRequestToEntity(AddTheatreRequest addTheatreRequest){
        Theatre theatre = Theatre.builder()
                .name(addTheatreRequest.getName())
                .address(addTheatreRequest.getAddress())
                .noOfScreen(addTheatreRequest.getNoOfScreen())
                .build();
        return theatre;
    }
}
