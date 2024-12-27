package control;

import connectDB.ConnectDB;
import entity.KhuyenMai;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @Dự án: tau-viet-express
 * @Class: DAOKhuyenMai
 * @Tạo vào ngày: 10/15/2024
 * @Tác giả: Thai
 */
public class DAOKhuyenMai {
    /*maKM        VARCHAR(15) PRIMARY KEY,
    ngayApDung  DATE NOT NULL,
    ngayKetThuc DATE NOT NULL,
    phanTramKM  FLOAT CHECK (phanTramKM > 0 AND phanTramKM <= 1),
    CHECK (ngayApDung >= CONVERT(DATE, GETDATE())),
    CHECK (ngayKetThuc > ngayApDung)*/
    // them INSERT INTO KhuyenMai (ngayApDung, ngayKetThuc, phanTramKM)
    public static boolean themKhuyenMai(KhuyenMai km) {
        String sql = "INSERT INTO KhuyenMai(ngayApDung, ngayKetThuc, doiTuong, phanTramKM) VALUES(?, ?, ?, ?)";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(km.getNgayBD())); // Chuyển đổi LocalDate thành Date
            stmt.setDate(2, Date.valueOf(km.getNgayKT())); // Chuyển đổi LocalDate thành Date
            stmt.setString(3, km.getDoiTuong());
            System.out.println(km.getDoiTuong());
            stmt.setDouble(4, km.getPhanTramKM());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    // lay tat ca cac doi tuong khuyen mai
    public static ArrayList<String> getDSDoiTuongKhuyenMai() {
        ArrayList<String> dsDoiTuong = new ArrayList<>();
        String sql = "SELECT DISTINCT doiTuong FROM KhuyenMai";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String doiTuong = rs.getString("doiTuong");
                dsDoiTuong.add(doiTuong);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsDoiTuong;
    }

