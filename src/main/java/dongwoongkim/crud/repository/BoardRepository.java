package dongwoongkim.crud.repository;

import dongwoongkim.crud.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long>{
}
