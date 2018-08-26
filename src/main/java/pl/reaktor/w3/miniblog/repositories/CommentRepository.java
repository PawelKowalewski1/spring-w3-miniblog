package pl.reaktor.w3.miniblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.reaktor.w3.miniblog.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository <Comment, Long>{
}
