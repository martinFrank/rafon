package com.github.martinfrank.data.service;

import com.github.martinfrank.data.entity.MapAreaAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapAreaActionRepository extends JpaRepository<MapAreaAction, Integer> {

    MapAreaAction findByMapAreaName(String mapAreaName);

}
