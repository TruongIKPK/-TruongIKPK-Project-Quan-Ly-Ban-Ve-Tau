package control;

import entity.ChoNgoi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IDAOChoNgoi extends Remote {
    ArrayList<ChoNgoi> getDanhSachChoNgoi() throws RemoteException;

    ArrayList<ChoNgoi> getDSChoNgoiTheoToa(String maToa) throws RemoteException;

    ArrayList<ChoNgoi> getDSChoNgoiTheoToaTrangThai(String maToa, String trangThai) throws RemoteException;

    ChoNgoi getChoNgoiTheoMa(String maCho) throws RemoteException;
}
