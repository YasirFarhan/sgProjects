 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.DvdLibrary;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Farhan
 */
public class DvdDaoTest {
 
    DvdDao dao;

    public DvdDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        dao = ctx.getBean("DvdDao", DvdDao.class);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addDvd method, of class DvdDao.
     */
    @Test
    public void testAddDvd() {

    }

    /**
     * Test of getAllDvds method, of class DvdDao.
     */
    @Test
    public void testGetAllDvds() {
        assertNotNull(dao.getAllDvds());
        assertEquals(dao.getAllDvds().size(), 5);

    }

    /**
     * Test of getDvdInfo method, of class DvdDao.
     */
    @Test
    public void testGetDvdInfo() {
        long id = 1;
        assertNotNull(dao.getDvdInfo(id));
        assertNotNull(dao.getDvdInfo(id).getTitle(), "FirstMovie");

    }

    /**
     * Test of removeDvd method, of class DvdDao.
     */
    @Test
    public void testRemoveDvd() {

    }

    /**
     * Test of editDvd method, of class DvdDao.
     */
    @Test
    public void testEditDvd() {
        long id = 1;
        DvdLibrary dvd = dao.getDvdInfo(id);
        String originlaTitle = dao.getDvdInfo(id).getTitle();
        dvd.setTitle("Changed");
        dao.editDvd(dvd);
        assertEquals(dao.getDvdInfo(id).getTitle(), "Changed");
        dvd.setTitle(originlaTitle);
        dao.editDvd(dvd);
        assertEquals(dao.getDvdInfo(id).getTitle(), originlaTitle);
    }

}
