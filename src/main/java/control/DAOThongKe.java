package control;

import connectDB.ConnectDB;

import javax.swing.*;
import java.sql.*;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class DAOThongKe {

    // Thống kê tổng số nhân viên
    public static int getTongSoNhanVien() {
        String sql = "SELECT COUNT(*) AS TongSoNhanVien FROM NhanVien";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("TongSoNhanVien");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Thống kê số lượng nhân viên theo chức vụ
    public static ArrayList<Object[]> getNhanVienTheoChucVu() {
        ArrayList<Object[]> results = new ArrayList<>();
        String sql = "SELECT tenCV, COUNT(*) AS SoLuongNhanVien " +
                "FROM NhanVien " +
                "JOIN ChucVu ON NhanVien.maChucVu = ChucVu.maCV " +
                "GROUP BY tenCV";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                results.add(new Object[]{rs.getString("tenCV"), rs.getInt("SoLuongNhanVien")});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    // Thống kê doanh thu theo tháng
    public static ArrayList<Object[]> getDoanhThuTheoThang(int year) {
        ArrayList<Object[]> results = new ArrayList<>();
        String sql = "SELECT YEAR(ngayGioLapHD) AS Nam, MONTH(ngayGioLapHD) AS Thang, SUM(thanhTien) AS DoanhThu " +
                "FROM HoaDon " +
                "WHERE YEAR(ngayGioLapHD) = ? " +
                "GROUP BY YEAR(ngayGioLapHD), MONTH(ngayGioLapHD) " +
                "ORDER BY Nam, Thang";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, year);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    results.add(new Object[]{rs.getInt("Nam"), rs.getInt("Thang"), rs.getDouble("DoanhThu")});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    // Lấy nhân viên bán được nhiều doanh thu nhất
    public static ArrayList<Object[]> getTopDoanhThuNhanVien() {
        ArrayList<Object[]> results = new ArrayList<>();
        String sql = "SELECT \n" +
                "            ROW_NUMBER() OVER (ORDER BY SUM(v.tongTien) DESC) AS STT,\n" +
                "            nv.maNV,\n" +
                "            nv.tenNV,\n" +
                "            SUM(v.tongTien) AS TongDoanhThu\n" +
                "        FROM NhanVien nv\n" +
                "        INNER JOIN HoaDon hd ON nv.maNV = hd.maNhanVien\n" +
                "        INNER JOIN View_VeTongTien v ON hd.maHD = v.maHoaDon\n" +
                "        GROUP BY nv.maNV, nv.tenNV\n" +
                "        ORDER BY TongDoanhThu DESC;";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[4]; // STT, MaNV, Ten, TongDoanhThu
                    row[0] = rs.getInt("STT"); // STT
                    row[1] = rs.getString("maNV"); // MaNV
                    row[2] = rs.getString("tenNV"); // Ten
                    row[3] = rs.getDouble("TongDoanhThu"); // TongDoanhThu
                    results.add(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    // Doanh thu từng ngày trong tháng
    public static ArrayList<Object[]> getDoanhThuTheoNgay(int year, int month) {
        ArrayList<Object[]> results = new ArrayList<>();
        String sql = "SELECT CAST(ngayGioLapHD AS DATE) AS Ngay, SUM(thanhTien) AS DoanhThu " +
                "FROM HoaDon " +
                "WHERE YEAR(ngayGioLapHD) = ? AND MONTH(ngayGioLapHD) = ? " +
                "GROUP BY CAST(ngayGioLapHD AS DATE) " +
                "ORDER BY CAST(ngayGioLapHD AS DATE)";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, year);
            stmt.setInt(2, month);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    results.add(new Object[]{rs.getDate("Ngay"), rs.getDouble("DoanhThu")});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public static ArrayList<Object[]> getTopDoanhThuNhanVienTrongNgay() {
        ArrayList<Object[]> results = new ArrayList<>();
        String sql = "SELECT \n" +
                "            ROW_NUMBER() OVER (ORDER BY SUM(v.tongTien) DESC) AS STT,\n" +
                "            nv.maNV,\n" +
                "            nv.tenNV,\n" +
                "            SUM(v.tongTien) AS TongDoanhThu\n" +
                "        FROM NhanVien nv\n" +
                "        INNER JOIN HoaDon hd ON nv.maNV = hd.maNhanVien\n" +
                "        INNER JOIN View_VeTongTien v ON hd.maHD = v.maHoaDon\n" +
                "        WHERE CAST(hd.ngayGioLapHD AS DATE) = CAST(GETDATE() AS DATE)\n" +
                "        GROUP BY nv.maNV, nv.tenNV\n" +
                "        ORDER BY TongDoanhThu DESC;";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[4]; // STT, MaNV, Ten, TongDoanhThu
                    row[0] = rs.getInt("STT"); // STT
                    row[1] = rs.getString("maNV"); // MaNV
                    row[2] = rs.getString("tenNV"); // Ten
                    row[3] = rs.getDouble("TongDoanhThu"); // TongDoanhThu
                    results.add(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
    public static ArrayList<Object[]> getTopDoanhThuNhanVienTheoTuan() {
        ArrayList<Object[]> results = new ArrayList<>();
        String sql = "SELECT \n" +
                "            ROW_NUMBER() OVER (ORDER BY SUM(v.tongTien) DESC) AS STT,\n" +
                "            nv.maNV,\n" +
                "            nv.tenNV,\n" +
                "            SUM(v.tongTien) AS TongDoanhThu\n" +
                "        FROM NhanVien nv\n" +
                "        INNER JOIN HoaDon hd ON nv.maNV = hd.maNhanVien\n" +
                "        INNER JOIN View_VeTongTien v ON hd.maHD = v.maHoaDon\n" +
                "        WHERE CAST(hd.ngayGioLapHD AS DATE) BETWEEN \n" +
                "              DATEADD(DAY, 1 - DATEPART(DW, GETDATE()), CAST(GETDATE() AS DATE)) \n" +
                "              AND CAST(GETDATE() AS DATE)\n" +
                "        GROUP BY nv.maNV, nv.tenNV\n" +
                "        ORDER BY TongDoanhThu DESC;";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[4]; // STT, MaNV, Ten, TongDoanhThu
                    row[0] = rs.getInt("STT"); // STT
                    row[1] = rs.getString("maNV"); // MaNV
                    row[2] = rs.getString("tenNV"); // Ten
                    row[3] = rs.getDouble("TongDoanhThu"); // TongDoanhThu
                    results.add(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
    public static ArrayList<Object[]> getTopDoanhThuNhanVienTheoThang() {
        ArrayList<Object[]> results = new ArrayList<>();
        String sql = "SELECT \n" +
                "            ROW_NUMBER() OVER (ORDER BY SUM(v.tongTien) DESC) AS STT,\n" +
                "            nv.maNV,\n" +
                "            nv.tenNV,\n" +
                "            SUM(v.tongTien) AS TongDoanhThu\n" +
                "        FROM NhanVien nv\n" +
                "        INNER JOIN HoaDon hd ON nv.maNV = hd.maNhanVien\n" +
                "        INNER JOIN View_VeTongTien v ON hd.maHD = v.maHoaDon\n" +
                "        WHERE MONTH(hd.ngayGioLapHD) = MONTH(GETDATE()) \n" +
                "              AND YEAR(hd.ngayGioLapHD) = YEAR(GETDATE())\n" +
                "        GROUP BY nv.maNV, nv.tenNV\n" +
                "        ORDER BY TongDoanhThu DESC;";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[4]; // STT, MaNV, Ten, TongDoanhThu
                    row[0] = rs.getInt("STT"); // STT
                    row[1] = rs.getString("maNV"); // MaNV
                    row[2] = rs.getString("tenNV"); // Ten
                    row[3] = rs.getDouble("TongDoanhThu"); // TongDoanhThu
                    results.add(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
    public static ArrayList<Object[]> getTopDoanhThuNhanVienTheoQuy() {
        ArrayList<Object[]> results = new ArrayList<>();
        String sql = "SELECT \n" +
                "            ROW_NUMBER() OVER (ORDER BY SUM(v.tongTien) DESC) AS STT,\n" +
                "            nv.maNV,\n" +
                "            nv.tenNV,\n" +
                "            SUM(v.tongTien) AS TongDoanhThu\n" +
                "        FROM NhanVien nv\n" +
                "        INNER JOIN HoaDon hd ON nv.maNV = hd.maNhanVien\n" +
                "        INNER JOIN View_VeTongTien v ON hd.maHD = v.maHoaDon\n" +
                "        WHERE DATEPART(QUARTER, hd.ngayGioLapHD) = DATEPART(QUARTER, GETDATE())\n" +
                "              AND YEAR(hd.ngayGioLapHD) = YEAR(GETDATE())\n" +
                "        GROUP BY nv.maNV, nv.tenNV\n" +
                "        ORDER BY TongDoanhThu DESC;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[4]; // STT, MaNV, Ten, TongDoanhThu
                    row[0] = rs.getInt("STT"); // STT
                    row[1] = rs.getString("maNV"); // MaNV
                    row[2] = rs.getString("tenNV"); // Ten
                    row[3] = rs.getDouble("TongDoanhThu"); // TongDoanhThu
                    results.add(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
    public static ArrayList<Object[]> getTopDoanhThuNhanVienTheoNam() {
        ArrayList<Object[]> results = new ArrayList<>();
        String sql = "SELECT \n" +
                "            ROW_NUMBER() OVER (ORDER BY SUM(v.tongTien) DESC) AS STT,\n" +
                "            nv.maNV,\n" +
                "            nv.tenNV,\n" +
                "            SUM(v.tongTien) AS TongDoanhThu\n" +
                "        FROM NhanVien nv\n" +
                "        INNER JOIN HoaDon hd ON nv.maNV = hd.maNhanVien\n" +
                "        INNER JOIN View_VeTongTien v ON hd.maHD = v.maHoaDon\n" +
                "        WHERE YEAR(hd.ngayGioLapHD) = YEAR(GETDATE())\n" +
                "        GROUP BY nv.maNV, nv.tenNV\n" +
                "        ORDER BY TongDoanhThu DESC;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[4]; // STT, MaNV, Ten, TongDoanhThu
                    row[0] = rs.getInt("STT"); // STT
                    row[1] = rs.getString("maNV"); // MaNV
                    row[2] = rs.getString("tenNV"); // Ten
                    row[3] = rs.getDouble("TongDoanhThu"); // TongDoanhThu
                    results.add(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public static ArrayList<Object[]> getDailyRevenueChange() {
        ArrayList<Object[]> results = new ArrayList<>();
        String sql = "SELECT " +
                "ROW_NUMBER() OVER (ORDER BY ABS(SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) = CAST(GETDATE() AS DATE) THEN 1 ELSE 0 END) " +
                "- SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) = CAST(DATEADD(DAY, -7, GETDATE()) AS DATE) THEN 1 ELSE 0 END)) DESC) AS [Top], " +
                "lc.maLC AS MaLoaiGhe, " +
                "lc.tenLC AS TenLoaiGhe, " +
                "SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) = CAST(DATEADD(DAY, -7, GETDATE()) AS DATE) THEN 1 ELSE 0 END) AS TongVeLoaiGheCu, " +
                "SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) = CAST(GETDATE() AS DATE) THEN 1 ELSE 0 END) AS TongVeLoaiGheMoi, " +
                "SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) = CAST(GETDATE() AS DATE) THEN 1 ELSE 0 END) " +
                "- SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) = CAST(DATEADD(DAY, -7, GETDATE()) AS DATE) THEN 1 ELSE 0 END) AS BienDong " +
                "FROM Ve v " +
                "JOIN ChoNgoi cn ON v.maChoNgoi = cn.maCho " +
                "JOIN LoaiCho lc ON cn.maloaiCho = lc.maLC " +
                "GROUP BY lc.maLC, lc.tenLC " +
                "ORDER BY BienDong DESC";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[6];
                    row[0] = rs.getInt(1);
                    row[1] = rs.getString("MaLoaiGhe");
                    row[2] = rs.getString("TenLoaiGhe");
                    row[3] = rs.getInt("TongVeLoaiGheCu");
                    row[4] = rs.getInt("TongVeLoaiGheMoi");
                    row[5] = rs.getInt("BienDong");
                    results.add(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
    public static ArrayList<Object[]> getDailyRevenueChange1() {
        ArrayList<Object[]> results = new ArrayList<>();

        String sql = "SELECT " +
                "ROW_NUMBER() OVER (ORDER BY ABS(SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) = CAST(GETDATE() AS DATE) THEN 1 ELSE 0 END) " +
                "- SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) = CAST(DATEADD(DAY, -7, GETDATE()) AS DATE) THEN 1 ELSE 0 END)) DESC) AS [Top], " +
                "lc.maLC AS MaLoaiGhe, " +
                "lc.tenLC AS TenLoaiGhe, " +
                "SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) = CAST(DATEADD(DAY, -7, GETDATE()) AS DATE) THEN 1 ELSE 0 END) AS TongVeLoaiGheCu, " +
                "SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) = CAST(GETDATE() AS DATE) THEN 1 ELSE 0 END) AS TongVeLoaiGheMoi, " +
                "SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) = CAST(GETDATE() AS DATE) THEN 1 ELSE 0 END) " +
                "- SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) = CAST(DATEADD(DAY, -7, GETDATE()) AS DATE) THEN 1 ELSE 0 END) AS BienDong " +
                "FROM Ve v " +
                "JOIN ChoNgoi cn ON v.maChoNgoi = cn.maCho " +
                "JOIN LoaiCho lc ON cn.maloaiCho = lc.maLC " +
                "GROUP BY lc.maLC, lc.tenLC " +
                "ORDER BY BienDong DESC";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[6];
                    row[0] = rs.getInt(1); // Thêm số thứ tự xếp hạng (Top) vào danh sách
                    row[1] = rs.getString("MaLoaiGhe"); // Thêm mã loại ghế
                    row[2] = rs.getString("TenLoaiGhe"); // Thêm tên loại ghế
                    row[3] = rs.getInt("TongVeLoaiGheCu"); // Thêm tổng vé ghế cũ
                    row[4] = rs.getInt("TongVeLoaiGheMoi"); // Thêm tổng vé ghế mới
                    row[5] = rs.getInt("BienDong");
                    results.add(row); // Thêm dữ liệu vào danh sách kết quả
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results; // Trả về danh sách kết quả
    }
    public static ArrayList<Object[]> getWeeklyRevenueChange() {
        ArrayList<Object[]> results = new ArrayList<>();

        String sql = "SELECT " +
                "ROW_NUMBER() OVER (ORDER BY ABS(SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) BETWEEN CAST(DATEADD(DAY, -7, GETDATE()) AS DATE) " +
                "AND CAST(DATEADD(DAY, -1, GETDATE()) AS DATE) THEN 1 ELSE 0 END) " +
                "- SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) BETWEEN CAST(DATEADD(DAY, -14, GETDATE()) AS DATE) " +
                "AND CAST(DATEADD(DAY, -8, GETDATE()) AS DATE) THEN 1 ELSE 0 END)) DESC) AS [Top], " +
                "lc.maLC AS MaLoaiGhe, " +
                "lc.tenLC AS TenLoaiGhe, " +
                "SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) BETWEEN CAST(DATEADD(DAY, -14, GETDATE()) AS DATE) " +
                "AND CAST(DATEADD(DAY, -8, GETDATE()) AS DATE) THEN 1 ELSE 0 END) AS TongVeTuanTruoc, " +
                "SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) BETWEEN CAST(DATEADD(DAY, -7, GETDATE()) AS DATE) " +
                "AND CAST(DATEADD(DAY, -1, GETDATE()) AS DATE) THEN 1 ELSE 0 END) AS TongVeTuanNay, " +
                "SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) BETWEEN CAST(DATEADD(DAY, -7, GETDATE()) AS DATE) " +
                "AND CAST(DATEADD(DAY, -1, GETDATE()) AS DATE) THEN 1 ELSE 0 END) " +
                "- SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) BETWEEN CAST(DATEADD(DAY, -14, GETDATE()) AS DATE) " +
                "AND CAST(DATEADD(DAY, -8, GETDATE()) AS DATE) THEN 1 ELSE 0 END) AS BienDong " +
                "FROM Ve v " +
                "JOIN ChoNgoi cn ON v.maChoNgoi = cn.maCho " +
                "JOIN LoaiCho lc ON cn.maloaiCho = lc.maLC " +
                "GROUP BY lc.maLC, lc.tenLC " +
                "ORDER BY BienDong DESC";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[6];
                    row[0] = rs.getInt(1);
                    row[1] = rs.getString("MaLoaiGhe");
                    row[2] = rs.getString("TenLoaiGhe");
                    row[3] = rs.getInt("TongVeTuanTruoc");
                    row[4] = rs.getInt("TongVeTuanNay");
                    row[5] = rs.getInt("BienDong");
                    results.add(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }
    public static ArrayList<Object[]> getMonthlyRevenueChange1() {
            ArrayList<Object[]> results = new ArrayList<>();

            String sql = "SELECT " +
                    "ROW_NUMBER() OVER (ORDER BY ABS(SUM(CASE WHEN MONTH(v.ngayGioXuatVe) = MONTH(GETDATE()) " +
                    "AND YEAR(v.ngayGioXuatVe) = YEAR(GETDATE()) THEN 1 ELSE 0 END) " +
                    "- SUM(CASE WHEN MONTH(v.ngayGioXuatVe) = MONTH(DATEADD(MONTH, -1, GETDATE())) " +
                    "AND YEAR(v.ngayGioXuatVe) = YEAR(DATEADD(MONTH, -1, GETDATE())) THEN 1 ELSE 0 END)) DESC) AS [Top], " +
                    "lc.maLC AS MaLoaiGhe, " +
                    "lc.tenLC AS TenLoaiGhe, " +
                    "SUM(CASE WHEN MONTH(v.ngayGioXuatVe) = MONTH(DATEADD(MONTH, -1, GETDATE())) " +
                    "AND YEAR(v.ngayGioXuatVe) = YEAR(DATEADD(MONTH, -1, GETDATE())) THEN 1 ELSE 0 END) AS TongVeThangTruoc, " +
                    "SUM(CASE WHEN MONTH(v.ngayGioXuatVe) = MONTH(GETDATE()) " +
                    "AND YEAR(v.ngayGioXuatVe) = YEAR(GETDATE()) THEN 1 ELSE 0 END) AS TongVeThangNay, " +
                    "SUM(CASE WHEN MONTH(v.ngayGioXuatVe) = MONTH(GETDATE()) " +
                    "AND YEAR(v.ngayGioXuatVe) = YEAR(GETDATE()) THEN 1 ELSE 0 END) " +
                    "- SUM(CASE WHEN MONTH(v.ngayGioXuatVe) = MONTH(DATEADD(MONTH, -1, GETDATE())) " +
                    "AND YEAR(v.ngayGioXuatVe) = YEAR(DATEADD(MONTH, -1, GETDATE())) THEN 1 ELSE 0 END) AS BienDong " +
                    "FROM Ve v " +
                    "JOIN ChoNgoi cn ON v.maChoNgoi = cn.maCho " +
                    "JOIN LoaiCho lc ON cn.maloaiCho = lc.maLC " +
                    "GROUP BY lc.maLC, lc.tenLC " +
                    "ORDER BY BienDong DESC";

            try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Object[] row = new Object[6];
                        row[0] = rs.getInt(1);
                        row[1] = rs.getString("MaLoaiGhe");
                        row[2] = rs.getString("TenLoaiGhe");
                        row[3] = rs.getInt("TongVeThangTruoc");
                        row[4] = rs.getInt("TongVeThangNay");
                        row[5] = rs.getInt("BienDong");
                        results.add(row);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return results;
        }
    public static ArrayList<Object[]> getQuarterlyRevenueChange() {
        ArrayList<Object[]> results = new ArrayList<>();

        String sql = "SELECT " +
                "ROW_NUMBER() OVER (ORDER BY ABS(SUM(CASE WHEN DATEPART(QUARTER, v.ngayGioXuatVe) = DATEPART(QUARTER, GETDATE()) " +
                "AND YEAR(v.ngayGioXuatVe) = YEAR(GETDATE()) THEN 1 ELSE 0 END) " +
                "- SUM(CASE WHEN DATEPART(QUARTER, v.ngayGioXuatVe) = DATEPART(QUARTER, DATEADD(QUARTER, -1, GETDATE())) " +
                "AND YEAR(v.ngayGioXuatVe) = YEAR(DATEADD(QUARTER, -1, GETDATE())) THEN 1 ELSE 0 END)) DESC) AS [Top], " +
                "lc.maLC AS MaLoaiGhe, " +
                "lc.tenLC AS TenLoaiGhe, " +
                "SUM(CASE WHEN DATEPART(QUARTER, v.ngayGioXuatVe) = DATEPART(QUARTER, DATEADD(QUARTER, -1, GETDATE())) " +
                "AND YEAR(v.ngayGioXuatVe) = YEAR(DATEADD(QUARTER, -1, GETDATE())) THEN 1 ELSE 0 END) AS TongVeQuyTruoc, " +
                "SUM(CASE WHEN DATEPART(QUARTER, v.ngayGioXuatVe) = DATEPART(QUARTER, GETDATE()) " +
                "AND YEAR(v.ngayGioXuatVe) = YEAR(GETDATE()) THEN 1 ELSE 0 END) AS TongVeQuyNay, " +
                "SUM(CASE WHEN DATEPART(QUARTER, v.ngayGioXuatVe) = DATEPART(QUARTER, GETDATE()) " +
                "AND YEAR(v.ngayGioXuatVe) = YEAR(GETDATE()) THEN 1 ELSE 0 END) " +
                "- SUM(CASE WHEN DATEPART(QUARTER, v.ngayGioXuatVe) = DATEPART(QUARTER, DATEADD(QUARTER, -1, GETDATE())) " +
                "AND YEAR(v.ngayGioXuatVe) = YEAR(DATEADD(QUARTER, -1, GETDATE())) THEN 1 ELSE 0 END) AS BienDong " +
                "FROM Ve v " +
                "JOIN ChoNgoi cn ON v.maChoNgoi = cn.maCho " +
                "JOIN LoaiCho lc ON cn.maloaiCho = lc.maLC " +
                "GROUP BY lc.maLC, lc.tenLC " +
                "ORDER BY BienDong DESC";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[6];
                    row[0] = rs.getInt(1);
                    row[1] = rs.getString("MaLoaiGhe");
                    row[2] = rs.getString("TenLoaiGhe");
                    row[3] = rs.getInt("TongVeQuyTruoc");
                    row[4] = rs.getInt("TongVeQuyNay");
                    row[5] = rs.getInt("BienDong");
                    results.add(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }
    public static ArrayList<Object[]> getYearlyRevenueChange() {
        ArrayList<Object[]> results = new ArrayList<>();

        String sql = "SELECT " +
                "ROW_NUMBER() OVER (ORDER BY ABS(SUM(CASE WHEN YEAR(v.ngayGioXuatVe) = YEAR(GETDATE()) THEN 1 ELSE 0 END) " +
                "- SUM(CASE WHEN YEAR(v.ngayGioXuatVe) = YEAR(GETDATE()) - 1 THEN 1 ELSE 0 END)) DESC) AS [Top], " +
                "lc.maLC AS MaLoaiGhe, " +
                "lc.tenLC AS TenLoaiGhe, " +
                "SUM(CASE WHEN YEAR(v.ngayGioXuatVe) = YEAR(GETDATE()) - 1 THEN 1 ELSE 0 END) AS TongVeNamTruoc, " +
                "SUM(CASE WHEN YEAR(v.ngayGioXuatVe) = YEAR(GETDATE()) THEN 1 ELSE 0 END) AS TongVeNamNay, " +
                "SUM(CASE WHEN YEAR(v.ngayGioXuatVe) = YEAR(GETDATE()) THEN 1 ELSE 0 END) - " +
                "SUM(CASE WHEN YEAR(v.ngayGioXuatVe) = YEAR(GETDATE()) - 1 THEN 1 ELSE 0 END) AS BienDong " +
                "FROM Ve v " +
                "JOIN ChoNgoi cn ON v.maChoNgoi = cn.maCho " +
                "JOIN LoaiCho lc ON cn.maloaiCho = lc.maLC " +
                "GROUP BY lc.maLC, lc.tenLC " +
                "ORDER BY BienDong DESC";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[6];
                    row[0] = rs.getInt(1);
                    row[1] = rs.getString("MaLoaiGhe");
                    row[2] = rs.getString("TenLoaiGhe");
                    row[3] = rs.getInt("TongVeNamTruoc");
                    row[4] = rs.getInt("TongVeNamNay");
                    row[5] = rs.getInt("BienDong");
                    results.add(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }
    public static Object[] getDoanhThuVaSoVeTrongKhoangThoiGian(Date startDate,Date endDate) {
        int totalRevenue = 0;
        int totalTickets = 0;

        String sql = "\n" +
                "SELECT \n" +
                "    COALESCE(SUM(tongTien), 0) AS totalRevenue,\n" +
                "\tCOUNT(*) AS totalTickets\n" +
                "FROM \n" +
                "    [dbo].[View_VeTongTien]\n" +
                "WHERE \n" +
                "    ngayGioXuatVe BETWEEN ? AND ?;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setDate(1, new Date(startDate.getTime()));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(endDate);
            calendar.add(Calendar.DATE, 1); // Cộng thêm 1 ngày
            stmt.setDate(2, new Date(calendar.getTimeInMillis()));
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    totalRevenue = resultSet.getInt("totalRevenue");
                    totalTickets = resultSet.getInt("totalTickets");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Lưu kết quả vào Object[]
        Object[] row = new Object[2];
        row[0] = totalRevenue; // Tổng doanh thu
        row[1] = totalTickets; // Tổng số vé bán

        return row;
    }
    public static Map<Integer, Double> getRevenueByHour() {
        String query = "WITH HourList AS (\n" +
                "                    SELECT 7 AS gio\n" +
                "                    UNION ALL\n" +
                "                    SELECT gio + 1 FROM HourList WHERE gio < 17\n" +
                "                )\n" +
                "                SELECT \n" +
                "                    h.gio,\n" +
                "                    ISNULL(SUM(v.tongTien), 0) AS doanhThu\n" +
                "                FROM \n" +
                "                    HourList h\n" +
                "                LEFT JOIN \n" +
                "                    View_VeTongTien v\n" +
                "                    ON DATEPART(HOUR, v.ngayGioXuatVe) = h.gio\n" +
                "                    AND CONVERT(DATE, v.ngayGioXuatVe) = CONVERT(DATE, GETDATE())\n" +
                "                GROUP BY \n" +
                "                    h.gio\n" +
                "                ORDER BY \n" +
                "                    h.gio;";

        Map<Integer, Double> revenueByHour = new TreeMap<>();

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int hour = rs.getInt("gio");
                    double revenue = rs.getDouble("doanhThu");
                    revenueByHour.put(hour, revenue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return revenueByHour;
    }
    public static Map<LocalDate, Double> getCurrentWeekRevenue() {
        Map<LocalDate, Double> dailyRevenue = new LinkedHashMap<>();
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY); // Thứ Hai của tuần này
        LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY); // Chủ nhật của tuần này

        String query = "WITH DateRange AS (\n" +
                "        SELECT CAST(? AS DATE) AS ngay\n" +
                "        UNION ALL\n" +
                "        SELECT DATEADD(DAY, 1, ngay)\n" +
                "        FROM DateRange\n" +
                "        WHERE ngay < ?\n" +
                "    ),\n" +
                "    RevenueData AS (\n" +
                "        SELECT \n" +
                "            CONVERT(DATE, ngayGioXuatVe) AS ngay,\n" +
                "            SUM(ISNULL(tongTien, 0)) AS doanhThu\n" +
                "        FROM View_VeTongTien\n" +
                "        WHERE CONVERT(DATE, ngayGioXuatVe) BETWEEN ? AND ? \n" +
                "        GROUP BY CONVERT(DATE, ngayGioXuatVe)\n" +
                "    )\n" +
                "    SELECT \n" +
                "        DATEDATE.ngay,\n" +
                "        ISNULL(RS.doanhThu, 0) AS doanhThu\n" +
                "    FROM DateRange DATEDATE\n" +
                "    LEFT JOIN RevenueData RS ON DATEDATE.ngay = RS.ngay\n" +
                "    ORDER BY DATEDATE.ngay\n" +
                "    OPTION (MAXRECURSION 0);";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)) {
            stmt.setDate(1, Date.valueOf(startOfWeek));
            stmt.setDate(2, Date.valueOf(endOfWeek));
            stmt.setDate(3, Date.valueOf(startOfWeek));
            stmt.setDate(4, Date.valueOf(endOfWeek));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    LocalDate date = rs.getDate("ngay").toLocalDate();
                    double revenue = rs.getDouble("doanhThu");
                    dailyRevenue.put(date, revenue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dailyRevenue;
    }
    public static Map<LocalDate, Double> getDoanhThuTheoNgay_TuNgay_DenNgay(Date startOfWeek, Date endOfWeek, String maLoaiVe) {
        Map<LocalDate, Double> dailyRevenue = new LinkedHashMap<>();
        String query = "WITH DateRange AS (\n" +
                "        SELECT CAST(? AS DATE) AS ngay\n" +
                "        UNION ALL\n" +
                "        SELECT DATEADD(DAY, 1, ngay)\n" +
                "        FROM DateRange\n" +
                "        WHERE ngay < ?\n" +
                "    ),\n" +
                "    RevenueData AS (\n" +
                "        SELECT \n" +
                "            CONVERT(DATE, ngayGioXuatVe) AS ngay,\n" +
                "            SUM(ISNULL(tongTien, 0)) AS doanhThu\n" +
                "        FROM View_VeTongTien\n" +
                "        WHERE CONVERT(DATE, ngayGioXuatVe) BETWEEN ? AND ? \n" +
                (maLoaiVe.isEmpty() ? "" : "      AND maLoaiVe = ? ") +
                "        GROUP BY CONVERT(DATE, ngayGioXuatVe)\n" +
                "    )\n" +
                "    SELECT \n" +
                "        DATEDATE.ngay,\n" +
                "        ISNULL(RS.doanhThu, 0) AS doanhThu\n" +
                "    FROM DateRange DATEDATE\n" +
                "    LEFT JOIN RevenueData RS ON DATEDATE.ngay = RS.ngay\n" +
                "    ORDER BY DATEDATE.ngay\n" +
                "    OPTION (MAXRECURSION 0);";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)) {
            stmt.setDate(1, new Date(startOfWeek.getTime()));
            stmt.setDate(2, new Date(endOfWeek.getTime()));
            stmt.setDate(3, new Date(startOfWeek.getTime()));
            stmt.setDate(4, new Date(endOfWeek.getTime()));
            if (!maLoaiVe.isEmpty()) {
                stmt.setString(5, maLoaiVe);
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    LocalDate date = rs.getDate("ngay").toLocalDate();
                    double revenue = rs.getDouble("doanhThu");
                    dailyRevenue.put(date, revenue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return dailyRevenue;
    }
    public static Map<Integer, Double> getTuan_TuNgay_DenNgay(Date startDate, Date endDate, String maLoaiVe) {
        Map<Integer, Double> weeklyRevenue = new LinkedHashMap<>();
        String query =
                "WITH DateRange AS ( " +
                        "    SELECT CAST(? AS DATE) AS ngay " +
                        "    UNION ALL " +
                        "    SELECT DATEADD(DAY, 1, ngay) " +
                        "    FROM DateRange " +
                        "    WHERE ngay <= ? " +
                        "), " +
                        "RevenueData AS ( " +
                        "    SELECT " +
                        "        CONVERT(DATE, ngayGioXuatVe) AS ngay, " +
                        "        SUM(ISNULL(tongTien, 0)) AS doanhThu " +
                        "    FROM View_VeTongTien " +
                        "    WHERE CONVERT(DATE, ngayGioXuatVe) BETWEEN ? AND ? " +
                        (maLoaiVe.isEmpty() ? "" : "      AND maLoaiVe = ? ") +
                        "    GROUP BY CONVERT(DATE, ngayGioXuatVe) " +
                        "), " +
                        "WeekData AS ( " +
                        "    SELECT " +
                        "        DATEDATE.ngay, " +
                        "        DATEADD(DAY, -DATEPART(WEEKDAY, DATEDATE.ngay) + 1, DATEDATE.ngay) AS startOfWeek, " +
                        "        ISNULL(RS.doanhThu, 0) AS doanhThu " +
                        "    FROM DateRange DATEDATE " +
                        "    LEFT JOIN RevenueData RS ON DATEDATE.ngay = RS.ngay " +
                        "), " +
                        "RankedWeeks AS ( " +
                        "    SELECT " +
                        "        ROW_NUMBER() OVER (ORDER BY startOfWeek) AS weekNumber, " +
                        "        startOfWeek, " +
                        "        SUM(doanhThu) AS totalRevenue " +
                        "    FROM WeekData " +
                        "    GROUP BY startOfWeek " +
                        ") " +
                        "SELECT " +
                        "    weekNumber, " +
                        "    totalRevenue " +
                        "FROM RankedWeeks " +
                        "ORDER BY weekNumber;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)) {
            // Set parameters for the query
            stmt.setDate(1, new Date(startDate.getTime()));
            stmt.setDate(2, new Date(endDate.getTime()));
            stmt.setDate(3, new Date(startDate.getTime()));
            stmt.setDate(4, new Date(endDate.getTime()));
            if (!maLoaiVe.isEmpty()) {
                stmt.setString(5, maLoaiVe);
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int weekNumber = rs.getInt("weekNumber");
                    double revenue = rs.getDouble("totalRevenue");
                    weeklyRevenue.put(weekNumber, revenue);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Weekly Revenue Data:");
        for (Map.Entry<Integer, Double> entry : weeklyRevenue.entrySet()) {
            System.out.println("Tuần " + entry.getKey() + ": Doanh thu = " + entry.getValue());
        }
        return weeklyRevenue;
    }
    public static Map<Integer, Double> getThangVe_NgayBatDau_NgayKetThuc(Date startDate, Date endDate, String maLoaiVe) {
        Map<Integer, Double> monthlyRevenue = new LinkedHashMap<>();
        String query = "WITH NumberTable AS (  " +
                "    SELECT TOP (DATEDIFF(DAY, ?, ?) + 1)  " +
                "        ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) - 1 AS num  " +
                "    FROM master.dbo.spt_values  " +
                "),  " +
                "DateRange AS (  " +
                "    SELECT DATEADD(DAY, num, ?) AS ngay  " +
                "    FROM NumberTable  " +
                "),  " +
                "RevenueData AS (  " +
                "    SELECT  " +
                "        CONVERT(DATE, ngayGioXuatVe) AS ngay,  " +
                "        SUM(ISNULL(tongTien, 0)) AS doanhThu  " +
                "    FROM View_VeTongTien  " +
                "    WHERE CONVERT(DATE, ngayGioXuatVe) BETWEEN ? AND ?  " +
                (maLoaiVe.isEmpty() ? "" : "      AND maLoaiVe = ? ") +
                "    GROUP BY CONVERT(DATE, ngayGioXuatVe)  " +
                "),  " +
                "MonthData AS (  " +
                "    SELECT  " +
                "        DATEDATE.ngay,  " +
                "        DATEADD(DAY, 1 - DAY(DATEDATE.ngay), DATEDATE.ngay) AS startOfMonth,  " +
                "        ISNULL(RS.doanhThu, 0) AS doanhThu  " +
                "    FROM DateRange DATEDATE  " +
                "    LEFT JOIN RevenueData RS ON DATEDATE.ngay = RS.ngay  " +
                "),  " +
                "RankedMonths AS (  " +
                "    SELECT  " +
                "        ROW_NUMBER() OVER (ORDER BY startOfMonth) AS monthNumber,  " +
                "        startOfMonth,  " +
                "        SUM(doanhThu) AS totalRevenue  " +
                "    FROM MonthData  " +
                "    GROUP BY startOfMonth  " +
                ")  " +
                "SELECT  " +
                "    monthNumber,  " +
                "    totalRevenue  " +
                "FROM RankedMonths  " +
                "ORDER BY monthNumber;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)) {
            // Gán giá trị tham số
            stmt.setDate(1, new Date(startDate.getTime()));
            stmt.setDate(2, new Date(endDate.getTime()));
            stmt.setDate(3, new Date(startDate.getTime()));
            stmt.setDate(4, new Date(startDate.getTime()));
            stmt.setDate(5, new Date(endDate.getTime()));
            if (!maLoaiVe.isEmpty()) {
                stmt.setString(6, maLoaiVe);
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int monthNumber = rs.getInt("monthNumber");
                    double totalRevenue = rs.getDouble("totalRevenue");
                    monthlyRevenue.put(monthNumber, totalRevenue);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return monthlyRevenue;
    }
    public static Map<Integer, Double> getQuarterlyRevenue(Date startDate, Date endDate, String maLoaiVe) {
        Map<Integer, Double> quarterlyRevenue = new LinkedHashMap<>();
        String query = "WITH NumberTable AS (  " +
                "    SELECT TOP (DATEDIFF(DAY, ?, ?) + 1)  " +
                "        ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) - 1 AS num  " +
                "    FROM master.dbo.spt_values  " +
                "),  " +
                "DateRange AS (  " +
                "    SELECT DATEADD(DAY, num, ?) AS ngay  " +
                "    FROM NumberTable  " +
                "),  " +
                "RevenueData AS (  " +
                "    SELECT  " +
                "        CONVERT(DATE, ngayGioXuatVe) AS ngay,  " +
                "        SUM(ISNULL(tongTien, 0)) AS doanhThu  " +
                "    FROM View_VeTongTien  " +
                "    WHERE CONVERT(DATE, ngayGioXuatVe) BETWEEN ? AND ?  " +
                (maLoaiVe.isEmpty() ? "" : "      AND maLoaiVe = ? ") +
                "    GROUP BY CONVERT(DATE, ngayGioXuatVe)  " +
                "),  " +
                "QuarterData AS (  " +
                "    SELECT  " +
                "        DATEDATE.ngay,  " +
                "        DATEPART(YEAR, DATEDATE.ngay) AS year,  " +
                "        ((DATEPART(MONTH, DATEDATE.ngay) - 1) / 3) + 1 AS quarter,  " +
                "        ISNULL(RS.doanhThu, 0) AS doanhThu  " +
                "    FROM DateRange DATEDATE  " +
                "    LEFT JOIN RevenueData RS ON DATEDATE.ngay = RS.ngay  " +
                "),  " +
                "RankedQuarters AS (  " +
                "    SELECT  " +
                "        year,  " +
                "        quarter,  " +
                "        SUM(doanhThu) AS totalRevenue  " +
                "    FROM QuarterData  " +
                "    GROUP BY year, quarter  " +
                ")  " +
                "SELECT  " +
                "    (year * 10 + quarter) AS quarterNumber,  " +
                "    totalRevenue  " +
                "FROM RankedQuarters  " +
                "ORDER BY year, quarter;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)) {
            // Gán giá trị tham số
            stmt.setDate(1, new Date(startDate.getTime()));
            stmt.setDate(2, new Date(endDate.getTime()));
            stmt.setDate(3, new Date(startDate.getTime()));
            stmt.setDate(4, new Date(startDate.getTime()));
            stmt.setDate(5, new Date(endDate.getTime()));
            if (!maLoaiVe.isEmpty()) {
                stmt.setString(6, maLoaiVe);
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int quarterNumber = rs.getInt("quarterNumber"); // VD: 20241 (Quý 1 năm 2024)
                    double totalRevenue = rs.getDouble("totalRevenue");
                    quarterlyRevenue.put(quarterNumber, totalRevenue);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quarterlyRevenue;
    }
    public static Map<Integer, Double> getYearlyRevenue(Date startDate, Date endDate, String maLoaiVe) {
        Map<Integer, Double> yearlyRevenue = new LinkedHashMap<>();
        String query = "WITH NumberTable AS (  " +
                "    SELECT TOP (DATEDIFF(DAY, ?, ?) + 1)  " +
                "        ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) - 1 AS num  " +
                "    FROM master.dbo.spt_values  " +
                "),  " +
                "DateRange AS (  " +
                "    SELECT DATEADD(DAY, num, ?) AS ngay  " +
                "    FROM NumberTable  " +
                "),  " +
                "RevenueData AS (  " +
                "    SELECT  " +
                "        CONVERT(DATE, ngayGioXuatVe) AS ngay,  " +
                "        SUM(ISNULL(tongTien, 0)) AS doanhThu  " +
                "    FROM View_VeTongTien  " +
                "    WHERE CONVERT(DATE, ngayGioXuatVe) BETWEEN ? AND ?  " +
                (maLoaiVe.isEmpty() ? "" : "      AND maLoaiVe = ? ") +
                "    GROUP BY CONVERT(DATE, ngayGioXuatVe)  " +
                "),  " +
                "YearData AS (  " +
                "    SELECT  " +
                "        DATEPART(YEAR, DATEDATE.ngay) AS year,  " +
                "        ISNULL(RS.doanhThu, 0) AS doanhThu  " +
                "    FROM DateRange DATEDATE  " +
                "    LEFT JOIN RevenueData RS ON DATEDATE.ngay = RS.ngay  " +
                "),  " +
                "RankedYears AS (  " +
                "    SELECT  " +
                "        year,  " +
                "        SUM(doanhThu) AS totalRevenue  " +
                "    FROM YearData  " +
                "    GROUP BY year  " +
                ")  " +
                "SELECT  " +
                "    year,  " +
                "    totalRevenue  " +
                "FROM RankedYears  " +
                "ORDER BY year;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)) {
            // Gán giá trị tham số
            stmt.setDate(1, new Date(startDate.getTime()));
            stmt.setDate(2, new Date(endDate.getTime()));
            stmt.setDate(3, new Date(startDate.getTime()));
            stmt.setDate(4, new Date(startDate.getTime()));
            stmt.setDate(5, new Date(endDate.getTime()));
            if (!maLoaiVe.isEmpty()) {
                stmt.setString(6, maLoaiVe);
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int year = rs.getInt("year");
                    double totalRevenue = rs.getDouble("totalRevenue");
                    yearlyRevenue.put(year, totalRevenue);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return yearlyRevenue;
    }
    public static Map<Integer, Double> getWeeklyRevenue() {
        Map<Integer, Double> weeklyRevenue = new LinkedHashMap<>();

        String query = "WITH RevenueData AS (\n" +
                "    SELECT \n" +
                "        DATEPART(WEEK, ngayGioXuatVe) - DATEPART(WEEK, DATEADD(MONTH, DATEDIFF(MONTH, 0, ngayGioXuatVe), 0)) + 1 AS weekInMonth,\n" +
                "        SUM(ISNULL(tongTien, 0)) AS totalRevenue\n" +
                "    FROM View_VeTongTien\n" +
                "    WHERE ngayGioXuatVe >= DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()), 0) \n" +
                "      AND ngayGioXuatVe < DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()) + 1, 0)\n" +
                "    GROUP BY DATEPART(WEEK, ngayGioXuatVe) - DATEPART(WEEK, DATEADD(MONTH, DATEDIFF(MONTH, 0, ngayGioXuatVe), 0)) + 1\n" +
                ")\n" +
                "SELECT \n" +
                "    ISNULL(weekInMonth, weeksInMonth) AS weekNumber,\n" +
                "    ISNULL(totalRevenue, 0) AS totalRevenue\n" +
                "FROM (\n" +
                "    SELECT TOP (5) ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS weeksInMonth\n" +
                "    FROM master.dbo.spt_values\n" +
                ") AS AllWeeks\n" +
                "LEFT JOIN RevenueData ON weeksInMonth = weekInMonth\n" +
                "ORDER BY weekNumber;\n";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int weekNumber = rs.getInt("weekNumber");
                double revenue = rs.getDouble("totalRevenue");
                weeklyRevenue.put(weekNumber, revenue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return weeklyRevenue;
    }
    public static Map<String, Double> getYearlyQuarterRevenue(int year) {
        Map<String, Double> quarterlyRevenue = new LinkedHashMap<>();

        // Khởi tạo các quý với giá trị mặc định là 0
        quarterlyRevenue.put("Quý 1", 0.0);
        quarterlyRevenue.put("Quý 2", 0.0);
        quarterlyRevenue.put("Quý 3", 0.0);
        quarterlyRevenue.put("Quý 4", 0.0);

        String query = "SELECT " +
                "  CASE " +
                "    WHEN MONTH(ngayGioXuatVe) BETWEEN 1 AND 3 THEN 'Quý 1' " +
                "    WHEN MONTH(ngayGioXuatVe) BETWEEN 4 AND 6 THEN 'Quý 2' " +
                "    WHEN MONTH(ngayGioXuatVe) BETWEEN 7 AND 9 THEN 'Quý 3' " +
                "    WHEN MONTH(ngayGioXuatVe) BETWEEN 10 AND 12 THEN 'Quý 4' " +
                "  END AS quarter, " +
                "  SUM(ISNULL(tongTien, 0)) AS doanhThu " +
                "FROM View_VeTongTien " +
                "WHERE YEAR(ngayGioXuatVe) = ? " +
                "GROUP BY CASE " +
                "  WHEN MONTH(ngayGioXuatVe) BETWEEN 1 AND 3 THEN 'Quý 1' " +
                "  WHEN MONTH(ngayGioXuatVe) BETWEEN 4 AND 6 THEN 'Quý 2' " +
                "  WHEN MONTH(ngayGioXuatVe) BETWEEN 7 AND 9 THEN 'Quý 3' " +
                "  WHEN MONTH(ngayGioXuatVe) BETWEEN 10 AND 12 THEN 'Quý 4' " +
                "END " +
                "ORDER BY quarter";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)) {
            stmt.setInt(1, year);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String quarter = rs.getString("quarter");
                    double revenue = rs.getDouble("doanhThu");
                    quarterlyRevenue.put(quarter, revenue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return quarterlyRevenue;
    }
    public static Map<String, Double> getMonthlyRevenue(int year) {
        Map<String, Double> monthlyRevenue = new LinkedHashMap<>();

        // Dựng bản đồ với tất cả 12 tháng mặc định là 0
        for (int month = 1; month <= 12; month++) {
            String monthKey = String.format("%02d", month); // Biểu diễn tháng với định dạng hai chữ số
            monthlyRevenue.put(monthKey, 0.0);
        }

        String query = "WITH MonthlyRevenue AS (\n" +
                "    SELECT \n" +
                "        MONTH(ngayGioXuatVe) AS thang,\n" +
                "        SUM(ISNULL(tongTien, 0)) AS doanhThu\n" +
                "    FROM View_VeTongTien\n" +
                "    WHERE YEAR(ngayGioXuatVe) = ?\n" +
                "    GROUP BY MONTH(ngayGioXuatVe)\n" +
                ") \n" +
                "SELECT \n" +
                "    thang,\n" +
                "    ISNULL(doanhThu, 0) AS doanhThu\n" +
                "FROM MonthlyRevenue;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)) {
            stmt.setInt(1, year);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String monthKey = String.format("%02d", rs.getInt("thang")); // Biểu diễn tháng dưới dạng số
                    double revenue = rs.getDouble("doanhThu");
                    monthlyRevenue.put(monthKey, revenue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return monthlyRevenue;
    }
    public static Object[] getThongKeLoaiVe(Date startDate, Date endDate, String maLoaiVe) {
        String sql = "SELECT \n" +
                "    vt.maLoaiVe, \n" +
                "    SUM(vt.tongTien) AS totalRevenue, \n" +
                "    COUNT(*) AS totalTickets\n" +
                "FROM \n" +
                "    [dbo].[View_VeTongTien] vt\n" +
                "WHERE \n" +
                "    vt.ngayGioXuatVe BETWEEN ? AND ?\n" +
                "\tand \n" +
                "\tvt.maLoaiVe = ?\n" +
                "GROUP BY \n" +
                "    vt.maLoaiVe;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setDate(1, new Date(startDate.getTime()));

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(endDate);
            calendar.add(Calendar.DATE, 1); // Cộng thêm 1 ngày
            stmt.setDate(2, new Date(calendar.getTimeInMillis()));

            stmt.setString(3, maLoaiVe);
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    Object[] row = new Object[3];
                    row[0] = resultSet.getString("maLoaiVe");   // Mã loại vé
                    row[1] = resultSet.getInt("totalRevenue");  // Tổng doanh thu
                    row[2] = resultSet.getInt("totalTickets");  // Tổng số vé bán
                    return row;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<Object[]> getChiTietVeTheoNgay(String maLoaiVe, Date startDate, Date endDate) {
        List<Object[]> danhSachChiTietVe = new ArrayList<>();
        String query = "SELECT " +
                "    CONVERT(DATE, ngayGioXuatVe) AS ngay, " +
                "    maLoaiVe, " +
                "    COUNT(*) AS soVeBanDuoc, " +
                "    SUM(tongTien) AS tongTien " +
                "FROM View_VeTongTien " +
                "WHERE CONVERT(DATE, ngayGioXuatVe) BETWEEN ? AND ? " +
                (maLoaiVe != null && !maLoaiVe.isEmpty() ? " AND maLoaiVe = ? " : "") +
                "GROUP BY CONVERT(DATE, ngayGioXuatVe), maLoaiVe " +
                "ORDER BY CONVERT(DATE, ngayGioXuatVe)";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)){
            stmt.setDate(1, startDate);
            stmt.setDate(2, endDate);
            if (maLoaiVe != null && !maLoaiVe.isEmpty()) {
                stmt.setString(3, maLoaiVe);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[4]; // Mỗi mảng đại diện một dòng dữ liệu
                    row[0] = rs.getDate("ngay"); // Cột "Ngày"
                    row[1] = rs.getString("maLoaiVe"); // Cột "Loại vé"
                    row[2] = rs.getInt("soVeBanDuoc"); // Cột "Số vé bán được"
                    row[3] = rs.getDouble("tongTien"); // Cột "Tổng tiền"

                    danhSachChiTietVe.add(row); // Thêm dòng vào danh sách
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachChiTietVe;
    }
    //// NHAN VIEN
    public static Object[] getThongKeTheoNhanVien(Date startDate, Date endDate, String maNhanVien) {
        String sql = "SELECT " +
                "    nv.tenNV, " +
                "    SUM(vt.tongTien) AS totalRevenue, " +
                "    COUNT(*) AS totalTickets " +
                "FROM " +
                "    [dbo].[View_VeTongTien] vt " +
                "JOIN " +
                "    [dbo].[HoaDon] hd on hd.maHD = vt.maHoaDon " +
                "JOIN " +
                "    [dbo].[NhanVien] nv ON hd.maNhanVien = nv.maNV " +
                "WHERE " +
                "    vt.ngayGioXuatVe BETWEEN ? AND ? " +
                "    AND hd.maNhanVien = ? " +
                "GROUP BY " +
                "    nv.tenNV;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)){
            // Cài đặt tham số
            stmt.setDate(1, new Date(startDate.getTime()));
            stmt.setDate(2, new Date(endDate.getTime()));
            stmt.setString(3, maNhanVien);

            // Thực thi truy vấn và nhận kết quả
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    Object[] row = new Object[3];
                    row[0] = resultSet.getString("tenNV");   // Tên nhân viên
                    row[1] = resultSet.getDouble("totalRevenue"); // Tổng doanh thu
                    row[2] = resultSet.getInt("totalTickets");   // Tổng số vé bán
                    return row; // Trả về kết quả
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi nếu có
        }
        return null; // Trả về null nếu không có kết quả
    }
    public static List<Object[]> getThongKeForAllNhanVien(Date startDate, Date endDate) {
        String sql = "SELECT " +
                "    nv.tenNV, " +
                "    SUM(vt.tongTien) AS totalRevenue, " +
                "    COUNT(*) AS totalTickets " +
                "FROM " +
                "    [dbo].[View_VeTongTien] vt " +
                "JOIN " +
                "    [dbo].[HoaDon] hd on hd.maHD = vt.maHoaDon " +
                "JOIN " +
                "    [dbo].[NhanVien] nv ON hd.maNhanVien = nv.maNV " +
                "WHERE " +
                "    vt.ngayGioXuatVe BETWEEN ? AND ? " +
                "GROUP BY " +
                "    nv.tenNV;";

        List<Object[]> resultList = new ArrayList<>();
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)){

            stmt.setDate(1, new Date(startDate.getTime()));
            stmt.setDate(2, new Date(endDate.getTime()));

            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    Object[] row = new Object[3];
                    row[0] = resultSet.getString("tenNV");    // Tên nhân viên
                    row[1] = resultSet.getDouble("totalRevenue"); // Tổng doanh thu
                    row[2] = resultSet.getInt("totalTickets");   // Tổng số vé bán
                    resultList.add(row); // Thêm vào danh sách kết quả
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList; // Trả về danh sách các kết quả
    }
    public static Map<LocalDate, Map<String, Double>> getDoanhThuTheoNgay_VaNhanVien(
            Date startDate, Date endDate, String maNhanVien) {

        Map<LocalDate, Map<String, Double>> revenueData = new LinkedHashMap<>();
        String query = "WITH DateRange AS (\n" +
                "    SELECT CAST(? AS DATE) AS ngay\n" +
                "    UNION ALL\n" +
                "    SELECT DATEADD(DAY, 1, ngay)\n" +
                "    FROM DateRange\n" +
                "    WHERE ngay <= ?\n" +
                "),\n" +
                "RevenueData AS (\n" +
                "    SELECT \n" +
                "        CONVERT(DATE, ngayGioXuatVe) AS ngay,\n" +
                "        SUM(ISNULL(tongTien, 0)) AS tongDoanhThu,\n" +
                "        SUM(CASE WHEN hd.maNhanVien = ? THEN ISNULL(tongTien, 0) ELSE 0 END) AS doanhThuNhanVien\n" +
                "    FROM View_VeTongTien vt\n" +
                "\tjoin HoaDon hd on vt.maHoaDon = hd.maHD \n" +
                "    WHERE CONVERT(DATE, ngayGioXuatVe) BETWEEN ? AND ?\n" +
                "    GROUP BY CONVERT(DATE, ngayGioXuatVe)\n" +
                ")\n" +
                "SELECT \n" +
                "    DATEDATE.ngay,\n" +
                "    ISNULL(RD.tongDoanhThu, 0) AS tongDoanhThu,\n" +
                "    ISNULL(RD.doanhThuNhanVien, 0) AS doanhThuNhanVien\n" +
                "FROM DateRange DATEDATE\n" +
                "LEFT JOIN RevenueData RD ON DATEDATE.ngay = RD.ngay\n" +
                "ORDER BY DATEDATE.ngay\n" +
                "OPTION (MAXRECURSION 0);\n";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)) {
            stmt.setDate(1, new Date(startDate.getTime()));
            stmt.setDate(2, new Date(endDate.getTime()));
            stmt.setString(3, maNhanVien);
            stmt.setDate(4, new Date(startDate.getTime()));
            stmt.setDate(5, new Date(endDate.getTime()));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    LocalDate date = rs.getDate("ngay").toLocalDate();
                    double totalRevenue = rs.getDouble("tongDoanhThu");
                    double employeeRevenue = rs.getDouble("doanhThuNhanVien");

                    Map<String, Double> dayData = new LinkedHashMap<>();
                    dayData.put("TongDoanhThu", totalRevenue);
                    dayData.put("DoanhThuNhanVien", employeeRevenue);

                    revenueData.put(date, dayData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return revenueData;
    }
    public static Map<Integer, Map<String, Double>> getDoanhThuTheoTuan_VaNhanVien(
            Date startDate, Date endDate, String maNhanVien) {

        Map<Integer, Map<String, Double>> weeklyRevenueData = new LinkedHashMap<>();
        String query = "WITH DateRange AS (\n" +
                "    SELECT CAST(? AS DATE) AS ngay\n" +
                "    UNION ALL\n" +
                "    SELECT DATEADD(DAY, 1, ngay)\n" +
                "    FROM DateRange\n" +
                "    WHERE ngay <= ?\n" +
                "),\n" +
                "RevenueData AS (\n" +
                "    SELECT \n" +
                "        CONVERT(DATE, ngayGioXuatVe) AS ngay,\n" +
                "        SUM(ISNULL(tongTien, 0)) AS tongDoanhThu,\n" +
                "        SUM(CASE WHEN hd.maNhanVien = ? THEN ISNULL(tongTien, 0) ELSE 0 END) AS doanhThuNhanVien\n" +
                "    FROM View_VeTongTien vt\n" +
                "    JOIN HoaDon hd ON vt.maHoaDon = hd.maHD \n" +
                "    WHERE CONVERT(DATE, ngayGioXuatVe) BETWEEN ? AND ?\n" +
                "    GROUP BY CONVERT(DATE, ngayGioXuatVe)\n" +
                "),\n" +
                "WeekData AS (\n" +
                "    SELECT \n" +
                "        DATEADD(DAY, -DATEPART(WEEKDAY, DATEDATE.ngay) + 1, DATEDATE.ngay) AS startOfWeek,\n" +
                "        ISNULL(RD.tongDoanhThu, 0) AS tongDoanhThu,\n" +
                "        ISNULL(RD.doanhThuNhanVien, 0) AS doanhThuNhanVien\n" +
                "    FROM DateRange DATEDATE\n" +
                "    LEFT JOIN RevenueData RD ON DATEDATE.ngay = RD.ngay\n" +
                "),\n" +
                "RankedWeeks AS (\n" +
                "    SELECT \n" +
                "        ROW_NUMBER() OVER (ORDER BY startOfWeek) AS weekNumber,\n" +
                "        startOfWeek,\n" +
                "        SUM(tongDoanhThu) AS totalRevenue,\n" +
                "        SUM(doanhThuNhanVien) AS employeeRevenue\n" +
                "    FROM WeekData\n" +
                "    GROUP BY startOfWeek\n" +
                ")\n" +
                "SELECT \n" +
                "    weekNumber,\n" +
                "    totalRevenue,\n" +
                "    employeeRevenue\n" +
                "FROM RankedWeeks\n" +
                "ORDER BY weekNumber;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)) {
            stmt.setDate(1, new Date(startDate.getTime()));
            stmt.setDate(2, new Date(endDate.getTime()));
            stmt.setString(3, maNhanVien);
            stmt.setDate(4, new Date(startDate.getTime()));
            stmt.setDate(5, new Date(endDate.getTime()));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int weekNumber = rs.getInt("weekNumber");
                    double totalRevenue = rs.getDouble("totalRevenue");
                    double employeeRevenue = rs.getDouble("employeeRevenue");

                    Map<String, Double> weekData = new LinkedHashMap<>();
                    weekData.put("TongDoanhThu", totalRevenue);
                    weekData.put("DoanhThuNhanVien", employeeRevenue);

                    weeklyRevenueData.put(weekNumber, weekData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return weeklyRevenueData;
    }
    public static Map<Integer, Map<String, Double>> getDoanhThuTheoThang_VaNhanVien(
            Date startDate, Date endDate, String maNhanVien) {

        Map<Integer, Map<String, Double>> revenueData = new LinkedHashMap<>();
        String query = "WITH DateRange AS (\n" +
                "    SELECT CAST(? AS DATE) AS ngay\n" +
                "    UNION ALL\n" +
                "    SELECT DATEADD(DAY, 1, ngay)\n" +
                "    FROM DateRange\n" +
                "    WHERE ngay <= ?\n" +
                "),\n" +
                "RevenueData AS (\n" +
                "    SELECT \n" +
                "        MONTH(CONVERT(DATE, ngayGioXuatVe)) AS thang,\n" +
                "        YEAR(CONVERT(DATE, ngayGioXuatVe)) AS nam,\n" +
                "        SUM(ISNULL(tongTien, 0)) AS tongDoanhThu,\n" +
                "        SUM(CASE WHEN hd.maNhanVien = ? THEN ISNULL(tongTien, 0) ELSE 0 END) AS doanhThuNhanVien\n" +
                "    FROM View_VeTongTien vt\n" +
                "    JOIN HoaDon hd ON vt.maHoaDon = hd.maHD \n" +
                "    WHERE CONVERT(DATE, ngayGioXuatVe) BETWEEN ? AND ?\n" +
                "    GROUP BY YEAR(CONVERT(DATE, ngayGioXuatVe)), MONTH(CONVERT(DATE, ngayGioXuatVe))\n" +
                ")\n" +
                "SELECT \n" +
                "    RD.thang,\n" +
                "    RD.nam,\n" +
                "    ISNULL(RD.tongDoanhThu, 0) AS tongDoanhThu,\n" +
                "    ISNULL(RD.doanhThuNhanVien, 0) AS doanhThuNhanVien\n" +
                "FROM RevenueData RD\n" +
                "ORDER BY RD.nam, RD.thang;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)) {
            stmt.setDate(1, new Date(startDate.getTime()));
            stmt.setDate(2, new Date(endDate.getTime()));
            stmt.setString(3, maNhanVien);
            stmt.setDate(4, new Date(startDate.getTime()));
            stmt.setDate(5, new Date(endDate.getTime()));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int month = rs.getInt("thang");
                    double totalRevenue = rs.getDouble("tongDoanhThu");
                    double employeeRevenue = rs.getDouble("doanhThuNhanVien");

                    Map<String, Double> monthData = new LinkedHashMap<>();
                    monthData.put("TongDoanhThu", totalRevenue);
                    monthData.put("DoanhThuNhanVien", employeeRevenue);

                    revenueData.put(month, monthData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return revenueData;
    }
    public static Map<Integer, Map<String, Double>> getDoanhThuTheoQuy_VaNhanVien(
            Date startDate, Date endDate, String maNhanVien) {

        Map<Integer, Map<String, Double>> revenueData = new LinkedHashMap<>();
        String query = "WITH DateRange AS (\n" +
                "    SELECT CAST(? AS DATE) AS ngay\n" +
                "    UNION ALL\n" +
                "    SELECT DATEADD(DAY, 1, ngay)\n" +
                "    FROM DateRange\n" +
                "    WHERE ngay <= ?\n" +
                "),\n" +
                "RevenueData AS (\n" +
                "    SELECT \n" +
                "        DATEPART(QUARTER, ngayGioXuatVe) AS quy,\n" +
                "        SUM(ISNULL(tongTien, 0)) AS tongDoanhThu,\n" +
                "        SUM(CASE WHEN hd.maNhanVien = ? THEN ISNULL(tongTien, 0) ELSE 0 END) AS doanhThuNhanVien\n" +
                "    FROM View_VeTongTien vt\n" +
                "    JOIN HoaDon hd ON vt.maHoaDon = hd.maHD \n" +
                "    WHERE CONVERT(DATE, ngayGioXuatVe) BETWEEN ? AND ?\n" +
                "    GROUP BY DATEPART(QUARTER, ngayGioXuatVe)\n" +
                ")\n" +
                "SELECT \n" +
                "    DR.quy,\n" +
                "    ISNULL(RD.tongDoanhThu, 0) AS tongDoanhThu,\n" +
                "    ISNULL(RD.doanhThuNhanVien, 0) AS doanhThuNhanVien\n" +
                "FROM (SELECT DISTINCT DATEPART(QUARTER, ngay) AS quy FROM DateRange) DR\n" +
                "LEFT JOIN RevenueData RD ON DR.quy = RD.quy\n" +
                "ORDER BY DR.quy;\n";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)) {
            stmt.setDate(1, new Date(startDate.getTime()));
            stmt.setDate(2, new Date(endDate.getTime()));
            stmt.setString(3, maNhanVien);
            stmt.setDate(4, new Date(startDate.getTime()));
            stmt.setDate(5, new Date(endDate.getTime()));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int quarter = rs.getInt("quy");
                    double totalRevenue = rs.getDouble("tongDoanhThu");
                    double employeeRevenue = rs.getDouble("doanhThuNhanVien");

                    Map<String, Double> quarterData = new LinkedHashMap<>();
                    quarterData.put("TongDoanhThu", totalRevenue);
                    quarterData.put("DoanhThuNhanVien", employeeRevenue);

                    revenueData.put(quarter, quarterData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return revenueData;
    }
    public static Map<Integer, Map<String, Double>> getDoanhThuTheoNam_VaNhanVien(
            Date startDate, Date endDate, String maNhanVien) {

        Map<Integer, Map<String, Double>> revenueData = new LinkedHashMap<>();
        String query = "WITH DateRange AS (\n" +
                "    SELECT CAST(? AS DATE) AS ngay\n" +
                "    UNION ALL\n" +
                "    SELECT DATEADD(DAY, 1, ngay)\n" +
                "    FROM DateRange\n" +
                "    WHERE ngay <= ?\n" +
                "),\n" +
                "RevenueData AS (\n" +
                "    SELECT \n" +
                "        YEAR(ngayGioXuatVe) AS nam,\n" +
                "        SUM(ISNULL(tongTien, 0)) AS tongDoanhThu,\n" +
                "        SUM(CASE WHEN hd.maNhanVien = ? THEN ISNULL(tongTien, 0) ELSE 0 END) AS doanhThuNhanVien\n" +
                "    FROM View_VeTongTien vt\n" +
                "    JOIN HoaDon hd ON vt.maHoaDon = hd.maHD \n" +
                "    WHERE CONVERT(DATE, ngayGioXuatVe) BETWEEN ? AND ?\n" +
                "    GROUP BY YEAR(ngayGioXuatVe)\n" +
                ")\n" +
                "SELECT \n" +
                "    DR.nam,\n" +
                "    ISNULL(RD.tongDoanhThu, 0) AS tongDoanhThu,\n" +
                "    ISNULL(RD.doanhThuNhanVien, 0) AS doanhThuNhanVien\n" +
                "FROM (SELECT DISTINCT YEAR(ngay) AS nam FROM DateRange) DR\n" +
                "LEFT JOIN RevenueData RD ON DR.nam = RD.nam\n" +
                "ORDER BY DR.nam;\n";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)) {
            stmt.setDate(1, new Date(startDate.getTime()));
            stmt.setDate(2, new Date(endDate.getTime()));
            stmt.setString(3, maNhanVien);
            stmt.setDate(4, new Date(startDate.getTime()));
            stmt.setDate(5, new Date(endDate.getTime()));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int year = rs.getInt("nam");
                    double totalRevenue = rs.getDouble("tongDoanhThu");
                    double employeeRevenue = rs.getDouble("doanhThuNhanVien");

                    Map<String, Double> yearData = new LinkedHashMap<>();
                    yearData.put("TongDoanhThu", totalRevenue);
                    yearData.put("DoanhThuNhanVien", employeeRevenue);

                    revenueData.put(year, yearData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return revenueData;
    }
    public static ArrayList<Object[]> getDoanhThuChiTietTheoNgay() {
        ArrayList<Object[]> results = new ArrayList<>();
        String sql = "SELECT \n" +
                "    CONVERT(DATE, hd.ngayGioLapHD) AS Ngay,\n" +
                "    nv.maNV AS MaNhanVien,\n" +
                "    nv.tenNV AS HoTenNhanVien,\n" +
                "    COUNT(v.maVe) AS SoLuongVeDaBan,\n" +
                "    SUM(v.tongTien) AS TongTien\n" +
                "FROM HoaDon hd\n" +
                "INNER JOIN NhanVien nv ON hd.maNhanVien = nv.maNV\n" +
                "INNER JOIN View_VeTongTien v ON hd.maHD = v.maHoaDon\n" +
                "GROUP BY CONVERT(DATE, hd.ngayGioLapHD), nv.maNV, nv.tenNV\n" +
                "ORDER BY Ngay ASC, TongTien DESC;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[5]; // Ngày, Mã nhân viên, Họ tên, Số lượng vé đã bán, Tổng tiền
                    row[0] = rs.getDate("Ngay"); // Ngày
                    row[1] = rs.getString("MaNhanVien"); // Mã nhân viên
                    row[2] = rs.getString("HoTenNhanVien"); // Họ tên nhân viên
                    row[3] = rs.getInt("SoLuongVeDaBan"); // Số lượng vé đã bán
                    row[4] = rs.getDouble("TongTien"); // Tổng tiền
                    results.add(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
    public static ArrayList<Object[]> getDoanhThuChiTietTheoKhoangNgay(Date startDate, Date endDate) {
        ArrayList<Object[]> results = new ArrayList<>();
        String sql = "SELECT \n" +
                "    CONVERT(DATE, hd.ngayGioLapHD) AS Ngay,\n" +
                "    nv.maNV AS MaNhanVien,\n" +
                "    nv.tenNV AS HoTenNhanVien,\n" +
                "    COUNT(v.maVe) AS SoLuongVeDaBan,\n" +
                "    SUM(v.tongTien) AS TongTien\n" +
                "FROM HoaDon hd\n" +
                "INNER JOIN NhanVien nv ON hd.maNhanVien = nv.maNV\n" +
                "INNER JOIN View_VeTongTien v ON hd.maHD = v.maHoaDon\n" +
                "WHERE CONVERT(DATE, hd.ngayGioLapHD) BETWEEN ? AND ?\n" + // Lọc theo khoảng ngày
                "GROUP BY CONVERT(DATE, hd.ngayGioLapHD), nv.maNV, nv.tenNV\n" +
                "ORDER BY Ngay ASC, TongTien DESC;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            // Gán giá trị ngày bắt đầu và ngày kết thúc
            stmt.setDate(1, new Date(startDate.getTime()));
            stmt.setDate(2, new Date(endDate.getTime()));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[5]; // Ngày, Mã nhân viên, Họ tên, Số lượng vé đã bán, Tổng tiền
                    row[0] = rs.getDate("Ngay"); // Ngày
                    row[1] = rs.getString("MaNhanVien"); // Mã nhân viên
                    row[2] = rs.getString("HoTenNhanVien"); // Họ tên nhân viên
                    row[3] = rs.getInt("SoLuongVeDaBan"); // Số lượng vé đã bán
                    row[4] = rs.getDouble("TongTien"); // Tổng tiền
                    results.add(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
    public static ArrayList<Object[]> getDoanhThuNhanVienTheoNgay(Date startDate, Date endDate, String maNhanVien) {
        ArrayList<Object[]> results = new ArrayList<>();
        String sql = "SELECT " +
                "    CAST(hd.ngayGioLapHD AS DATE) AS Ngay, " +
                "    COUNT(v.maVe) AS SoLuongVe, " +
                "    SUM(v.tongTien) AS TongDoanhThu " +
                "FROM HoaDon hd " +
                "INNER JOIN View_VeTongTien v ON hd.maHD = v.maHoaDon " +
                "WHERE hd.maNhanVien = ? " +
                "AND CAST(hd.ngayGioLapHD AS DATE) BETWEEN ? AND ? " +
                "GROUP BY CAST(hd.ngayGioLapHD AS DATE) " +
                "ORDER BY Ngay ASC";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            // Gán giá trị cho các tham số trong câu SQL
            stmt.setString(1, maNhanVien);
            stmt.setDate(2, new Date(startDate.getTime()));
            stmt.setDate(3, new Date(endDate.getTime()));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Tạo một dòng dữ liệu (mảng Object[])
                    Object[] row = new Object[3];
                    row[0] = rs.getDate("Ngay"); // Ngày
                    row[1] = rs.getInt("SoLuongVe"); // Tổng số vé
                    row[2] = rs.getDouble("TongDoanhThu"); // Tổng doanh thu

                    // Thêm dòng dữ liệu vào danh sách kết quả
                    results.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }
    //KHACHHANG
    public static List<Object> getLoyaltyStats() {
        List<Object> stats = new ArrayList<>();
        String sql = "WITH FirstTransaction AS ( \n" +
                "    SELECT  \n" +
                "        v.maKhachHang, \n" +
                "        MIN(v.ngayGioXuatVe) AS ngayGiaoDichDauTien \n" +
                "    FROM Ve v \n" +
                "    GROUP BY v.maKhachHang\n" +
                "), \n" +
                "RepeatCustomers AS ( \n" +
                "    SELECT  \n" +
                "        ft.maKhachHang, \n" +
                "        COUNT(v.maKhachHang) AS soLanGiaoDich \n" +
                "    FROM FirstTransaction ft \n" +
                "    JOIN Ve v ON ft.maKhachHang = v.maKhachHang AND v.ngayGioXuatVe > ft.ngayGiaoDichDauTien \n" +
                "    GROUP BY ft.maKhachHang \n" +
                "), \n" +
                "LoyaltyStats AS ( \n" +
                "    SELECT  \n" +
                "        COUNT(DISTINCT CASE WHEN rc.soLanGiaoDich > 0 THEN rc.maKhachHang END) AS soKHQuayLai, \n" +
                "        COUNT(DISTINCT ft.maKhachHang) AS tongSoKH \n" +
                "    FROM FirstTransaction ft \n" +
                "    LEFT JOIN RepeatCustomers rc ON ft.maKhachHang = rc.maKhachHang \n" +
                ") \n" +
                "SELECT  \n" +
                "    soKHQuayLai, \n" +
                "    tongSoKH, \n" +
                "    CAST(soKHQuayLai * 100.0 / tongSoKH AS DECIMAL(10, 2)) AS tiLeQuayLai \n" +
                "FROM LoyaltyStats;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    stats.add(rs.getInt("soKHQuayLai")); // Số khách hàng quay lại
                    stats.add(rs.getInt("tongSoKH")); // Tổng số khách hàng
                    stats.add(rs.getDouble("tiLeQuayLai")); // Tỷ lệ quay lại
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stats;
    }
    public static String getOverallAverageTransactionInterval() {
        String sql = "WITH OrderedTransactions AS (\n" +
                "            SELECT \n" +
                "                maKhachHang,\n" +
                "                ngayGioLapHD,\n" +
                "                LAG(ngayGioLapHD) OVER (PARTITION BY maKhachHang ORDER BY ngayGioLapHD) AS previousTransactionDate\n" +
                "            FROM HoaDon\n" +
                "        ),\n" +
                "        TransactionIntervals AS (\n" +
                "            SELECT \n" +
                "                DATEDIFF(SECOND, previousTransactionDate, ngayGioLapHD) AS intervalSeconds\n" +
                "            FROM OrderedTransactions\n" +
                "            WHERE previousTransactionDate IS NOT NULL\n" +
                "        )\n" +
                "        SELECT \n" +
                "            CAST(AVG(intervalSeconds) AS BIGINT) AS averageIntervalSeconds\n" +
                "        FROM TransactionIntervals;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                long averageSeconds = rs.getLong("averageIntervalSeconds");

                // Convert seconds to days, hours, minutes, and seconds
                long days = averageSeconds / (24 * 3600);
                averageSeconds %= (24 * 3600);
                long hours = averageSeconds / 3600;
                averageSeconds %= 3600;
                long minutes = averageSeconds / 60;
                long seconds = averageSeconds % 60;

                // Format result as a readable string
                return String.format("%d ngày - %d giờ", days, hours);
//                return String.format("%d ngày-%d giờ-%d phút-%d giây", days, hours, minutes, seconds);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Không có dữ liệu"; // Return fallback value if no data is available
    }
    public static int getTotalCustomersByDateRange(Date startDate, Date endDate) {
        // Hàm để tính tổng số lượng khách hàng mua vé trong khoảng thời gian
        int totalCustomers = 0;
        String query = "SELECT COUNT(DISTINCT v.maKhachHang) AS tongSoLuongKhachHang " +
                "FROM Ve v " +
                "INNER JOIN HoaDon h ON v.maHoaDon = h.maHD " +
                "WHERE h.ngayGioLapHD BETWEEN ? AND ?";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)){
            // Thiết lập tham số cho câu lệnh SQL
            stmt.setDate(1, startDate);
            stmt.setDate(2, endDate);
            // Thực thi câu lệnh SQL
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    totalCustomers = rs.getInt("tongSoLuongKhachHang");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // In lỗi nếu có
        }
        return totalCustomers;
    }

    public static List<Object[]> getCustomerCountByDateRange(Date startDate, Date endDate) {
        List<Object[]> resultList = new ArrayList<>();
        // SQL query
        String query = "WITH DateRange AS ( " +
                "    SELECT CAST(? AS DATE) AS ngayGioLap " +
                "    UNION ALL " +
                "    SELECT DATEADD(DAY, 1, ngayGioLap) " +
                "    FROM DateRange " +
                "    WHERE ngayGioLap < ? " +
                ") " +
                "SELECT " +
                "    d.ngayGioLap, " +
                "    COALESCE(COUNT(DISTINCT v.maKhachHang), 0) AS soLuongKhachHang " +
                "FROM " +
                "    DateRange d " +
                "LEFT JOIN " +
                "    Ve v ON CAST(v.ngayGioXuatVe AS DATE) = d.ngayGioLap " +
                "LEFT JOIN " +
                "    HoaDon h ON v.maHoaDon = h.maHD AND h.ngayGioLapHD BETWEEN ? AND ? " +
                "GROUP BY " +
                "    d.ngayGioLap " +
                "ORDER BY " +
                "    d.ngayGioLap";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)){
            // Thiết lập tham số cho câu lệnh SQL
            stmt.setDate(1, new Date(startDate.getTime()));
            stmt.setDate(2, new Date(endDate.getTime()));
            stmt.setDate(3, new Date(startDate.getTime()));
            stmt.setDate(4, new Date(endDate.getTime()));

            // Thực thi câu lệnh SQL và lấy kết quả
            try (ResultSet rs = stmt.executeQuery()) {
                int i=1;
                while (rs.next()) {
                    Object[] row = new Object[3];
                    row[0] = rs.getDate("ngayGioLap"); // Ngày
                    row[1] = rs.getInt("soLuongKhachHang"); // Số lượng khách hàng
                    row[2] = i++;
                    resultList.add(row); // Thêm vào danh sách kết quả
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi nếu có
        }

        return resultList;
    }
    public static List<Object[]> getCustomerCountByWeek(Date startDate, Date endDate) {
        List<Object[]> resultList = new ArrayList<>();

        // SQL query
        String query = "WITH DateRange AS ( " +
                "    SELECT CAST(? AS DATE) AS ngayGioLap " +
                "    UNION ALL " +
                "    SELECT DATEADD(DAY, 1, ngayGioLap) " +
                "    FROM DateRange " +
                "    WHERE ngayGioLap < ? " +
                ") " +
                "SELECT " +
                "    DATEPART(WEEK, d.ngayGioLap) AS weekNumber, " +
                "    COALESCE(COUNT(DISTINCT v.maKhachHang), 0) AS soLuongKhachHang " +
                "FROM " +
                "    DateRange d " +
                "LEFT JOIN " +
                "    Ve v ON CAST(v.ngayGioXuatVe AS DATE) = d.ngayGioLap " +
                "LEFT JOIN " +
                "    HoaDon h ON v.maHoaDon = h.maHD AND h.ngayGioLapHD BETWEEN ? AND ? " +
                "GROUP BY " +
                "    DATEPART(WEEK, d.ngayGioLap) " +
                "ORDER BY " +
                "    weekNumber";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)){
            // Thiết lập tham số cho câu lệnh SQL
            stmt.setDate(1, new Date(startDate.getTime()));
            stmt.setDate(2, new Date(endDate.getTime()));
            stmt.setDate(3, new Date(startDate.getTime()));
            stmt.setDate(4, new Date(endDate.getTime()));

            // Thực thi câu lệnh SQL và lấy kết quả
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[2];
                    row[0] = rs.getInt("weekNumber"); // Tuần
                    row[1] = rs.getInt("soLuongKhachHang"); // Số lượng khách hàng
                    resultList.add(row); // Thêm vào danh sách kết quả
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi nếu có
        }

        return resultList;
    }
    public static List<Object[]> getCustomerCountByMonth(Date startDate, Date endDate) {
        List<Object[]> resultList = new ArrayList<>();

        String query = "WITH DateRange AS ( " +
                "    SELECT CAST(? AS DATE) AS ngayGioLap " +
                "    UNION ALL " +
                "    SELECT DATEADD(DAY, 1, ngayGioLap) " +
                "    FROM DateRange " +
                "    WHERE ngayGioLap < ? " +
                ") " +
                "SELECT " +
                "    MONTH(d.ngayGioLap) AS monthNumber, " +
                "    YEAR(d.ngayGioLap) as yearNumber, "+
                "    COALESCE(COUNT(DISTINCT v.maKhachHang), 0) AS soLuongKhachHang " +
                "FROM " +
                "    DateRange d " +
                "LEFT JOIN " +
                "    Ve v ON CAST(v.ngayGioXuatVe AS DATE) = d.ngayGioLap " +
                "LEFT JOIN " +
                "    HoaDon h ON v.maHoaDon = h.maHD AND h.ngayGioLapHD BETWEEN ? AND ? " +
                "GROUP BY " +
                "    MONTH(d.ngayGioLap), YEAR(d.ngayGioLap) " +
                "ORDER BY " +
                "    yearNumber, monthNumber";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)){

            stmt.setDate(1, new Date(startDate.getTime()));
            stmt.setDate(2, new Date(endDate.getTime()));
            stmt.setDate(3, new Date(startDate.getTime()));
            stmt.setDate(4, new Date(endDate.getTime()));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[3];
                    row[0] = rs.getInt("monthNumber"); // Tháng
                    row[1] = rs.getInt("yearNumber"); // Năm
                    row[2] = rs.getInt("soLuongKhachHang"); // Số lượng khách hàng
                    resultList.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }
    public static List<Object[]> getCustomerCountByQuarter(Date startDate, Date endDate) {
        List<Object[]> resultList = new ArrayList<>();

        String query = "WITH DateRange AS ( " +
                "    SELECT CAST(? AS DATE) AS ngayGioLap " +
                "    UNION ALL " +
                "    SELECT DATEADD(DAY, 1, ngayGioLap) " +
                "    FROM DateRange " +
                "    WHERE ngayGioLap < ? " +
                ") " +
                "SELECT " +
                "    DATEPART(QUARTER, d.ngayGioLap) AS quarterNumber, " +
                "    YEAR(d.ngayGioLap) as yearNumber,"+
                "    COALESCE(COUNT(DISTINCT v.maKhachHang), 0) AS soLuongKhachHang " +
                "FROM " +
                "    DateRange d " +
                "LEFT JOIN " +
                "    Ve v ON CAST(v.ngayGioXuatVe AS DATE) = d.ngayGioLap " +
                "LEFT JOIN " +
                "    HoaDon h ON v.maHoaDon = h.maHD AND h.ngayGioLapHD BETWEEN ? AND ? " +
                "GROUP BY " +
                "    DATEPART(QUARTER, d.ngayGioLap), YEAR(d.ngayGioLap) " +
                "ORDER BY " +
                "    yearNumber, quarterNumber";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)){

            stmt.setDate(1, new Date(startDate.getTime()));
            stmt.setDate(2, new Date(endDate.getTime()));
            stmt.setDate(3, new Date(startDate.getTime()));
            stmt.setDate(4, new Date(endDate.getTime()));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[3];
                    row[0] = rs.getInt("quarterNumber"); // Quý
                    row[1] = rs.getInt("yearNumber"); // Năm
                    row[2] = rs.getInt("soLuongKhachHang"); // Số lượng khách hàng
                    resultList.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }
    public static List<Object[]> getCustomerCountByYear(Date startDate, Date endDate) {
        List<Object[]> resultList = new ArrayList<>();

        String query = "WITH DateRange AS ( " +
                "    SELECT CAST(? AS DATE) AS ngayGioLap " +
                "    UNION ALL " +
                "    SELECT DATEADD(DAY, 1, ngayGioLap) " +
                "    FROM DateRange " +
                "    WHERE ngayGioLap < ? " +
                ") " +
                "SELECT " +
                "    YEAR(d.ngayGioLap) AS yearNumber, " +
                "    COALESCE(COUNT(DISTINCT v.maKhachHang), 0) AS soLuongKhachHang " +
                "FROM " +
                "    DateRange d " +
                "LEFT JOIN " +
                "    Ve v ON CAST(v.ngayGioXuatVe AS DATE) = d.ngayGioLap " +
                "LEFT JOIN " +
                "    HoaDon h ON v.maHoaDon = h.maHD AND h.ngayGioLapHD BETWEEN ? AND ? " +
                "GROUP BY " +
                "    YEAR(d.ngayGioLap) " +
                "ORDER BY " +
                "    YEAR(d.ngayGioLap)";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)){

            stmt.setDate(1, new Date(startDate.getTime()));
            stmt.setDate(2, new Date(endDate.getTime()));
            stmt.setDate(3, new Date(startDate.getTime()));
            stmt.setDate(4, new Date(endDate.getTime()));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[2];
                    row[0] = rs.getInt("yearNumber"); // Năm
                    row[1] = rs.getInt("soLuongKhachHang"); // Số lượng khách hàng
                    resultList.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public static List<Object[]> getTyLeKhuyenMaiTheoDoiTuong(Date startDate, Date endDate) {
        List<Object[]> resultList = new ArrayList<>();
        String query = "SELECT\n" +
                "    km.doiTuong,\n" +
                "    COUNT(v.maVe) AS SoLuongVeDuocApDung,\n" +
                "    (COUNT(v.maVe) * 100.0 / (SELECT COUNT(v2.maVe) FROM Ve v2 INNER JOIN KhuyenMai km2 ON v2.maKhuyenMai = km2.maKM WHERE v2.ngayGioXuatVe BETWEEN ? AND ? AND km2.doiTuong <> N'Người lớn')) AS TiLeApDung\n" +
                "FROM\n" +
                "    Ve v\n" +
                "INNER JOIN\n" +
                "    KhuyenMai km ON v.maKhuyenMai = km.maKM\n" +
                "WHERE v.ngayGioXuatVe BETWEEN km.ngayApDung AND km.ngayKetThuc\n" +
                "  AND v.ngayGioXuatVe BETWEEN ? AND ?\n" +
                "  AND km.doiTuong <> N'Người lớn'\n" +
                "GROUP BY\n" +
                "    km.doiTuong;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)){
            stmt.setDate(1, new Date(startDate.getTime()));
            stmt.setDate(2, new Date(endDate.getTime()));
            stmt.setDate(3, new Date(startDate.getTime()));
            stmt.setDate(4, new Date(endDate.getTime()));

            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    Object[] row = new Object[3];
                    row[0] = resultSet.getNString("doiTuong"); // Đối tượng khuyến mãi
                    row[1] = resultSet.getInt("SoLuongVeDuocApDung"); // Số lượng vé
                    row[2] = resultSet.getDouble("TiLeApDung"); // Tỉ lệ
                    resultList.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Trả về null nếu có lỗi
        }
        return resultList;
    }
    public static List<Object[]> getDoanhThuKhuyenMaiTheoDoiTuong(Date startDate, Date endDate) {
        List<Object[]> resultList = new ArrayList<>();
        String query = "SELECT km.doiTuong, SUM(lc.giaCho + lc.giaCho * v.thue - (lc.giaCho + lc.giaCho * v.thue) * ISNULL(km.phanTramKM, 0)) AS TongTienKhuyenMai, (SUM(lc.giaCho + lc.giaCho * v.thue - (lc.giaCho + lc.giaCho * v.thue) * ISNULL(km.phanTramKM, 0)) * 100.0 / (SELECT SUM(lc2.giaCho + lc2.giaCho * v2.thue - (lc2.giaCho + lc2.giaCho * v2.thue) * ISNULL(km2.phanTramKM, 0)) FROM Ve v2 INNER JOIN KhuyenMai km2 ON v2.maKhuyenMai = km2.maKM INNER JOIN ChoNgoi cn2 ON v2.maChoNgoi = cn2.maCho INNER JOIN LoaiCho lc2 ON cn2.maloaiCho = lc2.maLC WHERE km2.doiTuong <> N'Người lớn' AND v2.ngayGioXuatVe BETWEEN ? AND ?)) AS TiLeDoanhThu FROM Ve v INNER JOIN KhuyenMai km ON v.maKhuyenMai = km.maKM INNER JOIN ChoNgoi cn ON v.maChoNgoi = cn.maCho INNER JOIN LoaiCho lc ON cn.maloaiCho = lc.maLC WHERE km.doiTuong <> N'Người lớn' AND v.ngayGioXuatVe BETWEEN ? AND ? GROUP BY km.doiTuong;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)){
            stmt.setDate(1, new Date(startDate.getTime()));
            stmt.setDate(2, new Date(endDate.getTime()));
            stmt.setDate(3, new Date(startDate.getTime()));
            stmt.setDate(4, new Date(endDate.getTime()));

            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    Object[] row = new Object[3];
                    row[0] = resultSet.getNString("doiTuong");
                    row[1] = resultSet.getDouble("TongTienKhuyenMai");
                    row[2] = resultSet.getDouble("TiLeDoanhThu");
                    resultList.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi kết nối CSDL!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return resultList;
    }
    public static List<Object[]> getTopCustomers(Date startDate, Date endDate) {
        List<Object[]> result = new ArrayList<>();
        String sql = "SELECT TOP 100 kh.maKH, kh.tenKH, kh.sdt, kh.email, COUNT(*) AS so_lan_di, SUM(v.tongTien) AS tongTien \n" +
                "                 FROM View_VeTongTien v \n" +
                "                 JOIN KhachHang kh ON v.maKhachHang = kh.maKH \n" +
                "                 WHERE v.ngayGioXuatVe BETWEEN ? AND ? \n" +
                "                 GROUP BY kh.maKH, kh.tenKH, kh.sdt, kh.email\n" +
                "                 ORDER BY so_lan_di DESC";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)){
            stmt.setDate(1, new Date(startDate.getTime()));
            stmt.setDate(2, new Date(endDate.getTime()));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[]{
                            rs.getString("maKH"),
                            rs.getString("tenKH"),
                            rs.getString("sdt"),
                            rs.getString("email"),
                            rs.getInt("so_lan_di"),
                            rs.getDouble("tongTien")
                    };
                    result.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi kết nối CSDL: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return result;
    }
    public static Object[] getTiLeDoiTraVe(Date startDate, Date endDate) {
        Object[] result = new Object[4];
        String query = "WITH VeBan AS (\n" +
                "    SELECT\n" +
                "        COUNT(*) AS SoLuongVeDaBan\n" +
                "    FROM\n" +
                "        Ve\n" +
                "    WHERE\n" +
                "        trangThai = N'Đã bán'\n" +
                "        AND ngayGioXuatVe BETWEEN ? AND ?\n" +
                "),\n" +
                "VeDoi AS (\n" +
                "    SELECT\n" +
                "        COUNT(*) AS SoLuongVeDaDoi\n" +
                "    FROM\n" +
                "        Ve\n" +
                "    WHERE\n" +
                "        trangThai IN (N'Đã đổi', N'Vé được đổi')\n" +
                "        AND ngayGioXuatVe BETWEEN ? AND ?\n" +
                "),\n" +
                "VeTra AS (\n" +
                "    SELECT\n" +
                "        COUNT(*) AS SoLuongVeDaTra\n" +
                "    FROM\n" +
                "        Ve\n" +
                "    WHERE\n" +
                "        trangThai = N'Đã trả'\n" +
                "        AND ngayGioXuatVe BETWEEN ? AND ?\n" +
                ")\n" +
                "SELECT\n" +
                "    ISNULL(CAST((VeDoi.SoLuongVeDaDoi * 100.0) AS DECIMAL(5,2)) / NULLIF(VeBan.SoLuongVeDaBan, 0), 0) AS TiLeDoi,\n" +
                "    VeDoi.SoLuongVeDaDoi AS SoLuongVeDoi,\n" +
                "    ISNULL(CAST((VeTra.SoLuongVeDaTra * 100.0)AS DECIMAL(5,2)) / NULLIF(VeBan.SoLuongVeDaBan, 0), 0) AS TiLeTra,\n" +
                "    VeTra.SoLuongVeDaTra AS SoLuongVeTra\n" +
                "FROM\n" +
                "    VeBan, VeDoi, VeTra;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)){

            Date sqlStartDate = new Date(startDate.getTime());
            Date sqlEndDate = new Date(endDate.getTime());

            // Set parameters for all date ranges
            for (int i = 1; i <= 6; i+=2) {
                stmt.setDate(i, sqlStartDate);
                stmt.setDate(i + 1, sqlEndDate);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    result[0] = rs.getDouble("TiLeDoi");
                    result[1] = rs.getInt("SoLuongVeDoi");
                    result[2] = rs.getDouble("TiLeTra");
                    result[3] = rs.getInt("SoLuongVeTra");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ, ví dụ: ném ngoại lệ tùy chỉnh hoặc ghi log
            return null; // Hoặc throw new RuntimeException(e);
        }
        return result;
    }

    public static List<Object[]> getThongKeVeDoiTheoNgay(Date startDate, Date endDate) {
        List<Object[]> results = new ArrayList<>();
        String query = "WITH DateSeries AS (\n" +
                "    SELECT CAST(? AS DATE) AS DateValue\n" +
                "    UNION ALL\n" +
                "    SELECT DATEADD(day, 1, DateValue)\n" +
                "    FROM DateSeries\n" +
                "    WHERE DateValue < ?\n" +
                "),\n" +
                "VeDoiTheoNgay AS (\n" +
                "    SELECT\n" +
                "        CAST(ngayGioXuatVe AS DATE) AS NgayDoiVe,\n" +
                "        COUNT(*) AS SoLuongVeDaDoi\n" +
                "    FROM\n" +
                "        Ve\n" +
                "    WHERE\n" +
                "        trangThai IN (N'Đã đổi')\n" +
                "        AND CAST(ngayGioXuatVe AS DATE) BETWEEN ? AND ?\n" +
                "    GROUP BY\n" +
                "        CAST(ngayGioXuatVe AS DATE)\n" +
                ")\n" +
                "SELECT\n" +
                "    ds.DateValue AS Ngay,\n" +
                "    ISNULL(vdtn.SoLuongVeDaDoi, 0) AS SoLuongVeDoi\n" +
                "FROM\n" +
                "    DateSeries ds\n" +
                "JOIN \n" +
                "    VeDoiTheoNgay vdtn ON ds.DateValue = vdtn.NgayDoiVe\n" +
                "ORDER BY\n" +
                "    ds.DateValue;"; // Gọi stored procedure

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)){
            // Chuyển đổi java.util.Date sang java.sql.Date
            Date sqlStartDate = new Date(startDate.getTime());
            Date sqlEndDate = new Date(endDate.getTime());

            stmt.setDate(1, sqlStartDate); // Gán giá trị cho tham số @StartDate
            stmt.setDate(2, sqlEndDate);
            stmt.setDate(3, sqlStartDate); // Gán giá trị cho tham số @StartDate
            stmt.setDate(4, sqlEndDate);// Gán giá trị cho tham số @EndDate

            try (ResultSet rs = stmt.executeQuery()) { // Thực thi stored procedure và lấy ResultSet
                while (rs.next()) {
                    Object[] row = new Object[2]; // Tạo một mảng Object để lưu trữ dữ liệu của một hàng
                    row[0] = rs.getDate("Ngay"); // Lấy giá trị cột "Ngay" (kiểu DATE)
                    row[1] = rs.getInt("SoLuongVeDoi"); // Lấy giá trị cột "SoLuongVeDoi" (kiểu INT)
                    results.add(row); // Thêm hàng dữ liệu vào danh sách kết quả
                }
            }
        } catch (SQLException e) {
            if ("The statement terminated. The maximum recursion 100 has been exhausted before statement completion.".equals(e.getMessage())) {
                System.err.println("Lỗi: Đạt giới hạn đệ quy tối đa trong CTE. " +
                        "Vui lòng kiểm tra khoảng thời gian thống kê hoặc điều chỉnh giới hạn đệ quy trong cơ sở dữ liệu.");
            } else {
                System.err.println("Lỗi SQL: " + e.getMessage());
            }
            e.printStackTrace(); // In ra thông tin chi tiết về lỗi
            return null; // Trả về null nếu có lỗi
        }
        return results; // Trả về danh sách kết quả
    }
    public static List<Object[]> getThongKeVeDoiTheoTuan(Date startDate, Date endDate) {
        List<Object[]> results = new ArrayList<>();
        String query = "WITH DateSeries AS (\n" +
                "    SELECT DATEPART(WEEK, ?) AS Tuan\n" +
                "    UNION ALL\n" +
                "    SELECT Tuan + 1\n" +
                "    FROM DateSeries\n" +
                "    WHERE Tuan < DATEPART(WEEK, ?)\n" +
                "),\n" +
                "VeDoiTheoTuan AS (\n" +
                "    SELECT\n" +
                "        DATEPART(WEEK, ngayGioXuatVe) AS Tuan,\n" +
                "        COUNT(*) AS SoLuongVeDaDoi\n" +
                "    FROM\n" +
                "        Ve\n" +
                "    WHERE\n" +
                "        trangThai IN (N'Đã đổi')\n" +
                "        AND ngayGioXuatVe BETWEEN ? AND ?\n" +
                "    GROUP BY\n" +
                "        DATEPART(WEEK, ngayGioXuatVe)\n" +
                ")\n" +
                "SELECT\n" +
                "    ds.Tuan,\n" +
                "    ISNULL(vdtn.SoLuongVeDaDoi, 0) AS SoLuongVeDoi\n" +
                "FROM\n" +
                "    DateSeries ds\n" +
                "LEFT JOIN\n" +
                "    VeDoiTheoTuan vdtn ON ds.Tuan = vdtn.Tuan\n" +
                "ORDER BY\n" +
                "    ds.Tuan;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)){

            // Chuyển đổi java.util.Date sang java.sql.Date
            Date sqlStartDate = new Date(startDate.getTime());
            Date sqlEndDate = new Date(endDate.getTime());

            stmt.setDate(1, sqlStartDate); // Gán giá trị cho tham số @StartDate trong DATEPART(WEEK, ?)
            stmt.setDate(2, sqlEndDate); // Gán giá trị cho tham số @EndDate trong WHERE Tuần < DATEPART(WEEK, ?)
            stmt.setDate(3, sqlStartDate); // Gán giá trị cho tham số @StartDate trong BETWEEN
            stmt.setDate(4, sqlEndDate); // Gán giá trị cho tham số @EndDate trong BETWEEN

            try (ResultSet rs = stmt.executeQuery()) { // Thực thi stored procedure và lấy ResultSet
                while (rs.next()) {
                    Object[] row = new Object[2]; // Tạo một mảng Object để lưu trữ dữ liệu của một hàng
                    row[0] = rs.getInt("Tuan"); // Lấy giá trị cột "Tuần" (kiểu INT)
                    row[1] = rs.getInt("SoLuongVeDoi"); // Lấy giá trị cột "SoLuongVeDoi" (kiểu INT)
                    results.add(row); // Thêm hàng dữ liệu vào danh sách kết quả
                }
            }
        } catch (SQLException e) {
            if ("The statement terminated. The maximum recursion 100 has been exhausted before statement completion.".equals(e.getMessage())) {
                System.err.println("Lỗi: Đạt giới hạn đệ quy tối đa trong CTE. " +
                        "Vui lòng kiểm tra khoảng thời gian thống kê hoặc điều chỉnh giới hạn đệ quy trong cơ sở dữ liệu.");
            } else {
                System.err.println("Lỗi SQL: " + e.getMessage());
            }
            e.printStackTrace(); // In ra thông tin chi tiết về lỗi
            return null; // Trả về null nếu có lỗi
        }
        return results; // Trả về danh sách kết quả
    }
    public static List<Object[]> getThongKeVeDoiTheoThang(Date startDate, Date endDate) {
        List<Object[]> results = new ArrayList<>();
        String query = "WITH DateSeries AS (\n" +
                "    SELECT MONTH(?) AS Thang, YEAR(?) AS Nam\n" +
                "    UNION ALL\n" +
                "    SELECT CASE WHEN Thang = 12 THEN 1 ELSE Thang + 1 END, CASE WHEN Thang = 12 THEN Nam + 1 ELSE Nam END\n" +
                "    FROM DateSeries\n" +
                "    WHERE (Thang < MONTH(?) AND Nam <= YEAR(?)) OR Nam < YEAR(?)\n" +
                "),\n" +
                "VeDoiTheoThang AS (\n" +
                "    SELECT\n" +
                "        MONTH(ngayGioXuatVe) AS Thang,\n" +
                "        YEAR(ngayGioXuatVe) AS Nam,\n" +
                "        COUNT(*) AS SoLuongVeDaDoi\n" +
                "    FROM\n" +
                "        Ve\n" +
                "    WHERE\n" +
                "        trangThai IN (N'Đã đổi')\n" +
                "        AND ngayGioXuatVe BETWEEN ? AND ?\n" +
                "    GROUP BY\n" +
                "        MONTH(ngayGioXuatVe), YEAR(ngayGioXuatVe)\n" +
                ")\n" +
                "SELECT\n" +
                "    ds.Thang, ds.Nam,\n" +
                "    ISNULL(vdtt.SoLuongVeDaDoi, 0) AS SoLuongVeDoi\n" +
                "FROM\n" +
                "    DateSeries ds\n" +
                "LEFT JOIN\n" +
                "    VeDoiTheoThang vdtt ON ds.Thang = vdtt.Thang AND ds.Nam = vdtt.Nam\n" +
                "ORDER BY\n" +
                "    ds.Nam, ds.Thang;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)){

            Date sqlStartDate = new Date(startDate.getTime());
            Date sqlEndDate = new Date(endDate.getTime());

            stmt.setDate(1, sqlStartDate); // MONTH(@StartDate)
            stmt.setDate(2, sqlStartDate); // YEAR(@StartDate)
            stmt.setDate(3, sqlEndDate); // MONTH(@EndDate)
            stmt.setDate(4, sqlEndDate); // YEAR(@EndDate)
            stmt.setDate(5, sqlEndDate); // YEAR(@EndDate)
            stmt.setDate(6, sqlStartDate); // BETWEEN @StartDate
            stmt.setDate(7, sqlEndDate); // BETWEEN @EndDate

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[3];
                    row[0] = rs.getInt("Thang");
                    row[1] = rs.getInt("SoLuongVeDoi");
                    results.add(row);
                }
            }
        } catch (SQLException e) {
            if ("The statement terminated. The maximum recursion 100 has been exhausted before statement completion.".equals(e.getMessage())) {
                System.err.println("Lỗi: Đạt giới hạn đệ quy tối đa trong CTE. " +
                        "Vui lòng kiểm tra khoảng thời gian thống kê hoặc điều chỉnh giới hạn đệ quy trong cơ sở dữ liệu.");
            } else {
                System.err.println("Lỗi SQL: " + e.getMessage());
            }
            e.printStackTrace(); // In ra thông tin chi tiết về lỗi
            return null; // Trả về null nếu có lỗi
        }
        return results;
    }
    public static List<Object[]> getThongKeVeDoiTheoQuy(Date startDate, Date endDate) {
        List<Object[]> results = new ArrayList<>();
        String query = "WITH DateSeries AS (\n" +
                "    SELECT DATEPART(QUARTER, ?) AS Quy, YEAR(?) AS Nam\n" +
                "    UNION ALL\n" +
                "    SELECT Quy + 1, Nam\n" +
                "    FROM DateSeries\n" +
                "    WHERE (Quy < DATEPART(QUARTER, ?) AND Nam <= YEAR(?)) OR Nam < YEAR(?)\n" +
                "),\n" +
                "VeDoiTheoQuy AS (\n" +
                "    SELECT\n" +
                "        DATEPART(QUARTER, ngayGioXuatVe) AS Quy,\n" +
                "        YEAR(ngayGioXuatVe) AS Nam,\n" +
                "        COUNT(*) AS SoLuongVeDaDoi\n" +
                "    FROM\n" +
                "        Ve\n" +
                "    WHERE\n" +
                "        trangThai IN (N'Đã đổi')\n" +
                "        AND ngayGioXuatVe BETWEEN ? AND ?\n" +
                "    GROUP BY\n" +
                "        DATEPART(QUARTER, ngayGioXuatVe), YEAR(ngayGioXuatVe)\n" +
                ")\n" +
                "SELECT\n" +
                "    ds.Quy, ds.Nam,\n" +
                "    ISNULL(vdtt.SoLuongVeDaDoi, 0) AS SoLuongVeDoi\n" +
                "FROM\n" +
                "    DateSeries ds\n" +
                "LEFT JOIN\n" +
                "    VeDoiTheoQuy vdtt ON ds.Quy = vdtt.Quy AND ds.Nam = vdtt.Nam\n" +
                "ORDER BY\n" +
                "    ds.Nam, ds.Quy;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)){

            Date sqlStartDate = new Date(startDate.getTime());
            Date sqlEndDate = new Date(endDate.getTime());

            stmt.setDate(1, sqlStartDate); // DATEPART(QUARTER, ?)
            stmt.setDate(2, sqlStartDate); // YEAR(?)
            stmt.setDate(3, sqlEndDate);   // DATEPART(QUARTER, ?) trong WHERE
            stmt.setDate(4, sqlEndDate);   // YEAR(?) trong WHERE
            stmt.setDate(5, sqlEndDate);   // YEAR(?) trong WHERE
            stmt.setDate(6, sqlStartDate); // BETWEEN ?
            stmt.setDate(7, sqlEndDate);   // BETWEEN ?


            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[3];
                    row[0] = rs.getInt("Quy");
                    row[1] = rs.getInt("SoLuongVeDoi");
                    results.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return results;
    }
    public static List<Object[]> getThongKeVeDoiTheoNam(Date startDate, Date endDate) {
        List<Object[]> results = new ArrayList<>();
        String query = "WITH DateSeries AS (\n" +
                "    SELECT YEAR(?) AS Nam\n" +
                "    UNION ALL\n" +
                "    SELECT Nam + 1\n" +
                "    FROM DateSeries\n" +
                "    WHERE Nam < YEAR(?)\n" +
                "),\n" +
                "VeDoiTheoNam AS (\n" +
                "    SELECT\n" +
                "        YEAR(ngayGioXuatVe) AS Nam,\n" +
                "        COUNT(*) AS SoLuongVeDaDoi\n" +
                "    FROM\n" +
                "        Ve\n" +
                "    WHERE\n" +
                "        trangThai IN (N'Đã đổi')\n" +
                "        AND ngayGioXuatVe BETWEEN ? AND ?\n" +
                "    GROUP BY\n" +
                "        YEAR(ngayGioXuatVe)\n" +
                ")\n" +
                "SELECT\n" +
                "    ds.Nam,\n" +
                "    ISNULL(vdtn.SoLuongVeDaDoi, 0) AS SoLuongVeDoi\n" +
                "FROM\n" +
                "    DateSeries ds\n" +
                "LEFT JOIN\n" +
                "    VeDoiTheoNam vdtn ON ds.Nam = vdtn.Nam\n" +
                "ORDER BY\n" +
                "    ds.Nam;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)){

            Date sqlStartDate = new Date(startDate.getTime());
            Date sqlEndDate = new Date(endDate.getTime());

            stmt.setDate(1, sqlStartDate); // YEAR(?) trong DateSeries
            stmt.setDate(2, sqlEndDate);   // YEAR(?) trong WHERE của DateSeries
            stmt.setDate(3, sqlStartDate); // BETWEEN ?
            stmt.setDate(4, sqlEndDate);   // BETWEEN ?

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[2];
                    row[0] = rs.getInt("Nam");
                    row[1] = rs.getInt("SoLuongVeDoi");
                    results.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return results;
    }

    public static List<Object[]> getThongKeVeTraTheoNgay(Date startDate, Date endDate) {
        List<Object[]> results = new ArrayList<>();
        String query = "WITH DateSeries AS (\n" +
                "    SELECT CAST(? AS DATE) AS DateValue\n" +
                "    UNION ALL\n" +
                "    SELECT DATEADD(day, 1, DateValue)\n" +
                "    FROM DateSeries\n" +
                "    WHERE DateValue < ?\n" +
                "),\n" +
                "VeDoiTheoNgay AS (\n" +
                "    SELECT\n" +
                "        CAST(ngayGioXuatVe AS DATE) AS NgayDoiVe,\n" +
                "        COUNT(*) AS SoLuongVeDaDoi\n" +
                "    FROM\n" +
                "        Ve\n" +
                "    WHERE\n" +
                "        trangThai IN (N'Đã trả')\n" +
                "        AND CAST(ngayGioXuatVe AS DATE) BETWEEN ? AND ?\n" +
                "    GROUP BY\n" +
                "        CAST(ngayGioXuatVe AS DATE)\n" +
                ")\n" +
                "SELECT\n" +
                "    ds.DateValue AS Ngay,\n" +
                "    ISNULL(vdtn.SoLuongVeDaDoi, 0) AS SoLuongVeDoi\n" +
                "FROM\n" +
                "    DateSeries ds\n" +
                "JOIN \n" +
                "    VeDoiTheoNgay vdtn ON ds.DateValue = vdtn.NgayDoiVe\n" +
                "ORDER BY\n" +
                "    ds.DateValue;"; // Gọi stored procedure

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)){
            // Chuyển đổi java.util.Date sang java.sql.Date
            Date sqlStartDate = new Date(startDate.getTime());
            Date sqlEndDate = new Date(endDate.getTime());

            stmt.setDate(1, sqlStartDate); // Gán giá trị cho tham số @StartDate
            stmt.setDate(2, sqlEndDate);
            stmt.setDate(3, sqlStartDate); // Gán giá trị cho tham số @StartDate
            stmt.setDate(4, sqlEndDate);// Gán giá trị cho tham số @EndDate

            try (ResultSet rs = stmt.executeQuery()) { // Thực thi stored procedure và lấy ResultSet
                while (rs.next()) {
                    Object[] row = new Object[2]; // Tạo một mảng Object để lưu trữ dữ liệu của một hàng
                    row[0] = rs.getDate("Ngay"); // Lấy giá trị cột "Ngay" (kiểu DATE)
                    row[1] = rs.getInt("SoLuongVeDoi"); // Lấy giá trị cột "SoLuongVeDoi" (kiểu INT)
                    results.add(row); // Thêm hàng dữ liệu vào danh sách kết quả
                }
            }
        } catch (SQLException e) {
            if ("The statement terminated. The maximum recursion 100 has been exhausted before statement completion.".equals(e.getMessage())) {
                System.err.println("Lỗi: Đạt giới hạn đệ quy tối đa trong CTE. " +
                        "Vui lòng kiểm tra khoảng thời gian thống kê hoặc điều chỉnh giới hạn đệ quy trong cơ sở dữ liệu.");
            } else {
                System.err.println("Lỗi SQL: " + e.getMessage());
            }
            e.printStackTrace(); // In ra thông tin chi tiết về lỗi
            return null; // Trả về null nếu có lỗi
        }
        return results; // Trả về danh sách kết quả
    }
    public static List<Object[]> getThongKeVeTraTheoTuan(Date startDate, Date endDate) {
        List<Object[]> results = new ArrayList<>();
        String query = "WITH DateSeries AS (\n" +
                "    SELECT DATEPART(WEEK, ?) AS Tuan\n" +
                "    UNION ALL\n" +
                "    SELECT Tuan + 1\n" +
                "    FROM DateSeries\n" +
                "    WHERE Tuan < DATEPART(WEEK, ?)\n" +
                "),\n" +
                "VeDoiTheoTuan AS (\n" +
                "    SELECT\n" +
                "        DATEPART(WEEK, ngayGioXuatVe) AS Tuan,\n" +
                "        COUNT(*) AS SoLuongVeDaDoi\n" +
                "    FROM\n" +
                "        Ve\n" +
                "    WHERE\n" +
                "        trangThai IN (N'Đã trả')\n" +
                "        AND ngayGioXuatVe BETWEEN ? AND ?\n" +
                "    GROUP BY\n" +
                "        DATEPART(WEEK, ngayGioXuatVe)\n" +
                ")\n" +
                "SELECT\n" +
                "    ds.Tuan,\n" +
                "    ISNULL(vdtn.SoLuongVeDaDoi, 0) AS SoLuongVeDoi\n" +
                "FROM\n" +
                "    DateSeries ds\n" +
                "LEFT JOIN\n" +
                "    VeDoiTheoTuan vdtn ON ds.Tuan = vdtn.Tuan\n" +
                "ORDER BY\n" +
                "    ds.Tuan;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)){

            // Chuyển đổi java.util.Date sang java.sql.Date
            Date sqlStartDate = new Date(startDate.getTime());
            Date sqlEndDate = new Date(endDate.getTime());

            stmt.setDate(1, sqlStartDate); // Gán giá trị cho tham số @StartDate trong DATEPART(WEEK, ?)
            stmt.setDate(2, sqlEndDate); // Gán giá trị cho tham số @EndDate trong WHERE Tuần < DATEPART(WEEK, ?)
            stmt.setDate(3, sqlStartDate); // Gán giá trị cho tham số @StartDate trong BETWEEN
            stmt.setDate(4, sqlEndDate); // Gán giá trị cho tham số @EndDate trong BETWEEN

            try (ResultSet rs = stmt.executeQuery()) { // Thực thi stored procedure và lấy ResultSet
                while (rs.next()) {
                    Object[] row = new Object[2]; // Tạo một mảng Object để lưu trữ dữ liệu của một hàng
                    row[0] = rs.getInt("Tuan"); // Lấy giá trị cột "Tuần" (kiểu INT)
                    row[1] = rs.getInt("SoLuongVeDoi"); // Lấy giá trị cột "SoLuongVeDoi" (kiểu INT)
                    results.add(row); // Thêm hàng dữ liệu vào danh sách kết quả
                }
            }
        } catch (SQLException e) {
            if ("The statement terminated. The maximum recursion 100 has been exhausted before statement completion.".equals(e.getMessage())) {
                System.err.println("Lỗi: Đạt giới hạn đệ quy tối đa trong CTE. " +
                        "Vui lòng kiểm tra khoảng thời gian thống kê hoặc điều chỉnh giới hạn đệ quy trong cơ sở dữ liệu.");
            } else {
                System.err.println("Lỗi SQL: " + e.getMessage());
            }
            e.printStackTrace(); // In ra thông tin chi tiết về lỗi
            return null; // Trả về null nếu có lỗi
        }
        return results; // Trả về danh sách kết quả
    }
    public static List<Object[]> getThongKeVeTraTheoThang(Date startDate, Date endDate) {
        List<Object[]> results = new ArrayList<>();
        String query = "WITH DateSeries AS (\n" +
                "    SELECT MONTH(?) AS Thang, YEAR(?) AS Nam\n" +
                "    UNION ALL\n" +
                "    SELECT CASE WHEN Thang = 12 THEN 1 ELSE Thang + 1 END, CASE WHEN Thang = 12 THEN Nam + 1 ELSE Nam END\n" +
                "    FROM DateSeries\n" +
                "    WHERE (Thang < MONTH(?) AND Nam <= YEAR(?)) OR Nam < YEAR(?)\n" +
                "),\n" +
                "VeDoiTheoThang AS (\n" +
                "    SELECT\n" +
                "        MONTH(ngayGioXuatVe) AS Thang,\n" +
                "        YEAR(ngayGioXuatVe) AS Nam,\n" +
                "        COUNT(*) AS SoLuongVeDaDoi\n" +
                "    FROM\n" +
                "        Ve\n" +
                "    WHERE\n" +
                "        trangThai IN (N'Đã trả')\n" +
                "        AND ngayGioXuatVe BETWEEN ? AND ?\n" +
                "    GROUP BY\n" +
                "        MONTH(ngayGioXuatVe), YEAR(ngayGioXuatVe)\n" +
                ")\n" +
                "SELECT\n" +
                "    ds.Thang, ds.Nam,\n" +
                "    ISNULL(vdtt.SoLuongVeDaDoi, 0) AS SoLuongVeDoi\n" +
                "FROM\n" +
                "    DateSeries ds\n" +
                "LEFT JOIN\n" +
                "    VeDoiTheoThang vdtt ON ds.Thang = vdtt.Thang AND ds.Nam = vdtt.Nam\n" +
                "ORDER BY\n" +
                "    ds.Nam, ds.Thang;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)){

            Date sqlStartDate = new Date(startDate.getTime());
            Date sqlEndDate = new Date(endDate.getTime());

            stmt.setDate(1, sqlStartDate); // MONTH(@StartDate)
            stmt.setDate(2, sqlStartDate); // YEAR(@StartDate)
            stmt.setDate(3, sqlEndDate); // MONTH(@EndDate)
            stmt.setDate(4, sqlEndDate); // YEAR(@EndDate)
            stmt.setDate(5, sqlEndDate); // YEAR(@EndDate)
            stmt.setDate(6, sqlStartDate); // BETWEEN @StartDate
            stmt.setDate(7, sqlEndDate); // BETWEEN @EndDate

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[3];
                    row[0] = rs.getInt("Thang");
                    row[1] = rs.getInt("SoLuongVeDoi");
                    results.add(row);
                }
            }
        } catch (SQLException e) {
            if ("The statement terminated. The maximum recursion 100 has been exhausted before statement completion.".equals(e.getMessage())) {
                System.err.println("Lỗi: Đạt giới hạn đệ quy tối đa trong CTE. " +
                        "Vui lòng kiểm tra khoảng thời gian thống kê hoặc điều chỉnh giới hạn đệ quy trong cơ sở dữ liệu.");
            } else {
                System.err.println("Lỗi SQL: " + e.getMessage());
            }
            e.printStackTrace(); // In ra thông tin chi tiết về lỗi
            return null; // Trả về null nếu có lỗi
        }
        return results;
    }
    public static List<Object[]> getThongKeVeTraTheoQuy(Date startDate, Date endDate) {
        List<Object[]> results = new ArrayList<>();
        String query = "WITH DateSeries AS (\n" +
                "    SELECT DATEPART(QUARTER, ?) AS Quy, YEAR(?) AS Nam\n" +
                "    UNION ALL\n" +
                "    SELECT Quy + 1, Nam\n" +
                "    FROM DateSeries\n" +
                "    WHERE (Quy < DATEPART(QUARTER, ?) AND Nam <= YEAR(?)) OR Nam < YEAR(?)\n" +
                "),\n" +
                "VeDoiTheoQuy AS (\n" +
                "    SELECT\n" +
                "        DATEPART(QUARTER, ngayGioXuatVe) AS Quy,\n" +
                "        YEAR(ngayGioXuatVe) AS Nam,\n" +
                "        COUNT(*) AS SoLuongVeDaDoi\n" +
                "    FROM\n" +
                "        Ve\n" +
                "    WHERE\n" +
                "        trangThai IN (N'Đã trả')\n" +
                "        AND ngayGioXuatVe BETWEEN ? AND ?\n" +
                "    GROUP BY\n" +
                "        DATEPART(QUARTER, ngayGioXuatVe), YEAR(ngayGioXuatVe)\n" +
                ")\n" +
                "SELECT\n" +
                "    ds.Quy, ds.Nam,\n" +
                "    ISNULL(vdtt.SoLuongVeDaDoi, 0) AS SoLuongVeDoi\n" +
                "FROM\n" +
                "    DateSeries ds\n" +
                "LEFT JOIN\n" +
                "    VeDoiTheoQuy vdtt ON ds.Quy = vdtt.Quy AND ds.Nam = vdtt.Nam\n" +
                "ORDER BY\n" +
                "    ds.Nam, ds.Quy;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)){

            Date sqlStartDate = new Date(startDate.getTime());
            Date sqlEndDate = new Date(endDate.getTime());

            stmt.setDate(1, sqlStartDate); // DATEPART(QUARTER, ?)
            stmt.setDate(2, sqlStartDate); // YEAR(?)
            stmt.setDate(3, sqlEndDate);   // DATEPART(QUARTER, ?) trong WHERE
            stmt.setDate(4, sqlEndDate);   // YEAR(?) trong WHERE
            stmt.setDate(5, sqlEndDate);   // YEAR(?) trong WHERE
            stmt.setDate(6, sqlStartDate); // BETWEEN ?
            stmt.setDate(7, sqlEndDate);   // BETWEEN ?


            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[3];
                    row[0] = rs.getInt("Quy");
                    row[1] = rs.getInt("SoLuongVeDoi");
                    results.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return results;
    }
    public static List<Object[]> getThongKeVeTraTheoNam(Date startDate, Date endDate) {
        List<Object[]> results = new ArrayList<>();
        String query = "WITH DateSeries AS (\n" +
                "    SELECT YEAR(?) AS Nam\n" +
                "    UNION ALL\n" +
                "    SELECT Nam + 1\n" +
                "    FROM DateSeries\n" +
                "    WHERE Nam < YEAR(?)\n" +
                "),\n" +
                "VeDoiTheoNam AS (\n" +
                "    SELECT\n" +
                "        YEAR(ngayGioXuatVe) AS Nam,\n" +
                "        COUNT(*) AS SoLuongVeDaDoi\n" +
                "    FROM\n" +
                "        Ve\n" +
                "    WHERE\n" +
                "        trangThai IN (N'Đã trả')\n" +
                "        AND ngayGioXuatVe BETWEEN ? AND ?\n" +
                "    GROUP BY\n" +
                "        YEAR(ngayGioXuatVe)\n" +
                ")\n" +
                "SELECT\n" +
                "    ds.Nam,\n" +
                "    ISNULL(vdtn.SoLuongVeDaDoi, 0) AS SoLuongVeDoi\n" +
                "FROM\n" +
                "    DateSeries ds\n" +
                "LEFT JOIN\n" +
                "    VeDoiTheoNam vdtn ON ds.Nam = vdtn.Nam\n" +
                "ORDER BY\n" +
                "    ds.Nam;";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)){

            Date sqlStartDate = new Date(startDate.getTime());
            Date sqlEndDate = new Date(endDate.getTime());

            stmt.setDate(1, sqlStartDate); // YEAR(?) trong DateSeries
            stmt.setDate(2, sqlEndDate);   // YEAR(?) trong WHERE của DateSeries
            stmt.setDate(3, sqlStartDate); // BETWEEN ?
            stmt.setDate(4, sqlEndDate);   // BETWEEN ?

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[2];
                    row[0] = rs.getInt("Nam");
                    row[1] = rs.getInt("SoLuongVeDoi");
                    results.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return results;
    }

    public static List<Object> dsVeTheoNgay_MaVe_ListObject(Date ngayXuatVe, String maLoaiVe) {
        List<Object> results = new ArrayList<>();
        String query = "SELECT * FROM Ve WHERE CAST(ngayGioXuatVe AS DATE) = ? AND maLoaiVe = ?";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(query)){

            Date sqlNgayXuatVe = new Date(ngayXuatVe.getTime());
            stmt.setDate(1, sqlNgayXuatVe);
            stmt.setString(2, maLoaiVe);

            try (ResultSet rs = stmt.executeQuery()) {
                ResultSetMetaData metaData = rs.getMetaData(); // Lấy metadata để biết số cột
                int columnCount = metaData.getColumnCount();

                while (rs.next()) {
                    Object[] row = new Object[columnCount]; // Tạo mảng Object với kích thước bằng số cột
                    for (int i = 1; i <= columnCount; i++) {
                        row[i - 1] = rs.getObject(i); // Lấy giá trị của từng cột
                    }
                    results.add(row); // Thêm mảng Object vào danh sách
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return results;
    }
}
