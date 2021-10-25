package com.github.martinfrank.data.service;

import com.github.martinfrank.data.entity.MapArea;
import com.github.martinfrank.data.entity.Player;
import com.github.martinfrank.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MapAreaRepository extends JpaRepository<MapArea, Integer> {

    MapArea findByMapAreaName(String mapAreaName);

//    @Query("select ma from MapArea ma where p.user = user")
//    MapArea findByPlayer(Player currentPlayer);

    @Query("select p from Player p where p.user = user")
    Player findByUser(@Param("userId") User user);
}
