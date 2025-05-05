package study.lecture;

import jakarta.persistence.EntityManager;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.lecture.repository.JdbcMemberRepository;
import study.lecture.repository.JdbcTemplateMemberRepository;
import study.lecture.repository.JpaMemberRepository;
import study.lecture.repository.MemberRepository;
import study.lecture.repository.MemoryMemberRepository;
import study.lecture.service.MemberService;

@Configuration
public class  SpringConfig {
  EntityManager em;

  public SpringConfig(EntityManager em) {
    this.em = em;
  }

  //  private DataSource dataSource;
//
//  @Autowired
//  public SpringConfig(DataSource dataSource) {
//    this.dataSource = dataSource;
//  }

  @Bean
  public MemberService memberService(){
    return new MemberService(memberRepository());
  }

  @Bean
  public MemberRepository memberRepository(){
//    return new MemoryMemberRepository();
//    return new JdbcMemberRepository(dataSource);
//    return new JdbcTemplateMemberRepository(dataSource);
    return new JpaMemberRepository(em);
  }
}
