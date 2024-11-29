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
        return userRepository.save(user);
    }
}