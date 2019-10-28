package useradmin.api.v1.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import useradmin.api.v1.model.RoleDTO;
import useradmin.domain.Role;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-10-28T16:57:44+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.4 (JetBrains s.r.o)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDTO roleToRoleDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId( role.getId() );
        roleDTO.setName( role.getName() );
        roleDTO.setDescription( role.getDescription() );
        roleDTO.setActive( role.isActive() );

        return roleDTO;
    }

    @Override
    public Role roleDtoToRole(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleDTO.getId() );
        role.setName( roleDTO.getName() );
        role.setDescription( roleDTO.getDescription() );
        role.setActive( roleDTO.isActive() );

        return role;
    }
}
