package security.SecurityConfigurations.entities;

import security.SecurityConfigurations.models.RoleModel;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_name", length = 20)
    private String roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRoleMapper> userRoleMapper = new LinkedList<>();

    public Role() {
    }

    public Role(RoleModel roleModel) {
        this.roleName = roleModel.getRoleName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<UserRoleMapper> getUserRoleMapper() {
        return userRoleMapper;
    }

    public void setUserRoleMapper(List<UserRoleMapper> userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleName, role.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName);
    }
}
