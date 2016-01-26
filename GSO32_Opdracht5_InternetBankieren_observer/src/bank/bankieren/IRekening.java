package bank.bankieren;

import java.io.Serializable;
import java.rmi.RemoteException;

public interface IRekening extends Serializable {
  int getNr();
  Money getSaldo();
  IKlant getEigenaar();
  int getKredietLimietInCenten();
  void addListener(RemotePropertyListener listener, String property);
  void removeListener(RemotePropertyListener listener, String property);
}

