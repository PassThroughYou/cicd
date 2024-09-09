package gna.crud.dto.comment;

import gna.crud.domain.comment.Comment;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {

    @NotBlank(message = "댓글을 입력해주세요.")
    private String comment;
    private String nickName;
    private Long boardId;
    private Long memberId;
    public static CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(comment.getComment(), comment.getMember().getNickname(), comment.getBoard().getId(), comment.getMember().getId());

    }
}
