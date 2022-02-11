package me.usermanagement.domain.model.user;


import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class UserInfo {
    private final String userId;
    private final String userName;
    private final User.Gender gender;
    private final User.UserStatus userStatus;
    private final ZonedDateTime createAt;
    private final ZonedDateTime updateAt;

    public UserInfo(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.gender = user.getGender();
        this.userStatus = user.getUserStatus();
        this.createAt = user.getCreateAt();
        this.updateAt = user.getUpdateAt();
    }
}
