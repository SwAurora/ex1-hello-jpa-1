package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class JpaMain7 {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member3 member = new Member3();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity", "street", "10000"));
            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new Address("old1", "street", "10000"));
            member.getAddressHistory().add(new Address("old2", "street", "10000"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("=============== START ===============");
            Member3 findMember = em.find(Member3.class, member.getId());

            Address a = findMember.getHomeAddress();

            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));

            List<Address> addressHistory = findMember.getAddressHistory();

            addressHistory.remove(new Address("old1", "street", "10000"));
            addressHistory.add(new Address("newCity1", "street", "10000"));

            addressHistory.forEach(address -> {
                System.out.println("address = " + address);
            });

            Set<String> favoriteFoods = findMember.getFavoriteFoods();

            favoriteFoods.remove("치킨");
            favoriteFoods.add("한식");

            favoriteFoods.forEach(food -> {
                System.out.println("food = " + food);
            });

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
