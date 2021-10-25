package com.github.martinfrank.data.service;

import com.github.martinfrank.data.entity.QuestItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestItemRepository extends JpaRepository<QuestItem, Integer> {

    QuestItem findByName(String username);
}