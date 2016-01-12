/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.internettoegang;

import bank.bankieren.Bank;
import bank.bankieren.IBank;
import java.rmi.RemoteException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author milton
 */
public class BalieTest
{
    
    public BalieTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of openRekening method, of class Balie.
     */
    @Test
    public void testOpenRekening() throws RemoteException
    {
        System.out.println("openRekening");
        
        String naam = "testName";
        String plaats = "testPlaats";
        String wachtwoord = "123456";
        Balie instance = new Balie(new Bank("testBank"));
        int expResult = 8;
        int result = instance.openRekening(naam, plaats, wachtwoord).length();
        assertEquals(expResult, result);
        
        naam = "";
        plaats = "testPlaats";
        wachtwoord = "123456";
        instance = new Balie(new Bank("testBank"));
        String expResult2 = null;
        String result2 = instance.openRekening(naam, plaats, wachtwoord);
        assertEquals(expResult2, result2);
        
        naam = "testNaam";
        plaats = "";
        wachtwoord = "123456";
        instance = new Balie(new Bank("testBank"));
        expResult2 = null;
        result2 = instance.openRekening(naam, plaats, wachtwoord);
        assertEquals(expResult2, result2);
        
        naam = "testNaam";
        plaats = "testPlaats";
        wachtwoord = "123";
        instance = new Balie(new Bank("testBank"));
        expResult2 = null;
        result2 = instance.openRekening(naam, plaats, wachtwoord);
        assertEquals(expResult2, result2);
        
        naam = "testNaam";
        plaats = "testPlaats";
        wachtwoord = "123456789";
        instance = new Balie(new Bank("testBank"));
        expResult2 = null;
        result2 = instance.openRekening(naam, plaats, wachtwoord);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of logIn method, of class Balie.
     */
    @Test
    public void testLogIn() throws Exception
    {
        System.out.println("logIn");
        
        String accountnaam = "testName";
        String wachtwoord = "123456";
        Balie instance = new Balie(new Bank("testBank"));
        
        instance.openRekening(accountnaam, wachtwoord, wachtwoord);
        
        String testNaam = "testName";
        String testWachtwoord = "123456";
        IBankiersessie result = instance.logIn(testNaam, testWachtwoord);
        assertNotNull(result);
        
        testNaam = "testName2";
        testWachtwoord = "123456";
        result = instance.logIn(testNaam, testWachtwoord);
        assertNull(result);
        
        testNaam = "testName";
        testWachtwoord = "1234567";
        result = instance.logIn(testNaam, testWachtwoord);
        assertNull(result);

        testNaam = "testName2";
        testWachtwoord = "1234567";
        result = instance.logIn(testNaam, testWachtwoord);
        assertNull(result);
    }
    
}
