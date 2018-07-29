/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMastery.dao;

import flooringmastery.dao.FlooringMasteryDao;
import flooringmastery.dao.FlooringMasteryPersistenceException;
import flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Y
 */
public class FlooringMasteryDaoImplTest {

    FlooringMasteryDao dao;// = new FlooringMasteryDaoImpl();

    public FlooringMasteryDaoImplTest() throws FlooringMasteryPersistenceException {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        dao = ctx.getBean("dao", FlooringMasteryDao.class);
        dao.loadProducts();
        dao.loadTaxes();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getOrdersList method, of class FlooringMasteryDaoImpl.
     */
    @Test
    public void testGetOrdersList() throws Exception {
        String date = "Test_09172017";
        assertNotNull(dao.getOrdersList(date));
        
        try {
        assertNotNull(dao.getOrdersList(date+"Invalid date"));
        fail("Exception expected");
        } catch (Exception e ) {
            
        
        }
        
        
        
    }

    /**
     * Test of findOrder method, of class FlooringMasteryDaoImpl.
     */
    @Test
    public void testFindOrder() throws Exception {
        String fileName = "Test_09172017";
        dao.loadOrders(fileName);
        assertNotNull(dao.findOrder("Test_09172017", 6));
        assertNull(dao.findOrder("Test_09172017", 100));
    }

    /**
     * Test of addOrder method, of class FlooringMasteryDaoImpl.
     */
    @Test
    public void testAddAndRemoveOrderAndtotalCostCalculation() throws Exception {
        int orderNumber = 66;
        Order testOrder = new Order(orderNumber);
        testOrder.setOrderNumber(orderNumber);
        testOrder.setArea(6);
        testOrder.setProductType("Wood");
        testOrder.setState("PA");
        testOrder.setCustomerName("TestCustomer");
        dao.addOrder(testOrder);
        String todaysDate = todaysDateAsString();

// test add order         
        assertNotNull(dao.findOrder(todaysDate, orderNumber));

// test  DTO testSetAndCalculateCost
        assertEquals(dao.findOrder(todaysDate, orderNumber).getTotal(), new BigDecimal("63.41"));

// test remove order        
        dao.removeOrder(todaysDate, orderNumber);
        assertNull(dao.findOrder(todaysDate, orderNumber));
    }

    /**
     * Test of editOrder method, of class FlooringMasteryDaoImpl.
     */
    @Test
    public void testEditOrder() throws FlooringMasteryPersistenceException {
// tested on Service layer testing
    }

    /**
     * Test of nextOrderNumber method, of class FlooringMasteryDaoImpl.
     */
    @Test
    public void testNextOrderNumber() throws Exception {
// this method also tests loadOrder method 
    }

    /**
     * Test of loadOrders method, of class FlooringMasteryDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testLoadOrders() throws Exception {

// tested in next order number.
    }

    /**
     * Test of writeAllLoadedOrders method, of class FlooringMasteryDaoImpl.
     */
    @Test
    public void testWriteAllLoadedOrders() throws Exception {
    }

    /**
     * Test of getAllTax method, of class FlooringMasteryDaoImpl.
     */
    @Test
    public void testLoadAndGetAllTax() {
        assertEquals(dao.getAllTax().size(), 4);
    }

    private String todaysDateAsString() {
        LocalDate date = LocalDate.now();
        String formattedTodaysDate = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        return formattedTodaysDate;
    }

}
