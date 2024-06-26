package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member2 extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    /*@Column(name = "TEAM_ID")
    private Long teamId;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team2 team;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCKER_ID")
    private Locker2 locker;

    /*@ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT")
    private List<Product2> products = new ArrayList<>();*/

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team2 getTeam() {
        return team;
    }

    public void changeTeam(Team2 team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
