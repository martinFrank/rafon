package com.github.martinfrank.views.hellome;

import com.github.martinfrank.data.entity.Player;
import com.github.martinfrank.data.entity.PlayerItem;
import com.github.martinfrank.data.entity.User;
import com.github.martinfrank.data.service.PlayerRepository;
import com.github.martinfrank.data.service.RepositoryService;
import com.github.martinfrank.data.service.UserRepository;
import com.github.martinfrank.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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


    public HelloMeView(RepositoryService service) {
        Player currentPlayer = getCurrentPlayer(service.getUserRepository(), service.getPlayerRepository());

        setMargin(true);
//        TextField inventoryText = new TextField("my inventory");
//        inventoryText.setValue(currentPlayer.getDisplayName());
//        inventoryText.setReadOnly(true);
//        add(inventoryText);
//
//        add(new Hr());
        add(createProfile(currentPlayer));

        if(!currentPlayer.getPlayerItems().isEmpty()){
            add(new Hr());
            for(PlayerItem playerItem: currentPlayer.getPlayerItems()){
                add(new Button(playerItem.getItem().toString()));
            }
        }


        if(currentPlayer.getCombat() != null){
            Notification.show("you are in a battle and cannot go home");
        }
    }

    private HorizontalLayout createProfile(Player currentPlayer) {
        Image image = new Image(currentPlayer.getUser().getProfilePictureUrl(), "DummyImage");

        //FIXME Data binding (at least one-directional)
        FormLayout layoutWithFormItems = new FormLayout();

        TextField title = new TextField();
        title.setValue("Beginner");
        title.setReadOnly(true);

        TextField name = new TextField();
        name.setValue(currentPlayer.getDisplayName());
        name.setReadOnly(true);

        TextField life = new TextField();
        life.setValue(currentPlayer.getCurrentLife()+" / "+currentPlayer.getMaxLife());
        life.setReadOnly(true);

        TextField endurance = new TextField();
        endurance.setValue(currentPlayer.getCurrentEndurance()+" / "+currentPlayer.getMaxEndurance());
        endurance.setReadOnly(true);

        TextField intelligence = new TextField();
        intelligence.setValue(""+currentPlayer.getIntelligence());
        intelligence.setReadOnly(true);

        TextField dexterity = new TextField();
        dexterity.setValue(""+currentPlayer.getDexterity());
        dexterity.setReadOnly(true);

        TextField strength = new TextField();
        strength.setValue(""+currentPlayer.getStrength());
        strength.setReadOnly(true);

        layoutWithFormItems.addFormItem(title, "title");
        layoutWithFormItems.addFormItem(name, "name");

        layoutWithFormItems.addFormItem(life, "life");
        layoutWithFormItems.addFormItem(endurance, "endurance");

        layoutWithFormItems.addFormItem(intelligence, "intelligence");
        layoutWithFormItems.addFormItem(dexterity, "dexterity");
        layoutWithFormItems.addFormItem(strength, "strength");

        return new HorizontalLayout(image, layoutWithFormItems);
    }

    private Player getCurrentPlayer(UserRepository userRepository, PlayerRepository playerRepository) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        User user = userRepository.findByUsername(principal.getUsername());
        return playerRepository.findByUser(user);
    }

}
