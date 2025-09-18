package com.asit.chatapp.Repositoty;

import com.asit.chatapp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
