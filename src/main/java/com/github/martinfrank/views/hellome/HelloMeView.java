package com.github.martinfrank.views.hellome;

import com.github.martinfrank.data.entity.MapArea;
import com.github.martinfrank.data.entity.Player;
import com.github.martinfrank.data.entity.PlayerItem;
import com.github.martinfrank.data.entity.User;
import com.github.martinfrank.data.service.PlayerRepository;
import com.github.martinfrank.data.service.RepositoryService;
import com.github.martinfrank.data.service.UserRepository;
import com.github.martinfrank.views.MainLayout;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.security.PermitAll;

@PageTitle("Hello Me")
@Route(value = "character", layout = MainLayout.class)
@PermitAll
public class HelloMeView extends VerticalLayout {

//    private final TextField inventoryText;

    public static final String HOME_MAP_AREA_NAME = "character";

//    private final RepositoryService service;
//    private final Player currentPlayer;

    public HelloMeView(RepositoryService service) {
//        this.service = service;
        Player currentPlayer = getCurrentPlayer(service.getUserRepository(), service.getPlayerRepository());
        MapArea homeMapArea = service.getMapAreaRepository().findByName(HOME_MAP_AREA_NAME);
        currentPlayer.setCurrentArea(homeMapArea);
        service.getPlayerRepository().save(currentPlayer);

        setMargin(true);
        TextField inventoryText = new TextField("my inventory");
        inventoryText.setValue(currentPlayer.getDisplayName());
        inventoryText.setReadOnly(true);
        add(inventoryText);

        if(!currentPlayer.getInventoryItems().isEmpty()){
            add(new Hr());
            for(PlayerItem playerItem: currentPlayer.getInventoryItems()){
                add(new Text(playerItem.getItem().getName()));
            }
        }


        if(currentPlayer.getCombat() != null){
            Notification.show("you are in a battle and cannot go home");
        }
    }

    private Player getCurrentPlayer(UserRepository userRepository, PlayerRepository playerRepository) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        User user = userRepository.findByUsername(principal.getUsername());
        return playerRepository.findByUser(user);
    }

}
