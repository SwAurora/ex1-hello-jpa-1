package hellojpa;

import javax.persistence.*;

@Entity
public class Locker2 {

    @Id
    @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;

    private String name;

    @OneToOne(mappedBy = "locker")
    private Member2 member;
}
