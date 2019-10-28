package useradmin.bootstrap;

import useradmin.domain.Role;
import useradmin.domain.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import useradmin.services.RoleService;
import useradmin.services.RoleUserRefService;
import useradmin.services.UserService;

/**
 * Andrija Đukić  21.10.2019.
 * Bootstrap klasa služi za inicijalizaciju podataka
 */
@Component
public class Bootstrap implements CommandLineRunner{

    private final UserService userService;
    private final RoleService roleService;
    private final RoleUserRefService roleUserRefService;

    public Bootstrap(UserService userService, RoleService roleService, RoleUserRefService roleUserRefService) {
        this.userService = userService;
        this.roleService = roleService;
        this.roleUserRefService = roleUserRefService;
    }

    @Override
    public void run(String... args) throws Exception {

        loadRolesAndUsers();
    }

    private void loadRolesAndUsers() {
        //roles
        Role admins = new Role();
        admins.setId(1l);
        admins.setName("Admin Power Users");
        admins.setActive(true);
        admins.setDescription("Admins - can add/remove rights and add/remove/update users and roles");

        Role lts = new Role();
        lts.setId(2l);
        lts.setName("LTS Power Users");
        lts.setActive(true);
        lts.setDescription("Application LTS users");

        Role ibis = new Role();
        ibis.setId(3l);
        ibis.setName("IBIS Power Users");
        ibis.setActive(true);
        ibis.setDescription("Application IBIS users");

        roleService.saveRoleWithHistoryData(admins);
        roleService.saveRoleWithHistoryData(lts);
        roleService.saveRoleWithHistoryData(ibis);

        System.out.println("Roles Loaded: " + roleService.getCountOfAllRoles());

        //users
        User user1 = new User();
        user1.setId(1l);
        user1.setUsername("adjukic");
        user1.setPassword("!juve123");
        user1.setFirstname("Andrija");
        user1.setLastname("Djukic");
        user1.setActive(true);
        userService.saveUserWithHistoryData(user1);

        User user2 = new User();
        user2.setId(2l);
        user2.setUsername("apopovic");
        user2.setPassword("jabuke1234");
        user2.setFirstname("Aleksandar");
        user2.setLastname("Popovic");
        user2.setActive(true);
        userService.saveUserWithHistoryData(user2);

        User user3 = new User();
        user3.setId(3l);
        user3.setUsername("mbojovic");
        user3.setPassword("audi200");
        user3.setFirstname("Milena");
        user3.setLastname("Bojovic");
        user3.setActive(true);
        userService.saveUserWithHistoryData(user3);

        System.out.println("Users Loaded: " + userService.getCountOfAllUsers());

        roleUserRefService.addUserRoleRefWithHistoryData(1L,1L);
        roleUserRefService.addUserRoleRefWithHistoryData(1L,2L);
        roleUserRefService.addUserRoleRefWithHistoryData(2L,3L);
        System.out.println("UserRoleRef Loaded: " + roleUserRefService.getCountOfAllUserRoleRef());
    }
}
