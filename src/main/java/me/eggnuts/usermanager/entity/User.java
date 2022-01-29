package me.eggnuts.usermanager.entity;


import me.eggnuts.usermanager.common.Builder;

import me.eggnuts.usermanager.converter.GenderConverter;
import me.eggnuts.usermanager.converter.StatusConverter;
import me.eggnuts.usermanager.defined.Gender;
import me.eggnuts.usermanager.defined.Status;
import me.eggnuts.usermanager.dto.UserDto;
import lombok.*;
import javax.persistence.*;



@Getter
@Entity
@NoArgsConstructor
public class User extends CommonEntity  {

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
