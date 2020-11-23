package com.example.mysynergybot.scheduler;

import com.example.mysynergybot.telegramchat.entity.GoalStatus;
import com.example.mysynergybot.telegramchat.entity.dto.UserDto;
import com.example.mysynergybot.telegramchat.entity.dto.UserResponseDto;
import com.example.mysynergybot.telegramchat.handler.UserResponseHandler;
import com.example.mysynergybot.telegramchat.service.chatuserservice.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class DbScheduler {

    //private final UserDao userDao;
    private final UserService userService;
    private final UserResponseHandler userResponseHandler;


    public DbScheduler(UserService userService, UserResponseHandler userResponseHandler) {
        this.userService = userService;
        this.userResponseHandler = userResponseHandler;
    }

    @Scheduled(cron="${dbscheduler.pickAndSortFinishedGoals.delay}")
    public void NotifyUsersWithOverDueGoals(){
        List<UserResponseDto> usersWithOverDueGoals = userService.findUsersWithGoalStatus(GoalStatus.OVERDUE);
        log.info("usersWithGoalStatus list - {}", usersWithOverDueGoals);
        userResponseHandler.handle(usersWithOverDueGoals);

    }



    @Scheduled(cron="${dbscheduler.updateUserWithNullTelegramId.delay}")
    public void updateUserWithNullTelegramId(){
        List<UserDto> usersWithNullTelegramId = userService.findUsersWithNullTelegramChatId();
        log.info("List UserDtos - {}",usersWithNullTelegramId);
        List<UserDto> UpdatedUsers = userService.getTelegramIdAndUpdateUser(usersWithNullTelegramId);

    }


}
