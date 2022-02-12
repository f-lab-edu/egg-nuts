package me.usermanagement.application;

import lombok.RequiredArgsConstructor;
import me.usermanagement.domain.model.user.UserCommand;
import me.usermanagement.domain.model.user.UserInfo;
import me.usermanagement.domain.model.user.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {
    private final UserService userService;

    public UserInfo registerUser(UserCommand userCommand) {
        return userService.registerUser(userCommand);
    }

    public UserInfo modifyUserStatus(String userId, UserCommand userCommand) {
        return userService.modifyUserStatus(userId, userCommand);
    }

    public UserInfo userSearchById(String userId) {
        return userService.userSearchById(userId);
    }
}
