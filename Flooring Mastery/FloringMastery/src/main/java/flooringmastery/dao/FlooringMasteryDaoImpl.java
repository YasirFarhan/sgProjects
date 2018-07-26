/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringmastery.dao;

import flooringmastery.dto.Order;
import flooringmastery.dto.Product;
import flooringmastery.dto.Tax;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Y
 */
public class FlooringMasteryDaoImpl implements FlooringMasteryDao {

    public static final String PRODUCTS = "Products.txt";
    public static final String TAXES = "Taxes.txt";
    public static final String CONFIGURATION = "Configuration.txt";
    public static final String DELIMITER = ",";
    private Map<String, Product> products = new HashMap<>();
    private Map<String, Tax> taxes = new HashMap<>();
    private Map<String, Map<Integer, Order>> ordersFileMap = new HashMap<>();
    private String productsFileHeadder = "OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total";

    @Override
    public List<Order> getOrdersList(String date) throws FlooringMasteryPersistenceException {
        String FILE = "Orders_" + date + ".txt";
        File f = new File(FILE);
        if (!f.exists() && !f.isFile()) {
            throw new FlooringMasteryPersistenceException("No orders Exist for the given date");
        }
        if (ordersFileMap.get(date) == null) {
            loadOrders(date);
        }
        return ordersFileMap.get(date).values().stream().collect(Collectors.toList());
    }

    @Override
    public Order findOrder(String date, int orderNumber) throws FlooringMasteryPersistenceException {
        return ordersFileMap.get(date).get(orderNumber);
    }

    @Override
    public Order addOrder(Order orderInfo) throws FlooringMasteryPersistenceException {
        String date = todaysDateAsString();
        orderInfo.setDate(date);
        if (ordersFileMap.get(date) == null) {
            loadOrders(date);
        }
        calculateOrderCost(orderInfo);
        return ordersFileMap.get(date).put(orderInfo.getOrderNumber(), orderInfo);
    }

    @Override
    public Order editOrder(Order orderInfo, Order originalOrder) throws FlooringMasteryPersistenceException {
        if (ordersFileMap.get(orderInfo.getDate()) == null) {
            loadOrders(orderInfo.getDate());
        }
        calculateOrderCost(orderInfo);
        ordersFileMap.get(originalOrder.getDate()).remove(originalOrder.getOrderNumber());
        return ordersFileMap.get(orderInfo.getDate()).put(orderInfo.getOrderNumber(), orderInfo);
    }

    private void calculateOrderCost(Order orderInfo) {
        double taxRate = taxes.get(orderInfo.getState()).getTaxRate();
        BigDecimal productCostPerSquareFoot = products.get(orderInfo.getProductType()).getCostPerSquareFoot();
        BigDecimal laborCostPerSquareFoot = products.get(orderInfo.getProductType()).getLaborCostPerSquareFoot();
        orderInfo.setAndCalculateCost(
                taxRate,
                productCostPerSquareFoot,
                laborCostPerSquareFoot);
    }

    @Override
    public Order removeOrder(String f, int orderNumber) {
        return ordersFileMap.get(f).remove(orderNumber);
    }

