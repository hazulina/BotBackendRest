package com.botbackendrest.dao;

import com.botbackendrest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

    void deleteUserByChatId(int chatId);

    Optional<User> findUserByChatId(int chatId);


}
