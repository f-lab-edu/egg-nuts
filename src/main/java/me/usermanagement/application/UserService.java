package me.usermanagement.application;

import me.usermanagement.interfaces.user.facade.dto.UserDto;

public interface UserService {
    void join(UserDto userDto);
    void modifyUser(UserDto userDto);
    UserDto userSearchById(String userId);
}
