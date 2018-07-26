/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachine.dao;

import VendingMachine.dto.VendingMachineItem;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Farhan
 */
public class VendingMachineDaoDbImpl implements VendingMachineDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // prepared statements starts here
    private static final String SQL_SELECT_ALL_ITEMS_WITH_INVENTORY
            = "SELECT items.itemId, items.itemName,items.itemPrice, inventory.inventoryLevel "
            + " FROM items JOIN inventory ON items.itemId = inventory.itemId";

    private static final String SQL_SELECT_ITEM
            = "SELECT items.itemId, items.itemName,items.itemPrice, inventory.inventoryLevel "
            + " FROM items JOIN inventory ON items.itemId = inventory.itemId"
            + " where items.itemId = ?";

    private static final String UPDATE_INVENTORY
            = "update inventory set "
            + "inventoryLevel=? "
            + "where itemId=?";

// prepared statements ends here
    @Override
    public BigDecimal getChangeInDollars(long id, BigDecimal amount) throws VendingMachinePersistanceException {
        BigDecimal itemPrice = getItem(id).getPrice();
        return amount.subtract(itemPrice);
    }

    @Override
    public VendingMachineItem getItem(long id) throws VendingMachinePersistanceException {
        return jdbcTemplate.queryForObject(SQL_SELECT_ITEM, new ItemMapper(), id);
    }

    @Override
    public List<VendingMachineItem> itemList() throws VendingMachinePersistanceException {
        return jdbcTemplate.query(SQL_SELECT_ALL_ITEMS_WITH_INVENTORY, new ItemMapper());
    }

    @Override
    public List<VendingMachineItem> getAllItems() throws VendingMachinePersistanceException {
        return jdbcTemplate.query(SQL_SELECT_ALL_ITEMS_WITH_INVENTORY, new ItemMapper());
    }

    @Override
    public VendingMachineItem addItem(VendingMachineItem itemInfo) throws VendingMachinePersistanceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateInventoryLevel(long id) throws VendingMachinePersistanceException {
        int inventoryLevel = getItem(id).getInventoryLevel() - 1;
        jdbcTemplate.update(UPDATE_INVENTORY, inventoryLevel, id);
    }

    @Override
    public VendingMachineItem removeItem(long id) throws VendingMachinePersistanceException {
        // ONLY USED FOR testing
        final String SQL_REMOVE_ITEM
                = "delete from inventory where itemId=? ; "
                + "delete from items where itemId_id=? ; ";
        jdbcTemplate.update(SQL_REMOVE_ITEM, id);

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadVendingMachineFile() throws VendingMachinePersistanceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeVendingMachine() throws VendingMachinePersistanceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sqlUpdateQuery(String preparedStatement, long id) {
        jdbcTemplate.update(preparedStatement, id);
    }

    // RowMapper for jdbc -- nested class
    private static final class ItemMapper implements RowMapper<VendingMachineItem> {

        @Override
        public VendingMachineItem mapRow(ResultSet rs, int i) throws SQLException {
            VendingMachineItem item = new VendingMachineItem();
            item.setId(rs.getLong("itemId"));
            item.setInventoryLevel(rs.getInt("inventoryLevel"));
            item.setName(rs.getString("itemName"));
            item.setPrice(rs.getBigDecimal("itemPrice"));
            return item;
        }
    }

}
