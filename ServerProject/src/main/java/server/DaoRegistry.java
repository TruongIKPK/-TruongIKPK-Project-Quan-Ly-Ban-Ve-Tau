package server;

import control.impl.DAOThongKe;
import control.impl.*;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class DaoRegistry {
    public static Map<String, Object> getDaoMap() throws RemoteException {
        Map<String, Object> daoMap = new HashMap<>();

        daoMap.put("caLamDao", new DAOCaLam());
        daoMap.put("choNgoiDao", new DAOChoNgoi());
        daoMap.put("chucVuDao", new DAOChucVu());
        daoMap.put("chuyenTauDao", new DAOChuyenTau());
        daoMap.put("gaDao", new DAOGa());
        daoMap.put("hoaDonDao", new DAOHoaDon());
        daoMap.put("khachHangDao", new DAOKhachHang());
        daoMap.put("khuyenMaiDao", new DAOKhuyenMai());
        daoMap.put("loaiChoDao", new DAOLoaiCho());
        daoMap.put("loaiToaDao", new DAOLoaiToa());
        daoMap.put("loaiVeDao", new DAOLoaiVe());
        daoMap.put("nhanVienDao", new DAONhanVien());
        daoMap.put("taiKhoanDao", new DAOTaiKhoan());
        daoMap.put("tauDao", new DAOTau());
        daoMap.put("toaDao", new DAOToa());
        daoMap.put("veDao", new DAOVe());
        daoMap.put("authService", new AuthService());
        daoMap.put("thongKeDao", new DAOThongKe());
        return daoMap;
    }
}
