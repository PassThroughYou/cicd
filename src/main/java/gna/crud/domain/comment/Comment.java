package gna.crud.domain.comment;

import gna.crud.domain.BaseEntity;
import gna.crud.domain.board.Board;
import gna.crud.domain.member.Member;
import gna.crud.dto.comment.CommentRequestDto;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

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
