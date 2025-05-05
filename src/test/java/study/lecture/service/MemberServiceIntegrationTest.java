package study.lecture.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import study.lecture.domain.Member;
import study.lecture.repository.MemberRepository;
import study.lecture.repository.MemoryMemberRepository;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

  @Autowired MemberService memberService;
  @Autowired MemberRepository memberRepository;

  @Test
  void join() {
    // given
    Member member = new Member();
    member.setName("hello");

    // when
    Long saveId = memberService.join(member);

    // then
    Member findMember = memberService.findOne(saveId).get();
    assertThat(member.getName()).isEqualTo(findMember.getName());
  }

  @Test
  public void duplicated_join_test(){
    // given
    Member member = new Member();
    member.setName("hello");

    Member member2 = new Member();
    member2.setName("hello");

    // when

    memberService.join(member);
    IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    // then

  }
}