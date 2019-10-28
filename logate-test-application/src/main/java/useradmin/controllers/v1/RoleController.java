package useradmin.controllers.v1;

import useradmin.api.v1.model.RoleDTO;
import useradmin.api.v1.model.RoleListDTO;
import useradmin.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(RoleController.BASE_URL)
public class RoleController {

    public static final String BASE_URL = "/api/v1/roles";

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RoleListDTO getAllRoles(){
        return new RoleListDTO(roleService.getAllRoles());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleDTO getRoleById(@PathVariable Long id){
        return roleService.getRoleDTOById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDTO createNewRole(@RequestBody RoleDTO roleDTO){
        return roleService.createNewRole(roleDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public RoleDTO updateRole(@PathVariable Long id, @RequestBody RoleDTO roleDTO){
        return roleService.saveRoleByDTO(id, roleDTO);
    }

    // delete Role uklonjen zato sto u administraciji rola ne bi trebalo da postoji klasicno brisanje podataka
    // za potrebe aktivne / neaktivne Role-a dodato u modelu IsActive
    //@DeleteMapping({"/{id}"})
    //@ResponseStatus(HttpStatus.OK)
    //public void deleteRole(@PathVariable Long id){
    //    roleService.deleteRoleById(id);
    //}
}
