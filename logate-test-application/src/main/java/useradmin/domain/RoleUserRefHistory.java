package useradmin.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class RoleUserRefHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long roleUserRefid;
    private Long userid;
    private Long roleid;
    private boolean IsRemoved;
    private Long audit_user_id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date audit_timestamp;

    public RoleUserRefHistory(RoleUserRef roleUserRef) {
        roleUserRefid = roleUserRef.getId();
        userid = roleUserRef.getUserid();
        roleid = roleUserRef.getRoleid();
        IsRemoved = roleUserRef.isIsRemoved();
        audit_user_id = roleUserRef.getAudit_user_id();
        audit_timestamp = roleUserRef.getAudit_timestamp();
    }
}
