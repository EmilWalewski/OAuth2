package security.SecurityConfigurations.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import security.SecurityConfigurations.models.UserRoleMapperId;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_role_mapper")
public class UserRoleMapper {

    @EmbeddedId
    UserRoleMapperId userRoleMapperId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @MapsId(value = "userId")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @MapsId(value = "roleId")
    @JsonIgnore
    private Role role;

    public UserRoleMapper() {
    }

    public UserRoleMapper(User user, Role role) {
        this.user = user;
        this.role = role;
        this.userRoleMapperId = new UserRoleMapperId(user.getId(), role.getId());
    }

    public UserRoleMapperId getUserRoleMapperId() {
        return userRoleMapperId;
    }

    public void setUserRoleMapperId(UserRoleMapperId userRoleMapperId) {
        this.userRoleMapperId = userRoleMapperId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleMapper that = (UserRoleMapper) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, role);
    }
}
