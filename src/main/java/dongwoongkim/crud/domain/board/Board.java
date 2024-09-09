package dongwoongkim.crud.domain.board;

import dongwoongkim.crud.domain.comment.Comment;
import dongwoongkim.crud.domain.member.Member;
import dongwoongkim.crud.domain.BaseEntity;
import dongwoongkim.crud.dto.board.BoardEditRequestDto;
import dongwoongkim.crud.dto.board.BoardRequestDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "board")
    @OrderBy("id asc")
    private List<Comment> comments = new ArrayList<>();

    public Board(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }
    public static Board toEntity(BoardRequestDto boardRequestDto) {
        return new Board(boardRequestDto.getTitle(), boardRequestDto.getContent(), boardRequestDto.getMember());
    }

    public void updateBoard(BoardEditRequestDto boardEditRequestDto) {
        this.title = boardEditRequestDto.getTitle();
        this.content = boardEditRequestDto.getContent();
    }
}
