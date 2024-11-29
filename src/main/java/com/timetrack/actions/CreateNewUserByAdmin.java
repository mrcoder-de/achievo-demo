package com.timetrack.actions;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.timetrack.domain.User;
import com.timetrack.domain.UserService;

@Component
public class CreateNewUserByAdmin {

    @Autowired
    private UserService userService;

    public User execute(User user) {
        return userService.createUser(user);
    }
}