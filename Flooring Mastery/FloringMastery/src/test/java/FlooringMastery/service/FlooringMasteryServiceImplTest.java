/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMastery.service;

import flooringmastery.dao.AuditDao;
import flooringmastery.dao.AuditDaoStubImpl;
import flooringmastery.dao.FloodingMasteryInvalidStateException;
import flooringmastery.dao.FlooringMasteryDao;
import flooringmastery.dao.FlooringMasteryDaoStubImpl;
import flooringmastery.dao.FlooringMasteryInvalidProductException;
import flooringmastery.dao.FlooringMasteryOrderDoesNotExistException;
import flooringmastery.dao.FlooringMasteryPersistenceException;
import flooringmastery.dto.Order;
import flooringmastery.service.FlooringMasteryService;
import flooringmastery.service.FlooringMasteryServiceImpl;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Y
 */
public class FlooringMasteryServiceImplTest {

//    String Test_File_Date = "Test_09172017";
    FlooringMasteryService service;
    String date = "09232017";

    public FlooringMasteryServiceImplTest() throws FlooringMasteryPersistenceException {

//        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        service = ctx.getBean("serviceLayer", FlooringMasteryService.class);

        AuditDao auditDao = new AuditDaoStubImpl();
        FlooringMasteryDao dao = new FlooringMasteryDaoStubImpl();
        service = new FlooringMasteryServiceImpl(dao, auditDao);
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
     * Test of editOrder method, of class FlooringMasteryServiceImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testEditOrder() throws Exception {
        int orderNumber = 1;
        Order originalOrder = service.findOrder(date, orderNumber);

        Order editOrder = new Order(orderNumber);
        editOrder.setOrderNumber(orderNumber);
        editOrder.setArea(6);
        editOrder.setProductType("");
        editOrder.setState("");
        editOrder.setDate("");
        editOrder.setCustomerName("onlyOrderEdited");

        service.editOrder(originalOrder, editOrder);
        assertEquals("Wood", service.findOrder(date, orderNumber).getProductType());
        assertEquals("IN", service.findOrder(date, orderNumber).getState());
        assertEquals("onlyOrderEdited", service.findOrder(date, orderNumber).getCustomerName());
    }

    /**
     * Test of getOrdersList method, of class FlooringMasteryServiceImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetOrdersList() throws Exception {
// tested in DaoImplTest
    }

    /**
     * Test of productValidation method, of class FlooringMasteryServiceImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testProductValidation() throws Exception {
        try {
            service.productValidation("Wood");
        } catch (FlooringMasteryInvalidProductException | FlooringMasteryPersistenceException e) {
            fail("exception was not expected");
        }
        try {
            service.productValidation("invalid product");
            fail("exception was not thrown");
        } catch (FlooringMasteryInvalidProductException | FlooringMasteryPersistenceException e) {
            return;
        }
    }

    /**
     * Test of addOrder method, of class FlooringMasteryServiceImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAddOrder() throws Exception {
        // tested on daoImpl Test
    }

    /**
     * Test of removeOrder method, of class FlooringMasteryServiceImpl.
     */
    @Test
    public void testRemoveOrder() {
        // tested on daoImpl Test
    }

    /**
     * Test of findOrder method, of class FlooringMasteryServiceImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testFindOrderAndOrderNumberValidation() throws Exception {
        assertNotNull(service.findOrder(date, 1));
        try {
            assertNotNull(service.findOrder(date, 3));
            fail("exception expected");
        } catch (FlooringMasteryOrderDoesNotExistException | FlooringMasteryPersistenceException e) {
            return;
        }
    }

    /**
     * Test of stateValidation method, of class FlooringMasteryServiceImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testStateValidation() throws Exception {

        try {
            service.stateValidation("IN");
        } catch (FloodingMasteryInvalidStateException | FlooringMasteryPersistenceException e) {
            fail("exception was not expected");
        }

        try {
            service.stateValidation("invalid state");
            fail("exception was not thrown");
        } catch (FloodingMasteryInvalidStateException | FlooringMasteryPersistenceException e) {
            return;
        }
    }

}
