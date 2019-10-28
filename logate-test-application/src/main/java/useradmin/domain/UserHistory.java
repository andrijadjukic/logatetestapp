package useradmin.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class UserHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userid;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String description;
    private boolean isActive;
    private Long audit_user_id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date audit_timestamp;

    public UserHistory(User user){
        userid = user.getId();
        username = user.getUsername();
        password = user.getPassword();
        firstname = user.getFirstname();
        lastname = user.getLastname();
        description = user.getDescription();
        isActive = user.isActive();
        audit_user_id = user.getAudit_user_id();
        audit_timestamp = user.getAudit_timestamp();
    }

}
