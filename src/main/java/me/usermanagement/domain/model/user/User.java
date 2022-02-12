package me.usermanagement.domain.model.user;


import me.usermanagement.common.response.errorClasses.UserException;
import me.usermanagement.common.response.messages.error.ErrorMessage;
import me.usermanagement.domain.model.CommonEntity;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Arrays;


@Getter
@Entity
@NoArgsConstructor
@Table(
        name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id"})
        }
)
public class User extends CommonEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_name")
    private String userName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Getter
    public enum Gender {
        M("M", "남성"),
        F("F", "여성"),
        UNKNOWN("", "");

        private final String code;
        private final String desc;

        Gender(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public static Gender findEnumByCode(String code) {
            return Arrays.stream(values())
                    .filter(e -> e.code.equals(code))
                    .findAny()
                    .orElse(UNKNOWN);
        }
    }

    @Getter
    public enum UserStatus {
        JOIN("J", "가입"),
        LEAVE("L", "탈퇴"),
        DORMANCY("D", "휴면계정"),
        UNKNOWN("", "");

        private final String code;
        private final String desc;

        UserStatus(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public static UserStatus findEnumByCode(String code) {
            return Arrays.stream(values())
                    .filter(e -> e.code.equals(code))
                    .findAny().orElse(UNKNOWN);
        }
    }

    @Builder
    public User(String userId, String userName, Gender gender) {
        if (StringUtils.isEmpty(userId)) throw new UserException(ErrorMessage.ID_EMPTY);
        if (StringUtils.isEmpty(userName)) throw new UserException(ErrorMessage.NAME_EMPTY);
        this.userId = userId;
        this.userName = userName;
        this.userStatus = UserStatus.JOIN;
        this.gender = gender;
    }

    public void changeUserStatus(String statusCode) {
        UserStatus userStatus = UserStatus.findEnumByCode(statusCode);
        if (userStatus == UserStatus.UNKNOWN) {
            throw new UserException(ErrorMessage.NO_STATUS);
        }
        this.userStatus = userStatus;
    }

    public void initUserGender(String statusCode) {
        Gender gender = Gender.findEnumByCode(statusCode);
        if (userStatus == UserStatus.UNKNOWN) {
            throw new UserException(ErrorMessage.NO_GENDER);
        }
        this.gender = gender;
    }
}
