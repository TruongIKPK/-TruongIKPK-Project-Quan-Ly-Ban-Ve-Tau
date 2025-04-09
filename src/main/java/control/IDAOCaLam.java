package control;

import entity.CaLam;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IDAOCaLam extends Remote {
    // lay danh sach ca lam
    ArrayList<CaLam> getDanhSachCaLam() throws RemoteException;

    // them ca lam
    boolean themCaLam(CaLam cl) throws RemoteException;

    // sua ca lam tra ve doi tuong ca lam
    CaLam suaCaLam(CaLam cl) throws RemoteException;

    // xoa ca lam
    boolean xoaCaLam(String maCL) throws RemoteException;

    // lay ca lam theo ma
    CaLam getCaLamTheoMa(String maCL) throws RemoteException;
}
