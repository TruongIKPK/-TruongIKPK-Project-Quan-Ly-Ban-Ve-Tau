package control;

import connectDB.ConnectDB;
import connectDB.connectDB_1;
import entity.LoaiToa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import service.ToaService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @Dự án: tau-viet-express
 * @Class: DAOLoaiToa
 * @Tạo vào ngày: 10/15/2024
 * @Tác giả: Thai
 */
public class DAOLoaiToa {
    /*CREATE TABLE LoaiToa (
        maLoai VARCHAR(10) PRIMARY KEY,
        tenLoai NVARCHAR(50) NOT NULL
    );*/
//    // Thêm loại toa
//    public static boolean themLoaiToa(LoaiToa loaiToa) {
//        String sql = "INSERT INTO LoaiToa(maLoai, tenLoai) VALUES(?, ?)";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, loaiToa.getMaLT());
//            stmt.setString(2, loaiToa.getTenLT());
//            return stmt.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
    private static EntityManager em = connectDB_1.getEntityManager();
    // Thêm loại toa
    public static boolean themLoaiToa(LoaiToa loaiToa) {
        try {
            em.getTransaction().begin();
            em.persist(loaiToa);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        return false;
    }


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
public static LoaiToa suaLoaiToa(LoaiToa loaiToa) {
    try {
        em.getTransaction().begin();
        LoaiToa updatedLoaiToa = em.merge(loaiToa);
        em.getTransaction().commit();
        return updatedLoaiToa;
    } catch (Exception e) {
        e.printStackTrace();
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
    return null;
}

//    //xoa loai toa
//    public static boolean xoaLoaiToa(String maLT) {
//        String sql = "DELETE FROM LoaiToa WHERE maLoai = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maLT);
//            return stmt.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }


    //xoa loai toa
    public static boolean xoaLoaiToa(String maLT) {
        try {
            em.getTransaction().begin();
            LoaiToa loaiToa = em.find(LoaiToa.class, maLT);
            if (loaiToa != null) {
                em.remove(loaiToa);
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

//
//    //lay tat ca loaitoa
//    public static ArrayList<LoaiToa> getDSLoaiToa() {
//        ArrayList<LoaiToa> dsLoaiToa = new ArrayList<>();
//        String sql = "SELECT * FROM LoaiToa ORDER BY tenLoai";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                String maLT = rs.getString("maLoai");
//                String tenLT = rs.getString("tenLoai");
//                LoaiToa loaiToa = new LoaiToa(maLT, tenLT);
//                dsLoaiToa.add(loaiToa);
//            }
//            return dsLoaiToa;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//lay tat ca loaitoa
    public static ArrayList<LoaiToa> getDSLoaiToa() {
        ArrayList<LoaiToa> dsLoaiToa = new ArrayList<>();
        try {
            String jpql = "SELECT lt FROM LoaiToa lt ORDER BY lt.tenLT";
            TypedQuery<LoaiToa> query = em.createQuery(jpql, LoaiToa.class);
            dsLoaiToa = new ArrayList<>(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsLoaiToa;
    }
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
public static LoaiToa getLoaiToaTheoMa(String maLT) {
    try {
        String jpql = "SELECT lt FROM LoaiToa lt WHERE lt.maLT = :maLT";
        TypedQuery<LoaiToa> query = em.createQuery(jpql, LoaiToa.class);
        query.setParameter("maLT", maLT);
        return query.getSingleResult();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
}