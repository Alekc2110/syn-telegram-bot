package com.example.mysynergybot.telegramchat.thread;

import com.example.mysynergybot.telegramchat.TelegramBotChat;
import com.example.mysynergybot.telegramchat.entity.dto.UserDto;
import com.example.mysynergybot.telegramchat.service.chatuserservice.ChatUserService;
import com.example.mysynergybot.telegramchat.service.chatuserservice.EmailService;
import com.example.mysynergybot.telegramchat.service.chatuserservice.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Slf4j
public class MessageSender  implements  Runnable{
    private static final int SLEEP_TIME = 1000;
    @Value("${telegram.username}")
    private transient String botUserName;
    @Value("${telegram.invitation.url}")
    private transient String url;

    @Autowired
    private transient TelegramBotChat telegramBotChat;
    @Autowired
    private transient UserService userService;
    @Autowired
    private transient ChatUserService chatUserService;
    @Autowired
    private transient EmailService emailService;


    @Override
    public void run() {
        if (log.isInfoEnabled()){ log.info("[STARTED] MsgSender...");}
        while (true) {
            for (Object object = telegramBotChat.sendQueue.poll(); object != null; object = telegramBotChat.sendQueue.poll()) {
                if (log.isDebugEnabled()){log.debug("Get new msg to send " + object);}
                send(object);
            }
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                if (log.isErrorEnabled()){log.error("Take interrupt while operate msg list", e);}
            }
        }

    }
    private void send(Object object) {
        SendMessage message = new SendMessage();
        try {
            final MessageType messageType = messageType(object);
            if (messageType == MessageType.SENDMESSAGE) {
                message = (SendMessage) object;
                if (log.isDebugEnabled()) {
                    log.debug("Use Execute for " + object);
                }
                telegramBotChat.execute(message);
            } else {
                if (log.isWarnEnabled()) {
                    log.warn("Cant detect type of object. " + object);
                }
            }
        } catch (TelegramApiException e) {
            if (log.isErrorEnabled()){log.error(e.getMessage(), e);}
            final UserDto userByTelegramId = userService.findUserByTelegramId(Long.parseLong(message.getChatId()));
            chatUserService.removeChatUser(Long.parseLong(message.getChatId()));
            final String link = url + botUserName;
            emailService.sendInvitationToTelegramEmail(userByTelegramId.getEmail(), link);
            // service in scheduler will do this job
        }
    }

    private MessageType messageType(Object object) {
        if (object instanceof SendMessage){return MessageType.SENDMESSAGE;}
        return MessageType.NOT_DETECTED;
    }

    enum MessageType {
        SENDMESSAGE, NOT_DETECTED
    }

}
