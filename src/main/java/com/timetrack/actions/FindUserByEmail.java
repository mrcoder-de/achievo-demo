package com.timetrack.actions;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.timetrack.domain.User;
import com.timetrack.domain.UserService;
import com.timetrack.domain.UserNotFoundException;

@Component
public class FindUserByEmail {

    @Autowired
    private UserService userService;

    public User execute(String email) throws UserNotFoundException {
        return userService.findUserByEmail(email);
    }
}