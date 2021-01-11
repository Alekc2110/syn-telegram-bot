package com.example.mysynergybot.telegramchat.data.dao;


import com.example.mysynergybot.telegramchat.entity.GoalStatus;
import com.example.mysynergybot.telegramchat.entity.User;
import com.example.mysynergybot.telegramchat.entity.dto.UserResponseDto;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    User findById(Long id);

    User findByEmail(String email);

    User findByPhone(String phone);

    User save(User user);

    void deleteById(Long id);

    void deleteByEmail(String email);

    boolean existByEmail(String email);

    boolean isAlreadyRegistered(String email);

    List<UserResponseDto> findUsersWithGoalStatus(GoalStatus status);

//    User findByTelegramChatId(Long telegramId);

    List<User> findUsersWithNullTelegramChatId();

    User findByTelegramId(Long telegramId);

}
