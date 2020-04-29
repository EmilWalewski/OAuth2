package security.SecurityConfigurations.endPoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import security.SecurityConfigurations.entities.Role;
import security.SecurityConfigurations.entities.User;
import security.SecurityConfigurations.models.RoleModel;
import security.SecurityConfigurations.models.UserModel;
import security.SecurityConfigurations.services.UserRoleService;

import java.util.List;

@RestController
@RequestMapping("/op")
public class ServiceEndPoints {

    @Autowired
    @Qualifier("twbolek")
    private UserRoleService userRoleService;

    @PostMapping(value = "/addUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUser(@RequestBody UserModel userModel){

        return ResponseEntity.ok(userRoleService.addUser(userModel));
    }

    @GetMapping(value = "/getUsers")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<User>> getUsers(){

        return ResponseEntity.ok(userRoleService.getUsers());
    }

    @PostMapping(value = "/addRole", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addRole(@RequestBody RoleModel roleModel){

        return ResponseEntity.ok(userRoleService.addRole(roleModel));
    }

    @GetMapping(value = "/getRoles")
    public ResponseEntity<List<Role>> getRoles(){

        return ResponseEntity.ok(userRoleService.getRoles());
    }
}
