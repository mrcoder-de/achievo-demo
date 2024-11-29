package com.timetrack.actions;

import org.springframework.stereotype.Component;
import com.timetrack.domain.User;
import java.util.List;

@Component
public class FetchAllUsersWithActiveFilter {

    public List<User> execute(Boolean activeFilter) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}