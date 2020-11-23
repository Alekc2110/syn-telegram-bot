package com.example.mysynergybot.telegramchat.service.chatuserservice.impl;

import com.example.mysynergybot.telegramchat.entity.dto.ParseResultDto;
import com.example.mysynergybot.telegramchat.service.chatuserservice.ParseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ParseServiceImpl implements ParseService {


    private final ObjectMapper mapper;

    public ParseServiceImpl(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ParseResultDto parse(String str) {
        ParseResultDto parseResultDto = new ParseResultDto();
        try {
            JsonNode root = mapper.readTree(str);
            JsonNode result = root.path("result");
            JsonNode contact = result.path("contact");
            JsonNode phone_number = contact.path("phone_number");
            JsonNode first_name = contact.path("first_name");
            JsonNode user_id = contact.path("user_id");

            parseResultDto.setTelegramId(user_id.asLong());
            parseResultDto.setFirstName(first_name.asText());
            parseResultDto.setPhone(phone_number.asText());
        } catch (JsonProcessingException e) {
            log.info("IOException when parsing - {}", e.getMessage());
        }
        return parseResultDto;
    }
}
