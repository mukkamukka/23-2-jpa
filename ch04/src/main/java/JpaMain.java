import domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        // 엔티티매니저팩토리 생성
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        // 엔티티매니저 생성
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // 트랜잭션 획득
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
//            logic(entityManager);
            save(entityManager);
            entityTransaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }

    public static void save(EntityManager em) {
        Member member1 = new Member();
        member1.setName("사용자1");
        member1.setCity("서울");
        member1.setStreet("강남구");
        member1.setZipcode("000000");
        em.persist(member1);

        Member member2 = new Member();
        member2.setName("사용자2");
        member2.setCity("부산");
        member2.setStreet("해운대구");
        member2.setZipcode("111111");
        em.persist(member2);

        Category parent = new Category();
        parent.setName("휴대폰");
        em.persist(parent);

        Category cate1 = new Category();
        cate1.setName("SKT");
        em.persist(cate1);
        Category cate2 = new Category();
        cate2.setName("KT");
        em.persist(cate2);
        Category cate3 = new Category();
        cate3.setName("LGU+");
        em.persist(cate3);

        parent.addChildCategory(cate1);
        parent.addChildCategory(cate2);
        parent.addChildCategory(cate3);

        Item item1 = new Item();
        item1.setName("상품1");
        item1.setPrice(9000);
        item1.setStockQuantity(10);
        em.persist(item1);
        Item item2 = new Item();
        item2.setName("상품2");
        item2.setPrice(40000);
        item2.setStockQuantity(2);
        em.persist(item2);
        Item item3 = new Item();
        item3.setName("상품3");
        item3.setPrice(3000);
        item3.setStockQuantity(35);
        em.persist(item3);

        cate1.setItems(List.of(item1, item2, item3));
        cate2.setItems(List.of(item2));

        Delivery delivery = new Delivery();
        delivery.setCity("서울");
        delivery.setStreet("강남구");
        delivery.setZipcode("000000");
        delivery.setStatus(DeliveryStatus.Wait);
        em.persist(delivery);

        Order order1 = new Order();
        order1.setMember(member1);
        order1.setDelivery(delivery);
        order1.setOrderDate(new Date());
        order1.setOrderStatus("ORDER");
        em.persist(order1);

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order1);
        orderItem.setItem(item1);
        orderItem.setOrderPrice(9000);
        orderItem.setCount(1);
        em.persist(orderItem);
    }

    private static void logic(EntityManager em) {
//        String id = "id1";
//        Member member = new Member();
//        member.setId(id);
//        member.setUsername("테스트");
//        member.setAge(2);
//
//        // 등록
//        em.persist(member);
//
//        // 수정
//        member.setAge(20);
//
//        Member findMember = em.find(Member.class, id);
//        System.out.println("findMember : "+findMember.getUsername()+" / age : "+ findMember.getAge());
//
//        List<Member> memberList = em.createQuery("select m from Member m", Member.class).getResultList();
//        System.out.println("memberList.size="+ memberList.size());
//
//        // 삭제
//        em.remove(member);
    }

}