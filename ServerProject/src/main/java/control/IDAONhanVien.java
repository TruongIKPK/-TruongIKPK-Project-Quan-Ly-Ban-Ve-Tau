package control;

import entity.NhanVien;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IDAONhanVien extends Remote {
    boolean themNhanVien(NhanVien nv) throws RemoteException;

    //get NV
    NhanVien getNhanVien(String maNV) throws RemoteException;

    //ds NV
    ArrayList<NhanVien> layDanhSachNhanVien() throws RemoteException;

    //get NV theo ten
    ArrayList<NhanVien> getNhanVienTheoTen(String tenNV) throws RemoteException;

    //get NV theo sdt
    NhanVien getNhanVienTheoSDT(String sdt) throws RemoteException;

    //get NV theo CCCD
    NhanVien getNhanVienTheoCCCD(String CCCD) throws RemoteException;

    //get NV theo chuc vu
    NhanVien getNhanVienTheoChucVu(String maChucVu) throws RemoteException;

    //get NV theo trang thai
    ArrayList<NhanVien> getNhanVienTheoTrangThai(String trangThai) throws RemoteException;

    //sua NV
    NhanVien suaNhanVien(NhanVien nv) throws RemoteException;

    void updateTaiKhoanStatus(NhanVien nhanVien) throws RemoteException;

    String getDuongDanAnh(String maNV) throws RemoteException;
}
