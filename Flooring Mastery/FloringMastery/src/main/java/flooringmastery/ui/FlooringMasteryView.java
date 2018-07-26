/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringmastery.ui;

import flooringmastery.dto.Order;
import flooringmastery.dto.Product;
import flooringmastery.dto.Tax;
import java.util.List;

/**
 *
 * @author Y
 */
public class FlooringMasteryView {

    private final UserIO io;
//    private String customerName;
//    private String productType;
//    private String state;

    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }

    public int menu() {
        io.print("**************SWG Corp**Menu*****************");
        io.print("*  1. DisplayOrders");
        io.print("*  2. Add an Order");
        io.print("*  3. Edit an Order");
        io.print("*  4. Remove an Order");
        io.print("*  5. Save Current Work");
        io.print("*  6. Quit");
        io.print("*********************************************");
        return io.readInt("Please make a selection");
    }

    public void displayAllOrders(List<Order> orders) {
        orders.stream().forEach(s -> {
            io.print("  |Order Number : " + s.getOrderNumber());
            io.printCustomerName("  |Customer Name: " + s.getCustomerName());
            io.print("  |State: " + s.getState());
            io.print("  |Product Type: " + s.getProductType());
            io.print("  |Labor Cost: $" + s.getLaborCost());
            io.print("  |Material Cost: $" + s.getMaterialCost());
            io.print("  |Tax : $" + s.getTax());
            io.print("  |Total Cost Is $" + s.getTotal());
            io.print("-------------------------------------------");
        });
    }

    public Order getOrderInfo(int orderNumber,
            List<Tax> states,
            List<Product> products
    ) {
        io.print("Order Number Is: " + orderNumber);
        String customerName = io.readCustomerName("   | Enter Customer Name: ");
        double area = io.readDouble(" | Enter Area: ");

        // list product Type
        io.print("Select from the following product types:");
        products.stream().forEach(s -> io.print(s.getProductType()));
        String productType = io.readString("| Enter product type:");

        //  list sates
        if (!productType.equalsIgnoreCase("")) {
            productType = productType.substring(0, 1).toUpperCase() + productType.substring(1);
        }
        io.print("Select from the following states:");
        states.stream().forEach(s -> io.print(s.getState()));

        String state = io.readString("| Enter State:");
        state = state.toUpperCase();
        Order order = new Order(orderNumber);
        order.setCustomerName(customerName);
        order.setArea(area);
        order.setProductType(productType);
        order.setState(state);
        return order;
    }

    public Order editOrderInfo(
            int orderNumber,
            Order originalOrder,
            List<Tax> states,
            List<Product> products) {

        io.print("Order Number Is: " + orderNumber);

        Order order = new Order(orderNumber);
        order.setOrderNumber(orderNumber);

        // update order date
        String updatedDate = io.readDateReturnString("Enter date if you would like to change it (" + originalOrder.getDate() + "):");

        if (!updatedDate.equals("")) {
            order.setDate(updatedDate);
        } else {
            order.setDate(originalOrder.getDate());
        }

        // read customer name
        String customerName = io.readCustomerName("   | Enter Customer Name (" + originalOrder.getCustomerName() + "):");
        order.setCustomerName(customerName);

        double area = io.readDoubleEdit(" | Enter Area (" + originalOrder.getArea() + "sqft):");
        order.setArea(area);

        // list available product types
        io.print("Select from the following product types:");
        products.stream().forEach(s -> io.print(s.getProductType()));

        String productType = io.readString("Enter Product Type (" + originalOrder.getProductType() + "):");
        if (!productType.equals("")) {
            String product = productType.substring(0, 1).toUpperCase() + productType.substring(1);
            order.setProductType(product);
        } else {
            order.setProductType(originalOrder.getProductType());
        }
// list available states
        io.print("Select from the following states:");
        states.stream().forEach(s -> io.print(s.getState()));
        String state = io.readString(" | Enter State (" + originalOrder.getState() + "):").toUpperCase();
        if (!state.equals("")) {
            order.setState(state.toUpperCase());
        } else {
            order.setState(originalOrder.getState());
        }
        return order;
    }

    public void displayNewOrderInfo(Order order) {
        io.print("OrderNumber: " + order.getOrderNumber());
        io.printCustomerName("CustomerName: " + order.getCustomerName());
        io.print("Material Cost: " + order.getMaterialCost());
        io.print("Labor Cost: " + order.getLaborCost());
        io.print("Tax: " + order.getTax());
        io.print("Total " + order.getTotal());
        Order setOrderInfo = new Order(order.getOrderNumber());
        setOrderInfo.setCustomerName(order.getCustomerName());
        setOrderInfo.setState(order.getState());
        setOrderInfo.setArea(order.getArea());
    }

    public int orderNumber() {
        return io.readInt("Enter Order Number");
    }

//    public String stateInput() {
//        String State = io.readString(" | Enter State: ").toUpperCase();
//        return State;
//    }
//    public void setStateInput(String state) {
//        this.state = state;
//    }
//    public String productInput() {
//        String productType = io.readString(" | Enter product type: ");
//        if (productType.equals("")) {
//            return "";
//        }
//        return productType.substring(0, 1).toUpperCase() + productType.substring(1);
//    }
//    public void setProductInput(String productType) {
//        this.productType = productType;
//    }
    public boolean userConfirmaton() {
        return io.readString("Confirm your selection ? Y/N ").equalsIgnoreCase("Y");

    }

    public void displayState(List<Tax> states) {
        io.print("Please select from the following states:");
        states.stream().forEach(s -> io.print(s.getState()));
    }

    public void displayProducts(List<Product> products) {
        io.print("Please select from the following product types:");
        products.stream().forEach(s -> io.print(s.getProductType()));
    }

    public String dateSelection() {
        String date = io.readStringDate("Enter a date in format MMDDYYYY: ");
        return date;
    }

    public void exceptionMessage(String message) {
        io.print(message + ":");
    }

    public void message(String msg) {
        io.print(msg);
    }

}
