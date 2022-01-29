package me.eggnuts.usermanager.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


// spring context를 loading  해서 test를 하겠다
@SpringBootTest
class UserRepositoryTest {
    private  final  UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository){
        this.userRepository =userRepository;
    }

    @Test
    void crud(){


//            System.out.println(UserCache.compute("bls",null,CacheCommand.GET).get());
//            System.out.println(UserCache.compute("bls",null,CacheCommand.DELETE));
//            System.out.println(UserCache.compute("bls",null,CacheCommand.GET));


       //  userRepository.save(    new USER.UserBuilder(userDto).builder());

    }


}