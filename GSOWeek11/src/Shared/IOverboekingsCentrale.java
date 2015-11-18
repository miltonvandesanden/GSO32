/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shared;

/**
 *
 * @author milton
 */
public interface IOverboekingsCentrale
{

    /**
     * @param IBAN het IBAN van de rekening van de klant die het bedrag overmaakt.
     * @param tegenIBAN het IBAN van de rekening van de klant die het bedrag ontvangt
     * @param amount het geldbedrag dat overgemaakt wordt.
     * @return int, voltooingscode, 0 is succcesfull, 1 is error #1, 2 is another error #2, etc
     * substracts the amount from the rekening with IBAN en adds it to the rekening with tegenIBAN and returns int 0, unless the saldo of the rekening with IBAN is smaller than amount then it returns an error code.
     */
    public int maakOver(String IBAN, String tegenIBAN, double amount);
}
