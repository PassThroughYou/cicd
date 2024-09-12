package gna.crud.service;

import gna.crud.domain.member.Member;
import gna.crud.dto.member.MemberEditRequestDto;
import gna.crud.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @AfterEach
    void clear() {
        memberRepository.deleteAll();
    }

    @Test
    void 회원생성() {
        // given
        memberRepository.save(new Member("김티맥맥", "123!@#qwe", "티맥이루", "tmax@naver.com"));

        // when
        List<Member> members = memberRepository.findAll();
        Member member = members.get(0);

        // then
        Assertions.assertThat(member.getUsername()).isEqualTo("김티맥맥");
    }

    @Test
    void 회원수정() {
        memberRepository.save(new Member("김티맥맥", "123!@#qwe", "티맥이루", "tmax@naver.com"));
        List<Member> members = memberRepository.findAll();
        Member member = members.get(0);

        memberService.update(member.getId(), new MemberEditRequestDto("321!@@@44", "티라미수케잌", "tmax@naver.com"));

        Member findMember = memberRepository.findById(member.getId()).get();

        Assertions.assertThat(findMember.getNickname()).isEqualTo("티라미수케잌");
        Assertions.assertThat(findMember.getPassword()).isEqualTo("321!@@@44");
        Assertions.assertThat(findMember.getEmail()).isEqualTo("tmax@naver.com");

    }
}