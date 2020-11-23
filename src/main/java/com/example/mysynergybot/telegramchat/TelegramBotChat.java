package  com.example.mysynergybot.telegramchat;

import com.example.mysynergybot.telegramchat.entity.dto.ChatUserDto;
import com.example.mysynergybot.telegramchat.service.chatuserservice.ChatUserService;
import com.example.mysynergybot.telegramchat.service.chatuserservice.TelegramBotApiService;
import com.example.mysynergybot.telegramchat.service.chatuserservice.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Queue;
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



    @Override
    public String getBotToken() {
        return botToken;
    }



    @Override
    public String getBotUsername() {
        return botUserName;
    }


}
