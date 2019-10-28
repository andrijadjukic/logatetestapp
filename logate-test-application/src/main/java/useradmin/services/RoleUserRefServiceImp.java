package useradmin.services;

import org.springframework.stereotype.Service;
import useradmin.domain.RoleUserRef;
import useradmin.domain.RoleUserRefHistory;
import useradmin.repositories.RoleUserRefHistoryRepository;
import useradmin.repositories.RoleUserRefRepository;

import java.util.Date;

@Service
public class RoleUserRefServiceImp implements RoleUserRefService {

    private final RoleUserRefRepository roleUserRefRepository;
    private final RoleUserRefHistoryRepository roleUserRefHistoryRepository;
    private final ApplicationContextValues applicationContextValues;

    public RoleUserRefServiceImp(RoleUserRefRepository roleUserRefRepository, RoleUserRefHistoryRepository roleUserRefHistoryRepository, ApplicationContextValues applicationContextValues) {
        this.roleUserRefRepository = roleUserRefRepository;
        this.roleUserRefHistoryRepository = roleUserRefHistoryRepository;
        this.applicationContextValues = applicationContextValues;
    }

    @Override
    public RoleUserRef getUserRoleRefByUserIdAndRoleID(Long userId, Long roleId) {
        RoleUserRef roleUserRef = new RoleUserRef();
        return roleUserRefRepository.getRoleUserRefByUserIdAndRoleID(userId, roleId)
                .orElse(roleUserRef);
    }

    @Override
    public Long getCountOfAllUserRoleRef() {

        return roleUserRefRepository.count();
    }

    @Override
    public String addUserRoleRefWithHistoryData(Long userId, Long roleId){
        RoleUserRef roleUserRef = getUserRoleRefByUserIdAndRoleID(userId,roleId);
        roleUserRef.setAudit_user_id(applicationContextValues.getCurrentUserID());
        roleUserRef.setAudit_timestamp(new Date());
        roleUserRef.setUserid(userId);
        roleUserRef.setRoleid(roleId);
        roleUserRef.setIsRemoved(false);
        roleUserRefRepository.save(roleUserRef);
        roleUserRefHistoryRepository.save(new RoleUserRefHistory(roleUserRef));
        return "SUCCESS";
    }

    @Override
    public String removeUserRoleRefWithHistoryData(Long userId, Long roleId) {
        RoleUserRef roleUserRef = getUserRoleRefByUserIdAndRoleID(userId,roleId);
        if (roleUserRef.getUserid() != null) {
            roleUserRef.setIsRemoved(true);
            roleUserRef.setAudit_user_id(applicationContextValues.getCurrentUserID());
            roleUserRef.setAudit_timestamp(new Date());
            roleUserRefRepository.save(roleUserRef);
            roleUserRefHistoryRepository.save(new RoleUserRefHistory(roleUserRef));
        }
        return "SUCCESS";
    }

}
