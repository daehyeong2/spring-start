package todo.todo_server.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import todo.todo_server.domain.Member;

class MemoryMemberRepositoryTest {
  MemoryMemberRepository repository = new MemoryMemberRepository();

  @AfterEach
  public void afterEach() {
    repository.clearStore();
  }

  @Test
  public void save(){
    Member member = new Member();
    member.setName("kwon");

    repository.save(member);

    Member result = repository.findById(member.getId()).get();
    assertThat(member).isEqualTo(result);
  }

  @Test
  public void findByName(){
    Member member1 = new Member();
    member1.setName("kim1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("kim2");
    repository.save(member2);

    Member result = repository.findByName("kim1").get();
    assertThat(member1).isEqualTo(result);
  }

  @Test
  public void findAll(){
    Member member1 = new Member();
    member1.setName("name_1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("name_2");
    repository.save(member2);

    List<Member> result = repository.findAll();
    assertThat(result.size()).isEqualTo(2);
  }
}