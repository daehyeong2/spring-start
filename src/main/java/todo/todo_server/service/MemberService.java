package todo.todo_server.service;

import java.util.List;
import java.util.Optional;
import todo.todo_server.domain.Member;
import todo.todo_server.repository.MemberRepository;
import todo.todo_server.repository.MemoryMemberRepository;

public class MemberService {
  private final MemberRepository memberRepository = new MemoryMemberRepository();

  /**
   * 회원 가입
   */
  public Long join(Member member){
    validateDuplicatename(member); // 중복 회원 검증
    memberRepository.save(member);
    return member.getId();
  }

  private void validateDuplicatename(Member member) {
    memberRepository.findByName(member.getName())
        .ifPresent(m -> {
          throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
  }

  /**
   * 전체 회원 조회
   */
  public List<Member> findMembers(){
    return memberRepository.findAll();
  }

  public Optional<Member> findOne(Long memberId){
    return memberRepository.findById(memberId);
  }
}
