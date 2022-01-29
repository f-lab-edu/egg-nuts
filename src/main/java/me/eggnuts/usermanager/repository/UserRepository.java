package me.eggnuts.usermanager.repository;

import me.eggnuts.usermanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,String> {
}
