package pl.reaktor.w3.miniblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.reaktor.w3.miniblog.entities.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository <Comment, Long>{

    List<Comment>findAllByPostId(Long postId);
}
