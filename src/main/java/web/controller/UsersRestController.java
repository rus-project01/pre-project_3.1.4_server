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
    private RoleService roleServiceImpl;

    @PostMapping(value = "/admin/addUser")
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        user.setRole(roleServiceImpl.findByName(user.getRole().get(0).getName()));
        userServiceImpl.add(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/admin/editUser")
    public ResponseEntity<Void> editUser(@RequestBody User user) {
        user.setRole(roleServiceImpl.findByName(user.getRole().get(0).getName()));
        userServiceImpl.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/admin/deleted")
    public ResponseEntity<Void> delUser(@RequestBody User user)  {
        userServiceImpl.deleteUser(user.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
