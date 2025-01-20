package control;

import connectDB.ConnectDB;
import connectDB.connectDB_1;
import entity.KhachHang;
import entity.Ve;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import service.KhachHangService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DAOKhachHang {
    /*maKH     VARCHAR(20) PRIMARY KEY,
    tenKH    NVARCHAR(50) NOT NULL,
    CCCD     CHAR(12) NULL UNIQUE,
    sdt      CHAR(10) CHECK (sdt LIKE '0%' AND LEN(sdt) = 10) NOT NULL,
    email    VARCHAR(100) NULL CHECK (email LIKE '%@%.%' OR email IS NULL),
    ngaySinh DATE CHECK (ngaySinh < GETDATE()),
    doiTuong NVARCHAR(30) NULL*/
    // Hàm thêm khách hàng chi can INSERT INTO KhachHang(tenKH, CCCD, sdt, email, ngaySinh, doiTuong)
//    public static boolean themKhachHang(KhachHang kh) {
//        String sql = "INSERT INTO KhachHang(tenKH, CCCD, sdt, email, ngaySinh, doiTuong) VALUES(?,?,?,?,?,?)";
//        try {
//            PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
//            // Set giá trị cho các cột
//            stmt.setString(1, kh.getTenKH());
//
//            // Kiểm tra và đặt giá trị cho CCCD
//            if (kh.getCCCD() == null || kh.getCCCD().trim().isEmpty()) {
//                stmt.setNull(2, java.sql.Types.VARCHAR); // Đặt NULL nếu chuỗi rỗng hoặc null
//            } else {
//                stmt.setString(2, kh.getCCCD());
//            }
//
//            stmt.setString(3, kh.getSdt());
//            stmt.setString(4, kh.getEmail());
//
//            // Kiểm tra và đặt giá trị cho ngày sinh
//            if (kh.getNgaySinh() == null) {
//                stmt.setNull(5, java.sql.Types.DATE); // Đặt NULL nếu ngày sinh không tồn tại
//            } else {
//                stmt.setDate(5, Date.valueOf(kh.getNgaySinh()));
//            }
//
//            stmt.setString(6, kh.getDoiTuong());
//
//            // Thực thi câu lệnh
//            return stmt.executeUpdate() > 0;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }

    private static EntityManager em;

    public DAOKhachHang(EntityManager em) {
        this.em = em;
    }

    public static boolean themKhachHang(KhachHang kh) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            KhachHangService khachHangService = new KhachHangService(em);
            khachHangService.persistKhachHang(kh);

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

    public static void main(String[] args) throws SQLException {
        // Initialize the database connection
        connectDB_1.connect();
        EntityManager em = connectDB_1.getEntityManager();

        DAOKhachHang daoKhachHang = new DAOKhachHang(em);

        // Retrieve tickets by invoice ID
        KhachHang kh = new KhachHang("KH01", "Nguyen Van A", "123456789012", "0123456789", "email@gmail.com", LocalDate.of(2000, 1, 1), "Sinh vien");
        System.out.println(daoKhachHang.themKhachHang(kh));

        // Close the EntityManager and database connection
        em.close();
        connectDB_1.close();
    }

    // Hàm sửa khách hàng tra ve doi tuong khach hang
//    public static KhachHang suaKhachHang(KhachHang kh) {
//        String sql = "UPDATE KhachHang SET tenKH = ?, CCCD = ?, sdt = ?, email = ?, ngaySinh = ?, doiTuong = ? WHERE maKH = ?";
//            try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//                stmt.setString(1, kh.getTenKH());
//                //stmt.setString(2, kh.getCCCD());
//                // neu cccd rong hoac null thi set null
//                if(kh == null || kh.getCCCD().trim().isEmpty() ){
//                    stmt.setNull(2, java.sql.Types.VARCHAR);
//                }
//                else {
//                    stmt.setString(2, kh.getCCCD());
//                }
//                stmt.setString(3, kh.getSdt());
//                stmt.setString(4, kh.getEmail());
//                stmt.setDate(5, Date.valueOf(kh.getNgaySinh()));
//                stmt.setString(6, kh.getDoiTuong());
//                stmt.setString(7, kh.getMaKH());
//                stmt.executeUpdate();
//                return kh;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
    public static KhachHang suaKhachHang(KhachHang kh) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            KhachHang existingKhachHang = em.find(KhachHang.class, kh.getMaKH());
            if (existingKhachHang != null) {
                existingKhachHang.setTenKH(kh.getTenKH());
                if (kh.getCCCD() == null || kh.getCCCD().trim().isEmpty()) {
                    existingKhachHang.setCCCD(null);
                } else {
                    existingKhachHang.setCCCD(kh.getCCCD());
                }
                existingKhachHang.setSdt(kh.getSdt());
                existingKhachHang.setEmail(kh.getEmail());
                existingKhachHang.setNgaySinh(kh.getNgaySinh());
                existingKhachHang.setDoiTuong(kh.getDoiTuong());
                em.merge(existingKhachHang);
            }
            transaction.commit();
            return existingKhachHang;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    // Hàm tìm kiếm khách hàng theo CCCD va SĐT
//    public static KhachHang timKhachHang(String CCCD, String sdt) {
//        String sql = "SELECT * FROM KhachHang WHERE CCCD = ? AND sdt = ?";
//        KhachHang kh = null;
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//
//            stmt.setString(1, CCCD);
//            stmt.setString(2, sdt);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    String maKH = rs.getString(1);
//                    String tenKH = rs.getString(2);
//                    String CCCD1 = rs.getString(3);
//                    String sdt1 = rs.getString(4);
//                    String email = rs.getString(5);
//                    Date ngaySinh = rs.getDate(6);
//                    String doiTuong = rs.getString(7);
//                    kh = new KhachHang(maKH, tenKH, CCCD1, sdt1, email, ngaySinh.toLocalDate(), doiTuong);
//                }
//            }
//
//            return kh;
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//        return null;
//    }

    public static KhachHang timKhachHang(String CCCD, String sdt) {
        try {
            TypedQuery<KhachHang> query = em.createQuery(
                    "SELECT kh FROM KhachHang kh WHERE kh.CCCD = :CCCD AND kh.sdt = :sdt", KhachHang.class);
            query.setParameter("CCCD", CCCD);
            query.setParameter("sdt", sdt);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Hàm lấy danh sách khách hàng
//    public static ArrayList<KhachHang> layDanhSachKhachHang() {
//        ArrayList<KhachHang> list = new ArrayList<>();
//        String sql = "SELECT * FROM KhachHang";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    String maKH = rs.getString(1);
//                    String tenKH = rs.getString(2);
//                    String CCCD = rs.getString(3);
//                    String sdt = rs.getString(4);
//                    String email = rs.getString(5);
//                    Date ngaySinh = rs.getDate(6);
//                    LocalDate localNgaySinh;
//                    if (ngaySinh == null) {
//                        localNgaySinh = null;
//                    } else {
//                        localNgaySinh = ngaySinh.toLocalDate();
//                    }
//                    String doiTuong = rs.getString(7);
//                    KhachHang kh = new KhachHang(maKH, tenKH, CCCD, sdt, email, localNgaySinh, doiTuong);
//                    list.add(kh);
//                }
//            }
//
//            return list;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static ArrayList<KhachHang> layDanhSachKhachHang() {
        try {
            TypedQuery<KhachHang> query = em.createQuery("SELECT kh FROM KhachHang kh", KhachHang.class);
            List<KhachHang> resultList = query.getResultList();
            return new ArrayList<>(resultList); // Chuyển đổi List thành ArrayList
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Hàm lay khách hàng theo CCCD
//    public static KhachHang layKhachHangTheoCCCD(String CCCD) {
//        String sql = "SELECT * FROM KhachHang WHERE CCCD = ?";
//        KhachHang kh = null;
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, CCCD);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    String maKH = rs.getString(1);
//                    String tenKH = rs.getString(2);
//                    String CCCD1 = rs.getString(3);
//                    String sdt = rs.getString(4);
//                    String email = rs.getString(5);
//                    Date ngaySinh = rs.getDate(6);
//                    String doiTuong = rs.getString(7);
//                    if (ngaySinh == null) {
//                        kh = new KhachHang(maKH, tenKH, CCCD, sdt, email,  null, doiTuong);
//                    }
//                    else {
//                        kh = new KhachHang(maKH, tenKH, CCCD, sdt, email,  ngaySinh.toLocalDate(), doiTuong);
//                    }
//
//                }
//            }
//
//            return kh;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static KhachHang layKhachHangTheoCCCD(String CCCD) {
        try {
            TypedQuery<KhachHang> query = em.createQuery(
                    "SELECT kh FROM KhachHang kh WHERE kh.CCCD = :CCCD", KhachHang.class);
            query.setParameter("CCCD", CCCD);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Hàm lay khách hàng theo SĐT
//    public static KhachHang layKhachHangTheoSdt(String sdt) {
//        String sql = "SELECT * FROM KhachHang WHERE sdt = ?";
//        KhachHang kh = null;
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, sdt);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    String maKH = rs.getString(1);
//                    String tenKH = rs.getString(2);
//                    String CCCD = rs.getString(3);
//                    String sdt1 = rs.getString(4);
//                    String email = rs.getString(5);
//                    Date ngaySinh = rs.getDate(6);
//                    String doiTuong = rs.getString(7);
//                    if (ngaySinh == null) {
//                        kh = new KhachHang(maKH, tenKH, CCCD, sdt1, email,  null, doiTuong);
//                    }
//                    else {
//                        kh = new KhachHang(maKH, tenKH, CCCD, sdt1, email,  ngaySinh.toLocalDate(), doiTuong);
//                    }
//
//                }
//
//            }
//
//            return kh;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static KhachHang layKhachHangTheoSdt(String sdt) {
        try {
            TypedQuery<KhachHang> query = em.createQuery(
                    "SELECT kh FROM KhachHang kh WHERE kh.sdt = :sdt", KhachHang.class);
            query.setParameter("sdt", sdt);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Hàm lay khách hàng theo mã
//    public static KhachHang layKhachHangTheoMa(String maKH) {
//        String sql = "SELECT * FROM KhachHang WHERE maKH = ?";
//        KhachHang kh = null;
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maKH);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    String maKH1 = rs.getString(1);
//                    String tenKH = rs.getString(2);
//                    String CCCD = rs.getString(3);
//                    String sdt = rs.getString(4);
//                    String email = rs.getString(5);
//                    Date ngaySinh = rs.getDate(6);
//                    String doiTuong = rs.getString(7);
//                    // Kiểm tra null trước khi chuyển đổi
//                    LocalDate ngaySinhLocal = (ngaySinh != null) ? ngaySinh.toLocalDate() : null;
//
//                    kh = new KhachHang(maKH1, tenKH, CCCD, sdt, email, ngaySinhLocal, doiTuong);
//                }
//            }
//
//            return kh;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static KhachHang layKhachHangTheoMa(String maKH) {
        try {
            TypedQuery<KhachHang> query = em.createQuery(
                    "SELECT kh FROM KhachHang kh WHERE kh.maKH = :maKH", KhachHang.class);
            query.setParameter("maKH", maKH);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Tìm khách hàng theo email
//    public static KhachHang timKhachHangTheoEmail(String email) {
//        String sql = "SELECT * FROM KhachHang WHERE email = ?";
//        KhachHang kh = null;
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, email);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    String maKH = rs.getString(1);
//                    String tenKH = rs.getString(2);
//                    String CCCD = rs.getString(3);
//                    String sdt = rs.getString(4);
//                    String email1 = rs.getString(5);
//                    Date ngaySinh = rs.getDate(6);
//                    String doiTuong = rs.getString(7);
//                    kh = new KhachHang(maKH, tenKH, CCCD, sdt, email1, ngaySinh.toLocalDate(), doiTuong);
//                }
//            }
//
//            return kh;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    public static KhachHang timKhachHangTheoEmail(String email) {
        try {
            TypedQuery<KhachHang> query = em.createQuery(
                    "SELECT kh FROM KhachHang kh WHERE kh.email = :email", KhachHang.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //lay khach hang theo doi tuong
//    public static ArrayList<KhachHang> layKhachHangTheoDoiTuong(String doiTuong) {
//        ArrayList<KhachHang> list = new ArrayList<>();
//        String sql = "SELECT * FROM KhachHang WHERE doiTuong = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, doiTuong);
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    String maKH = rs.getString(1);
//                    String tenKH = rs.getString(2);
//                    String CCCD = rs.getString(3);
//                    String sdt = rs.getString(4);
//                    String email = rs.getString(5);
//                    Date ngaySinh = rs.getDate(6);
//                    LocalDate localNgaySinh;
//                    if (ngaySinh == null) {
//                        localNgaySinh = null;
//                    } else {
//                        localNgaySinh = ngaySinh.toLocalDate();
//                    }
//                    String doiTuong1 = rs.getString(7);
//                    KhachHang kh = new KhachHang(maKH, tenKH, CCCD, sdt, email, localNgaySinh, doiTuong1);
//                    list.add(kh);
//                }
//            }
//
//            return list;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    public static ArrayList<KhachHang> layKhachHangTheoDoiTuong(String doiTuong) {
        try {
            TypedQuery<KhachHang> query = em.createQuery(
                    "SELECT kh FROM KhachHang kh WHERE kh.doiTuong = :doiTuong", KhachHang.class);
            query.setParameter("doiTuong", doiTuong);
            List<KhachHang> resultList = query.getResultList();
            return new ArrayList<>(resultList); // Chuyển đổi List thành ArrayList
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
