package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Member {
    @Id
    private Long id;
    @Column(name = "name") // db에는 name으로 column 이름이 들어간다
    private String username;
    private Integer age;
    @Enumerated(EnumType.STRING) // Enum 타입을 사용하고 싶을 때
    private RoleType roleType;
    @Temporal(TemporalType.TIMESTAMP) // DATE, TIME, TIMESTAMP 3개가 있다
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Lob // varchar를 넘어서는 큰 컨텐츠를 넣고 싶을 때
    private String description;

}
