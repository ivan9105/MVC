package com.springapp.mvc.shop.item;

import com.springapp.mvc.context.SpringContextHelper;
import com.springapp.mvc.data.shop.CategoryPagingRepository;
import com.springapp.mvc.model.StandardEntity;
import com.springapp.mvc.model.shop.Category;
import com.springapp.mvc.model.shop.Item;
import com.springapp.mvc.shop.base.AbstractForm;
import com.springapp.mvc.shop.base.AbstractLayout;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ���� on 25.12.2016.
 */
public class ItemForm extends AbstractForm {
    private TextField nameField = new TextField("Name");
    private TextField countField = new TextField("Count");
    private TextArea descriptionField = new TextArea("Description");
    private TextField priceField = new TextField("Price");
    private ComboBox categoryField = new ComboBox("Category");
    private List<Category> categories = new ArrayList<>();

    public ItemForm(PagingAndSortingRepository repository, AbstractLayout layout, SpringContextHelper helper) {
        super(repository, layout, helper);

        configureComponents();
        buildLayout();
    }

    @Override
    protected void buildLayout() {
        super.buildLayout();
        addComponents(nameField, countField, descriptionField, priceField, categoryField);
        descriptionField.setRows(5);

        CategoryPagingRepository categoryRepository = (CategoryPagingRepository)
                helper.getBean(CategoryPagingRepository.class);
        Iterable<Category> iterable = categoryRepository.findAll();
        for (Category category : iterable) {
            categories.add(category);
        }
        categoryField.addItems(categories);
        categoryField.setImmediate(true);
        categoryField.setNullSelectionAllowed(false);

        countField.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(FieldEvents.TextChangeEvent event) {
                String text = event.getText();
                if (StringUtils.isNotEmpty(text)) {
                    if (text.matches(".*[^\\d].*")) {
                        String stringValue = text.replaceAll("[^\\d]", "");
                        Integer integerValue = Integer.valueOf(stringValue);
                        countField.setValue(integerValue.toString());
                    } else if (text.toCharArray()[0] == '0') {
                        Integer integerValue = Integer.valueOf(text);
                        countField.setValue(integerValue.toString());
                    }
                }
            }
        });

        priceField.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(FieldEvents.TextChangeEvent event) {
                String text = event.getText();
                if (StringUtils.isNotEmpty(text)) {
                    if (text.matches(".*[^.\\d].*")) {
                        String stringValue = text.replaceAll(",", ".").replaceAll("[^,.\\d]", "");
                        if (StringUtils.isNotEmpty(stringValue)) {
                            BigDecimal decimalValue = new BigDecimal(stringValue);
                            priceField.setValue(decimalValue.toString());
                        }
                    } else if (text.toCharArray()[0] == '0') {
                        BigDecimal decimalValue = new BigDecimal(text);
                        countField.setValue(decimalValue.toString());
                    }
                }
            }
        });
    }

    @Override
    protected void save(Button.ClickEvent event) {
        Item item_ = (Item) item;
        item_.setName(nameField.getValue());
        item_.setCount(StringUtils.isNotEmpty(countField.getValue()) ? Integer.valueOf(countField.getValue()) : 0);
        item_.setDescription(descriptionField.getValue());
        item_.setCategory((Category) categoryField.getValue());
        item_.setPrice(new BigDecimal(priceField.getValue()));
        repository.save(item_);
        String msg = String.format("Saved '%s'.", item_.getName());
        Notification.show(msg, Notification.Type.TRAY_NOTIFICATION);
        layout.switchForm(false, true);
    }

    @Override
    protected void cancel(Button.ClickEvent event) {
        Notification.show("Cancelled", Notification.Type.TRAY_NOTIFICATION);
        layout.switchForm(false, false);
    }

    @Override
    public void edit(StandardEntity item) {
        this.item = item;
        Item item_ = (Item) item;
        if (item != null) {
            nameField.setValue(item_.getName() != null ? item_.getName() : "");
            descriptionField.setValue(item_.getDescription() != null ? item_.getDescription() : "");
            priceField.setValue(item_.getPrice() != null ? item_.getPrice().toString() : "0");

            if (categories != null && categories.size() > 0 && item_.getCategory() != null) {
                for (Category category : categories) {
                    if (item_.getCategory().getId().equals(category.getId())) {
                        categoryField.setValue(category);
                        break;
                    }
                }
            }

            countField.setValue(item_.getCount() != null ? item_.getCount().toString() : "");
            nameField.focus();

        }
        setVisible(item_ != null);
    }
}
