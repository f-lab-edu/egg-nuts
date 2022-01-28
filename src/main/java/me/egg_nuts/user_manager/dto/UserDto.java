package me.egg_nuts.user_manager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import me.egg_nuts.user_manager.common.Builder;
import me.egg_nuts.user_manager.defined.Status;
import me.egg_nuts.user_manager.entity.USER;
import me.egg_nuts.user_manager.error.UserException;
import me.egg_nuts.user_manager.message.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;


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


    public void changeUserStatus(String status){
        boolean existEnum = Arrays.stream(Status.values())
                .map(Status::getCode)
                .collect(Collectors.toList())
                .contains(status);

        if(existEnum){
            this.userStatus = status;
        }else{
            throw new UserException(ErrorMessage.NO_STATUS);
        }

    }

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

        public UserEntryToDtoBuilder(USER USER){
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
