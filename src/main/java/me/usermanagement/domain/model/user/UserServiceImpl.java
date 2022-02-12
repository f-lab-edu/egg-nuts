package me.usermanagement.domain.model.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserReader userReader;  // JPA, MyBatis 노관심 ... 10개... 6개 JPA, 4개 MyBatis.. DIP
    private final UserStore userStore;

    @Override
    @Transactional
    public UserInfo registerUser(UserCommand userCommand) {
        User initPartner = userCommand.toEntity();
        User user = userStore.store(initPartner);
        return new UserInfo(user);
    }

    // TODO - lazyInitializationException
    // TODO - @ManyToOne @OneToMany    (fetch = EAGER or Lazy)
    @Override
    @Transactional
    public UserInfo modifyUserStatus(String userId, UserCommand userCommand) {
        // TODO - findBy ... Optional 을 리턴... getBy ... 무조건 있어야 하는, 없으면 Exception
        // TODO - String userId, 는 제거해보기
        User user = userReader.getUserById(userCommand.getUserId());
        user.changeUserStatus(userCommand.getUserStatus());
        userStore.store(user);
        return new UserInfo(user);
    }

    // TODO - Tx 의 propagation
    @Override
    @Transactional(readOnly = true)
    public UserInfo userSearchById(String userId) {
        User user = userReader.getUserById(userId);
        return new UserInfo(user);
    }
}
