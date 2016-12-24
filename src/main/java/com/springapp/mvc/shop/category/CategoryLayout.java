package com.springapp.mvc.shop.category;

import com.springapp.mvc.context.SpringContextHelper;
import com.springapp.mvc.data.shop.CategoryPagingRepository;
import com.springapp.mvc.model.shop.Category;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ���� on 18.12.2016.
 */
public class CategoryLayout extends VerticalLayout {
    private TextField filter = new TextField();
    private Grid categoryList = new Grid();
    private Button createBtn = new Button("Create");
    private Button removeBtn = new Button("Remove");
    private Button editBtn = new Button("Edit");
    private CategoryForm categoryForm;

    private CategoryPagingRepository repository;

    public CategoryLayout(SpringContextHelper contextHelper) {
        this.repository = (CategoryPagingRepository) contextHelper.getBean(CategoryPagingRepository.class);
        this.categoryForm = new CategoryForm(repository, this);

        configureComponents();
        buildLayout();
    }

    private void configureComponents() {
        createBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                categoryForm.edit(new Category());
            }
        });
        removeBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Category category = (Category) categoryList.getSelectedRow();
                repository.delete(category);
                refresh(null);
            }
        });
        editBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Category selected = (Category) categoryList.getSelectedRow();
                if (selected != null) {
                    categoryForm.edit(selected);
                    switchForm(true);
                }
            }
        });

        filter.setInputPrompt("Filter contacts ...");
        filter.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(FieldEvents.TextChangeEvent event) {
                refresh(event.getText());
            }
        });

        categoryList.setContainerDataSource(new BeanItemContainer<>(Category.class));
        categoryList.setColumnOrder("name", "description");
        categoryList.removeColumn("id");
        categoryList.removeColumn("items");
        categoryList.setSelectionMode(Grid.SelectionMode.SINGLE);
        refresh(filter.getValue());
    }

    private void buildLayout() {
        HorizontalLayout actions = new HorizontalLayout(filter, createBtn, removeBtn, editBtn);
        actions.setWidth("100%");
        filter.setWidth("100%");
        actions.setExpandRatio(filter, 1);

        VerticalLayout left = new VerticalLayout(actions, categoryList);
        left.setSizeFull();
        categoryList.setSizeFull();
        left.setExpandRatio(categoryList, 1);

        HorizontalLayout mainLayout = new HorizontalLayout(left, categoryForm);
        mainLayout.setSizeFull();
        mainLayout.setExpandRatio(left, 1);

        addComponent(mainLayout);
    }

    private void refresh(String filterText) {
        Iterable<Category> iterable;
        if (StringUtils.isEmpty(filterText)) {
            iterable = repository.findAll();
        } else {
            iterable = repository.findAll(filterText);
        }

        List<Category> list = new ArrayList<>();
        for (Category next : iterable) {
            list.add(next);
        }
        categoryList.setContainerDataSource(new BeanItemContainer<>(Category.class, list));
    }

    public void switchForm(boolean visible) {
        categoryForm.setVisible(visible);
    }
}
