package com.springapp.mvc.shop.category;

import com.springapp.mvc.model.StandardEntity;
import com.springapp.mvc.model.shop.Category;
import com.springapp.mvc.shop.base.AbstractForm;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Иван on 17.12.2016.
 */
@SpringUI
public class CategoryForm extends AbstractForm {
    private TextField nameField = new TextField("Name");
    private TextArea descriptionField = new TextArea("Description");

    public CategoryForm(PagingAndSortingRepository repository, com.springapp.mvc.shop.base.AbstractLayout layout) {
        super(repository, layout);

        configureComponents();
        buildLayout();
    }

    @Override
    protected void buildLayout() {
        super.buildLayout();
        addComponents(nameField, descriptionField);
    }

    @Override
    protected void save(Button.ClickEvent event) {
        Category category = (Category) item;
        category.setName(nameField.getValue());
        category.setDescription(descriptionField.getValue());
        repository.save(category);
        String msg = String.format("Saved '%s'.", category.getName());
        Notification.show(msg, Notification.Type.TRAY_NOTIFICATION);
        layout.switchForm(false, true);
    }

    @Override
    protected void cancel(Button.ClickEvent cancel) {
        Notification.show("Cancelled", Notification.Type.TRAY_NOTIFICATION);
        layout.switchForm(false, false);
    }

    @Override
    public void edit(StandardEntity item) {
        this.item = item;
        Category category = (Category) item;
        if (item != null) {
            nameField.setValue(category.getName() != null ? category.getName() : "");
            descriptionField.setValue(category.getDescription() != null ? category.getDescription() : "");
            nameField.focus();
        }
        setVisible(category != null);
    }
}
