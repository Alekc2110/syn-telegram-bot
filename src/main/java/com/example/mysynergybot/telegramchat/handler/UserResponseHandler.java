package com.example.mysynergybot.telegramchat.handler;

import com.example.mysynergybot.telegramchat.TelegramBotChat;
import com.example.mysynergybot.telegramchat.entity.dto.UserResponseDto;
import com.example.mysynergybot.telegramchat.service.chatuserservice.ChatUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserResponseHandler {
    @Value("${telegram.notifytext}")
    private String NOTIFY_USER_FOR_OVERDUE_GOAL;


    private final TelegramBotChat telegramBotChat;



 public void handle(List<UserResponseDto> list){
      if(list.size()>0){
          list.stream().filter(this::verifyUser)
                  .map(this::createMessage)
                  .forEach(sendMessage -> {
                          telegramBotChat.sendQueue.add(sendMessage);
                          log.info("Sending Message - {}", sendMessage);
                  });

      }
 }

 private SendMessage createMessage(UserResponseDto user) {
     return SendMessage.builder()
             .chatId(String.valueOf(user.getTelegramChatId()))
             .text(String.format(NOTIFY_USER_FOR_OVERDUE_GOAL, user.getGoalDescription()))
             .build();
 }

 private boolean verifyUser(UserResponseDto user){
     return user.getTelegramChatId() != null;
     //&& user.getTelegramChatId() != 0
 }

}

