package com.example.mysynergybot.telegramchat.service.chatuserservice.impl;

import com.example.mysynergybot.telegramchat.entity.dto.ParseResultDto;
import com.example.mysynergybot.telegramchat.service.chatuserservice.ParseService;
import com.example.mysynergybot.telegramchat.service.chatuserservice.TelegramBotApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Service
@Slf4j
@RequiredArgsConstructor
public class TelegramBotApiServiceImpl implements TelegramBotApiService {

    @Value("${telegram.api.url}")
    private String apiUrl;
    @Value("${telegram.token}")
    private String botToken;

    private final RestTemplate restTemplate;
    private final ParseService parseService;


    @Override
    public ParseResultDto getUserIdFromTelegram(Long telId, String phone, String firstName) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .path(botToken)
                .path("/sendContact").queryParam("chat_id", telId)
                .queryParam("phone_number", phone)
                .queryParam("first_name", firstName);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        return parseService.parse(response.getBody());
    }
}
