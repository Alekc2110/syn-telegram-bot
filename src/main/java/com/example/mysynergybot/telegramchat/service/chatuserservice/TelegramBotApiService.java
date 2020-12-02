package com.example.mysynergybot.telegramchat.service.chatuserservice;

import com.example.mysynergybot.telegramchat.entity.dto.ParseResultDto;

public interface TelegramBotApiService {

    ParseResultDto getUserIdFromTelegram(Long telId, String phone, String firstName);


}
