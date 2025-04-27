package control;

import entity.ChucVu;
import jakarta.transaction.Transactional;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IDAOChucVu {
    // Lấy danh sách chức vụ
    ArrayList<ChucVu> getDanhSachChucVu() throws RemoteException;

    // Lấy chức vụ theo mã
    ChucVu getChucVuTheoMa(String maCV) throws RemoteException;

    // Thêm chức vụ
    @Transactional
    boolean themChucVu(ChucVu cv) throws RemoteException;

    // Sửa chức vụ, trả về đối tượng ChucVu đã cập nhật
    @Transactional
    ChucVu suaChucVu(ChucVu cv) throws RemoteException;
}
