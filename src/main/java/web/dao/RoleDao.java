package web.dao;

import web.model.Role;
import web.model.User;

public interface RoleDao {
    void add(Role role);
    void updateRole(Role role);
}
