/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringmastery.controller;

import flooringmastery.dao.FloodingMasteryInvalidStateException;
import flooringmastery.dao.FlooringMasteryInvalidConfigException;
import flooringmastery.dao.FlooringMasteryInvalidProductException;
import flooringmastery.dao.FlooringMasteryOrderDoesNotExistException;
import flooringmastery.dao.FlooringMasteryPersistenceException;
import flooringmastery.dto.Order;
import flooringmastery.service.FlooringMasteryService;
import flooringmastery.ui.FlooringMasteryView;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Y
 */
public class FlooringMasteryController {

    private FlooringMasteryService myService;
    private FlooringMasteryView view;

    public FlooringMasteryController(FlooringMasteryService myService, FlooringMasteryView myView) {
        this.myService = myService;
        this.view = myView;
    }

    public void run() throws Exception {
        String config = myService.loadConfigFile();
        if (config.equalsIgnoreCase("Prod")) {
            view.message("Program is running in " + config);
        } else if (config.equalsIgnoreCase("Training")) {
            view.message("Program is running in " + config);
        } else {
            throw new FlooringMasteryInvalidConfigException("Invalid Config, Please set the configuration to either Prod or Traning. Programm will now exit");
        }

        int selection = view.menu();
        switch (selection) {
            case 1: //    1. DisplayOrders
                try {
                    displayOrders();
                } catch (FloodingMasteryInvalidStateException | FlooringMasteryInvalidConfigException | FlooringMasteryInvalidProductException | FlooringMasteryOrderDoesNotExistException | FlooringMasteryPersistenceException | IOException e) {
                    view.exceptionMessage(e.getMessage());
                    run();
                    //displayOrders();
                }
                run();
            case 2:  //  2. Add an Order  
                try {
                    addOrder();
                } catch (FloodingMasteryInvalidStateException | FlooringMasteryInvalidProductException | FlooringMasteryOrderDoesNotExistException | FlooringMasteryPersistenceException e) {
                    view.exceptionMessage(e.getMessage());
                    run();
                    //addOrder();
                }
                run();
            case 3: //  3. Edit an Order
                try {
                    editOrder();
                } catch (FloodingMasteryInvalidStateException | FlooringMasteryInvalidProductException | FlooringMasteryOrderDoesNotExistException | FlooringMasteryPersistenceException e) {
                    view.exceptionMessage(e.getMessage());
                    run();
                    //  editOrder();
                }
                run();
            case 4:// 4. Remove an Order
                try {
                    removeOrder();
                } catch (FloodingMasteryInvalidStateException | FlooringMasteryInvalidProductException | FlooringMasteryOrderDoesNotExistException | FlooringMasteryPersistenceException e) {
                    view.exceptionMessage(e.getMessage());
                    run();
                    // removeOrder();
                }
                run();
            case 5:  // 5. Save Current Work
                myService.writeAllLoadedOrders();
                run();
            case 6:   // 6. Quit
                if (view.userConfirmaton()) {
                    view.message("Good Bye !!!");
                    System.exit(0);
                }
                run();
            default:
                view.message("Please make a valid selection");
                run();
        }
    }

    private void editOrder() throws FlooringMasteryInvalidProductException,
            FloodingMasteryInvalidStateException,
            FlooringMasteryPersistenceException,
            FlooringMasteryOrderDoesNotExistException,
            FlooringMasteryInvalidConfigException,
            IOException,
            Exception {
// displays originalOrder Info
        String date = fileNameValidation();
        int orderNumb = view.orderNumber();
        try {
            Order originalOrder = myService.findOrder(date, orderNumb);
//user input and validates user info       
            Order editOrder = view.editOrderInfo(orderNumb, originalOrder, myService.getAllTax(), myService.getAllProducts());
            if (view.userConfirmaton()) {
                myService.editOrder(originalOrder, editOrder);

            }
        } catch (FlooringMasteryInvalidProductException | FlooringMasteryPersistenceException e) {
            view.exceptionMessage(e.getMessage());
            view.message("Please re-enter the order date ");
            editOrder();
        }
        run();
    }

