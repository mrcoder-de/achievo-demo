package com.timetrack.actions;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.timetrack.domain.User;
import com.timetrack.domain.UserService;
import java.util.List;

@Component
public class FetchAllUsersWithActiveFilter {

    @Autowired
    private UserService userService;

    public List<User> execute(Boolean activeFilter) {
        return userService.fetchAllUsers(activeFilter);
    }
}