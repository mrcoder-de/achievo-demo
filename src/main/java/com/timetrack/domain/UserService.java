package com.timetrack.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
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
}