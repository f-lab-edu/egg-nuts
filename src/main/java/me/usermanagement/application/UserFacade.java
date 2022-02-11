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
        UserInfo userInfo = userService.registerUser(userCommand);
        return userInfo;
    }

    public UserInfo modifyUserStatus(String userID,UserCommand userCommand) {
        UserInfo userInfo = userService.modifyUserStatus(userID,userCommand);
        return userInfo;
    }

    public UserInfo userSearchById(String userId) {
        UserInfo userInfo = userService.userSearchById(userId);
        return userInfo;
    }
}
