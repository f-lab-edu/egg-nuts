package me.usermanagement.infrastructure;

import lombok.RequiredArgsConstructor;
import me.usermanagement.domain.model.user.User;
import me.usermanagement.domain.model.user.UserStore;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserStoreImpl implements UserStore {
    private final UserRepository userRepository;

    @Override
    public User store(User user) {
        return userRepository.save(user);
    }
}
