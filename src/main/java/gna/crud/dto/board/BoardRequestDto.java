package gna.crud.dto.board;

import gna.crud.domain.board.Board;
import gna.crud.domain.member.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequestDto {

    private Long id;

    @NotBlank(message = "게시글 제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "게시글 내용을 입력해주세요.")
    private String content;

    private Member member;

    public static BoardRequestDto toDto(Board board) {
        return new BoardRequestDto(board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getMember());
    }

}
