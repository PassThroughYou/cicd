package dongwoongkim.crud.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentEditRequestDto {

    @NotBlank(message = "댓글을 입력해주세요.")
    private String comment;
}
