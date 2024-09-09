package gna.crud.domain.board;

import gna.crud.domain.comment.Comment;
import gna.crud.domain.member.Member;
import gna.crud.domain.BaseEntity;
import gna.crud.dto.board.BoardEditRequestDto;
import gna.crud.dto.board.BoardRequestDto;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

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
