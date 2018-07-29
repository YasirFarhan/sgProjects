/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.List;
import model.CharacterDetails;
import model.Location;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Farhan
 */
public class LocationServiceTest {

    public LocationServiceTest() {
        // There is no business logic in service layer thus no service layer testing is requested

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
     * Test of getAllLocation method, of class LocationService.
     */
    @Test
    public void testGetAllLocation() {

    }

    /**
     * Test of getSingleLocation method, of class LocationService.
     */
    @Test
    public void testGetSingleLocation() {

    }

    /**
     * Test of createLocation method, of class LocationService.
     */
    @Test
    public void testCreateLocation() {

    }

    /**
     * Test of updateLocation method, of class LocationService.
     */
    @Test
    public void testUpdateLocation() {

    }

    /**
     * Test of removeLocation method, of class LocationService.
     */
    @Test
    public void testRemoveLocation() {

    }

    /**
     * Test of findCharacterLocations method, of class LocationService.
     */
    @Test
    public void testFindCharacterLocations() {

    }

    public class LocationServiceImpl implements LocationService {

        public List<Location> getAllLocation() {
            return null;
        }

        public Location getSingleLocation(long id) {
            return null;
        }

        public Location createLocation(Location loc) {
            return null;
        }

        public Location updateLocation(Location loc) {
            return null;
        }

        public void removeLocation(long id) {
        }

        public void findCharacterLocations(List<CharacterDetails> c) {
        }

        @Override
        public void findCharacterLocationsByDateAndTime(List<CharacterDetails> c, String sightingDate, String SightingTime) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

}
