package control;

import connectDB.ConnectDB;
import connectDB.connectDB_1;
import entity.ChuyenTau;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import service.ChuyenTauService;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Dự án: tau-viet-express
 * @Class: ChuyenTau
 * @Tạo vào ngày: 10/15/2024
 * @Tác giả: Huy
 */

public class DAOChuyenTau {
    /**
     * Thêm một chuyến tàu vào cơ sở dữ liệu.
     *
     * @param chuyenTau Đối tượng chuyến tàu cần thêm.
     * @return Đối tượng chuyến tàu sau khi thêm.
     * @throws SQLException Nếu xảy ra lỗi khi thực hiện truy vấn.
     */
//    public static ChuyenTau themChuyenTau(ChuyenTau chuyenTau) throws SQLException {
//        String sql = "INSERT INTO ChuyenTau(maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES(?, ?, ?, ?, ?)";
//        try (
//                PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, chuyenTau.getTau().getMaTau());
//            stmt.setInt(2, chuyenTau.getGaDi().getMaGa());
//            stmt.setInt(3, chuyenTau.getGaDen().getMaGa());
//            stmt.setTimestamp(4, Timestamp.valueOf(chuyenTau.getNgayGioDi()));
//            stmt.setTimestamp(5, Timestamp.valueOf(chuyenTau.getNgayGioDen()));
//
//            // Thực thi câu lệnh SQL
//            stmt.executeUpdate();
//
//            // SELECT Lại chuyến tàu vừa thêm
//            String sql2 = "SELECT * FROM ChuyenTau WHERE maTau = ? AND maGaDi = ? AND maGaDen = ? AND ngayGioKhoiHanh = ? AND ngayGioDen = ?";
//            try (PreparedStatement stmt2 = ConnectDB.getConnection().prepareStatement(sql2)) {
//                stmt2.setString(1, chuyenTau.getTau().getMaTau());
//                stmt2.setInt(2, chuyenTau.getGaDi().getMaGa());
//                stmt2.setInt(3, chuyenTau.getGaDen().getMaGa());
//                stmt2.setTimestamp(4, Timestamp.valueOf(chuyenTau.getNgayGioDi()));
//                stmt2.setTimestamp(5, Timestamp.valueOf(chuyenTau.getNgayGioDen()));
//                ResultSet rs = stmt2.executeQuery();
//                if (rs.next()) {
//                    String maChuyen = rs.getString("maChuyen");
//                    String maTau = rs.getString("maTau");
//                    int maGaDi = rs.getInt("maGaDi");
//                    int maGaDen = rs.getInt("maGaDen");
//                    String macTau = rs.getString("macTau");
//                    LocalDateTime ngayGioDi = rs.getTimestamp("ngayGioKhoiHanh").toLocalDateTime();
//                    LocalDateTime ngayGioDen = rs.getTimestamp("ngayGioDen").toLocalDateTime();
//                    String trangThai = rs.getString("trangThai");
//                    return new ChuyenTau(maChuyen, macTau, DAOTau.getTauTheoMa(maTau), DAOGa.getGaTheoMaGa(maGaDi), DAOGa.getGaTheoMaGa(maGaDen), ngayGioDi, ngayGioDen, trangThai);
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//
//        return null;
//    }

    private static EntityManager em = connectDB_1.getEntityManager();

