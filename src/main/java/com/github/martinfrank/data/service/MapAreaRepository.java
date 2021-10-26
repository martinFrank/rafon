package com.github.martinfrank.data.service;

import com.github.martinfrank.data.entity.MapArea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapAreaRepository extends JpaRepository<MapArea, Integer> {

    MapArea findByName(String mapAreaName);

//    @Query("select ma from MapArea ma where p.user = user")
//    MapArea findByPlayer(Player currentPlayer);
//
//    @Query("select p from Player p where p.user = user")
//    Player findByUser(@Param("userId") User user);
}
