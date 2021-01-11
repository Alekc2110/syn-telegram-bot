package com.example.mysynergybot.telegramchat.data.dao.impl;

import com.example.mysynergybot.telegramchat.data.dao.UserDao;
import com.example.mysynergybot.telegramchat.entity.GoalStatus;
import com.example.mysynergybot.telegramchat.entity.User;
import com.example.mysynergybot.telegramchat.data.repository.UserRepository;
import com.example.mysynergybot.telegramchat.entity.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException(String.format("User with id '%s' not found", id)));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()
                -> new EntityNotFoundException(String.format("User with email '%s' not found", email)));
    }

    @Override
    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone).orElseThrow(()
                -> new EntityNotFoundException(String.format("User with phone '%s' not found", phone)));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    @Override
    public boolean existByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public boolean isAlreadyRegistered(String email) {
        return userRepository.existsUserByEmailAndAlreadyRegisteredIsTrue(email);
    }

    @Override
    public List<UserResponseDto> findUsersWithGoalStatus(GoalStatus status) {
        return userRepository.findUsersWithGoalStatus(status);
    }

//    @Override
//    public User findByTelegramChatId(Long telegramId) {
//
//        return userRepository.findByTelegramChatId(telegramId).orElseThrow(()
//                -> new EntityNotFoundException(String.format("User with telegramId '%d' not found", telegramId )));
//    }

    @Override
    public List<User> findUsersWithNullTelegramChatId() {
        return userRepository.findUsersWithNullTelegramChatId();
    }

    @Override
    public User findByTelegramId(Long telegramId) {
        return userRepository.findByTelegramChatId(telegramId).orElseThrow(()
                -> new EntityNotFoundException(String.format("User with telegramId '%s' not found", telegramId)));
    }

}
