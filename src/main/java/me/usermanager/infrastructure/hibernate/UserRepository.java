package me.usermanager.infrastructure.hibernate;

import me.usermanager.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,String>  {
}
