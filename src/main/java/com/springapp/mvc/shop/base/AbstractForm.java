package com.springapp.mvc.shop.base;

import com.springapp.mvc.model.StandardEntity;
import com.vaadin.event.ShortcutAction;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by ���� on 25.12.2016.
 */
@SpringUI
public abstract class AbstractForm extends FormLayout {
    protected PagingAndSortingRepository repository;

    protected Button okButton = new Button("OK", new Button.ClickListener() {
        @Override
        public void buttonClick(Button.ClickEvent event) {
            save(event);
        }
    });

    protected Button cancelButton = new Button("Cancel", new Button.ClickListener() {
        @Override
        public void buttonClick(Button.ClickEvent event) {
            cancel(event);
        }
    });

    protected StandardEntity item;
    protected AbstractLayout layout;

    public AbstractForm(PagingAndSortingRepository repository, AbstractLayout layout) {
        this.repository = repository;
        this.layout = layout;
    }

    protected void buildLayout() {
        setSizeUndefined();
        setMargin(true);

        HorizontalLayout actions = new HorizontalLayout(okButton, cancelButton);
        actions.setSpacing(true);

        addComponents(actions);
    }

    protected void configureComponents() {
        okButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
        okButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        setVisible(false);
    }

    protected abstract void save(Button.ClickEvent event);

    protected abstract void cancel(Button.ClickEvent event);

    public abstract void edit(StandardEntity item);
}
