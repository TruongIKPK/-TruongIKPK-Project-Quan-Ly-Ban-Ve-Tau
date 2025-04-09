package control;

import entity.LoaiToa;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IDAOLoaiToa extends Remote {
    boolean themLoaiToa(LoaiToa loaiToa) throws RemoteException;
    LoaiToa suaLoaiToa(LoaiToa loaiToa) throws RemoteException;
    boolean xoaLoaiToa(String maLT) throws RemoteException;
    ArrayList<LoaiToa> getDSLoaiToa() throws RemoteException;
    LoaiToa getLoaiToaTheoMa(String maLT) throws RemoteException;
}
