package study.lecture.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.lecture.domain.Member;
import study.lecture.repository.MemberRepository;

@Transactional
public class MemberService {
  private final MemberRepository memberRepository;

  @Autowired
  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

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