    // sua doi tuong khuyen mai tra ve doi tuong khuyen mai
    public static KhuyenMai suaKhuyenMai(KhuyenMai km) {
        String sql = "UPDATE KhuyenMai SET ngayApDung = ?, ngayKetThuc = ?, phanTramKM = ? WHERE maKM = ?";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(km.getNgayBD())); // Chuyển đổi LocalDate thành Date
            stmt.setDate(2, Date.valueOf(km.getNgayKT())); // Chuyển đổi LocalDate thành Date
            stmt.setDouble(3, km.getPhanTramKM());
            stmt.setString(4, km.getMaKM());
            if(stmt.executeUpdate() > 0) {
                return km;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    // xoa
    public static boolean xoaKhuyenMai(String maKM) {
        String sql = "DELETE FROM KhuyenMai WHERE maKM = ?";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, maKM);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // lay danh sach khuyen mai
    public static ArrayList<KhuyenMai> getDSKhuyenMai() {
        ArrayList<KhuyenMai> dsKhuyenMai = new ArrayList<>();
        String sql = "SELECT * FROM KhuyenMai";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String maKM = rs.getString("maKM");
                LocalDate ngayBD = rs.getDate("ngayApDung").toLocalDate(); // Chuyển đổi Date thành LocalDate
                LocalDate ngayKT = rs.getDate("ngayKetThuc").toLocalDate(); // Chuyển đổi Date thành LocalDate
                String doiTuong = rs.getString("doiTuong");
                double phanTramKM = rs.getDouble("phanTramKM");
                Boolean daGuiThongBao = (Boolean) rs.getObject("daGuiThongBao");
                if (daGuiThongBao == null) {
                    daGuiThongBao = false; // Nếu là null, gán là false
                }

                KhuyenMai km = new KhuyenMai(maKM, ngayBD, ngayKT, doiTuong, phanTramKM, daGuiThongBao);
                dsKhuyenMai.add(km);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsKhuyenMai;
    }

    public static void main(String[] args) {
        // get ds doi tuong khuyen mai
        try {
            ConnectDB.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ArrayList<String> dsDoiTuong = getDSDoiTuongKhuyenMai();
        for (String doiTuong : dsDoiTuong) {
            System.out.println(doiTuong);
        }
    }

    // lay khuyen mai theo ma
    public  static KhuyenMai getKhuyenMaiTheoMa(String maKM) {
        KhuyenMai km = null;
        String sql = "SELECT * FROM KhuyenMai WHERE maKM = ?";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, maKM);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    LocalDate ngayBD = rs.getDate("ngayApDung").toLocalDate(); // Chuyển đổi Date thành LocalDate
                    LocalDate ngayKT = rs.getDate("ngayKetThuc").toLocalDate(); // Chuyển đổi Date thành LocalDate
                    String doiTuong = rs.getString("doiTuong");
                    double phanTramKM = rs.getDouble("phanTramKM");
                    Boolean daGuiThongBao = (Boolean) rs.getObject("daGuiThongBao");
                    if (daGuiThongBao == null) {
                        daGuiThongBao = false; // Nếu là null, gán là false
                    }

                    km = new KhuyenMai(maKM, ngayBD, ngayKT, doiTuong, phanTramKM, daGuiThongBao);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return km;
    }
    // lay ds ma khuyenMai theo ma like
    public static ArrayList<KhuyenMai> getDSMaKhuyenMaiTheoMaLike(String maKM) {
        ArrayList<KhuyenMai> dsMaKM = new ArrayList<>();
        String sql = "SELECT * FROM KhuyenMai WHERE maKM LIKE ?";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, "%" + maKM + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String maKM1 = rs.getString("maKM");
                    LocalDate ngayBD = rs.getDate("ngayApDung").toLocalDate(); // Chuyển đổi Date thành LocalDate
                    LocalDate ngayKT = rs.getDate("ngayKetThuc").toLocalDate(); // Chuyển đổi Date thành LocalDate
                    String doiTuong = rs.getString("doiTuong");
                    double phanTramKM = rs.getDouble("phanTramKM");
                    Boolean daGuiThongBao = (Boolean) rs.getObject("daGuiThongBao");
                    if (daGuiThongBao == null) {
                        daGuiThongBao = false; // Nếu là null, gán là false
                    }

                    KhuyenMai km = new KhuyenMai(maKM, ngayBD, ngayKT, doiTuong, phanTramKM, daGuiThongBao);
                    dsMaKM.add(km);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsMaKM;
    }

    // lay khuyen mai theo ngay
    public static KhuyenMai getKhuyenMaiTheoNgay(LocalDate ngay) {
        KhuyenMai km = null;
        String sql = "SELECT * FROM KhuyenMai WHERE ? BETWEEN ngayApDung AND ngayKetThuc";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(ngay)); // Chuyển đổi LocalDate thành Date
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String maKM = rs.getString("maKM");
                    LocalDate ngayBD = rs.getDate("ngayApDung").toLocalDate(); // Chuyển đổi Date thành LocalDate
                    LocalDate ngayKT = rs.getDate("ngayKetThuc").toLocalDate(); // Chuyển đổi Date thành LocalDate
                    String doiTuong = rs.getString("doiTuong");
                    double phanTramKM = rs.getDouble("phanTramKM");

                    // Sử dụng rs.getObject() để lấy giá trị daGuiThongBao có thể là null
                    Boolean daGuiThongBao = (Boolean) rs.getObject("daGuiThongBao");
                    if (daGuiThongBao == null) {
                        daGuiThongBao = false; // Nếu là null, gán là false
                    }

                    km = new KhuyenMai(maKM, ngayBD, ngayKT, doiTuong, phanTramKM, daGuiThongBao);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return km;
    }


    public static void capNhatTrangThaiDaGuiThongBao(String maKM) {
        String sql = "UPDATE KhuyenMai SET daGuiThongBao = 1 WHERE maKM = ?";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, maKM);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}