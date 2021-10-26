package com.github.martinfrank.data.generator;

import com.github.martinfrank.data.Role;
import com.github.martinfrank.data.entity.MapArea;
import com.github.martinfrank.data.entity.Player;
import com.github.martinfrank.data.entity.QuestItem;
import com.github.martinfrank.data.entity.User;
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

    @Bean
    public CommandLineRunner loadData(PasswordEncoder passwordEncoder,
//                                      UserRepository userRepository,
//                                      PlayerRepository playerRepository,
//                                      MapAreaRepository mapAreaRepository
                                      RepositoryService repositoryService
                                      ) {
        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            if (repositoryService.getUserRepository().count() != 0L) {
                logger.info("Using existing database");
                return;
            }
            int seed = 123;

            logger.info("Generating demo data");


//            User user = new User();
//            user.setName("John Normal");
//            user.setUsername("user");
//            user.setHashedPassword(passwordEncoder.encode("user"));
//            user.setProfilePictureUrl(
//                    "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80");
//            user.setRoles(Collections.singleton(Role.USER));
//            repositoryService.getUserRepository().save(user);
//            User admin = new User();
//            admin.setName("John Normal");
//            admin.setUsername("admin");
//            admin.setHashedPassword(passwordEncoder.encode("admin"));
//            admin.setProfilePictureUrl(
//                    "https://images.unsplash.com/photo-1607746882042-944635dfe10e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80");
//            admin.setRoles(Collections.singleton(Role.ADMIN));
//            repositoryService.getUserRepository().save(admin);
//            logger.info("done create two default user");

            logger.info("... generating area entities...");

            MapArea world = new MapArea();
            world.setMapAreaName("world");
            repositoryService.getMapAreaRepository().save(world);

            MapArea city = new MapArea();
            city.setMapAreaName("city");
            repositoryService.getMapAreaRepository().save(city);

            MapArea home = new MapArea();
            home.setMapAreaName("home");
            repositoryService.getMapAreaRepository().save(home);

            Set<MapArea> worldSubAreas = new HashSet<>();
            worldSubAreas.add(city);
            worldSubAreas.add(home);
            world.setSubMapAreas(worldSubAreas);
            repositoryService.getMapAreaRepository().save(world);

            Set<MapArea> citySubAreas = new HashSet<>();
            worldSubAreas.add(world);
            city.setSubMapAreas(citySubAreas);
            repositoryService.getMapAreaRepository().save(city);

            Set<MapArea> homeSubAreas = new HashSet<>();
            homeSubAreas.add(world);
            home.setSubMapAreas(homeSubAreas);
            repositoryService.getMapAreaRepository().save(home);


            logger.info("... generating 2 User entities...");
            //my user
            User martinUser = new User();
            martinUser.setName("martin");
            martinUser.setUsername("martin");
            martinUser.setHashedPassword(passwordEncoder.encode("martin"));
            martinUser.setProfilePictureUrl(
                    "https://images.unsplash.com/photo-1607746882042-944635dfe10e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80");
            martinUser.setRoles(Collections.singleton(Role.USER));
            //repositoryService.getUserRepository().save(martinUser);

            Player martinPlayer = new Player();
            martinPlayer.setDisplayName("[M@rtin]");
            martinPlayer.setUser(martinUser);

            martinPlayer.setCurrentArea(world);



            repositoryService.getPlayerRepository().save(martinPlayer);
            repositoryService.getUserRepository().save(martinUser);

            //another user
            User mrxUser = new User();
            mrxUser.setName("mrx");
            mrxUser.setUsername("mrx");
            mrxUser.setHashedPassword(passwordEncoder.encode("mrx"));
            mrxUser.setProfilePictureUrl(
                    "https://images.unsplash.com/photo-1607746882042-944635dfe10e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80");
            mrxUser.setRoles(Collections.singleton(Role.USER));


            Player mrxPlayer = new Player();
            mrxPlayer.setDisplayName("Mr. X");
            mrxPlayer.setUser(mrxUser);

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

            logger.info("Generated demo data");
        };
    }

}