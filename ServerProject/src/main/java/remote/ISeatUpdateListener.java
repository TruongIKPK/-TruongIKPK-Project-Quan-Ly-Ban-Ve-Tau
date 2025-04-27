package remote;

import entity.Ve;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISeatUpdateListener extends Remote {
    void onSeatStatusChanged(Ve ve) throws RemoteException;
}