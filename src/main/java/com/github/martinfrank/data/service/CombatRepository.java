package com.github.martinfrank.data.service;

import com.github.martinfrank.data.entity.Combat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CombatRepository extends JpaRepository<Combat, Integer> {

    Combat findByName(String name);
}