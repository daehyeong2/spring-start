package study.lecture;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.lecture.repository.MemberRepository;
import study.lecture.repository.MemoryMemberRepository;
import study.lecture.service.MemberService;

@Configuration
public class  SpringConfig {
  @Bean
  public MemberService memberService(){
    return new MemberService(memberRepository());
  }

  @Bean
  public MemberRepository memberRepository(){
    return new MemoryMemberRepository();
  }
}
