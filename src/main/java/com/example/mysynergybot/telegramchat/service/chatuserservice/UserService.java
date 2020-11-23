package com.example.mysynergybot.telegramchat.service.chatuserservice;


import com.example.mysynergybot.telegramchat.entity.GoalStatus;
import com.example.mysynergybot.telegramchat.entity.dto.UserDto;
import com.example.mysynergybot.telegramchat.entity.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    List<UserDto> getUsers();

    UserDto getUserById(Long id);

    UserDto getUserByEmail(String email);

    UserDto getUserByPhone(String phone);

    Long addUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    void deleteUserById(Long id);

    void deleteUserByEmail(String email);

    Long saveDefaultUser(String email);

    boolean existByEmail(String email);

    boolean isAlreadyRegistered(String email);

    List<UserResponseDto> findUsersWithGoalStatus(GoalStatus status);

//    UserDto getUserByTelegramId(Long telegramId);

    List<UserDto> findUsersWithNullTelegramChatId();

    List<UserDto> getTelegramIdAndUpdateUser(List<UserDto> list);

}

