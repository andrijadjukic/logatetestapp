package useradmin.api.v1.mapper;

import useradmin.api.v1.model.UserDTO;
import useradmin.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(uses = RoleMapper.class)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);

    User userDtoToUser(UserDTO userDTO);
}
