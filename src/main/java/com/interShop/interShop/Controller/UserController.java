package com.interShop.interShop.Controller;

import com.interShop.interShop.Entity.Users;
import com.interShop.interShop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/intershop/users")
class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<Users> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public Users createUser(@RequestBody Users user) {
        return userService.saveUser(user);
    }

    @PutMapping
    public Users updateUser(@RequestBody Users user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
