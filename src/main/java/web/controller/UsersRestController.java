package web.controller;

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
    private RoleService roleService;

//    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<User> addUser(@RequestBody User user) {
//        ResponseEntity<User> responseEntity = new ResponseEntity<User>(userServiceImpl.add(user), HttpStatus.OK);
//        return null;
//    }

//    @Autowired
//    private RoleService roleServiceImpl;
//

    @GetMapping(value = "/qweqwe")
    public List<User> login() {
        return userServiceImpl.listUsers();
    }

    @GetMapping(value = "/admin/getUsers")
    public ResponseEntity<List<User>> listUsers() {
        return new ResponseEntity<>(userServiceImpl.listUsers(), HttpStatus.OK);
    }

    @PostMapping(value = "/admin/addUser")
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        userServiceImpl.add(user);
        roleService.add(user.getRole().get(0));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/admin/editUser")
    public ResponseEntity<Void> editUser(@RequestBody User user) {
        System.out.println(user.getId());
        userServiceImpl.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/admin/deleted")
    public ResponseEntity<Void> delUser(@RequestBody User user)  {
        userServiceImpl.deleteUser(user.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
