package com.example.mysynergybot.telegramchat.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contact {
    @JsonProperty(value = "user_id")
    private Long userId;
    @JsonProperty(value = "phone_number")
    private String phone;
    @JsonProperty(value = "first_name")
    private String firstName;

}
