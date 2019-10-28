package useradmin.services;

import useradmin.api.v1.model.RoleDTO;
import useradmin.api.v1.model.UserDTO;
import useradmin.domain.User;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    List<RoleDTO> getAllUserRoles(Long id);

    Long getCountOfAllUsers();

    UserDTO getUserDTOById(Long id);

    User getUserById(Long id);

    UserDTO createNewUser(UserDTO userDTO);

    UserDTO saveUserByDTO(Long id, UserDTO userDTO);

    User saveUserWithHistoryData(User user);

    void deleteUserById(Long id);

    String addUserRoleRef(Long userID,Long roleID);

    String removeUserRoleRef(Long userID,Long roleID);
}
