package gna.crud.controller;

import gna.crud.dto.board.BoardResponseDto;
import gna.crud.dto.comment.CommentEditRequestDto;
import gna.crud.dto.comment.CommentRequestDto;
import gna.crud.dto.comment.CommentResponseDto;
import gna.crud.dto.member.MemberResponseDto;
import gna.crud.response.Response;
import gna.crud.service.BoardService;
import gna.crud.service.CommentService;
import gna.crud.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class CommentController {

    private final BoardService boardService;
    private final CommentService commentService;
    private final MemberService memberService;

    @GetMapping("/{boardId}/comment")
    public Response getCommentInBoard(@PathVariable Long boardId) {
        List<CommentResponseDto> commentAllResponseDto = commentService.findAll(boardId);
        return Response.success(commentAllResponseDto);
    }
    @PostMapping("/{boardId}/comment")
    public Response commentPost(@PathVariable Long boardId, @Valid @RequestBody CommentRequestDto commentRequestDto,
                         @RequestParam("name") String nickName) {
        BoardResponseDto boardResponseDto = boardService.findById(boardId);
        MemberResponseDto memberResponseDto = memberService.findByNickname(nickName);
        CommentResponseDto commentResponseDto = commentService.save(commentRequestDto, boardId, nickName);
        return Response.success(commentResponseDto);
    }
    @PatchMapping("/comment/{commentId}")
    public Response commentEdit(@PathVariable Long commentId, @Valid @RequestBody CommentEditRequestDto commentEditRequestDto) {
        CommentResponseDto commentResponseDto = commentService.update(commentId, commentEditRequestDto);
        return Response.success(commentResponseDto);
    }

    @DeleteMapping("/comment/{commentId}")
    public Response commentDelete(@PathVariable Long commentId) {
        commentService.delete(commentId);
        return Response.success("댓글 삭제 완료");
    }
}
