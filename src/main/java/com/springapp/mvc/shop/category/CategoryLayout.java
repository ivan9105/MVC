package com.springapp.mvc.shop.category;

import com.springapp.mvc.context.SpringContextHelper;
import com.springapp.mvc.data.shop.CategoryPagingRepository;
import com.springapp.mvc.model.shop.Category;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.SelectionEvent;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ���� on 18.12.2016.
 */
public class CategoryLayout extends VerticalLayout {
    private TextField filter = new TextField();
    private Grid categoryList = new Grid();
    private Button newButton = new Button("New Category");
    private CategoryForm categoryForm;

    private CategoryPagingRepository repository;

    public CategoryLayout(SpringContextHelper contextHelper) {
        this.repository = (CategoryPagingRepository) contextHelper.getBean(CategoryPagingRepository.class);
        this.categoryForm = new CategoryForm(repository, this);

        configureComponents();
        buildLayout();

        //Todo remove action
    }

    private void configureComponents() {
        newButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                categoryForm.edit(new Category());
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
        categoryList.addSelectionListener(new SelectionEvent.SelectionListener() {
            @Override
            public void select(SelectionEvent event) {
                categoryForm.edit((Category) categoryList.getSelectedRow());
            }
        });
        refresh(filter.getValue());
    }

    private void buildLayout() {
        HorizontalLayout actions = new HorizontalLayout(filter, newButton);
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
        Iterable<Category> iterable = repository.findAll();
        List<Category> list = new ArrayList<>();
        for (Category next : iterable) {
            list.add(next);
        }
        categoryList.setContainerDataSource(new BeanItemContainer<>(Category.class, list));
    }

    public Grid getList() {
        return categoryList;
    }
}
