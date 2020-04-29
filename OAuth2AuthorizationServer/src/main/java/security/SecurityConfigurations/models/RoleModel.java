package security.SecurityConfigurations.models;

import security.SecurityConfigurations.entities.Role;

public class RoleModel {

    private String roleName;

    public RoleModel() {
    }

    public RoleModel(Role role) {
        this.roleName = role.getRoleName();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
