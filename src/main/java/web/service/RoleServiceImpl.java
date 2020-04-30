package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    @Qualifier("roleDaoImpl")
    private RoleDao roleDao;

    @Transactional
    @Override
    public void add(Role role) {
        roleDao.add(role);
    }

    @Transactional
    @Override
    public void updateRole(Role role) {
        roleDao.updateRole(role);
    }

    @Transactional
    @Override
    public List<Role> findByName(String role) {
        return roleDao.findByName(role);
    }

    @Transactional
    @Override
    public List<Role> listRoles() {
        return roleDao.listRoles();
    }
}
