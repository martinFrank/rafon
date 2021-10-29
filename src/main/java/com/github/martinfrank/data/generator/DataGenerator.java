package com.github.martinfrank.data.generator;

import com.github.martinfrank.data.Role;
import com.github.martinfrank.data.entity.*;
import com.github.martinfrank.data.service.RepositoryService;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@SpringComponent
public class DataGenerator {

    private static final String IMG_URL = "https://images.unsplash.com/photo-1607746882042-944635dfe10e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80";
    private PasswordEncoder passwordEncoder;
    private RepositoryService repositoryService;

    private MapArea world;
    private MapArea city;
    private MapArea home;
    private MapArea meadow;
    private MapArea shadySideAlley;

    @Bean
    public CommandLineRunner loadData(PasswordEncoder passwordEncoder, RepositoryService repositoryService) {
        this.passwordEncoder = passwordEncoder;
        this.repositoryService = repositoryService;
        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            if (repositoryService.getUserRepository().count() != 0L) {
                logger.info("Using existing database");
                return;
            }
            int seed = 123;

            logger.info("Generating demo data");
            logger.info("... generating area entities...");

            world = createAndSaveMapArea("world");
            city = createAndSaveMapArea("city");
            home = createAndSaveMapArea("home");
            meadow = createAndSaveMapArea("meadow");
            shadySideAlley = createAndSaveMapArea("shady side alley");

            world.setSubMapAreas(createMapAreaSet(city, home));
            repositoryService.getMapAreaRepository().save(world);

            city.setSubMapAreas(createMapAreaSet(world, shadySideAlley, meadow));
            repositoryService.getMapAreaRepository().save(city);

            home.setSubMapAreas(createMapAreaSet(world));
            repositoryService.getMapAreaRepository().save(home);

            meadow.setSubMapAreas(createMapAreaSet(city));
            repositoryService.getMapAreaRepository().save(meadow);

            shadySideAlley.setSubMapAreas(createMapAreaSet(city));
            repositoryService.getMapAreaRepository().save(shadySideAlley);

            //actions
            MapAreaAction harvestAction = new MapAreaAction();
            harvestAction.setName("harvest");
            repositoryService.getMapAreaActionRepository().save(harvestAction);

            Set<MapAreaAction> meadowActions = new HashSet<>();
            meadowActions.add(harvestAction);
            meadow.setAreaActions(meadowActions);
            repositoryService.getMapAreaRepository().save(meadow);

            MapAreaAction lookingForTroubleAction = new MapAreaAction();
            lookingForTroubleAction.setName("looking for trouble");
            repositoryService.getMapAreaActionRepository().save(lookingForTroubleAction);

            Set<MapAreaAction> sideAlleyActions = new HashSet<>();
            sideAlleyActions.add(lookingForTroubleAction);
            shadySideAlley.setAreaActions(sideAlleyActions);
            repositoryService.getMapAreaRepository().save(shadySideAlley);

            logger.info("... generating 2 User entities...");
            //my user
            User martinUser = createUser("martin","martin","martin",IMG_URL, Role.USER);
            Player martinPlayer = createPlayer("[M@rtin]", martinUser, 20L, 20L);
            repositoryService.getPlayerRepository().save(martinPlayer);
            repositoryService.getUserRepository().save(martinUser);

            //another user
            User mrxUser = createUser("mrx",  "mrx", "mrx",IMG_URL, Role.USER);
            Player mrxPlayer = createPlayer("Mr. X", mrxUser, 20L, 20L);
            repositoryService.getPlayerRepository().save(mrxPlayer);
            repositoryService.getUserRepository().save(mrxUser);

            logger.info("... generating quest item entities...");

            //quest items
            QuestItem mapOfTheArea = new QuestItem();
            mapOfTheArea.setName("map of the world");
            Set<MapArea> areas = createMapAreaSet(world, city, shadySideAlley, home);
            mapOfTheArea.setGrantedAccess(areas);
            repositoryService.getQuestItemRepository().save(mapOfTheArea);

            Set<QuestItem> questItems = new HashSet<>();
            questItems.add(mapOfTheArea);
            martinPlayer.setQuestItems(questItems);
            repositoryService.getPlayerRepository().save(martinPlayer);

            Set<QuestItem> questItems2 = new HashSet<>();
            questItems2.add(mapOfTheArea);
            mrxPlayer.setQuestItems(questItems2);
            repositoryService.getPlayerRepository().save(mrxPlayer);

            Opponent shadyGuy = new Opponent();
            shadyGuy.setName("shady guy");
            shadyGuy.setMaxLife(20);
            repositoryService.getOpponentRepository().save(shadyGuy);

            Item butterflyKnife = new Item();
            butterflyKnife.setName("butterfly knife");
            repositoryService.getItemRepository().save(butterflyKnife);

            PlayerItem playerItem = new PlayerItem();
            playerItem.setItem(butterflyKnife);
            playerItem.setPlayer(martinPlayer);
            Set<PlayerItem> playerItems = martinPlayer.getPlayerItems();
            if (playerItems == null){
                playerItems = new HashSet<>();
                martinPlayer.setPlayerItems(playerItems);
            }
            playerItems.add(playerItem);
            repositoryService.getPlayerItemRepository().save(playerItem);
            repositoryService.getPlayerRepository().save(martinPlayer);

            logger.info("Generated demo data");
        };
    }

    private Set<MapArea> createMapAreaSet(MapArea... areas) {
        Set<MapArea> subAreas = new HashSet<>();
        subAreas.addAll(Arrays.asList(areas));
        return subAreas;
    }

    private MapArea createAndSaveMapArea(String name) {
        MapArea area = new MapArea();
        area.setName(name);
        repositoryService.getMapAreaRepository().save(area);
        return area;
    }

    private Player createPlayer(String displayName, User user, long life, long endurance) {
        Player player = new Player();
        player.setDisplayName(displayName);
        player.setUser(user);
        player.setCurrentLife(life);
        player.setMaxLife(life);
        player.setMaxEndurance(endurance);
        player.setCurrentEndurance(endurance);
        player.setCurrentArea(world);
        return player;
    }

    private User createUser(String name, String username, String pass, String imageUrl, Role role) {
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setHashedPassword(passwordEncoder.encode(pass));
        user.setProfilePictureUrl(imageUrl);
        user.setRoles(Collections.singleton(role));
        return user;
    }

}