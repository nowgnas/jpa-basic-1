package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // EntityManagerFactor는 서버 시작 시 애플리케이션 전체에 하나만 생성됨
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); // 트랜잭션 시작
        try {
            // 실제 동작코드 작성

            // 준영속
            Member member = em.find(Member.class, 101L);

            em.detach(member); // jpa에서 관리하지 않음 영속성 컨텍스트에서 관리하지 않음
            em.clear(); // 영속성 컨텍스트 초기화 1차 캐시 모두 사라짐
//            em.close(); // 영속성 컨텍스트 종료 트랜잭션 끝난 후 닫아줌



            // 영속
//            Member member = new Member(200L, "member");
//            em.persist(member);
//
//            em.flush(); // 강제 호출: 즉시 sql 쿼리가 실행된다
//
//            System.out.println("-----------");

            // 변경 감지
//            Member member = em.find(Member.class, 101L);
//            member.setName("zzz");
            // 변경 시 persist를 사용하면 안됨


            // 쓰기 지연
//            Member member = new Member(150L, "a");
//            Member member1 = new Member(160L, "b");
//
//            em.persist(member1);
//            em.persist(member);
//            System.out.println("persist");

            // -------------------------------------------
            // insert
            // 비영속
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("helloC");
//
//            // 영속 상태: em에 의해 관리가 된다
//            em.persist(member); // db에 저장되지 않는 상태
//            // 1차 캐시에 저장된다
//
//            Member findMember = em.find(Member.class, 101L);
//            System.out.println("find member " + findMember.getName());
            // -------------------------------------------

            // find 1차 캐시
//            Member member = em.find(Member.class, 1L);
//            Member member1 = em.find(Member.class, 1L);
//
//            System.out.println(member1 == member);

            // select
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("find " + findMember.getName());

//            List<Member> resultList = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(1) // offset
//                    .setMaxResults(5) // limit
//                    .getResultList();
//            for (Member m : resultList
//            ) {
//                System.out.println("member.name " + m.getName());
//
//            }

            // delete
//            em.remove(findMember); // 찾은 값을 제거

            // update
//            findMember.setName("hellojpa");

            tx.commit(); // 트랜잭션 커밋

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();

        }

        // -------------
        emf.close();
    }
}
