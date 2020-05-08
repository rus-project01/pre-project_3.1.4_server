package web.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.List;

@RestController
public class UsersRestController {

    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private RoleService roleServiceImpl;

    @PostMapping(value = "/admin/add")
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        userServiceImpl.add(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/admin/getListUsers")
    public List<User> listUsers() {
        return userServiceImpl.listUsers();
    }

    @PostMapping(value = "/admin/editInUsers")
    public ResponseEntity<Void> editUser(@RequestBody User user) {
        userServiceImpl.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/admin/userDelete/{id}")
    public ResponseEntity<Void> delUser(@PathVariable Long id)  {
        userServiceImpl.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/admin/userFind")
    public User findUser(@RequestBody User user)  {
        return userServiceImpl.findUserById(user.getId());
    }

    @GetMapping(value = "/admin/userFindByName/{name}")
    public User findUserk(@PathVariable String name)  {
        return userServiceImpl.findUserByName(name);
    }

    @GetMapping(value = "/admin/roleFindByName/{name}")
    public Role findRole(@PathVariable String name)  {
        return roleServiceImpl.findRoleByNameUs(name);
    }

    @PostMapping(value = "/admin/userFindToUs/")
    public User findRole(@RequestBody User user)  {
        return userServiceImpl.findUserByName(user.getName());
    }

}
