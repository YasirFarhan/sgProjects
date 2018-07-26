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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Y
 */
public class FlooringMasteryDaoStubImpl implements FlooringMasteryDao {

//    public static final String PRODUCTS = "Products.txt";
//    public static final String TAXES = "Taxes.txt";
//    public static final String CONFIGURATION = "Configuration.txt";
//    public static final String DELIMITER = ",";
    private Map<String, Product> products = new HashMap<>();
    private Map<String, Tax> taxes = new HashMap<>();
    private Map<String, Map<Integer, Order>> ordersFileMap = new HashMap<>();
    private Order order;
    private Tax tax;
    private Product product;
//    private Product product2;
//    private String productsFileHeadder = "OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total";

    public FlooringMasteryDaoStubImpl() {
// add tax info to Taxes Map
        tax = new Tax("IN");
        tax.setTaxRate(6.00);
        taxes.put("IN", tax);

// add product info to products Map        
        product = new Product("Wood");
        product.setProductType("Wood");
        product.setCostPerSquareFoot(new BigDecimal("5.15"));
        product.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        products.put("Wood", product);

// add order Info to orderFileMap        
        order = new Order(1);
        order.setOrderNumber(1);
        order.setCustomerName("onlyOrder");
        order.setState("IN");
        order.setProductType("Wood");
        order.setDate("09232017");
        order.setArea(20);
        order.setCostPerSquareFoot(products.get("Wood").getCostPerSquareFoot());
        order.setLaborCost(products.get("Wood").getLaborCostPerSquareFoot());
        order.setTaxRate(taxes.get("IN").getTaxRate());
        order.setAndCalculateCost(taxes.get("IN").getTaxRate(),
                products.get("Wood").getCostPerSquareFoot(),
                products.get("Wood").getLaborCostPerSquareFoot());

        Map<Integer, Order> tempMap = new HashMap<>();
        tempMap.put(order.getOrderNumber(), order);
        ordersFileMap.put("09232017", tempMap);

    }

    @Override
    public List<Order> getOrdersList(String date) throws FlooringMasteryPersistenceException {
        return ordersFileMap.get(date).values().stream().collect(Collectors.toList());
    }

    @Override
    public Order findOrder(String date, int OrderNumber) throws FlooringMasteryPersistenceException {
        return ordersFileMap.get(date).get(OrderNumber);
    }

    @Override
    public Order addOrder(Order orderInfo) throws FlooringMasteryPersistenceException {
        String date = todaysDateAsString();
        if (ordersFileMap.get(date) == null) {
        }
        calculateOrderCost(orderInfo);
        return ordersFileMap.get(date).put(orderInfo.getOrderNumber(), orderInfo);
    }

    @Override
    public Order editOrder(Order orderInfo, Order originalOrder) throws FlooringMasteryPersistenceException {
        calculateOrderCost(orderInfo);
        ordersFileMap.get(originalOrder.getDate()).remove(originalOrder.getOrderNumber());
        return ordersFileMap.get(orderInfo.getDate()).put(orderInfo.getOrderNumber(), orderInfo);
    }
//    @Override
//    public Order editOrder(Order orderInfo, Order originalOrder) {
//
//        calculateOrderCost(orderInfo);
//        ordersFileMap.get(originalOrder.getDate()).remove(originalOrder.getOrderNumber());
//        return ordersFileMap.get(orderInfo.getDate()).put(orderInfo.getOrderNumber(), orderInfo);
//
////        calculateOrderCost(orderInfo);
////        ordersFileMap.get(originalOrder.getDate()).remove(originalOrder.getOrderNumber());
////        return ordersFileMap.get(orderInfo.getDate()).put(orderInfo.getOrderNumber(), orderInfo);
//    }

    private void calculateOrderCost(Order orderInfo) {
        double taxRate = taxes.get(orderInfo.getState()).getTaxRate();
        BigDecimal productCostPerSquareFoot = products.get(orderInfo.getProductType()).getCostPerSquareFoot();
        BigDecimal laborCostPerSquareFoot = products.get(orderInfo.getProductType()).getLaborCostPerSquareFoot();

        orderInfo.setAndCalculateCost(
                taxRate,
                productCostPerSquareFoot,
                laborCostPerSquareFoot);
//        orderInfo.setAndCalculateCost(
//                taxes.get(orderInfo.getState()).getTaxRate(),
//                products.get(orderInfo.getProductType()).getCostPerSquareFoot(),
//                products.get(orderInfo.getProductType()).getLaborCostPerSquareFoot());
// 
    }

    @Override
    public Order removeOrder(String f, int orderNumber) {
        return ordersFileMap.get(f).remove(orderNumber);
    }

    private String todaysDateAsString() {
        LocalDate date = LocalDate.now();
        String formattedTodaysDate = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        return formattedTodaysDate;
    }

    @Override
    public List<Product> getAllProducts() {
        return products.values().stream().collect(Collectors.toList());
    }

    @Override
    public List<Tax> getAllTax() {
        return taxes.values().stream().collect(Collectors.toList());
    }

    @Override
    public void writeAllLoadedOrders() throws FlooringMasteryPersistenceException {
    }

    @Override
    public void loadProducts() throws FlooringMasteryPersistenceException {
    }

    @Override
    public void loadTaxes() throws FlooringMasteryPersistenceException {
    }

    @Override
    public void loadOrders(String date) throws FlooringMasteryPersistenceException {
    }

    @Override
    public int nextOrderNumber() throws FlooringMasteryPersistenceException, IOException {
        return 1;
    }

    @Override
    public String loadConfigFile() throws FlooringMasteryPersistenceException, FlooringMasteryInvalidConfigException {
        return "a";
    }

}
