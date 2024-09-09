package dongwoongkim.crud.dto.board;

import dongwoongkim.crud.domain.board.Board;
import dongwoongkim.crud.dto.comment.CommentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponseDto {

    private Long id;
    private String titleDto;
    private String contentDto;
    private String writerDto;
    private LocalDateTime createdTime;
    private LocalDateTime lastModified;
    private List<CommentResponseDto> comments = new ArrayList<>();

    public static BoardResponseDto toDto(Board board) {
        return new BoardResponseDto(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getMember().getNickname(),
                board.getCreatedDate(),
                board.getLastModified(),
                board.getComments().stream().map(comment -> CommentResponseDto.toDto(comment)).collect(Collectors.toList()));
    }
}
