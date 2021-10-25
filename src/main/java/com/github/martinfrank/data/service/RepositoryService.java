package com.github.martinfrank.data.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepositoryService {

    private PlayerRepository playerRepository;
    private MapAreaRepository mapAreaRepository;
    private UserRepository userRepository;

    public RepositoryService(
            @Autowired PlayerRepository playerRepository,
            @Autowired MapAreaRepository mapAreaRepository,
            @Autowired UserRepository userRepository) {
        this.playerRepository = playerRepository;
        this.mapAreaRepository = mapAreaRepository;
        this.userRepository = userRepository;
    }

    public MapAreaRepository getMapAreaRepository() {
        return mapAreaRepository;
    }

    public PlayerRepository getPlayerRepository() {
        return playerRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

}
