package io.gatarrays.useservice.service;

import io.gatarrays.useservice.entites.Role;
import io.gatarrays.useservice.entites.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username,String roleName);
    User getUser(String username);
    List<User> getUsers();
}
