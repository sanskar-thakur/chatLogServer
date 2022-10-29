package com.project.chatlogserver.repositories;

import com.project.chatlogserver.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
