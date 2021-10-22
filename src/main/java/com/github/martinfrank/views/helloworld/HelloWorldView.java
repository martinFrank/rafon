package com.github.martinfrank.views.helloworld;

import com.github.martinfrank.data.entity.User;
import com.github.martinfrank.data.service.UserRepository;
import com.github.martinfrank.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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
public class HelloWorldView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;
    private Button nameUser;
    private final UserRepository userRepository;
    private final Authentication auth;

    public HelloWorldView(UserRepository userRepository, User currentUser) {
        this.userRepository = userRepository;
        auth = SecurityContextHolder.getContext().getAuthentication();

        setMargin(true);
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        nameUser = new Button("reveal your name");
        add(name, sayHello, nameUser);
        setVerticalComponentAlignment(Alignment.END, name, sayHello,nameUser);
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });

        nameUser.addClickListener(e -> {
            if (auth != null) {
                org.springframework.security.core.userdetails.User user =
                        (org.springframework.security.core.userdetails.User) auth.getPrincipal();
                User user2 = userRepository.findByUsername(user.getUsername());
                name.setValue(user2.getName()+" "+user2.getId() );
            }
        });

    }

}
