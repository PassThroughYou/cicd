package dongwoongkim.crud.repository;

import dongwoongkim.crud.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
