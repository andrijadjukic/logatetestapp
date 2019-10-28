package useradmin.controllers.v1;

import useradmin.api.v1.model.RoleListDTO;
import useradmin.api.v1.model.UserDTO;
import useradmin.api.v1.model.UserListDTO;
import useradmin.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

    public static final String BASE_URL = "/api/v1/users";

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserListDTO getListOfUsers(){

        return new UserListDTO(userService.getAllUsers());
    }

    @GetMapping("/{id}/roles")
    @ResponseStatus(HttpStatus.OK)
    public RoleListDTO getListOfAllUserRoles(@PathVariable Long id){
        return new RoleListDTO(userService.getAllUserRoles(id));
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserById(@PathVariable Long id){
        return userService.getUserDTOById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createNewUser(@RequestBody UserDTO userDTO){
        return userService.createNewUser(userDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO){
        return userService.saveUserByDTO(id, userDTO);
    }

    // delete User uklonjen zato sto u administraciji korisnika ne bi trebalo da postoji klasicno brisanje podataka
    // za potrebe aktivnog / neaktivnog User-a dodato u modelu IsActive
    //@DeleteMapping({"/{id}"})
    //@ResponseStatus(HttpStatus.OK)
    //public void deleteUser(@PathVariable Long id){
    //   userService.deleteUserById(id);
    //}

    @PostMapping({"/ref/{userID}/{roleID}"})
    @ResponseStatus(HttpStatus.CREATED)
    public String addUserRoleRef(@PathVariable Long userID, @PathVariable Long roleID){
         return userService.addUserRoleRef(userID, roleID);
    }

    @DeleteMapping({"/ref/{userID}/{roleID}"})
    @ResponseStatus(HttpStatus.CREATED)
    public String removeUserRoleRef(@PathVariable Long userID, @PathVariable Long roleID){
        return userService.removeUserRoleRef(userID, roleID);
    }

}
