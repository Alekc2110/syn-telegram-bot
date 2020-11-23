package com.example.mysynergybot.telegramchat.service.chatuserservice;

public interface TelegramBotApiService {

    String getUserIdFromTelegram(Long chatId, String phone, String firstName);

}
