package com.github.martinfrank.views.hellohome;

import com.github.martinfrank.data.entity.MapArea;
import com.github.martinfrank.data.entity.Player;
import com.github.martinfrank.data.entity.User;
import com.github.martinfrank.data.service.PlayerRepository;
import com.github.martinfrank.data.service.RepositoryService;
import com.github.martinfrank.data.service.UserRepository;
import com.github.martinfrank.views.MainLayout;
import com.github.martinfrank.views.helloworld.HelloWorldView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.security.PermitAll;

@PageTitle("Hello Home")
@Route(value = "home", layout = MainLayout.class)
@PermitAll
public class HelloHomeView extends HorizontalLayout {

    private TextField homeText;

    public static final String HOME_MAP_AREA_NAME = "home";

    private final RepositoryService service;
    private final Player currentPlayer;

    public HelloHomeView(RepositoryService service) {
        this.service = service;
        currentPlayer = getCurrentPlayer(service.getUserRepository(), service.getPlayerRepository());
        MapArea homeMapArea = service.getMapAreaRepository().findByName(HOME_MAP_AREA_NAME);
        currentPlayer.setCurrentArea(homeMapArea);
        service.getPlayerRepository().save(currentPlayer);

        setMargin(true);
        homeText = new TextField("You are home again");
        homeText.setValue(currentPlayer.getDisplayName());
        homeText.setReadOnly(true);
        add(homeText);

        if(currentPlayer.getCombat() != null){
            Notification.show("you are in combat and cannot go home - you are being brought back to combat...");
            new Thread(() -> {
                try {
                    Thread.sleep(500);
                    navigateToWorld();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private void navigateToWorld() {
        UI.getCurrent().navigate(HelloWorldView.WORLD_MAP_AREA_NAME);
    }

    private Player getCurrentPlayer(UserRepository userRepository, PlayerRepository playerRepository) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        User user = userRepository.findByUsername(principal.getUsername());
        return playerRepository.findByUser(user);
    }

}
