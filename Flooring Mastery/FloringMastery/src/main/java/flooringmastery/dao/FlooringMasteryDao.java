/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringmastery.dao;

import flooringmastery.dto.Order;
import flooringmastery.dto.Product;
import flooringmastery.dto.Tax;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Y
 */
public interface FlooringMasteryDao {

    public Order editOrder(
            //String date, 
            Order orderInfo, Order originalOrder) throws FlooringMasteryPersistenceException;

    public List<Tax> getAllTax();

    public void writeAllLoadedOrders() throws FlooringMasteryPersistenceException;

    List<Order> getOrdersList(String date) throws FlooringMasteryPersistenceException;

    public List<Product> getAllProducts();

    public void loadProducts() throws FlooringMasteryPersistenceException;

    public void loadTaxes() throws FlooringMasteryPersistenceException;

    public void loadOrders(String date) throws FlooringMasteryPersistenceException;

    public Order removeOrder(String date, int OrderNumber);

    public Order addOrder(Order orderInfo) throws FlooringMasteryPersistenceException;

    public Order findOrder(String date, int OrderNumber) throws FlooringMasteryPersistenceException;

    public int nextOrderNumber() throws FlooringMasteryPersistenceException, IOException;

    public String loadConfigFile() throws FlooringMasteryPersistenceException, FlooringMasteryInvalidConfigException;

}
