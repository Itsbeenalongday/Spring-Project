package HELLO.hellospring.repository;

import HELLO.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest { // 관례 상, 테스트하고자 하는 클래스명+Test라고 이름 붙임

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 클래스 레벨에서 테스트를 수행하면 함수의 순서가 보장이 안되어, 먼저했던 함수의 결과가 나중에 테스트하는 함수의 결과에 영향을 끼쳐 알 수없는 결과를 초래함
    // 그래서 테스트 하나씩 할때마다 하고나서 데이터 지워주는 작업이 필요하다

    // 테스트를 먼저만들고 그 다음 구현하는 것을 TDD라고 한다.

    @AfterEach // 각 테스트 끝날때마다 callback 호출되어 수행됨됨
    public void afterEach(){
        repository.clearStore();
    }

    @Test // Test annotation을 붙이면 실행 가능해진다.
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }

}
