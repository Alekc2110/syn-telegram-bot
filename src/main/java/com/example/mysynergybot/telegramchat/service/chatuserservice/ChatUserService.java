package com.example.mysynergybot.telegramchat.service.chatuserservice;

import com.example.mysynergybot.telegramchat.entity.dto.ChatUserDto;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.List;


public interface ChatUserService {
    void removeChatUser(User user);
    boolean addChatUser(ChatUserDto chatUserDto);
    boolean hasChatUser(Long chatUserId);
    List<ChatUserDto> getChatUsersList();
    void updateChatUser(ChatUserDto user);


   }
