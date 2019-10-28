package useradmin.services;

import useradmin.api.v1.model.RoleDTO;
import useradmin.domain.Role;

import java.util.List;


public interface RoleService {

    List<RoleDTO> getAllRoles();

    Long getCountOfAllRoles();

    RoleDTO getRoleDTOById(Long id);

    Role getRoleById(Long id);

    List<RoleDTO> getAllUserRoles(Long id);

    RoleDTO createNewRole(RoleDTO roleDTO);

    RoleDTO saveRoleByDTO(Long id, RoleDTO roleDTO);

    Role saveRoleWithHistoryData(Role role);

    void deleteRoleById(Long id);
}
