package com.example.mysynergybot.telegramchat.data.dao.impl;

import com.example.mysynergybot.telegramchat.data.dao.GoalDao;
import com.example.mysynergybot.telegramchat.data.repository.GoalRepository;
import com.example.mysynergybot.telegramchat.entity.Goal;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class GoalDaoImpl implements GoalDao {
    private final GoalRepository goalRepository;

    public GoalDaoImpl(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @Override
    public List<Goal> findAll() {
        return goalRepository.findAll();
    }

    @Override
    public Optional<Goal> findById(Long id) {
        return goalRepository.findById(id);
    }

    @Override
    public Goal save(Goal goal) {
        return goalRepository.save(goal);
    }

    @Override
    public void deleteById(Long id) {
      goalRepository.deleteById(id);
    }
}
