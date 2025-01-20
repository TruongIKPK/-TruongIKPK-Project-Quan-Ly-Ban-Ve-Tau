package control;

import connectDB.ConnectDB;
import entity.ChucVu;
import entity.ChucVu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOChucVu {

    private EntityManager em;

    public DAOChucVu(EntityManager em) {
        this.em = em;
    }

    // Lấy danh sách chức vụ
    public ArrayList<ChucVu> getDanhSachChucVu() {
        ArrayList<ChucVu> dsChucVu = new ArrayList<>();
        String jpql = "SELECT cv FROM ChucVu cv";
        TypedQuery<ChucVu> query = em.createQuery(jpql, ChucVu.class);
        dsChucVu.addAll(query.getResultList());
        return dsChucVu;
    }

    // Lấy chức vụ theo mã
    public ChucVu getChucVuTheoMa(String maCV) {
        String jpql = "SELECT cv FROM ChucVu cv WHERE cv.maCV = :maCV";
        TypedQuery<ChucVu> query = em.createQuery(jpql, ChucVu.class);
        query.setParameter("maCV", maCV);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    // Thêm chức vụ
    @Transactional
    public boolean themChucVu(ChucVu cv) {
        try {
            em.persist(cv);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Sửa chức vụ, trả về đối tượng ChucVu đã cập nhật
    @Transactional
    public ChucVu suaChucVu(ChucVu cv) {
        String jpql = "UPDATE ChucVu cv SET cv.tenCV = :tenCV WHERE cv.maCV = :maCV";
        int updatedCount = em.createQuery(jpql)
                .setParameter("tenCV", cv.getTenCV())
                .setParameter("maCV", cv.getMaCV())
                .executeUpdate();
        return updatedCount > 0 ? cv : null;
    }

//    // Get list of ChucVu
//    public static ArrayList<ChucVu> getDanhSachChucVu() {
//        ArrayList<ChucVu> dsChucVu = new ArrayList<>();
//        String sql = "SELECT * FROM ChucVu";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//
//            while (rs.next()) {
//                String maCV = rs.getString("MaCV");
//                String tenCV = rs.getString("TenCV");
//
//                ChucVu cv = new ChucVu(maCV, tenCV);
//                dsChucVu.add(cv);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return dsChucVu;
//    }
//
////     Get ChucVu by maCV
//    public static ChucVu getChucVuTheoMa(String maCV) {
//        ChucVu cv = null;
//        String sql = "SELECT * FROM ChucVu WHERE MaCV = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//
//            stmt.setString(1, maCV);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    String ma = rs.getString("MaCV");
//                    String ten = rs.getString("TenCV");
//                    cv = new ChucVu(ma, ten);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return cv;
//    }
//
//    // them chuc vu
//    public static boolean themChucVu(ChucVu cv) {
//        String sql = "INSERT INTO ChucVu(maCV, tenCV) VALUES(?, ?)";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, cv.getMaCV());
//            stmt.setString(2, cv.getTenCV());
//            return stmt.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    //sua chuc vu tra ve doi tuong chuc vu
//    public static ChucVu suaChucVu(ChucVu cv) {
//        String sql = "UPDATE ChucVu SET tenCV = ? WHERE maCV = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, cv.getTenCV());
//            stmt.setString(2, cv.getMaCV());
//            if (stmt.executeUpdate() > 0) {
//                return cv;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}