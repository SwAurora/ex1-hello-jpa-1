package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;

public class JpaMain4 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team2 team = new Team2();
            team.setName("teamA");
            em.persist(team);

            Member2 member = new Member2();
            member.setUsername("hello");
            member.changeTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            /*Member2 findMember = em.find(Member2.class, member.getId());
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.username = " + findMember.getUsername());*/

            Member2 findMember = em.getReference(Member2.class, member.getId());
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.username = " + findMember.getUsername());
            System.out.println("findMember.getClass() = " + findMember.getClass());
            System.out.println("findMember.getTeam() = " + findMember.getTeam());
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(findMember));

            Hibernate.initialize(findMember); // 강제 초기화

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void printMember(Member2 member) {
        System.out.println("member = " + member.getUsername());
    }

    private static void printMemberAndTeam(Member2 member) {
        String username = member.getUsername();
        System.out.println("username = " + username);

        Team2 team = member.getTeam();
        System.out.println("team = " + team.getName());
    }
}
