package web.dao;

import web.model.Role;
import web.model.User;

import java.util.List;

public interface RoleDao {
    void add(Role role);
    void updateRole(Role role);
    List<Role> listRoles();
    Role findByName(String role);
    Role findRoleByNameUs(String role);

}
