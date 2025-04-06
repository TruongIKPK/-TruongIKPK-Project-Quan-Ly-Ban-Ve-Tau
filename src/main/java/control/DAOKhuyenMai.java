package control;

import connectDB.ConnectDB;
import connectDB.connectDB_1;
import entity.ChucVu;
import entity.KhuyenMai;
import entity.Ve;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import service.KhuyenMaiService;
import service.VeService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DAOKhuyenMai {
    /*maKM        VARCHAR(15) PRIMARY KEY,
    ngayApDung  DATE NOT NULL,
    ngayKetThuc DATE NOT NULL,
    phanTramKM  FLOAT CHECK (phanTramKM > 0 AND phanTramKM <= 1),
    CHECK (ngayApDung >= CONVERT(DATE, GETDATE())),
    CHECK (ngayKetThuc > ngayApDung)*/
    // them INSERT INTO KhuyenMai (ngayApDung, ngayKetThuc, phanTramKM)
//    public static boolean themKhuyenMai(KhuyenMai km) {
//        String sql = "INSERT INTO KhuyenMai(ngayApDung, ngayKetThuc, doiTuong, phanTramKM) VALUES(?, ?, ?, ?)";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setDate(1, Date.valueOf(km.getNgayBD())); // Chuyển đổi LocalDate thành Date
//            stmt.setDate(2, Date.valueOf(km.getNgayKT())); // Chuyển đổi LocalDate thành Date
//            stmt.setString(3, km.getDoiTuong());
//            System.out.println(km.getDoiTuong());
//            stmt.setDouble(4, km.getPhanTramKM());
//            return stmt.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    private static EntityManager em = connectDB_1.getEntityManager();

    public static boolean themKhuyenMai(KhuyenMai km) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            KhuyenMaiService khuyenMaiService = new KhuyenMaiService(em);
            khuyenMaiService.persistKhuyenMai(km);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    // lay tat ca cac doi tuong khuyen mai
//    public static ArrayList<String> getDSDoiTuongKhuyenMai() {
//        ArrayList<String> dsDoiTuong = new ArrayList<>();
//        String sql = "SELECT DISTINCT doiTuong FROM KhuyenMai";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                String doiTuong = rs.getString("doiTuong");
//                dsDoiTuong.add(doiTuong);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dsDoiTuong;
//    }

//    public static ArrayList<String> getDSDoiTuongKhuyenMai() {
//        ArrayList<String> dsDoiTuong = new ArrayList<>();
//        String jpql = "SELECT DISTINCT km.doiTuong FROM KhuyenMai km";
//
//        try {
//            dsDoiTuong = (ArrayList<String>) em.createQuery(jpql, KhuyenMai.class)
//                    .getResultList();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dsDoiTuong;
//    }

    public static ArrayList<String> getDSDoiTuongKhuyenMai() {
        String jpql = "SELECT DISTINCT km.doiTuong FROM KhuyenMai km";

        TypedQuery<String> query = em.createQuery(jpql, String.class);

        List<String> dsDoiTuong = query.getResultList();

        return new ArrayList<>(dsDoiTuong);
    }

    // sua doi tuong khuyen mai tra ve doi tuong khuyen mai
//    public static KhuyenMai suaKhuyenMai(KhuyenMai km) {
//        String sql = "UPDATE KhuyenMai SET ngayApDung = ?, ngayKetThuc = ?, phanTramKM = ? WHERE maKM = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setDate(1, Date.valueOf(km.getNgayBD())); // Chuyển đổi LocalDate thành Date
//            stmt.setDate(2, Date.valueOf(km.getNgayKT())); // Chuyển đổi LocalDate thành Date
//            stmt.setDouble(3, km.getPhanTramKM());
//            stmt.setString(4, km.getMaKM());
//            if(stmt.executeUpdate() > 0) {
//                return km;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static KhuyenMai suaKhuyenMai(KhuyenMai km) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();

            String jpql = "UPDATE KhuyenMai km " +
                    "SET km.ngayBD = :ngayBD, " +
                    "km.ngayKT = :ngayKT, " +
                    "km.phanTramKM = :phanTramKM " +
                    "WHERE km.maKM = :maKM";

            Query query = em.createQuery(jpql);
            query.setParameter("ngayBD", km.getNgayBD());
            query.setParameter("ngayKT", km.getNgayKT());
            query.setParameter("phanTramKM", km.getPhanTramKM());
            query.setParameter("maKM", km.getMaKM());

            int kmToUpdated = query.executeUpdate();

            if (kmToUpdated > 0) {
                tr.commit();
                return km;
            } else {
                tr.rollback();
            }
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }


    // xoa
