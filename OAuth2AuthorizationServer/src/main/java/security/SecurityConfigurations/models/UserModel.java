package security.SecurityConfigurations.models;

import security.SecurityConfigurations.entities.User;

import java.util.List;

public class UserModel {

    private Integer id;

    private String username;

    private String password;

    private List<Integer> roleIdList;

    public UserModel() {
    }

    public UserModel(String username, String password, List<Integer> roleIdList) {
        this.username = username;
        this.password = password;
        this.roleIdList = roleIdList;
    }

    public UserModel(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

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

    public List<Integer> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Integer> roleIdList) {
        this.roleIdList = roleIdList;
    }
}
