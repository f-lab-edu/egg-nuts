package me.usermanager.application;

import me.usermanager.interfaces.user.facade.dto.UserDto;

public interface UserService {
    void join(UserDto userDto);
    void modifyUser(UserDto userDto);
    UserDto userSearchById(String userId);
}
