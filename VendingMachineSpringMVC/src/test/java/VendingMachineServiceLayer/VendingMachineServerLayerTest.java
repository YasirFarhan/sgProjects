/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineServiceLayer;

import VendingMachine.ServiceLayer.ServiceLayer;
import VendingMachine.ServiceLayer.ServiceLayerImpl;
import VendingMachine.dao.VendingMachineDao;
import VendingMachine.dao.VendingMachineDaoImpl;
import VendingMachine.dao.VendingMachinePersistanceException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Farhan
 */
public class VendingMachineServerLayerTest {

    ServiceLayer myService;

    public VendingMachineServerLayerTest() throws VendingMachinePersistanceException {

        VendingMachineDao dao = new VendingMachineDaoImpl(true);
        myService = new ServiceLayerImpl(dao);
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        myService = ctx.getBean("service", ServiceLayer.class);
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws VendingMachinePersistanceException {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getChangeInDollars method, of class ServerLayer.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testReturnChangeInDollars() throws Exception {
        // tested in dao
    }

    /**
     * Test of updateInventoryLevel method, of class ServerLayer.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateInventoryLevel() throws Exception {
// tested in dao 
    }

    /**
     * Test of verifySelectedItem method, of class ServerLayer.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testVerifySelectedItem() throws Exception {
        long itemId = myService.verifySelectedItem(1);
        assertEquals(1, itemId);
        try {
            myService.verifySelectedItem(500);
            fail("Expected result");
        } catch (Exception e) {
            
        }
    }
}
