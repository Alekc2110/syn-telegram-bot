package com.example.mysynergybot.telegramchat.service.chatuserservice.impl;

import com.example.mysynergybot.telegramchat.service.chatuserservice.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {


    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private transient String mail;
    @Value("${telegram.invitation.subject}")
    private transient String invSubject;
    @Value("${telegram.invitation.text}")
    private transient String invText;


        @Override
    public void sendInvitationToTelegramEmail(String userMail, String invLink) {

            final SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(userMail);
            mailMessage.setSubject(invSubject);
            mailMessage.setFrom(mail);
            mailMessage.setText(String.format("%s\n\n %s", invText, invLink));
            javaMailSender.send(mailMessage);

    }

}
