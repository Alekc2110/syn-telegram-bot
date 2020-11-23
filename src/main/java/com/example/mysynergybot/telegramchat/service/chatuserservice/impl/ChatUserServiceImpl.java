package com.example.mysynergybot.telegramchat.service.chatuserservice.impl;

import com.example.mysynergybot.telegramchat.data.dao.ChatUserDao;
import com.example.mysynergybot.telegramchat.entity.ChatUser;
import com.example.mysynergybot.telegramchat.entity.dto.ChatUserDto;
import com.example.mysynergybot.telegramchat.service.chatuserservice.ChatUserService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatUserServiceImpl implements ChatUserService {

     private final ChatUserDao chatUserDao;
     private final MapperFacade mapperFacade;

    public ChatUserServiceImpl(ChatUserDao chatUserDao, MapperFacade mapperFacade) {
        this.chatUserDao = chatUserDao;
        this.mapperFacade = mapperFacade;
    }


    @Override
    public void removeChatUser(User user) {
        chatUserDao.deleteById(user.getId().longValue());
    }

    @Override
    public boolean addChatUser(ChatUserDto chatUserdto) {
        return chatUserDao.save(mapperFacade.map(chatUserdto, ChatUser.class));
    }

    @Override
    public boolean hasChatUser(User user) {
        return getChatUsersList().stream().anyMatch(a-> a.getTelegramId().equals(user.getId()));
    }

    @Override
    public String getUserName(User user) {
        return getChatUsersList().stream().filter(u-> u.getFirstName().equals(user.getFirstName())).map(ChatUserDto::getFirstName)
                .findFirst().orElse("");
    }


    @Override
    public List<ChatUserDto> getChatUsersList() {

        return chatUserDao.findAll().stream().map(chatUser -> mapperFacade.map(chatUser, ChatUserDto.class)).collect(Collectors.toList());
    }

    @Override
    public void updateChatUserName(User user) {
        chatUserDao.findById(user.getId().longValue()).ifPresent(chatUser -> chatUser.setFirstName(user.getFirstName()));

    }

//    @Override
//    public List<ChatUser> findUsersWithOverdueGoals() {
//        List<Game> all = gameDao.findAll();
//
//
//
//
//        List<Goal> sortedGoals = goalDao.findAll()
//                .stream().filter(goal -> goal.getStatus().equals(GoalStatus.ACTIVE) && goal.getFinishTime()
//                        .isAfter(LocalDateTime.now())).collect(Collectors.toList());
//
//        return null;
//    }


}

