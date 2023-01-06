package hellojpa;

import javax.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "USERNAME")
    private String username;

    //    @Column(name = "TEAM_ID")
    //    private Long teamId;
//    @ManyToOne
//    @JoinColumn(name = "TEAM_ID")
//    private Team team;

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


    // team에서 정하면 하지 않아도 된다
//    public void changeTeam(Team team) {
//        this.team = team;
//        team.getMembers().add(this); // this는 Member(자기자신)을 가리킨다
//    }
}