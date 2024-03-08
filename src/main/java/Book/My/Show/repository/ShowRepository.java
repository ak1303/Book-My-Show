package Book.My.Show.repository;

import Book.My.Show.dto.request.AddShowRequest;
import Book.My.Show.model.Movie;
import Book.My.Show.model.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<Show,Integer> {
}
