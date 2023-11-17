import domain.*;

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
//            testSave(entityManager);
//            updateRelation(entityManager);
//            queryJoin(entityManager);
//            deleteRelation(entityManager);
//            biDirection(entityManager);
//            biSave(entityManager);
//            biSaveNonOwner(entityManager);

            Member member1 = new Member("m1", "회원1");
            entityManager.persist(member1);
            Team team1 = new Team("t1", "팀1");
            team1.getMembers().add(member1);
            entityManager.persist(team1);

            Locker locker1 = new Locker(1L, "L1");
            entityManager.persist(locker1);

//            member1.setLocker(locker1);
            locker1.setMember(member1);
            entityManager.flush();
            entityManager.clear();

            Locker findLocker = entityManager.find(Locker.class, 1L);
            Member findMember = findLocker.getMember();
            System.out.println("findMember name = " + findMember.getUsername());

            Product product1 = new Product("p1", "상품1");
            entityManager.persist(product1);

            Member member2 = new Member("m2", "회원2");
//            member2.getProducts().add(product1);
            entityManager.persist(member2);

            entityManager.flush();
            entityManager.clear();

            Member member = entityManager.find(Member.class, "m2");
//            List<Product> products = member.getProducts();
//            for (Product product : products) {
//                System.out.println("product name = " + product.getName());
//            }

            save(entityManager);
            find(entityManager);

            entityTransaction.commit();
        } catch (Exception e) {
            System.out.println("error = " + e);
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

    public static void save(EntityManager em) {
        Member member1 = new Member();
        member1.setId("member1");
        member1.setUsername("회원1");
        em.persist(member1);

        Product product1 = new Product();
        product1.setId("product1");
        product1.setName("상품1");
        em.persist(product1);

//        MemberProduct memberProduct = new MemberProduct();
//        memberProduct.setMember(member1);
//        memberProduct.setProduct(product1);
//        memberProduct.setOrderAmount(2);
//        em.persist(memberProduct);

        Order order = new Order();
        order.setMember(member1);
        order.setProduct(product1);
        order.setOrderAmount(2);
        em.persist(order);
    }

    public static void find(EntityManager em) {
        //기본 키값
//        MemberProductId memberProductId = new MemberProductId();
//        memberProductId.setMember("member1");
//        memberProductId.setProduct("product1");

//        MemberProduct memberProduct = em.find(MemberProduct.class, memberProductId);
//        Member member = memberProduct.getMember();
//        Product product = memberProduct.getProduct();
//

        Order order = em.find(Order.class, 1L);
        Member member = order.getMember();
        Product product = order.getProduct();

        System.out.println("member = " + member.getUsername());
        System.out.println("product = " + product.getName());
        System.out.println("orderAmount = " + order.getOrderAmount());

    }

    public static void testSave(EntityManager em) {
        Team team1 = new Team("t1", "팀1");
        em.persist(team1);

        Member member1 = new Member("m1", "회원1");
//        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member("m2", "회원2");
//        member2.setTeam(team1);
        em.persist(member2);

        Member findMember = em.find(Member.class, "m1");
//        Team findTeam = findMember.getTeam();
//        System.out.println(findMember.getUsername() + "의 팀 :" + findTeam.getName());
    }

    public static void queryJoin(EntityManager em) {
//        String jpql = "select m from Member m join m.team t where t.name = :teamName";

//        List<Member> members = em.createQuery(jpql, Member.class).setParameter("teamName", "팀1").getResultList();
//        for (Member member : members) {
//            System.out.println("[query] member.username=" + member.getUsername());
//        }
    }

    public static void updateRelation(EntityManager em) {
        Team team2 = new Team("t2", "팀2");
        em.persist(team2);

        Member member = em.find(Member.class, "m1");
//        member.setTeam(team2);
    }

    public static void deleteRelation(EntityManager em) {
        Member member2 = em.find(Member.class, "m2");
//        member2.setTeam(null);
        Team team2 = em.find(Team.class, "t2");
        em.remove(team2);
    }

    public static void biDirection(EntityManager em){
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");
//        member1.setTeam(team1);
        em.persist(member1);

        Team findTeam = em.find(Team.class, team1.getId());
        List<Member> members = findTeam.getMembers();
        System.out.println("members.size() = " + members.size());
    }

    public static void biSave(EntityManager em) {
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");
//        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
//        member1.setTeam(team1);
        em.persist(member2);
    }

    public static void biSaveNonOwner(EntityManager em) {
        Member member1 = new Member("member1", "회원1");
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        em.persist(member2);

        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        team1.getMembers().add(member1);
        team1.getMembers().add(member2);
    }

    public static void originalJava() {
        Member member1 = new Member("member1", "회원1");
        Member member2 = new Member("member2", "회원2");
        Team team1 = new Team("team1", "팀1");

//        member1.setTeam(team1);
//        member2.setTeam(team1);

        List<Member> members = team1.getMembers();
        System.out.println(members.size());
    }
}
