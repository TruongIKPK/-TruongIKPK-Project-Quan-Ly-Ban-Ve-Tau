package control;

import connectDB.ConnectDB;
import entity.ChuyenTau;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @Dự án: tau-viet-express
 * @Class: ChuyenTau
 * @Tạo vào ngày: 10/15/2024
 * @Tác giả: Huy
 */

public class DAOChuyenTau {
    /**
     * Thêm một chuyến tàu vào cơ sở dữ liệu.
     * @param chuyenTau Đối tượng chuyến tàu cần thêm.
     * @return Đối tượng chuyến tàu sau khi thêm.
     * @throws SQLException Nếu xảy ra lỗi khi thực hiện truy vấn.
     */
    public static ChuyenTau themChuyenTau(ChuyenTau chuyenTau) throws SQLException {
        String sql = "INSERT INTO ChuyenTau(maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES(?, ?, ?, ?, ?)";
        try (
                PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, chuyenTau.getTau().getMaTau());
            stmt.setInt(2, chuyenTau.getGaDi().getMaGa());
            stmt.setInt(3, chuyenTau.getGaDen().getMaGa());
            stmt.setTimestamp(4, Timestamp.valueOf(chuyenTau.getNgayGioDi()));
            stmt.setTimestamp(5, Timestamp.valueOf(chuyenTau.getNgayGioDen()));

            // Thực thi câu lệnh SQL
            stmt.executeUpdate();

            // SELECT Lại chuyến tàu vừa thêm
            String sql2 = "SELECT * FROM ChuyenTau WHERE maTau = ? AND maGaDi = ? AND maGaDen = ? AND ngayGioKhoiHanh = ? AND ngayGioDen = ?";
            try (PreparedStatement stmt2 = ConnectDB.getConnection().prepareStatement(sql2)) {
                stmt2.setString(1, chuyenTau.getTau().getMaTau());
                stmt2.setInt(2, chuyenTau.getGaDi().getMaGa());
                stmt2.setInt(3, chuyenTau.getGaDen().getMaGa());
                stmt2.setTimestamp(4, Timestamp.valueOf(chuyenTau.getNgayGioDi()));
                stmt2.setTimestamp(5, Timestamp.valueOf(chuyenTau.getNgayGioDen()));
                ResultSet rs = stmt2.executeQuery();
                if (rs.next()) {
                    String maChuyen = rs.getString("maChuyen");
                    String maTau = rs.getString("maTau");
                    int maGaDi = rs.getInt("maGaDi");
                    int maGaDen = rs.getInt("maGaDen");
                    String macTau = rs.getString("macTau");
                    LocalDateTime ngayGioDi = rs.getTimestamp("ngayGioKhoiHanh").toLocalDateTime();
                    LocalDateTime ngayGioDen = rs.getTimestamp("ngayGioDen").toLocalDateTime();
                    String trangThai = rs.getString("trangThai");
                    return new ChuyenTau(maChuyen, macTau, DAOTau.getTauTheoMa(maTau), DAOGa.getGaTheoMaGa(maGaDi), DAOGa.getGaTheoMaGa(maGaDen), ngayGioDi, ngayGioDen, trangThai);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        return null;
    }

    /**
     * Xóa một chuyến tàu khỏi cơ sở dữ liệu.
     * @return true nếu xóa thành công, ngược lại false.
     */
    public static ArrayList<ChuyenTau> getDanhSachChuyenTau() {
        ArrayList<ChuyenTau> dsChuyenTau = new ArrayList<>();
        try {
            Statement statement = ConnectDB.getConnection().createStatement();
            String sql = "SELECT * FROM ChuyenTau";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String maChuyen = rs.getString("maChuyen");
                String maTau = rs.getString("maTau");
                int maGaDi = rs.getInt("maGaDi");
                int maGaDen = rs.getInt("maGaDen");
                String macTau = rs.getString("macTau");
                LocalDateTime ngayGioDi = rs.getTimestamp("ngayGioKhoiHanh").toLocalDateTime();
                LocalDateTime ngayGioDen = rs.getTimestamp("ngayGioDen").toLocalDateTime();
                String trangThai = rs.getString("trangThai");
                ChuyenTau chuyenTau = new ChuyenTau(maChuyen, macTau, DAOTau.getTauTheoMa(maTau), DAOGa.getGaTheoMaGa(maGaDi), DAOGa.getGaTheoMaGa(maGaDen), ngayGioDi, ngayGioDen, trangThai);
                dsChuyenTau.add(chuyenTau);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsChuyenTau;
    }

    /**
     * Lấy danh sách chuyến tàu trong ngày hiện tại theo thứ tự ngày tăng dần.
     * @return Danh sách chuyến tàu.
     */
    public static ArrayList<ChuyenTau> getDanhSachChuyenTauTrongNgay() {
        String sql = "SELECT * FROM ChuyenTau WHERE CONVERT(DATE, ngayGioKhoiHanh) = CONVERT(DATE, GETDATE()) ORDER BY ngayGioKhoiHanh ASC";
        ArrayList<ChuyenTau> dsChuyenTau = new ArrayList<>();
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maChuyen = rs.getString("maChuyen");
                String maTau = rs.getString("maTau");
                int maGaDi = rs.getInt("maGaDi");
                int maGaDen = rs.getInt("maGaDen");
                String macTau = rs.getString("macTau");
                LocalDateTime ngayGioDi = rs.getTimestamp("ngayGioKhoiHanh").toLocalDateTime();
                LocalDateTime ngayGioDen = rs.getTimestamp("ngayGioDen").toLocalDateTime();
                String trangThai = rs.getString("trangThai");
                ChuyenTau chuyenTau = new ChuyenTau(maChuyen, macTau, DAOTau.getTauTheoMa(maTau), DAOGa.getGaTheoMaGa(maGaDi), DAOGa.getGaTheoMaGa(maGaDen), ngayGioDi, ngayGioDen, trangThai);
                dsChuyenTau.add(chuyenTau);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsChuyenTau;
    }

    /**
     * Lấy danh sách 10 chuyến tàu sắp khởi hành.
     * @return Danh sách chuyến tàu.
     */
    public static ArrayList<ChuyenTau> getDanhSachChuyenTauSapKhoiHanh() {
        String sql = "SELECT TOP 10 * FROM ChuyenTau WHERE ngayGioKhoiHanh > GETDATE() ORDER BY ngayGioKhoiHanh ASC";
        ArrayList<ChuyenTau> dsChuyenTau = new ArrayList<>();
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maChuyen = rs.getString("maChuyen");
                String maTau = rs.getString("maTau");
                int maGaDi = rs.getInt("maGaDi");
                int maGaDen = rs.getInt("maGaDen");
                String macTau = rs.getString("macTau");
                LocalDateTime ngayGioDi = rs.getTimestamp("ngayGioKhoiHanh").toLocalDateTime();
                LocalDateTime ngayGioDen = rs.getTimestamp("ngayGioDen").toLocalDateTime();
                String trangThai = rs.getString("trangThai");
                ChuyenTau chuyenTau = new ChuyenTau(maChuyen, macTau, DAOTau.getTauTheoMa(maTau), DAOGa.getGaTheoMaGa(maGaDi), DAOGa.getGaTheoMaGa(maGaDen), ngayGioDi, ngayGioDen, trangThai);
                dsChuyenTau.add(chuyenTau);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsChuyenTau;
    }

    /**
     * Lấy danh sách chuyến tàu theo ngày, mã ga đi và mã ga đến.
     * @param ngayDi Ngày cần tìm.
     * @param maGaDi Mã ga đi.
     * @param maGaDen Mã ga đến.
     * @return Danh sách chuyến tàu.
     */
    public static ArrayList<ChuyenTau> getDanhSachChuyenTauTheoNgaymaGaDimaGaDen(LocalDate ngayDi, int maGaDi, int maGaDen) {
        System.out.println("getDanhSachChuyenTauTheoNgaymaGaDimaGaDen");
        String sql = "SELECT * FROM ChuyenTau WHERE CONVERT(DATE, ngayGioKhoiHanh) = ? AND maGaDi = ? AND maGaDen = ?";
        ArrayList<ChuyenTau> dsChuyenTau = new ArrayList<>();
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(ngayDi));
            stmt.setInt(2, maGaDi);
            stmt.setInt(3, maGaDen);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maChuyen = rs.getString("maChuyen");
                String maTau = rs.getString("maTau");
                String macTau = rs.getString("macTau");
                LocalDateTime ngayGioDi = rs.getTimestamp("ngayGioKhoiHanh").toLocalDateTime();
                LocalDateTime ngayGioDen = rs.getTimestamp("ngayGioDen").toLocalDateTime();
                String trangThai = rs.getString("trangThai");
                ChuyenTau chuyenTau = new ChuyenTau(maChuyen, macTau, DAOTau.getTauTheoMa(maTau), DAOGa.getGaTheoMaGa(maGaDi), DAOGa.getGaTheoMaGa(maGaDen), ngayGioDi, ngayGioDen, trangThai);
                dsChuyenTau.add(chuyenTau);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsChuyenTau;
    }

    /**
     * Lấy chuyến tàu theo mã chuyến.
     * @param maChuyen Mã chuyến cần tìm.
     * @return Chuyến tàu tìm được.
     */
    public static ChuyenTau getChuyenTauTheoMa(String maChuyen) {
        String sql = "SELECT * FROM ChuyenTau WHERE maChuyen = ?";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, maChuyen);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String maTau = rs.getString("maTau");
                int maGaDi = rs.getInt("maGaDi");
                int maGaDen = rs.getInt("maGaDen");
                String macTau = rs.getString("macTau");
                LocalDateTime ngayGioDi = rs.getTimestamp("ngayGioKhoiHanh").toLocalDateTime();
                LocalDateTime ngayGioDen = rs.getTimestamp("ngayGioDen").toLocalDateTime();
                String trangThai = rs.getString("trangThai");

                return new ChuyenTau(maChuyen, macTau, DAOTau.getTauTheoMa(maTau), DAOGa.getGaTheoMaGa(maGaDi), DAOGa.getGaTheoMaGa(maGaDen), ngayGioDi, ngayGioDen, trangThai);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Lấy số lượng chỗ của chuyến
     * @param maChuyen Mã chuyến cần tìm
     * @return Số lượng chỗ
     */
    public static int getTongSoLuongChoCuaChuyen(String maChuyen) {
        String sql = "SELECT CT.maChuyen, COUNT(*) AS tongSoCho\n" +
                "FROM ChuyenTau AS CT \n" +
                "    INNER JOIN Tau AS T ON CT.maTau = T.maTau\n" +
                "    INNER JOIN Toa AS TOA ON T.maTau = TOA.maTau\n" +
                "    INNER JOIN ChoNgoi AS CN ON TOA.maToa = CN.maToa\n" +
                "WHERE CT.maChuyen = ?\n" +
                "GROUP BY CT.maChuyen\n";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, maChuyen);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("tongSoCho");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Cập nhật chuyến tàu.
     * @param chuyenTau Chuyến tàu cần cập nhật.
     * @return true nếu cập nhật thành công, ngược lại false.
     */
    public static boolean capNhatChuyenTau(ChuyenTau chuyenTau) {
        String sql = "UPDATE ChuyenTau SET maTau = ?, maGaDi = ?, maGaDen = ?, ngayGioKhoiHanh = ?, ngayGioDen = ?, trangThai = ? WHERE maChuyen = ?";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, chuyenTau.getTau().getMaTau());
            stmt.setInt(2, chuyenTau.getGaDi().getMaGa());
            stmt.setInt(3, chuyenTau.getGaDen().getMaGa());
            stmt.setTimestamp(4, Timestamp.valueOf(chuyenTau.getNgayGioDi()));
            stmt.setTimestamp(5, Timestamp.valueOf(chuyenTau.getNgayGioDen()));
            stmt.setString(6, chuyenTau.getTrangThai());
            stmt.setString(7, chuyenTau.getMaChuyen());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Lấy chuyến tàu theo mã tàu, mã ga đi, mã ga đến, ngày giờ đi và ngày giờ đến.
     * @param maTau Mã tàu
     * @param maGaDi Mã ga đi
     * @param maGaDen Mã ga đến
     * @param ngayGioDi Ngày giờ đi
     * @param ngayGioDen Ngày giờ đến
     * @return Chuyến tàu tìm được.
     */
    public static ChuyenTau getChuyenTauTheoMaTauMaGaDiMaGaDenNgayGioDiNgayGioDen(String maTau, int maGaDi, int maGaDen, LocalDateTime ngayGioDi, LocalDateTime ngayGioDen) {
        String sql = "SELECT * FROM ChuyenTau WHERE maTau = ? AND maGaDi = ? AND maGaDen = ? AND ngayGioKhoiHanh = ? AND ngayGioDen = ?";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, maTau);
            stmt.setInt(2, maGaDi);
            stmt.setInt(3, maGaDen);
            stmt.setTimestamp(4, Timestamp.valueOf(ngayGioDi));
            stmt.setTimestamp(5, Timestamp.valueOf(ngayGioDen));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String maChuyen = rs.getString("maChuyen");
                String macTau = rs.getString("macTau");
                String trangThai = rs.getString("trangThai");
                return new ChuyenTau(maChuyen, macTau, DAOTau.getTauTheoMa(maTau), DAOGa.getGaTheoMaGa(maGaDi), DAOGa.getGaTheoMaGa(maGaDen), ngayGioDi, ngayGioDen, trangThai);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
