package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userDaoImpl")
    private UserDao userDao;

    @Autowired
    @Qualifier("roleDaoImpl")
    private RoleDao roleDao;

    @Transactional
    @Override
    public void add(User user) {
        user.setRole(setUser(user));
        userDao.add(user);
    }

    @Transactional
    @Override
    public User findUserByName(String name) {
        return userDao.findUserByName(name);
    }

    @Transactional
    @Override
    public User findUserById(Long id) {
        return userDao.findUserById(id);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        user.setRole(setUser(user));
        userDao.updateUser(user);
    }

    @Transactional
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional
    @Override
    public boolean checkUser(User user) {
        return userDao.checkUser(user);
    }

    List<Role> setUser (User user) {
        List<Role> list = new ArrayList<>();
        for (int i = 0; i < user.getRole().size(); i++) {
            list.add(roleDao.findByName(user.getRole().get(i).getName()));
        }
        return list;
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

//    @Override
//    @Transactional(readOnly = true)
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        User user = userDao.findUserByName(s);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        return user;
//    }
}