//    public static boolean xoaKhuyenMai(String maKM) {
//        String sql = "DELETE FROM KhuyenMai WHERE maKM = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maKM);
//            return stmt.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    public static boolean xoaKhuyenMai(String maKM) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            KhuyenMai km = em.find(KhuyenMai.class, maKM);
            em.remove(km);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    // lay danh sach khuyen mai
//    public static ArrayList<KhuyenMai> getDSKhuyenMai() {
//        ArrayList<KhuyenMai> dsKhuyenMai = new ArrayList<>();
//        String sql = "SELECT * FROM KhuyenMai";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                String maKM = rs.getString("maKM");
//                LocalDate ngayBD = rs.getDate("ngayApDung").toLocalDate(); // Chuyển đổi Date thành LocalDate
//                LocalDate ngayKT = rs.getDate("ngayKetThuc").toLocalDate(); // Chuyển đổi Date thành LocalDate
//                String doiTuong = rs.getString("doiTuong");
//                double phanTramKM = rs.getDouble("phanTramKM");
//                Boolean daGuiThongBao = (Boolean) rs.getObject("daGuiThongBao");
//                if (daGuiThongBao == null) {
//                    daGuiThongBao = false; // Nếu là null, gán là false
//                }
//
//                KhuyenMai km = new KhuyenMai(maKM, ngayBD, ngayKT, doiTuong, phanTramKM, daGuiThongBao);
//                dsKhuyenMai.add(km);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dsKhuyenMai;
//    }

    public static ArrayList<KhuyenMai> getDSKhuyenMai() {
        ArrayList<KhuyenMai> dsKhuyenMai = new ArrayList<>();
        String jpql = "SELECT km FROM KhuyenMai km";

        try {
            dsKhuyenMai = (ArrayList<KhuyenMai>) em.createQuery(jpql, KhuyenMai.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsKhuyenMai;
    }

//    public static void main(String[] args) {
//        // get ds doi tuong khuyen mai
//        try {
//            ConnectDB.connect();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        ArrayList<String> dsDoiTuong = getDSDoiTuongKhuyenMai();
//        for (String doiTuong : dsDoiTuong) {
//            System.out.println(doiTuong);
//        }
//    }

    // lay khuyen mai theo ma
//    public  static KhuyenMai getKhuyenMaiTheoMa(String maKM) {
//        KhuyenMai km = null;
//        String sql = "SELECT * FROM KhuyenMai WHERE maKM = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maKM);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    LocalDate ngayBD = rs.getDate("ngayApDung").toLocalDate(); // Chuyển đổi Date thành LocalDate
//                    LocalDate ngayKT = rs.getDate("ngayKetThuc").toLocalDate(); // Chuyển đổi Date thành LocalDate
//                    String doiTuong = rs.getString("doiTuong");
//                    double phanTramKM = rs.getDouble("phanTramKM");
//                    Boolean daGuiThongBao = (Boolean) rs.getObject("daGuiThongBao");
//                    if (daGuiThongBao == null) {
//                        daGuiThongBao = false; // Nếu là null, gán là false
//                    }
//
//                    km = new KhuyenMai(maKM, ngayBD, ngayKT, doiTuong, phanTramKM, daGuiThongBao);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return km;
//    }

   public static ArrayList<KhuyenMai> getKhuyenMaiTheoMa(String maKM) {
        ArrayList<KhuyenMai> dsKhuyenMai = new ArrayList<>();
        String jpql = "SELECT km FROM KhuyenMai km WHERE km.maKM = :maKM";

        try {
            dsKhuyenMai = (ArrayList<KhuyenMai>) em.createQuery(jpql, KhuyenMai.class)
                    .setParameter("maKM", maKM)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsKhuyenMai;
    }

    // lay ds ma khuyenMai theo ma like
//    public static ArrayList<KhuyenMai> getDSMaKhuyenMaiTheoMaLike(String maKM) {
//        ArrayList<KhuyenMai> dsMaKM = new ArrayList<>();
//        String sql = "SELECT * FROM KhuyenMai WHERE maKM LIKE ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, "%" + maKM + "%");
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    String maKM1 = rs.getString("maKM");
//                    LocalDate ngayBD = rs.getDate("ngayApDung").toLocalDate(); // Chuyển đổi Date thành LocalDate
//                    LocalDate ngayKT = rs.getDate("ngayKetThuc").toLocalDate(); // Chuyển đổi Date thành LocalDate
//                    String doiTuong = rs.getString("doiTuong");
//                    double phanTramKM = rs.getDouble("phanTramKM");
//                    Boolean daGuiThongBao = (Boolean) rs.getObject("daGuiThongBao");
//                    if (daGuiThongBao == null) {
//                        daGuiThongBao = false; // Nếu là null, gán là false
//                    }
//
//                    KhuyenMai km = new KhuyenMai(maKM, ngayBD, ngayKT, doiTuong, phanTramKM, daGuiThongBao);
//                    dsMaKM.add(km);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dsMaKM;
//    }

    public static ArrayList<KhuyenMai> getDSMaKhuyenMaiTheoMaLike(String maKM) {
        ArrayList<KhuyenMai> dsMaKM = new ArrayList<>();
        String jpql = "SELECT km FROM KhuyenMai km WHERE km.maKM LIKE :maKM";
        try {
            dsMaKM = (ArrayList<KhuyenMai>) em.createQuery(jpql, KhuyenMai.class)
                    .setParameter("maKM", "%" + maKM + "%")
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsMaKM;
    }

    // lay khuyen mai theo ngay
//    public static KhuyenMai getKhuyenMaiTheoNgay(LocalDate ngay) {
//        KhuyenMai km = null;
//        String sql = "SELECT * FROM KhuyenMai WHERE ? BETWEEN ngayApDung AND ngayKetThuc";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setDate(1, Date.valueOf(ngay)); // Chuyển đổi LocalDate thành Date
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    String maKM = rs.getString("maKM");
//                    LocalDate ngayBD = rs.getDate("ngayApDung").toLocalDate(); // Chuyển đổi Date thành LocalDate
//                    LocalDate ngayKT = rs.getDate("ngayKetThuc").toLocalDate(); // Chuyển đổi Date thành LocalDate
//                    String doiTuong = rs.getString("doiTuong");
//                    double phanTramKM = rs.getDouble("phanTramKM");
//
//                    // Sử dụng rs.getObject() để lấy giá trị daGuiThongBao có thể là null
//                    Boolean daGuiThongBao = (Boolean) rs.getObject("daGuiThongBao");
//                    if (daGuiThongBao == null) {
//                        daGuiThongBao = false; // Nếu là null, gán là false
//                    }
//
//                    km = new KhuyenMai(maKM, ngayBD, ngayKT, doiTuong, phanTramKM, daGuiThongBao);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return km;
//    }

    public static ArrayList<KhuyenMai> getKhuyenMaiTheoNgay(LocalDate ngay) {
        ArrayList<KhuyenMai> dsKMTheoNgay = new ArrayList<>();
        String jdql = "SELECT km FROM KhuyenMai km WHERE :ngay BETWEEN km.ngayBD AND km.ngayKT";

        try {
            dsKMTheoNgay = (ArrayList<KhuyenMai>) em.createQuery(jdql, KhuyenMai.class)
                    .setParameter("ngay", ngay)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsKMTheoNgay;
    }

//    public static void capNhatTrangThaiDaGuiThongBao(String maKM) {
//        String sql = "UPDATE KhuyenMai SET daGuiThongBao = 1 WHERE maKM = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maKM);
//            stmt.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static void capNhatTrangThaiDaGuiThongBao(String maKM) {
        EntityTransaction tr = em.getTransaction();
        String jdql = "UPDATE KhuyenMai km SET km.daGuiThongBao = 1 WHERE km.maKM = :maKM";

        try {
            tr.begin();
            Query query = em.createQuery(jdql);
            query.setParameter("maKM", maKM);
            query.executeUpdate();
            tr.commit();
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            e.printStackTrace();
        }
    }
}