    public static ChuyenTau themChuyenTau(ChuyenTau chuyenTau) {
//        EntityManager em = ConnectDB.getEntityManager(); // Giả sử bạn đã cấu hình EntityManager

        try {
            em.getTransaction().begin();

            // Thêm ChuyenTau vào database
//
            ChuyenTauService chuyenTauService = new ChuyenTauService(em);
            chuyenTauService.persistChuyenTau(chuyenTau);
            em.getTransaction().commit();

            // Truy vấn lại ChuyenTau vừa thêm
            TypedQuery<ChuyenTau> query = em.createQuery(
                    "SELECT ct FROM ChuyenTau ct WHERE ct.tau.maTau = :maTau AND ct.gaDi.maGa = :maGaDi AND ct.gaDen.maGa = :maGaDen AND ct.ngayGioDi = :ngayGioDi AND ct.ngayGioDen = :ngayGioDen",
                    ChuyenTau.class);
            query.setParameter("maTau", chuyenTau.getTau().getMaTau());
            query.setParameter("maGaDi", chuyenTau.getGaDi().getMaGa());
            query.setParameter("maGaDen", chuyenTau.getGaDen().getMaGa());
            query.setParameter("ngayGioDi", chuyenTau.getNgayGioDi());
            query.setParameter("ngayGioDen", chuyenTau.getNgayGioDen());

            return query.getSingleResult();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        }


    }
//    /**
//     * Xóa một chuyến tàu khỏi cơ sở dữ liệu.
//     * @return true nếu xóa thành công, ngược lại false.
//     */
//    public static ArrayList<ChuyenTau> getDanhSachChuyenTau() {
//        ArrayList<ChuyenTau> dsChuyenTau = new ArrayList<>();
//        try {
//            Statement statement = ConnectDB.getConnection().createStatement();
//            String sql = "SELECT * FROM ChuyenTau";
//            ResultSet rs = statement.executeQuery(sql);
//
//            while (rs.next()) {
//                String maChuyen = rs.getString("maChuyen");
//                String maTau = rs.getString("maTau");
//                int maGaDi = rs.getInt("maGaDi");
//                int maGaDen = rs.getInt("maGaDen");
//                String macTau = rs.getString("macTau");
//                LocalDateTime ngayGioDi = rs.getTimestamp("ngayGioKhoiHanh").toLocalDateTime();
//                LocalDateTime ngayGioDen = rs.getTimestamp("ngayGioDen").toLocalDateTime();
//                String trangThai = rs.getString("trangThai");
//                ChuyenTau chuyenTau = new ChuyenTau(maChuyen, macTau, DAOTau.getTauTheoMa(maTau), DAOGa.getGaTheoMaGa(maGaDi), DAOGa.getGaTheoMaGa(maGaDen), ngayGioDi, ngayGioDen, trangThai);
//                dsChuyenTau.add(chuyenTau);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return dsChuyenTau;
//    }

