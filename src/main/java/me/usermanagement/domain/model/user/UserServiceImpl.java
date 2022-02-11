package me.usermanagement.domain.model.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserReader userReader;
    private final UserStore userStore;

    @Override
    @Transactional
    public UserInfo registerUser(UserCommand userCommand) {
        User initPartner = userCommand.toEntity();
        initPartner.initUserGender(userCommand.getGender());
        User user = userStore.store(initPartner);
        return new UserInfo(user);
    }

    @Override
    public UserInfo modifyUserStatus(String userId,UserCommand userCommand) {
        userSearchById(userId);
        User user = userReader.getUserById(userCommand.getUserId());
        user.changeUserStatus(userCommand.getUserStatus());
        userStore.store(user);
        return new UserInfo(user);
    }

    @Override
    public UserInfo userSearchById(String userId) {
        User user = userReader.getUserById(userId);
        return new UserInfo(user);
    }
}
