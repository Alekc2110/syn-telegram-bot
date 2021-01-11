package com.example.mysynergybot.telegramchat.data.repository;


import com.example.mysynergybot.telegramchat.entity.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {
    void deleteChatUserByTelegramId(Long telegramId);
}
