package me.usermanagement.domain.model.user;


import me.usermanagement.common.Builder;

import me.usermanagement.common.converter.GenderConverter;
import me.usermanagement.common.converter.StatusConverter;
import me.usermanagement.common.enums.Gender;
import me.usermanagement.common.enums.Status;
import me.usermanagement.domain.model.CommonEntity;
import lombok.*;
import me.usermanagement.interfaces.user.facade.dto.UserDto;

import javax.persistence.*;



@Getter
@Entity
@NoArgsConstructor
public class User extends CommonEntity {

    @Id
    private String USER_ID;

    @NonNull
    private String USER_NM;

    @Convert(converter = GenderConverter.class)
    private Gender GENDER;

    @Convert(converter = StatusConverter.class)
    private Status USER_STATUS;

    private User(UserBuilder userBuilder){
        this.USER_ID = userBuilder.userId;
        this.USER_NM = userBuilder.userName;
        this.GENDER = userBuilder.gender;
        this.USER_STATUS = userBuilder.userStatus;
    }

    public static class  UserBuilder implements Builder<User>{
        private final String userId;
        private final String userName;
        private final Gender gender;
        private final Status userStatus;

        public UserBuilder(UserDto userDto){
            this.userId= userDto.getUserId();
            this.userName = userDto.getUserName();
            this.gender =  Gender.findEnumByCode(userDto.getGender());
            this.userStatus = Status.findEnumByCode(userDto.getUserStatus());
        }

        @Override
        public User builder() {
            return new User(this);
        }

    }
}
