 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.CharacterDetails;
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
public class CharacterDaoTest {

    CharacterDao dao;

    public CharacterDaoTest() {
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
        dao = ctx.getBean("CharacterDao", CharacterDao.class);
        List<CharacterDetails> characters = dao.getAllCharacters();
        characters.forEach((c) -> {
            dao.removeCharacter(c.getCharacterId());
        });

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllCharacters method, of class CharacterDao.
     */
    @Test
    public void testCreateAndGetAllCharacters() {
        createFirstCharacter();
        createSecondCharacter();
        assertEquals(dao.getAllCharacters().size(), 2);
    }

    /**
     * Test of getSingleCharacter method, of class CharacterDao.
     */
    @Test
    public void testGetSingleCharacter() {
        CharacterDetails c1 = createFirstCharacter();
        assertEquals(dao.getSingleCharacter(c1.getCharacterId()).getCharacterName(), "TestName");
    }

    /**
     * Test of createCharacter method, of class CharacterDao.
     */
    @Test
    public void testCreateCharacter() {
        // tested above testCreateAndGetAllCharacters();

    }

    /**
     * Test of getRecentSightings method, of class CharacterDao.
     */
    @Test
    public void testGetRecentSightings() {
    }

    /**
     * Test of updateCharacter method, of class CharacterDao.
     */
    @Test
    public void testUpdateCharacter() {
        CharacterDetails c1 = new CharacterDetails();
        c1.setCharacterDescription("TestUpdateDescription");
        c1.setCharacterName("TestName");
        c1.setCharacterType("HERO");
        c1.setImg("TestImg");
        c1.setSuperPower("TestPower");
        long c1Id = dao.createCharacter(c1).getCharacterId();
        c1.setCharacterName("UpdatedName");
        c1.setSuperPower("UpdatedPower");
        dao.updateCharacter(c1);
        assertEquals(dao.getSingleCharacter(c1Id).getCharacterName(), "UpdatedName");
        assertEquals(dao.getSingleCharacter(c1Id).getSuperPower(), "UpdatedPower");
    }

    /**
     * Test of removeCharacter method, of class CharacterDao.
     */
    @Test
    public void testRemoveCharacter() {
        CharacterDetails c1 = createFirstCharacter();
        createSecondCharacter();
        assertEquals(dao.getAllCharacters().size(), 2);
        dao.removeCharacter(c1.getCharacterId());
        assertEquals(dao.getAllCharacters().size(), 1);
    }

    /**
     * Test of search method, of class CharacterDao.
     *
     */
    @Test
    public void search() {
        createFirstCharacter();
        createSecondCharacter();
        Map<SearchTerm, String> criteriaMap = new HashMap<>();
        criteriaMap.put(SearchTerm.CharacterType, "VILLAIN");
        criteriaMap.put(SearchTerm.SuperPower, "TestPower2");
        List<CharacterDetails> cList = dao.search(criteriaMap);
        assertEquals(1, cList.size());
    }

    /**
     * Test of getCharacterBySightingLocation method, of class CharacterDao.
     */
    @Test
    public void testGetCharacterBySightingLocation() {

    }

    /**
     * Test of getCharacterByOrganization method, of class CharacterDao.
     */
    @Test
    public void testGetCharacterByOrganization() {

    }

    /**
     * Test of getCharacterBySightingDate method, of class CharacterDao.
     */
    @Test
    public void testGetCharacterBySightingDate() {

    }

    /**
     * Test of insertCharacterOrganization method, of class CharacterDao.
     */
    @Test
    public void testInsertCharacterOrganization() {

    }

    /**
     * Test of insertCharacterSighting method, of class CharacterDao.
     */
    @Test
    public void testInsertCharacterocation() {

    }

    /**
     * Test of findOrganizationCharacter method, of class CharacterDao.
     */
    @Test
    public void testFindOrganizationCharacter() {

    }

    /**
     * Test of findLocationCharacter method, of class CharacterDao.
     */
    @Test
    public void testFindLocationCharacter() {

    }

    /**
     * Test of removeLocationFromCharacter method, of class CharacterDao.
     */
    @Test
    public void testRemoveLocationFromCharacter() {

    }
// helper Methods

    private CharacterDetails createFirstCharacter() {
        CharacterDetails c1 = new CharacterDetails();
        c1.setCharacterDescription("TestDesc1");
        c1.setCharacterName("TestName");
        c1.setCharacterType("HERO");
        c1.setImg("TestImg");
        c1.setSuperPower("TestPower");
        return dao.createCharacter(c1);
    }

    private CharacterDetails createSecondCharacter() {
        CharacterDetails c2 = new CharacterDetails();
        c2.setCharacterDescription("TestDesc2");
        c2.setCharacterName("TestName2");
        c2.setCharacterType("VILLAIN");
        c2.setImg("TestImg2");
        c2.setSuperPower("TestPower2");
        return dao.createCharacter(c2);
    }
}
