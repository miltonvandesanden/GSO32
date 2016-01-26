package bank.bankieren;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author milton
 */
public class Rekening extends UnicastRemoteObject implements IRekeningTbvBank, RemotePublisher {

    private static final long serialVersionUID = 7221569686169173632L;
    private static final int KREDIETLIMIET = -10000;
    private int nr;
    private IKlant eigenaar;
    private Money saldo;
    private BasicPublisher basicPublisher;

    /**
     * creatie van een bankrekening met saldo van 0.0<br>
     * de constructor heeft package-access omdat de PersistentAccount-objecten door een
     * het PersistentBank-object worden beheerd
     * @see banking.persistence.PersistentBank
     * @param number het bankrekeningnummer
     * @param klant de eigenaar van deze rekening
     * @param currency de munteenheid waarin het saldo is uitgedrukt
     */
    Rekening(int number, IKlant klant, String currency) throws RemoteException {
        this(number, klant, new Money(0, currency));
    }

    /**
     * creatie van een bankrekening met saldo saldo<br>
     * de constructor heeft package-access omdat de PersistentAccount-objecten door een
     * het PersistentBank-object worden beheerd
     * @see banking.persistence.PersistentBank
     * @param number het bankrekeningnummer
     * @param name de naam van de eigenaar
     * @param city de woonplaats van de eigenaar
     * @param currency de munteenheid waarin het saldo is uitgedrukt
     */
    Rekening(int number, IKlant klant, Money saldo) throws RemoteException {
        this.nr = number;
        this.eigenaar = klant;
        this.saldo = saldo;
        String[] saldoArray = new String[1];
        saldoArray[0] = saldo.toString();
        basicPublisher = new BasicPublisher(saldoArray);
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        return nr == ((IRekening) obj).getNr();
    }

    @Override
    public int getNr() {
        return nr;
    }

    @Override
    public String toString() {
        return nr + ": " + eigenaar.toString();
    }

    boolean isTransferPossible(Money bedrag) {
        return (bedrag.getCents() + saldo.getCents() >= KREDIETLIMIET);
    }

    @Override
    public IKlant getEigenaar() {
        return eigenaar;
    }

    @Override
    public Money getSaldo() {
        return saldo;
    }

    @Override
    public boolean muteer(Money bedrag) {
        if (bedrag.getCents() == 0) {
            throw new RuntimeException(" bedrag = 0 bij aanroep 'muteer'");
        }

        if (isTransferPossible(bedrag)) {
            saldo = Money.sum(saldo, bedrag);
            return true;
        }
        return false;
    }

    @Override
    public int getKredietLimietInCenten() {
        return KREDIETLIMIET;
    }

    @Override
    public void addListener(RemotePropertyListener listener, String property)
    {
        basicPublisher.addListener(listener, property);
    }

    @Override
    public void removeListener(RemotePropertyListener listener, String property)
    {
        basicPublisher.removeListener(listener, property);
    }
}
