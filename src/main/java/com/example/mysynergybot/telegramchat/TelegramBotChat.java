package  com.example.mysynergybot.telegramchat;

import com.example.mysynergybot.telegramchat.entity.dto.ChatUserDto;
import com.example.mysynergybot.telegramchat.entity.dto.UserDto;
import com.example.mysynergybot.telegramchat.service.chatuserservice.ChatUserService;
import com.example.mysynergybot.telegramchat.service.chatuserservice.TelegramBotApiService;
import com.example.mysynergybot.telegramchat.service.chatuserservice.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendContact;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;


@Component
@Slf4j
public class TelegramBotChat extends TelegramLongPollingBot {

   @Value("${telegram.username}")
   private String botUserName;
   @Value("${telegram.token}")
   private String botToken;

   private final int RECONNECT_PAUSE = 10000;


    private final ChatUserService chatService;
    private final UserService userService;
    private final TelegramBotApiService telegramBotApiService;


    public final Queue<Object> receiveQueue = new ConcurrentLinkedQueue<>();
    public final Queue<Object> sendQueue = new ConcurrentLinkedQueue<>();


    public TelegramBotChat(ChatUserService chatService, UserService userService, TelegramBotApiService telegramBotApiService) {
        this.chatService = chatService;
        this.userService = userService;

        this.telegramBotApiService = telegramBotApiService;
    }

    @PostConstruct
    public void botConnect() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(this);
            log.info("[STARTED] TelegramAPI. Bot Connected. Bot class: " + this);
        } catch (TelegramApiRequestException e) {
            log.error("Cant Connect. Pause " + RECONNECT_PAUSE / 1000 + "sec and try again. Error: " + e.getMessage());
            try {
                Thread.sleep(RECONNECT_PAUSE);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
                return;
            }
            botConnect();
        }
    }
    @PostConstruct
    public void getUserTelegramId(){
//        List<UserDto> collected = userService.getUsers().stream()
//               .filter(userDto -> userDto.getTelegramChatId() == null).collect(Collectors.toList());
//        for (UserDto u : collected) {
//            String userIdFromTelegram = userService.getUserIdFromTelegram(Math.round(Math.random()), u.getPhone());
//            u.setTelegramChatId(Long.parseLong(userIdFromTelegram));
//            userService.updateUser(u);
    }


//        List<UserDto> collected = userService.getUsers().stream()
//                .filter(userDto -> userDto.getTelegramChatId() == null).collect(Collectors.toList());
//        for (UserDto u : collected) {
//            String userIdFromTelegram = userService.getUserIdFromTelegram(Math.round(Math.random()), u.getPhone());
//            u.setTelegramChatId(Long.parseLong(userIdFromTelegram));
//            userService.updateUser(u);
//        }



    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage()) {
            log.error("Update doesn't have a body!");
            throw new IllegalStateException("Update doesn't have a body!");
        }
        User from = update.getMessage().getFrom();
       if(update.getMessage().getText().equals("/start")){

           ChatUserDto newUser = ChatUserDto.builder()
                   .telegramId(from.getId())
                   .firstName(Objects.requireNonNull(from.getFirstName()))
                   .build();

          chatService.addChatUser(newUser);

       }

       receiveQueue.add(update);

       log.info("update - {} message.chat -{} message.contact -{} message.getFrom - {} getText - {}",
                update.getUpdateId() + "\n", update.getMessage().getChat().toString() + "\n",
                update.getMessage().getContact() + "\n", update.getMessage().getFrom() + "\n",
                update.getMessage().getText());

       }

    private void replyToUser(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("error {} when trying to reply to user", e.getMessage());
        }
    }



//
//    // несколько проверок, чтобы можно было отправлять сообщения другим пользователям
//    private boolean canSendMessage(User user, Message msg) {
//
//        SendMessage answer = new SendMessage();
//        answer.setChatId(msg.getChatId());
//
//        if (!msg.hasText() || msg.getText().trim().length() == 0) {
//            log.info("User {} is trying to send empty message!", user.getId());
//            answer.setText("You shouldn't send empty messages!");
//            replyToUser(answer);
//            return false;
//        }

//        if(!chatService.hasChatUser(user)) {
//            log.info("User {} is trying to send message without starting the bot!", user.getId());
//            answer.setText("Firstly you should start bot! Use /start command!");
//            replyToUser(answer);
//            return false;
//        }

//        if (chatService.getDisplayedName(user) == null) {
//            log.info("User {} is trying to send message without setting a name!", user.getId());
//            answer.setText("You must set a name before sending messages.\nUse '/set_name <displayed_name>' command.");
//            replyToUser(answer);
//            return false;
//        }

//        return true;
//    }

//    private void sendMessageToUsers(SendMessage message) {
//        try {
//            execute(message);
//            } catch (TelegramApiException e) {
//            log.error("error {} when trying to send massage to Users", e.getMessage());
//        }
//    }
//



    @Override
    public String getBotToken() {
        return botToken;
    }



    @Override
    public String getBotUsername() {
        return botUserName;
    }


}
