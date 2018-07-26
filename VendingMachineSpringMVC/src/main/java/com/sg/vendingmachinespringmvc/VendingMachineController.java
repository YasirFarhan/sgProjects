/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc;

import VendingMachine.ServiceLayer.InsuficientFundsException;
import VendingMachine.ServiceLayer.ItemDoNotExistException;
import VendingMachine.ServiceLayer.NoInventoryException;
import VendingMachine.ServiceLayer.ServiceLayer;
import VendingMachine.dao.VendingMachinePersistanceException;
import VendingMachine.dto.ConvertingChange;
import VendingMachine.dto.VendingMachineItem;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Farhan
 */
@Controller
public class VendingMachineController {

    ServiceLayer service;

    @Inject
    public VendingMachineController(ServiceLayer service) throws VendingMachinePersistanceException {
        this.service = service;
    }

    @RequestMapping(value = "/displayAllItems", method = RequestMethod.GET)
    public String displayAllItems(Model model)
            throws VendingMachinePersistanceException {
        List<VendingMachineItem> itemsList = service.getAllItems();
        model.addAttribute("items", itemsList);
        model.addAttribute("change", service.getChange());
        return "Items";
    }

    @RequestMapping(value = "/selectItem", method = RequestMethod.GET)
    public String selectItem(@Valid HttpServletRequest request, Model model) throws VendingMachinePersistanceException, ItemDoNotExistException {
        String itemId = request.getParameter("id");
        // when user decides to add money before selecting any item
        // if statement will avoid parsing exception
        if (itemId == null) {
            itemId = "0";
        }
        long id = Long.parseLong(itemId);

        if (id != 0) {
            service.verifySelectedItem(id);
            List<VendingMachineItem> selectedItem = service.getAnItem(id);
            model.addAttribute("selectedItem", selectedItem);
        }
        model.addAttribute("totalMoney", service.getTotalMoney());
        return displayAllItems(model);
    }

    @RequestMapping(value = "/moneyAdded")
    public String addMoney(HttpServletRequest request) throws VendingMachinePersistanceException {
        String money = request.getParameter("amount");
        double moneyDouble = Double.parseDouble(money);
        service.moneyAdded(moneyDouble);
        return "redirect:selectItem";
    }

    @RequestMapping(value = "/makePurchase")
    public String makePurchase(@Valid HttpServletRequest request, Model model)
            throws VendingMachinePersistanceException, NoInventoryException, ItemDoNotExistException, InsuficientFundsException {
        if (service.getSelectedItem() == 0) {
            return "redirect:selectItem";
        }
        if (service.getTotalMoney() != 0) {
            BigDecimal b = new BigDecimal(service.getTotalMoney());
            try {
                b = service.returnChangeInDollars(service.getSelectedItem(), b);
                model.addAttribute("message", "Thank You !!");
                service.updateInventoryLevel(service.getSelectedItem());
                service.moneyAdded(0);
            } catch (InsuficientFundsException e) {
                model.addAttribute("message", e.getMessage());
                model.addAttribute("totalMoney", service.getTotalMoney());
            }
            model.addAttribute("change", service.change(b));
            model.addAttribute("items", service.getAllItems());
        }
        return displayAllItems(model);
//        return "Items";
    }

    @RequestMapping(value = "/returnChange")
    public String returnChange(HttpServletRequest request, Model model)
            throws VendingMachinePersistanceException, NoInventoryException, ItemDoNotExistException, InsuficientFundsException {
        if (service.getTotalMoney() != 0) {
            ConvertingChange change = service.change(new BigDecimal(service.getTotalMoney()));
            service.moneyAdded(0);
            model.addAttribute("change", change);
        }
        return displayAllItems(model);
    }
}
