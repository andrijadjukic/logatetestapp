package useradmin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import useradmin.api.v1.mapper.RoleMapper;
import useradmin.api.v1.mapper.UserMapper;
import useradmin.api.v1.model.RoleDTO;
import useradmin.api.v1.model.UserDTO;
import useradmin.domain.User;
import useradmin.domain.UserHistory;
import useradmin.repositories.UserHistoryRepository;
import useradmin.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames={"usersLoad"})
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserHistoryRepository userHistoryRepository;
    private final RoleService roleService;
    private final RoleMapper roleMapper;
    private final RoleUserRefService roleUserRefService;
    private final ApplicationContextValues applicationContextValues;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository,
                           UserHistoryRepository userHistoryRepository,
                           RoleService roleService, RoleMapper roleMapper,
                           RoleUserRefService roleUserRefService,
                           ApplicationContextValues applicationContextValues) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.userHistoryRepository = userHistoryRepository;
        this.roleService = roleService;
        this.roleMapper = roleMapper;
        this.roleUserRefService = roleUserRefService;
        this.applicationContextValues = applicationContextValues;
    }

    @Override
    @Cacheable
    public List<UserDTO> getAllUsers() {
        simulateSlowService(); // dodata f-ja da bi se simulirao @Cacheable

        return userRepository
                .findAll()
                .stream()
                .map(user -> {
                   UserDTO userDTO = userMapper.userToUserDTO(user);
                   return userDTO;
                })
                .collect(Collectors.toList());
    }

    private void simulateSlowService() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<RoleDTO> getAllUserRoles(Long id) {
        List<RoleDTO> roleList = roleService.getAllUserRoles(id);

        return roleList;
    }

    @Override
    public Long getCountOfAllUsers() {

        return userRepository.count();
    }

    @Override
    public UserDTO getUserDTOById(Long id) {

        return userRepository.findById(id)
                .map(userMapper::userToUserDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    @CacheEvict(allEntries = true)
    public UserDTO createNewUser(UserDTO userDTO) {

        return saveAndReturnDTO(userMapper.userDtoToUser(userDTO));
    }

    private UserDTO saveAndReturnDTO(User user) {
        User savedUser = saveUserWithHistoryData(user);
        UserDTO returnDto = userMapper.userToUserDTO(savedUser);

        return returnDto;
    }

    @Override
    @CacheEvict(allEntries = true)
    public UserDTO saveUserByDTO(Long id, UserDTO userDTO) {
        User user = userMapper.userDtoToUser(userDTO);
        user.setId(id);

        return saveAndReturnDTO(user);
    }

    @Override
    @CacheEvict(allEntries = true)
    public User saveUserWithHistoryData(User user){
        user.setAudit_user_id(applicationContextValues.getCurrentUserID());
        user.setAudit_timestamp(new Date());
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        userHistoryRepository.save(new UserHistory(user));
        return savedUser;
    }

    @Override
    @CacheEvict(allEntries = true)
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public String addUserRoleRef(Long userID, Long roleID) {
        getUserById(userID); // ako ne postoji UserID vrati gresku
        roleService.getRoleById(roleID); // ako ne postoji RoleID vrati gresku
        roleUserRefService.addUserRoleRefWithHistoryData(userID,roleID); // ako postoje i UserID i RoleID, onda nastavi dalje sa unosom
        return "SUCCESS";
    }

    @Override
    public String removeUserRoleRef(Long userID, Long roleID) {
        roleUserRefService.removeUserRoleRefWithHistoryData(userID,roleID);
        return "SUCCESS";
    }
}
