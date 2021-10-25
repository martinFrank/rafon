package com.github.martinfrank.data.service;

import com.github.martinfrank.data.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    Player findByUsername(String username);

}
