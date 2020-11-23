package com.example.mysynergybot.telegramchat.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParseResultDto {
    private Long telegramId;
    private String firstName;
    private String phone;

}
