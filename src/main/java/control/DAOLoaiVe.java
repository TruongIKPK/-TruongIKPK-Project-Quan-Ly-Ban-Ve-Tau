package control;

import connectDB.ConnectDB;
import entity.LoaiCho;
import entity.LoaiVe;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class DAOLoaiVe {
    /*
     * CREATE TABLE LoaiVe
     * (
     * maLoai VARCHAR(10) PRIMARY KEY,
     * tenLoai NVARCHAR(50) NOT NULL
     */
    // them loai ve
//    public static boolean themLoaiVe(LoaiVe lv) {
//        String sql = "INSERT INTO LoaiVe(maLoai, tenLoai) VALUES(?,?)";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//
//            stmt.setString(1, lv.getMaLV());
//            stmt.setString(2, lv.getTenLV());
//            return stmt.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
    private static EntityManager em;
    public DAOLoaiVe(EntityManager em) {
        this.em = em;
    }
    public static boolean themLoaiVe(LoaiVe lv) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(lv);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (tr.isActive()) {
                tr.rollback();
            }
            return false;
        }
    }

    // xoa loai ve
//    public static boolean xoaLoaiVe(String maLV) {
//        String sql = "DELETE FROM LoaiVe WHERE maLoai = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//
//            stmt.setString(1, maLV);
//            return stmt.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    public static boolean xoaLoaiVe(String maLV) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            LoaiVe lv = em.find(LoaiVe.class, maLV);
            if (lv != null) {
                em.remove(lv);
                tr.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return false;
    }

    // sua loai ve
//    public static LoaiVe suaLoaiVe(LoaiVe lv) {
//        String sql = "UPDATE LoaiVe SET tenLoai = ? WHERE maLoai = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, lv.getTenLV());
//            stmt.setString(2, lv.getMaLV());
//            if (stmt.executeUpdate() > 0) {
//                return lv;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static LoaiVe suaLoaiVe(LoaiVe lv) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            LoaiVe existingLoaiVe = em.find(LoaiVe.class, lv.getMaLV());
            if (existingLoaiVe != null) {
                existingLoaiVe.setTenLV(lv.getTenLV());
                em.merge(existingLoaiVe);
                tr.commit();
                return existingLoaiVe;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return null;
    }

    // lay danh sach loai ve
//    public static ArrayList<LoaiVe> layDSLoaiVe() {
//        ArrayList<LoaiVe> dsLoaiVe = new ArrayList<>();
//        String sql = "SELECT * FROM LoaiVe";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                String maLV = rs.getString(1);
//                String tenLV = rs.getString(2);
//                LoaiVe lv = new LoaiVe(maLV, tenLV);
//                dsLoaiVe.add(lv);
//            }
//            return dsLoaiVe;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static ArrayList<LoaiVe> layDSLoaiVe() {
        ArrayList<LoaiVe> dsLoaiVe = new ArrayList<>();
        String jpql = "SELECT lv FROM LoaiVe lv";
        try {
            dsLoaiVe = (ArrayList<LoaiVe>) em.createQuery(jpql, LoaiVe.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsLoaiVe;
    }

    // lay loai ve theo ma loai
//    public static LoaiVe layLoaiVeTheoMa(String maLV) {
//        String sql = "SELECT * FROM LoaiVe WHERE maLoai = ?";
//        try (
//                PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maLV);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    String maLoai = rs.getString(1);
//                    String tenLoai = rs.getString(2);
//                    return new LoaiVe(maLoai, tenLoai);
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static LoaiVe layLoaiVeTheoMa(String maLV) {
        LoaiVe loaiVe = null;
        String jpql = "SELECT lv FROM LoaiVe lv WHERE lv.maLoai = :maLoai";
        try {
            loaiVe = em.createQuery(jpql, LoaiVe.class)
                    .setParameter("maLoai", maLV)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loaiVe;
    }
}
