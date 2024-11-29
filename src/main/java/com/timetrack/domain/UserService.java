package com.timetrack.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserService {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Autowired
    private UserRepository userRepository;

    public boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public User createUser(User user) {
        if (!isValidEmail(user.getEmail())) {
            throw new InvalidUserEmailException("Invalid user email address");
        }
        if (!isValidFirstName(user.getFirstName())) {
            throw new InvalidUserFirstNameException("First name cannot be empty");
        }
        if (!isValidLastName(user.getLastName())) {
            throw new InvalidUserLastNameException("Last name cannot be empty");
        }
        if (userExistsWithEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("User with this email already exists");
        }
        return userRepository.save(user);
    }

    private boolean isValidFirstName(String firstName) {
        return firstName != null && !firstName.trim().isEmpty();
    }

    private boolean isValidLastName(String lastName) {
        return lastName != null && !lastName.trim().isEmpty();
    }

    private boolean userExistsWithEmail(String email) {
        return userRepository.existsById(email);
    }

    public List<User> fetchAllUsers(Boolean activeFilter) {
        if (activeFilter == null) {
            return userRepository.findAll();
        }
        return userRepository.findByIsActive(activeFilter);
    }

    public User findUserByEmail(String email) {
        if (!isValidEmail(email)) {
            throw new InvalidUserEmailException("Invalid user email address");
        }
        return userRepository.findById(email)
                .orElseThrow(() -> new UserNotFoundException("User with provided email not found"));
    }

    public User updateUser(User updatedUser) {
        if (!isValidEmail(updatedUser.getEmail())) {
            throw new InvalidUserEmailException("Invalid user email address");
        }
        if (!isValidFirstName(updatedUser.getFirstName())) {
            throw new InvalidUserFirstNameException("First name cannot be empty");
        }
        if (!isValidLastName(updatedUser.getLastName())) {
            throw new InvalidUserLastNameException("Last name cannot be empty");
        }
        
        User existingUser = userRepository.findById(updatedUser.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User with provided email not found"));
        
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setIsActive(updatedUser.getIsActive());
        
        return userRepository.save(existingUser);
    }
}