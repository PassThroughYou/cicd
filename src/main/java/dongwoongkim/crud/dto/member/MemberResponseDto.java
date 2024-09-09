package dongwoongkim.crud.dto.member;

import dongwoongkim.crud.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {

    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String email;

    public static MemberResponseDto toDto(Member member) {
        return new MemberResponseDto(
                member.getId(),
                member.getUsername(),
                member.getPassword(),
                member.getNickname(),
                member.getEmail());
    }
}
