/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringmastery.service;

import flooringmastery.dao.FloodingMasteryInvalidStateException;
import flooringmastery.dao.FlooringMasteryInvalidConfigException;
import flooringmastery.dao.FlooringMasteryInvalidProductException;
import flooringmastery.dao.FlooringMasteryOrderDoesNotExistException;
import flooringmastery.dao.FlooringMasteryPersistenceException;
import flooringmastery.dto.Order;
import flooringmastery.dto.Product;
import flooringmastery.dto.Tax;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Y
 */
public interface FlooringMasteryService {

    public void loadOrders(String date) throws FlooringMasteryPersistenceException;

    public String loadConfigFile() throws FlooringMasteryPersistenceException, FlooringMasteryInvalidConfigException;

    public Order findOrder(String date, int OrderNumber)
            throws FlooringMasteryPersistenceException,
            FlooringMasteryOrderDoesNotExistException;

    public void writeAllLoadedOrders() throws FlooringMasteryPersistenceException, FlooringMasteryInvalidConfigException;

    List<Order> getOrdersList(String date) throws FlooringMasteryPersistenceException;

    public void productValidation(String productInfo) throws FlooringMasteryInvalidProductException, FlooringMasteryPersistenceException;

    public void stateValidation(String state) throws FloodingMasteryInvalidStateException, FlooringMasteryPersistenceException;

    public void orderNumberValidation(String date, int OrderNumber) throws
            FlooringMasteryPersistenceException,
            FlooringMasteryOrderDoesNotExistException;

    public Order addOrder(Order info) throws FlooringMasteryPersistenceException, FlooringMasteryInvalidProductException, FloodingMasteryInvalidStateException;

    public int nextOrderNumber() throws FlooringMasteryPersistenceException, IOException;

    public Order removeOrder(String Date, int OrderNumber);

    public Order editOrder(Order originalOrder, Order editOrder)
            throws FlooringMasteryPersistenceException,
            FlooringMasteryInvalidProductException,
            FloodingMasteryInvalidStateException;

    public List<Tax> getAllTax();

    public List<Product> getAllProducts();
}
