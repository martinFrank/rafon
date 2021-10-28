
package com.github.martinfrank.views.helloworld;

import com.github.martinfrank.data.entity.*;
import com.github.martinfrank.data.service.PlayerRepository;
import com.github.martinfrank.data.service.RepositoryService;
import com.github.martinfrank.data.service.UserRepository;
import com.github.martinfrank.views.MainLayout;
import com.github.martinfrank.views.hellohome.HelloHomeView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.security.PermitAll;
import java.util.HashSet;
import java.util.Set;

@PageTitle("Hello World")
@Route(value = "world", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PermitAll
public class HelloWorldView extends VerticalLayout {

    public static final String WORLD_MAP_AREA_NAME = "world";

    private final TextField location;
    private final Player currentPlayer;
    private final RepositoryService service;
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldView.class);

    public HelloWorldView(RepositoryService service) {
        this.service = service;
        currentPlayer = getCurrentPlayer(service.getUserRepository(), service.getPlayerRepository());
        setMargin(true);
        location = new TextField("Your are here:");
        location.setReadOnly(true);

        recreatePage();
    }

    private void recreatePage() {
        removeAll();
        location.setValue(currentPlayer.getCurrentArea().getName());
        add(location);

        if(currentPlayer.getCombat() != null){
            displayCombat();
        }else{
            displayMapAreas();
        }

    }

    private void displayCombat() {
        add(new Text("you are fighting "+currentPlayer.getCombat().getOpponent().getName()));

        //TODO: add buttons for actions
        Button attackButton = new Button("attack");
        attackButton.addClickListener(e -> attack());
        add(attackButton);
    }

    private void attack() {
        Notification.show("you attacked "+currentPlayer.getCombat().getOpponent().getName());
    }

    private void displayMapAreas() {
        Set<MapAreaAction> mapAreaActions = currentPlayer.getCurrentArea().getAreaActions();
        if(!mapAreaActions.isEmpty()){
            add(new Hr());
        }
        for (MapAreaAction mapAreaAction : mapAreaActions) {
            Button button = new Button(mapAreaAction.getName());
            button.addClickListener(e -> executeAction(mapAreaAction));
            add(button);
        }
        Set<MapArea> filteredAreas = filterMapAreasByQuestItem();
        if(!filteredAreas.isEmpty()){
            add(new Hr());
        }
        for (MapArea mapArea : filteredAreas) {
            Button button = new Button(mapArea.getName());
            button.addClickListener(e -> travelTo(mapArea));
            add(button);
        }
    }

    private void executeAction(MapAreaAction mapAreaAction) {
//        Notification.show("execute action "+mapAreaAction.getName()+": coming soon...");
        Combat combat = new Combat();
        Opponent shadyGuy = service.getOpponentRepository().findByName("shady guy");
        combat.setOpponent(shadyGuy);
        combat.setOpponentLife(shadyGuy.getMaxLife());
//        service.getCombatRepository().save(combat);
        currentPlayer.setCombat(combat);
        service.getPlayerRepository().save(currentPlayer);
        Notification.show("execute action "+mapAreaAction.getName()+": a new combat, you vs. "+shadyGuy.getName());
        recreatePage();

    }

    private Set<MapArea> filterMapAreasByQuestItem() {
        Set<MapArea> areas = currentPlayer.getCurrentArea().getSubMapAreas();
        Set<MapArea> grantedByQuestItems = getGrantedAreas();
        Set<MapArea> merge = new HashSet<>();
        for(MapArea area: areas){
            if(grantedByQuestItems.contains(area)){
                merge.add(area);
            }
        }
        return merge;
    }

    private Set<MapArea> getGrantedAreas() {
        Set<MapArea> granted = new HashSet<>();
        for(QuestItem qi: currentPlayer.getQuestItems()) {
            granted.addAll(qi.getGrantedAccess());
        }
        return granted;
    }

    private void travelTo(MapArea mapArea) {
        if (HelloHomeView.HOME_MAP_AREA_NAME.equals(mapArea.getName())) {
            UI.getCurrent().navigate(HelloHomeView.HOME_MAP_AREA_NAME);
            return;
        }

        MapArea destiny = service.getMapAreaRepository().findByName(mapArea.getName());//initiate lazy loading
        currentPlayer.setCurrentArea(destiny);
        service.getPlayerRepository().save(currentPlayer);
        recreatePage();
    }

    private Player getCurrentPlayer(UserRepository userRepository, PlayerRepository playerRepository) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        User user = userRepository.findByUsername(principal.getUsername());
        return playerRepository.findByUser(user);
    }

}
