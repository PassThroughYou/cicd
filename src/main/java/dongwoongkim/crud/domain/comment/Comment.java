package dongwoongkim.crud.domain.comment;

import dongwoongkim.crud.domain.BaseEntity;
import dongwoongkim.crud.domain.board.Board;
import dongwoongkim.crud.domain.member.Member;
import dongwoongkim.crud.dto.comment.CommentRequestDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public Comment(String comment, Board board, Member member) {
        this.comment = comment;
        this.board = board;
        this.member = member;
    }

    public static Comment toEntity(CommentRequestDto commentRequestDto) {
        return new Comment(commentRequestDto.getComment(),commentRequestDto.getBoard(),commentRequestDto.getMember());
    }

    public void update(String comment) {
        this.comment = comment;
    }

}
