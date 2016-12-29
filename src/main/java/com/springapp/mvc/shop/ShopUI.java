package com.springapp.mvc.shop;

import com.springapp.mvc.context.SpringContextHelper;
import com.springapp.mvc.shop.category.CategoryLayout;
import com.springapp.mvc.shop.command.ShopCommand;
import com.springapp.mvc.shop.item.ItemLayout;
import com.springapp.mvc.shop.user.UserLayout;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("valo")
@SpringUI(path = "main")
@SuppressWarnings("serial")
public class ShopUI extends UI {
    private SpringContextHelper contextHelper;

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        contextHelper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());

        MenuBar menuBar = new MenuBar();
        layout.addComponent(menuBar);

        final TabSheet tabSheet = new TabSheet();
        layout.addComponent(tabSheet);

        MenuBar.MenuItem items = menuBar.addItem("Item management", null);
        items.addItem("Items", new ShopCommand("Items", tabSheet) {
            @Override
            protected void addTab() {
                TabSheet.Tab tab = tabSheet.addTab(new ItemLayout(contextHelper));
                tab.setCaption("Items");
                tab.setClosable(true);
            }
        });

        items.addItem("Items Generator", null);

        menuBar.addItem("Categories", new ShopCommand("Categories", tabSheet) {
            @Override
            protected void addTab() {
                TabSheet.Tab tab = tabSheet.addTab(new CategoryLayout(contextHelper));
                tab.setCaption("Categories");
                tab.setClosable(true);
            }
        });

        menuBar.addItem("Users", new ShopCommand("Users", tabSheet) {
            @Override
            protected void addTab() {
                TabSheet.Tab tab = tabSheet.addTab(new UserLayout(contextHelper));
                tab.setCaption("Users");
                tab.setClosable(true);
            }
        });

        menuBar.addItem("Info", null);
    }
}