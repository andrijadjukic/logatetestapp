package useradmin.api.v1.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import useradmin.api.v1.model.UserDTO;
import useradmin.domain.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-10-28T16:57:44+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.4 (JetBrains s.r.o)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO userToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setPassword( user.getPassword() );
        userDTO.setFirstname( user.getFirstname() );
        userDTO.setLastname( user.getLastname() );
        userDTO.setDescription( user.getDescription() );
        userDTO.setActive( user.isActive() );

        return userDTO;
    }

    @Override
    public User userDtoToUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setUsername( userDTO.getUsername() );
        user.setPassword( userDTO.getPassword() );
        user.setFirstname( userDTO.getFirstname() );
        user.setLastname( userDTO.getLastname() );
        user.setDescription( userDTO.getDescription() );
        user.setActive( userDTO.isActive() );

        return user;
    }
}
