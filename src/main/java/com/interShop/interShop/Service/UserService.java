package com.interShop.interShop.Service;

import com.interShop.interShop.Entity.Users;
import com.interShop.interShop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<Users> getAllUsers() {
        return repository.findAll();
    }

    public Optional<Users> getUserById(Long id) {
        return repository.findById(id);
    }

    public Users saveUser(Users user) {
        return repository.save(user);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
