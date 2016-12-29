package com.springapp.mvc.shop.user;

import com.springapp.mvc.context.SpringContextHelper;
import com.springapp.mvc.data.shop.UserPagingRepository;
import com.springapp.mvc.model.shop.User;
import com.springapp.mvc.shop.base.AbstractLayout;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Иван on 27.12.2016.
 */
public class UserLayout extends AbstractLayout {
    public UserLayout(SpringContextHelper contextHelper) {
        super(contextHelper, UserPagingRepository.class);
    }

    @Override
    protected void configureComponents() {
        super.configureComponents();

        list.setContainerDataSource(new BeanItemContainer<>(User.class));
        list.setColumnOrder("name", "surname", "middleName", "login");
        list.removeColumn("id");
        list.removeColumn("password");
        list.setSelectionMode(Grid.SelectionMode.SINGLE);
        refresh(filter.getValue());
    }

    @Override
    protected void initForm() {
        this.form = new UserForm(repository, this, helper);
    }

    @Override
    protected void doEdit(Button.ClickEvent event) {
        User selected = (User) list.getSelectedRow();
        if (selected != null) {
            form.edit(selected);
            switchForm(true, false);
        }
    }

    @Override
    protected void doCreate(Button.ClickEvent event) {
        form.edit(new User());
    }

    @Override
    protected void doRemove(Button.ClickEvent event) {
        User item = (User) list.getSelectedRow();
        ((UserPagingRepository) repository).delete(item);
        refresh(null);
    }

    @Override
    protected void refresh(String filterText) {
        Iterable<User> iterable;
        if (StringUtils.isEmpty(filterText)) {
            iterable = repository.findAll();
        } else {
            iterable = ((UserPagingRepository) repository).findAll(filterText);
        }

        List<User> items = new ArrayList<>();
        for (User next : iterable) {
            items.add(next);
        }
        list.setContainerDataSource(new BeanItemContainer<>(User.class, items));
    }
}
