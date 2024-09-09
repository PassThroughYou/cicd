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
        memberRepository.save(new Member("KIM", "123!@#qwe", "지나", "wlskb@naver.com"));

        // when
        List<Member> members = memberRepository.findAll();
        Member member = members.get(0);

        // then
        Assertions.assertThat(member.getUsername()).isEqualTo("KIM");
    }

    @Test
    void 회원수정() {
        memberRepository.save(new Member("KIM", "123!@#qwe", "지나", "wlskbdkfnb@naver.com"));
        List<Member> members = memberRepository.findAll();
        Member member = members.get(0);

        memberService.update(member.getId(), new MemberEditRequestDto("321!@@@44", "지나아루0813", "wlskbdkfn0813@naver.com"));

        Member findMember = memberRepository.findById(member.getId()).get();

        Assertions.assertThat(findMember.getNickname()).isEqualTo("지나아루0813");
        Assertions.assertThat(findMember.getPassword()).isEqualTo("321!@@@44");
        Assertions.assertThat(findMember.getEmail()).isEqualTo("wlskbdkfn0813@naver.com");

    }
}