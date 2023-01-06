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
            Team team = new Team();
            team.setName("TeamA");
//            team.getMembers().add(member); // member를 추가해 줬음
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            // Team에서 연관관계 세팅 함
//            member.changeTeam(team); // 연관관계의 주인에만 값을 추가
            em.persist(member);

            team.addMember(member);

//            em.flush();
//            em.clear();

            // 객체의 메소드로 가능하다 setter에서 설정
//            team.getMembers().add(member); // 꼭 추가해 줘야 한다

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            for (Member m : members) {
                System.out.println("m : " + m.getUsername());
            }


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
