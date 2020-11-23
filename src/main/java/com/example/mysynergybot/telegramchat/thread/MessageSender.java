package com.example.mysynergybot.telegramchat.thread;

import com.example.mysynergybot.telegramchat.TelegramBotChat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;


@Slf4j
public class MessageSender  implements  Runnable{

    private final int SENDER_SLEEP_TIME = 1000;

    @Autowired
    private TelegramBotChat telegramBotChat;


    @Override
    public void run() {
        log.info("[STARTED] MsgSender...");
        try {
            while (true) {
                for (Object object = telegramBotChat.sendQueue.poll(); object != null; object = telegramBotChat.sendQueue.poll()) {
                    log.debug("Get new msg to send " + object);
                    send(object);
                }
                try {
                    Thread.sleep(SENDER_SLEEP_TIME);
                } catch (InterruptedException e) {
                    log.error("Take interrupt while operate msg list", e);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
    private void send(Object object) {
        try {
            MessageType messageType = messageType(object);
            switch (messageType) {
                case SENDMESSAGE:
                    SendMessage message = (SendMessage) object;
                    log.debug("Use Execute for " + object);
                    telegramBotChat.execute(message);
                    break;

                default:
                    log.warn("Cant detect type of object. " + object);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private MessageType messageType(Object object) {
        if (object instanceof SendMessage) return MessageType.SENDMESSAGE;

        return MessageType.NOT_DETECTED;
    }

    enum MessageType {
        SENDMESSAGE, NOT_DETECTED
    }


}
