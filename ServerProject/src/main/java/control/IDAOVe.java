package control;

import entity.Ve;
import remote.ISeatUpdateListener;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface IDAOVe extends Remote {
    void registerSeatUpdateListener(String username, ISeatUpdateListener listener) throws RemoteException;
    void unregisterSeatUpdateListener(String username) throws RemoteException;
    void updateSeatStatus(String seatId, String status) throws RemoteException;
    void unlockSeat(String trainId, String seatId, String userId) throws RemoteException;
    boolean lockSeatsForBooking(String trainId, List<String> seatIds, String userId) throws RemoteException;

    boolean themVeCoKhuyenMai(Ve ve) throws RemoteException;
    boolean themVeKhongKhuyenMai(Ve ve) throws RemoteException;
    boolean suaVe(Ve ve) throws RemoteException;
    ArrayList<Ve> layDSVeTheoMaHD(String maHD) throws RemoteException;
    ArrayList<Ve> layDSVeDaBanTheoMaHD(String maHD) throws RemoteException;
    ArrayList<Ve> layDSVeTheoMaChuyen(String maChuyen) throws RemoteException;
    ArrayList<Ve> layDSVeTheoMaKH(String maKH) throws RemoteException;
    Ve layVeTheoMa(String maVe) throws RemoteException;
    Ve layVeTheoMaChuyenVaMaKH(String maChuyen, String maKH) throws RemoteException;
    ArrayList<Ve> layDSVeTheoSDT(String sdt) throws RemoteException;
    ArrayList<Ve> layDSVeGanNhat() throws RemoteException;
    String layDoanhThuTheoNgay(Date ngay) throws RemoteException;
    String layDoanhThuTheoNgayHienTai() throws RemoteException;
    String layDoanhThuTuanHienTai() throws RemoteException;
    String layDoanhThuThangHienTai() throws RemoteException;
    String layDoanhThuQuyHienTai() throws RemoteException;
    String layDoanhThuNamHienTai() throws RemoteException;
    String layDoanhThuNgayHomQua() throws RemoteException;
    String layDoanhThuTuanVuaRoi() throws RemoteException;
    String layDoanhThuThangVuaRoi() throws RemoteException;
    String layDoanhThuQuyVuaRoi() throws RemoteException;
    String layDoanhThuNamVuaRoi() throws RemoteException;
    double getBestSellingHourOfDay() throws RemoteException;
    double getAverageBestSellingHourOfWeek() throws RemoteException;
    double getAverageBestSellingHourOfMonth() throws RemoteException;
    double getAverageBestSellingHourOfQuarter() throws RemoteException;
    double getAverageBestSellingHourOfYear() throws RemoteException;
    String getSlowestSalesTimeLastWeek() throws RemoteException;
    String getSlowestSalesTimeLastMonth() throws RemoteException;
    String getSlowestSalesTimeLastQuarter() throws RemoteException;
    String getSlowestSalesTimeLastYear() throws RemoteException;
    ArrayList<Ve> getDanhSachVeDaTra() throws RemoteException;
    ArrayList<Ve> layDSVeDaBanTheoMaChuyen(String maChuyen) throws RemoteException;
}
