package control;

import entity.ChuyenTau;
import remote.ISeatUpdateListener;
import remote.IUpdateChuyenTauManagerListener;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IDAOChuyenTau extends Remote {

    void registerUpdateChuyenTauListener(String username, IUpdateChuyenTauManagerListener listener) throws RemoteException;

    //void unregisterUpdateChuyenTauListener(String username) throws RemoteException;

    ChuyenTau themChuyenTau(ChuyenTau chuyenTau) throws RemoteException;

    ArrayList<ChuyenTau> getDanhSachChuyenTau() throws RemoteException;

    ArrayList<ChuyenTau> getDanhSachChuyenTauTrongNgay() throws RemoteException;

    ArrayList<ChuyenTau> getDanhSachChuyenTauSapKhoiHanh() throws RemoteException;

    ArrayList<ChuyenTau> getDanhSachChuyenTauTheoNgaymaGaDimaGaDen(LocalDate ngayDi, int maGaDi, int maGaDen) throws RemoteException;

    ChuyenTau getChuyenTauTheoMa(String maChuyen) throws RemoteException;

    int getTongSoLuongChoCuaChuyen(String maChuyen) throws RemoteException;

    boolean capNhatChuyenTau(ChuyenTau chuyenTau) throws RemoteException;

    ChuyenTau getChuyenTauTheoMaTauMaGaDiMaGaDenNgayGioDiNgayGioDen(String maTau, int maGaDi, int maGaDen, LocalDateTime ngayGioDi, LocalDateTime ngayGioDen) throws RemoteException;
}
