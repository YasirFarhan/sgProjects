/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringmastery.service;

import flooringmastery.dao.FloodingMasteryInvalidStateException;
import flooringmastery.dao.FlooringMasteryDao;
import flooringmastery.dao.FlooringMasteryInvalidConfigException;
import flooringmastery.dao.FlooringMasteryInvalidProductException;
import flooringmastery.dao.FlooringMasteryOrderDoesNotExistException;
import flooringmastery.dao.FlooringMasteryPersistenceException;
import flooringmastery.dto.Order;
import flooringmastery.dto.Product;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import flooringmastery.dto.Tax;
import java.io.IOException;
import flooringmastery.dao.AuditDao;

/**
 *
 * @author Y
 */
public class FlooringMasteryServiceImpl implements FlooringMasteryService {

    private FlooringMasteryDao myDao;
    AuditDao auditDao;

//    public FlooringMasteryServiceImpl(FlooringMasteryDao myDao, AuditDaoImpl AuditDao) throws FlooringMasteryPersistenceException {
    public FlooringMasteryServiceImpl(FlooringMasteryDao myDao, AuditDao auditDao) throws FlooringMasteryPersistenceException {
        this.myDao = myDao;
        this.auditDao = auditDao;
        myDao.loadTaxes();
        myDao.loadProducts();
    }

    @Override
    public List<Order> getOrdersList(String date) throws FlooringMasteryPersistenceException {
        return myDao.getOrdersList(date);
    }

    @Override
    public Order addOrder(Order info) throws FlooringMasteryPersistenceException, FlooringMasteryInvalidProductException, FloodingMasteryInvalidStateException {
        stateValidation(info.getState());
        productValidation(info.getProductType());

        return myDao.addOrder(info);
    }

    @Override
    public Order removeOrder(String date, int orderNumber) {
        return myDao.removeOrder(date, orderNumber);
    }

    @Override
    public int nextOrderNumber() throws FlooringMasteryPersistenceException, IOException {
        LocalDate date = LocalDate.now();
        String formatted = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        return myDao.nextOrderNumber();
    }

    @Override
    public Order findOrder(String date, int OrderNumber) throws
            FlooringMasteryPersistenceException,
            FlooringMasteryOrderDoesNotExistException {
        Order findOrder = myDao.findOrder(date, OrderNumber);
        if (findOrder == null) {
            throw new FlooringMasteryOrderDoesNotExistException("Order does not exisit");
        }
        return findOrder;
    }

    @Override
    public void orderNumberValidation(String date, int orderNumber) throws
            FlooringMasteryPersistenceException,
            FlooringMasteryOrderDoesNotExistException {
        Order findOrder = myDao.findOrder(date, orderNumber);
        if (findOrder == null) {
            throw new FlooringMasteryOrderDoesNotExistException("Order does not exisit");
        }
    }

    @Override
    public void productValidation(String productInfo) throws FlooringMasteryInvalidProductException, FlooringMasteryPersistenceException {
        List<Product> product = myDao.getAllProducts().stream().filter(s -> s.getProductType().equalsIgnoreCase(productInfo)).collect(Collectors.toList());
        if (product.size() == 0) {
            throw new FlooringMasteryInvalidProductException("Invalid product type please enter a valid product type");
        }
    }

    @Override
    public void stateValidation(String state) throws FloodingMasteryInvalidStateException, FlooringMasteryPersistenceException {
        if (myDao.getAllTax().stream().filter(s -> s.getState().equalsIgnoreCase(state)).collect(Collectors.toList()).size() == 0) {
            throw new FloodingMasteryInvalidStateException("Invalid state please enter a valid state");
        }
    }

    @Override
    public String loadConfigFile() throws FlooringMasteryPersistenceException, FlooringMasteryInvalidConfigException {
        return myDao.loadConfigFile();
    }

    @Override
    public void writeAllLoadedOrders() throws FlooringMasteryPersistenceException, FlooringMasteryInvalidConfigException {
        String config = myDao.loadConfigFile();
        if (config.equalsIgnoreCase("Prod")) {
            myDao.writeAllLoadedOrders();
        }
        if (config.equalsIgnoreCase("Training")) {
            // programm is in training mode and will not write to the file 
        }

    }

    @Override
    public Order editOrder(Order originalOrder, Order editOrder)
            throws FlooringMasteryPersistenceException,
            FlooringMasteryInvalidProductException,
            FloodingMasteryInvalidStateException {

        Order updateOrder = new Order(editOrder.getOrderNumber());
        updateOrder.setOrderNumber(originalOrder.getOrderNumber());
        // update order date

        if (editOrder.getDate().equalsIgnoreCase("")) {
            updateOrder.setDate(originalOrder.getDate());
        } else {
            updateOrder.setDate(editOrder.getDate());
        }

// update customer name        
        if (editOrder.getCustomerName().equalsIgnoreCase("")) {
            updateOrder.setCustomerName(originalOrder.getCustomerName());
        } else {
            updateOrder.setCustomerName(editOrder.getCustomerName());
        }

// update order area
        if (editOrder.getArea() == 0) {
            updateOrder.setArea(originalOrder.getArea());
        } else {
            updateOrder.setArea(editOrder.getArea());
        }
// update state/tax rate

        if (editOrder.getState().equalsIgnoreCase("")) {
            updateOrder.setState(originalOrder.getState());
        } else {
            stateValidation(editOrder.getState());
            updateOrder.setState(editOrder.getState());
        }
// update product

        if (editOrder.getProductType().equalsIgnoreCase("")) {
            updateOrder.setProductType(originalOrder.getProductType());
        } else {

            productValidation(editOrder.getProductType());
            updateOrder.setProductType(editOrder.getProductType());
        }
       return myDao.editOrder(updateOrder, originalOrder);

    }

    @Override
    public void loadOrders(String date) throws FlooringMasteryPersistenceException {
        myDao.loadOrders(date);
    }

    @Override
    public List<Tax> getAllTax() {
        return myDao.getAllTax();
    }

    @Override
    public List<Product> getAllProducts() {
        return myDao.getAllProducts();
    }

}
