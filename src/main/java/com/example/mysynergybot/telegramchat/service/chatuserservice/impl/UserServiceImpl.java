package com.example.mysynergybot.telegramchat.service.chatuserservice.impl;

import com.example.mysynergybot.telegramchat.data.dao.UserDao;
import com.example.mysynergybot.telegramchat.entity.GoalStatus;
import com.example.mysynergybot.telegramchat.entity.User;
import com.example.mysynergybot.telegramchat.entity.dto.ParseResultDto;
import com.example.mysynergybot.telegramchat.entity.dto.UserDto;
import com.example.mysynergybot.telegramchat.entity.dto.UserResponseDto;
import com.example.mysynergybot.telegramchat.service.chatuserservice.EmailService;
import com.example.mysynergybot.telegramchat.service.chatuserservice.ParseService;
import com.example.mysynergybot.telegramchat.service.chatuserservice.TelegramBotApiService;
import com.example.mysynergybot.telegramchat.service.chatuserservice.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Value("${telegram.api.url}")
    private String apiUrl;
    @Value("${telegram.token}")
    private String botToken;
    @Value("${telegram.username}")
    private String botUserName;
    @Value("${telegram.invitation.url}")
    private transient String url;

    private final UserDao userDao;
    private final MapperFacade mapperFacade;
    private final TelegramBotApiService telegramBotApiService;
    private final EmailService emailService;



    @Override
    public List<UserDto> getUsers() {
        return userDao.findAll().stream()
                .map(user -> mapperFacade.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        return mapperFacade.map(userDao.findById(id), UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return mapperFacade.map(userDao.findByEmail(email), UserDto.class);
    }

    @Override
    public UserDto getUserByPhone(String phone) {
        return mapperFacade.map(userDao.findByPhone(phone), UserDto.class);
    }

    @Override
    public Long addUser(UserDto userDto) {
        return userDao.save(mapperFacade.map(userDto, User.class)).getId();
    }

    @Override
    public UserDto updateUser(UserDto userDto) {

        final User userById = userDao.findById(userDto.getId());

        mapperFacade.map(mapperFacade.map(userDto, User.class), userById);

        return mapperFacade.map(userById, UserDto.class);
    }


    @Override
    public void deleteUserById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public void deleteUserByEmail(String email) {
        userDao.deleteByEmail(email);
    }

    @Override
    public Long saveDefaultUser(String email) {
        final var userDto = new UserDto();
            userDto.setEmail(email);
            userDto.setRoles(List.of("GUEST"));
        return userDao.save(mapperFacade.map(userDto, User.class)).getId();
    }

    @Override
    public boolean existByEmail(String email) {
        return userDao.existByEmail(email);
    }

    @Override
    public boolean isAlreadyRegistered(String email) {
        return userDao.isAlreadyRegistered(email);
    }

    @Override
    public List<UserResponseDto> findUsersWithGoalStatus(GoalStatus status) {
        return userDao.findUsersWithGoalStatus(status);
    }

    @Override
    public List<UserDto> findUsersWithNullTelegramChatId() {
        return userDao.findUsersWithNullTelegramChatId().stream()
                .map(user -> mapperFacade.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void getTelegramIdAndUpdateUser(List<UserDto> list) {
        for (UserDto u : list) {
            ParseResultDto parsedUserDto = telegramBotApiService.getUserIdFromTelegram(825619647L, u.getPhone(), u.getFirstName());
            log.info("parsed user - {}", parsedUserDto);

            if(parsedUserDto.getTelegramId()==0){
               final User user = userDao.findByPhone(parsedUserDto.getPhone());
               final String link = url + botUserName;
               emailService.sendInvitationToTelegramEmail(user.getEmail(),link);
            } else {
                u.setTelegramChatId(parsedUserDto.getTelegramId());
            }
        }

        list.forEach(this::updateUser);
    }

}


