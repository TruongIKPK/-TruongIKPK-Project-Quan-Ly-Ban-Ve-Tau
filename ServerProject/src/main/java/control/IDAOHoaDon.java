package control;

import entity.HoaDon;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface IDAOHoaDon extends Remote {
    boolean themHoaDon(HoaDon hd) throws RemoteException;

    ArrayList<HoaDon> docHoaDonTheoNhanVien(String maNV)throws RemoteException;

    ArrayList<HoaDon> docHoaDonTheoKhachHang(String maKH)throws RemoteException;

    ArrayList<HoaDon> docHoaDonTheoNgay(LocalDate ngay)throws RemoteException;

    ArrayList<HoaDon> layDanhSachHoaDon()throws RemoteException;

    HoaDon getHoaDonCuoiCung()throws RemoteException;

    HoaDon getHoaDon(String maHD)throws RemoteException;
}
