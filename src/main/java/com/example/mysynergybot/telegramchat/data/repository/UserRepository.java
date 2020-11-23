package com.example.mysynergybot.telegramchat.data.repository;


import com.example.mysynergybot.telegramchat.entity.GoalStatus;
import com.example.mysynergybot.telegramchat.entity.User;
import com.example.mysynergybot.telegramchat.entity.dto.UserResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);

    boolean existsUserByEmailAndAlreadyRegisteredIsTrue(String email);

    boolean existsUserByEmail (String email);

    void deleteByEmail(String email);

    @Query("select new com.example.mysynergybot.telegramchat.entity.dto.UserResponseDto (u.id, u.telegramChatId, u.firstName, g.status, g.description) from User u inner join Goal g on u.id = g.user.id where g.status =:status")
    List<UserResponseDto> findUsersWithGoalStatus(@Param("status") GoalStatus status);

 //   Optional<User> findByTelegramChatId(Long telegramId);

    @Query("select u from User u where u.telegramChatId IS NULL")
    List<User> findUsersWithNullTelegramChatId();


}
