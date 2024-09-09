package gna.crud.dto.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentEditRequestDto {

    @NotBlank(message = "댓글을 입력해주세요.")
    private String comment;
}
