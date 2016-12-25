package com.springapp.mvc.shop.category;

import com.springapp.mvc.context.SpringContextHelper;
import com.springapp.mvc.data.shop.CategoryPagingRepository;
import com.springapp.mvc.model.shop.Category;
import com.springapp.mvc.shop.base.AbstractLayout;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ���� on 18.12.2016.
 */
public class CategoryLayout extends AbstractLayout {
    public CategoryLayout(SpringContextHelper contextHelper) {
        super(contextHelper, CategoryPagingRepository.class);
    }

    @Override
    protected void configureComponents() {
        super.configureComponents();

        list.setContainerDataSource(new BeanItemContainer<>(Category.class));
        list.setColumnOrder("name", "description");
        list.removeColumn("id");
        list.removeColumn("items");
        list.setSelectionMode(Grid.SelectionMode.SINGLE);
        refresh(filter.getValue());
    }

    @Override
    protected void initForm() {
        this.form = new CategoryForm(repository, this, helper);
    }

    @Override
    protected void doEdit(Button.ClickEvent event) {
        Category selected = (Category) list.getSelectedRow();
        if (selected != null) {
            form.edit(selected);
            switchForm(true, false);
        }
    }

    @Override
    protected void doCreate(Button.ClickEvent event) {
        form.edit(new Category());
    }

    @Override
    protected void doRemove(Button.ClickEvent event) {
        Category category = (Category) list.getSelectedRow();
        ((CategoryPagingRepository) repository).delete(category);
        refresh(null);
    }

    @Override
    protected void refresh(String filterText) {
        Iterable<Category> iterable;
        if (StringUtils.isEmpty(filterText)) {
            iterable = repository.findAll();
        } else {
            iterable = ((CategoryPagingRepository) repository).findAll(filterText);
        }

        List<Category> items = new ArrayList<>();
        for (Category next : iterable) {
            items.add(next);
        }
        list.setContainerDataSource(new BeanItemContainer<>(Category.class, items));
    }
}
