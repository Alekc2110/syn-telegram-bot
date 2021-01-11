package com.example.mysynergybot.telegramchat.service.chatuserservice.impl;

import com.example.mysynergybot.telegramchat.data.dao.ChatUserDao;
import com.example.mysynergybot.telegramchat.entity.ChatUser;
import com.example.mysynergybot.telegramchat.entity.dto.ChatUserDto;
import com.example.mysynergybot.telegramchat.service.chatuserservice.ChatUserService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;


import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ChatUserServiceImpl implements ChatUserService {

     private final ChatUserDao chatUserDao;
     private final MapperFacade mapperFacade;

    public ChatUserServiceImpl(ChatUserDao chatUserDao, MapperFacade mapperFacade) {
        this.chatUserDao = chatUserDao;
        this.mapperFacade = mapperFacade;
    }


    @Override
    public void removeChatUser(Long telegramId) {
        chatUserDao.deleteById(telegramId);
    }

    @Override
    public boolean addChatUser(ChatUserDto chatUserdto) {
        return chatUserDao.save(mapperFacade.map(chatUserdto, ChatUser.class));
    }

    @Override
    public boolean hasChatUser(Long telegramId) {
        return chatUserDao.findAll().stream().anyMatch(a-> a.getTelegramId().longValue()==telegramId);
    }




    @Override
    public List<ChatUserDto> getChatUsersList() {

        return chatUserDao.findAll().stream().map(chatUser -> mapperFacade.map(chatUser, ChatUserDto.class)).collect(Collectors.toList());
    }

    @Override
    public void updateChatUser(ChatUserDto user) {
        chatUserDao.save(mapperFacade.map(user, ChatUser.class));

    }

}

