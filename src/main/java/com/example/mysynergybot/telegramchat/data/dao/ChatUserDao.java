package com.example.mysynergybot.telegramchat.data.dao;


import com.example.mysynergybot.telegramchat.entity.ChatUser;

import java.util.List;
import java.util.Optional;

public interface ChatUserDao {

    List<ChatUser> findAll();

    Optional<ChatUser> findById(Long id);

    boolean save(ChatUser chatUser);

    void deleteById(Long id);

}
