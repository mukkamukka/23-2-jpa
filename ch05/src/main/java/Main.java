import domain.Member;
import domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            //실행로직
            testSave(entityManager);
            updateRelation(entityManager);
            queryJoin(entityManager);
            deleteRelation(entityManager);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();

//        domain.Member member1 = new domain.Member("member1", "회원1");
//        domain.Member member2 = new domain.Member("member2", "회원2");
//
//        domain.Team team1 = new domain.Team("team1", "팀1");
//
//        member1.setTeam(team1);
//        member2.setTeam(team1);
//
//        domain.Team findTeam = member1.getTeam();
//        System.out.println("회원 1은 " + findTeam.getName()+"에 속해있다.");
    }

    public static void testSave(EntityManager em) {
        Team team1 = new Team("t1", "팀1");
        em.persist(team1);

        Member member1 = new Member("m1", "회원1");
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member("m2", "회원2");
        member2.setTeam(team1);
        em.persist(member2);

        Member findMember = em.find(Member.class, "m1");
        Team findTeam = findMember.getTeam();
        System.out.println(findMember.getUsername() + "의 팀 :" + findTeam.getName());
    }

    public static void queryJoin(EntityManager em) {
        String jpql = "select m from Member m join m.team t where t.name = :teamName";

        List<Member> members = em.createQuery(jpql, Member.class).setParameter("teamName", "팀1").getResultList();
        for (Member member : members) {
            System.out.println("[query] member.username=" + member.getUsername());
        }
    }

    public static void updateRelation(EntityManager em) {
        Team team2 = new Team("t2", "팀2");
        em.persist(team2);

        Member member = em.find(Member.class, "m1");
        member.setTeam(team2);
    }

    public static void deleteRelation(EntityManager em) {
        Member member2 = em.find(Member.class, "m2");
        member2.setTeam(null);
        Team team2 = em.find(Team.class, "t2");
        em.remove(team2);
    }
}
