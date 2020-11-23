package com.example.mysynergybot.telegramchat.entity.dto;

import com.example.mysynergybot.telegramchat.entity.GoalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private Long UserId;
    private Long telegramChatId;
    private String firstName;
    private GoalStatus status;
    private String goalDescription;


}
