package com.example.mysynergybot.telegramchat.data.dao.impl;

import com.example.mysynergybot.telegramchat.data.dao.ChatUserDao;
import com.example.mysynergybot.telegramchat.entity.ChatUser;
import com.example.mysynergybot.telegramchat.data.repository.ChatUserRepository;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

@Component
public class ChatUserDaoImpl implements ChatUserDao {

    private final ChatUserRepository chatUserRepository;

    public ChatUserDaoImpl(ChatUserRepository chatUserRepository) {
        this.chatUserRepository = chatUserRepository;
    }

    @Override
    public List<ChatUser> findAll() {
        return chatUserRepository.findAll();
    }

    @Override
    public Optional<ChatUser> findById(Long id) {
        return chatUserRepository.findById(id);
    }

    @Override
    public boolean save(ChatUser chatUser) {
        ChatUser saved = chatUserRepository.save(chatUser);
        return saved.getId() == null;
    }

    @Override
    public void deleteById(Long id) {
      chatUserRepository.deleteChatUserByTelegramId(id);
    }
}
