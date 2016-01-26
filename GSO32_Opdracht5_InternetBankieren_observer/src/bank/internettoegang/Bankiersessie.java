package bank.internettoegang;

import bank.bankieren.BasicPublisher;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import bank.bankieren.IBank;
import bank.bankieren.IRekening;
import bank.bankieren.Money;
import bank.bankieren.RemotePropertyListener;
import bank.bankieren.RemotePublisher;

import fontys.util.InvalidSessionException;
import fontys.util.NumberDoesntExistException;
import java.beans.PropertyChangeEvent;

public class Bankiersessie extends UnicastRemoteObject implements IBankiersessie, RemotePropertyListener, RemotePublisher {

	private static final long serialVersionUID = 1L;
	private long laatsteAanroep;
	private int reknr;
	private IBank bank;
        private String saldo;
        private String[] saldoArray;
        private BasicPublisher basicPublisher;

	public Bankiersessie(int reknr, IBank bank) throws RemoteException {
		laatsteAanroep = System.currentTimeMillis();
		this.reknr = reknr;
		this.bank = bank;
                saldoArray = new String[1];
                saldoArray[0] = saldo.toString();
                
                bank.getRekening(reknr).addListener(this, saldo);
                basicPublisher = new BasicPublisher(saldoArray);
	}

        @Override
	public boolean isGeldig() {
		return System.currentTimeMillis() - laatsteAanroep < GELDIGHEIDSDUUR;
	}

	@Override
	public boolean maakOver(int bestemming, Money bedrag)
			throws NumberDoesntExistException, InvalidSessionException,
			RemoteException {
		
		updateLaatsteAanroep();
		
		if (reknr == bestemming)
			throw new RuntimeException(
					"source and destination must be different");
		if (!bedrag.isPositive())
			throw new RuntimeException("amount must be positive");
		
		return bank.maakOver(reknr, bestemming, bedrag);
	}

	private void updateLaatsteAanroep() throws InvalidSessionException {
		if (!isGeldig()) {
			throw new InvalidSessionException("session has been expired");
		}
		
		laatsteAanroep = System.currentTimeMillis();
	}

	@Override
	public IRekening getRekening() throws InvalidSessionException,
			RemoteException {

		updateLaatsteAanroep();
		
		return bank.getRekening(reknr);
	}

	@Override
	public void logUit() throws RemoteException
        {
            bank.getRekening(reknr).removeListener(this, saldo);
            UnicastRemoteObject.unexportObject(this, true);
	}

    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException
    {
        saldo = (String) evt.getNewValue();
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