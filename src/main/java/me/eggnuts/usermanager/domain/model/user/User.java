package me.eggnuts.usermanager.domain.model.user;


import me.eggnuts.usermanager.domain.model.Builder;

import me.eggnuts.usermanager.domain.converter.GenderConverter;
import me.eggnuts.usermanager.domain.converter.StatusConverter;
import me.eggnuts.usermanager.domain.model.enums.Gender;
import me.eggnuts.usermanager.domain.model.enums.Status;
import me.eggnuts.usermanager.domain.model.CommonEntity;
import lombok.*;

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
