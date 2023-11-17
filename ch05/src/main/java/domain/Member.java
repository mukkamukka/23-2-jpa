package domain;

import domain.Team;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

@Entity
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    @OneToOne(mappedBy = "member")
//    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

//    @ManyToMany
//    @JoinTable(name = "MEMBER_PRODUCT"
//            , joinColumns = @JoinColumn(name = "MEMBER_ID")
//            , inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
//    private List<Product> products = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name = "TEAM_ID")
//    private Team team;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public Team getTeam() {
//        return team;
//    }
//
//    public void setTeam(Team team) {
//        if (this.team != null) {
//            this.team.getMembers().remove(this);
//        }
//        this.team = team;
//        team.getMembers().add(this);
//    }


    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }


//    public List<MemberProduct> getMemberProducts() {
//        return memberProducts;
//    }
//
//    public void setMemberProducts(List<MemberProduct> memberProducts) {
//        this.memberProducts = memberProducts;
//    }

    public Member(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public Member() {}
}
