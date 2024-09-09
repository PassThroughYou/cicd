package dongwoongkim.crud.dto.comment;

import dongwoongkim.crud.domain.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

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
