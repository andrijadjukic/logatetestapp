package useradmin.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class RoleHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long roleid;
    private String name;
    private String description;
    private boolean isActive;
    private Long audit_user_id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date audit_timestamp;

    public RoleHistory(Role role){
        roleid = role.getId();
        name = role.getName();
        description = role.getDescription();
        isActive = role.isActive();
        audit_user_id = role.getAudit_user_id();
        audit_timestamp = role.getAudit_timestamp();
    }
}
