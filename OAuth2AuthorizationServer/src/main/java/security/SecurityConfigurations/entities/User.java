package security.SecurityConfigurations.entities;

import security.SecurityConfigurations.models.UserModel;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user_table", uniqueConstraints = @UniqueConstraint(name = "username_unique_constraint", columnNames = "username"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", length = 20)
    private String username;

    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(UserModel userModel) {
        this.username = userModel.getUsername();
        this.password = userModel.getPassword();
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<UserRoleMapper> userRoleMapper = new LinkedList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
