package useradmin.services;

import useradmin.domain.RoleUserRef;

public interface RoleUserRefService {

    RoleUserRef getUserRoleRefByUserIdAndRoleID(Long userId, Long roleId);

    String addUserRoleRefWithHistoryData(Long userId, Long roleId);

    String removeUserRoleRefWithHistoryData(Long userId, Long roleId);

    Long getCountOfAllUserRoleRef();
}
