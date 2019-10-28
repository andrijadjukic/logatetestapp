package useradmin.services;

import useradmin.api.v1.mapper.RoleMapper;
import useradmin.api.v1.model.RoleDTO;
import useradmin.domain.Role;
import useradmin.domain.RoleHistory;
import useradmin.repositories.RoleHistoryRepository;
import useradmin.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;
    private final RoleHistoryRepository roleHistoryRepository;
    private final ApplicationContextValues applicationContextValues;

    public RoleServiceImpl(RoleMapper roleMapper, RoleRepository roleRepository, RoleHistoryRepository roleHistoryRepository, ApplicationContextValues applicationContextValues) {
        this.roleMapper = roleMapper;
        this.roleRepository = roleRepository;
        this.roleHistoryRepository = roleHistoryRepository;
        this.applicationContextValues = applicationContextValues;
    }

    @Override
    public List<RoleDTO> getAllRoles() {

        return roleRepository.findAll()
                .stream()
                .map(roleMapper::roleToRoleDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoleDTO> getAllUserRoles(Long id) {

        return roleRepository.getAllUserRoles(id)
                .stream()
                .map(roleMapper::roleToRoleDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Long getCountOfAllRoles() {

        return roleRepository.count();
    }

    @Override
    public RoleDTO getRoleDTOById(Long id) {

        return roleRepository.findById(id)
                .map(roleMapper::roleToRoleDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Role getRoleById(Long id) {

        return roleRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public RoleDTO createNewRole(RoleDTO roleDTO) {

        return saveAndReturnDTO(roleMapper.roleDtoToRole(roleDTO));
    }

    private RoleDTO saveAndReturnDTO(Role role) {
        Role savedRole = saveRoleWithHistoryData(role);

        RoleDTO returnDto = roleMapper.roleToRoleDTO(savedRole);

        return returnDto;
    }

    @Override
    public RoleDTO saveRoleByDTO(Long id, RoleDTO roleDTO) {
        Role role = roleMapper.roleDtoToRole(roleDTO);
        role.setId(id);

        return saveAndReturnDTO(role);
    }

    @Override
    public Role saveRoleWithHistoryData(Role role){
        role.setAudit_user_id(applicationContextValues.getCurrentUserID());
        role.setAudit_timestamp(new Date());
        Role savedRole = roleRepository.save(role);
        roleHistoryRepository.save(new RoleHistory(role));
        return savedRole;
    }

    @Override
    public void deleteRoleById(Long id) {

        roleRepository.deleteById(id);
    }
}
