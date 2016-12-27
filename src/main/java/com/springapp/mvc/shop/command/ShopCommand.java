package com.springapp.mvc.shop.command;

import com.vaadin.ui.Component;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TabSheet;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Иван on 27.12.2016.
 */
public class ShopCommand implements MenuBar.Command {
    private String caption;
    private TabSheet tabSheet;

    public ShopCommand(String caption, TabSheet tabSheet) {
        this.caption = caption;
        this.tabSheet = tabSheet;
    }

    @Override
    public void menuSelected(MenuBar.MenuItem selectedItem) {
        boolean hasTab = false;

        for (Component component : tabSheet) {
            TabSheet.Tab tab = tabSheet.getTab(component);
            if (StringUtils.equals(caption, tab.getCaption())) {
                tabSheet.setSelectedTab(tab);
                hasTab = true;
                break;
            }
        }

        if (!hasTab) {
            addTab();
        }
    }

    protected void addTab() {
    }
}
