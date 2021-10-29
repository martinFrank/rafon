package com.github.martinfrank.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepositoryService {

    private final PlayerRepository playerRepository;
    private final MapAreaRepository mapAreaRepository;
    private final UserRepository userRepository;
    private final QuestItemRepository questItemRepository;
    private final MapAreaActionRepository mapAreaActionRepository;
    private final CombatRepository combatRepository;
    private final OpponentRepository opponentRepository;
    private final ItemRepository itemRepository;
    private final PlayerItemRepository playerItemRepository;

    public RepositoryService(
            @Autowired PlayerRepository playerRepository,
            @Autowired MapAreaRepository mapAreaRepository,
            @Autowired UserRepository userRepository,
            @Autowired QuestItemRepository questItemRepository,
            @Autowired MapAreaActionRepository mapAreaActionRepository,
            @Autowired CombatRepository combatRepository,
            @Autowired OpponentRepository opponentRepository,
            @Autowired ItemRepository itemRepository,
            @Autowired PlayerItemRepository playerItemRepository) {
        this.playerRepository = playerRepository;
        this.mapAreaRepository = mapAreaRepository;
        this.userRepository = userRepository;
        this.questItemRepository = questItemRepository;
        this.mapAreaActionRepository = mapAreaActionRepository;
        this.combatRepository = combatRepository;
        this.opponentRepository = opponentRepository;
        this.itemRepository = itemRepository;
        this.playerItemRepository = playerItemRepository;
    }

    public PlayerRepository getPlayerRepository() {
        return playerRepository;
    }

    public MapAreaRepository getMapAreaRepository() {
        return mapAreaRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public QuestItemRepository getQuestItemRepository() {
        return questItemRepository;
    }

    public MapAreaActionRepository getMapAreaActionRepository() {
        return mapAreaActionRepository;
    }

    public CombatRepository getCombatRepository() {
        return combatRepository;
    }

    public OpponentRepository getOpponentRepository() {
        return opponentRepository;
    }

    public ItemRepository getItemRepository() {
        return itemRepository;
    }

    public PlayerItemRepository getPlayerItemRepository() {
        return playerItemRepository;
    }
}
