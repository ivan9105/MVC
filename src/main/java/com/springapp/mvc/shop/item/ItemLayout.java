package com.springapp.mvc.shop.item;

import com.springapp.mvc.context.SpringContextHelper;
import com.springapp.mvc.data.shop.ItemPagingRepository;
import com.springapp.mvc.model.shop.Item;
import com.springapp.mvc.shop.base.AbstractLayout;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Иван on 24.12.2016.
 */
public class ItemLayout extends AbstractLayout {
    public ItemLayout(SpringContextHelper contextHelper) {
        super(contextHelper, ItemPagingRepository.class);
    }

    @Override
    protected void configureComponents() {
        super.configureComponents();

        list.setContainerDataSource(new BeanItemContainer<>(Item.class));
        list.setColumnOrder("name", "description", "category");
        list.removeColumn("id");
        list.setSelectionMode(Grid.SelectionMode.SINGLE);
        refresh(filter.getValue());
    }

    @Override
    protected void initForm() {
        this.form = new ItemForm(repository, this, helper);
    }

    @Override
    protected void doEdit(Button.ClickEvent event) {
        Item selected = (Item) list.getSelectedRow();
        if (selected != null) {
            form.edit(selected);
            switchForm(true, false);
        }
    }

    @Override
    protected void doCreate(Button.ClickEvent event) {
        form.edit(new Item());
    }

    @Override
    protected void doRemove(Button.ClickEvent event) {
        Item item = (Item) list.getSelectedRow();
        ((ItemPagingRepository) repository).delete(item);
        refresh(null);
    }

    @Override
    protected void refresh(String filterText) {
        Iterable<Item> iterable;
        if (StringUtils.isEmpty(filterText)) {
            iterable = repository.findAll();
        } else {
            iterable = ((ItemPagingRepository) repository).findAll(filterText);
        }

        List<Item> items = new ArrayList<>();
        for (Item next : iterable) {
            items.add(next);
        }
        list.setContainerDataSource(new BeanItemContainer<>(Item.class, items));

    }
}
