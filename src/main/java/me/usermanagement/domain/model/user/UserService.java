package me.usermanagement.domain.model.user;

public interface UserService {
    UserInfo registerUser(UserCommand userCommand);
    UserInfo modifyUserStatus(String userId,UserCommand userCommand);
    UserInfo userSearchById(String userId);
}
