package com.example.mysynergybot.telegramchat.service.chatuserservice;

public interface EmailService {

    void sendInvitationToTelegramEmail(final String userMail, final String invLink);

}
