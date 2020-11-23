package com.example.mysynergybot.telegramchat.service.chatuserservice;

import com.example.mysynergybot.telegramchat.entity.dto.ParseResultDto;

public interface ParseService {
    ParseResultDto parse(String str);
}
