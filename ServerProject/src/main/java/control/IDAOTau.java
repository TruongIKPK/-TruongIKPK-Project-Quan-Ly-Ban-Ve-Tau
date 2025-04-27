package control;

import entity.Tau;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IDAOTau extends Remote {
    // Thêm tàu
    boolean  themTau(Tau tau) throws RemoteException;

    // Câp nhật trạng thái tàu
    boolean capNhatTrangThaiTau(String maTau, String trangThai) throws RemoteException;

    // Cập nhật tàu
    boolean capNhatTau(Tau tau)throws RemoteException;

    //Get danh sach tàu
    ArrayList<Tau> getDSTau()throws RemoteException;

    // Get tàu theo mã
    Tau getTauTheoMa(String maTau)throws RemoteException;
}
