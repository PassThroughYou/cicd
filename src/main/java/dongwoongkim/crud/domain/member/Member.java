package dongwoongkim.crud.domain.member;

import dongwoongkim.crud.domain.comment.Comment;
import dongwoongkim.crud.domain.BaseEntity;
import dongwoongkim.crud.domain.board.Board;
import dongwoongkim.crud.dto.member.MemberEditRequestDto;
import dongwoongkim.crud.dto.member.MemberRequestDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;


    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MemberRole> roles = new HashSet<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Board> boards = new ArrayList<>();

    public Member(MemberRequestDto memberRequestDto) {
        this.username = memberRequestDto.getUsername();
        this.password = memberRequestDto.getPassword();
        this.nickname = memberRequestDto.getNickname();
        this.email = memberRequestDto.getEmail();
    }

    public Member(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }

    public void updateMember(MemberEditRequestDto memberEditRequestDto) {
        this.password = memberEditRequestDto.getPassword();
        this.email = memberEditRequestDto.getEmail();
        this.nickname = memberEditRequestDto.getNickname();
    }

}
