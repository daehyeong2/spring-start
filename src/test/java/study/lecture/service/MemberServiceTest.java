package study.lecture.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import study.lecture.domain.Member;
import study.lecture.repository.MemberRepository;
import study.lecture.repository.MemoryMemberRepository;

class MemberServiceTest {

  MemberService memberService;
  MemoryMemberRepository memberRepository;

  @BeforeEach
  public void BeforeEach(){
    memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);
  }

  @AfterEach
  public void afterEach(){
    memberRepository.clearStore();
  }

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

  @Test
  void findMembers() {
  }

  @Test
  void findOne() {
  }
}