/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shared;

/**
 *
 * @author martijn
 */
public interface IAccessControl
{
    /**
     * Login see if this sessionID is still logged in
     * @param sessionID The ID you have on your client right now
     * @return null if not logged in anymore, or the sessionID you are logged in with
     */
    public int login(int sessionID);
    
    /**
     * Login with username and password in the system
     * @param userName From the account you want to login on
     * @param password Which belongs to the username
     * @return null if no account found with this username with password or the created session's sessionID if logged in
     */
    public int login(String userName, String password);
    
    /**
     * Send an amount of money from IBAN to another IBAN
     * @param sessionID The ID you are logged in on your client
     * @param vanIBAN Your IBAN you want to send the money from
     * @param naarIBAN The IBAN you want to send money to
     * @param amount The amount of money you want to send
     * @return errorcode as int | where 0 is no success
     */
    public int maakOver(int sessionID, String vanIBAN, String naarIBAN, double amount);

    /**
     * Disable the current session with the ID
     * @param sessionID The sessionID you want to disable
     * @return errorcode as int | where 0 is no success
     */
    public int logout(int sessionID);
}
