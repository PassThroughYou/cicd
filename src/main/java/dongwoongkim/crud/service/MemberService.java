package dongwoongkim.crud.service;

import dongwoongkim.crud.domain.member.Member;
import dongwoongkim.crud.dto.member.MemberEditRequestDto;
import dongwoongkim.crud.dto.member.MemberRequestDto;
import dongwoongkim.crud.dto.member.MemberResponseDto;
import dongwoongkim.crud.exception.MemberNotFoundException;
import dongwoongkim.crud.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public MemberResponseDto save(MemberRequestDto memberRequestDto) {
        return MemberResponseDto.toDto(memberRepository.save(new Member(memberRequestDto)));
    }

    @Transactional
    public MemberResponseDto update(Long id, MemberEditRequestDto memberEditRequestDto) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        member.updateMember(memberEditRequestDto);
        return MemberResponseDto.toDto(member);
    }

    public MemberResponseDto findByNickname(String nickName) {
        Member member = memberRepository.findByNickname(nickName).orElseThrow(MemberNotFoundException::new);
        log.info("member = {} ",member.getEmail());
        return MemberResponseDto.toDto(member);
    }

    @Transactional
    public void delete(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        memberRepository.delete(member);
    }


}
