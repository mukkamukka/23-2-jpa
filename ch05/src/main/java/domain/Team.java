package domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Team {

    @Id
    @Column(name = "TEAM_ID")
    private String id;

    private String name;

    @JoinColumn(name = "TEAM_ID") //Member 테이블의 TEAM_ID(FK)
    @OneToMany//(mappedBy = "team")
    private List<Member> members = new ArrayList<Member>();

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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Team() {}
}
