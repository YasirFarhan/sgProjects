/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachine.ServiceLayer;

import VendingMachine.dao.VendingMachinePersistanceException;
import VendingMachine.dto.ConvertingChange;
import VendingMachine.dto.VendingMachineItem;
import java.math.BigDecimal;  
import java.util.List;

/**
 *
 * @author Farhan
 */
public interface ServiceLayer {

    public long getSelectedItem();

    public ConvertingChange getChange();

    public double getTotalMoney();

    public BigDecimal returnChangeInDollars(long id, BigDecimal amount)
            throws InsuficientFundsException, NoInventoryException, VendingMachinePersistanceException;

    public List<VendingMachineItem> getAllItems() throws VendingMachinePersistanceException;

    public List<VendingMachineItem> getAnItem(long id) throws VendingMachinePersistanceException;

    public void updateInventoryLevel(long id) throws VendingMachinePersistanceException, NoInventoryException;

    public long verifySelectedItem(long id) throws VendingMachinePersistanceException, ItemDoNotExistException;

    public double moneyAdded(double moneyAdded);

    public void loadFile() throws VendingMachinePersistanceException;

    public void writeVendingMachine() throws VendingMachinePersistanceException;

    public ConvertingChange change(BigDecimal amount);
}