    private void displayOrders() throws
            FlooringMasteryPersistenceException,
            FlooringMasteryInvalidProductException,
            FloodingMasteryInvalidStateException,
            FlooringMasteryOrderDoesNotExistException,
            FlooringMasteryInvalidConfigException,
            IOException,
            Exception {
        try {
            String dateSelection = view.dateSelection();
            List<Order> orders = myService.getOrdersList(dateSelection);
            view.displayAllOrders(orders);
        } catch (FlooringMasteryPersistenceException e) {
            view.exceptionMessage(e.getMessage());
            run();
        }
        run();
    }

    private void addOrder() throws
            FloodingMasteryInvalidStateException,
            FlooringMasteryInvalidProductException,
            FlooringMasteryPersistenceException,
            FlooringMasteryOrderDoesNotExistException,
            FlooringMasteryInvalidConfigException,
            IOException,
            Exception {
        try {
//        stateValidation();
//        productValidation();
            int nextOrderNumber = myService.nextOrderNumber();

            Order orderInfo = view.getOrderInfo(nextOrderNumber, myService.getAllTax(), myService.getAllProducts());
            if (view.userConfirmaton()) {
                myService.addOrder(orderInfo);
            }
        } catch (Exception e) {
            view.exceptionMessage(e.getMessage());
            run();
        }
    }

    private void removeOrder() throws
            FlooringMasteryPersistenceException,
            FlooringMasteryInvalidProductException,
            FloodingMasteryInvalidStateException,
            FlooringMasteryOrderDoesNotExistException,
            FlooringMasteryInvalidConfigException,
            IOException,
            Exception {
        String fileName = fileNameValidation();
        int orderNum = view.orderNumber();
        view.displayNewOrderInfo(myService.findOrder(fileName, orderNum));
        if (view.userConfirmaton()) {
            myService.removeOrder(fileName, orderNum);
        }
        run();
    }

    private String fileNameValidation() throws FlooringMasteryPersistenceException, FlooringMasteryInvalidProductException, FloodingMasteryInvalidStateException, FlooringMasteryOrderDoesNotExistException, FlooringMasteryInvalidConfigException, IOException, Exception {
        String date = view.dateSelection();
        try {
            myService.getOrdersList(date);
        } catch (FlooringMasteryPersistenceException e) {
            view.exceptionMessage(e.getMessage());
            run();
        }
        return date;
    }

//    private void stateValidation() throws FloodingMasteryInvalidStateException, FlooringMasteryInvalidProductException, FloodingMasteryInvalidStateException, FlooringMasteryOrderDoesNotExistException, FlooringMasteryInvalidConfigException, IOException, FlooringMasteryPersistenceException {
//
//        try {
//            view.displayState(myService.getAllTax());
//            String state = view.stateInput();
//            myService.stateValidation(state);
//            view.setStateInput(state);
//        } catch (FloodingMasteryInvalidStateException
//                | FlooringMasteryPersistenceException e) {
//            view.exceptionMessage(e.getMessage());
//            stateValidation();
//        }
//    }
//    private void productValidation() throws FlooringMasteryInvalidProductException, FlooringMasteryInvalidProductException, FloodingMasteryInvalidStateException, FlooringMasteryOrderDoesNotExistException, FlooringMasteryInvalidConfigException, IOException, FlooringMasteryPersistenceException {
//        try {
//            view.displayProducts(myService.getAllProducts());
//            String product = view.productInput();
//            myService.productValidation(product);
//            view.setProductInput(product);
//        } catch (FlooringMasteryInvalidProductException | FlooringMasteryPersistenceException e) {
//            view.exceptionMessage(e.getMessage());
//            productValidation();
//        }
    //}
}
