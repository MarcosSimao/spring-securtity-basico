package io.gatarrays.useservice.controller;

import io.gatarrays.useservice.entites.Role;
import io.gatarrays.useservice.entites.RoleToUserForm;
import io.gatarrays.useservice.entites.User;
import io.gatarrays.useservice.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());

    }
    @PostMapping("/users/save")
    public ResponseEntity<User> getUsers(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));

    }
    @PostMapping("/role/save")
    public ResponseEntity<Role> getUsers(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));

    }
    @PostMapping("/role/addtouser")
    public ResponseEntity<?> getUsers(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getUsername(), form.getRolename());
        return ResponseEntity.ok().build();

    }
}
