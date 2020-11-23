package com.example.mysynergybot.telegramchat.data.repository;

import com.example.mysynergybot.telegramchat.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {
}
