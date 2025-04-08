package control;

import entity.ChuyenTau;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IDAOChuyenTau extends Remote {
    ChuyenTau themChuyenTau(ChuyenTau chuyenTau) throws RemoteException;

    ArrayList<ChuyenTau> getDanhSachChuyenTau() throws RemoteException;

    ArrayList<ChuyenTau> getDanhSachChuyenTauTrongNgay() throws RemoteException;

    ArrayList<ChuyenTau> getDanhSachChuyenTauSapKhoiHanh() throws RemoteException;

    ArrayList<ChuyenTau> getDanhSachChuyenTauTheoNgaymaGaDimaGaDen(LocalDate ngayDi, int maGaDi, int maGaDen) throws RemoteException;

    //    public static ChuyenTau getChuyenTauTheoMa(String maChuyen) {
//        String sql = "SELECT * FROM ChuyenTau WHERE maChuyen = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maChuyen);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                String maTau = rs.getString("maTau");
//                int maGaDi = rs.getInt("maGaDi");
//                int maGaDen = rs.getInt("maGaDen");
//                String macTau = rs.getString("macTau");
//                LocalDateTime ngayGioDi = rs.getTimestamp("ngayGioKhoiHanh").toLocalDateTime();
//                LocalDateTime ngayGioDen = rs.getTimestamp("ngayGioDen").toLocalDateTime();
//                String trangThai = rs.getString("trangThai");
//
//                return new ChuyenTau(maChuyen, macTau, DAOTau.getTauTheoMa(maTau), DAOGa.getGaTheoMaGa(maGaDi), DAOGa.getGaTheoMaGa(maGaDen), ngayGioDi, ngayGioDen, trangThai);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    ChuyenTau getChuyenTauTheoMa(String maChuyen) throws RemoteException;
//    public static int getTongSoLuongChoCuaChuyen(String maChuyen) {
//        String sql = "SELECT CT.maChuyen, COUNT(*) AS tongSoCho\n" +
//                "FROM ChuyenTau AS CT \n" +
//                "    INNER JOIN Tau AS T ON CT.maTau = T.maTau\n" +
//                "    INNER JOIN Toa AS TOA ON T.maTau = TOA.maTau\n" +
//                "    INNER JOIN ChoNgoi AS CN ON TOA.maToa = CN.maToa\n" +
//                "WHERE CT.maChuyen = ?\n" +
//                "GROUP BY CT.maChuyen\n";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maChuyen);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                return rs.getInt("tongSoCho");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }

    int getTongSoLuongChoCuaChuyen(String maChuyen) throws RemoteException;

    boolean capNhatChuyenTau(ChuyenTau chuyenTau) throws RemoteException;

    ChuyenTau getChuyenTauTheoMaTauMaGaDiMaGaDenNgayGioDiNgayGioDen(String maTau, int maGaDi, int maGaDen, LocalDateTime ngayGioDi, LocalDateTime ngayGioDen) throws RemoteException;
}
