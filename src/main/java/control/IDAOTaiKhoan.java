package control;

import entity.TaiKhoan;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IDAOTaiKhoan extends Remote {
    TaiKhoan login(String maTK, String matKhau) throws RemoteException;

    //chuyen trang thai tai khoan
    boolean chuyenTrangThaiTaiKhoan(String maTK, String trangThai) throws RemoteException;

    //get ds tai khoan
    ArrayList<TaiKhoan> getDSTaiKhoan() throws RemoteException;

    //get tai khoan theo maTK
    TaiKhoan getTaiKhoanTheoMa(String maTK) throws RemoteException;

    //sua tai khoan
    TaiKhoan suaTaiKhoan(TaiKhoan tk) throws RemoteException;
}
