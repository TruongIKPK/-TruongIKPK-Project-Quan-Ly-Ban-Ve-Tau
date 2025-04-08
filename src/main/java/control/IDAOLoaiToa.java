package control;

import entity.LoaiToa;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IDAOLoaiToa extends Remote {
    // Thêm loại toa
    boolean themLoaiToa(LoaiToa loaiToa) throws RemoteException;

    //    // Sửa loại toa
    //    public static LoaiToa suaLoaiToa(LoaiToa loaiToa) {
    //        String sql = "UPDATE LoaiToa SET tenLoai = ? WHERE maLoai = ?";
    //        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
    //            stmt.setString(1, loaiToa.getTenLT());
    //            stmt.setString(2, loaiToa.getMaLT());
    //            if (stmt.executeUpdate() > 0) {
    //                return loaiToa;
    //            }
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //        return null;
    //
    //    }
    // Sửa loại toa
    LoaiToa suaLoaiToa(LoaiToa loaiToa) throws RemoteException;

    //xoa loai toa
    boolean xoaLoaiToa(String maLT) throws RemoteException;

    //lay tat ca loaitoa
    ArrayList<LoaiToa> getDSLoaiToa() throws RemoteException;

    //    // Get loại toa theo mã
    //    public static LoaiToa getLoaiToaTheoMa(String maLT) {
    //        String sql = "SELECT * FROM LoaiToa WHERE maLoai = ?";
    //        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
    //            stmt.setString(1, maLT);
    //            ResultSet rs = stmt.executeQuery();
    //            if (rs.next()) {
    //                return new LoaiToa(maLT, rs.getString("tenLoai"));
    //            }
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //
    //        return null;
    //    }
    //}
    // Get loại toa theo mã
    LoaiToa getLoaiToaTheoMa(String maLT) throws RemoteException;
}
