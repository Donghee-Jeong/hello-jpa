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
            Team team = new Team();
            team.setName("팀1");
            em.persist(team);
            
            Member member = new Member();
            member.setCreateBy("me");
            member.setCreateDate(LocalDateTime.now());
            member.setName("홍길동");
            member.setTeam(team);
            em.persist(member);
            
            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            Team findMemberTeam = findMember.getTeam();
            System.out.println("findMemberTeam.getName() = " + findMemberTeam.getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
