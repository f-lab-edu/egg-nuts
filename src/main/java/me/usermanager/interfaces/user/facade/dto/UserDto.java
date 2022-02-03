package me.usermanager.interfaces.user.facade.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import me.usermanager.common.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.usermanager.domain.model.user.User;

import java.time.ZonedDateTime;


@NoArgsConstructor
@Getter
@AllArgsConstructor
@ToString
public class UserDto  {
    private String userId;
    private String userName;
    private String gender;
    @JsonProperty(value="status")
    private String userStatus;
    private ZonedDateTime createAt;
    private ZonedDateTime updateAt;

    private UserDto(UserEntryToDtoBuilder userEntryToDtoBuilder){
        this.userId = userEntryToDtoBuilder.userId;
        this.userName = userEntryToDtoBuilder.userName;
        this.gender = userEntryToDtoBuilder.gender;
        this.userStatus = userEntryToDtoBuilder.userStatus;
        this.createAt = userEntryToDtoBuilder.createAt;
        this.updateAt = userEntryToDtoBuilder.updateAt;

    }


    public static class UserEntryToDtoBuilder implements Builder<UserDto> {
        private final String userId;
        private final String userName;
        private final String gender;
        private final String userStatus;
        private final ZonedDateTime createAt;
        private final ZonedDateTime updateAt;

        public UserEntryToDtoBuilder(User USER){
            this.userId = USER.getUSER_ID();
            this.userName = USER.getUSER_NM();
            this.gender = USER.getGENDER().getValue();
            this.userStatus =USER.getUSER_STATUS().getValue();
            this.createAt = USER.getCRATE_AT();
            this.updateAt = USER.getUPDATE_AT();
        }

        @Override
        public UserDto builder() {
            return new UserDto(this);
        }
    }


}
