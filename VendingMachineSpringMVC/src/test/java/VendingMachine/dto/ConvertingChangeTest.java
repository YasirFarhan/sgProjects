/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachine.dto;

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
public class ConvertingChangeTest {
    ConvertingChange change; // = new ConvertingChange();
    
    
      
      public ConvertingChangeTest() {
          ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
       change = ctx.getBean("change", ConvertingChange.class); 
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
     * Test of calculatingChange method, of class ConvertingChange.
     */
    @Test
    public void testCalculatingChange() {
        change.calculatingChange(new BigDecimal("3.94"));
        assertEquals(new BigDecimal("15"), change.getQuarterChange());
        assertEquals(new BigDecimal("1"), change.getDimesChange());
        assertEquals(new BigDecimal("1"), change.getNickelsChange());
        assertEquals(new BigDecimal("4"), change.getPenniesChange());
    }

    /**
     * Test of getQuarterChange method, of class ConvertingChange.
     */
    @Test
    public void testGetQuarterChange() {
//tested in CalculatingChange method
    }

    /**
     * Test of getDimesChange method, of class ConvertingChange.
     */
    @Test
    public void testGetDimesChange() {
        //tested in CalculatingChange method
    }

    /**
     * Test of getNickelsChange method, of class ConvertingChange.
     */
    @Test
    public void testGetNickelsChange() {
        //tested in CalculatingChange method
    }

    /**
     * Test of getPenniesChange method, of class ConvertingChange.
     */
    @Test
    public void testGetPenniesChange() {
        //tested in CalculatingChange method
    }
}
