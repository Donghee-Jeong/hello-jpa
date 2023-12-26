package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");// input persistence unit name

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            em.persist(team);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setAge(10);
            member1.setTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setAge(20);
            member2.setTeam(team);
            em.persist(member2);

            String query = "select m.username From Team t join t.members m";
            List<String> results = em.createQuery(query, String.class)
                    .getResultList();

            for (String s : results) {
                System.out.println("s = " + s);
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
