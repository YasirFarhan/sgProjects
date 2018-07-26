   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachine.dto;

import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Farhan
 */
public class VendingMachineItem {

    private long id;
    private String name;
    @NotEmpty(message = "Sold Out!!")
    @NotNull(message = "Sold Out!!") 
    private int inventoryLevel;
    private BigDecimal price;

    public VendingMachineItem(long id) {
        this.id = id;
    }

//    public VendingMachineItem(String name) {
//        this.name = name;
//    }
    public VendingMachineItem() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInventoryLevel() {
        return inventoryLevel;
    }

    public void setInventoryLevel(int InventoryLevel) {
        this.inventoryLevel = InventoryLevel;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + this.inventoryLevel;
        hash = 67 * hash + Objects.hashCode(this.price);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VendingMachineItem other = (VendingMachineItem) obj;
        if (this.inventoryLevel != other.inventoryLevel) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        return true;
    }
}
