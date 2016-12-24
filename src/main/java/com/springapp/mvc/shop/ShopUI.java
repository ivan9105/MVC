package com.springapp.mvc.shop;

import com.springapp.mvc.context.SpringContextHelper;
import com.springapp.mvc.shop.category.CategoryLayout;
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
        items.addItem("Items", null);
        items.addItem("Items Generator", null);
        MenuBar.MenuItem categories = menuBar.addItem("Categories", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                TabSheet.Tab tab = tabSheet.addTab(new CategoryLayout(contextHelper));
                tab.setCaption("Categories");
                tab.setClosable(true);
            }
        });
        MenuBar.MenuItem info = menuBar.addItem("Info", null);
    }


}