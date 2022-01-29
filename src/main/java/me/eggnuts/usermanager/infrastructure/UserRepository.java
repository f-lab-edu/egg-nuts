package me.eggnuts.usermanager.infrastructure;

import me.eggnuts.usermanager.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,String> {
}
