package com.grayseal.microfictionapi.service;

import com.grayseal.microfictionapi.model.Role;
import com.grayseal.microfictionapi.model.User;
import com.grayseal.microfictionapi.model.UserRegistrationRequest;
import com.grayseal.microfictionapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Long registerUser(UserRegistrationRequest registrationRequest) {
        if (userRepository.findByEmail(registrationRequest.getEmail()) == null) {
            User user = new User();
            user.setEmail(registrationRequest.getEmail());
            user.setRole(Role.ROLE_USER);
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            userRepository.save(user);
            return user.getId();
        }
        return null;
    }

    public boolean registerAdmin(UserRegistrationRequest registrationRequest) {
        if (userRepository.findByEmail(registrationRequest.getEmail()) == null) {
            User admin = new User();
            admin.setEmail(registrationRequest.getEmail());
            admin.setRole(Role.ROLE_ADMIN);
            admin.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            userRepository.save(admin);
            return true;
        }
        return false;
    }

    public boolean deleteUserById(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public User findUserById(Long userId) {
        Optional<User> user = Optional.empty();
        if (userRepository.existsById(userId)) {
            user = userRepository.findById(userId);
        }
        return user.orElse(null);
    }

    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Boolean updateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
