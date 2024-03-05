package Book.My.Show.service;

import Book.My.Show.dto.request.AddTheatreRequest;
import Book.My.Show.model.Theatre;
import Book.My.Show.repository.TheatreRepository;
import Book.My.Show.transformer.TheatreTransformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheatreService {
    @Autowired
    TheatreRepository theatreRepository;

    public String addTheatre(AddTheatreRequest addTheatreRequest){
        Theatre theatre = TheatreTransformers.convertRequestToEntity(addTheatreRequest);
        theatre = theatreRepository.save(theatre);
        return "theatre "+ theatre.getName()+" with theatre id "+theatre.getId()+" added successfully";
    }
}
