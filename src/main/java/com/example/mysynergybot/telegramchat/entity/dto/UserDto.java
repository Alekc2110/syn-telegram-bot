package com.example.mysynergybot.telegramchat.entity.dto;

import com.example.mysynergybot.telegramchat.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private boolean alreadyRegistered;
    private boolean isActive;
    private String phone;
    private Date dateOfBirth;
    private Long telegramChatId;
    private String photo;
    private List<String> roles;



}
