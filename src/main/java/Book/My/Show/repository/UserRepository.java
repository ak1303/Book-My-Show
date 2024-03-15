package Book.My.Show.repository;

import Book.My.Show.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByEmailId(String emailId);
}
