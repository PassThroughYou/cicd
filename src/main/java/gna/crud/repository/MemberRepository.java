package gna.crud.repository;

import gna.crud.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository  extends JpaRepository<Member,Long>{

    // 기본적인 CRUD 코드는 Spring Data JPA가 제공해줌.


    // Spring Data JPA에서 제공하는 쿼리 메소드 기능으로 작성한 메소드
    Optional<Member> findByNickname(String nickName);
    Optional<Member> findByEmail(String email);

}


// : 규칙에 따라 메소드만 선언해주면 DB에 전달하는 SQL문은