package me.usermanagement.interfaces.user;


import lombok.*;
import me.usermanagement.common.Builder;
import me.usermanagement.domain.model.user.User;
import me.usermanagement.domain.model.user.UserCommand;
import me.usermanagement.domain.model.user.UserInfo;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.*;
import java.time.ZonedDateTime;

public class UserDto {

    @Getter
    @Setter
    @ToString
    public static class RegisterUser implements Builder<UserCommand> {
        @NotBlank(message = "아이디는 필수값입니다")
        @Length(min = 4, message = "아이디는 최소 4자 이상이여야합니다")
        private String userId;
        @NotBlank(message = "이름은 필수값입니다")
        private String userName;
        @NotBlank(message = "성별은 필수값입니다")
        private String gender;

        public UserCommand builder() {
            return UserCommand.builder()
                    .userId(userId)
                    .userName(userName)
                    .gender(gender)
                    .build();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class modifyUserStatus implements Builder<UserCommand> {
        @NotBlank(message = "아이디는 필수값입니다")
        @Length(min = 4, message = "아이디는 최소 4자 이상이여야합니다")
        private String userId;
        @NotBlank(message = "상태는 필수값입니다")
        private String userStatus;

        @Override
        public UserCommand builder() {
            return UserCommand.builder()
                    .userId(userId)
                    .userStatus(userStatus)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class Response {
        private final String userId;
        private final String userName;
        private final User.Gender gender;
        private final User.UserStatus userStatus;
        private final ZonedDateTime createAt;
        private final ZonedDateTime updateAt;

        public Response(UserInfo userInfo) {
            this.userId = userInfo.getUserId();
            this.userName = userInfo.getUserName();
            this.gender = userInfo.getGender();
            this.userStatus = userInfo.getUserStatus();
            this.createAt = userInfo.getCreateAt();
            this.updateAt = userInfo.getUpdateAt();
        }
    }
}
