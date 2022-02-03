package me.usermanager.application.impl;

import me.usermanager.application.UserService;
import me.usermanager.common.utils;
import me.usermanager.interfaces.user.facade.dto.UserDto;
import me.usermanager.domain.model.user.User;
import me.usermanager.common.response.messages.error.ErrorMessage;
import me.usermanager.common.response.errorClasses.UserException;
import me.usermanager.infrastructure.hibernate.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public void join(UserDto userDto) {
       validateDuplicateUser(userDto);
       userRepository.save(convertUserDtoToUserEntity(userDto));
    }

    public void modifyUser(UserDto userDto) {
        validateExistUser(userDto);
        userRepository.save(convertUserDtoToUserEntity(userDto));
    }

    public UserDto userSearchById(String userId) {
        return convertUserEntityToUserDto(getUserById(userId));
    }

    private void  validateDuplicateUser(UserDto userDto) {
         getUserByIdInDb(existUserId(userDto)).ifPresent(e-> {throw  new UserException(ErrorMessage.DUPLICATE_ID); });
    }



    private void validateExistUser(UserDto userDto){
        getUserById(userDto);
    }

    private String existUserId(UserDto userDto) {
        if (utils.stringIsEmpty(userDto.getUserId())) {
            throw new UserException(ErrorMessage.ID_EMPTY);
        }
        return userDto.getUserId();
    }

    private User getUserById(UserDto userDto){
        return getUserByIdInDb(existUserId(userDto)).orElseThrow(()->new UserException(ErrorMessage.UNKNOWN_USER));
    }

    private User getUserById(String userId){
        return getUserByIdInDb(userId).orElseThrow(()->new UserException(ErrorMessage.UNKNOWN_USER));
    }

    private Optional<User> getUserByIdInDb(String userId) {
        return userRepository.findById(userId);
    }

    private User convertUserDtoToUserEntity(UserDto userDto){
        return new User.UserBuilder(userDto).builder();
    }

    private UserDto convertUserEntityToUserDto(User user){
        return  new UserDto.UserEntryToDtoBuilder(user).builder();
    }


}
