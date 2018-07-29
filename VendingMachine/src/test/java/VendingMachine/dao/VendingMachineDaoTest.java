/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachine.dao;

import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Farhan
 */
public class VendingMachineDaoTest {

    int totalNumberOfItems = 3;
    private VendingMachineDao VendingMachineDao;// = new VendingMachineDaoImpl(true);

    public VendingMachineDaoTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineDao = ctx.getBean("VendingMachineDao", VendingMachineDao.class);

    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() {

    }

    /**
     * Test of getChangeInDollars method, of class VendingMachineDao.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetChangeInDollars() throws Exception {
        assertEquals(VendingMachineDao.getChangeInDollars(1, BigDecimal.valueOf(5)), BigDecimal.valueOf(2));
    }

    /**
     * Test of getItem method, of class VendingMachineDao.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetItem() throws Exception {
        assertNotNull(VendingMachineDao.getItem(3));
        assertEquals(VendingMachineDao.getItem(1).getPrice(), BigDecimal.valueOf(3));
        assertEquals(VendingMachineDao.getItem(3).getInventoryLevel(), 6);
    }

    /**
     * Test of itemList method, of class VendingMachineDao.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testItemList() throws Exception {
        assertNotNull(VendingMachineDao.getAllItems());
    }

    /**
     * Test of loadVendingMachineFile method, of class VendingMachineDao.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testLoadVendingMachineFile() throws Exception {
        assertEquals(VendingMachineDao.getAllItems().size(), totalNumberOfItems);
    }

    /**
     * Test of writeVendingMachine method, of class VendingMachineDao.
     */
    @Test
    public void testWriteVendingMachine() throws Exception {

    }

    /**
     * Test of getAllItems method, of class VendingMachineDao.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllItems() throws Exception {
//        VendingMachineDao.loadVendingMachineFile();
        assertEquals(VendingMachineDao.getAllItems().size(), totalNumberOfItems);
    }

    /**
     * Test of updateInventoryLevel method, of class VendingMachineDao.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateInventoryLevel() throws Exception {
        VendingMachineDao.updateInventoryLevel(1);
        assertEquals(VendingMachineDao.getItem(1).getInventoryLevel(), 2);
        // reset inventory levels 
        final String SQL_UPDATE_INVENTORY
                = "update inventory set "
                + " inventoryLevel = 3 "
                + "where inventory.itemId = ?; ";

        VendingMachineDao.sqlUpdateQuery(SQL_UPDATE_INVENTORY, 1);
    }

    /**
     * Test of sqlUpdateQuery method, of class VendingMachineDao.
     *
     * @throws VendingMachine.dao.VendingMachinePersistanceException
     */
    @Test
    public void testAddItem() throws VendingMachinePersistanceException {
//       
    }

    /**
     * Test of removeItem method, of class VendingMachineDao.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testRemoveItem() throws Exception {

    }

}
