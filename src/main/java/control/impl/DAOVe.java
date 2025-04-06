package control;

import connectDB.ConnectDB;
import connectDB.connectDB_1;
import entity.*;
import enums.ETrangThaiVe;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import service.VeService;

import java.math.BigDecimal;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.DecimalFormat;
import java.util.List;

public class DAOVe {

    /*CREATE TABLE Ve
(
    maVe          VARCHAR(20) PRIMARY KEY,
    maHoaDon      VARCHAR(20) NOT NULL,
    maLoaiVe      VARCHAR(10) NOT NULL,
    ngayGioXuatVe DATETIME    NOT NULL,
    maChoNgoi     VARCHAR(20) NOT NULL,
    maChuyenTau   VARCHAR(20) NOT NULL,
    maKhachHang   VARCHAR(20) NOT NULL,
    trangThai     NVARCHAR(20) CHECK (trangThai IN (N'Đã bán', N'Đã đổi', N'Đã trả', N'Vé được đổi', N'Vé được trả')) NOT NULL,
    thue          FLOAT DEFAULT 0.1,
    maKhuyenMai   VARCHAR(15),
    */

    // THÊM vé
//    public static boolean themVeCoKhuyenMai(Ve ve) {
//        String sql = "INSERT INTO Ve(maHoaDon, maLoaiVe, ngayGioXuatVe, maChoNgoi, maChuyenTau, " +
//                "maKhachHang, trangThai, maKhuyenMai ) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, ve.getHoaDon().getMaHD());
//            stmt.setString(2, ve.getLoaiVe().getMaLV());
//            stmt.setTimestamp(3, Timestamp.valueOf(ve.getNgayGioXuatVe()));
//            stmt.setString(4, ve.getChoNgoi().getMaCho());
//            stmt.setString(5, ve.getChuyenTau().getMaChuyen());
//            stmt.setString(6, ve.getKhachHang().getMaKH());
//            stmt.setString(7, ve.getTrangThai());
//            stmt.setString(8, ve.getKhuyenMai().getMaKM());
//            return stmt.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
    private static EntityManager em = connectDB_1.getEntityManager();

    public static boolean themVeCoKhuyenMai(Ve ve) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
                VeService veService = new VeService(em);
                veService.persistVe(ve);
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

    // them ve khong co khuyen mai
//    public static boolean themVeKhongKhuyenMai(Ve ve) {
//        String sql = "INSERT INTO Ve(maHoaDon, maLoaiVe, ngayGioXuatVe, maChoNgoi, maChuyenTau, maKhachHang, trangThai ) " +
//                "VALUES(?, ?, ?, ?, ?, ?, ?)";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, ve.getHoaDon().getMaHD());
//            stmt.setString(2, ve.getLoaiVe().getMaLV());
//            stmt.setTimestamp(3, Timestamp.valueOf(ve.getNgayGioXuatVe()));
//            stmt.setString(4, ve.getChoNgoi().getMaCho());
//            stmt.setString(5, ve.getChuyenTau().getMaChuyen());
//            stmt.setString(6, ve.getKhachHang().getMaKH());
//            stmt.setString(7, ve.getTrangThai());
//            stmt.setDouble(8, ve.getThanhTien());
//            return stmt.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    public static boolean themVeKhongKhuyenMai(Ve ve) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
                VeService veService = new VeService(em);
                veService.persistVe(ve);
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

    // SỬA vé(trang thai) tra ve doi tuong ve
//    public static boolean suaVe(Ve ve) {
//        String sql = "UPDATE Ve SET trangThai = ? WHERE maVe = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, ve.getTrangThai());
//            stmt.setString(2, ve.getMaVe());
//            return stmt.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    public static boolean suaVe(Ve ve) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Ve veToUpdate = em.find(Ve.class, ve.getMaVe());
            if (veToUpdate != null) {
                veToUpdate.setTrangThai(ve.getTrangThai());
                transaction.commit();
                return true;
            }
            transaction.rollback();
            return false;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    // lay ds vé theo mã hóa đơn
//    public static ArrayList<Ve> layDSVeTheoMaHD(String maHD) {
//        System.out.println("Lấy DS Vé theo mã hóa đơn" + maHD);
//        ArrayList<Ve> dsVe = new ArrayList<>();
//        String sql = "SELECT * FROM Ve WHERE maHoaDon = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maHD);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                String maVe = rs.getString("maVe");
//                System.out.println("MA VE" + maVe);
//                String maLoaiVe = rs.getString("maLoaiVe");
//                LoaiVe loaiVe = DAOLoaiVe.layLoaiVeTheoMa(maLoaiVe);
//                String maHD1 = rs.getString("maHoaDon");
//                HoaDon hoaDon = new HoaDon(maHD1);
//                LocalDateTime ngayGioXuatVe = rs.getTimestamp("ngayGioXuatVe").toLocalDateTime();
//                String maChoNgoi = rs.getString("maChoNgoi");
//                ChoNgoi choNgoi = DAOChoNgoi.getChoNgoiTheoMa(maChoNgoi);
//                String maChuyenTau = rs.getString("maChuyenTau");
//                ChuyenTau chuyenTau = DAOChuyenTau.getChuyenTauTheoMa(maChuyenTau);
//                String maKhachHang = rs.getString("maKhachHang");
//                KhachHang khachHang = DAOKhachHang.layKhachHangTheoMa(maKhachHang);
//                String trangThai = rs.getString("trangThai");
//                double thue = rs.getDouble("thue");
//                String maKhuyenMai = rs.getString("maKhuyenMai");
//                KhuyenMai khuyenMai = DAOKhuyenMai.getKhuyenMaiTheoMa(maKhuyenMai);
//
//                Ve ve = new Ve(maVe, hoaDon, loaiVe, ngayGioXuatVe, choNgoi, chuyenTau, khachHang, thue, khuyenMai, trangThai);
//                dsVe.add(ve);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("SIZE ds ve" + dsVe.size());
//        return dsVe;
//    }

