import entitiy.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
//            logic(entityManager);
            testDetached(entityManager);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }

    private static void logic(EntityManager em) {
        String id = "id2";
        Member member = new Member();
        //~비영속

        //등록
        em.persist(member);
        //~~영속

        //수정

        Member findMember = em.find(Member.class, id);

        List<Member> memberList = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("memberList.size : " + memberList.size());
        //삭제
        em.remove(member);
    }

    public static void testDetached(EntityManager em) {
        Member member = new Member();

        //~비영속

        //영속
        em.persist(member);

        //준영속
//        em.detach(member);
        em.clear();
    }
}
