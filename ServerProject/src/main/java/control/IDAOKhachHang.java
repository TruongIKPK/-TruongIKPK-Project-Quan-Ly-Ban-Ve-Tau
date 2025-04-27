package control;

import entity.KhachHang;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IDAOKhachHang extends Remote {
    boolean themKhachHang(KhachHang kh)throws RemoteException;

    KhachHang suaKhachHang(KhachHang kh)throws RemoteException;

    KhachHang timKhachHang(String CCCD, String sdt)throws RemoteException;

    ArrayList<KhachHang> layDanhSachKhachHang()throws RemoteException;

    KhachHang layKhachHangTheoCCCD(String CCCD)throws RemoteException;

    KhachHang layKhachHangTheoSdt(String sdt)throws RemoteException;

    KhachHang layKhachHangTheoMa(String maKH)throws RemoteException;

    KhachHang timKhachHangTheoEmail(String email)throws RemoteException;

    ArrayList<KhachHang> layKhachHangTheoDoiTuong(String doiTuong)throws RemoteException;
}
