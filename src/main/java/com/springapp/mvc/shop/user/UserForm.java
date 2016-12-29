package com.springapp.mvc.shop.user;

import com.springapp.mvc.context.SpringContextHelper;
import com.springapp.mvc.model.StandardEntity;
import com.springapp.mvc.model.shop.User;
import com.springapp.mvc.shop.base.AbstractForm;
import com.springapp.mvc.shop.base.AbstractLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Иван on 27.12.2016.
 */
public class UserForm extends AbstractForm {
    private TextField nameField = new TextField("Name");
    private TextField surnameField = new TextField("Surname");
    private TextField middleNameField = new TextField("Middle name");
    private TextField loginField = new TextField("Login");
    private PasswordField passwordField = new PasswordField("Password");

    public UserForm(PagingAndSortingRepository repository, AbstractLayout layout, SpringContextHelper helper) {
        super(repository, layout, helper);

        configureComponents();
        buildLayout();
    }

    @Override
    protected void buildLayout() {
        super.buildLayout();

        addComponents(nameField, surnameField, middleNameField, loginField, passwordField);
    }

    @Override
    protected void save(Button.ClickEvent event) {
        User user = (User) item;
        user.setName(nameField.getValue());
        user.setSurname(surnameField.getValue());
        user.setMiddleName(middleNameField.getValue());
        user.setLogin(loginField.getValue());
        user.setPassword(passwordField.getValue());
        repository.save(user);
        String msg = String.format("Saved '%s'.", user.getName());
        Notification.show(msg, Notification.Type.TRAY_NOTIFICATION);
        layout.switchForm(false, true);
    }

    @Override
    protected void cancel(Button.ClickEvent event) {
        Notification.show("Cancelled", Notification.Type.TRAY_NOTIFICATION);
        layout.switchForm(false, false);
    }

    @Override
    public void edit(StandardEntity item) {
        this.item = item;
        User user = (User) item;
        if (user != null) {
            nameField.setValue(user.getName() != null ? user.getName() : "");
            surnameField.setValue(user.getSurname() != null ? user.getSurname() : "");
            middleNameField.setValue(user.getMiddleName() != null ? user.getMiddleName() : "");
            loginField.setValue(user.getLogin() != null ? user.getPassword() : "");
            passwordField.setValue(user.getPassword() != null ? user.getPassword() : "");
            nameField.focus();
        }
        setVisible(user != null);
    }
}
