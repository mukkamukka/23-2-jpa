package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id
    @Column(name = "PRODUCT_ID")
    private String id;
    private String name;

//    @ManyToMany(mappedBy = "products")
//    private List<Member> members = new ArrayList<>();

    public Product(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Member> getMembers() {
//        return members;
//    }
//
//    public void setMembers(List<Member> members) {
//        this.members = members;
//    }

//    public List<MemberProduct> getMemberProducts() {
//        return memberProducts;
//    }
//
//    public void setMemberProducts(List<MemberProduct> memberProducts) {
//        this.memberProducts = memberProducts;
//    }
}
