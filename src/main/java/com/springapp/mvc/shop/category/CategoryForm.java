package com.springapp.mvc.shop.category;

import com.springapp.mvc.data.shop.CategoryPagingRepository;
import com.springapp.mvc.model.shop.Category;
import com.vaadin.event.ShortcutAction;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by Иван on 17.12.2016.
 */
@SpringUI
public class CategoryForm extends FormLayout {
    private CategoryPagingRepository repository;

    private Button okButton = new Button("OK", new Button.ClickListener() {
        @Override
        public void buttonClick(Button.ClickEvent event) {
            save(event);
        }
    });

    private Button cancelButton = new Button("Cancel", new Button.ClickListener() {
        @Override
        public void buttonClick(Button.ClickEvent event) {
            cancel(event);
        }
    });

    private TextField nameField = new TextField("Name");
    private TextArea descriptionField = new TextArea("Description");

    private Category category;
    private CategoryLayout layout;

    public CategoryForm(CategoryPagingRepository repository, CategoryLayout layout) {
        configureComponents();
        buildLayout();

        this.repository = repository;
        this.layout = layout;
    }

    private void configureComponents() {
        okButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
        okButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        setVisible(false);
    }

    private void buildLayout() {
        setSizeUndefined();
        setMargin(true);

        HorizontalLayout actions = new HorizontalLayout(okButton, cancelButton);
        actions.setSpacing(true);

        addComponents(actions, nameField, descriptionField);
    }

    private void save(Button.ClickEvent event) {
        category.setName(nameField.getValue());
        category.setDescription(descriptionField.getValue());
        repository.save(category);
        String msg = String.format("Saved '%s'.", category.getName());
        Notification.show(msg, Notification.Type.TRAY_NOTIFICATION);
        layout.switchForm(false);
    }

    private void cancel(Button.ClickEvent cancel) {
        Notification.show("Cancelled", Notification.Type.TRAY_NOTIFICATION);
        layout.switchForm(false);
    }

    public void edit(Category category) {
        this.category = category;
        if (category != null) {
            nameField.setValue(category.getName());
            descriptionField.setValue(category.getDescription());
            nameField.focus();
        }
        setVisible(category != null);
    }
}
