package gna.crud.service;

import gna.crud.domain.board.Board;
import gna.crud.domain.comment.Comment;
import gna.crud.domain.member.Member;
import gna.crud.dto.comment.CommentEditRequestDto;
import gna.crud.dto.comment.CommentRequestDto;
import gna.crud.dto.comment.CommentResponseDto;
import gna.crud.exception.BoardNotFoundException;
import gna.crud.exception.CommentNotFoundException;
import gna.crud.exception.MemberNotFoundException;
import gna.crud.repository.BoardRepository;
import gna.crud.repository.CommentRepository;
import gna.crud.repository.MemberRepository;
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
