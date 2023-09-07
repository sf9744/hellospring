package Hello.hellospring.repository;
//회원리포지토리 메모리구현체
import Hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;  //0,1,2 키값을 생성해 준다.

    @Override
    public Member save(Member member) {
        member.setId(++sequence);  //아이디 자동 입력, 시스템이 정해준다.
        store.put(member.getId(), member);  //아이디, 멤버저장
        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); //Stream에서 가장 먼저 탐색되는 요소를 리턴한다
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
