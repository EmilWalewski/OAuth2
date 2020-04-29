package security.SecurityConfigurations.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import security.SecurityConfigurations.entities.Role;
import security.SecurityConfigurations.entities.User;
import security.SecurityConfigurations.entities.UserRoleMapper;
import security.SecurityConfigurations.models.RoleModel;
import security.SecurityConfigurations.models.UserModel;
import security.SecurityConfigurations.repositories.RoleRepository;
import security.SecurityConfigurations.repositories.UserRepository;

import java.util.Iterator;
import java.util.List;

@Service
public class UserRoleService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserRoleService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public String addUser(UserModel userModel){

        List<Role> roleList = roleRepository.findAll();

        User user = new User();

        userModel.getRoleIdList().forEach(roleID -> {

            for (Iterator iterator = roleList.iterator(); iterator.hasNext();) {
                Role r = (Role) iterator.next();
                if (r.getId() == roleID)
                    user.getUserRoleMapper().add(new UserRoleMapper(user, r));
            }
        });

        user.setUsername(userModel.getUsername());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));

        userRepository.save(user);

        return "User has been added successfully";
    }

    public void addRoleToUser(User user, Role role) {

        UserRoleMapper userRole = new UserRoleMapper(user, role);
        user.getUserRoleMapper().add(userRole);
        role.getUserRoleMapper().add(userRole);
    }

    public void removeRole(User user, Role role) {

        for (Iterator<UserRoleMapper> iterator = user.getUserRoleMapper().iterator(); iterator.hasNext(); ) {

            UserRoleMapper userRole = iterator.next();

            if (userRole.getUser().equals(user) && userRole.getRole().equals(role)) {

                iterator.remove();
                userRole.getRole().getUserRoleMapper().remove(userRole);
                userRole.setUser(null);
                userRole.setRole(null);
            }
        }
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public String addRole(RoleModel roleModel){

        roleRepository.save(new Role(roleModel));

        return "Role has been added successfully";
    }

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }

}
