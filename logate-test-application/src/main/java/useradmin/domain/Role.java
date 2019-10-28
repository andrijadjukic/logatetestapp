package useradmin.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;


@Data
@EqualsAndHashCode(exclude = {"users"})
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private boolean isActive;
    private Long audit_user_id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date audit_timestamp;

    /*
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
    */
}
