package com.example.mysynergybot.telegramchat.service.chatuserservice.impl;

import com.example.mysynergybot.telegramchat.service.chatuserservice.TelegramBotApiService;
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
public class TelegramBotApiServiceImpl implements TelegramBotApiService {

    @Value("${telegram.api.url}")
    private String apiUrl;
    @Value("${telegram.token}")
    private String botToken;

    private final RestTemplate restTemplate;

    public TelegramBotApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public String getUserIdFromTelegram(Long chatId, String phone, String firstName) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .path(botToken)
                .path("/sendContact").queryParam("chat_id", chatId)
                .queryParam("phone_number", phone)
                .queryParam("first_name", firstName);

//        ResponseEntity<Contact> responseContact = restTemplate.getForEntity(builder.toUriString(), Contact.class);
//        log.info("TelegramBotApiService returned contact - {}", responseContact.toString());

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);
 //         return responseContact.getHeaders().getFirst("user_id");
        return response.getBody();
    }
}
