package dongwoongkim.crud.service;

import dongwoongkim.crud.domain.board.Board;
import dongwoongkim.crud.domain.comment.Comment;
import dongwoongkim.crud.domain.member.Member;
import dongwoongkim.crud.dto.comment.CommentEditRequestDto;
import dongwoongkim.crud.dto.comment.CommentRequestDto;
import dongwoongkim.crud.dto.comment.CommentResponseDto;
import dongwoongkim.crud.exception.BoardNotFoundException;
import dongwoongkim.crud.exception.CommentNotFoundException;
import dongwoongkim.crud.exception.MemberNotFoundException;
import dongwoongkim.crud.repository.BoardRepository;
import dongwoongkim.crud.repository.CommentRepository;
import dongwoongkim.crud.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public CommentResponseDto save(@RequestBody CommentRequestDto commentRequestDto, Long boardId, String nickName) {

        Member member = memberRepository.findByNickname(nickName).orElseThrow(MemberNotFoundException::new);
        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);

        commentRequestDto.setBoard(board);
        commentRequestDto.setMember(member);

        Comment comment = commentRepository.save(Comment.toEntity(commentRequestDto));
        return CommentResponseDto.toDto(comment);
    }
    public CommentResponseDto update(Long id, CommentEditRequestDto commentEditRequestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
        comment.update(commentEditRequestDto.getComment());
        return CommentResponseDto.toDto(comment);
    }
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
        commentRepository.delete(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> findAll(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        List<Comment> comments = board.getComments();
        return comments.stream().map(c -> CommentResponseDto.toDto(c)).collect(Collectors.toList());
    }

}
