
package com.github.martinfrank.views.helloworld;

import com.github.martinfrank.data.entity.MapArea;
import com.github.martinfrank.data.entity.Player;
import com.github.martinfrank.data.entity.User;
import com.github.martinfrank.data.service.PlayerRepository;
import com.github.martinfrank.data.service.RepositoryService;
import com.github.martinfrank.data.service.UserRepository;
import com.github.martinfrank.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.security.PermitAll;

@PageTitle("Hello World")
@Route(value = "world", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PermitAll
public class HelloWorldView extends VerticalLayout {

    private TextField location;
    private Player currentPlayer;
    private RepositoryService service;


    public HelloWorldView(RepositoryService service) {
        this.service = service;
        currentPlayer = getCurrentPlayer(service.getUserRepository(), service.getPlayerRepository());

        setMargin(true);
        location = new TextField("Your are here:");
        location.setValue(currentPlayer.getCurrentArea().getMapAreaName());
        add(location);

        for(MapArea mapArea: currentPlayer.getCurrentArea().getSubMapAreas()){
            Button button = new Button(mapArea.getMapAreaName());
            button.addClickListener(e -> travelTo(mapArea));
            add(button);
        }

//        String test = service == null?"null":"service.user"+service.getUserRepository();
//        name.setValue(""+service);
//        sayHello = new Button("Say hello");
//        add(name, sayHello);
//        setVerticalComponentAlignment(Alignment.END, name, sayHello);
//        sayHello.addClickListener(e -> {
//            Notification.show("Hello " + name.getValue());
//        });


    }

    private void travelTo(MapArea mapArea) {
        currentPlayer.setCurrentArea(mapArea);
        service.getPlayerRepository().save(currentPlayer);
        UI.getCurrent().getPage().reload();
    }

    private Player getCurrentPlayer(UserRepository userRepository, PlayerRepository playerRepository) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        User user = userRepository.findByUsername(principal.getUsername());
        return playerRepository.findByUser(user);
    }

}
