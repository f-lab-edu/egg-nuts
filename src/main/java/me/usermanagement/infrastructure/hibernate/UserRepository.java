package me.usermanagement.infrastructure.hibernate;

import me.usermanagement.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,String>  {
}
