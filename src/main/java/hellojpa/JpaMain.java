package hellojpa;

import hellojpa.extend.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");// input persistence unit name

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Parent parent = new Parent();
            parent.setName("parent");
            parent.setHomeAddress(new Address("city", "street", "zipcode"));

            parent.getFavoriteFoods().add("치킨");
            parent.getFavoriteFoods().add("피자");

            parent.getAddressHistory().add(new Address("oldct", "oldst", "oldzc"));

            em.persist(parent);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}
