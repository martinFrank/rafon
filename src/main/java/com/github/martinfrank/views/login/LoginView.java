package com.github.martinfrank.views.login;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import org.springframework.web.util.UriUtils;
import java.nio.charset.StandardCharsets;
import com.vaadin.flow.component.html.Label;
import java.io.ByteArrayOutputStream;
import elemental.json.Json;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.textfield.TextField;
import java.util.Base64;

@PageTitle("Login")
@Route(value = "login")
public class LoginView extends LoginOverlay {
    public LoginView() {
        setAction("login");

        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("RaFoN");
        i18n.getHeader().setDescription("Login using user/user or admin/admin");
        i18n.setAdditionalInformation(null);
        setI18n(i18n);

        setForgotPasswordButtonVisible(false);
        setOpened(true);
    }

}
