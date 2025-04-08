package control;

import entity.KhuyenMai;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface IDAOKhuyenMai extends Remote {
    boolean themKhuyenMai(KhuyenMai km) throws RemoteException;

    ArrayList<String> getDSDoiTuongKhuyenMai() throws RemoteException;

    KhuyenMai suaKhuyenMai(KhuyenMai km) throws RemoteException;

    boolean xoaKhuyenMai(String maKM) throws RemoteException;

    ArrayList<KhuyenMai> getDSKhuyenMai() throws RemoteException;

    ArrayList<KhuyenMai> getKhuyenMaiTheoMa(String maKM) throws RemoteException;

    ArrayList<KhuyenMai> getDSMaKhuyenMaiTheoMaLike(String maKM) throws RemoteException;

    ArrayList<KhuyenMai> getKhuyenMaiTheoNgay(LocalDate ngay) throws RemoteException;

    void capNhatTrangThaiDaGuiThongBao(String maKM) throws RemoteException;
}