    public static ArrayList<Ve> layDSVeTheoMaHD(String maHD) {
        ArrayList<Ve> dsVe = new ArrayList<>();
        String sql = "SELECT v FROM Ve v WHERE v.hoaDon.maHD = :maHD";
        try {
            dsVe = (ArrayList<Ve>) em.createQuery(sql, Ve.class).setParameter("maHD", maHD).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsVe;
    }


//    public static void main(String[] args) throws SQLException {
//        // Initialize the database connection
//        connectDB_1.connect();
//
//        // Retrieve tickets by invoice ID
//        ArrayList<Ve> dsVe = layDSVeTheoMaHD("HD241212000034");
//        for (Ve ve : dsVe) {
//            System.out.println(ve);
//        }
//
//        // Close the database connection
//        connectDB_1.close();
//    }

    // chỉ lấy vé đã bán
//    public static ArrayList<Ve> layDSVeDaBanTheoMaHD(String maHD) {
//        ArrayList<Ve> dsVe = new ArrayList<>();
//        String sql = "SELECT * FROM Ve WHERE maHoaDon = ? AND trangThai = N'Đã bán'";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maHD);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                String maVe = rs.getString("maVe");
//                String maLoaiVe = rs.getString("maLoaiVe");
//                LoaiVe loaiVe = DAOLoaiVe.layLoaiVeTheoMa(maLoaiVe);
//                String maHD1 = rs.getString("maHoaDon");
//                HoaDon hoaDon = new HoaDon(maHD1);
//                LocalDateTime ngayGioXuatVe = rs.getTimestamp("ngayGioXuatVe").toLocalDateTime();
//                String maChoNgoi = rs.getString("maChoNgoi");
//                ChoNgoi choNgoi = DAOChoNgoi.getChoNgoiTheoMa(maChoNgoi);
//                String maChuyenTau = rs.getString("maChuyenTau");
//                ChuyenTau chuyenTau = DAOChuyenTau.getChuyenTauTheoMa(maChuyenTau);
//                String maKhachHang = rs.getString("maKhachHang");
//                KhachHang khachHang = DAOKhachHang.layKhachHangTheoMa(maKhachHang);
//                String trangThai = rs.getString("trangThai");
//                double thue = rs.getDouble("thue");
//                String maKhuyenMai = rs.getString("maKhuyenMai");
//                KhuyenMai khuyenMai = DAOKhuyenMai.getKhuyenMaiTheoMa(maKhuyenMai);
//
//                Ve ve = new Ve(maVe, hoaDon, loaiVe, ngayGioXuatVe, choNgoi, chuyenTau, khachHang, thue, khuyenMai, trangThai);
//                dsVe.add(ve);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dsVe;
//    }

    public static ArrayList<Ve> layDSVeDaBanTheoMaHD(String maHD) {
        ArrayList<Ve> dsVe = new ArrayList<>();
        String sql = "SELECT v FROM Ve v WHERE v.hoaDon.maHD = :maHD AND v.trangThai = :trangThai";
        try {
            dsVe = (ArrayList<Ve>) em.createQuery(sql, Ve.class).setParameter("maHD", maHD).setParameter("trangThai", ETrangThaiVe.DA_BAN.name()).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsVe;
    }

    // lay ds vé theo mã chuyến tàu
//    public static ArrayList<Ve> layDSVeTheoMaChuyen(String maChuyen) {
//        ArrayList<Ve> dsVe = new ArrayList<>();
//        String sql = "SELECT * FROM Ve WHERE maChuyenTau = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maChuyen);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                String maVe = rs.getString("maVe");
//                String maLoaiVe = rs.getString("maLoaiVe");
//                LoaiVe loaiVe = DAOLoaiVe.layLoaiVeTheoMa(maLoaiVe);
//                String maHD1 = rs.getString("maHoaDon");
//                HoaDon hoaDon = new HoaDon(maHD1);
//                LocalDateTime ngayGioXuatVe = rs.getTimestamp("ngayGioXuatVe").toLocalDateTime();
//                String maChoNgoi = rs.getString("maChoNgoi");
//                ChoNgoi choNgoi = DAOChoNgoi.getChoNgoiTheoMa(maChoNgoi);
//                String maChuyenTau = rs.getString("maChuyenTau");
//                ChuyenTau chuyenTau = DAOChuyenTau.getChuyenTauTheoMa(maChuyenTau);
//                String maKhachHang = rs.getString("maKhachHang");
//                KhachHang khachHang = DAOKhachHang.layKhachHangTheoMa(maKhachHang);
//                String trangThai = rs.getString("trangThai");
//                double thue = rs.getDouble("thue");
//                String maKhuyenMai = rs.getString("maKhuyenMai");
//                KhuyenMai khuyenMai = DAOKhuyenMai.getKhuyenMaiTheoMa(maKhuyenMai);
//                Ve ve = new Ve(maVe, hoaDon, loaiVe, ngayGioXuatVe, choNgoi, chuyenTau, khachHang, thue, khuyenMai, trangThai);
//                dsVe.add(ve);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dsVe;
//    }

    public static ArrayList<Ve> layDSVeTheoMaChuyen(String maChuyen) {
        ArrayList<Ve> dsVe = new ArrayList<>();
        String sql = "SELECT v FROM Ve v WHERE v.chuyenTau.maChuyen = :maChuyen";
        try {
            dsVe = (ArrayList<Ve>) em.createQuery(sql, Ve.class).setParameter("maChuyen", maChuyen).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsVe;
    }

    // Get ds vé theo mã khách hàng
//    public static ArrayList<Ve> layDSVeTheoMaKH(String maKH) {
//        ArrayList<Ve> dsVe = new ArrayList<>();
//        String sql = "SELECT * FROM Ve WHERE maKhachHang = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maKH);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                String maVe = rs.getString("maVe");
//                String maLoaiVe = rs.getString("maLoaiVe");
//                LoaiVe loaiVe = DAOLoaiVe.layLoaiVeTheoMa(maLoaiVe);
//                String maHD1 = rs.getString("maHoaDon");
//                HoaDon hoaDon = new HoaDon(maHD1);
//                LocalDateTime ngayGioXuatVe = rs.getTimestamp("ngayGioXuatVe").toLocalDateTime();
//                String maChoNgoi = rs.getString("maChoNgoi");
//                ChoNgoi choNgoi = DAOChoNgoi.getChoNgoiTheoMa(maChoNgoi);
//                String maChuyenTau = rs.getString("maChuyenTau");
//                ChuyenTau chuyenTau = DAOChuyenTau.getChuyenTauTheoMa(maChuyenTau);
//                String maKhachHang = rs.getString("maKhachHang");
//                KhachHang khachHang = DAOKhachHang.layKhachHangTheoMa(maKhachHang);
//                String trangThai = rs.getString("trangThai");
//                double thue = rs.getDouble("thue");
//                String maKhuyenMai = rs.getString("maKhuyenMai");
//                KhuyenMai khuyenMai = DAOKhuyenMai.getKhuyenMaiTheoMa(maKhuyenMai);
//                Ve ve = new Ve(maVe, hoaDon, loaiVe, ngayGioXuatVe, choNgoi, chuyenTau, khachHang, thue, khuyenMai, trangThai);
//                dsVe.add(ve);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return dsVe;
//    }

    public static ArrayList<Ve> layDSVeTheoMaKH(String maKH) {
        ArrayList<Ve> dsVe = new ArrayList<>();
        String sql = "SELECT v FROM Ve v WHERE v.khachHang.maKH = :maKH";
        try {
            dsVe = (ArrayList<Ve>) em.createQuery(sql, Ve.class).setParameter("maKH", maKH).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsVe;
    }

    // Get vé theo mã
//    public static Ve layVeTheoMa(String maVe) {
//        String sql = "SELECT * FROM Ve WHERE maVe = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maVe);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                String maLoaiVe = rs.getString("maLoaiVe");
//                LoaiVe loaiVe = DAOLoaiVe.layLoaiVeTheoMa(maLoaiVe);
//                String maHD1 = rs.getString("maHoaDon");
//                HoaDon hoaDon = new HoaDon(maHD1);
//                LocalDateTime ngayGioXuatVe = rs.getTimestamp("ngayGioXuatVe").toLocalDateTime();
//                String maChoNgoi = rs.getString("maChoNgoi");
//                ChoNgoi choNgoi = DAOChoNgoi.getChoNgoiTheoMa(maChoNgoi);
//                String maChuyenTau = rs.getString("maChuyenTau");
//                ChuyenTau chuyenTau = DAOChuyenTau.getChuyenTauTheoMa(maChuyenTau);
//                String maKhachHang = rs.getString("maKhachHang");
//                KhachHang khachHang = DAOKhachHang.layKhachHangTheoMa(maKhachHang);
//                String trangThai = rs.getString("trangThai");
//                double thue = rs.getDouble("thue");
//                String maKhuyenMai = rs.getString("maKhuyenMai");
//                KhuyenMai khuyenMai = DAOKhuyenMai.getKhuyenMaiTheoMa(maKhuyenMai);
//                return new Ve(maVe, hoaDon, loaiVe, ngayGioXuatVe, choNgoi, chuyenTau, khachHang, thue, khuyenMai, trangThai);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static Ve layVeTheoMa(String maVe) {
        Ve ve = null;
        try {
            ve = em.find(Ve.class, maVe);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("VE--------------------------------------------------------------------------------------------------------Ve: " + ve);
        return ve;
    }


    // Get vé theo mã chuyến và mã khách hàng
//    public static Ve layVeTheoMaChuyenVaMaKH(String maChuyen, String maKH) {
//        String sql = "SELECT * FROM Ve WHERE maChuyenTau = ? AND maKhachHang = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maChuyen);
//            stmt.setString(2, maKH);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                String maVe = rs.getString("maVe");
//                String maLoaiVe = rs.getString("maLoaiVe");
//                LoaiVe loaiVe = DAOLoaiVe.layLoaiVeTheoMa(maLoaiVe);
//                String maHD1 = rs.getString("maHoaDon");
//                HoaDon hoaDon = new HoaDon(maHD1);
//                LocalDateTime ngayGioXuatVe = rs.getTimestamp("ngayGioXuatVe").toLocalDateTime();
//                String maChoNgoi = rs.getString("maChoNgoi");
//                ChoNgoi choNgoi = DAOChoNgoi.getChoNgoiTheoMa(maChoNgoi);
//                String maChuyenTau = rs.getString("maChuyenTau");
//                ChuyenTau chuyenTau = DAOChuyenTau.getChuyenTauTheoMa(maChuyenTau);
//                String maKhachHang = rs.getString("maKhachHang");
//                KhachHang khachHang = DAOKhachHang.layKhachHangTheoMa(maKhachHang);
//                String trangThai = rs.getString("trangThai");
//                double thue = rs.getDouble("thue");
//                String maKhuyenMai = rs.getString("maKhuyenMai");
//                KhuyenMai khuyenMai = DAOKhuyenMai.getKhuyenMaiTheoMa(maKhuyenMai);
//                return new Ve(maVe, hoaDon, loaiVe, ngayGioXuatVe, choNgoi, chuyenTau, khachHang, thue, khuyenMai, trangThai);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static Ve layVeTheoMaChuyenVaMaKH(String maChuyen, String maKH) {
        Ve ve = null;
        String sql = "SELECT v FROM Ve v WHERE v.chuyenTau.maChuyen = :maChuyen AND v.khachHang.maKH = :maKH";
        try {
            ve = (Ve) em.createQuery(sql, Ve.class).setParameter("maChuyen", maChuyen).setParameter("maKH", maKH).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ve;
    }


    //      --cau truy van timf ds vé theo sdt
//    SELECT * FROM Ve WHERE maKhachHang = (SELECT maKH FROM KhachHang WHERE sdt = '0987654321')
//    public static ArrayList<Ve> layDSVeTheoSDT(String sdt) {
//        ArrayList<Ve> dsVe = new ArrayList<>();
//        String sql = "SELECT * FROM Ve WHERE maKhachHang = (SELECT maKH FROM KhachHang WHERE sdt = ?)";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, sdt);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                String maVe = rs.getString("maVe");
//                String maLoaiVe = rs.getString("maLoaiVe");
//                LoaiVe loaiVe = DAOLoaiVe.layLoaiVeTheoMa(maLoaiVe);
//                String maHD1 = rs.getString("maHoaDon");
//                HoaDon hoaDon = new HoaDon(maHD1);
//                LocalDateTime ngayGioXuatVe = rs.getTimestamp("ngayGioXuatVe").toLocalDateTime();
//                String maChoNgoi = rs.getString("maChoNgoi");
//                ChoNgoi choNgoi = DAOChoNgoi.getChoNgoiTheoMa(maChoNgoi);
//                String maChuyenTau = rs.getString("maChuyenTau");
//                ChuyenTau chuyenTau = DAOChuyenTau.getChuyenTauTheoMa(maChuyenTau);
//                String maKhachHang = rs.getString("maKhachHang");
//                KhachHang khachHang = DAOKhachHang.layKhachHangTheoMa(maKhachHang);
//                String trangThai = rs.getString("trangThai");
//                double thue = rs.getDouble("thue");
//                String maKhuyenMai = rs.getString("maKhuyenMai");
//                KhuyenMai khuyenMai = DAOKhuyenMai.getKhuyenMaiTheoMa(maKhuyenMai);
//                Ve ve = new Ve(maVe, hoaDon, loaiVe, ngayGioXuatVe, choNgoi, chuyenTau, khachHang, thue, khuyenMai, trangThai);
//                dsVe.add(ve);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dsVe;
//    }

    public static ArrayList<Ve> layDSVeTheoSDT(String sdt) {
        ArrayList<Ve> dsVe = new ArrayList<>();
        String sql = "SELECT v FROM Ve v WHERE v.khachHang.sdt = :sdt";
        try {
            dsVe = (ArrayList<Ve>) em.createQuery(sql, Ve.class).setParameter("sdt", sdt).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsVe;
    }

    // Get danh sách vé gần nhất
//    public static ArrayList<Ve> layDSVeGanNhat() {
//        ArrayList<Ve> dsVe = new ArrayList<>();
//        String sql = "SELECT TOP 10 * FROM Ve ORDER BY ngayGioXuatVe DESC";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                String maVe = rs.getString("maVe");
//                String maLoaiVe = rs.getString("maLoaiVe");
//                LoaiVe loaiVe = DAOLoaiVe.layLoaiVeTheoMa(maLoaiVe);
//                String maHD1 = rs.getString("maHoaDon");
//                HoaDon hoaDon = new HoaDon(maHD1);
//                LocalDateTime ngayGioXuatVe = rs.getTimestamp("ngayGioXuatVe").toLocalDateTime();
//                String maChoNgoi = rs.getString("maChoNgoi");
//                ChoNgoi choNgoi = DAOChoNgoi.getChoNgoiTheoMa(maChoNgoi);
//                String maChuyenTau = rs.getString("maChuyenTau");
//                ChuyenTau chuyenTau = DAOChuyenTau.getChuyenTauTheoMa(maChuyenTau);
//                String maKhachHang = rs.getString("maKhachHang");
//                KhachHang khachHang = DAOKhachHang.layKhachHangTheoMa(maKhachHang);
//                String trangThai = rs.getString("trangThai");
//                double thue = rs.getDouble("thue");
//                String maKhuyenMai = rs.getString("maKhuyenMai");
//                KhuyenMai khuyenMai = DAOKhuyenMai.getKhuyenMaiTheoMa(maKhuyenMai);
//                Ve ve = new Ve(maVe, hoaDon, loaiVe, ngayGioXuatVe, choNgoi, chuyenTau, khachHang, thue, khuyenMai, trangThai);
//                dsVe.add(ve);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dsVe;
//    }

    public static ArrayList<Ve> layDSVeGanNhat() {
        ArrayList<Ve> dsVe = new ArrayList<>();
        String sql = "SELECT v FROM Ve v ORDER BY v.ngayGioXuatVe DESC";
        try {
            dsVe = (ArrayList<Ve>) em.createQuery(sql, Ve.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsVe;
    }

    // Lấy danh sách vé đã bán theo chuyến
//    public static ArrayList<Ve> layDSVeDaBanTheoMaChuyen(String maChuyen) {
//        ArrayList<Ve> dsVe = new ArrayList<>();
//        String sql = "SELECT * FROM Ve WHERE maChuyenTau = ? AND trangThai = N'Đã bán'";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maChuyen);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                String maVe = rs.getString("maVe");
//                String maLoaiVe = rs.getString("maLoaiVe");
//                LoaiVe loaiVe = DAOLoaiVe.layLoaiVeTheoMa(maLoaiVe);
//                String maHD1 = rs.getString("maHoaDon");
//                HoaDon hoaDon = new HoaDon(maHD1);
//                LocalDateTime ngayGioXuatVe = rs.getTimestamp("ngayGioXuatVe").toLocalDateTime();
//                String maChoNgoi = rs.getString("maChoNgoi");
//                ChoNgoi choNgoi = DAOChoNgoi.getChoNgoiTheoMa(maChoNgoi);
//                String maChuyenTau = rs.getString("maChuyenTau");
//                ChuyenTau chuyenTau = DAOChuyenTau.getChuyenTauTheoMa(maChuyenTau);
//                String maKhachHang = rs.getString("maKhachHang");
//                KhachHang khachHang = DAOKhachHang.layKhachHangTheoMa(maKhachHang);
//                String trangThai = rs.getString("trangThai");
//                double thue = rs.getDouble("thue");
//                String maKhuyenMai = rs.getString("maKhuyenMai");
//                KhuyenMai khuyenMai = DAOKhuyenMai.getKhuyenMaiTheoMa(maKhuyenMai);
//                Ve ve = new Ve(maVe, hoaDon, loaiVe, ngayGioXuatVe, choNgoi, chuyenTau, khachHang, thue, khuyenMai, trangThai);
//                dsVe.add(ve);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dsVe;
//    }

    public static ArrayList<Ve> layDSVeDaBanTheoMaChuyen(String maChuyen) {
        ArrayList<Ve> dsVe = new ArrayList<>();
        String sql = "SELECT v FROM Ve v WHERE v.chuyenTau.maChuyen = :maChuyen AND v.trangThai = :trangThai";
        try {
            TypedQuery<Ve> query = em.createQuery(sql, Ve.class);
            query.setParameter("maChuyen", maChuyen);
            query.setParameter("trangThai", ETrangThaiVe.DA_BAN.name()); // Sử dụng enum thay vì chuỗi
            List<Ve> resultList = query.getResultList();
            dsVe = new ArrayList<>(resultList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsVe;
    }

//    public static ArrayList<Ve> layDSVeDaBanTheoMaChuyen(String maChuyen) {
//        ArrayList<Ve> dsVe = new ArrayList<>();
//        List<Ve> dsVe1 = new ArrayList<>();
//        String jpql = "SELECT v FROM Ve v WHERE v.chuyenTau.maChuyen = :maChuyen AND v.trangThai = :trangThai";
//        try {
//            TypedQuery<Ve> query = em.createQuery(jpql, Ve.class);
//            query.setParameter("maChuyen", maChuyen);
//            query.setParameter("trangThai", "Da ban"); // Giá trị trạng thái vé
//            dsVe1 = query.getResultList();
//            dsVe = new ArrayList<>(dsVe1);
//            System.out.println("------------DSVEDABAN--------------"+dsVe+"------------------------------");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dsVe;
//    }

//    public static String layDoanhThuTheoNgay(Date ngay) {
//        String sql = "SELECT SUM(lc.giaCho) AS totalRevenue " +
//                "FROM Ve v " +
//                "JOIN ChoNgoi cn ON v.maChoNgoi = cn.maCho " +
//                "JOIN LoaiCho lc ON cn.maloaiCho = lc.maLC " +
//                "WHERE CONVERT(date, v.ngayGioXuatVe) = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setDate(1, new java.sql.Date(ngay.getTime()));
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    double totalRevenue = rs.getDouble("totalRevenue");
//                    DecimalFormat df = new DecimalFormat("#");
//                    df.setMaximumFractionDigits(8);
//                    return df.format(totalRevenue);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "0";
//    }

    public static String layDoanhThuTheoNgay(Date ngay) {
        String sql = "SELECT SUM(lc.giaCho) AS totalRevenue " +
                "FROM Ve v " +
                "JOIN ChoNgoi cn ON v.choNgoi.maCho = cn.maCho " +
                "JOIN LoaiCho lc ON cn.loaiCho.maLC = lc.maLC " +
                "WHERE v.ngayGioXuatVe >= :ngay AND v.ngayGioXuatVe < :ngay";
        try {
            return em.createQuery(sql, String.class).setParameter("ngay", ngay).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
        }

//    public static String layDoanhThuTheoNgayHienTai() {
//        String sql = "SELECT SUM(lc.giaCho) AS totalRevenue " +
//                "FROM Ve v " +
//                "JOIN ChoNgoi cn ON v.maChoNgoi = cn.maCho " +
//                "JOIN LoaiCho lc ON cn.maloaiCho = lc.maLC " +
//                "WHERE CONVERT(date, v.ngayGioXuatVe) = CONVERT(date, GETDATE())";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            if (rs.next()) {
//                return rs.getString("totalRevenue");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "0";
//    }

    public static String layDoanhThuTheoNgayHienTai() {
        String sql = "SELECT SUM(lc.giaCho) AS totalRevenue " +
                "FROM Ve v " +
                "JOIN ChoNgoi cn ON v.choNgoi.maCho = cn.maCho " +
                "JOIN LoaiCho lc ON cn.loaiCho.maLC = lc.maLC " +
                "WHERE v.ngayGioXuatVe >= :ngay AND v.ngayGioXuatVe < :ngay";
        try {
            return em.createQuery(sql, String.class).setParameter("ngay", new Date()).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    // Get total revenue for the current week
//    public static String layDoanhThuTuanHienTai() {
//        String sql = "SELECT SUM(lc.giaCho) AS totalRevenue " +
//                "FROM Ve v " +
//                "JOIN ChoNgoi cn ON v.maChoNgoi = cn.maCho " +
//                "JOIN LoaiCho lc ON cn.maloaiCho = lc.maLC " +
//                "WHERE v.ngayGioXuatVe >= DATEADD(DAY, 1 - DATEPART(WEEKDAY, GETDATE()), CAST(GETDATE() AS DATE)) " +
//                "AND v.ngayGioXuatVe < DATEADD(DAY, 8 - DATEPART(WEEKDAY, GETDATE()), CAST(GETDATE() AS DATE))";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            if (rs.next()) {
//                return rs.getString("totalRevenue");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "0";
//    }

    public static String layDoanhThuTuanHienTai() {
        try {
            LocalDate now = LocalDate.now();
            LocalDate startOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDate endOfWeek = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

            TypedQuery<BigDecimal> query = em.createQuery(
                    "SELECT SUM(lc.giaCho) FROM Ve v " +
                            "JOIN v.choNgoi cn " +
                            "JOIN cn.loaiCho lc " +
                            "WHERE v.ngayGioXuatVe >= :startOfWeek " +
                            "AND v.ngayGioXuatVe < :endOfWeek", BigDecimal.class);

            query.setParameter("startOfWeek", startOfWeek);
            query.setParameter("endOfWeek", endOfWeek);

            BigDecimal totalRevenue = query.getSingleResult();
            return totalRevenue != null ? totalRevenue.toString() : "0";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    // Get total revenue for the current month
//    public static String layDoanhThuThangHienTai() {
//        String sql = "SELECT SUM(lc.giaCho) AS totalRevenue " +
//                "FROM Ve v " +
//                "JOIN ChoNgoi cn ON v.maChoNgoi = cn.maCho " +
//                "JOIN LoaiCho lc ON cn.maloaiCho = lc.maLC " +
//                "WHERE v.ngayGioXuatVe >= DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()), 0) " +
//                "AND v.ngayGioXuatVe < DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()) + 1, 0)";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            if (rs.next()) {
//                return rs.getString("totalRevenue");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "0";
//    }

    public static String layDoanhThuThangHienTai() {
        try {
            LocalDate now = LocalDate.now();
            LocalDate startOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
            LocalDate endOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());

            TypedQuery<BigDecimal> query = em.createQuery(
                    "SELECT SUM(lc.giaCho) FROM Ve v " +
                            "JOIN v.choNgoi cn " +
                            "JOIN cn.loaiCho lc " +
                            "WHERE v.ngayGioXuatVe >= :startOfMonth " +
                            "AND v.ngayGioXuatVe < :endOfMonth", BigDecimal.class);

            query.setParameter("startOfMonth", startOfMonth);
            query.setParameter("endOfMonth", endOfMonth);

            BigDecimal totalRevenue = query.getSingleResult();
            return totalRevenue != null ? totalRevenue.toString() : "0";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    // Get total revenue for the current quarter
//    public static String layDoanhThuQuyHienTai() {
//        String sql = "SELECT SUM(lc.giaCho) AS totalRevenue " +
//                "FROM Ve v " +
//                "JOIN ChoNgoi cn ON v.maChoNgoi = cn.maCho " +
//                "JOIN LoaiCho lc ON cn.maloaiCho = lc.maLC " +
//                "WHERE v.ngayGioXuatVe >= DATEADD(QUARTER, DATEDIFF(QUARTER, 0, GETDATE()), 0) " +
//                "AND v.ngayGioXuatVe < DATEADD(QUARTER, DATEDIFF(QUARTER, 0, GETDATE()) + 1, 0)";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            if (rs.next()) {
//                return rs.getString("totalRevenue");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "0";
//    }

    public static String layDoanhThuQuyHienTai() {
        try {
            LocalDate now = LocalDate.now();
            int quarter = (now.getMonthValue() - 1) / 3 + 1;
            LocalDate startOfQuarter = now.withMonth((quarter - 1) * 3 + 1).with(TemporalAdjusters.firstDayOfMonth());
            LocalDate endOfQuarter = startOfQuarter.plusMonths(3);

            TypedQuery<BigDecimal> query = em.createQuery(
                    "SELECT SUM(lc.giaCho) FROM Ve v " +
                            "JOIN v.choNgoi cn " +
                            "JOIN cn.loaiCho lc " +
                            "WHERE v.ngayGioXuatVe >= :startOfQuarter " +
                            "AND v.ngayGioXuatVe < :endOfQuarter", BigDecimal.class);

            query.setParameter("startOfQuarter", startOfQuarter);
            query.setParameter("endOfQuarter", endOfQuarter);

            BigDecimal totalRevenue = query.getSingleResult();
            return totalRevenue != null ? totalRevenue.toString() : "0";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    // Get total revenue for the current year
//    public static String layDoanhThuNamHienTai() {
//        String sql = "SELECT SUM(lc.giaCho) AS totalRevenue " +
//                "FROM Ve v " +
//                "JOIN ChoNgoi cn ON v.maChoNgoi = cn.maCho " +
//                "JOIN LoaiCho lc ON cn.maloaiCho = lc.maLC " +
//                "WHERE v.ngayGioXuatVe >= DATEADD(YEAR, DATEDIFF(YEAR, 0, GETDATE()), 0) " +
//                "AND v.ngayGioXuatVe < DATEADD(YEAR, DATEDIFF(YEAR, 0, GETDATE()) + 1, 0)";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            if (rs.next()) {
//                return rs.getString("totalRevenue");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "0";
//    }

    public static String layDoanhThuNamHienTai() {
        try {
            LocalDate now = LocalDate.now();
            LocalDate startOfYear = now.with(TemporalAdjusters.firstDayOfYear());
            LocalDate endOfYear = now.with(TemporalAdjusters.lastDayOfYear());

            TypedQuery<BigDecimal> query = em.createQuery(
                    "SELECT SUM(lc.giaCho) FROM Ve v " +
                            "JOIN v.choNgoi cn " +
                            "JOIN cn.loaiCho lc " +
                            "WHERE v.ngayGioXuatVe >= :startOfYear " +
                            "AND v.ngayGioXuatVe < :endOfYear", BigDecimal.class);

            query.setParameter("startOfYear", startOfYear);
            query.setParameter("endOfYear", endOfYear);

            BigDecimal totalRevenue = query.getSingleResult();
            return totalRevenue != null ? totalRevenue.toString() : "0";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }


//    public static String layDoanhThuNgayHomQua() {
//        String sql = "SELECT SUM(lc.giaCho) AS totalRevenue " +
//                "FROM Ve v " +
//                "JOIN ChoNgoi cn ON v.maChoNgoi = cn.maCho " +
//                "JOIN LoaiCho lc ON cn.maloaiCho = lc.maLC " +
//                "WHERE CONVERT(date, v.ngayGioXuatVe) = CONVERT(date, DATEADD(day, -1, GETDATE()))";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            if (rs.next()) {
//                return rs.getString("totalRevenue");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "0";
//    }

    public static String layDoanhThuNgayHomQua() {
        try {
            LocalDate yesterday = LocalDate.now().minusDays(1);

            TypedQuery<BigDecimal> query = em.createQuery(
                    "SELECT SUM(lc.giaCho) FROM Ve v " +
                            "JOIN v.choNgoi cn " +
                            "JOIN cn.loaiCho lc " +
                            "WHERE v.ngayGioXuatVe >= :yesterday " +
                            "AND v.ngayGioXuatVe < :yesterday", BigDecimal.class);

            query.setParameter("yesterday", yesterday);

            BigDecimal totalRevenue = query.getSingleResult();
            return totalRevenue != null ? totalRevenue.toString() : "0";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

//    public static String layDoanhThuTuanVuaRoi() {
//        String sql = "SELECT SUM(lc.giaCho) AS totalRevenue " +
//                "FROM Ve v " +
//                "JOIN ChoNgoi cn ON v.maChoNgoi = cn.maCho " +
//                "JOIN LoaiCho lc ON cn.maloaiCho = lc.maLC " +
//                "WHERE v.ngayGioXuatVe >= DATEADD(DAY, 1 - DATEPART(WEEKDAY, GETDATE()) - 7, CAST(GETDATE() AS DATE)) " +
//                "AND v.ngayGioXuatVe < DATEADD(DAY, 1 - DATEPART(WEEKDAY, GETDATE()), CAST(GETDATE() AS DATE))";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            if (rs.next()) {
//                return rs.getString("totalRevenue");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "0";
//    }

    public static String layDoanhThuTuanVuaRoi() {
        try {
            LocalDate now = LocalDate.now();
            LocalDate startOfLastWeek = now.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
            LocalDate endOfLastWeek = now.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));

            TypedQuery<BigDecimal> query = em.createQuery(
                    "SELECT SUM(lc.giaCho) FROM Ve v " +
                            "JOIN v.choNgoi cn " +
                            "JOIN cn.loaiCho lc " +
                            "WHERE v.ngayGioXuatVe >= :startOfLastWeek " +
                            "AND v.ngayGioXuatVe < :endOfLastWeek", BigDecimal.class);

            query.setParameter("startOfLastWeek", startOfLastWeek);
            query.setParameter("endOfLastWeek", endOfLastWeek);

            BigDecimal totalRevenue = query.getSingleResult();
            return totalRevenue != null ? totalRevenue.toString() : "0";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

//    public static String layDoanhThuThangVuaRoi() {
//        String sql = "SELECT SUM(lc.giaCho) AS totalRevenue " +
//                "FROM Ve v " +
//                "JOIN ChoNgoi cn ON v.maChoNgoi = cn.maCho " +
//                "JOIN LoaiCho lc ON cn.maloaiCho = lc.maLC " +
//                "WHERE v.ngayGioXuatVe >= DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()) - 1, 0) " +
//                "AND v.ngayGioXuatVe < DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()), 0)";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            if (rs.next()) {
//                return rs.getString("totalRevenue");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "0";
//    }

    public static String layDoanhThuThangVuaRoi() {
        try {
            LocalDate now = LocalDate.now();
            LocalDate startOfLastMonth = now.with(TemporalAdjusters.firstDayOfMonth()).minusMonths(1);
            LocalDate endOfLastMonth = now.with(TemporalAdjusters.firstDayOfMonth());

            TypedQuery<BigDecimal> query = em.createQuery(
                    "SELECT SUM(lc.giaCho) FROM Ve v " +
                            "JOIN v.choNgoi cn " +
                            "JOIN cn.loaiCho lc " +
                            "WHERE v.ngayGioXuatVe >= :startOfLastMonth " +
                            "AND v.ngayGioXuatVe < :endOfLastMonth", BigDecimal.class);

            query.setParameter("startOfLastMonth", startOfLastMonth);
            query.setParameter("endOfLastMonth", endOfLastMonth);

            BigDecimal totalRevenue = query.getSingleResult();
            return totalRevenue != null ? totalRevenue.toString() : "0";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

//    public static String layDoanhThuQuyVuaRoi() {
//        String sql = "SELECT SUM(lc.giaCho) AS totalRevenue " +
//                "FROM Ve v " +
//                "JOIN ChoNgoi cn ON v.maChoNgoi = cn.maCho " +
//                "JOIN LoaiCho lc ON cn.maloaiCho = lc.maLC " +
//                "WHERE v.ngayGioXuatVe >= DATEADD(QUARTER, DATEDIFF(QUARTER, 0, GETDATE()) - 1, 0) " +
//                "AND v.ngayGioXuatVe < DATEADD(QUARTER, DATEDIFF(QUARTER, 0, GETDATE()), 0)";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            if (rs.next()) {
//                return rs.getString("totalRevenue");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "0";
//    }

    public static String layDoanhThuQuyVuaRoi() {
        try {
            LocalDate now = LocalDate.now();
            int quarter = (now.getMonthValue() - 1) / 3 + 1;
            LocalDate startOfLastQuarter = now.withMonth((quarter - 2) * 3 + 1).with(TemporalAdjusters.firstDayOfMonth());
            LocalDate endOfLastQuarter = startOfLastQuarter.plusMonths(3);

            TypedQuery<BigDecimal> query = em.createQuery(
                    "SELECT SUM(lc.giaCho) FROM Ve v " +
                            "JOIN v.choNgoi cn " +
                            "JOIN cn.loaiCho lc " +
                            "WHERE v.ngayGioXuatVe >= :startOfLastQuarter " +
                            "AND v.ngayGioXuatVe < :endOfLastQuarter", BigDecimal.class);

            query.setParameter("startOfLastQuarter", startOfLastQuarter);
            query.setParameter("endOfLastQuarter", endOfLastQuarter);

            BigDecimal totalRevenue = query.getSingleResult();
            return totalRevenue != null ? totalRevenue.toString() : "0";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

//    public static String layDoanhThuNamVuaRoi() {
//        String sql = "SELECT SUM(lc.giaCho) AS totalRevenue " +
//                "FROM Ve v " +
//                "JOIN ChoNgoi cn ON v.maChoNgoi = cn.maCho " +
//                "JOIN LoaiCho lc ON cn.maloaiCho = lc.maLC " +
//                "WHERE v.ngayGioXuatVe >= DATEADD(YEAR, DATEDIFF(YEAR, 0, GETDATE()) - 1, 0) " +
//                "AND v.ngayGioXuatVe < DATEADD(YEAR, DATEDIFF(YEAR, 0, GETDATE()), 0)";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            if (rs.next()) {
//                return rs.getString("totalRevenue");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "0";
//    }

    public static String layDoanhThuNamVuaRoi() {
        try {
            LocalDate now = LocalDate.now();
            LocalDate startOfLastYear = now.with(TemporalAdjusters.firstDayOfYear()).minusYears(1);
            LocalDate endOfLastYear = now.with(TemporalAdjusters.lastDayOfYear()).minusYears(1);

            TypedQuery<BigDecimal> query = em.createQuery(
                    "SELECT SUM(lc.giaCho) FROM Ve v " +
                            "JOIN v.choNgoi cn " +
                            "JOIN cn.loaiCho lc " +
                            "WHERE v.ngayGioXuatVe >= :startOfLastYear " +
                            "AND v.ngayGioXuatVe < :endOfLastYear", BigDecimal.class);

            query.setParameter("startOfLastYear", startOfLastYear);
            query.setParameter("endOfLastYear", endOfLastYear);

            BigDecimal totalRevenue = query.getSingleResult();
            return totalRevenue != null ? totalRevenue.toString() : "0";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

//    public static double getBestSellingHourOfDay() {
//        String query = "SELECT TOP 1\n" +
//                "    DATEPART(HOUR, ngayGioXuatVe) AS gio,\n" +
//                "    COUNT(maVe) AS soVeBan,\n" +
//                "    SUM(tongTien) AS doanhThu\n" +
//                "FROM \n" +
//                "    View_VeTongTien\n" +
//                "WHERE \n" +
//                "    CONVERT(DATE, ngayGioXuatVe) = CONVERT(DATE, GETDATE())\n" +
//                "GROUP BY \n" +
//                "    DATEPART(HOUR, ngayGioXuatVe)\n" +
//                "ORDER BY \n" +
//                "    soVeBan Desc;";
//
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)) {
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                return rs.getDouble("gio");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return -1;
//    }

    public double getBestSellingHourOfDay() {
        try {
            String jpql = "SELECT EXTRACT(HOUR FROM v.ngayGioXuatVe) AS gio, COUNT(v.maVe) AS soVeBan " +
                    "FROM Ve v " +
                    "WHERE FUNCTION('DATE', v.ngayGioXuatVe) = FUNCTION('CURRENT_DATE') " +
                    "GROUP BY EXTRACT(HOUR FROM v.ngayGioXuatVe) " +
                    "ORDER BY soVeBan DESC";

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            List<Object[]> results = query.setMaxResults(1).getResultList();

            if (!results.isEmpty()) {
                return (double) results.get(0)[0];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

//    public static double getAverageBestSellingHourOfWeek() {
//        String query = "SELECT TOP 1 \n" +
//                "    HourlySales.hour,\n" +
//                "    SUM(HourlySales.total_sales) AS total_sales\n" +
//                "FROM (\n" +
//                "    SELECT \n" +
//                "        DATEPART(HOUR, ve.ngayGioXuatVe) AS hour,\n" +
//                "        COUNT(*) AS total_sales\n" +
//                "    FROM HoaDon AS h\n" +
//                "    JOIN Ve AS ve ON h.maHD = ve.maHoaDon\n" +
//                "    WHERE h.ngayGioLapHD >= DATEADD(WEEK, DATEDIFF(WEEK, 0, GETDATE()), 0)\n" +
//                "      AND h.ngayGioLapHD < DATEADD(WEEK, DATEDIFF(WEEK, 0, GETDATE()) + 1, 0)\n" +
//                "    GROUP BY DATEPART(HOUR, ve.ngayGioXuatVe)\n" +
//                ") AS HourlySales\n" +
//                "GROUP BY HourlySales.hour\n" +
//                "ORDER BY SUM(HourlySales.total_sales) DESC;";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)){
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                return rs.getDouble("hour");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return -1;
//    }

    public double getAverageBestSellingHourOfWeek() {
        try {
            String jpql = "SELECT EXTRACT(HOUR FROM v.ngayGioXuatVe) AS hour, COUNT(v.maVe) AS total_sales " +
                    "FROM Ve v " +
                    "JOIN v.hoaDon h " +
                    "WHERE h.ngayGioLapHD >= :startOfWeek " +
                    "AND h.ngayGioLapHD < :endOfWeek " +
                    "GROUP BY EXTRACT(HOUR FROM v.ngayGioXuatVe) " +
                    "ORDER BY total_sales DESC";

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);

            // Calculate the start and end of the week
            LocalDate now = LocalDate.now();
            LocalDate startOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDate endOfWeek = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

            query.setParameter("startOfWeek", startOfWeek);
            query.setParameter("endOfWeek", endOfWeek);

            List<Object[]> results = query.setMaxResults(1).getResultList();

            if (!results.isEmpty()) {
                return (double) results.get(0)[0];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

//    public static double getAverageBestSellingHourOfMonth() {
//        String query = "SELECT TOP 1 \n" +
//                "    HourlySales.hour, \n" +
//                "    SUM(HourlySales.total_sales) AS total_sales \n" +
//                "FROM (\n" +
//                "    SELECT \n" +
//                "        DATEPART(HOUR, ve.ngayGioXuatVe) AS hour, \n" +
//                "        COUNT(*) AS total_sales \n" +
//                "    FROM HoaDon AS h\n" +
//                "    JOIN Ve AS ve ON h.maHD = ve.maHoaDon\n" +
//                "    WHERE h.ngayGioLapHD >= DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()) - 3, 0)\n" +
//                "      AND h.ngayGioLapHD < DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()), 0)\n" +
//                "    GROUP BY DATEPART(HOUR, ve.ngayGioXuatVe)\n" +
//                ") AS HourlySales\n" +
//                "GROUP BY HourlySales.hour\n" +
//                "ORDER BY SUM(HourlySales.total_sales) DESC;";
//
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query);
//             ResultSet rs = stmt.executeQuery()) {
//            if (rs.next()) {
//                return rs.getDouble("hour");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return -1;
//    }

    public double getAverageBestSellingHourOfMonth() {
        try {
            String jpql = "SELECT EXTRACT(HOUR FROM v.ngayGioXuatVe) AS hour, COUNT(v.maVe) AS total_sales " +
                    "FROM Ve v " +
                    "JOIN v.hoaDon h " +
                    "WHERE h.ngayGioLapHD >= :startOfMonth " +
                    "AND h.ngayGioLapHD < :endOfMonth " +
                    "GROUP BY EXTRACT(HOUR FROM v.ngayGioXuatVe) " +
                    "ORDER BY total_sales DESC";

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);

            // Calculate the start and end of the month
            LocalDate now = LocalDate.now();
            LocalDate startOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
            LocalDate endOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());

            query.setParameter("startOfMonth", startOfMonth);
            query.setParameter("endOfMonth", endOfMonth);

            List<Object[]> results = query.setMaxResults(1).getResultList();

            if (!results.isEmpty()) {
                return (double) results.get(0)[0];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

//    public static double getAverageBestSellingHourOfQuarter() {
//        String query = "SELECT TOP 1 \n" +
//                "    HourlySales.hour,\n" +
//                "    SUM(HourlySales.total_sales) AS total_sales\n" +
//                "FROM (\n" +
//                "    SELECT \n" +
//                "        DATEPART(HOUR, ve.ngayGioXuatVe) AS hour,\n" +
//                "        COUNT(*) AS total_sales\n" +
//                "    FROM HoaDon AS h\n" +
//                "    JOIN Ve AS ve ON h.maHD = ve.maHoaDon\n" +
//                "    WHERE h.ngayGioLapHD >= DATEADD(QUARTER, DATEDIFF(QUARTER, 0, GETDATE()), 0)\n" +
//                "      AND h.ngayGioLapHD < DATEADD(QUARTER, DATEDIFF(QUARTER, 0, GETDATE()) + 1, 0)\n" +
//                "    GROUP BY DATEPART(HOUR, ve.ngayGioXuatVe)\n" +
//                ") AS HourlySales\n" +
//                "GROUP BY HourlySales.hour\n" +
//                "ORDER BY SUM(HourlySales.total_sales) DESC;";
//
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query);
//             ResultSet rs = stmt.executeQuery()) {
//            if (rs.next()) {
//                return rs.getDouble("hour");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return -1;
//    }

    public double getAverageBestSellingHourOfQuarter() {
        try {
            String jpql = "SELECT EXTRACT(HOUR FROM v.ngayGioXuatVe) AS hour, COUNT(v.maVe) AS total_sales " +
                    "FROM Ve v " +
                    "JOIN v.hoaDon h " +
                    "WHERE h.ngayGioLapHD >= :startOfQuarter " +
                    "AND h.ngayGioLapHD < :endOfQuarter " +
                    "GROUP BY EXTRACT(HOUR FROM v.ngayGioXuatVe) " +
                    "ORDER BY total_sales DESC";

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);

            // Calculate the start and end of the quarter
            LocalDate now = LocalDate.now();
            int quarter = (now.getMonthValue() - 1) / 3 + 1;
            LocalDate startOfQuarter = now.withMonth((quarter - 1) * 3 + 1).with(TemporalAdjusters.firstDayOfMonth());
            LocalDate endOfQuarter = startOfQuarter.plusMonths(3);

            query.setParameter("startOfQuarter", startOfQuarter);
            query.setParameter("endOfQuarter", endOfQuarter);

            List<Object[]> results = query.setMaxResults(1).getResultList();

            if (!results.isEmpty()) {
                return (double) results.get(0)[0];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

//    public static double getAverageBestSellingHourOfYear() {
//        String query = "SELECT TOP 1 \n" +
//                "    HourlySales.hour,\n" +
//                "    SUM(HourlySales.total_sales) AS total_sales\n" +
//                "FROM (\n" +
//                "    SELECT \n" +
//                "        DATEPART(HOUR, ve.ngayGioXuatVe) AS hour,\n" +
//                "        COUNT(*) AS total_sales\n" +
//                "    FROM HoaDon AS h\n" +
//                "    JOIN Ve AS ve ON h.maHD = ve.maHoaDon\n" +
//                "    WHERE h.ngayGioLapHD >= DATEADD(YEAR, DATEDIFF(YEAR, 0, GETDATE()), 0)\n" +
//                "      AND h.ngayGioLapHD < DATEADD(YEAR, DATEDIFF(YEAR, 0, GETDATE()) + 1, 0)\n" +
//                "    GROUP BY DATEPART(HOUR, ve.ngayGioXuatVe)\n" +
//                ") AS HourlySales\n" +
//                "GROUP BY HourlySales.hour\n" +
//                "ORDER BY SUM(HourlySales.total_sales) DESC;";
//
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query);
//             ResultSet rs = stmt.executeQuery()) {
//            if (rs.next()) {
//                return rs.getDouble("hour");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return -1;
//    }

    public double getAverageBestSellingHourOfYear() {
        try {
            String jpql = "SELECT EXTRACT(HOUR FROM v.ngayGioXuatVe) AS hour, COUNT(v.maVe) AS total_sales " +
                    "FROM Ve v " +
                    "JOIN v.hoaDon h " +
                    "WHERE h.ngayGioLapHD >= :startOfYear " +
                    "AND h.ngayGioLapHD < :endOfYear " +
                    "GROUP BY EXTRACT(HOUR FROM v.ngayGioXuatVe) " +
                    "ORDER BY total_sales DESC";

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);

            // Calculate the start and end of the year
            LocalDate now = LocalDate.now();
            LocalDate startOfYear = now.with(TemporalAdjusters.firstDayOfYear());
            LocalDate endOfYear = now.with(TemporalAdjusters.lastDayOfYear());

            query.setParameter("startOfYear", startOfYear);
            query.setParameter("endOfYear", endOfYear);

            List<Object[]> results = query.setMaxResults(1).getResultList();

            if (!results.isEmpty()) {
                return (double) results.get(0)[0];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

//    public static String getSlowestSalesTimeLastWeek() {
//        String query =
//                "WITH HourlySales AS (\n" +
//                        "    SELECT \n" +
//                        "        DATEPART(HOUR, v.ngayGioXuatVe) AS hour,\n" +
//                        "        COUNT(*) AS total_sales\n" +
//                        "    FROM HoaDon AS h\n" +
//                        "    JOIN Ve AS v ON h.maHD = v.maHoaDon\n" +
//                        "    WHERE h.ngayGioLapHD >= DATEADD(DAY, -7, CAST(GETDATE() AS DATE))\n" +
//                        "          AND h.ngayGioLapHD < DATEADD(DAY, -6, CAST(GETDATE() AS DATE))\n" +
//                        "    GROUP BY DATEPART(HOUR, v.ngayGioXuatVe)\n" +
//                        "),\nCompleteHours AS (\n    SELECT h.hour, COALESCE(s.total_sales, 0) AS total_sales\n    FROM (SELECT 7 AS hour UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 \n          UNION ALL SELECT 11 UNION ALL SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14 \n          UNION ALL SELECT 15 UNION ALL SELECT 16 UNION ALL SELECT 17) AS h\n    LEFT JOIN HourlySales s ON h.hour = s.hour\n),\nSlidingWindow AS (\n    SELECT \n        h1.hour AS start_hour, \n        h1.hour + 2 AS end_hour, \n        SUM(h2.total_sales) AS total_sales_in_range\n    FROM CompleteHours h1\n    JOIN CompleteHours h2 ON h2.hour BETWEEN h1.hour AND h1.hour + 2\n    GROUP BY h1.hour\n)\nSELECT TOP 1 \n    CAST(start_hour AS VARCHAR) + ':00 - ' + CAST(end_hour AS VARCHAR) + ':00' AS time_range,\n    total_sales_in_range\nFROM SlidingWindow\nORDER BY total_sales_in_range ASC;";
//
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query);
//             ResultSet rs = stmt.executeQuery()) {
//
//            if (rs.next()) {
//                return rs.getString("time_range"); // Trả về khoảng thời gian bán chậm nhất
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return "N/A"; // Trả về 'N/A' nếu không tìm thấy dữ liệu
//    }

    public static String getSlowestSalesTimeLastWeek() {
        try {
            LocalDate now = LocalDate.now();
            LocalDate startOfLastWeek = now.with(TemporalAdjusters.previous(DayOfWeek.MONDAY)).minusWeeks(1);
            LocalDate endOfLastWeek = now.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).minusWeeks(1);

            TypedQuery<Object[]> query = em.createQuery(
                    "SELECT EXTRACT(HOUR FROM v.ngayGioXuatVe) AS hour, COUNT(v.maVe) AS total_sales " +
                            "FROM Ve v " +
                            "JOIN v.hoaDon h " +
                            "WHERE h.ngayGioLapHD >= :startOfLastWeek " +
                            "AND h.ngayGioLapHD < :endOfLastWeek " +
                            "GROUP BY EXTRACT(HOUR FROM v.ngayGioXuatVe) " +
                            "ORDER BY total_sales ASC", Object[].class);

            query.setParameter("startOfLastWeek", startOfLastWeek);
            query.setParameter("endOfLastWeek", endOfLastWeek);

            List<Object[]> results = query.setMaxResults(1).getResultList();

            if (!results.isEmpty()) {
                return results.get(0)[0] + ":00 - " + ((int) results.get(0)[0] + 2) + ":00";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "N/A";
    }

    public static String getSlowestSalesTimeLastMonth() {
        String query =
                "WITH HourlySales AS (\n" +
                        "    SELECT \n" +
                        "        DATEPART(HOUR, v.ngayGioXuatVe) AS hour,\n" +
                        "        COUNT(*) AS total_sales\n" +
                        "    FROM HoaDon AS h\n" +
                        "    JOIN Ve AS v ON h.maHD = v.maHoaDon\n" +
                        "    WHERE h.ngayGioLapHD >= DATEADD(MONTH, -1, DATEFROMPARTS(YEAR(GETDATE()), MONTH(GETDATE()), 1))\n" +
                        "          AND h.ngayGioLapHD < DATEFROMPARTS(YEAR(GETDATE()), MONTH(GETDATE()), 1)\n" +
                        "    GROUP BY DATEPART(HOUR, v.ngayGioXuatVe)\n),\nCompleteHours AS (\n    SELECT h.hour, COALESCE(s.total_sales, 0) AS total_sales\n    FROM (SELECT 7 AS hour UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 \n          UNION ALL SELECT 11 UNION ALL SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14 \n          UNION ALL SELECT 15 UNION ALL SELECT 16 UNION ALL SELECT 17) AS h\n    LEFT JOIN HourlySales s ON h.hour = s.hour\n),\nSlidingWindow AS (\n    SELECT \n        h1.hour AS start_hour, \n        h1.hour + 2 AS end_hour, \n        SUM(h2.total_sales) AS total_sales_in_range\n    FROM CompleteHours h1\n    JOIN CompleteHours h2 ON h2.hour BETWEEN h1.hour AND h1.hour + 2\n    GROUP BY h1.hour\n)\nSELECT TOP 1 \n    CAST(start_hour AS VARCHAR) + ':00 - ' + CAST(end_hour AS VARCHAR) + ':00' AS time_range,\n    total_sales_in_range\nFROM SlidingWindow\nORDER BY total_sales_in_range ASC;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getString("time_range"); // Trả về khoảng thời gian bán chậm nhất
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "N/A";
    }
//    public static String getSlowestSalesTimeLastQuarter() {
//        String query =
//                "WITH HourlySales AS (\n" +
//                        "    SELECT \n" +
//                        "        DATEPART(HOUR, v.ngayGioXuatVe) AS hour,\n" +
//                        "        COUNT(*) AS total_sales\n" +
//                        "    FROM HoaDon AS h\n" +
//                        "    JOIN Ve AS v ON h.maHD = v.maHoaDon\n" +
//                        "    WHERE h.ngayGioLapHD >= DATEADD(QUARTER, -1, DATEFROMPARTS(YEAR(GETDATE()), MONTH(GETDATE()), 1))\n" +
//                        "          AND h.ngayGioLapHD < DATEFROMPARTS(YEAR(GETDATE()), MONTH(GETDATE()), 1)\n" +
//                        "    GROUP BY DATEPART(HOUR, v.ngayGioXuatVe)\n),\nCompleteHours AS (\n    SELECT h.hour, COALESCE(s.total_sales, 0) AS total_sales\n    FROM (SELECT 7 AS hour UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 \n          UNION ALL SELECT 11 UNION ALL SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14 \n          UNION ALL SELECT 15 UNION ALL SELECT 16 UNION ALL SELECT 17) AS h\n    LEFT JOIN HourlySales s ON h.hour = s.hour\n),\nSlidingWindow AS (\n    SELECT \n        h1.hour AS start_hour, \n        h1.hour + 2 AS end_hour, \n        SUM(h2.total_sales) AS total_sales_in_range\n    FROM CompleteHours h1\n    JOIN CompleteHours h2 ON h2.hour BETWEEN h1.hour AND h1.hour + 2\n    GROUP BY h1.hour\n)\nSELECT TOP 1 \n    CAST(start_hour AS VARCHAR) + ':00 - ' + CAST(end_hour AS VARCHAR) + ':00' AS time_range,\n    total_sales_in_range\nFROM SlidingWindow\nORDER BY total_sales_in_range ASC;";
//
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query);
//             ResultSet rs = stmt.executeQuery()) {
//
//            if (rs.next()) {
//                return rs.getString("time_range"); // Trả về khoảng thời gian bán chậm nhất
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return "N/A";
//    }

    public static String getSlowestSalesTimeLastQuarter() {
        String query =
                "WITH HourlySales AS (\n" +
                        "    SELECT \n" +
                        "        DATEPART(HOUR, v.ngayGioXuatVe) AS hour,\n" +
                        "        COUNT(*) AS total_sales\n" +
                        "    FROM HoaDon AS h\n" +
                        "    JOIN Ve AS v ON h.maHD = v.maHoaDon\n" +
                        "    WHERE h.ngayGioLapHD >= DATEADD(QUARTER, -1, DATEFROMPARTS(YEAR(GETDATE()), MONTH(GETDATE()), 1))\n" +
                        "          AND h.ngayGioLapHD < DATEFROMPARTS(YEAR(GETDATE()), MONTH(GETDATE()), 1)\n" +
                        "    GROUP BY DATEPART(HOUR, v.ngayGioXuatVe)\n),\nCompleteHours AS (\n    SELECT h.hour, COALESCE(s.total_sales, 0) AS total_sales\n    FROM (SELECT 7 AS hour UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 \n          UNION ALL SELECT 11 UNION ALL SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14 \n          UNION ALL SELECT 15 UNION ALL SELECT 16 UNION ALL SELECT 17) AS h\n    LEFT JOIN HourlySales s ON h.hour = s.hour\n),\nSlidingWindow AS (\n    SELECT \n        h1.hour AS start_hour, \n        h1.hour + 2 AS end_hour, \n        SUM(h2.total_sales) AS total_sales_in_range\n    FROM CompleteHours h1\n    JOIN CompleteHours h2 ON h2.hour BETWEEN h1.hour AND h1.hour + 2\n    GROUP BY h1.hour\n)\nSELECT TOP 1 \n    CAST(start_hour AS VARCHAR) + ':00 - ' + CAST(end_hour AS VARCHAR) + ':00' AS time_range,\n    total_sales_in_range\nFROM SlidingWindow\nORDER BY total_sales_in_range ASC;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getString("time_range"); // Trả về khoảng thời gian bán
            } else {
                return "N/A";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "N/A";
    }

//    public static String getSlowestSalesTimeLastYear() {
//        String query =
//                "WITH HourlySales AS (\n" +
//                        "    SELECT \n" +
//                        "        DATEPART(HOUR, v.ngayGioXuatVe) AS hour,\n" +
//                        "        COUNT(*) AS total_sales\n" +
//                        "    FROM HoaDon AS h\n" +
//                        "    JOIN Ve AS v ON h.maHD = v.maHoaDon\n" +
//                        "    WHERE h.ngayGioLapHD >= DATEFROMPARTS(YEAR(GETDATE()) - 1, 1, 1)\n" +
//                        "          AND h.ngayGioLapHD < DATEFROMPARTS(YEAR(GETDATE()), 1, 1)\n" +
//                        "    GROUP BY DATEPART(HOUR, v.ngayGioXuatVe)\n),\nCompleteHours AS (\n    SELECT h.hour, COALESCE(s.total_sales, 0) AS total_sales\n    FROM (SELECT 7 AS hour UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 \n          UNION ALL SELECT 11 UNION ALL SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14 \n          UNION ALL SELECT 15 UNION ALL SELECT 16 UNION ALL SELECT 17) AS h\n    LEFT JOIN HourlySales s ON h.hour = s.hour\n),\nSlidingWindow AS (\n    SELECT \n        h1.hour AS start_hour, \n        h1.hour + 2 AS end_hour, \n        SUM(h2.total_sales) AS total_sales_in_range\n    FROM CompleteHours h1\n    JOIN CompleteHours h2 ON h2.hour BETWEEN h1.hour AND h1.hour + 2\n    GROUP BY h1.hour\n)\nSELECT TOP 1 \n    CAST(start_hour AS VARCHAR) + ':00 - ' + CAST(end_hour AS VARCHAR) + ':00' AS time_range,\n    total_sales_in_range\nFROM SlidingWindow\nORDER BY total_sales_in_range ASC;";
//
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query);
//             ResultSet rs = stmt.executeQuery()) {
//
//            if (rs.next()) {
//                return rs.getString("time_range"); // Trả về khoảng thời gian bán chậm nhất
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "N/A";
//    }

    public static String getSlowestSalesTimeLastYear() {
        String query =
                "WITH HourlySales AS (\n" +
                        "    SELECT \n" +
                        "        DATEPART(HOUR, v.ngayGioXuatVe) AS hour,\n" +
                        "        COUNT(*) AS total_sales\n" +
                        "    FROM HoaDon AS h\n" +
                        "    JOIN Ve AS v ON h.maHD = v.maHoaDon\n" +
                        "    WHERE h.ngayGioLapHD >= DATEFROMPARTS(YEAR(GETDATE()) - 1, 1, 1)\n" +
                        "          AND h.ngayGioLapHD < DATEFROMPARTS(YEAR(GETDATE()), 1, 1)\n" +
                        "    GROUP BY DATEPART(HOUR, v.ngayGioXuatVe)\n),\nCompleteHours AS (\n    SELECT h.hour, COALESCE(s.total_sales, 0) AS total_sales\n    FROM (SELECT 7 AS hour UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 \n          UNION ALL SELECT 11 UNION ALL SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14 \n          UNION ALL SELECT 15 UNION ALL SELECT 16 UNION ALL SELECT 17) AS h\n    LEFT JOIN HourlySales s ON h.hour = s.hour\n),\nSlidingWindow AS (\n    SELECT \n        h1.hour AS start_hour, \n        h1.hour + 2 AS end_hour, \n        SUM(h2.total_sales) AS total_sales_in_range\n    FROM CompleteHours h1\n    JOIN CompleteHours h2 ON h2.hour BETWEEN h1.hour AND h1.hour + 2\n    GROUP BY h1.hour\n)\nSELECT TOP 1 \n    CAST(start_hour AS VARCHAR) + ':00 - ' + CAST(end_hour AS VARCHAR) + ':00' AS time_range,\n    total_sales_in_range\nFROM SlidingWindow\nORDER BY total_sales_in_range ASC;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getString("time_range"); // Trả về khoảng thời gian bán
            } else {
                return "N/A";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "N/A";
    }

//    public static ArrayList<Ve> getDanhSachVeDaTra() {
//        ArrayList<Ve> dsVe = new ArrayList<>();
//        String sql = "SELECT * FROM Ve WHERE trangThai = N'Đã trả'";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                String maVe = rs.getString("maVe");
//                String maLoaiVe = rs.getString("maLoaiVe");
//                LoaiVe loaiVe = DAOLoaiVe.layLoaiVeTheoMa(maLoaiVe);
//                String maHD1 = rs.getString("maHoaDon");
//                HoaDon hoaDon = new HoaDon(maHD1);
//                LocalDateTime ngayGioXuatVe = rs.getTimestamp("ngayGioXuatVe").toLocalDateTime();
//                String maChoNgoi = rs.getString("maChoNgoi");
//                ChoNgoi choNgoi = DAOChoNgoi.getChoNgoiTheoMa(maChoNgoi);
//                String maChuyenTau = rs.getString("maChuyenTau");
//                ChuyenTau chuyenTau = DAOChuyenTau.getChuyenTauTheoMa(maChuyenTau);
//                String maKhachHang = rs.getString("maKhachHang");
//                KhachHang khachHang = DAOKhachHang.layKhachHangTheoMa(maKhachHang);
//                String trangThai = rs.getString("trangThai");
//                double thue = rs.getDouble("thue");
//                String maKhuyenMai = rs.getString("maKhuyenMai");
//                KhuyenMai khuyenMai = DAOKhuyenMai.getKhuyenMaiTheoMa(maKhuyenMai);
//                Ve ve = new Ve(maVe, hoaDon, loaiVe, ngayGioXuatVe, choNgoi, chuyenTau, khachHang, thue, khuyenMai, trangThai);
//                dsVe.add(ve);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return dsVe;
//    }

     public static ArrayList<Ve> getDanhSachVeDaTra() {
        try {
            TypedQuery<Ve> query = em.createQuery(
                    "SELECT v FROM Ve v WHERE v.trangThai = :trangThai", Ve.class);
            query.setParameter("trangThai", "Đã trả");
            return new ArrayList<>(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
