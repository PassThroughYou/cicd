package gna.crud.controller;

import gna.crud.dto.member.MemberEditRequestDto;
import gna.crud.dto.member.MemberRequestDto;
import gna.crud.dto.member.MemberResponseDto;
import gna.crud.response.Response;
import gna.crud.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public Response memberSave(@Valid @RequestBody MemberRequestDto memberRequestDto) {
         MemberResponseDto memberResponseDto = memberService.save(memberRequestDto);
        return Response.success(memberResponseDto);
    }


    @PatchMapping("/{id}")
    public Response memberEdit(@PathVariable Long id, @Valid @RequestBody MemberEditRequestDto memberEditRequestDto) {
        MemberResponseDto memberResponseDto = memberService.update(id, memberEditRequestDto);
        return Response.success(memberResponseDto);
    }


    @DeleteMapping("/{id}")
    public Response memberDelete(@PathVariable Long id) {
        memberService.delete(id);
        return Response.success("회원 삭제 완료");
    }

}
