 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachine.ServiceLayer;

import VendingMachine.dao.VendingMachineDao;
import VendingMachine.dao.VendingMachinePersistanceException;
import VendingMachine.dto.ConvertingChange;
import VendingMachine.dto.VendingMachineItem;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Farhan
 */
public class ServiceLayerImpl implements ServiceLayer {

    VendingMachineDao myDao;
    double totalMoney;
    private ConvertingChange change;
    private long selectedItem;

    public ServiceLayerImpl(VendingMachineDao myDao) {
        this.myDao = myDao;
    }

    @Override
    public long verifySelectedItem(long id) throws
            VendingMachinePersistanceException, ItemDoNotExistException {

        if (myDao.getItem(id) == null) {
            throw new ItemDoNotExistException(
                    "Item Does Not Exist : "
            );
        }
        return id;
    }

    @Override
    public BigDecimal returnChangeInDollars(long id, BigDecimal amount)
            throws InsuficientFundsException, NoInventoryException, VendingMachinePersistanceException {
        BigDecimal itemPrice = myDao.getItem(id).getPrice();
        BigDecimal check = itemPrice.subtract(amount);
        if (itemPrice.compareTo(amount) > 0) {
            throw new InsuficientFundsException(
                    "Insufficient funds please add " + check
            );
        }
        return myDao.getChangeInDollars(id, amount);
    }

    @Override
    public List<VendingMachineItem> getAllItems() throws VendingMachinePersistanceException {
        
        return myDao.getAllItems().stream()
                .collect(Collectors.toList());
    }

    @Override
    public void updateInventoryLevel(long id) throws
            VendingMachinePersistanceException, NoInventoryException {

        if (myDao.getItem(id) == null || myDao.getItem(id).getInventoryLevel() <= 0) {
            throw new NoInventoryException(
                    "  Item Not Available :"
            );
        } else {
            myDao.updateInventoryLevel(id);
        }
        //myDao.writeVendingMachine();
    }

    @Override
    public void loadFile() throws VendingMachinePersistanceException {
        myDao.loadVendingMachineFile();
    }

    @Override
    public void writeVendingMachine() throws VendingMachinePersistanceException {
        myDao.writeVendingMachine();
    }

    @Override
    public double moneyAdded(double moneyAdded) {
        if (moneyAdded == 0) {
            return totalMoney = 0;
        }
        totalMoney = totalMoney + moneyAdded;
        return totalMoney;
    }

    @Override
    public double getTotalMoney() {
        return totalMoney;
    }

    @Override
    public List<VendingMachineItem> getAnItem(long id) throws VendingMachinePersistanceException {
        this.selectedItem = id;
        return getAllItems().stream().filter(c -> c.getId() == id).collect(Collectors.toList());
    }

    @Override
    public long getSelectedItem() {
        return selectedItem;
    }

    @Override
    public ConvertingChange change(BigDecimal amount) {
        ConvertingChange changeCalculation = new ConvertingChange();
        changeCalculation.calculatingChange(amount);
        return change = changeCalculation;
    }

    @Override
    public ConvertingChange getChange() {
        return change;
    }

}
