package com.example.mysynergybot.telegramchat.entity;

import lombok.*;
import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "chatUser")
public class ChatUser {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column(name = "telegram_user_id", unique = true)
    private Long telegramId;
    @Column(name = "first_name")
    private String firstName;
//    @Column(name = "phone_number", unique = true)
//    private String phone;


}