    /**
     * Lấy danh sách tất cả các chuyến tàu từ cơ sở dữ liệu.
     *
     * @return Danh sách các chuyến tàu.
     */
    public static ArrayList<ChuyenTau> getDanhSachChuyenTau() {
        ArrayList<ChuyenTau> dsChuyenTau = new ArrayList<>();
        try {
            TypedQuery<ChuyenTau> query = em.createQuery("SELECT ct FROM ChuyenTau ct", ChuyenTau.class);
            dsChuyenTau = new ArrayList<>(query.getResultList()); // Chuyển đổi kết quả sang ArrayList
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsChuyenTau;
    }


//    /**
//     * Lấy danh sách chuyến tàu trong ngày hiện tại theo thứ tự ngày tăng dần.
//     * @return Danh sách chuyến tàu.
//     */
//    public static ArrayList<ChuyenTau> getDanhSachChuyenTauTrongNgay() {
//        String sql = "SELECT * FROM ChuyenTau WHERE CONVERT(DATE, ngayGioKhoiHanh) = CONVERT(DATE, GETDATE()) ORDER BY ngayGioKhoiHanh ASC";
//        ArrayList<ChuyenTau> dsChuyenTau = new ArrayList<>();
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                String maChuyen = rs.getString("maChuyen");
//                String maTau = rs.getString("maTau");
//                int maGaDi = rs.getInt("maGaDi");
//                int maGaDen = rs.getInt("maGaDen");
//                String macTau = rs.getString("macTau");
//                LocalDateTime ngayGioDi = rs.getTimestamp("ngayGioKhoiHanh").toLocalDateTime();
//                LocalDateTime ngayGioDen = rs.getTimestamp("ngayGioDen").toLocalDateTime();
//                String trangThai = rs.getString("trangThai");
//                ChuyenTau chuyenTau = new ChuyenTau(maChuyen, macTau, DAOTau.getTauTheoMa(maTau), DAOGa.getGaTheoMaGa(maGaDi), DAOGa.getGaTheoMaGa(maGaDen), ngayGioDi, ngayGioDen, trangThai);
//                dsChuyenTau.add(chuyenTau);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return dsChuyenTau;
//    }
    /**
     * Lấy danh sách chuyến tàu trong ngày hiện tại theo thứ tự ngày tăng dần.
     *
     * @return Danh sách chuyến tàu.
     */
    public static ArrayList<ChuyenTau> getDanhSachChuyenTauTrongNgay() {
        ArrayList<ChuyenTau> dsChuyenTau = new ArrayList<>();
        try {
            TypedQuery<ChuyenTau> query = em.createQuery(
                    "SELECT ct FROM ChuyenTau ct WHERE FUNCTION('DATE', ct.ngayGioDi) = FUNCTION('DATE', CURRENT_DATE) ORDER BY ct.ngayGioDi ASC",
                    ChuyenTau.class);
            dsChuyenTau = new ArrayList<>(query.getResultList()); // Chuyển đổi kết quả sang ArrayList
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsChuyenTau;
    }

//    /**
//     * Lấy danh sách 10 chuyến tàu sắp khởi hành.
//     * @return Danh sách chuyến tàu.
//     */
//    public static ArrayList<ChuyenTau> getDanhSachChuyenTauSapKhoiHanh() {
//        String sql = "SELECT TOP 10 * FROM ChuyenTau WHERE ngayGioKhoiHanh > GETDATE() ORDER BY ngayGioKhoiHanh ASC";
//        ArrayList<ChuyenTau> dsChuyenTau = new ArrayList<>();
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                String maChuyen = rs.getString("maChuyen");
//                String maTau = rs.getString("maTau");
//                int maGaDi = rs.getInt("maGaDi");
//                int maGaDen = rs.getInt("maGaDen");
//                String macTau = rs.getString("macTau");
//                LocalDateTime ngayGioDi = rs.getTimestamp("ngayGioKhoiHanh").toLocalDateTime();
//                LocalDateTime ngayGioDen = rs.getTimestamp("ngayGioDen").toLocalDateTime();
//                String trangThai = rs.getString("trangThai");
//                ChuyenTau chuyenTau = new ChuyenTau(maChuyen, macTau, DAOTau.getTauTheoMa(maTau), DAOGa.getGaTheoMaGa(maGaDi), DAOGa.getGaTheoMaGa(maGaDen), ngayGioDi, ngayGioDen, trangThai);
//                dsChuyenTau.add(chuyenTau);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return dsChuyenTau;
//    }
    /**
     * Lấy danh sách 10 chuyến tàu sắp khởi hành.
     *
     * @return Danh sách chuyến tàu.
     */
    public static ArrayList<ChuyenTau> getDanhSachChuyenTauSapKhoiHanh() {
        ArrayList<ChuyenTau> dsChuyenTau = new ArrayList<>();
        try {
            TypedQuery<ChuyenTau> query = em.createQuery(
                    "SELECT ct FROM ChuyenTau ct WHERE ct.ngayGioDi > CURRENT_TIMESTAMP ORDER BY ct.ngayGioDi ASC",
                    ChuyenTau.class);
            query.setMaxResults(10); // Giới hạn kết quả trả về 10 chuyến tàu
            dsChuyenTau = new ArrayList<>(query.getResultList()); // Chuyển đổi kết quả sang ArrayList
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsChuyenTau;
    }


//    /**
//     * Lấy danh sách chuyến tàu theo ngày, ga đi và ga đến.
//     *
//     * @param ngayDi  Ngày khởi hành.
//     * @param maGaDi  Mã ga đi.
//     * @param maGaDen Mã ga đến.
//     * @return Danh sách chuyến tàu.
//     */
//    public static ArrayList<ChuyenTau> getDanhSachChuyenTauTheoNgaymaGaDimaGaDen(LocalDate ngayDi, int maGaDi, int maGaDen) {
//        System.out.println("getDanhSachChuyenTauTheoNgaymaGaDimaGaDen");
//        String sql = "SELECT * FROM ChuyenTau WHERE CONVERT(DATE, ngayGioKhoiHanh) = ? AND maGaDi = ? AND maGaDen = ?";
//        ArrayList<ChuyenTau> dsChuyenTau = new ArrayList<>();
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setDate(1, Date.valueOf(ngayDi));
//            stmt.setInt(2, maGaDi);
//            stmt.setInt(3, maGaDen);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                String maChuyen = rs.getString("maChuyen");
//                String maTau = rs.getString("maTau");
//                String macTau = rs.getString("macTau");
//                LocalDateTime ngayGioDi = rs.getTimestamp("ngayGioKhoiHanh").toLocalDateTime();
//                LocalDateTime ngayGioDen = rs.getTimestamp("ngayGioDen").toLocalDateTime();
//                String trangThai = rs.getString("trangThai");
//                ChuyenTau chuyenTau = new ChuyenTau(maChuyen, macTau, DAOTau.getTauTheoMa(maTau), DAOGa.getGaTheoMaGa(maGaDi), DAOGa.getGaTheoMaGa(maGaDen), ngayGioDi, ngayGioDen, trangThai);
//                dsChuyenTau.add(chuyenTau);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return dsChuyenTau;
//    }
    /**
     * Lấy danh sách chuyến tàu theo ngày, ga đi và ga đến.
     *
     * @param ngayDi  Ngày khởi hành.
     * @param maGaDi  Mã ga đi.
     * @param maGaDen Mã ga đến.
     * @return Danh sách chuyến tàu.
     */
    public static ArrayList<ChuyenTau> getDanhSachChuyenTauTheoNgaymaGaDimaGaDen(LocalDate ngayDi, int maGaDi, int maGaDen) {
        ArrayList<ChuyenTau> dsChuyenTau = new ArrayList<>();
        try {
            TypedQuery<ChuyenTau> query = em.createQuery(
                    "SELECT ct FROM ChuyenTau ct WHERE FUNCTION('DATE', ct.ngayGioDi) = :ngayDi AND ct.gaDi.maGa = :maGaDi AND ct.gaDen.maGa = :maGaDen",
                    ChuyenTau.class);
            query.setParameter("ngayDi", ngayDi);
            query.setParameter("maGaDi", maGaDi);
            query.setParameter("maGaDen", maGaDen);

            dsChuyenTau = new ArrayList<>(query.getResultList()); // Chuyển đổi kết quả sang ArrayList
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsChuyenTau;
    }

    /**
     * Lấy chuyến tàu theo mã chuyến.
     *
     * @param maChuyen Mã chuyến cần tìm.
     * @return Chuyến tàu tìm được.
     */
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
    public static ChuyenTau getChuyenTauTheoMa(String maChuyen) {
        ChuyenTau chuyenTau = null;
        try {
            TypedQuery<ChuyenTau> query = em.createQuery("SELECT ct FROM ChuyenTau ct WHERE ct.maChuyen = :maChuyen", ChuyenTau.class);
            query.setParameter("maChuyen", maChuyen);
            chuyenTau = query.getSingleResult();
        } catch (NoResultException e) {
            //không tìm thấy chuyến tàu
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chuyenTau;

    }
    /**
     * Lấy số lượng chỗ của chuyến
     * @param maChuyen Mã chuyến cần tìm
     * @return Số lượng chỗ
     */
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

        public static int getTongSoLuongChoCuaChuyen(String maChuyen) {
            String jpql = "SELECT COUNT(cn) " +
                    "FROM ChuyenTau ct " +
                    "JOIN ct.tau t " +
                    "JOIN t.danhSachToa toa " +
                    "JOIN toa.danhSachChoNgoi cn " +
                    "WHERE ct.maChuyen = :maChuyen";

            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            query.setParameter("maChuyen", maChuyen);

            Long result = query.getSingleResult();
            return result != null ? result.intValue() : 0;
        }



//    /**
//     * Cập nhật chuyến tàu.
//     * @param chuyenTau Chuyến tàu cần cập nhật.
//     * @return true nếu cập nhật thành công, ngược lại false.
//     */
//    public static boolean capNhatChuyenTau(ChuyenTau chuyenTau) {
//        String sql = "UPDATE ChuyenTau SET maTau = ?, maGaDi = ?, maGaDen = ?, ngayGioKhoiHanh = ?, ngayGioDen = ?, trangThai = ? WHERE maChuyen = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, chuyenTau.getTau().getMaTau());
//            stmt.setInt(2, chuyenTau.getGaDi().getMaGa());
//            stmt.setInt(3, chuyenTau.getGaDen().getMaGa());
//            stmt.setTimestamp(4, Timestamp.valueOf(chuyenTau.getNgayGioDi()));
//            stmt.setTimestamp(5, Timestamp.valueOf(chuyenTau.getNgayGioDen()));
//            stmt.setString(6, chuyenTau.getTrangThai());
//            stmt.setString(7, chuyenTau.getMaChuyen());
//
//            return stmt.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }
    /**
     * Cập nhật chuyến tàu.
     *
     * @param chuyenTau Chuyến tàu cần cập nhật.
     * @return true nếu cập nhật thành công, ngược lại false.
     */
    public static boolean capNhatChuyenTau(ChuyenTau chuyenTau) {
        try {
            //tìm chuyến tàu theo mã chuyến
            ChuyenTau ct = em.find(ChuyenTau.class, chuyenTau.getMaChuyen());
            if (ct != null) {
                // Cập nhật các trường dữ liệu của chuyến tàu
                ct.setTau(chuyenTau.getTau());
                ct.setGaDi(chuyenTau.getGaDi());
                ct.setGaDen(chuyenTau.getGaDen());
                ct.setNgayGioDi(chuyenTau.getNgayGioDi());
                ct.setNgayGioDen(chuyenTau.getNgayGioDen());
                ct.setTrangThai(chuyenTau.getTrangThai());

                // Lưu lại thay đổi
                em.getTransaction().begin();
                em.merge(ct); // Cập nhật chuyến tàu trong cơ sở dữ liệu
                em.getTransaction().commit();

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        return false;
    }

//    /**
//     * Lấy chuyến tàu theo mã tàu, mã ga đi, mã ga đến, ngày giờ đi và ngày giờ đến.
//     *
//     * @param maTau      Mã tàu
//     * @param maGaDi     Mã ga đi
//     * @param maGaDen    Mã ga đến
//     * @param ngayGioDi  Ngày giờ đi
//     * @param ngayGioDen Ngày giờ đến
//     * @return Chuyến tàu tìm được.
//     */

//    public static ChuyenTau getChuyenTauTheoMaTauMaGaDiMaGaDenNgayGioDiNgayGioDen(String maTau, int maGaDi, int maGaDen, LocalDateTime ngayGioDi, LocalDateTime ngayGioDen) {
//        String sql = "SELECT * FROM ChuyenTau WHERE maTau = ? AND maGaDi = ? AND maGaDen = ? AND ngayGioKhoiHanh = ? AND ngayGioDen = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maTau);
//            stmt.setInt(2, maGaDi);
//            stmt.setInt(3, maGaDen);
//            stmt.setTimestamp(4, Timestamp.valueOf(ngayGioDi));
//            stmt.setTimestamp(5, Timestamp.valueOf(ngayGioDen));
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                String maChuyen = rs.getString("maChuyen");
//                String macTau = rs.getString("macTau");
//                String trangThai = rs.getString("trangThai");
//                return new ChuyenTau(maChuyen, macTau, DAOTau.getTauTheoMa(maTau), DAOGa.getGaTheoMaGa(maGaDi), DAOGa.getGaTheoMaGa(maGaDen), ngayGioDi, ngayGioDen, trangThai);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
    /**
     * Lấy chuyến tàu theo mã tàu, mã ga đi, mã ga đến, ngày giờ đi và ngày giờ đến.
     * @param maTau Mã tàu
     * @param maGaDi Mã ga đi
     * @param maGaDen Mã ga đến
     * @param ngayGioDi Ngày giờ đi
     * @param ngayGioDen Ngày giờ đến
     * @return Chuyến tàu tìm được.
    //     */
    public static ChuyenTau getChuyenTauTheoMaTauMaGaDiMaGaDenNgayGioDiNgayGioDen(String maTau, int maGaDi, int maGaDen, LocalDateTime ngayGioDi, LocalDateTime ngayGioDen) {
        try {
            // Sử dụng JPQL để lấy chuyến tàu
            TypedQuery<ChuyenTau> query = em.createQuery(
                    "SELECT ct FROM ChuyenTau ct " +
                            "WHERE ct.tau.maTau = :maTau " +
                            "AND ct.gaDi.maGa = :maGaDi " +
                            "AND ct.gaDen.maGa = :maGaDen " +
                            "AND ct.ngayGioDi = :ngayGioDi " +
                            "AND ct.ngayGioDen = :ngayGioDen", ChuyenTau.class);

            query.setParameter("maTau", maTau);
            query.setParameter("maGaDi", maGaDi);
            query.setParameter("maGaDen", maGaDen);
            query.setParameter("ngayGioDi", ngayGioDi);
            query.setParameter("ngayGioDen", ngayGioDen);

            List<ChuyenTau> result = query.getResultList();
            if (!result.isEmpty()) {
                return result.get(0); // Nếu có kết quả, trả về chuyến tàu đầu tiên
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
