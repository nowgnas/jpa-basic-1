package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
            Member member = new Member();
            member.setUsername("member1");
            member.setAddress(new Address("hjh", "hjh", "oio"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");

            member.getAddressHistory().add(new Address("c1", "st1", "zip1"));
            member.getAddressHistory().add(new Address("c2", "st1", "zip1"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("---------start----------");
            Member findMember = em.find(Member.class, member.getId());

            // 지연 로딩
            List<Address> addressHistory = findMember.getAddressHistory();
            for (Address address : addressHistory) {
                System.out.println("addr : " + address.getCity());
            }

            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            for (String favoriteFood : favoriteFoods) {
                System.out.println("food : " + favoriteFood);
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
