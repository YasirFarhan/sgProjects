   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachine.dao;

import VendingMachine.dto.VendingMachineItem;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Farhan
 */
public class VendingMachineDaoImpl implements VendingMachineDao {
    public static final String VENDING_FILE = "D:\\SG DDWA\\yasir-farhan-individual-work\\VendingMachineSpringMVC\\Items.txt";
    public static final String VENDING_FILE_TEST = "vendingMachineTest.txt";
    public static final String DELIMITER = "::";
    String FILE;

    public VendingMachineDaoImpl(boolean isTest) {
        if (isTest) {
            FILE = VENDING_FILE_TEST;
        } else {
            FILE = VENDING_FILE;
        }
    }

    public VendingMachineDaoImpl() {
        FILE = VENDING_FILE;
    }

    private Map<Long, VendingMachineItem> vendingMachineMap = new HashMap<>();

    @Override
    public BigDecimal getChangeInDollars(long id, BigDecimal amount) throws VendingMachinePersistanceException {
        BigDecimal itemPrice = vendingMachineMap.get(id).getPrice();
        return amount.subtract(itemPrice);
    }

    @Override
    public void updateInventoryLevel(long id) throws VendingMachinePersistanceException {
        getItem(id).setInventoryLevel(
                getItem(id).getInventoryLevel() - 1);
    }

    @Override
    public VendingMachineItem getItem(long id) throws VendingMachinePersistanceException {
        return vendingMachineMap.get(id);
    }

    @Override
    public List<VendingMachineItem> itemList() throws VendingMachinePersistanceException {
        return new ArrayList<>(vendingMachineMap.values());
    }

    @Override
    public void loadVendingMachineFile() throws VendingMachinePersistanceException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistanceException(
                    "*** Could not data*** ", e);
        }
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            VendingMachineItem currentItem = new VendingMachineItem(Long.parseLong(currentTokens[0]));
            currentItem.setId(Long.parseLong(currentTokens[0]));
            currentItem.setName(currentTokens[1]);
            currentItem.setPrice(new BigDecimal(currentTokens[2]));
            currentItem.setInventoryLevel(Integer.parseInt(currentTokens[3]));
            vendingMachineMap.put(currentItem.getId(), currentItem);
        }
        scanner.close();
    }

    @Override
    public void writeVendingMachine() throws VendingMachinePersistanceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(VENDING_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistanceException(
                    "Could not save item data.", e);
        }
        List<VendingMachineItem> itemList = this.itemList();
        itemList.stream().map((currentItem) -> {
            out.println(currentItem.getId() + DELIMITER
                    + currentItem.getName() + DELIMITER
                    + currentItem.getPrice() + DELIMITER
                    + currentItem.getInventoryLevel()
            );
            return currentItem;
        }).forEach((_item) -> {
            out.flush();
        });
        out.close();
    }

    @Override
    public List<VendingMachineItem>
            getAllItems() throws VendingMachinePersistanceException {
        return vendingMachineMap.values()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public VendingMachineItem removeItem(long id) throws VendingMachinePersistanceException {
        return vendingMachineMap.remove(id);
    }

    @Override
    public VendingMachineItem addItem(VendingMachineItem itemInfo) throws VendingMachinePersistanceException {
        return vendingMachineMap.put(itemInfo.getId(), itemInfo);
    }

    @Override
    public void sqlUpdateQuery(String preparedStatement, long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
