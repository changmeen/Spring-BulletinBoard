package RCM.CRUDTest.repository;

import RCM.CRUDTest.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
//    주로 사용하는 메서드들을 알아보자.

//    findAll() : 모든 엔티티 조회
//    findById(Id id) : 아이디가 동일한 엔티티 조회
//    save(T entity) : 새로운 엔티티 저장, 이미 있는 엔티티는 병합
//    delete(T entity) : 엔티티 삭제

//    등등 여러 메소드를 제공한다.
}
