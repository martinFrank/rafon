package com.github.martinfrank.data.service;

import com.github.martinfrank.data.entity.Opponent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpponentRepository extends JpaRepository<Opponent, Integer> {

    Opponent findByName(String name);
}