
package com.github.martinfrank.views.helloworld;

import com.github.martinfrank.data.entity.MapArea;
import com.github.martinfrank.data.entity.Player;
import com.github.martinfrank.data.entity.QuestItem;
import com.github.martinfrank.data.entity.User;
import com.github.martinfrank.data.service.PlayerRepository;
import com.github.martinfrank.data.service.RepositoryService;
import com.github.martinfrank.data.service.UserRepository;
import com.github.martinfrank.views.MainLayout;
import com.github.martinfrank.views.hellohome.HelloHomeView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
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
        location.setValue(currentPlayer.getCurrentArea().getMapAreaName());
        add(location);
        Set<MapArea> filteredAreas = filterMapAreasByQuestItem();
        for (MapArea mapArea : currentPlayer.getCurrentArea().getSubMapAreas()) {
            Button button = new Button(mapArea.getMapAreaName());
            button.addClickListener(e -> travelTo(mapArea));
            add(button);
        }
    }

    private Set<MapArea> filterMapAreasByQuestItem() {
        Set<MapArea> areas = currentPlayer.getCurrentArea().getSubMapAreas();
        Set<MapArea> grantedByQuestItems = getGrantedAreas();
        LOGGER.debug("---- SET OF AREAS: {} ----",areas);
        LOGGER.debug("---- SET OF ITEMS: {} ----",grantedByQuestItems);
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
        if (HelloHomeView.HOME_MAP_AREA_NAME.equals(mapArea.getMapAreaName())) {
            UI.getCurrent().navigate("home");
            return;
        }

        MapArea destiny = service.getMapAreaRepository().findByMapAreaName(mapArea.getMapAreaName());//initiate lazy loading
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
