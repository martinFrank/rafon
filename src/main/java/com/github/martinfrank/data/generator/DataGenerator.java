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

            logger.info("... generating 2 User entities...");
            User user = new User();
            user.setName("John Normal");
            user.setUsername("user");
            user.setHashedPassword(passwordEncoder.encode("user"));
            user.setProfilePictureUrl(
                    "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80");
            user.setRoles(Collections.singleton(Role.USER));
            repositoryService.getUserRepository().save(user);
            User admin = new User();
            admin.setName("John Normal");
            admin.setUsername("admin");
            admin.setHashedPassword(passwordEncoder.encode("admin"));
            admin.setProfilePictureUrl(
                    "https://images.unsplash.com/photo-1607746882042-944635dfe10e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80");
            admin.setRoles(Collections.singleton(Role.ADMIN));
            repositoryService.getUserRepository().save(admin);

            //my user
            User martinUser = new User();
            martinUser.setName("martin");
            martinUser.setUsername("martin");
            martinUser.setHashedPassword(passwordEncoder.encode("martin"));
            martinUser.setProfilePictureUrl(
                    "https://images.unsplash.com/photo-1607746882042-944635dfe10e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80");
            martinUser.setRoles(Collections.singleton(Role.USER));
            repositoryService.getUserRepository().save(martinUser);

            Player martinPlayer = new Player();
            martinPlayer.setDisplayName("[M@rtin]");
            martinPlayer.setUser(martinUser);

            MapArea city = new MapArea();
            city.setMapAreaName("city");
            martinPlayer.setCurrentArea(city);
            repositoryService.getMapAreaRepository().save(city);

            repositoryService.getPlayerRepository().save(martinPlayer);

            //another user
            User mrxUser = new User();
            mrxUser.setName("mrx");
            mrxUser.setUsername("mrx");
            mrxUser.setHashedPassword(passwordEncoder.encode("mrx"));
            mrxUser.setProfilePictureUrl(
                    "https://images.unsplash.com/photo-1607746882042-944635dfe10e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80");
            mrxUser.setRoles(Collections.singleton(Role.USER));
            repositoryService.getUserRepository().save(mrxUser);

            Player mrxPlayer = new Player();
            mrxPlayer.setDisplayName("Mr. X");
            mrxPlayer.setUser(mrxUser);

            mrxPlayer.setCurrentArea(city);

            repositoryService.getPlayerRepository().save(mrxPlayer);

            //quest items
            QuestItem mapOfTheArea = new QuestItem();
            mapOfTheArea.setName("map of the area");
            Set<MapArea> areas = new HashSet<>();
            areas.add(city);
            mapOfTheArea.setGrantedAccess(areas);
            repositoryService.getQuestItemRepository().save(mapOfTheArea);

            Set<QuestItem> questItems = new HashSet<>();
            questItems.add(mapOfTheArea);
            martinPlayer.setQuestItems(questItems);
            repositoryService.getPlayerRepository().save(martinPlayer);

            logger.info("Generated demo data");
        };
    }

}