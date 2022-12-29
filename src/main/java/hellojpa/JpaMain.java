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

            // insert
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("helloB");
//            em.persist(member);

            // select
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("find " + findMember.getName());

            List<Member> resultList = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1) // offset
                    .setMaxResults(5) // limit
                    .getResultList();
            for (Member m : resultList
            ) {
                System.out.println("member.name " + m.getName());

            }

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
