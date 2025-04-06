package control.impl;

import connectDB.connectDB_1;
import entity.HoaDon;
import entity.Ve;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import service.HoaDonService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DAOHoaDon extends UnicastRemoteObject {
    /*CREATE TABLE HoaDon
(
    maHD         VARCHAR(20) PRIMARY KEY,
    ngayGioLapHD DATETIME DEFAULT GETDATE(),
    maNhanVien   VARCHAR(11) NOT NULL,
    maKhachHang  VARCHAR(20) NOT NULL,
    soLuong      INT CHECK (soLuong > 0),
    */
    // ham them hoa don 4 thuoc tinh
    //INSERT INTO HoaDon(maNhanVien, maKhachHang, soLuong)

//    public static boolean themHoaDon(HoaDon hd) {
//        String sql = "INSERT INTO HoaDon(maNhanVien, maKhachHang, soLuong) VALUES(?, ?, ?)";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, hd.getNhanVien().getMaNV());
//            stmt.setString(2, hd.getKhachHang().getMaKH());
//            stmt.setInt(3, hd.getSoLuong());
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    public static EntityManager em = connectDB_1.getEntityManager();

    protected DAOHoaDon() throws RemoteException {
    }

    public static boolean themHoaDon(HoaDon hd) throws RemoteException{
        try {
                em.getTransaction().begin();
                HoaDonService hoaDonService = new HoaDonService(em);
                hoaDonService.persistHoaDon(hd);
                em.getTransaction().commit();
                System.out.println("Thêm hóa đơn thành công");
            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    // doc hoa don theo ma nhan vien
//    public static ArrayList<HoaDon> docHoaDonTheoNhanVien(String maNV) {
//        String sql = "SELECT * FROM HoaDon WHERE maNhanVien = ?";
//        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
//
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//
//            stmt.setString(1, maNV);
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    String maHD = rs.getString("maHD");
//                    LocalDateTime ngayGioLapHD = rs.getTimestamp("ngayGioLapHD").toLocalDateTime();
//                    String maKhachHang = rs.getString("maKhachHang");
//                    int soLuong = rs.getInt("soLuong");
//                    ArrayList<Ve> dsVe = DAOVe.layDSVeTheoMaHD(maHD);
//                    NhanVien nv = DAONhanVien.getNhanVien(maNV);
//                    KhachHang kh = DAOKhachHang.layKhachHangTheoMa(maKhachHang);
//                    HoaDon hd = new HoaDon(maHD, ngayGioLapHD, nv, kh, soLuong, dsVe);
//                    dsHoaDon.add(hd);
//                }
//
//                return dsHoaDon;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return dsHoaDon; // Trả về danh sách trống nếu có lỗi xảy ra
//    }

    public ArrayList<HoaDon> docHoaDonTheoNhanVien(String maNV)throws RemoteException {
        try {
            TypedQuery<HoaDon> query = em.createQuery("SELECT hd FROM HoaDon hd WHERE hd.nhanVien.maNV = :maNV", HoaDon.class);
            query.setParameter("maNV", maNV);
            List<HoaDon> resultList = query.getResultList();
            return new ArrayList<>(resultList); // Chuyển đổi List thành ArrayList
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Trả về danh sách trống nếu có lỗi xảy ra
        }
    }

    // read danh sach hoa don theo ma khach hang
//    public static ArrayList<HoaDon> docHoaDonTheoKhachHang(String maKH) {
//        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
//        String sql = "SELECT * FROM HoaDon WHERE maKhachHang = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maKH);
//
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    String maHD = rs.getString("maHD");
//                    LocalDateTime ngayGioLapHD = rs.getTimestamp("ngayGioLapHD").toLocalDateTime();
//                    String maNhanVien = rs.getString("maNhanVien");
//                    int soLuong = rs.getInt("soLuong");
//                    ArrayList<Ve> dsVe = DAOVe.layDSVeTheoMaHD(maHD);
//                    NhanVien nv = DAONhanVien.getNhanVien(maNhanVien);
//                    KhachHang kh = DAOKhachHang.layKhachHangTheoMa(maKH);
//                    HoaDon hd = new HoaDon(maHD, ngayGioLapHD, nv, kh, soLuong, dsVe);
//                    dsHoaDon.add(hd);
//                }
//            }
//            return dsHoaDon;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dsHoaDon;
//    }

    public static ArrayList<HoaDon> docHoaDonTheoKhachHang(String maKH)throws RemoteException {
        String jpql = "SELECT h FROM HoaDon h WHERE h.khachHang.maKH = :maKH";
        TypedQuery<HoaDon> query = em.createQuery(jpql, HoaDon.class);
        query.setParameter("maKH", maKH);

        List<HoaDon> dsHoaDon = query.getResultList();

        for (HoaDon hoaDon : dsHoaDon) {
            List<Ve> dsVe = DAOVe.layDSVeTheoMaHD(hoaDon.getMaHD());
            ArrayList<Ve> dsVe1 = new ArrayList<>(dsVe);
            hoaDon.setDanhSachVe(dsVe1);
        }

        return new ArrayList<>(dsHoaDon);
    }

//    public static ArrayList<HoaDon> docHoaDonTheoKhachHang(String maKH) {
//        try {
//            TypedQuery<HoaDon> query = em.createQuery(
//                    "SELECT hd FROM HoaDon hd WHERE hd.khachHang.maKH = :maKH", HoaDon.class);
//            query.setParameter("maKH", maKH);
//
//            List<HoaDon> resultList = query.getResultList();
//            System.out.println("Danh sách hóa đơn theo khách hàng: "+maKH+"-----------------------------------------------------" + resultList);
//            return new ArrayList<>(resultList); // Chuyển đổi List thành ArrayList
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ArrayList<>(); // Trả về danh sách trống nếu có lỗi xảy ra
//        }
//    }

    // doc danh sach hoa don theo ngay
//    public static ArrayList<HoaDon> docHoaDonTheoNgay(LocalDate ngay) {
//        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
//        String sql = "SELECT * FROM HoaDon WHERE CONVERT(DATE, ngayGioLapHD) = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setDate(1, java.sql.Date.valueOf(ngay));
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    String maHD = rs.getString("maHD");
//                    LocalDateTime ngayGioLapHD = rs.getTimestamp("ngayGioLapHD").toLocalDateTime();
//                    String maNhanVien = rs.getString("maNhanVien");
//                    NhanVien nv = DAONhanVien.getNhanVien(maNhanVien);
//                    String maKhachHang = rs.getString("maKhachHang");
//                    KhachHang kh = DAOKhachHang.layKhachHangTheoMa(maKhachHang);
//                    int soLuong = rs.getInt("soLuong");
//                    ArrayList<Ve> dsVe = DAOVe.layDSVeTheoMaHD(maHD);
//                    HoaDon hd = new HoaDon(maHD, ngayGioLapHD, nv, kh, soLuong, dsVe);
//                    dsHoaDon.add(hd);
//                }
//                return dsHoaDon;
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static ArrayList<HoaDon> docHoaDonTheoNgay(LocalDate ngay)throws RemoteException{
        try {
            TypedQuery<HoaDon> query = em.createQuery(
                    "SELECT hd FROM HoaDon hd WHERE hd.ngayGioLapHD = :ngay", HoaDon.class);
            query.setParameter("ngay", ngay);
            List<HoaDon> resultList = query.getResultList();
            return new ArrayList<>(resultList); // Chuyển đổi List thành ArrayList
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Trả về danh sách trống nếu có lỗi xảy ra
        }
    }

    // lay toan bo ds hoa don trong csdl
//    public static ArrayList<HoaDon> layDanhSachHoaDon() {
//        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
//        String sql = "SELECT * FROM HoaDon";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                String maHD = rs.getString("maHD");
//                LocalDateTime ngayGioLapHD = rs.getTimestamp("ngayGioLapHD").toLocalDateTime();
//                String maNhanVien = rs.getString("maNhanVien");
//                NhanVien nv = DAONhanVien.getNhanVien(maNhanVien);
//                String maKhachHang = rs.getString("maKhachHang");
//                KhachHang kh = DAOKhachHang.layKhachHangTheoMa(maKhachHang);
//                int soLuong = rs.getInt("soLuong");
//                ArrayList<Ve> dsVe = DAOVe.layDSVeTheoMaHD(maHD);
//                HoaDon hd = new HoaDon(maHD, ngayGioLapHD, nv, kh, soLuong, dsVe);
//                dsHoaDon.add(hd);
//
//                System.out.println("HOA DON: " + hd);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dsHoaDon;
//    }

    public static ArrayList<HoaDon> layDanhSachHoaDon()throws RemoteException {
        try {
            TypedQuery<HoaDon> query = em.createQuery("SELECT hd FROM HoaDon hd", HoaDon.class);
            List<HoaDon> resultList = query.getResultList();
            return new ArrayList<>(resultList); // Chuyển đổi List thành ArrayList
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Trả về danh sách trống nếu có lỗi xảy ra
        }
    }

    // Lấy hóa đơn cuối cùng được tạo
//    public static HoaDon getHoaDonCuoiCung() {
//        String sql = "SELECT TOP 1 * FROM HoaDon ORDER BY ngayGioLapHD DESC";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            if (rs.next()) {
//                String maHD = rs.getString("maHD");
//
//                System.out.println("Mã hóa đơn: " + maHD);
//                LocalDateTime ngayGioLapHD = rs.getTimestamp("ngayGioLapHD").toLocalDateTime();
//                String maNhanVien = rs.getString("maNhanVien");
//                NhanVien nv = DAONhanVien.getNhanVien(maNhanVien);
//                String maKhachHang = rs.getString("maKhachHang");
//                KhachHang kh = DAOKhachHang.layKhachHangTheoMa(maKhachHang);
//                int soLuong = rs.getInt("soLuong");
//                ArrayList<Ve> dsVe = DAOVe.layDSVeTheoMaHD(maHD);
//                System.out.println("lay ds ve theo ma hd: " + dsVe);
//                dsVe.forEach(System.out::println);
//                return new HoaDon(maHD, ngayGioLapHD, nv, kh, soLuong, dsVe);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    public static HoaDon getHoaDonCuoiCung()throws RemoteException {
        try {
            TypedQuery<HoaDon> query = em.createQuery("SELECT hd FROM HoaDon hd ORDER BY hd.ngayGioLapHD DESC", HoaDon.class);
            query.setMaxResults(1);

            List<HoaDon> resultList = query.getResultList();

            if (resultList.isEmpty()) {
                System.out.println("Danh sách hóa đơn trống.");
                return null; // Trả về null nếu danh sách rỗng
            }

            return resultList.get(0); // Trả về hóa đơn cuối cùng được tạo
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Trả về null nếu có lỗi xảy ra
        }
    }

    //    get hóa đơn theo mã hóa đơn
//    public static HoaDon getHoaDon(String maHD) {
//        String sql = "SELECT * FROM HoaDon WHERE maHD = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maHD);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    LocalDateTime ngayGioLapHD = rs.getTimestamp("ngayGioLapHD").toLocalDateTime();
//                    String maNhanVien = rs.getString("maNhanVien");
//                    NhanVien nv = DAONhanVien.getNhanVien(maNhanVien);
//                    String maKhachHang = rs.getString("maKhachHang");
//                    KhachHang kh = DAOKhachHang.layKhachHangTheoMa(maKhachHang);
//                    int soLuong = rs.getInt("soLuong");
//                    ArrayList<Ve> dsVe = DAOVe.layDSVeDaBanTheoMaHD(maHD);
//                    return new HoaDon(maHD, ngayGioLapHD, nv, kh, soLuong, dsVe);
//
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//
//    }

    public static HoaDon getHoaDon(String maHD)throws RemoteException {
        try {
            TypedQuery<HoaDon> query = em.createQuery("SELECT hd FROM HoaDon hd WHERE hd.maHD = :maHD", HoaDon.class);
            query.setParameter("maHD", maHD);
            List<HoaDon> resultList = query.getResultList();
            return resultList.get(0); // Trả về hóa đơn có mã tương ứng
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Trả về null nếu có lỗi xảy ra
        }
    }

//    public static void main(String[] args) throws SQLException {
//        //lay ds ve daban theo mahd
//        System.out.println(DAOHoaDon.getHoaDon("HD241023000003").getDanhSachVe());
//    }
}
