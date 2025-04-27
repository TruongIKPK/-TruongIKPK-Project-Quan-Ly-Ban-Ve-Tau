package remote;

import entity.ChuyenTau;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IUpdateChuyenTauManagerListener extends Remote {
    void onUpdateChuyenTau(ChuyenTau chuyenTau) throws RemoteException;
}
