package me.usermanagement.infrastructure;

import lombok.RequiredArgsConstructor;
import me.usermanagement.common.response.errorClasses.UserException;
import me.usermanagement.common.response.messages.error.ErrorMessage;
import me.usermanagement.domain.model.user.User;
import me.usermanagement.domain.model.user.UserReader;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserReaderImpl implements UserReader {
    private final UserRepository userRepository;

    @Override
    public User getUserById(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserException(ErrorMessage.UNKNOWN_USER));
    }
}
