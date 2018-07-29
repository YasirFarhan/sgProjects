 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.List;
import model.Organization;
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
public class OrganizationDaoTest {

    OrganizationDao dao;

    public OrganizationDaoTest() {
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
        dao = ctx.getBean("OrganizationDao", OrganizationDao.class);
        List<Organization> org = dao.getAllOrganizations();
        org.forEach((o) -> {
            dao.removeOrganization(o.getOrgId());
        });
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllOrganizations method, of class OrganizationDao.
     */
    @Test
    public void testGetAllOrganizations() {
        createFirstOrganization();
        createSecondOrganization();
        assertEquals(dao.getAllOrganizations().size(), 2);

    }

    /**
     * Test of getSingleOrganization method, of class OrganizationDao.
     */
    @Test
    public void testGetSingleOrganization() {
        Organization org1 = createFirstOrganization();
        Organization org2 = createSecondOrganization();
        assertEquals(dao.getSingleOrganization(org1.getOrgId()).getOrgName(), org1.getOrgName());
    }

    /**
     * Test of createOrganization method, of class OrganizationDao.
     */
    @Test
    public void testCreateOrganization() {
        Organization org1 = createFirstOrganization();
        assertNotNull(dao.getAllOrganizations());
    }

    /**
     * Test of updateOrganization method, of class OrganizationDao.
     */
    @Test
    public void testUpdateOrganization() {
        Organization org1 = createFirstOrganization();
        org1.setOrgName("Air-Org_Updated");
        dao.updateOrganization(org1);
        assertEquals(dao.getSingleOrganization(org1.getOrgId()).getOrgName(), "Air-Org_Updated");
    }

    /**
     * Test of removeOrganization method, of class OrganizationDao.
     */
    @Test
    public void testRemoveOrganization() {
        Organization org1 = createFirstOrganization();
        Organization org = createSecondOrganization();
        assertEquals(dao.getAllOrganizations().size(), 2);
        dao.removeOrganization(org1.getOrgId());
        assertEquals(dao.getAllOrganizations().size(), 1);
    }

    // helper methods
    private Organization createFirstOrganization() {
        Organization org1 = new Organization();
        org1.setOrgName("Air-Org");
        org1.setOrgDescription("This Is Air Defence");
        org1.setPhone("123456");
        org1.setOrgAddress("Airg-Org-Address");
        dao.createOrganization(org1);
        return org1;
    }

    private Organization createSecondOrganization() {
        Organization org1 = new Organization();
        org1.setOrgName("Land-Org");
        org1.setOrgDescription("This Is Land Defence");
        org1.setPhone("987654");
        org1.setOrgAddress("Land-Org-Address");
        dao.createOrganization(org1);
        return org1;
    }

}
