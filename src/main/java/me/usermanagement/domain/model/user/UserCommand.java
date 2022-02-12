package me.usermanagement.domain.model.user;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UserCommand {
    private final String userId;
    private final String userName;
    private final String gender;
    private final String userStatus;

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .userName(userName)
                .gender(User.Gender.findEnumByCode(gender))
                .build();
    }
}
