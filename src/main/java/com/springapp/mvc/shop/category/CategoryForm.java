package com.springapp.mvc.shop.category;

import com.springapp.mvc.context.SpringContextHelper;
import com.springapp.mvc.data.shop.CategoryPagingRepository;
import com.springapp.mvc.model.StandardEntity;
import com.springapp.mvc.model.shop.Category;
import com.springapp.mvc.service.ShopCategoryService;
import com.springapp.mvc.shop.base.AbstractForm;
import com.vaadin.data.Property;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Иван on 17.12.2016.
 */
@SpringUI
public class CategoryForm extends AbstractForm {
    private TextField nameField = new TextField("Name");
    private TextArea descriptionField = new TextArea("Description");
    private ComboBox categoryField = new ComboBox("Category");
    private List<Category> categories = new ArrayList<>();
    private int level;
    private ShopCategoryService shopCategoryService;
    private boolean parentChanged = false;

    public CategoryForm(PagingAndSortingRepository repository, com.springapp.mvc.shop.base.AbstractLayout layout,
                        SpringContextHelper helper) {
        super(repository, layout, helper);

        configureComponents();
        buildLayout();

        this.shopCategoryService = (ShopCategoryService) helper.getBean(ShopCategoryService.class);
    }

    @Override
    protected void buildLayout() {
        super.buildLayout();
        addComponents(nameField, descriptionField, categoryField);

        refreshCategoryField();
        categoryField.setNullSelectionAllowed(true);
        categoryField.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                Property property = event.getProperty();
                if (property == null || property.getValue() == null) {
                    level = 0;
                } else {
                    Category parent = (Category) property.getValue();
                    if (parent.getLevel() != null) {
                        level = parent.getLevel() + 1;
                    }
                }
                parentChanged = true;
            }
        });
    }

    private void refreshCategoryField() {
        CategoryPagingRepository categoryRepository = (CategoryPagingRepository)
                helper.getBean(CategoryPagingRepository.class);
        Iterable<Category> iterable = categoryRepository.findAll();
        for (Category category : iterable) {
            categories.add(category);
        }
        categoryField.addItems(categories);
    }

    @Override
    protected void save(Button.ClickEvent event) {
        Category category = (Category) item;
        category.setName(nameField.getValue());
        category.setDescription(descriptionField.getValue());
        category.setLevel(level);
        category.setParent((Category) categoryField.getValue());
        repository.save(category);
        if (parentChanged) {
            shopCategoryService.updateHierarchy(category);
        }
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
            level = category.getLevel() == null ? 0 : category.getLevel();

            refreshCategoryField();
            categoryField.setValue(null);
            if (categories != null && categories.size() > 0 && category.getParent() != null) {
                for (Category category_ : categories) {
                    if (category.getParent().getId().equals(category_.getId())) {
                        categoryField.setValue(category_);
                        break;
                    }
                }
            }

            nameField.focus();
        }
        setVisible(category != null);
    }
}
