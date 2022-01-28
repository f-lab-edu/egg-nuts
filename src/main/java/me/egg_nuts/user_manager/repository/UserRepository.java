package me.egg_nuts.user_manager.repository;

import me.egg_nuts.user_manager.entity.USER;
import org.springframework.data.jpa.repository.JpaRepository;

// <domainObject,domainObject pk data type
                                        // CrudRepository를 상속해서 많이 사용한다.
                                        // 대부분 사용하는 메서드는 CrudRepository에 있다
public interface UserRepository extends JpaRepository<USER,String> {
}
