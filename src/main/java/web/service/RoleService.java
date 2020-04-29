package web.service;

import web.model.Role;
import web.model.User;

import java.util.List;

public interface RoleService {
    void add(Role role);
    void updateRole(Role role);
    List<Role> listRoles();
}
