package control;

import entity.LoaiCho;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IDAOLoaiCho extends Remote {
    boolean themLoaiCho(LoaiCho lc) throws RemoteException;

    ArrayList<LoaiCho> getDSLoaiCho() throws RemoteException;

    LoaiCho getLoaiChoByID(String maLC) throws RemoteException;

    LoaiCho suaLoaiCho(LoaiCho lc) throws RemoteException;

    boolean xoaLoaiCho(String maLC) throws RemoteException;
}
