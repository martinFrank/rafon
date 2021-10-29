package com.github.martinfrank.data.service;

import com.github.martinfrank.data.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    Item findByName(String name);
}