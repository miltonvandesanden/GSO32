/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.bankieren;

import bank.internettoegang.Balie;
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
 * @author Martijn
 */
public class BankTest {
    
    private String name;
    private IBank instance;
    
    public BankTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        name = "Bank";
        instance = new Bank(name);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of openRekening method, of class Bank.
     */
    @Test
    public void testOpenRekening() {
        System.out.println("openRekening");
        // Initialiseer data
        String name = "Martijn Vriens";
        String city = "Tilburg";
        
        // Run testen
        assertEquals(instance.openRekening("",city), -1);
        assertEquals(instance.openRekening(name,""), -1);
        assertNotEquals(instance.openRekening(name,city), -1);
    }

    /**
     * Test of getRekening method, of class Bank.
     */
    @Test
    public void testGetRekening() {
        System.out.println("getRekening");
        // Initialiseer data
        String name = "Martijn Vriens";
        String city = "Tilburg";
        int rekNr = instance.openRekening(name, city);
        IRekening rek = instance.getRekening(rekNr);
        
        // Run testen
        assertEquals(rek.getNr(), rekNr);
    }

    /**
     * Test of maakOver method, of class Bank.
     */
    @Test
    public void testMaakOver() throws Exception {
        System.out.println("maakOver");
        // Initialiseer data
        String name = "Martijn Vriens";
        String city = "Tilburg";       

        int reknr = instance.openRekening(name, city);
        int reknr_to = instance.openRekening("Milton", "Tilburg");
        Rekening rek = (Rekening) instance.getRekening(reknr);

        // Niet genoeg saldo
        assertFalse(instance.maakOver(reknr, reknr_to, new Money(10, Money.EURO)));
        
        // Zet geld op rekening
        rek.muteer(new Money(100, Money.EURO));
        
        // Naar jezelf overmaken
        try {
            instance.maakOver(reknr, reknr, new Money(10, Money.EURO));
            assertTrue(false);
        } catch (RuntimeException e) {
            assertTrue(true);
        }
        
        // Negatieve hoeveelheid
        try {
            instance.maakOver(reknr, reknr_to, new Money(-1, Money.EURO));
            assertFalse(false);
        } catch (RuntimeException e) {
            assertTrue(true);
        }
        
        // Niet bestaande to
        try {
            instance.maakOver(reknr, 0, new Money(10, Money.EURO));
            assertTrue(false);
        } catch (NumberDoesntExistException e) {
            assertTrue(true);
        }
        
        // Niet bestaande van
        try {
            instance.maakOver(0, reknr_to, new Money(10, Money.EURO));
            assertTrue(false);
        } catch (NumberDoesntExistException e) {
            assertTrue(true);
        }
        
        assertTrue(instance.maakOver(reknr, reknr_to, new Money(10, Money.EURO)));
    }

    /**
     * Test of getName method, of class Bank.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        
        // Run testen
        assertEquals(instance.getName(), name);
    }
    
}
