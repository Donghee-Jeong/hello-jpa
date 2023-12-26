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
            Member member = new Member();
            member.setUsername("memberA");
            member.setAge(10);

            em.persist(member);

            Member result = em.createQuery("select m from Member m where m.age > :age", Member.class)
                    .setParameter("age", 9)
                    .getSingleResult();

            System.out.println("member = " + result.getAge());

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
