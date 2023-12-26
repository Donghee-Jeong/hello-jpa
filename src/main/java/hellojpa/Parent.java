package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Parent {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    private Period period;
    @Embedded
    private Address homeAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city",
            column = @Column(name = "work_city")),
            @AttributeOverride(name = "street",
            column = @Column(name = "work_street")),
            @AttributeOverride(name = "zipcode",
            column = @Column(name = "work_zipcode"))
    })
    private Address workAddress;


}
