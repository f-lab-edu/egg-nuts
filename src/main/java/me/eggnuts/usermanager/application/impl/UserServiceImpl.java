package me.eggnuts.usermanager.application.impl;

import me.eggnuts.usermanager.application.UserService;
import me.eggnuts.usermanager.interfaces.cache.UserCache;
import me.eggnuts.usermanager.interfaces.cache.CacheCommand;
import me.eggnuts.usermanager.domain.model.user.UserDto;
import me.eggnuts.usermanager.domain.model.user.User;
import me.eggnuts.usermanager.interfaces.message.ErrorMessage;
import me.eggnuts.usermanager.interfaces.error.UserException;
import me.eggnuts.usermanager.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.BiPredicate;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserCache userCache;

    public void join(UserDto userDto) {
        validateDuplicateUser(userDto.getUserId()).ifPresent(e -> {
            throw new UserException(ErrorMessage.DUPLICATE_ID);
        });
        User userEntity = new User.UserBuilder(userDto).builder();
        userEntity = userRepository.save(userEntity);

        userCache(userDto.getUserId(), new UserDto.UserEntryToDtoBuilder(userEntity).builder());
    }

    public void modifyUser(UserDto userDto) {
        validateDuplicateUser(userDto.getUserId()).orElseGet(() -> {
            throw new UserException(ErrorMessage.UNKNOWN_USER);
        });

        User userEntity = new User.UserBuilder(userDto).builder();
        userEntity = userRepository.save(userEntity);
        removeUserCache(userDto.getUserId());
        userCache(userDto.getUserId(), new UserDto.UserEntryToDtoBuilder(userEntity).builder());
    }


    public UserDto userSearchById(String userId) {
        Optional<UserDto> getUserByIdInCache = validateDuplicateUser(userId);
        getUserByIdInCache.orElseGet(() -> {
            throw new UserException(ErrorMessage.UNKNOWN_USER);
        });

        return getUser(userId);
    }


    private UserDto getUser(String userId) {
        Optional<UserDto> getUserByIdInCache = validateDuplicateUser(userId);
        getUserByIdInCache.orElseGet(() -> {
            throw new UserException(ErrorMessage.UNKNOWN_USER);
        });

        return getUserByIdInCache.get();
    }

    private Optional<UserDto> validateDuplicateUser(String userId) {
        if (stringIsEmpty(userId)) {
            throw new UserException(ErrorMessage.ID_EMPTY);
        } else {
            Optional<UserDto> getUserByIdInCache = userCache.computed(userId, null, CacheCommand.GET);
            getUserByIdInCache = Optional.ofNullable(getUserByIdInCache.orElseGet(() -> {
                Optional<User> getUserByIdInDb = getUserByIdInDb(userId);
                if (getUserByIdInDb.isPresent()) {
                    return new UserDto.UserEntryToDtoBuilder(getUserByIdInDb.get()).builder();
                } else {
                    return null;
                }
            }));

            return getUserByIdInCache;
        }
    }

    private void userCache(String userId, UserDto userDto) {
        userCache.computed(userId, Optional.ofNullable(userDto), CacheCommand.CACHE);
    }

    private void removeUserCache(String userId) {
        userCache.computed(userId, null, CacheCommand.DELETE);
    }

    private boolean stringIsEmpty(String target) {
        BiPredicate<String, String> isEmpty = String::equals;

        return target == null || isEmpty.test(target, "");
    }

    private Optional<User> getUserByIdInDb(String userId) {
        return userRepository.findById(userId);
    }

}
