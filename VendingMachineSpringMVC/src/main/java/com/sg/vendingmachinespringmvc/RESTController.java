   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc;

import VendingMachine.ServiceLayer.ServiceLayer;
import VendingMachine.dao.VendingMachinePersistanceException;
import VendingMachine.dto.VendingMachineItem;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Farhan
 */
@CrossOrigin
@Controller
public class RESTController {
    private ServiceLayer service;
    @Inject 
    public RESTController (ServiceLayer service){
    this.service=service;
    }
    
    
    @RequestMapping(value="/items", method = RequestMethod.GET)
    @ResponseBody
    public List<VendingMachineItem> getAllItems() throws VendingMachinePersistanceException{
    return service.getAllItems();
    }
}
