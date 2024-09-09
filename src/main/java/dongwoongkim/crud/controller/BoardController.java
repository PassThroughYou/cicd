package dongwoongkim.crud.controller;


import dongwoongkim.crud.dto.board.BoardEditRequestDto;
import dongwoongkim.crud.dto.board.BoardRequestDto;
import dongwoongkim.crud.dto.board.BoardResponseDto;
import dongwoongkim.crud.dto.member.MemberResponseDto;
import dongwoongkim.crud.response.Response;
import dongwoongkim.crud.service.BoardService;
import dongwoongkim.crud.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping
    public Response getBoards(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BoardResponseDto> pages = boardService.findAll(pageable);
        return Response.success(pages);
    }

    @GetMapping("/{id}")
    public Response getBoardById(@PathVariable Long id) {
        BoardResponseDto boardResponseDto = boardService.findById(id);
        return Response.success(boardResponseDto);
    }

    @PostMapping
    public Response boardPost(@Valid @RequestBody BoardRequestDto boardRequestDto, @RequestParam String nickName) {
        MemberResponseDto memberResponseDto = memberService.findByNickname(nickName);
        BoardResponseDto boardResponseDto = boardService.save(boardRequestDto,nickName);
        return Response.success(boardResponseDto);
    }

    @PatchMapping("/{id}")
    public Response boardEdit(@PathVariable Long id, @Valid @RequestBody BoardEditRequestDto boardEditRequestDto) {
        BoardResponseDto boardResponseDto = boardService.update(id, boardEditRequestDto);
        return Response.success(boardResponseDto);
    }

    @DeleteMapping("/{id}")
    public Response boardDelete(@PathVariable Long id) {
        boardService.delete(id);
        return Response.success("글 삭제 완료");
    }

}
