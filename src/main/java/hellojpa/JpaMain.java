package hellojpa;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        // EntityManagerFactor는 서버 시작 시 애플리케이션 전체에 하나만 생성됨
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); // 트랜잭션 시작
        try {
            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);

//            Query query1 = em.createQuery("select m.username, m.age from Member m");

            List<MemberDTO> result = em.createQuery("select new hellojpa.MemberDTO(m.username, m.age) from Member m", MemberDTO.class).getResultList();

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
