package com.github.martinfrank.data.service;

import com.github.martinfrank.data.entity.PlayerItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerItemRepository extends JpaRepository<PlayerItem, Integer> {

//    PlayerItem findByName(String name);

}