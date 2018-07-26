/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringmastery.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Y
 */
public class Order {

    private String date;
    private int orderNumber;
    private String customerName;
    private String State;
    private double taxRate;
    private String productType;
    private double area;
    private BigDecimal costPerSquareFoot; // Material Cost  / sqft
    private BigDecimal laborCostPerSquareFoot; // labor cost / sqft
    private BigDecimal materialCost;  // total materialcost 
    private BigDecimal laborCost; // total laborCost
    private BigDecimal tax; // total tax
    private BigDecimal total;  // total cost 

    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setAndCalculateCost(double taxRate, BigDecimal costPerSquareFoot, BigDecimal laborCostPerSquareFoot) {
        this.taxRate = taxRate;
        this.costPerSquareFoot = costPerSquareFoot;
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
        this.materialCost = (costPerSquareFoot.multiply(new BigDecimal(this.area)))
                .setScale(2, RoundingMode.HALF_UP);
        this.laborCost = (laborCostPerSquareFoot.multiply(new BigDecimal(this.area)))
                .setScale(2, RoundingMode.HALF_UP);
        this.tax = ((this.laborCost.add(this.materialCost)).multiply(new BigDecimal(this.taxRate / 100))).setScale(2, RoundingMode.HALF_UP);
        this.total = (this.tax.add(this.materialCost).add(this.laborCost)).setScale(2, RoundingMode.HALF_UP);
        // BigDecimal totalCost = this.total;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        this.State = state;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
