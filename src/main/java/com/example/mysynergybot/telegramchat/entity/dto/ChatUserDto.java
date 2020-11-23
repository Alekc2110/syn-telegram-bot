package com.example.mysynergybot.telegramchat.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatUserDto {

    private Long id;
    private Integer telegramId;
    private String firstName;
    private String phone;


}
