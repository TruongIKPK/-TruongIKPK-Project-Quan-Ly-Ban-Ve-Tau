package control;

import entity.LoaiVe;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IDAOLoaiVe extends Remote {
    boolean themLoaiVe(LoaiVe lv) throws RemoteException;

    boolean xoaLoaiVe(String maLV) throws RemoteException;

    LoaiVe suaLoaiVe(LoaiVe lv) throws RemoteException;

    ArrayList<LoaiVe> layDSLoaiVe() throws RemoteException;

    LoaiVe layLoaiVeTheoMa(String maLV) throws RemoteException;
}
