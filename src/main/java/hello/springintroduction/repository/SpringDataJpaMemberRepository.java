package hello.springintroduction.repository;

import hello.springintroduction.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    //JPQL select m from Member m where m.name = ?
    Optional<Member> findByName(String name);
}
