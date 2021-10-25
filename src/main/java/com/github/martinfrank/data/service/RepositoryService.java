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

    public PlayerRepository getPlayerRepository() {
        return playerRepository;
    }

    public void setPlayerRepository(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public MapAreaRepository getMapAreaRepository() {
        return mapAreaRepository;
    }

    public void setMapAreaRepository(MapAreaRepository mapAreaRepository) {
        this.mapAreaRepository = mapAreaRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
