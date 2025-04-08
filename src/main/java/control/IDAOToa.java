package control;

import entity.Toa;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IDAOToa extends Remote {
    boolean xoaToa(String maToa) throws RemoteException;

    Toa suaToa(Toa toa) throws RemoteException;

    ArrayList<Toa> getToaTheoTau(String maTau) throws RemoteException;

    ArrayList<Toa> getToaTheoLoaiToa(String maLoaiToa) throws RemoteException;

    Toa getToaTheoMa(String maToa) throws RemoteException;

    Toa taoToaTuResultSet(ResultSet rs) throws SQLException;
}
