/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachine.dao;

import VendingMachine.dto.VendingMachineItem;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Farhan
 */
public interface VendingMachineDao {

    public BigDecimal getChangeInDollars(long id, BigDecimal amount) throws VendingMachinePersistanceException;

    public VendingMachineItem getItem(long id) throws VendingMachinePersistanceException;

    List<VendingMachineItem> itemList() throws VendingMachinePersistanceException;

    public List<VendingMachineItem> getAllItems() throws VendingMachinePersistanceException;

    public VendingMachineItem addItem(VendingMachineItem itemInfo) throws VendingMachinePersistanceException;

    public void sqlUpdateQuery(String preparedStatement, long id);

    public void updateInventoryLevel(long id) throws VendingMachinePersistanceException;

    public VendingMachineItem removeItem(long id) throws VendingMachinePersistanceException;

    //public void loadVendingMachineFile() throws VendingMachinePersistanceException;
    public void loadVendingMachineFile() throws VendingMachinePersistanceException;

    public void writeVendingMachine() throws VendingMachinePersistanceException;
//    public void writeVendingMachine() throws VendingMachinePersistanceException;
}
