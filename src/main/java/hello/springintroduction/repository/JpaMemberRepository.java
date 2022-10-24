package hello.springintroduction.repository;

import hello.springintroduction.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    // bulid.gradle에서 data-jpa의 라이브러리 받았으니, 스프링부트가 자동으로 EntityManager를 현재 db랑 연결해서 만들어준다.
    private final EntityManager em; //jpa는 entitymanager로 모든게 동작함.
    //우린 이 만들어진 EntityManager를 injection 받으면 된다.
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //persist 영구 저장하다.
        return member;
        //이렇게 하면 jpa가 insert qurry 만들어서 db에 집어넣고, setId도 member에다가 해줌.
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
