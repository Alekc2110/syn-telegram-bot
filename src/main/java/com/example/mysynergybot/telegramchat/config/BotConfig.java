package com.example.mysynergybot.telegramchat.config;
import com.example.mysynergybot.telegramchat.thread.MessageReceiver;
import com.example.mysynergybot.telegramchat.thread.MessageSender;
import org.hibernate.annotations.SQLInsert;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
@SQLInsert(sql = "import.sql")
public class BotConfig {

//    @Bean
//    public MessageReceiver messageReceiver() {
//        return new MessageReceiver();
//    }

    @Bean
    public MessageSender messageSender(){
        return new MessageSender();
    }


//    @Bean
//    public Thread createThreadReceiver(){
//        Thread receiver = new Thread(messageReceiver());
//        receiver.setDaemon(true);
//        receiver.setName("MsgReciever");
//        receiver.setPriority(3);
//        receiver.start();
//        return receiver;
//    }

    @Bean
    public Thread createThreadSender(){
        Thread sender = new Thread(messageSender());
        sender.setDaemon(true);
        sender.setName("MsgSender");
        sender.setPriority(1);
        sender.start();
        return sender;
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
