package useradmin.api.v1.mapper;

import org.mapstruct.Mapping;
import useradmin.api.v1.model.RoleDTO;
import useradmin.domain.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO roleToRoleDTO(Role role);

    Role roleDtoToRole(RoleDTO roleDTO);
}