    @Override
    public int nextOrderNumber() throws FlooringMasteryPersistenceException, IOException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader("Order_Numbers.txt")));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException(
                    "*** Could not load order number  *** ", e);
        }
        String currentLine = scanner.nextLine().trim();
        scanner.close();
        PrintWriter out;
        out = new PrintWriter(new FileWriter("Order_Numbers.txt"));
        out.print(Integer.parseInt(currentLine) + 1);
        out.flush();
        out.close();
        return Integer.parseInt(currentLine) + 1;
    }

    @Override
    public String loadConfigFile() throws FlooringMasteryPersistenceException, FlooringMasteryInvalidConfigException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader("Configuration.txt")));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException(
                    "*** Could not load config file *** ", e);
        }

        String currentLine = scanner.nextLine().trim();

        scanner.close();
        return currentLine;
    }

    @Override
    public void loadOrders(String date) throws FlooringMasteryPersistenceException {
        String FILE = "Orders_" + date + ".txt";
        File f = new File(FILE);
        if (!f.exists() && !f.isFile()) {
            PrintWriter out;
            try {
                out = new PrintWriter(new FileWriter(FILE));
                out.println(productsFileHeadder);
                out.flush();
                out.close();
            } catch (IOException e) {
                throw new FlooringMasteryPersistenceException(
                        "Could not save item data.", e);
            }
        }

        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException(
                    "*** Could not orders*** ", e);
        }
        String currentLine;
        String[] currentTokens;

        int counter = 0;
        Map<Integer, Order> ordersTempHolder = new HashMap<>();
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            if (counter >= 1) {
                currentTokens = currentLine.split(DELIMITER);
                Order currentItem = new Order(Integer.parseInt(currentTokens[0]));
                currentItem.setDate(date);
                currentItem.setOrderNumber(Integer.parseInt(currentTokens[0]));
                currentItem.setCustomerName(currentTokens[1]);
                currentItem.setState(currentTokens[2]);
                currentItem.setTaxRate(Double.parseDouble(currentTokens[3]));
                currentItem.setProductType(currentTokens[4]);
                currentItem.setArea(Double.parseDouble(currentTokens[5]));
                currentItem.setCostPerSquareFoot(new BigDecimal(currentTokens[6]));
                currentItem.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[7]));
                currentItem.setMaterialCost(new BigDecimal(currentTokens[8]));
                currentItem.setLaborCost(new BigDecimal(currentTokens[9]));
                currentItem.setTax(new BigDecimal(currentTokens[10]));
                currentItem.setTotal(new BigDecimal(currentTokens[11]));
                ordersTempHolder.put(currentItem.getOrderNumber(), currentItem);
            }
            counter++;
        }
        ordersFileMap.put(date, ordersTempHolder);
        scanner.close();
    }

    @Override
    public void loadTaxes() throws FlooringMasteryPersistenceException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(TAXES)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException(
                    "*** Could not load Tax*** ", e);
        }
        String currentLine;
        String[] currentTokens;

        int counter = 0;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            if (counter > 0) {
                currentTokens = currentLine.split(DELIMITER);
                Tax currentTax = new Tax((currentTokens[0]));
                currentTax.setState(currentTokens[0]);
                currentTax.setTaxRate(Double.parseDouble(currentTokens[1]));
                taxes.put(currentTax.getState(), currentTax);
            }
            counter++;
        }
        scanner.close();
    }

    @Override
    public void loadProducts() throws FlooringMasteryPersistenceException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(PRODUCTS)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException(
                    "*** Could not Products*** ", e);
        }
        String currentLine;
        String[] currentTokens;
        int counter = 0;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            if (counter > 0) {
                currentTokens = currentLine.split(DELIMITER);
                Product currentProduct = new Product((currentTokens[0]));
                currentProduct.setProductType(currentTokens[0]);
                currentProduct.setCostPerSquareFoot(new BigDecimal(currentTokens[1]));
                currentProduct.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[2]));
                products.put(currentProduct.getProductType(), currentProduct);
            }
            counter++;
        }
        scanner.close();
    }

    @Override
    public void writeAllLoadedOrders() throws FlooringMasteryPersistenceException {
//     Map<Integer, Order> ordersTempHolder = new HashMap<>();
        Set<String> fileName = ordersFileMap.keySet();
        for (String f : fileName) {
            Map<Integer, Order> ordersTempHolder = new HashMap<>();
            Set<Integer> orderNumbers = ordersFileMap.get(f).keySet();
            for (int i : orderNumbers) {
                Order values = ordersFileMap.get(f).get(i);
                ordersTempHolder.put(i, values);
            }
            List<Order> ordersList = ordersTempHolder.values().stream().collect(Collectors.toList());
            writeOrders(f, ordersList);
        }
    }

    private void writeOrders(String FILE, List<Order> orderList) throws FlooringMasteryPersistenceException {
        if (FILE.isEmpty()) {
            LocalDate date = LocalDate.now();
            String formatted = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
            FILE = "Orders_" + date.format(DateTimeFormatter.ofPattern(formatted.toString())) + ".txt";
        }
        String date = FILE;
        FILE = "Orders_" + FILE + ".txt";
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(FILE));

        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException(
                    "Could not save item data.", e);
        }
        out.println(productsFileHeadder);
        orderList.stream().map((currentItem) -> {
            out.println(currentItem.getOrderNumber() + DELIMITER
                    + currentItem.getCustomerName() + DELIMITER
                    + currentItem.getState() + DELIMITER
                    + currentItem.getTaxRate() + DELIMITER
                    + currentItem.getProductType() + DELIMITER
                    + currentItem.getArea() + DELIMITER
                    + currentItem.getCostPerSquareFoot() + DELIMITER
                    + currentItem.getLaborCostPerSquareFoot() + DELIMITER
                    + currentItem.getMaterialCost() + DELIMITER
                    + currentItem.getLaborCost() + DELIMITER
                    + currentItem.getTax() + DELIMITER
                    + currentItem.getTotal()
            );
            return currentItem;
        }).forEach((_item) -> {
            out.flush();
        });
        out.close();
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

}
