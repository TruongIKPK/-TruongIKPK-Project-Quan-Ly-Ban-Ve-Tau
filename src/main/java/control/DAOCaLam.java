package control;

import connectDB.ConnectDB;
import entity.CaLam;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;


public class DAOCaLam {

    private static EntityManager em;

    public DAOCaLam(EntityManager entityManager) {
        this.em = entityManager;
    }
    // lay danh sach ca lam
    public static ArrayList<CaLam> getDanhSachCaLam() {
        ArrayList<CaLam> dsCaLam = new ArrayList<>();
        String jpql = "SELECT cl FROM CaLam cl";
        TypedQuery<CaLam> query = em.createQuery(jpql, CaLam.class);
        dsCaLam.addAll(query.getResultList());
        return dsCaLam;
    }
    // them ca lam
    public static boolean themCaLam(CaLam cl) {
        try {
            em.getTransaction().begin();
            em.persist(cl);  // Thêm ca làm vào cơ sở dữ liệu
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return false;
    }
    // sua ca lam tra ve doi tuong ca lam
    public static CaLam suaCaLam(CaLam cl) {
        try {
            em.getTransaction().begin();
            CaLam existingCaLam = em.find(CaLam.class, cl.getMaCL());  // Tìm ca làm cũ theo mã
            if (existingCaLam != null) {
                existingCaLam.setTenCL(cl.getTenCL());
                existingCaLam.setGioBD(cl.getGioBD());
                existingCaLam.setGioKetThuc(cl.getGioKetThuc());
                em.getTransaction().commit();
                return existingCaLam;  // Trả về ca làm đã sửa
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
    // xoa ca lam
    public static boolean xoaCaLam(String maCL) {
        try {
            em.getTransaction().begin();
            CaLam cl = em.find(CaLam.class, maCL);  // Tìm ca làm theo mã
            if (cl != null) {
                em.remove(cl);  // Xóa ca làm
                em.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return false;
    }
    // lay ca lam theo ma
    public static CaLam getCaLamTheoMa(String maCL) {
        try {
            return em.find(CaLam.class, maCL);  // Tìm ca làm theo mã
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



//    // lay danh sach ca lam
//    public static ArrayList<CaLam> getDanhSachCaLam() {
//        ArrayList<CaLam> dsCaLam = new ArrayList<>();
//        String sql = "SELECT * FROM CaLam";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                String maCL = rs.getString("MaCL");
//                String tenCL = rs.getString("TenCL");
//                LocalTime gioBD = rs.getTime("GioBD").toLocalTime();
//                LocalTime gioKetThuc = rs.getTime("gioKetThuc").toLocalTime();
//                CaLam cl = new CaLam(maCL, tenCL, gioBD, gioKetThuc);
//                dsCaLam.add(cl);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return dsCaLam;
//    }
//
//    // them ca lam
//    public static boolean themCaLam(CaLam cl) {
//        String sql = "INSERT INTO CaLam(maCL, tenCL, gioBD, gioKetThuc) VALUES(?, ?, ?, ?)";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, cl.getMaCL());
//            stmt.setString(2, cl.getTenCL());
//            stmt.setTime(3, Time.valueOf(cl.getGioBD()));
//            stmt.setTime(4, Time.valueOf(cl.getGioKetThuc()));
//            return stmt.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    // sua ca lam tra ve doi tuong ca lam
//    public static CaLam suaCaLam(CaLam cl) {
//        String sql = "UPDATE CaLam SET TenCL = ?, GioBD = ?, GioKetThuc = ? WHERE MaCL = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, cl.getTenCL());
//            stmt.setTime(2, Time.valueOf(cl.getGioBD()));
//            stmt.setTime(3, Time.valueOf(cl.getGioKetThuc()));
//            stmt.setString(4, cl.getMaCL());
//            if (stmt.executeUpdate() > 0) {
//                return cl;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    // xoa ca lam
//    public static boolean xoaCaLam(String maCL) {
//        String sql = "DELETE FROM CaLam WHERE MaCL = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maCL);
//            return stmt.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    // lay ca lam theo ma
//    public static CaLam getCaLamTheoMa(String maCL) {
//        CaLam cl = null;
//        String sql = "SELECT * FROM CaLam WHERE MaCL = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maCL);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    String ma = rs.getString("MaCL");
//                    String ten = rs.getString("TenCL");
//                    LocalTime gioBD = rs.getTime("GioBD").toLocalTime();
//                    LocalTime gioKetThuc = rs.getTime("GioKetThuc").toLocalTime();
//                    cl = new CaLam(ma, ten, gioBD, gioKetThuc);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return cl;
//    }
}
