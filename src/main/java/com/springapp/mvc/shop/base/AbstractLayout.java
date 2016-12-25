package com.springapp.mvc.shop.base;

import com.springapp.mvc.context.SpringContextHelper;
import com.vaadin.event.FieldEvents;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Иван on 25.12.2016.
 */
@SpringUI
public abstract class AbstractLayout extends VerticalLayout {
    protected TextField filter = new TextField();
    protected Grid list = new Grid();
    protected Button createBtn = new Button("Create");
    protected Button removeBtn = new Button("Remove");
    protected Button editBtn = new Button("Edit");

    protected PagingAndSortingRepository repository;
    protected AbstractForm form;
    protected SpringContextHelper helper;

    protected AbstractLayout(SpringContextHelper contextHelper, Class repositoryCls) {
        this.repository = (PagingAndSortingRepository) contextHelper.getBean(repositoryCls);
        this.helper = contextHelper;

        initForm();
        configureComponents();
        buildLayout();
    }

    protected void configureComponents() {
        createBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                doCreate(event);
            }
        });
        removeBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                doRemove(event);
            }
        });
        editBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                doEdit(event);
            }
        });

        filter.setInputPrompt("Filter contacts ...");
        filter.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(FieldEvents.TextChangeEvent event) {
                refresh(event.getText());
            }
        });
    }

    protected void buildLayout() {
        HorizontalLayout actions = new HorizontalLayout(filter, createBtn, removeBtn, editBtn);
        actions.setWidth("100%");
        filter.setWidth("100%");
        actions.setExpandRatio(filter, 1);

        VerticalLayout left = new VerticalLayout(actions, list);
        left.setSizeFull();
        list.setSizeFull();
        left.setExpandRatio(list, 1);

        HorizontalLayout mainLayout = new HorizontalLayout(left, form);
        mainLayout.setSizeFull();
        mainLayout.setExpandRatio(left, 1);

        addComponent(mainLayout);
    }

    protected abstract void initForm();

    protected abstract void doEdit(Button.ClickEvent event);

    protected abstract void doCreate(Button.ClickEvent event);

    protected abstract void doRemove(Button.ClickEvent event);

    protected abstract void refresh(String filterText);

    public void switchForm(boolean visible, boolean refresh) {
        form.setVisible(visible);
        if (refresh) {
            refresh(filter.getValue());
        }
    }
}
