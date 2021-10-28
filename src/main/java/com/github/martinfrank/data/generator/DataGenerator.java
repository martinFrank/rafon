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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@SpringComponent
public class DataGenerator {

    private static final String IMG_URL = "https://images.unsplash.com/photo-1607746882042-944635dfe10e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80";
    private PasswordEncoder passwordEncoder;



    @Bean
    public CommandLineRunner loadData(PasswordEncoder passwordEncoder, RepositoryService repositoryService) {
        this.passwordEncoder = passwordEncoder;
        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            if (repositoryService.getUserRepository().count() != 0L) {
                logger.info("Using existing database");
                return;
            }
            int seed = 123;

            logger.info("Generating demo data");
            logger.info("... generating area entities...");

            MapArea world = new MapArea();
            world.setName("world");
            repositoryService.getMapAreaRepository().save(world);

            MapArea city = new MapArea();
            city.setName("city");
            repositoryService.getMapAreaRepository().save(city);

            MapArea home = new MapArea();
            home.setName("home");
            repositoryService.getMapAreaRepository().save(home);

            MapArea meadow = new MapArea();
            meadow.setName("meadow");
            repositoryService.getMapAreaRepository().save(meadow);

            MapArea shadySideAlley = new MapArea();
            shadySideAlley.setName("shady side alley");
            repositoryService.getMapAreaRepository().save(shadySideAlley);

            Set<MapArea> worldSubAreas = new HashSet<>();
            worldSubAreas.add(city);
            worldSubAreas.add(home);
            world.setSubMapAreas(worldSubAreas);
            repositoryService.getMapAreaRepository().save(world);

            Set<MapArea> citySubAreas = new HashSet<>();
            citySubAreas.add(world);
            citySubAreas.add(shadySideAlley);
            citySubAreas.add(meadow);
            city.setSubMapAreas(citySubAreas);
            repositoryService.getMapAreaRepository().save(city);

            Set<MapArea> homeSubAreas = new HashSet<>();
            homeSubAreas.add(world);
            home.setSubMapAreas(homeSubAreas);
            repositoryService.getMapAreaRepository().save(home);

            Set<MapArea> meadowSubAreas = new HashSet<>();
            meadowSubAreas.add(city);
            meadow.setSubMapAreas(meadowSubAreas);
            repositoryService.getMapAreaRepository().save(meadow);

            Set<MapArea> shadySideAlleySubAreas = new HashSet<>();
            shadySideAlleySubAreas.add(city);
            shadySideAlley.setSubMapAreas(shadySideAlleySubAreas);
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
            martinPlayer.setCurrentArea(world);

            repositoryService.getPlayerRepository().save(martinPlayer);
            repositoryService.getUserRepository().save(martinUser);

            //another user
            User mrxUser = createUser("mrx",  "mrx", "mrx",IMG_URL, Role.USER);
            Player mrxPlayer = createPlayer("Mr. X", mrxUser, 20L, 20L);
            mrxPlayer.setCurrentArea(world);

            repositoryService.getPlayerRepository().save(mrxPlayer);
            repositoryService.getUserRepository().save(mrxUser);

            logger.info("... generating quest item entities...");

            //quest items
            QuestItem mapOfTheArea = new QuestItem();
            mapOfTheArea.setName("map of the world");
            Set<MapArea> areas = new HashSet<>();
            areas.add(world);
            areas.add(city);
            areas.add(shadySideAlley);
            areas.add(home);
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

            logger.info("Generated demo data");
        };
    }

    private Player createPlayer(String displayName, User user, long life, long endurance) {
        Player player = new Player();
        player.setDisplayName(displayName);
        player.setUser(user);
        player.setCurrentLife(life);
        player.setMaxLife(life);
        player.setMaxEndurance(endurance);
        player.setCurrentEndurance(endurance);
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