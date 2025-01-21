package control;

import connectDB.ConnectDB;
import entity.LoaiCho;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static control.DAOToa.em;

public class DAOLoaiCho {
    // crud
    /*CREATE TABLE LoaiCho (
    maLC VARCHAR(10) PRIMARY KEY,
    tenLC NVARCHAR(50) NOT NULL UNIQUE,
    giaCho FLOAT CHECK (giaCho > 0),
    moTa NVARCHAR(200) NOT NULL
);*/

    // create loai cho
//    public static boolean themLoaiCho(LoaiCho lc) {
//        String sql = "INSERT INTO LoaiCho(maLC, tenLC, giaCho, moTa) VALUES(?, ?, ?, ?)";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, lc.getMaLC());
//            stmt.setString(2, lc.getTenLC());
//            stmt.setDouble(3, lc.getGiaCho());
//            stmt.setString(4, lc.getMoTa());
//            return stmt.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
    private static EntityManager em;
    public DAOLoaiCho(EntityManager em) {
        this.em = em;
    }

    public static boolean themLoaiCho(LoaiCho lc) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(lc);
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

    // read loai cho
//    public static ArrayList<LoaiCho> getDSLoaiCho() {
//        ArrayList<LoaiCho> dsLoaiCho = new ArrayList<>();
//        String sql = "SELECT * FROM LoaiCho";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                String maLC = rs.getString("maLC");
//                String tenLC = rs.getString("tenLC");
//                double giaCho = rs.getDouble("giaCho");
//                String moTa = rs.getString("moTa");
//                LoaiCho lc = new LoaiCho(maLC, tenLC, moTa, giaCho);
//                dsLoaiCho.add(lc);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dsLoaiCho;
//    }

    public static ArrayList<LoaiCho> getDSLoaiCho() {
        ArrayList<LoaiCho> dsLoaiCho = new ArrayList<>();
        String sqdl = "SELECT lc FROM LoaiCho lc";
        try {
            TypedQuery<LoaiCho> query = em.createQuery(sqdl, LoaiCho.class);
            dsLoaiCho = (ArrayList<LoaiCho>) query.getResultList();
            return dsLoaiCho;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // read loai cho by id
//    public static LoaiCho getLoaiChoByID(String maLC) {
//        String sql = "SELECT * FROM LoaiCho WHERE maLC = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maLC);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    String tenLC = rs.getString("tenLC");
//                    double giaCho = rs.getDouble("giaCho");
//                    String moTa = rs.getString("moTa");
//                    return new LoaiCho(maLC, tenLC, moTa, giaCho);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static LoaiCho getLoaiChoByID(String maLC) {
        String jqdl = "SELECT lc FROM LoaiCho lc WHERE lc.maLC = :maLC";
        try {
            TypedQuery<LoaiCho> query = em.createQuery(jqdl, LoaiCho.class);
            query.setParameter("maLC", maLC);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // update loai cho
//    public static LoaiCho suaLoaiCho(LoaiCho lc) {
//        String sql = "UPDATE LoaiCho SET tenLC = ?, giaCho = ?, moTa = ? WHERE maLC = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, lc.getTenLC());
//            stmt.setDouble(2, lc.getGiaCho());
//            stmt.setString(3, lc.getMoTa());
//            stmt.setString(4, lc.getMaLC());
//            if (stmt.executeUpdate() > 0) {
//                return lc;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static LoaiCho suaLoaiCho(LoaiCho lc) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            LoaiCho existingLoaiCho = em.find(LoaiCho.class, lc.getMaLC());
            if (existingLoaiCho != null) {
                existingLoaiCho.setTenLC(lc.getTenLC());
                existingLoaiCho.setGiaCho(lc.getGiaCho());
                existingLoaiCho.setMoTa(lc.getMoTa());
                em.merge(existingLoaiCho);
                tr.commit();
                return existingLoaiCho;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return null;
    }

    // delete loai cho
//    public static boolean xoaLoaiCho(String maLC) {
//        String sql = "DELETE FROM LoaiCho WHERE maLC = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maLC);
//            return stmt.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    public static boolean xoaLoaiCho(String maLC) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            LoaiCho existingLoaiCho = em.find(LoaiCho.class, maLC);
            if (existingLoaiCho != null) {
                em.remove(existingLoaiCho);
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
}
