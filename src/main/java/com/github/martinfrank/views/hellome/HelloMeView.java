package com.github.martinfrank.views.hellome;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.github.martinfrank.views.MainLayout;
import javax.annotation.security.PermitAll;

@PageTitle("Hello Me")
@Route(value = "character", layout = MainLayout.class)
@PermitAll
public class HelloMeView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;

    public HelloMeView() {
        setMargin(true);
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        add(name, sayHello);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });
    }

}
