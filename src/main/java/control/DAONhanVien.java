package control;

import connectDB.ConnectDB;
import connectDB.connectDB_1;
import entity.CaLam;
import entity.ChucVu;
import entity.NhanVien;
import entity.TaiKhoan;
import enums.ETrangThaiNhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import service.NhanVienService;
import jakarta.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DAONhanVien {
    /*CREATE TABLE NhanVien
(
    maNV       VARCHAR(11) PRIMARY KEY NOT NULL,
    tenNV      NVARCHAR(100) NOT NULL,
    gioiTinh   NVARCHAR(3) NOT NULL,
    ngaySinh   DATE                    NOT NULL,
    ngayVaoLam DATE                    NOT NULL,
    CCCD       VARCHAR(12) UNIQUE      NOT NULL,
    sdt        VARCHAR(12)             NOT NULL,
    email      VARCHAR(255)            NOT NULL,
    diaChi     NVARCHAR(255) NOT NULL,
    trangThai  NVARCHAR(20) NOT NULL,
    macaLam    VARCHAR(3)              NOT NULL,
    maTaiKhoan VARCHAR(11)             NOT NULL,
    maChucVu   VARCHAR(2)              NOT NULL,*/
    // ham them nhan vien khong can ma nhan vien


    //-- NhanVien
    //INSERT INTO NhanVien (tenNV, gioiTinh, ngaySinh, ngayVaoLam, CCCD, sdt, email, diaChi, trangThai, macaLam, maTaiKhoan, maChucVu) VALUES
//    public static boolean themNhanVien(NhanVien nv) {
////        String sql = "INSERT INTO NhanVien (tenNV, gioiTinh, ngaySinh, ngayVaoLam, CCCD, sdt, email, diaChi, trangThai, macaLam, maTaiKhoan, maChucVu) VALUES()"
//        String sql = "INSERT INTO NhanVien (tenNV, gioiTinh, ngaySinh, ngayVaoLam, CCCD, sdt, email, diaChi, trangThai, macaLam, maTaiKhoan, maChucVu) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, nv.getTenNV());
//            stmt.setString(2, nv.getGioiTinh());
//            stmt.setDate(3, java.sql.Date.valueOf(nv.getNgaySinh()));
//            stmt.setString(4, nv.getNgayVaoLam().toString());
//            stmt.setString(5, nv.getCCCD());
//            stmt.setString(6, nv.getSdt());
//            stmt.setString(7, nv.getEmail());
//            stmt.setString(8, nv.getDiaChi());
//            stmt.setString(9, nv.getTrangThai());
//            stmt.setString(10, nv.getCaLam().getMaCL());
//            stmt.setString(11, nv.getTaiKhoan().getMaTK());
//            stmt.setString(12, nv.getChucVu().getMaCV());
//            return stmt.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
    private static EntityManager em = connectDB_1.getEntityManager();

    public static boolean themNhanVien(NhanVien nv) {
        try {
            NhanVienService nhanVienService = new NhanVienService(em);
            nhanVienService.persistNhanVien(nv);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
//get NV
    public static NhanVien getNhanVien(String maNV) {
        String jpql = "SELECT nv FROM NhanVien nv WHERE nv.maNV = :maNV";
        try {
            TypedQuery<NhanVien> query = em.createQuery(jpql, NhanVien.class);
            query.setParameter("maNV", maNV);
            NhanVien nv = query.getSingleResult();
            return nv;
        } catch (NoResultException e) {
            System.out.println("Không tìm thấy nhân viên với mã " + maNV);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;  // Trả về null nếu không tìm thấy hoặc có lỗi
    }
//ds NV
public static ArrayList<NhanVien> layDanhSachNhanVien() {
    ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
    String jpql = "SELECT nv FROM NhanVien nv";
    try {
        TypedQuery<NhanVien> query = em.createQuery(jpql, NhanVien.class);
        List<NhanVien> resultList = query.getResultList();
        dsNhanVien.addAll(resultList);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return dsNhanVien;
}
//get NV theo ten
public static NhanVien getNhanVienTheoTen(String tenNV) {
    String jpql = "SELECT nv FROM NhanVien nv WHERE nv.tenNV LIKE :tenNV";
    try {
        TypedQuery<NhanVien> query = em.createQuery(jpql, NhanVien.class);
        query.setParameter("tenNV", "%" + tenNV + "%");
        NhanVien nv = query.getSingleResult();
        return nv;
    } catch (NoResultException e) {
        System.out.println("Không tìm thấy nhân viên với tên: " + tenNV);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
//get NV theo sdt
public static NhanVien getNhanVienTheoSDT(String sdt) {
    String jpql = "SELECT nv FROM NhanVien nv WHERE nv.sdt = :sdt";
    try {
        TypedQuery<NhanVien> query = em.createQuery(jpql, NhanVien.class);
        query.setParameter("sdt", sdt);
        NhanVien nv = query.getSingleResult();
        return nv;

    } catch (NoResultException e) {
        System.out.println("Không tìm thấy nhân viên với số điện thoại: " + sdt);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
//get NV theo CCCD
public static NhanVien getNhanVienTheoCCCD(String CCCD) {
    String jpql = "SELECT nv FROM NhanVien nv WHERE nv.CCCD = :CCCD";
    try {
        TypedQuery<NhanVien> query = em.createQuery(jpql, NhanVien.class);
        query.setParameter("CCCD", CCCD);
        NhanVien nv = query.getSingleResult();
        return nv;
    } catch (NoResultException e) {
        System.out.println("Không tìm thấy nhân viên với CCCD: " + CCCD);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
//get NV theo chuc vu
public static NhanVien getNhanVienTheoChucVu(String maChucVu) {
    String jpql = "SELECT nv FROM NhanVien nv WHERE nv.chucVu.maCV = :maChucVu";
    try {
        TypedQuery<NhanVien> query = em.createQuery(jpql, NhanVien.class);
        query.setParameter("maChucVu", maChucVu);
        NhanVien nv = query.getSingleResult();
    } catch (NoResultException e) {
        System.out.println("Không tìm thấy nhân viên với mã chức vụ: " + maChucVu);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
//get NV theo trang thai
public static ArrayList<NhanVien> getNhanVienTheoTrangThai(String trangThai) {
    ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
    String jpql = "SELECT nv FROM NhanVien nv WHERE nv.trangThai = :trangThai";
    try {
        TypedQuery<NhanVien> query = em.createQuery(jpql, NhanVien.class);
        query.setParameter("trangThai", trangThai);
        dsNhanVien = new ArrayList<>(query.getResultList());
    } catch (Exception e) {
        e.printStackTrace();
    }
    return dsNhanVien;
}
//sua NV
public static NhanVien suaNhanVien(NhanVien nv) {
    String jpql = "UPDATE NhanVien nv SET nv.tenNV = :tenNV, nv.gioiTinh = :gioiTinh, nv.ngaySinh = :ngaySinh, nv.ngayVaoLam = :ngayVaoLam, nv.CCCD = :CCCD, nv.sdt = :sdt, nv.email = :email, nv.diaChi = :diaChi, nv.trangThai = :trangThai, nv.caLam = :caLam, nv.taiKhoan = :taiKhoan, nv.chucVu = :chucVu WHERE nv.maNV = :maNV";
    try {

        Query query = em.createQuery(jpql);
        query.setParameter("tenNV", nv.getTenNV());
        query.setParameter("gioiTinh", nv.getGioiTinh());
        query.setParameter("ngaySinh", java.sql.Date.valueOf(nv.getNgaySinh()));
        query.setParameter("ngayVaoLam", java.sql.Date.valueOf(nv.getNgayVaoLam()));
        query.setParameter("CCCD", nv.getCCCD());
        query.setParameter("sdt", nv.getSdt());
        query.setParameter("email", nv.getEmail());
        query.setParameter("diaChi", nv.getDiaChi());
        query.setParameter("trangThai", nv.getTrangThai());
        query.setParameter("caLam", nv.getCaLam());
        query.setParameter("taiKhoan", nv.getTaiKhoan());
        query.setParameter("chucVu", nv.getChucVu());
        query.setParameter("maNV", nv.getMaNV());

        // Thực hiện truy vấn và kiểm tra số lượng bản ghi bị ảnh hưởng
        int result = query.executeUpdate();

        if (result > 0) {
            return nv; // Trả về đối tượng nhân viên đã được cập nhật
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
    // ham nay tra ve email, sdt, cua ma nv
//hàm ảnh
    public static String getDuongDanAnh(String maNV) {
        String jpql = "SELECT nv.duongDanAnh FROM NhanVien nv WHERE nv.maNV = :maNV";
        try {
            // Tạo query với JPQL và ánh xạ kiểu dữ liệu trả về là String
            TypedQuery<String> query = em.createQuery(jpql, String.class);
            query.setParameter("maNV", maNV);

            // Lấy kết quả đầu tiên (duongDanAnh)
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Không tìm thấy đường dẫn ảnh cho nhân viên với mã " + maNV);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }






    // 1. Lấy  nhân viên theo mã nhân viên
//    public static NhanVien getNhanVien(String maNV) {
//        String sql = "SELECT * FROM NhanVien WHERE maNV = ?";
//        try (
//                PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
//        ) {
//            stmt.setString(1, maNV);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                String tenNV = rs.getString("tenNV");
//                String gioiTinh = rs.getString("gioiTinh");
//                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
//                String ngayVaoLam = rs.getString("ngayVaoLam");
//                String CCCD = rs.getString("CCCD");
//                String sdt = rs.getString("sdt");
//                String email = rs.getString("email");
//                String diaChi = rs.getString("diaChi");
//                String trangThai = rs.getString("trangThai");
//                String macaLam = rs.getString("macaLam");
//                String maTaiKhoan = rs.getString("maTaiKhoan");
//                String maChucVu = rs.getString("maChucVu");
//                NhanVien nv = new NhanVien(maNV, tenNV, gioiTinh, ngaySinh, sdt, email, diaChi, CCCD, LocalDate.parse(ngayVaoLam), DAOChucVu.getChucVuTheoMa(maChucVu), DAOTaiKhoan.getTaiKhoanTheoMa(maTaiKhoan), trangThai, DAOCaLam.getCaLamTheoMa(macaLam));
//                return nv;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//        return null;
//    }




//    public static NhanVien getNhanVien(String maNV) {
//        String jpql = "SELECT nv FROM NhanVien nv WHERE nv.maNV = :maNV";
//        try {
//            // Tạo một đối tượng query với JPQL
//            TypedQuery<NhanVien> query = em.createQuery(jpql, NhanVien.class);
//            query.setParameter("maNV", maNV);
//
//            // Thực hiện truy vấn và lấy kết quả
//            NhanVien nv = query.getSingleResult(); // getSingleResult sẽ trả về 1 kết quả duy nhất nếu có
//
//            return nv;
//        } catch (NoResultException e) {
//            System.out.println("Không tìm thấy nhân viên với mã " + maNV);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    // lay toan bo nhan vien
//    public static ArrayList<NhanVien> layDanhSachNhanVien() {
//        ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
//        String sql = "SELECT * FROM NhanVien";
//        try (
//                PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
//                ResultSet rs = stmt.executeQuery();
//        ) {
//            while (rs.next()) {
//                String maNV = rs.getString("maNV");
//                String tenNV = rs.getString("tenNV");
//                String gioiTinh = rs.getString("gioiTinh");
//                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
//                String ngayVaoLam = rs.getString("ngayVaoLam");
//                String CCCD = rs.getString("CCCD");
//                String sdt = rs.getString("sdt");
//                String email = rs.getString("email");
//                String diaChi = rs.getString("diaChi");
//                String trangThai = rs.getString("trangThai");
//                String macaLam = rs.getString("macaLam");
//                String maTaiKhoan = rs.getString("maTaiKhoan");
//                String maChucVu = rs.getString("maChucVu");
//                NhanVien nv = new NhanVien(maNV, tenNV, gioiTinh, ngaySinh, sdt, email, diaChi, CCCD, LocalDate.parse(ngayVaoLam), DAOChucVu.getChucVuTheoMa(maChucVu), DAOTaiKhoan.getTaiKhoanTheoMa(maTaiKhoan), trangThai, DAOCaLam.getCaLamTheoMa(macaLam));
//                dsNhanVien.add(nv);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dsNhanVien;
//    }

    // 2. Lấy  nhân viên theo tên nhân viên dùng like
//    public static NhanVien getNhanVienTheoTen(String tenNV) {
//        String sql = "SELECT * FROM NhanVien WHERE tenNV LIKE ?";
//        try (
//                PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
//        ) {
//            stmt.setString(1, "%" + tenNV + "%");
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                String maNV = rs.getString("maNV");
//                String gioiTinh = rs.getString("gioiTinh");
//                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
//                String ngayVaoLam = rs.getString("ngayVaoLam");
//                String CCCD = rs.getString("CCCD");
//                String sdt = rs.getString("sdt");
//                String email = rs.getString("email");
//                String diaChi = rs.getString("diaChi");
//                String trangThai = rs.getString("trangThai");
//                String macaLam = rs.getString("macaLam");
//                CaLam cl = DAOCaLam.getCaLamTheoMa(macaLam);
//                String maTaiKhoan = rs.getString("maTaiKhoan");
//                TaiKhoan tk = DAOTaiKhoan.getTaiKhoanTheoMa(maTaiKhoan);
//                String maChucVu = rs.getString("maChucVu");
//                ChucVu cv = DAOChucVu.getChucVuTheoMa(maChucVu);
//                NhanVien nv = new NhanVien(maNV, tenNV, gioiTinh, ngaySinh, sdt, email, diaChi, CCCD, LocalDate.parse(ngayVaoLam), cv, tk, trangThai, cl);
//                return nv;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//
//    }

//    // 3. Lấy  nhân viên theo số điện thoại
//    public static NhanVien getNhanVienTheoSDT(String sdt) {
//        String sql = "SELECT * FROM NhanVien WHERE sdt = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, sdt);
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                String maNV = rs.getString("maNV");
//                String tenNV = rs.getString("tenNV");
//                String gioiTinh = rs.getString("gioiTinh");
//                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
//                String ngayVaoLam = rs.getString("ngayVaoLam");
//                String CCCD = rs.getString("CCCD");
//                String email = rs.getString("email");
//                String diaChi = rs.getString("diaChi");
//                String trangThai = rs.getString("trangThai");
//                String macaLam = rs.getString("macaLam");
//                CaLam cl = DAOCaLam.getCaLamTheoMa(macaLam);
//                String maTaiKhoan = rs.getString("maTaiKhoan");
//                TaiKhoan tk = DAOTaiKhoan.getTaiKhoanTheoMa(maTaiKhoan);
//                String maChucVu = rs.getString("maChucVu");
//                ChucVu cv = DAOChucVu.getChucVuTheoMa(maChucVu);
//                NhanVien nv = new NhanVien(maNV, tenNV, gioiTinh, ngaySinh, sdt, email, diaChi, CCCD, LocalDate.parse(ngayVaoLam), cv, tk, trangThai, cl);
//                return nv;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    // 5. Lấy  nhân viên theo chứng minh nhân dân
//    public static NhanVien getNhanVienTheoCCCD(String CCCD) {
//        String sql = "SELECT * FROM NhanVien WHERE CCCD = ?";
//        try (
//                PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, CCCD);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                String maNV = rs.getString("maNV");
//                String tenNV = rs.getString("tenNV");
//                String gioiTinh = rs.getString("gioiTinh");
//                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
//                String ngayVaoLam = rs.getString("ngayVaoLam");
//                String sdt = rs.getString("sdt");
//                String email = rs.getString("email");
//                String diaChi = rs.getString("diaChi");
//                String trangThai = rs.getString("trangThai");
//                String macaLam = rs.getString("macaLam");
//                CaLam cl = DAOCaLam.getCaLamTheoMa(macaLam);
//                String maTaiKhoan = rs.getString("maTaiKhoan");
//                TaiKhoan tk = DAOTaiKhoan.getTaiKhoanTheoMa(maTaiKhoan);
//                String maChucVu = rs.getString("maChucVu");
//                ChucVu cv = DAOChucVu.getChucVuTheoMa(maChucVu);
//                NhanVien nv = new NhanVien(maNV, tenNV, gioiTinh, ngaySinh, sdt, email, diaChi, CCCD, LocalDate.parse(ngayVaoLam), cv, tk, trangThai, cl);
//                return nv;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    // 6.to do Lấy  các nhân viên theo mã chức vụ
//    public static NhanVien getNhanVienTheoChucVu(String maChucVu) {
//        String sql = "SELECT * FROM NhanVien WHERE maChucVu = ?";
//        try (
//                PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maChucVu);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                String maNV = rs.getString("maNV");
//                String tenNV = rs.getString("tenNV");
//                String gioiTinh = rs.getString("gioiTinh");
//                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
//                String ngayVaoLam = rs.getString("ngayVaoLam");
//                String CCCD = rs.getString("CCCD");
//                String sdt = rs.getString("sdt");
//                String email = rs.getString("email");
//                String diaChi = rs.getString("diaChi");
//                String trangThai = rs.getString("trangThai");
//                String macaLam = rs.getString("macaLam");
//                CaLam cl = DAOCaLam.getCaLamTheoMa(macaLam);
//                String maTaiKhoan = rs.getString("maTaiKhoan");
//                TaiKhoan tk = DAOTaiKhoan.getTaiKhoanTheoMa(maTaiKhoan);
//                ChucVu cv = DAOChucVu.getChucVuTheoMa(maChucVu);
//                NhanVien nv = new NhanVien(maNV, tenNV, gioiTinh, ngaySinh, sdt, email, diaChi, CCCD, LocalDate.parse(ngayVaoLam), cv, tk, trangThai, cl);
//                return nv;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    // 8. to do Lấy cac nhân viên theo trạng thái
//    public static ArrayList<NhanVien> getNhanVienTheoTrangThai(String trangThai) {
//        ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
//        String sql = "SELECT * FROM NhanVien WHERE trangThai = ?";
//        try (
//                PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, trangThai);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                String maNV = rs.getString("maNV");
//                String tenNV = rs.getString("tenNV");
//                String gioiTinh = rs.getString("gioiTinh");
//                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
//                String ngayVaoLam = rs.getString("ngayVaoLam");
//                String CCCD = rs.getString("CCCD");
//                String sdt = rs.getString("sdt");
//                String email = rs.getString("email");
//                String diaChi = rs.getString("diaChi");
//                String macaLam = rs.getString("macaLam");
//                CaLam cl = DAOCaLam.getCaLamTheoMa(macaLam);
//                String maTaiKhoan = rs.getString("maTaiKhoan");
//                TaiKhoan tk = DAOTaiKhoan.getTaiKhoanTheoMa(maTaiKhoan);
//                String maChucVu = rs.getString("maChucVu");
//                ChucVu cv = DAOChucVu.getChucVuTheoMa(maChucVu);
//                NhanVien nv = new NhanVien(maNV, tenNV, gioiTinh, ngaySinh, sdt, email, diaChi, CCCD, LocalDate.parse(ngayVaoLam), cv, tk, trangThai, cl);
//                dsNhanVien.add(nv);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dsNhanVien;
//    }
//
//    //sua nhan vien tra ve doi tuong nhan vien
//    public static NhanVien suaNhanVien(NhanVien nv) {
//        String sql = "UPDATE NhanVien SET tenNV = ?, gioiTinh = ?, ngaySinh = ?, ngayVaoLam = ?, CCCD = ?, sdt = ?, email = ?, diaChi = ?, trangThai = ?, macaLam = ?, maTaiKhoan = ?, maChucVu = ? WHERE maNV = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, nv.getTenNV());
//            stmt.setString(2, nv.getGioiTinh());
//            stmt.setDate(3, java.sql.Date.valueOf(nv.getNgaySinh()));
//            stmt.setString(4, nv.getNgayVaoLam().toString());
//            stmt.setString(5, nv.getCCCD());
//            stmt.setString(6, nv.getSdt());
//            stmt.setString(7, nv.getEmail());
//            stmt.setString(8, nv.getDiaChi());
//            stmt.setString(9, nv.getTrangThai());
//            stmt.setString(10, nv.getCaLam().getMaCL());
//            stmt.setString(11, nv.getTaiKhoan().getMaTK());
//            stmt.setString(12, nv.getChucVu().getMaCV());
//            stmt.setString(13, nv.getMaNV());
//            if (stmt.executeUpdate() > 0) {
//                return nv;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    // ham nay tra ve email, sdt, cua ma nv
//    public static String getContactInfo(String maTK) {
//        String sql = "SELECT email, phone FROM TaiKhoan WHERE maTK = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maTK);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    String email = rs.getString("email");
//                    String phone = rs.getString("phone");
//                    return email != null ? email : phone;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    // lay duongDanAnh cua anh nhan vien
//    public static String getDuongDanAnh(String maNV) {
//        String sql = "SELECT duongDanAnh FROM NhanVien WHERE maNV = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maNV);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    return rs.getString("duongDanAnh");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    public static String getDuongDanAnh(String maNV) {
//        String jpql = "SELECT nv.duongDanAnh FROM NhanVien nv WHERE nv.maNV = :maNV";
//        try {
//            // Tạo query với JPQL và ánh xạ kiểu dữ liệu trả về là String
//            TypedQuery<String> query = em.createQuery(jpql, String.class);
//            query.setParameter("maNV", maNV);
//
//            // Lấy kết quả đầu tiên (duongDanAnh)
//            return query.getSingleResult();
//        } catch (NoResultException e) {
//            System.out.println("Không tìm thấy đường dẫn ảnh cho nhân viên với mã " + maNV);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

}
