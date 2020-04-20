package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    List<User> listUsers();
    User findUserByName(String name);
    void updateUser(User user);
    boolean checkUser(User user);
    void deleteUser(Long id);
    User findUserById(Long id);
}
