package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.locks.Lock;

@Entity
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    // Period
    @Embedded
    private Period period;

    // 주소
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city",
                    column = @Column(name = "WORK_CITY")),
            @AttributeOverride(name = "street",
            column = @Column(name = "WORK_STREET"))
    })
    private Address address;

    // team에서 정하면 하지 않아도 된다
//    public void changeTeam(Team team) {
//        this.team = team;
//        team.getMembers().add(this); // this는 Member(자기자신)을 가리킨다
//    }
}