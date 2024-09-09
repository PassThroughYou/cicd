package gna.crud.dto.comment;

import gna.crud.domain.board.Board;
import gna.crud.domain.comment.Comment;
import gna.crud.domain.member.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class CommentRequestDto {

    @NotBlank(message = "댓글을 입력해주세요.")
    private String comment;
    private Board board;
    private Member member;

    public static CommentRequestDto toDto(Comment comment) {
        return new CommentRequestDto(comment.getComment(), comment.getBoard(), comment.getMember());
    }
}
