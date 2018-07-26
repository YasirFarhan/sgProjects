 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.math.BigDecimal;
import java.util.List;
import model.Location;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Farhan
 */
public class LocationDaoTest {

    LocationDao dao;

    public LocationDaoTest() {
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
        dao = ctx.getBean("LocationDao", LocationDao.class);
        List<Location> loc = dao.getAllLocation();
        loc.forEach((l) -> {
            dao.removeLocation(l.getLocationId());
        });
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllLocation method, of class LocationDao.
     */
    @Test
    public void testGetAllLocation() {

        Location loc = new Location();
        loc.setLatitude(new BigDecimal("50"));
        loc.setLongitude(new BigDecimal("40"));
        loc.setLocationName("NewYork");
        loc.setLocationDescription("NewYorkCity");
        loc.setLocationAddress("NewYorkCityAddress");
        dao.createLocation(loc);
        assertEquals(dao.getAllLocation().size(), 1);

    }

    /**
     * Test of getSingleLocation method, of class LocationDao.
     */
    @Test
    public void testGetSingleLocation() {
        Location loc1 = createFirstLocation();
        Location loc2 = createSecondLocation();
        assertEquals(dao.getSingleLocation(loc1.getLocationId()).getLocationName(), "NewYork");
        assertEquals(dao.getSingleLocation(loc2.getLocationId()).getLocationName(), "Toronto");
    }

    /**
     * Test of createLocation method, of class LocationDao.
     */
    @Test
    public void testCreateLocation() {
// tested in testGetAllLocation
    }

    /**
     * Test of updateLocation method, of class LocationDao.
     */
    @Test
    public void testUpdateLocation() {
        Location loc1 = createFirstLocation();
        loc1.setLocationName("NewYorkUpdated");
        Location loc2 = dao.updateLocation(loc1);
        assertEquals(dao.getSingleLocation(loc2.getLocationId()).getLocationName(), "NewYorkUpdated");
    }

    /**
     * Test of removeLocation method, of class LocationDao.
     */
    @Test
    public void testRemoveLocation() {
        Location loc1 = createFirstLocation();
        Location loc2 = createSecondLocation();
        assertEquals(dao.getAllLocation().size(), 2);
        dao.removeLocation(loc1.getLocationId());
        assertEquals(dao.getAllLocation().size(), 1);

    }

// helper methods
    public Location createFirstLocation() {
        Location loc = new Location();
        loc.setLatitude(new BigDecimal("50"));
        loc.setLongitude(new BigDecimal("40"));
        loc.setLocationName("NewYork");
        loc.setLocationDescription("NewYorkCity");
        loc.setLocationAddress("NewYorkCityAddress");
        return dao.createLocation(loc);
    }

    public Location createSecondLocation() {
        Location loc2 = new Location();
        loc2.setLatitude(new BigDecimal("30"));
        loc2.setLongitude(new BigDecimal("20"));
        loc2.setLocationName("Toronto");
        loc2.setLocationDescription("Toronto");
        loc2.setLocationAddress("TorontoCityAddress");
        return dao.createLocation(loc2);
    }
}
