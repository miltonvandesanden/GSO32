/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.internettoegang;

import bank.bankieren.Bank;
import bank.bankieren.IRekening;
import bank.bankieren.Money;
import bank.bankieren.Rekening;
import fontys.util.InvalidSessionException;
import fontys.util.NumberDoesntExistException;
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
public class BankiersessieTest
{
    
    public BankiersessieTest()
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
     * Test of isGeldig method, of class Bankiersessie.
     */
    @Test
    public void testIsGeldig() throws RemoteException, InterruptedException
    {
        System.out.println("isGeldig");
        
        Bank bank = new Bank("testBank");
        int reknr = bank.openRekening("testNaam", "testCity");
        
        Bankiersessie instance = new Bankiersessie(reknr, bank);
        boolean result = instance.isGeldig();
        assertTrue(result);
        
        instance = new Bankiersessie(reknr, bank);
        Thread.sleep(600000);
        result = instance.isGeldig();
        assertFalse(result);
    }

    /**
     * Test of maakOver method, of class Bankiersessie.
     */
    @Test
    public void testMaakOver() throws Exception
    {
        System.out.println("maakOver");
        
        Bank bank = new Bank("testBank");
        bank.openRekening("naam1", "testStad");
        bank.openRekening("naam2", "testStad2");
        
        int bestemming = 100000001;
        Money bedrag = new Money(2, "EURO");
        Bankiersessie instance = new Bankiersessie(100000000, bank);
        boolean result = instance.maakOver(bestemming, bedrag);
        assertFalse(result);
        
        ((Rekening) bank.getRekening(100000000)).muteer(new Money(10, "EURO"));
        
        result = instance.maakOver(bestemming, bedrag);
        assertTrue(result);
    }
    
    @Test(expected = NumberDoesntExistException.class)
    public void testMaakOverNumberDoesntExist() throws Exception
    {
        System.out.println("maakOver");
        
        Bank bank = new Bank("testBank");
        bank.openRekening("naam1", "testStad");
        ((Rekening)bank.getRekening(100000000)).muteer(new Money(5, "EURO"));
        
        int bestemming = 100000001;
        Money bedrag = new Money(2, "EURO");
        Bankiersessie instance = new Bankiersessie(100000000, bank);
        instance.maakOver(bestemming, bedrag);
    }
    
    @Test(expected = InvalidSessionException.class)
    public void testMaakOverInvalidSession() throws Exception
    {
        System.out.println("maakOver");
        
        Bank bank = new Bank("testBank");
        bank.openRekening("naam1", "testStad");
        bank.openRekening("naam2", "testStad2");
        ((Rekening)bank.getRekening(100000000)).muteer(new Money(5, "EURO"));
        
        Thread.sleep(600010);
        
        int bestemming = 100000001;
        Money bedrag = new Money(2, "EURO");
        Bankiersessie instance = new Bankiersessie(100000000, bank);
        instance.maakOver(bestemming, bedrag);
    }


    /**
     * Test of getRekening method, of class Bankiersessie.
     */
    @Test
    public void testGetRekening() throws Exception
    {
        System.out.println("getRekening");
        
        Bank bank = new Bank("testBank");
        bank.openRekening("testNaam", "testStad");
        
        Bankiersessie instance = new Bankiersessie(100000000, bank);
        IRekening result = instance.getRekening();
        assertNotNull(result);
    }
    
        @Test(expected = InvalidSessionException.class)
    public void testGetRekeningInvalidSession() throws Exception
    {
        System.out.println("getRekening");
        
        Bank bank = new Bank("testBank");
        bank.openRekening("testNaam", "testStad");
        
        Bankiersessie instance = new Bankiersessie(100000000, bank);
        
        Thread.sleep(600010);
        
        IRekening result = instance.getRekening();
    }


    /**
     * Test of logUit method, of class Bankiersessie.
     */
    @Test
    public void testLogUit() throws Exception
    {
        System.out.println("logUit");
        Bankiersessie instance = null;
        instance.logUit();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
