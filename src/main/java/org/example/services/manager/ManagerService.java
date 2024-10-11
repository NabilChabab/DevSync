package org.example.services.manager;

import jakarta.servlet.http.HttpServlet;
import org.example.exceptions.UserNotFoundException;
import org.example.models.User;
import org.example.models.enums.UserRole;
import org.example.repository.interfaces.UserRepository;
import org.example.validation.UserValidator;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;


public class ManagerService{

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    public ManagerService(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    public void saveUser(String username, String email, String password, String role, String filName) {
        userValidator.validateUsername(username);
        userValidator.validateEmail(email);
        userValidator.validatePassword(password);
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(UserRole.valueOf(role));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setProfile(filName);

        userRepository.save(user);
    }

    public void updateUser(Long id, String username, String email) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }

        userValidator.validateUsername(username);
        userValidator.validateEmail(email);

        user.setUsername(username);
        user.setEmail(email);
        userRepository.update(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        userRepository.delete(user.getId());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getLastUsers() {
        return userRepository.findLastFoor();
    }


}
