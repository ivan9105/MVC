package com.springapp.mvc.controller;

import com.springapp.mvc.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Иван on 15.10.2016.
 */
@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private MainService mainService;

    @RequestMapping(value = "crudMain", method = RequestMethod.GET)
    public String getMainCRUDPage() {
        return "crud/crudMain";
    }

    @RequestMapping(value = "generateTestData", method = RequestMethod.GET)
    public String generateTestDate() {
        mainService.generateTestData();
        return "crud/crudMain";
    }

    @RequestMapping(value = "shop", method = RequestMethod.GET)
    public String getShopMainPage(@RequestParam(value = "cId", required = false) String cId,
                                  Model model) {
        model.addAttribute("cId", cId);
        return "shop/main";
    }

    @RequestMapping(value = "item", method = RequestMethod.GET)
    public String getItemPage(@RequestParam(value = "id", required = true) String id,
                              Model model) {
        model.addAttribute("id", id);
        return "shop/item";
    }
}
