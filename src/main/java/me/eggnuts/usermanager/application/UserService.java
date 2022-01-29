package me.eggnuts.usermanager.application;

import me.eggnuts.usermanager.domain.model.user.UserDto;

public interface UserService {
    public void join(UserDto userDto);
    public void modifyUser(UserDto userDto);
    public UserDto userSearchById(String userId);

}
