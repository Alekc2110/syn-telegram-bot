package com.example.mysynergybot.telegramchat.data.dao;

import com.example.mysynergybot.telegramchat.entity.Goal;

import java.util.List;
import java.util.Optional;

public interface GoalDao {
    List<Goal> findAll();

    Optional<Goal> findById(Long id);

    Goal save(Goal goal);

    void deleteById(Long id);
}
