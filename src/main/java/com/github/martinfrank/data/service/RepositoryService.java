package com.github.martinfrank.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepositoryService {

    private PlayerRepository playerRepository;
    private MapAreaRepository mapAreaRepository;
    private UserRepository userRepository;
    private QuestItemRepository questItemRepository;
    private MapAreaActionRepository mapAreaActionRepository;

    public RepositoryService(
            @Autowired PlayerRepository playerRepository,
            @Autowired MapAreaRepository mapAreaRepository,
            @Autowired UserRepository userRepository,
            @Autowired QuestItemRepository questItemRepository,
            @Autowired MapAreaActionRepository mapAreaActionRepository) {
        this.playerRepository = playerRepository;
        this.mapAreaRepository = mapAreaRepository;
        this.userRepository = userRepository;
        this.questItemRepository = questItemRepository;
        this.mapAreaActionRepository = mapAreaActionRepository;
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

    public QuestItemRepository getQuestItemRepository() {
        return questItemRepository;
    }

    public void setQuestItemRepository(QuestItemRepository questItemRepository) {
        this.questItemRepository = questItemRepository;
    }

    public MapAreaActionRepository getMapAreaActionRepository() {
        return mapAreaActionRepository;
    }

    public void setMapAreaActionRepository(MapAreaActionRepository mapAreaActionRepository) {
        this.mapAreaActionRepository = mapAreaActionRepository;
    }
}
