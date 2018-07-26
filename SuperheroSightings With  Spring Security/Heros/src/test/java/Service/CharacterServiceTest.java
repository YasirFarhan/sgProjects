/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Dao.SearchTerm;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import model.CharacterDetails;
import model.CharacterLocationBridgeTable;
import model.Location;
import model.Organization;
import model.RecentSightings;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Farhan
 */
public class CharacterServiceTest {

    public CharacterServiceTest() {
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
     * Test of getAllCharacters method, of class CharacterService.
     */
    @Test
    public void testGetAllCharacters() {

    }

    /**
     * Test of getSingleCharacter method, of class CharacterService.
     */
    @Test
    public void testGetSingleCharacter() {

    }

    /**
     * Test of createCharacter method, of class CharacterService.
     */
    @Test
    public void testCreateCharacter() {

    }

    /**
     * Test of updateCharacter method, of class CharacterService.
     */
    @Test
    public void testUpdateCharacter() {

    }

    /**
     * Test of removeCharacter method, of class CharacterService.
     */
    @Test
    public void testRemoveCharacter() {

    }

    /**
     * Test of getCharacterBySightingLocation method, of class CharacterService.
     */
    @Test
    public void testGetCharacterBySightingLocation() {

    }

    /**
     * Test of getCharacterByOrganization method, of class CharacterService.
     */
    @Test
    public void testGetCharacterByOrganization() {

    }

    /**
     * Test of getCharacterBySightingDate method, of class CharacterService.
     */
    @Test
    public void testGetCharacterBySightingDate() {

    }

    /**
     * Test of insertCharacterOrganization method, of class CharacterService.
     */
    @Test
    public void testInsertCharacterOrganization() {

    }

    /**
     * Test of insertCharacterSighting method, of class CharacterService.
     */
    @Test
    public void testInsertCharacterLocation() {

    }

    /**
     * Test of getRecentSightings method, of class CharacterService.
     */
    @Test
    public void testGetRecentSightings() {

    }

    /**
     * Test of findOrganizationCharacter method, of class CharacterService.
     */
    @Test
    public void testFindOrganizationCharacter() {

    }

    /**
     * Test of findLocationCharacter method, of class CharacterService.
     */
    @Test
    public void testFindLocationCharacter() {

    }

    /**
     * Test of removeLocationFromCharacter method, of class CharacterService.
     */
    @Test
    public void testRemoveLocationFromCharacter() {

    }

    public class CharacterServiceImpl implements CharacterService {

        public List<CharacterDetails> getAllCharacters() {
            return null;
        }

        public CharacterDetails getSingleCharacter(long id) {
            return null;
        }

        public CharacterDetails createCharacter(CharacterDetails c) {
            return null;
        }

        public void updateCharacter(CharacterDetails c) {
        }

        public void removeCharacter(long id) {
        }

        public List<CharacterDetails> getCharacterBySightingLocation(Location loc) {
            return null;
        }

        public List<CharacterDetails> getCharacterByOrganization(Organization org) {
            return null;
        }

        public List<CharacterDetails> getCharacterBySightingDate(LocalDate date) {
            return null;
        }

        public void insertCharacterOrganization(CharacterLocationBridgeTable bt) {
        }

        public void insertCharacterSighting(CharacterLocationBridgeTable bt) {
        }

        public List<RecentSightings> getRecentSightings() {
            return null;
        }

        public void findOrganizationCharacter(List<Organization> org) {
        }

        public void findLocationCharacter(List<Location> loc) {
        }

        public void removeLocationFromCharacter(long characterId, long locationId) {
        }

        @Override
        public List<CharacterDetails> search(Map<SearchTerm, String> criterialMap) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void removeOrgFromCharacter(long characterId, long orgId) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

}
