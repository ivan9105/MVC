package com.springapp.mvc.shop;

import com.springapp.mvc.context.SpringContextHelper;
import com.springapp.mvc.service.ShopLoginService;
import com.springapp.mvc.shop.category.CategoryLayout;
import com.springapp.mvc.shop.command.ShopCommand;
import com.springapp.mvc.shop.item.ItemLayout;
import com.springapp.mvc.shop.user.UserLayout;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import org.apache.commons.lang3.StringUtils;

@Theme("valo")
@SpringUI(path = "main")
@SuppressWarnings("serial")
public class ShopUI extends UI {
    private static final String TRUSTED_PASSWORD = "admin";
    private static final String TRUSTED_LOGIN = "admin";

    private SpringContextHelper contextHelper;
    private VerticalLayout screensLayout;
    private VerticalLayout loginLayout;
    private ShopLoginService shopLoginService;

    @Override
    protected void init(VaadinRequest request) {
        setSizeFull();

        contextHelper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());

        initScreensLayout();

        VerticalLayout loginFieldsLayout = new VerticalLayout();
        loginFieldsLayout.setMargin(true);
        loginFieldsLayout.setCaption("Please login to access the application. " +
                "(Default " + TRUSTED_LOGIN + "/" + TRUSTED_PASSWORD);
        loginFieldsLayout.setSpacing(true);
        loginFieldsLayout.setMargin(new MarginInfo(true, true, true, false));
        loginFieldsLayout.setSizeUndefined();

        final TextField loginField = new TextField("User:");
        loginField.setWidth("300px");
        loginField.setRequired(true);
        loginField.setInputPrompt("Input login value");
        loginField.setInvalidAllowed(false);

        final PasswordField passwordField = new PasswordField("Password:");
        passwordField.setWidth("300px");
        passwordField.setRequired(true);
        passwordField.setValue("");
        passwordField.setNullRepresentation("");

        Button loginButton = new Button("Login", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                StringBuilder sb = new StringBuilder();

                String login = loginField.getValue();
                String password = passwordField.getValue();

                if (StringUtils.isEmpty(login)) {
                    sb.append("Login field must be filled").append("\n");
                }


                if (StringUtils.isEmpty(password)) {
                    sb.append("Password field must be filled").append("\n");
                }

                if (sb.length() == 0 && !isTrusted(login, password)
                        && !shopLoginService.checkLogin(login, password)) {
                    sb.append("Incorrect login or password").append("\n");
                }

                if (sb.length() == 0) {
                    setContent(screensLayout);
                } else {
                    Notification.show(sb.toString(), Notification.Type.TRAY_NOTIFICATION);
                }
            }
        });

        loginFieldsLayout.addComponents(loginField, passwordField, loginButton);

        loginLayout = new VerticalLayout(loginFieldsLayout);
        loginLayout.setSizeFull();
        loginLayout.setComponentAlignment(loginFieldsLayout, Alignment.MIDDLE_CENTER);
        loginLayout.setStyleName(Reindeer.LAYOUT_BLUE);
        setContent(loginLayout);

        shopLoginService = (ShopLoginService) contextHelper.getBean(ShopLoginService.class);
    }

    private boolean isTrusted(String login, String password) {
        return StringUtils.equals(TRUSTED_LOGIN, login) && StringUtils.equals(TRUSTED_PASSWORD, password);
    }

    private void initScreensLayout() {
        screensLayout = new VerticalLayout();
        screensLayout.setMargin(true);

        MenuBar menuBar = new MenuBar();
        screensLayout.addComponent(menuBar);

        final TabSheet tabSheet = new TabSheet();
        screensLayout.addComponent(tabSheet);

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