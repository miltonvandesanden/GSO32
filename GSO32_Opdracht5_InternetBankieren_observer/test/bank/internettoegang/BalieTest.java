/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.internettoegang;

import bank.bankieren.Bank;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public void testOpenRekening()
    {
        System.out.println("openRekening");
        
        String naam = "testName";
        String plaats = "testPlaats";
        String wachtwoord = "123456";
        
        int expResult;
        int result;
        
        Balie instance;
        try
        {
            instance = new Balie(new Bank("testBank"));
            expResult = 8;
            result = instance.openRekening(naam, plaats, wachtwoord).length();
            assertEquals(expResult, result);
        } catch (RemoteException ex)
        {
            fail("RemoteException");
        }
        
        naam = "";
        plaats = "testPlaats";
        wachtwoord = "123456";
        
        String expResult2;
        String result2;
        
        try
        {
            instance = new Balie(new Bank("testBank"));
            expResult2 = null;
            result2 = instance.openRekening(naam, plaats, wachtwoord);
            assertEquals(expResult2, result2);
        } catch (RemoteException ex)
        {
            fail("RemoteException");
        }
        
        naam = "testNaam";
        plaats = "";
        wachtwoord = "123456";
        try
        {
            instance = new Balie(new Bank("testBank"));
            expResult2 = null;
            result2 = instance.openRekening(naam, plaats, wachtwoord);
            assertEquals(expResult2, result2);
        } catch (RemoteException ex)
        {
            fail("RemoteException");
        }   
        
        naam = "testNaam";
        plaats = "testPlaats";
        wachtwoord = "123";
        try
        {
            instance = new Balie(new Bank("testBank"));
            
            expResult2 = null;
            result2 = instance.openRekening(naam, plaats, wachtwoord);
            assertEquals(expResult2, result2);
        } catch (RemoteException ex)
        {
            fail("RemoteException");
        }
        
        
        naam = "testNaam";
        plaats = "testPlaats";
        wachtwoord = "123456789";
        try
        {
            instance = new Balie(new Bank("testBank"));
            expResult2 = null;
            result2 = instance.openRekening(naam, plaats, wachtwoord);
            assertEquals(expResult2, result2);
        } catch (RemoteException ex)
        {
            fail("RemoteException");
        }
    }

    /**
     * Test of logIn method, of class Balie.
     */
    @Test
    public void testLogIn()
    {
        System.out.println("logIn");
        
        String accountnaam = "";
        String wachtwoord = "";
        
        Balie instance = null;
        
        IBankiersessie expResult = null;
        IBankiersessie result;
        try
        {
            result = instance.logIn(accountnaam, wachtwoord);
            
            assertEquals(expResult, result);
        } catch (RemoteException ex)
        {
            fail("RemoteException");
        }
    }
    
}
