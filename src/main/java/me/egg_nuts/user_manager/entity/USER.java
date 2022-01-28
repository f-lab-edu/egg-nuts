package me.egg_nuts.user_manager.entity;


import me.egg_nuts.user_manager.common.Builder;

import me.egg_nuts.user_manager.converter.GenderConverter;
import me.egg_nuts.user_manager.converter.StatusConverter;
import me.egg_nuts.user_manager.defined.Gender;
import me.egg_nuts.user_manager.defined.Status;
import me.egg_nuts.user_manager.dto.UserDto;
import lombok.*;
import javax.persistence.*;



@Getter
@Entity
@NoArgsConstructor
public class USER extends CommonEntity  {

    @Id
    private String USER_ID;

    @NonNull
    private String USER_NM;



    @Convert(converter = GenderConverter.class)
    private Gender GENDER;


    @Convert(converter = StatusConverter.class)
    private Status USER_STATUS;






    private USER(UserBuilder userBuilder){
        this.USER_ID = userBuilder.userId;
        this.USER_NM = userBuilder.userName;
        this.GENDER = userBuilder.gender;
        this.USER_STATUS = userBuilder.userStatus;
    }

    public static class  UserBuilder implements Builder<USER>{
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
        public USER builder() {
            return new USER(this);
        }

    }


}
