package domain;

import javax.persistence.*;

@Entity
public class Locker {

    @Id
    @Column(name = "LOCKER_ID")
    private Long id;

    private String name;

//    @OneToOne(mappedBy = "locker") //Member.locker가 주인이다 양방향일때 mapppedBy 속성을 씀
    @OneToOne
    @JoinColumn(name ="MEMBER_ID")
    private Member member;

    public Locker(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Locker() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
