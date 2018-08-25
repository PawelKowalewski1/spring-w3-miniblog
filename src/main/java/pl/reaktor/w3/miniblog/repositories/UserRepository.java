package pl.reaktor.w3.miniblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.reaktor.w3.miniblog.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    List<User> findAllByEmail(String email);
}
