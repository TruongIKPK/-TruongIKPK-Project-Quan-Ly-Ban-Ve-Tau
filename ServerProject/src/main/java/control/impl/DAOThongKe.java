package control.impl;

import connectDB.connectDB_1;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class DAOThongKe extends UnicastRemoteObject implements control.IDAOThongKe {
    private static EntityManager em = connectDB_1.getEntityManager();
    public DAOThongKe() throws RemoteException {
    }
    //Thanh Hien
    @Override
    public int getTongSoNhanVien()throws RemoteException {
        Query query = em.createQuery("SELECT COUNT(n) FROM NhanVien n");
        return ((Number) query.getSingleResult()).intValue();
    }
    @Override
    public List<Object[]> getNhanVienTheoChucVu()throws RemoteException {
        Query query = em.createQuery(
                "SELECT cv.tenCV, COUNT(n) " +
                        "FROM NhanVien n JOIN n.chucVu cv " +
                        "GROUP BY cv.tenCV");
        return query.getResultList();
    }
    @Override
    public List<Object[]> getDoanhThuTheoThang(int year)throws RemoteException {
        List<Object[]> results = new ArrayList<>();
        try {
            Query query = em.createNativeQuery(
                    "SELECT YEAR(ngayGioXuatVe) AS Nam, MONTH(ngayGioXuatVe) AS Thang, SUM(tongTien) AS DoanhThu " +
                            "FROM View_VeTongTien " +
                            "WHERE YEAR(ngayGioXuatVe) = :year " +
                            "GROUP BY YEAR(ngayGioXuatVe), MONTH(ngayGioXuatVe) " +
                            "ORDER BY Nam, Thang"
            );
            query.setParameter("year", year);
            results = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
    @Override
    public List<Object[]> getTopDoanhThuNhanVien()throws RemoteException {
        Query query = em.createNativeQuery(
                "SELECT ROW_NUMBER() OVER (ORDER BY SUM(v.tongTien) DESC) AS STT, " +
                        "nv.maNV, nv.tenNV, SUM(v.tongTien) AS TongDoanhThu " +
                        "FROM NhanVien nv " +
                        "INNER JOIN HoaDon hd ON nv.maNV = hd.maNV " +
                        "INNER JOIN View_VeTongTien v ON hd.maHD = v.maHD " + // SỬA Ở ĐÂY: v.maHoaDon ➔ v.maHD
                        "GROUP BY nv.maNV, nv.tenNV " +
                        "ORDER BY TongDoanhThu DESC"
        );
        return query.getResultList();
    }
    @Override
    public List<Object[]> getDoanhThuTheoNgay(int year, int month)throws RemoteException {
        Query query = em.createNativeQuery(
                "SELECT CAST(ngayGioLapHD AS DATE) AS ngay, SUM(tongTien) AS DoanhThu " +
                        "FROM View_VeTongTien " +
                        "WHERE YEAR(ngayGioLapHD) = ? AND MONTH(ngayGioLapHD) = ? " +
                        "GROUP BY CAST(ngayGioLapHD AS DATE) " +
                        "ORDER BY CAST(ngayGioLapHD AS DATE)"
        );
        query.setParameter(1, year);
        query.setParameter(2, month);
        return query.getResultList();
    }
    @Override
    public List<Object[]> getTopDoanhThuNhanVienTrongNgay()throws RemoteException {
        Query query = em.createNativeQuery(
                "SELECT ROW_NUMBER() OVER (ORDER BY SUM(v.tongTien) DESC) AS STT, " +
                        "nv.maNV, nv.tenNV, SUM(v.tongTien) AS TongDoanhThu " +
                        "FROM NhanVien nv " +
                        "INNER JOIN HoaDon hd ON nv.maNV = hd.maNV " +
                        "INNER JOIN View_VeTongTien v ON hd.maHD = v.maHD " +
                        "WHERE CAST(hd.ngayGioLapHD AS DATE) = CAST(GETDATE() AS DATE) " +
                        "GROUP BY nv.maNV, nv.tenNV " +
                        "ORDER BY TongDoanhThu DESC");
        return query.getResultList();
    }
    @Override
    public List<Object[]> getTopDoanhThuNhanVienTheoTuan()throws RemoteException {
        Query query = em.createNativeQuery(
                "SELECT ROW_NUMBER() OVER (ORDER BY SUM(v.tongTien) DESC) AS STT, " +
                        "nv.maNV, nv.tenNV, SUM(v.tongTien) AS TongDoanhThu " +
                        "FROM NhanVien nv " +
                        "INNER JOIN HoaDon hd ON nv.maNV = hd.maNV " +
                        "INNER JOIN View_VeTongTien v ON hd.maHD = v.maHD " +
                        "WHERE CAST(hd.ngayGioLapHD AS DATE) BETWEEN " +
                        "DATEADD(DAY, 1 - DATEPART(DW, GETDATE()), CAST(GETDATE() AS DATE)) " +
                        "AND CAST(GETDATE() AS DATE) " +
                        "GROUP BY nv.maNV, nv.tenNV " +
                        "ORDER BY TongDoanhThu DESC");
        return query.getResultList();
    }
    @Override
    public List<Object[]> getTopDoanhThuNhanVienTheoThang()throws RemoteException {
        Query query = em.createNativeQuery(
                "SELECT ROW_NUMBER() OVER (ORDER BY SUM(v.tongTien) DESC) AS STT, " +
                        "nv.maNV, nv.tenNV, SUM(v.tongTien) AS TongDoanhThu " +
                        "FROM NhanVien nv " +
                        "INNER JOIN HoaDon hd ON nv.maNV = hd.maNV " +
                        "INNER JOIN View_VeTongTien v ON hd.maHD = v.maHD " +
                        "WHERE MONTH(hd.ngayGioLapHD) = MONTH(GETDATE()) " +
                        "AND YEAR(hd.ngayGioLapHD) = YEAR(GETDATE()) " +
                        "GROUP BY nv.maNV, nv.tenNV " +
                        "ORDER BY TongDoanhThu DESC");
        return query.getResultList();
    }
    @Override
    public List<Object[]> getTopDoanhThuNhanVienTheoQuy()throws RemoteException {
        Query query = em.createNativeQuery(
                "SELECT ROW_NUMBER() OVER (ORDER BY SUM(v.tongTien) DESC) AS STT, " +
                        "nv.maNV, nv.tenNV, SUM(v.tongTien) AS TongDoanhThu " +
                        "FROM NhanVien nv " +
                        "INNER JOIN HoaDon hd ON nv.maNV = hd.maNV " +
                        "INNER JOIN View_VeTongTien v ON hd.maHD = v.maHD " +
                        "WHERE DATEPART(QUARTER, hd.ngayGioLapHD) = DATEPART(QUARTER, GETDATE()) " +
                        "AND YEAR(hd.ngayGioLapHD) = YEAR(GETDATE()) " +
                        "GROUP BY nv.maNV, nv.tenNV " +
                        "ORDER BY TongDoanhThu DESC");
        return query.getResultList();
    }
    @Override
    public List<Object[]> getTopDoanhThuNhanVienTheoNam() throws RemoteException{
        Query query = em.createNativeQuery(
                "SELECT ROW_NUMBER() OVER (ORDER BY SUM(v.tongTien) DESC) AS STT, " +
                        "nv.maNV, nv.tenNV, SUM(v.tongTien) AS TongDoanhThu " +
                        "FROM NhanVien nv " +
                        "INNER JOIN HoaDon hd ON nv.maNV = hd.maNV " +
                        "INNER JOIN View_VeTongTien v ON hd.maHD = v.maHD " +
                        "WHERE YEAR(hd.ngayGioLapHD) = YEAR(GETDATE()) " +
                        "GROUP BY nv.maNV, nv.tenNV " +
                        "ORDER BY TongDoanhThu DESC");
        return query.getResultList();
    }
    @Override
    public List<Object[]> getDailyRevenueChange1()throws RemoteException {
        Query query = em.createNativeQuery(
                "SELECT " +
                        "ROW_NUMBER() OVER (ORDER BY ABS(SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) = CAST(GETDATE() AS DATE) THEN 1 ELSE 0 END) " +
                        "- SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) = CAST(DATEADD(DAY, -7, GETDATE()) AS DATE) THEN 1 ELSE 0 END)) DESC) AS [Top], " +
                        "lc.maLC AS MaLC, " +
                        "lc.tenLC AS TenLoaiGhe, " +
                        "SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) = CAST(DATEADD(DAY, -7, GETDATE()) AS DATE) THEN 1 ELSE 0 END) AS TongVeLoaiGheCu, " +
                        "SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) = CAST(GETDATE() AS DATE) THEN 1 ELSE 0 END) AS TongVeLoaiGheMoi, " +
                        "SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) = CAST(GETDATE() AS DATE) THEN 1 ELSE 0 END) " +
                        "- SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) = CAST(DATEADD(DAY, -7, GETDATE()) AS DATE) THEN 1 ELSE 0 END) AS BienDong " +
                        "FROM Ve v " +
                        "JOIN ChoNgoi cn ON v.maCho = cn.maCho " +
                        "JOIN LoaiCho lc ON cn.maLC = lc.maLC " +
                        "GROUP BY lc.maLC, lc.tenLC " +
                        "ORDER BY BienDong DESC"
        );

        return query.getResultList();
    }
    @Override
    public List<Object[]> getDailyRevenueChange()throws RemoteException {
        Query query = em.createNativeQuery(
                "SELECT ROW_NUMBER() OVER (ORDER BY ABS(SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) = CAST(GETDATE() AS DATE) THEN 1 ELSE 0 END) " +
                        "- SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) = CAST(DATEADD(DAY, -7, GETDATE()) AS DATE) THEN 1 ELSE 0 END)) DESC) AS [Top], " +
                        "lc.maLC AS MaLoaiGhe, lc.tenLC AS TenLoaiGhe, " +
                        "SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) = CAST(DATEADD(DAY, -7, GETDATE()) AS DATE) THEN 1 ELSE 0 END) AS TongVeLoaiGheCu, " +
                        "SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) = CAST(GETDATE() AS DATE) THEN 1 ELSE 0 END) AS TongVeLoaiGheMoi, " +
                        "SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) = CAST(GETDATE() AS DATE) THEN 1 ELSE 0 END) " +
                        "- SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) = CAST(DATEADD(DAY, -7, GETDATE()) AS DATE) THEN 1 ELSE 0 END) AS BienDong " +
                        "FROM Ve v " +
                        "JOIN ChoNgoi cn ON v.maCho = cn.maCho " +
                        "JOIN LoaiCho lc ON cn.maLC = lc.maLC " +
                        "GROUP BY lc.maLC, lc.tenLC " +
                        "ORDER BY BienDong DESC");
        return query.getResultList();
    }
    @Override
    public List<Object[]> getWeeklyRevenueChange()throws RemoteException {
        Query query = em.createNativeQuery(
                "SELECT ROW_NUMBER() OVER (ORDER BY ABS(SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) BETWEEN CAST(DATEADD(DAY, -7, GETDATE()) AS DATE) " +
                        "AND CAST(DATEADD(DAY, -1, GETDATE()) AS DATE) THEN 1 ELSE 0 END) " +
                        "- SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) BETWEEN CAST(DATEADD(DAY, -14, GETDATE()) AS DATE) " +
                        "AND CAST(DATEADD(DAY, -8, GETDATE()) AS DATE) THEN 1 ELSE 0 END)) DESC) AS [Top], " +
                        "lc.maLC AS MaLC, lc.tenLC AS TenLoaiGhe, " +
                        "SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) BETWEEN CAST(DATEADD(DAY, -14, GETDATE()) AS DATE) " +
                        "AND CAST(DATEADD(DAY, -8, GETDATE()) AS DATE) THEN 1 ELSE 0 END) AS TongVeTuanTruoc, " +
                        "SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) BETWEEN CAST(DATEADD(DAY, -7, GETDATE()) AS DATE) " +
                        "AND CAST(DATEADD(DAY, -1, GETDATE()) AS DATE) THEN 1 ELSE 0 END) AS TongVeTuanNay, " +
                        "SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) BETWEEN CAST(DATEADD(DAY, -7, GETDATE()) AS DATE) " +
                        "AND CAST(DATEADD(DAY, -1, GETDATE()) AS DATE) THEN 1 ELSE 0 END) " +
                        "- SUM(CASE WHEN CAST(v.ngayGioXuatVe AS DATE) BETWEEN CAST(DATEADD(DAY, -14, GETDATE()) AS DATE) " +
                        "AND CAST(DATEADD(DAY, -8, GETDATE()) AS DATE) THEN 1 ELSE 0 END) AS BienDong " +
                        "FROM Ve v " +
                        "JOIN ChoNgoi cn ON v.maCho = cn.maCho " +
                        "JOIN LoaiCho lc ON cn.maLC= lc.maLC " +
                        "GROUP BY lc.maLC, lc.tenLC " +
                        "ORDER BY BienDong DESC");
        return query.getResultList();
    }
    @Override
    public List<Object[]> getMonthlyRevenueChange1()throws RemoteException {
        Query query = em.createNativeQuery(
                "SELECT ROW_NUMBER() OVER (ORDER BY ABS(SUM(CASE WHEN MONTH(v.ngayGioXuatVe) = MONTH(GETDATE()) " +
                        "AND YEAR(v.ngayGioXuatVe) = YEAR(GETDATE()) THEN 1 ELSE 0 END) " +
                        "- SUM(CASE WHEN MONTH(v.ngayGioXuatVe) = MONTH(DATEADD(MONTH, -1, GETDATE())) " +
                        "AND YEAR(v.ngayGioXuatVe) = YEAR(DATEADD(MONTH, -1, GETDATE())) THEN 1 ELSE 0 END)) DESC) AS [Top], " +
                        "lc.maLC AS MaLoaiGhe, lc.tenLC AS TenLoaiGhe, " +
                        "SUM(CASE WHEN MONTH(v.ngayGioXuatVe) = MONTH(DATEADD(MONTH, -1, GETDATE())) " +
                        "AND YEAR(v.ngayGioXuatVe) = YEAR(DATEADD(MONTH, -1, GETDATE())) THEN 1 ELSE 0 END) AS TongVeThangTruoc, " +
                        "SUM(CASE WHEN MONTH(v.ngayGioXuatVe) = MONTH(GETDATE()) " +
                        "AND YEAR(v.ngayGioXuatVe) = YEAR(GETDATE()) THEN 1 ELSE 0 END) AS TongVeThangNay, " +
                        "SUM(CASE WHEN MONTH(v.ngayGioXuatVe) = MONTH(GETDATE()) " +
                        "AND YEAR(v.ngayGioXuatVe) = YEAR(GETDATE()) THEN 1 ELSE 0 END) " +
                        "- SUM(CASE WHEN MONTH(v.ngayGioXuatVe) = MONTH(DATEADD(MONTH, -1, GETDATE())) " +
                        "AND YEAR(v.ngayGioXuatVe) = YEAR(DATEADD(MONTH, -1, GETDATE())) THEN 1 ELSE 0 END) AS BienDong " +
                        "FROM Ve v " +
                        "JOIN ChoNgoi cn ON v.maCho = cn.maCho " +
                        "JOIN LoaiCho lc ON cn.maLC = lc.maLC " +
                        "GROUP BY lc.maLC, lc.tenLC " +
                        "ORDER BY BienDong DESC");
        return query.getResultList();
    }
    @Override
    public List<Object[]> getQuarterlyRevenueChange()throws RemoteException {
        Query query = em.createNativeQuery(
                "SELECT ROW_NUMBER() OVER (ORDER BY ABS(SUM(CASE WHEN DATEPART(QUARTER, v.ngayGioXuatVe) = DATEPART(QUARTER, GETDATE()) " +
                        "AND YEAR(v.ngayGioXuatVe) = YEAR(GETDATE()) THEN 1 ELSE 0 END) " +
                        "- SUM(CASE WHEN DATEPART(QUARTER, v.ngayGioXuatVe) = DATEPART(QUARTER, DATEADD(QUARTER, -1, GETDATE())) " +
                        "AND YEAR(v.ngayGioXuatVe) = YEAR(DATEADD(QUARTER, -1, GETDATE())) THEN 1 ELSE 0 END)) DESC) AS [Top], " +
                        "lc.maLC AS MaLoaiGhe, lc.tenLC AS TenLoaiGhe, " +
                        "SUM(CASE WHEN DATEPART(QUARTER, v.ngayGioXuatVe) = DATEPART(QUARTER, DATEADD(QUARTER, -1, GETDATE())) " +
                        "AND YEAR(v.ngayGioXuatVe) = YEAR(DATEADD(QUARTER, -1, GETDATE())) THEN 1 ELSE 0 END) AS TongVeQuyTruoc, " +
                        "SUM(CASE WHEN DATEPART(QUARTER, v.ngayGioXuatVe) = DATEPART(QUARTER, GETDATE()) " +
                        "AND YEAR(v.ngayGioXuatVe) = YEAR(GETDATE()) THEN 1 ELSE 0 END) AS TongVeQuyNay, " +
                        "SUM(CASE WHEN DATEPART(QUARTER, v.ngayGioXuatVe) = DATEPART(QUARTER, GETDATE()) " +
                        "AND YEAR(v.ngayGioXuatVe) = YEAR(GETDATE()) THEN 1 ELSE 0 END) " +
                        "- SUM(CASE WHEN DATEPART(QUARTER, v.ngayGioXuatVe) = DATEPART(QUARTER, DATEADD(QUARTER, -1, GETDATE())) " +
                        "AND YEAR(v.ngayGioXuatVe) = YEAR(DATEADD(QUARTER, -1, GETDATE())) THEN 1 ELSE 0 END) AS BienDong " +
                        "FROM Ve v " +
                        "JOIN ChoNgoi cn ON v.maCho = cn.maCho " +
                        "JOIN LoaiCho lc ON cn.maLC = lc.maLC " +
                        "GROUP BY lc.maLC, lc.tenLC " +
                        "ORDER BY BienDong DESC");
        return query.getResultList();
    }
    @Override
    public List<Object[]> getYearlyRevenueChange()throws RemoteException {
        Query query = em.createNativeQuery(
                "SELECT ROW_NUMBER() OVER (ORDER BY ABS(SUM(CASE WHEN YEAR(v.ngayGioXuatVe) = YEAR(GETDATE()) THEN 1 ELSE 0 END) " +
                        "- SUM(CASE WHEN YEAR(v.ngayGioXuatVe) = YEAR(GETDATE()) - 1 THEN 1 ELSE 0 END)) DESC) AS [Top], " +
                        "lc.maLC AS MaLoaiGhe, lc.tenLC AS TenLoaiGhe, " +
                        "SUM(CASE WHEN YEAR(v.ngayGioXuatVe) = YEAR(GETDATE()) - 1 THEN 1 ELSE 0 END) AS TongVeNamTruoc, " +
                        "SUM(CASE WHEN YEAR(v.ngayGioXuatVe) = YEAR(GETDATE()) THEN 1 ELSE 0 END) AS TongVeNamNay, " +
                        "SUM(CASE WHEN YEAR(v.ngayGioXuatVe) = YEAR(GETDATE()) THEN 1 ELSE 0 END) - " +
                        "SUM(CASE WHEN YEAR(v.ngayGioXuatVe) = YEAR(GETDATE()) - 1 THEN 1 ELSE 0 END) AS BienDong " +
                        "FROM Ve v " +
                        "JOIN ChoNgoi cn ON v.maCho = cn.maCho " +
                        "JOIN LoaiCho lc ON cn.maLC = lc.maLC " +
                        "GROUP BY lc.maLC, lc.tenLC " +
                        "ORDER BY BienDong DESC");
        return query.getResultList();
    }

    //    Trường
    @Override
    public Object[] getDoanhThuVaSoVeTrongKhoangThoiGian(Date startDate, Date endDate)throws RemoteException {
    int totalRevenue = 0;
    int totalTickets = 0;
    try {
        // Cộng thêm 1 ngày vào endDate để bao phủ toàn bộ ngày cuối
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.DATE, 1);
        Date adjustedEndDate = new Date(calendar.getTimeInMillis());
        String sql = "SELECT COALESCE(SUM(tongTien), 0), COUNT(*) FROM View_VeTongTien " +
                "WHERE ngayGioXuatVe BETWEEN ? AND ?";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, new java.sql.Date(startDate.getTime()));
        query.setParameter(2, new java.sql.Date(adjustedEndDate.getTime()));

        Object[] result = (Object[]) query.getSingleResult();
        if (result != null) {
            totalRevenue = ((Number) result[0]).intValue();
            totalTickets = ((Number) result[1]).intValue();
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return new Object[]{totalRevenue, totalTickets};
}
    @Override
    public Map<Integer, Double> getRevenueByHour()throws RemoteException {
        String query = "WITH HourList AS (\n" +
                "    SELECT 7 AS gio\n" +
                "    UNION ALL\n" +
                "    SELECT gio + 1 FROM HourList WHERE gio < 17\n" +
                ")\n" +
                "SELECT \n" +
                "    h.gio,\n" +
                "    ISNULL(SUM(v.tongTien), 0) AS doanhThu\n" +
                "FROM \n" +
                "    HourList h\n" +
                "LEFT JOIN \n" +
                "    View_VeTongTien v\n" +
                "    ON DATEPART(HOUR, v.ngayGioXuatVe) = h.gio\n" +
                "    AND CONVERT(DATE, v.ngayGioXuatVe) = CONVERT(DATE, GETDATE())\n" +
                "GROUP BY \n" +
                "    h.gio\n" +
                "ORDER BY \n" +
                "    h.gio;";

        Map<Integer, Double> revenueByHour = new TreeMap<>();

        try {
            List<Object[]> results = em.createNativeQuery(query).getResultList();
            for (Object[] row : results) {
                Integer hour = ((Number) row[0]).intValue();
                Double revenue = ((Number) row[1]).doubleValue();
                revenueByHour.put(hour, revenue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return revenueByHour;
    }
    @Override
    public Map<LocalDate, Double> getCurrentWeekRevenue()throws RemoteException {
    Map<LocalDate, Double> dailyRevenue = new LinkedHashMap<>();
    LocalDate today = LocalDate.now();
    LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
    LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);

    String query = "WITH DateRange AS (\n" +
            "    SELECT CAST(:startDate AS DATE) AS ngay\n" +
            "    UNION ALL\n" +
            "    SELECT DATEADD(DAY, 1, ngay)\n" +
            "    FROM DateRange\n" +
            "    WHERE ngay < :endDate\n" +
            "),\n" +
            "RevenueData AS (\n" +
            "    SELECT \n" +
            "        CONVERT(DATE, ngayGioXuatVe) AS ngay,\n" +
            "        SUM(ISNULL(tongTien, 0)) AS doanhThu\n" +
            "    FROM View_VeTongTien\n" +
            "    WHERE CONVERT(DATE, ngayGioXuatVe) BETWEEN :startDate2 AND :endDate2\n" +
            "    GROUP BY CONVERT(DATE, ngayGioXuatVe)\n" +
            ")\n" +
            "SELECT \n" +
            "    DATEDATE.ngay,\n" +
            "    ISNULL(RS.doanhThu, 0) AS doanhThu\n" +
            "FROM DateRange DATEDATE\n" +
            "LEFT JOIN RevenueData RS ON DATEDATE.ngay = RS.ngay\n" +
            "ORDER BY DATEDATE.ngay\n" +
            "OPTION (MAXRECURSION 0);";

    try {
        List<Object[]> result = em.createNativeQuery(query)
                .setParameter("startDate", java.sql.Date.valueOf(startOfWeek))
                .setParameter("endDate", java.sql.Date.valueOf(endOfWeek))
                .setParameter("startDate2", java.sql.Date.valueOf(startOfWeek))
                .setParameter("endDate2", java.sql.Date.valueOf(endOfWeek))
                .getResultList();

        for (Object[] row : result) {
            LocalDate date = ((java.sql.Date) row[0]).toLocalDate();
            Double revenue = ((Number) row[1]).doubleValue();
            dailyRevenue.put(date, revenue);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return dailyRevenue;
}
    @Override
    public Map<LocalDate, Double> getDoanhThuTheoNgay_TuNgay_DenNgay(Date startOfWeek, Date endOfWeek, String maLoaiVe)throws RemoteException {
        Map<LocalDate, Double> dailyRevenue = new LinkedHashMap<>();

        // Xây dựng query động tùy theo có mã loại vé hay không
        String query = "WITH DateRange AS (\n" +
                "    SELECT CAST(:startDate AS DATE) AS ngay\n" +
                "    UNION ALL\n" +
                "    SELECT DATEADD(DAY, 1, ngay)\n" +
                "    FROM DateRange\n" +
                "    WHERE ngay < :endDate\n" +
                "),\n" +
                "RevenueData AS (\n" +
                "    SELECT \n" +
                "        CONVERT(DATE, ngayGioXuatVe) AS ngay,\n" +
                "        SUM(ISNULL(tongTien, 0)) AS doanhThu\n" +
                "    FROM View_VeTongTien\n" +
                "    WHERE CONVERT(DATE, ngayGioXuatVe) BETWEEN :startDate2 AND :endDate2\n" +
                (maLoaiVe.isEmpty() ? "" : " AND maLV = :maLV") + "\n" +
                "    GROUP BY CONVERT(DATE, ngayGioXuatVe)\n" +
                ")\n" +
                "SELECT \n" +
                "    DATEDATE.ngay,\n" +
                "    ISNULL(RS.doanhThu, 0) AS doanhThu\n" +
                "FROM DateRange DATEDATE\n" +
                "LEFT JOIN RevenueData RS ON DATEDATE.ngay = RS.ngay\n" +
                "ORDER BY DATEDATE.ngay\n" +
                "OPTION (MAXRECURSION 0);";

        try {
            Query nativeQuery = em.createNativeQuery(query);
            nativeQuery.setParameter("startDate", new java.sql.Date(startOfWeek.getTime()));
            nativeQuery.setParameter("endDate", new java.sql.Date(endOfWeek.getTime()));
            nativeQuery.setParameter("startDate2", new java.sql.Date(startOfWeek.getTime()));
            nativeQuery.setParameter("endDate2", new java.sql.Date(endOfWeek.getTime()));
            if (!maLoaiVe.isEmpty()) {
                nativeQuery.setParameter("maLV", maLoaiVe);
            }

            List<Object[]> resultList = nativeQuery.getResultList();

            for (Object[] row : resultList) {
                LocalDate date = ((java.sql.Date) row[0]).toLocalDate();
                Double revenue = ((Number) row[1]).doubleValue();
                dailyRevenue.put(date, revenue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dailyRevenue;
    }
    @Override
    public Map<Integer, Double> getTuan_TuNgay_DenNgay(Date startDate, Date endDate, String maLoaiVe)throws RemoteException {
        Map<Integer, Double> weeklyRevenue = new LinkedHashMap<>();

        String query =
                "WITH DateRange AS ( " +
                        "    SELECT CAST(:startDate AS DATE) AS ngay " +
                        "    UNION ALL " +
                        "    SELECT DATEADD(DAY, 1, ngay) " +
                        "    FROM DateRange " +
                        "    WHERE ngay <= :endDate " +
                        "), " +
                        "RevenueData AS ( " +
                        "    SELECT " +
                        "        CONVERT(DATE, ngayGioXuatVe) AS ngay, " +
                        "        SUM(ISNULL(tongTien, 0)) AS doanhThu " +
                        "    FROM View_VeTongTien " +
                        "    WHERE CONVERT(DATE, ngayGioXuatVe) BETWEEN :startDate2 AND :endDate2 " +
                        (maLoaiVe.isEmpty() ? "" : " AND maLV = :maLoaiVe ") +
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
                        "ORDER BY weekNumber";

        try {
            Query nativeQuery = em.createNativeQuery(query);
            nativeQuery.setParameter("startDate", new java.sql.Date(startDate.getTime()));
            nativeQuery.setParameter("endDate", new java.sql.Date(endDate.getTime()));
            nativeQuery.setParameter("startDate2", new java.sql.Date(startDate.getTime()));
            nativeQuery.setParameter("endDate2", new java.sql.Date(endDate.getTime()));
            if (!maLoaiVe.isEmpty()) {
                nativeQuery.setParameter("maLoaiVe", maLoaiVe);
            }

            List<Object[]> resultList = nativeQuery.getResultList();

            for (Object[] row : resultList) {
                Integer weekNumber = ((Number) row[0]).intValue();
                Double revenue = ((Number) row[1]).doubleValue();
                weeklyRevenue.put(weekNumber, revenue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Weekly Revenue Data:");
        weeklyRevenue.forEach((week, revenue) ->
                System.out.println("Tuần " + week + ": Doanh thu = " + revenue)
        );

        return weeklyRevenue;
    }
    @Override
    public Map<Integer, Double> getThangVe_NgayBatDau_NgayKetThuc(Date startDate, Date endDate, String maLoaiVe)throws RemoteException {
        Map<Integer, Double> monthlyRevenue = new LinkedHashMap<>();

        String query = "WITH NumberTable AS (  " +
                "    SELECT TOP (DATEDIFF(DAY, :startDate, :endDate) + 1)  " +
                "        ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) - 1 AS num  " +
                "    FROM master.dbo.spt_values  " +
                "),  " +
                "DateRange AS (  " +
                "    SELECT DATEADD(DAY, num, :startDate2) AS ngay  " +
                "    FROM NumberTable  " +
                "),  " +
                "RevenueData AS (  " +
                "    SELECT  " +
                "        CONVERT(DATE, ngayGioXuatVe) AS ngay,  " +
                "        SUM(ISNULL(tongTien, 0)) AS doanhThu  " +
                "    FROM View_VeTongTien  " +
                "    WHERE CONVERT(DATE, ngayGioXuatVe) BETWEEN :startDate3 AND :endDate2  " +
                (maLoaiVe.isEmpty() ? "" : " AND maLV = :maLoaiVe ") +
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
                "ORDER BY monthNumber";

        try {
            Query nativeQuery = em.createNativeQuery(query);
            nativeQuery.setParameter("startDate", new java.sql.Date(startDate.getTime()));
            nativeQuery.setParameter("endDate", new java.sql.Date(endDate.getTime()));
            nativeQuery.setParameter("startDate2", new java.sql.Date(startDate.getTime()));
            nativeQuery.setParameter("startDate3", new java.sql.Date(startDate.getTime()));
            nativeQuery.setParameter("endDate2", new java.sql.Date(endDate.getTime()));

            if (!maLoaiVe.isEmpty()) {
                nativeQuery.setParameter("maLoaiVe", maLoaiVe);
            }

            List<Object[]> resultList = nativeQuery.getResultList();
            for (Object[] row : resultList) {
                Integer monthNumber = ((Number) row[0]).intValue();
                Double revenue = ((Number) row[1]).doubleValue();
                monthlyRevenue.put(monthNumber, revenue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return monthlyRevenue;
    }
    @Override
    public Map<Integer, Double> getQuarterlyRevenue(Date startDate, Date endDate, String maLoaiVe)throws RemoteException {
        Map<Integer, Double> quarterlyRevenue = new LinkedHashMap<>();

        String query = "WITH NumberTable AS (  " +
                "    SELECT TOP (DATEDIFF(DAY, :startDate, :endDate) + 1)  " +
                "        ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) - 1 AS num  " +
                "    FROM master.dbo.spt_values  " +
                "),  " +
                "DateRange AS (  " +
                "    SELECT DATEADD(DAY, num, :startDate2) AS ngay  " +
                "    FROM NumberTable  " +
                "),  " +
                "RevenueData AS (  " +
                "    SELECT  " +
                "        CONVERT(DATE, ngayGioXuatVe) AS ngay,  " +
                "        SUM(ISNULL(tongTien, 0)) AS doanhThu  " +
                "    FROM View_VeTongTien  " +
                "    WHERE CONVERT(DATE, ngayGioXuatVe) BETWEEN :startDate3 AND :endDate2  " +
                (maLoaiVe.isEmpty() ? "" : " AND maLV = :maLoaiVe ") +
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
                "ORDER BY year, quarter";

        try {
            Query nativeQuery = em.createNativeQuery(query);
            nativeQuery.setParameter("startDate", new java.sql.Date(startDate.getTime()));
            nativeQuery.setParameter("endDate", new java.sql.Date(endDate.getTime()));
            nativeQuery.setParameter("startDate2", new java.sql.Date(startDate.getTime()));
            nativeQuery.setParameter("startDate3", new java.sql.Date(startDate.getTime()));
            nativeQuery.setParameter("endDate2", new java.sql.Date(endDate.getTime()));

            if (!maLoaiVe.isEmpty()) {
                nativeQuery.setParameter("maLoaiVe", maLoaiVe);
            }

            List<Object[]> resultList = nativeQuery.getResultList();
            for (Object[] row : resultList) {
                Integer quarterNumber = ((Number) row[0]).intValue(); // Ví dụ: 20241
                Double revenue = ((Number) row[1]).doubleValue();
                quarterlyRevenue.put(quarterNumber, revenue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return quarterlyRevenue;
    }
    @Override
    public Map<Integer, Double> getYearlyRevenue(Date startDate, Date endDate, String maLoaiVe)throws RemoteException {
        Map<Integer, Double> yearlyRevenue = new LinkedHashMap<>();

        String query = "WITH NumberTable AS (  " +
                "    SELECT TOP (DATEDIFF(DAY, :startDate, :endDate) + 1)  " +
                "        ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) - 1 AS num  " +
                "    FROM master.dbo.spt_values  " +
                "),  " +
                "DateRange AS (  " +
                "    SELECT DATEADD(DAY, num, :startDate2) AS ngay  " +
                "    FROM NumberTable  " +
                "),  " +
                "RevenueData AS (  " +
                "    SELECT  " +
                "        CONVERT(DATE, ngayGioXuatVe) AS ngay,  " +
                "        SUM(ISNULL(tongTien, 0)) AS doanhThu  " +
                "    FROM View_VeTongTien  " +
                "    WHERE CONVERT(DATE, ngayGioXuatVe) BETWEEN :startDate3 AND :endDate2  " +
                (maLoaiVe.isEmpty() ? "" : " AND maLV = :maLoaiVe ") +
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
                "ORDER BY year";

        try {
            Query nativeQuery = em.createNativeQuery(query);
            nativeQuery.setParameter("startDate", new java.sql.Date(startDate.getTime()));
            nativeQuery.setParameter("endDate", new java.sql.Date(endDate.getTime()));
            nativeQuery.setParameter("startDate2", new java.sql.Date(startDate.getTime()));
            nativeQuery.setParameter("startDate3", new java.sql.Date(startDate.getTime()));
            nativeQuery.setParameter("endDate2", new java.sql.Date(endDate.getTime()));

            if (!maLoaiVe.isEmpty()) {
                nativeQuery.setParameter("maLoaiVe", maLoaiVe);
            }

            List<Object[]> resultList = nativeQuery.getResultList();
            for (Object[] row : resultList) {
                Integer year = ((Number) row[0]).intValue();
                Double revenue = ((Number) row[1]).doubleValue();
                yearlyRevenue.put(year, revenue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return yearlyRevenue;
    }
    @Override
    public Map<Integer, Double> getWeeklyRevenue()throws RemoteException {
        Map<Integer, Double> weeklyRevenue = new LinkedHashMap<>();

        String query = "WITH RevenueData AS ( " +
                "    SELECT  " +
                "        DATEPART(WEEK, ngayGioXuatVe) - DATEPART(WEEK, DATEADD(MONTH, DATEDIFF(MONTH, 0, ngayGioXuatVe), 0)) + 1 AS weekInMonth, " +
                "        SUM(ISNULL(tongTien, 0)) AS totalRevenue " +
                "    FROM View_VeTongTien " +
                "    WHERE ngayGioXuatVe >= DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()), 0) " +
                "      AND ngayGioXuatVe < DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()) + 1, 0) " +
                "    GROUP BY DATEPART(WEEK, ngayGioXuatVe) - DATEPART(WEEK, DATEADD(MONTH, DATEDIFF(MONTH, 0, ngayGioXuatVe), 0)) + 1 " +
                "), " +
                "AllWeeks AS ( " +
                "    SELECT TOP (5) ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS weeksInMonth " +
                "    FROM master.dbo.spt_values " +
                ") " +
                "SELECT  " +
                "    ISNULL(weekInMonth, weeksInMonth) AS weekNumber, " +
                "    ISNULL(totalRevenue, 0) AS totalRevenue " +
                "FROM AllWeeks " +
                "LEFT JOIN RevenueData ON weeksInMonth = weekInMonth " +
                "ORDER BY weekNumber;";

        try {
            Query nativeQuery = em.createNativeQuery(query);
            List<Object[]> resultList = nativeQuery.getResultList();
            for (Object[] row : resultList) {
                Integer weekNumber = ((Number) row[0]).intValue();
                Double revenue = ((Number) row[1]).doubleValue();
                weeklyRevenue.put(weekNumber, revenue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return weeklyRevenue;
    }
    @Override
    public Map<String, Double> getYearlyQuarterRevenue(int year)throws RemoteException {
        Map<String, Double> quarterlyRevenue = new LinkedHashMap<>();

        // Khởi tạo các quý với giá trị mặc định là 0
        quarterlyRevenue.put("Quý 1", 0.0);
        quarterlyRevenue.put("Quý 2", 0.0);
        quarterlyRevenue.put("Quý 3", 0.0);
        quarterlyRevenue.put("Quý 4", 0.0);

        String sql = "SELECT " +
                "  CASE " +
                "    WHEN MONTH(ngayGioXuatVe) BETWEEN 1 AND 3 THEN 'Quý 1' " +
                "    WHEN MONTH(ngayGioXuatVe) BETWEEN 4 AND 6 THEN 'Quý 2' " +
                "    WHEN MONTH(ngayGioXuatVe) BETWEEN 7 AND 9 THEN 'Quý 3' " +
                "    WHEN MONTH(ngayGioXuatVe) BETWEEN 10 AND 12 THEN 'Quý 4' " +
                "  END AS quarter, " +
                "  SUM(ISNULL(tongTien, 0)) AS doanhThu " +
                "FROM View_VeTongTien " +
                "WHERE YEAR(ngayGioXuatVe) = :year " +
                "GROUP BY CASE " +
                "  WHEN MONTH(ngayGioXuatVe) BETWEEN 1 AND 3 THEN 'Quý 1' " +
                "  WHEN MONTH(ngayGioXuatVe) BETWEEN 4 AND 6 THEN 'Quý 2' " +
                "  WHEN MONTH(ngayGioXuatVe) BETWEEN 7 AND 9 THEN 'Quý 3' " +
                "  WHEN MONTH(ngayGioXuatVe) BETWEEN 10 AND 12 THEN 'Quý 4' " +
                "END";

        try {
            Query nativeQuery = em.createNativeQuery(sql);
            nativeQuery.setParameter("year", year);

            List<Object[]> results = nativeQuery.getResultList();
            for (Object[] row : results) {
                String quarter = (String) row[0];
                Double revenue = ((Number) row[1]).doubleValue();
                quarterlyRevenue.put(quarter, revenue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return quarterlyRevenue;
    }
    @Override
    public Map<String, Double> getMonthlyRevenue(int year)throws RemoteException {
        Map<String, Double> monthlyRevenue = new LinkedHashMap<>();

        // Khởi tạo map chứa đủ 12 tháng với doanh thu mặc định là 0
        for (int month = 1; month <= 12; month++) {
            String monthKey = String.format("%02d", month);
            monthlyRevenue.put(monthKey, 0.0);
        }

        String sql = "WITH MonthlyRevenue AS ( " +
                "    SELECT " +
                "        MONTH(ngayGioXuatVe) AS thang, " +
                "        SUM(ISNULL(tongTien, 0)) AS doanhThu " +
                "    FROM View_VeTongTien " +
                "    WHERE YEAR(ngayGioXuatVe) = :year " +
                "    GROUP BY MONTH(ngayGioXuatVe) " +
                ") " +
                "SELECT thang, ISNULL(doanhThu, 0) AS doanhThu FROM MonthlyRevenue;";

        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter("year", year);

            List<Object[]> results = query.getResultList();
            for (Object[] row : results) {
                String monthKey = String.format("%02d", ((Number) row[0]).intValue());
                double revenue = ((Number) row[1]).doubleValue();
                monthlyRevenue.put(monthKey, revenue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return monthlyRevenue;
    }
    @Override
    public Object[] getThongKeLoaiVe(Date startDate, Date endDate, String maLoaiVe)throws RemoteException {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(endDate);  // endDate là java.util.Date
            calendar.add(Calendar.DATE, 1);  // Thêm 1 ngày

            // Lấy giá trị ngày mới từ Calendar
            java.util.Date utilDate = calendar.getTime();

            // Chuyển từ java.util.Date sang java.sql.Date
            java.sql.Date endDatePlusOne = new java.sql.Date(utilDate.getTime());

            String nativeQuery = "SELECT " +
                    "    vt.maLV, " +
                    "    SUM(vt.tongTien) AS totalRevenue, " +
                    "    COUNT(*) AS totalTickets " +
                    "FROM " +
                    "    [dbo].[View_VeTongTien] vt " +
                    "WHERE " +
                    "    vt.ngayGioXuatVe BETWEEN ?1 AND ?2 " +
                    "    AND vt.maLV = ?3 " +
                    "GROUP BY " +
                    "    vt.maLV";

            Query query = em.createNativeQuery(nativeQuery);
            query.setParameter(1, startDate);
            query.setParameter(2, endDatePlusOne);
            query.setParameter(3, maLoaiVe);

            List<Object[]> results = query.getResultList();
            if (!results.isEmpty()) {
                return results.get(0); // Return first result
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Object[]> getChiTietVeTheoNgay(String maLoaiVe, Date startDate, Date endDate)throws RemoteException {
        try {
            String nativeQuery = "SELECT " +
                    "    CONVERT(DATE, ngayGioXuatVe) AS ngay, " +
                    "    maLV, " +
                    "    COUNT(*) AS soVeBanDuoc, " +
                    "    SUM(tongTien) AS tongTien " +
                    "FROM View_VeTongTien " +
                    "WHERE CONVERT(DATE, ngayGioXuatVe) BETWEEN ?1 AND ?2 " +
                    (maLoaiVe != null && !maLoaiVe.isEmpty() ? " AND maLV = ?3 " : "") +
                    "GROUP BY CONVERT(DATE, ngayGioXuatVe), maLV " +
                    "ORDER BY CONVERT(DATE, ngayGioXuatVe)";

            Query query = em.createNativeQuery(nativeQuery);
            query.setParameter(1, startDate);
            query.setParameter(2, endDate);

            if (maLoaiVe != null && !maLoaiVe.isEmpty()) {
                query.setParameter(3, maLoaiVe);
            }

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    @Override
    public Object[] getThongKeTheoNhanVien(Date startDate, Date endDate, String maNhanVien)throws RemoteException {
        try {
            String nativeQuery = "SELECT " +
                    "    nv.tenNV, " +
                    "    SUM(vt.tongTien) AS totalRevenue, " +
                    "    COUNT(*) AS totalTickets " +
                    "FROM " +
                    "    [dbo].[View_VeTongTien] vt " +
                    "JOIN " +
                    "    [dbo].[HoaDon] hd on hd.maHD = vt.maHD " +
                    "JOIN " +
                    "    [dbo].[NhanVien] nv ON hd.maNV = nv.maNV " +
                    "WHERE " +
                    "    vt.ngayGioXuatVe BETWEEN ?1 AND ?2 " +
                    "    AND hd.maNV = ?3 " +
                    "GROUP BY " +
                    "    nv.tenNV";

            Query query = em.createNativeQuery(nativeQuery);
            query.setParameter(1, startDate);
            query.setParameter(2, endDate);
            query.setParameter(3, maNhanVien);

            List<Object[]> results = query.getResultList();
            if (!results.isEmpty()) {
                return results.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Khoe
    @Override
    public List<Object[]> getThongKeForAllNhanVien(Date startDate, Date endDate)throws RemoteException {
        String sql = "SELECT " +
                "    nv.tenNV, " +
                "    SUM(vt.tongTien) AS totalRevenue, " +
                "    COUNT(*) AS totalTickets " +
                "FROM " +
                "    [dbo].[View_VeTongTien] vt " +
                "JOIN " +
                "    [dbo].[HoaDon] hd on hd.maHD = vt.maHD " +
                "JOIN " +
                "    [dbo].[NhanVien] nv ON hd.maNV = nv.maNV " +
                "WHERE " +
                "    vt.ngayGioXuatVe BETWEEN :startDate AND :endDate " +
                "GROUP BY " +
                "    nv.tenNV";

        List<Object[]> resultList = new ArrayList<>();
        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);

            List<Object[]> results = query.getResultList();
            for (Object[] row : results) {
                Object[] mappedRow = new Object[3];
                mappedRow[0] = row[0]; // tenNV
                mappedRow[1] = row[1]; // totalRevenue
                mappedRow[2] = row[2]; // totalTickets
                resultList.add(mappedRow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
    @Override
    public Map<LocalDate, Map<String, Double>> getDoanhThuTheoNgay_VaNhanVien(
            Date startDate, Date endDate, String maNhanVien)throws RemoteException {

        Map<LocalDate, Map<String, Double>> revenueData = new LinkedHashMap<>();

        String query = "WITH DateRange AS ( " +
                "    SELECT CAST(:startDate AS DATE) AS ngay " +
                "    UNION ALL " +
                "    SELECT DATEADD(DAY, 1, ngay) " +
                "    FROM DateRange " +
                "    WHERE ngay <= :endDate " +
                "), " +
                "RevenueData AS ( " +
                "    SELECT " +
                "        CONVERT(DATE, ngayGioXuatVe) AS ngay, " +
                "        SUM(ISNULL(tongTien, 0)) AS tongDoanhThu, " +
                "        SUM(CASE WHEN hd.maNV = :maNhanVien THEN ISNULL(tongTien, 0) ELSE 0 END) AS doanhThuNhanVien " +
                "    FROM View_VeTongTien vt " +
                "    JOIN HoaDon hd ON vt.maHD = hd.maHD " +
                "    WHERE CONVERT(DATE, ngayGioXuatVe) BETWEEN :startDate2 AND :endDate2 " +
                "    GROUP BY CONVERT(DATE, ngayGioXuatVe) " +
                ") " +
                "SELECT " +
                "    DATEDATE.ngay, " +
                "    ISNULL(RD.tongDoanhThu, 0) AS tongDoanhThu, " +
                "    ISNULL(RD.doanhThuNhanVien, 0) AS doanhThuNhanVien " +
                "FROM DateRange DATEDATE " +
                "LEFT JOIN RevenueData RD ON DATEDATE.ngay = RD.ngay " +
                "ORDER BY DATEDATE.ngay " +
                "OPTION (MAXRECURSION 0)";

        try {
            Query nativeQuery = em.createNativeQuery(query);

            nativeQuery.setParameter("startDate", startDate);
            nativeQuery.setParameter("endDate", endDate);
            nativeQuery.setParameter("maNhanVien", maNhanVien);
            nativeQuery.setParameter("startDate2", startDate);
            nativeQuery.setParameter("endDate2", endDate);

            List<Object[]> results = nativeQuery.getResultList();
            for (Object[] row : results) {
                LocalDate date = ((java.sql.Date) row[0]).toLocalDate();
                double totalRevenue = ((Number) row[1]).doubleValue();
                double employeeRevenue = ((Number) row[2]).doubleValue();

                Map<String, Double> dayData = new LinkedHashMap<>();
                dayData.put("TongDoanhThu", totalRevenue);
                dayData.put("DoanhThuNhanVien", employeeRevenue);

                revenueData.put(date, dayData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return revenueData;
    }
    @Override
    public Map<Integer, Map<String, Double>> getDoanhThuTheoTuan_VaNhanVien(
            Date startDate, Date endDate, String maNhanVien)throws RemoteException {

        Map<Integer, Map<String, Double>> weeklyRevenueData = new LinkedHashMap<>();

        String query = "WITH DateRange AS ( " +
                "    SELECT CAST(:startDate AS DATE) AS ngay " +
                "    UNION ALL " +
                "    SELECT DATEADD(DAY, 1, ngay) " +
                "    FROM DateRange " +
                "    WHERE ngay <= :endDate " +
                "), " +
                "RevenueData AS ( " +
                "    SELECT " +
                "        CONVERT(DATE, ngayGioXuatVe) AS ngay, " +
                "        SUM(ISNULL(tongTien, 0)) AS tongDoanhThu, " +
                "        SUM(CASE WHEN hd.maNV = :maNhanVien THEN ISNULL(tongTien, 0) ELSE 0 END) AS doanhThuNhanVien " +
                "    FROM View_VeTongTien vt " +
                "    JOIN HoaDon hd ON vt.maHD = hd.maHD " +
                "    WHERE CONVERT(DATE, ngayGioXuatVe) BETWEEN :startDate2 AND :endDate2 " +
                "    GROUP BY CONVERT(DATE, ngayGioXuatVe) " +
                "), " +
                "WeekData AS ( " +
                "    SELECT " +
                "        DATEADD(DAY, -DATEPART(WEEKDAY, DATEDATE.ngay) + 1, DATEDATE.ngay) AS startOfWeek, " +
                "        ISNULL(RD.tongDoanhThu, 0) AS tongDoanhThu, " +
                "        ISNULL(RD.doanhThuNhanVien, 0) AS doanhThuNhanVien " +
                "    FROM DateRange DATEDATE " +
                "    LEFT JOIN RevenueData RD ON DATEDATE.ngay = RD.ngay " +
                "), " +
                "RankedWeeks AS ( " +
                "    SELECT " +
                "        ROW_NUMBER() OVER (ORDER BY startOfWeek) AS weekNumber, " +
                "        startOfWeek, " +
                "        SUM(tongDoanhThu) AS totalRevenue, " +
                "        SUM(doanhThuNhanVien) AS employeeRevenue " +
                "    FROM WeekData " +
                "    GROUP BY startOfWeek " +
                ") " +
                "SELECT " +
                "    weekNumber, " +
                "    totalRevenue, " +
                "    employeeRevenue " +
                "FROM RankedWeeks " +
                "ORDER BY weekNumber";

        try {
            Query nativeQuery = em.createNativeQuery(query);

            nativeQuery.setParameter("startDate", startDate);
            nativeQuery.setParameter("endDate", endDate);
            nativeQuery.setParameter("maNhanVien", maNhanVien);
            nativeQuery.setParameter("startDate2", startDate);
            nativeQuery.setParameter("endDate2", endDate);

            List<Object[]> results = nativeQuery.getResultList();
            for (Object[] row : results) {
                int weekNumber = ((Number) row[0]).intValue();
                double totalRevenue = ((Number) row[1]).doubleValue();
                double employeeRevenue = ((Number) row[2]).doubleValue();

                Map<String, Double> weekData = new LinkedHashMap<>();
                weekData.put("TongDoanhThu", totalRevenue);
                weekData.put("DoanhThuNhanVien", employeeRevenue);

                weeklyRevenueData.put(weekNumber, weekData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return weeklyRevenueData;
    }
    @Override
    public Map<Integer, Map<String, Double>> getDoanhThuTheoThang_VaNhanVien(
            Date startDate, Date endDate, String maNhanVien)throws RemoteException {

        Map<Integer, Map<String, Double>> revenueData = new LinkedHashMap<>();

        String query = "WITH DateRange AS ( " +
                "    SELECT CAST(:startDate AS DATE) AS ngay " +
                "    UNION ALL " +
                "    SELECT DATEADD(DAY, 1, ngay) " +
                "    FROM DateRange " +
                "    WHERE ngay <= :endDate " +
                "), " +
                "RevenueData AS ( " +
                "    SELECT " +
                "        MONTH(CONVERT(DATE, ngayGioXuatVe)) AS thang, " +
                "        YEAR(CONVERT(DATE, ngayGioXuatVe)) AS nam, " +
                "        SUM(ISNULL(tongTien, 0)) AS tongDoanhThu, " +
                "        SUM(CASE WHEN hd.maNV = :maNhanVien THEN ISNULL(tongTien, 0) ELSE 0 END) AS doanhThuNhanVien " +
                "    FROM View_VeTongTien vt " +
                "    JOIN HoaDon hd ON vt.maHD = hd.maHD " +
                "    WHERE CONVERT(DATE, ngayGioXuatVe) BETWEEN :startDate2 AND :endDate2 " +
                "    GROUP BY YEAR(CONVERT(DATE, ngayGioXuatVe)), MONTH(CONVERT(DATE, ngayGioXuatVe)) " +
                ") " +
                "SELECT " +
                "    RD.thang, " +
                "    RD.nam, " +
                "    ISNULL(RD.tongDoanhThu, 0) AS tongDoanhThu, " +
                "    ISNULL(RD.doanhThuNhanVien, 0) AS doanhThuNhanVien " +
                "FROM RevenueData RD " +
                "ORDER BY RD.nam, RD.thang";

        try {
            Query nativeQuery = em.createNativeQuery(query);

            nativeQuery.setParameter("startDate", startDate);
            nativeQuery.setParameter("endDate", endDate);
            nativeQuery.setParameter("maNhanVien", maNhanVien);
            nativeQuery.setParameter("startDate2", startDate);
            nativeQuery.setParameter("endDate2", endDate);

            List<Object[]> results = nativeQuery.getResultList();
            for (Object[] row : results) {
                int month = ((Number) row[0]).intValue();         // lấy cột 'thang'
                double totalRevenue = ((Number) row[2]).doubleValue(); // cột 'tongDoanhThu'
                double employeeRevenue = ((Number) row[3]).doubleValue(); // cột 'doanhThuNhanVien'

                Map<String, Double> monthData = new LinkedHashMap<>();
                monthData.put("TongDoanhThu", totalRevenue);
                monthData.put("DoanhThuNhanVien", employeeRevenue);

                revenueData.put(month, monthData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return revenueData;
    }
    @Override
    public Map<Integer, Map<String, Double>> getDoanhThuTheoQuy_VaNhanVien(
            Date startDate, Date endDate, String maNhanVien)throws RemoteException {

        Map<Integer, Map<String, Double>> revenueData = new LinkedHashMap<>();

        String query = "WITH DateRange AS ( " +
                "    SELECT CAST(:startDate AS DATE) AS ngay " +
                "    UNION ALL " +
                "    SELECT DATEADD(DAY, 1, ngay) " +
                "    FROM DateRange " +
                "    WHERE ngay <= :endDate " +
                "), " +
                "RevenueData AS ( " +
                "    SELECT " +
                "        DATEPART(QUARTER, ngayGioXuatVe) AS quy, " +
                "        SUM(ISNULL(tongTien, 0)) AS tongDoanhThu, " +
                "        SUM(CASE WHEN hd.maNV = :maNhanVien THEN ISNULL(tongTien, 0) ELSE 0 END) AS doanhThuNhanVien " +
                "    FROM View_VeTongTien vt " +
                "    JOIN HoaDon hd ON vt.maHD = hd.maHD " +
                "    WHERE CONVERT(DATE, ngayGioXuatVe) BETWEEN :startDate2 AND :endDate2 " +
                "    GROUP BY DATEPART(QUARTER, ngayGioXuatVe) " +
                ") " +
                "SELECT " +
                "    DR.quy, " +
                "    ISNULL(RD.tongDoanhThu, 0) AS tongDoanhThu, " +
                "    ISNULL(RD.doanhThuNhanVien, 0) AS doanhThuNhanVien " +
                "FROM (SELECT DISTINCT DATEPART(QUARTER, ngay) AS quy FROM DateRange) DR " +
                "LEFT JOIN RevenueData RD ON DR.quy = RD.quy " +
                "ORDER BY DR.quy";

        try {
            Query nativeQuery = em.createNativeQuery(query);

            nativeQuery.setParameter("startDate", startDate);
            nativeQuery.setParameter("endDate", endDate);
            nativeQuery.setParameter("maNhanVien", maNhanVien);
            nativeQuery.setParameter("startDate2", startDate);
            nativeQuery.setParameter("endDate2", endDate);

            List<Object[]> results = nativeQuery.getResultList();
            for (Object[] row : results) {
                int quarter = ((Number) row[0]).intValue();          // cột quy
                double totalRevenue = ((Number) row[1]).doubleValue(); // cột tongDoanhThu
                double employeeRevenue = ((Number) row[2]).doubleValue(); // cột doanhThuNhanVien

                Map<String, Double> quarterData = new LinkedHashMap<>();
                quarterData.put("TongDoanhThu", totalRevenue);
                quarterData.put("DoanhThuNhanVien", employeeRevenue);

                revenueData.put(quarter, quarterData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return revenueData;
    }
    @Override
    public Map<Integer, Map<String, Double>> getDoanhThuTheoNam_VaNhanVien(
            Date startDate, Date endDate, String maNhanVien)throws RemoteException {
        Map<Integer, Map<String, Double>> revenueData = new LinkedHashMap<>();

        String query = "WITH DateRange AS ( " +
                "    SELECT CAST(:startDate AS DATE) AS ngay " +
                "    UNION ALL " +
                "    SELECT DATEADD(DAY, 1, ngay) " +
                "    FROM DateRange " +
                "    WHERE ngay <= :endDate " +
                "), " +
                "RevenueData AS ( " +
                "    SELECT " +
                "        YEAR(ngayGioXuatVe) AS nam, " +
                "        SUM(ISNULL(tongTien, 0)) AS tongDoanhThu, " +
                "        SUM(CASE WHEN hd.maNV = :maNhanVien THEN ISNULL(tongTien, 0) ELSE 0 END) AS doanhThuNhanVien " +
                "    FROM View_VeTongTien vt " +
                "    JOIN HoaDon hd ON vt.maHD = hd.maHD " +
                "    WHERE CONVERT(DATE, ngayGioXuatVe) BETWEEN :startDate2 AND :endDate2 " +
                "    GROUP BY YEAR(ngayGioXuatVe) " +
                ") " +
                "SELECT " +
                "    DR.nam, " +
                "    ISNULL(RD.tongDoanhThu, 0) AS tongDoanhThu, " +
                "    ISNULL(RD.doanhThuNhanVien, 0) AS doanhThuNhanVien " +
                "FROM (SELECT DISTINCT YEAR(ngay) AS nam FROM DateRange) DR " +
                "LEFT JOIN RevenueData RD ON DR.nam = RD.nam " +
                "ORDER BY DR.nam";

        try {
            Query nativeQuery = em.createNativeQuery(query);

            nativeQuery.setParameter("startDate", startDate);
            nativeQuery.setParameter("endDate", endDate);
            nativeQuery.setParameter("maNhanVien", maNhanVien);
            nativeQuery.setParameter("startDate2", startDate);
            nativeQuery.setParameter("endDate2", endDate);

            List<Object[]> results = nativeQuery.getResultList();
            for (Object[] row : results) {
                int year = ((Number) row[0]).intValue();               // cột nam
                double totalRevenue = ((Number) row[1]).doubleValue();  // cột tongDoanhThu
                double employeeRevenue = ((Number) row[2]).doubleValue(); // cột doanhThuNhanVien

                Map<String, Double> yearData = new LinkedHashMap<>();
                yearData.put("TongDoanhThu", totalRevenue);
                yearData.put("DoanhThuNhanVien", employeeRevenue);

                revenueData.put(year, yearData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return revenueData;
    }
    @Override
    public List<Object[]> getDoanhThuChiTietTheoNgay()throws RemoteException {
        List<Object[]> results = new ArrayList<>();

        String sql = "SELECT " +
                "    CONVERT(DATE, hd.ngayGioLapHD) AS Ngay, " +
                "    nv.maNV AS MaNhanVien, " +
                "    nv.tenNV AS HoTenNhanVien, " +
                "    COUNT(v.maVe) AS SoLuongVeDaBan, " +
                "    SUM(v.tongTien) AS TongTien " +
                "FROM HoaDon hd " +
                "INNER JOIN NhanVien nv ON hd.maNhanVien = nv.maNV " +
                "INNER JOIN View_VeTongTien v ON hd.maHD = v.maHoaDon " +
                "GROUP BY CONVERT(DATE, hd.ngayGioLapHD), nv.maNV, nv.tenNV " +
                "ORDER BY Ngay ASC, TongTien DESC";

        try {
            Query query = em.createNativeQuery(sql);
            results = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }
    @Override
    public List<Object[]> getDoanhThuChiTietTheoKhoangNgay(Date startDate, Date endDate)throws RemoteException {
        List<Object[]> results = new ArrayList<>();

        String sql = "SELECT " +
                "    CONVERT(DATE, hd.ngayGioLapHD) AS Ngay, " +
                "    nv.maNV AS MaNhanVien, " +
                "    nv.tenNV AS HoTenNhanVien, " +
                "    COUNT(v.maVe) AS SoLuongVeDaBan, " +
                "    SUM(v.tongTien) AS TongTien " +
                "FROM HoaDon hd " +
                "INNER JOIN NhanVien nv ON hd.maNV = nv.maNV " +
                "INNER JOIN View_VeTongTien v ON hd.maHD = v.maHD " +
                "WHERE CONVERT(DATE, hd.ngayGioLapHD) BETWEEN ?1 AND ?2 " +
                "GROUP BY CONVERT(DATE, hd.ngayGioLapHD), nv.maNV, nv.tenNV " +
                "ORDER BY Ngay ASC, TongTien DESC";

        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter(1, startDate); // truyền trực tiếp Date gốc
            query.setParameter(2, endDate);   // truyền trực tiếp Date gốc

            results = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }
    @Override
    public List<Object[]> getDoanhThuNhanVienTheoNgay(Date startDate, Date endDate, String maNhanVien)throws RemoteException {
        List<Object[]> results = new ArrayList<>();

        String sql = "SELECT " +
                "    CAST(hd.ngayGioLapHD AS DATE) AS Ngay, " +
                "    COUNT(v.maVe) AS SoLuongVe, " +
                "    SUM(v.tongTien) AS TongDoanhThu " +
                "FROM HoaDon hd " +
                "INNER JOIN View_VeTongTien v ON hd.maHD = v.maHD " +
                "WHERE hd.maNV = ?1 " +
                "AND CAST(hd.ngayGioLapHD AS DATE) BETWEEN ?2 AND ?3 " +
                "GROUP BY CAST(hd.ngayGioLapHD AS DATE) " +
                "ORDER BY Ngay ASC";

        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter(1, maNhanVien);
            query.setParameter(2, startDate);
            query.setParameter(3, endDate);

            results = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }
    @Override
    public List<Object> getLoyaltyStats()throws RemoteException {
        List<Object> stats = new ArrayList<>();

        String sql = "WITH FirstTransaction AS ( " +
                "    SELECT " +
                "        v.maKH, " +
                "        MIN(v.ngayGioXuatVe) AS ngayGiaoDichDauTien " +
                "    FROM Ve v " +
                "    GROUP BY v.maKH " +
                "), " +
                "RepeatCustomers AS ( " +
                "    SELECT " +
                "        ft.maKH, " +
                "        COUNT(v.maKH) AS soLanGiaoDich " +
                "    FROM FirstTransaction ft " +
                "    JOIN Ve v ON ft.maKH = v.maKH AND v.ngayGioXuatVe > ft.ngayGiaoDichDauTien " +
                "    GROUP BY ft.maKH " +
                "), " +
                "LoyaltyStats AS ( " +
                "    SELECT " +
                "        COUNT(DISTINCT CASE WHEN rc.soLanGiaoDich > 0 THEN rc.maKH END) AS soKHQuayLai, " +
                "        COUNT(DISTINCT ft.maKH) AS tongSoKH " +
                "    FROM FirstTransaction ft " +
                "    LEFT JOIN RepeatCustomers rc ON ft.maKH = rc.maKH " +
                ") " +
                "SELECT " +
                "    soKHQuayLai, " +
                "    tongSoKH, " +
                "    CAST(soKHQuayLai * 100.0 / tongSoKH AS DECIMAL(10, 2)) AS tiLeQuayLai " +
                "FROM LoyaltyStats";

        try {
            Query query = em.createNativeQuery(sql);
            Object[] result = (Object[]) query.getSingleResult();

            stats.add(result[0]); // Số khách hàng quay lại
            stats.add(result[1]); // Tổng số khách hàng
            stats.add(result[2]); // Tỷ lệ quay lại
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stats;
    }
    @Override
    public String getOverallAverageTransactionInterval()throws RemoteException {
        String sql = "WITH OrderedTransactions AS ( " +
                "    SELECT " +
                "        maKH, " +
                "        ngayGioLapHD, " +
                "        LAG(ngayGioLapHD) OVER (PARTITION BY maKH ORDER BY ngayGioLapHD) AS previousTransactionDate " +
                "    FROM HoaDon " +
                "), " +
                "TransactionIntervals AS ( " +
                "    SELECT " +
                "        DATEDIFF(SECOND, previousTransactionDate, ngayGioLapHD) AS intervalSeconds " +
                "    FROM OrderedTransactions " +
                "    WHERE previousTransactionDate IS NOT NULL " +
                ") " +
                "SELECT " +
                "    CAST(AVG(intervalSeconds) AS BIGINT) AS averageIntervalSeconds " +
                "FROM TransactionIntervals";

        try {
            Query query = em.createNativeQuery(sql);
            Object result = query.getSingleResult();

            if (result != null) {
                long averageSeconds = ((Number) result).longValue();

                // Convert seconds to days and hours
                long days = averageSeconds / (24 * 3600);
                averageSeconds %= (24 * 3600);
                long hours = averageSeconds / 3600;
                averageSeconds %= 3600;
                long minutes = averageSeconds / 60;
                long seconds = averageSeconds % 60;

                return String.format("%d ngày - %d giờ", days, hours);
//            return String.format("%d ngày - %d giờ - %d phút - %d giây", days, hours, minutes, seconds);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Không có dữ liệu"; // fallback nếu không có kết quả
    }
    @Override
    public int getTotalCustomersByDateRange(Date startDate, Date endDate)throws RemoteException {
        int totalCustomers = 0;
        String sql = "SELECT COUNT(DISTINCT v.maKH) AS tongSoLuongKhachHang " +
                "FROM Ve v " +
                "INNER JOIN HoaDon h ON v.maHD = h.maHD " +
                "WHERE h.ngayGioLapHD BETWEEN ?1 AND ?2";

        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter(1, startDate);
            query.setParameter(2, endDate);

            Object result = query.getSingleResult();
            if (result != null) {
                totalCustomers = ((Number) result).intValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalCustomers;
    }
    @Override
    public List<Object[]> getCustomerCountByDateRange(Date startDate, Date endDate)throws RemoteException {
        List<Object[]> resultList = new ArrayList<>();
        String sql = "WITH DateRange AS ( " +
                "    SELECT CAST(?1 AS DATE) AS ngayGioLap " +
                "    UNION ALL " +
                "    SELECT DATEADD(DAY, 1, ngayGioLap) " +
                "    FROM DateRange " +
                "    WHERE ngayGioLap < ?2 " +
                ") " +
                "SELECT " +
                "    d.ngayGioLap, " +
                "    COALESCE(COUNT(DISTINCT v.maKH), 0) AS soLuongKhachHang " +
                "FROM " +
                "    DateRange d " +
                "LEFT JOIN " +
                "    Ve v ON CAST(v.ngayGioXuatVe AS DATE) = d.ngayGioLap " +
                "LEFT JOIN " +
                "    HoaDon h ON v.maHD = h.maHD AND h.ngayGioLapHD BETWEEN ?3 AND ?4 " +
                "GROUP BY " +
                "    d.ngayGioLap " +
                "ORDER BY " +
                "    d.ngayGioLap";

        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter(1, startDate);
            query.setParameter(2, endDate);
            query.setParameter(3, startDate);
            query.setParameter(4, endDate);

            List<Object[]> queryResult = query.getResultList();
            int i = 1;
            for (Object[] row : queryResult) {
                Object[] newRow = new Object[3];
                newRow[0] = row[0]; // ngayGioLap
                newRow[1] = row[1]; // soLuongKhachHang
                newRow[2] = i++;    // thêm thứ tự
                resultList.add(newRow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
    @Override
    public List<Object[]> getCustomerCountByWeek(Date startDate, Date endDate)throws RemoteException {
        List<Object[]> resultList = new ArrayList<>();

        String sql = "WITH DateRange AS ( " +
                "    SELECT CAST(?1 AS DATE) AS ngayGioLap " +
                "    UNION ALL " +
                "    SELECT DATEADD(DAY, 1, ngayGioLap) " +
                "    FROM DateRange " +
                "    WHERE ngayGioLap < ?2 " +
                ") " +
                "SELECT " +
                "    DATEPART(WEEK, d.ngayGioLap) AS weekNumber, " +
                "    COALESCE(COUNT(DISTINCT v.maKH), 0) AS soLuongKhachHang " +
                "FROM " +
                "    DateRange d " +
                "LEFT JOIN " +
                "    Ve v ON CAST(v.ngayGioXuatVe AS DATE) = d.ngayGioLap " +
                "LEFT JOIN " +
                "    HoaDon h ON v.maHD = h.maHD AND h.ngayGioLapHD BETWEEN ?3 AND ?4 " +
                "GROUP BY " +
                "    DATEPART(WEEK, d.ngayGioLap) " +
                "ORDER BY " +
                "    weekNumber";

        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter(1, startDate);
            query.setParameter(2, endDate);
            query.setParameter(3, startDate);
            query.setParameter(4, endDate);

            List<Object[]> queryResult = query.getResultList();
            for (Object[] row : queryResult) {
                Object[] formattedRow = new Object[2];
                formattedRow[0] = row[0]; // Tuần
                formattedRow[1] = row[1]; // Số lượng khách hàng
                resultList.add(formattedRow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }
    @Override
    public List<Object[]> getCustomerCountByMonth(Date startDate, Date endDate)throws RemoteException {
        List<Object[]> resultList = new ArrayList<>();

        String sql = "WITH DateRange AS ( " +
                "    SELECT CAST(?1 AS DATE) AS ngayGioLap " +
                "    UNION ALL " +
                "    SELECT DATEADD(DAY, 1, ngayGioLap) " +
                "    FROM DateRange " +
                "    WHERE ngayGioLap < ?2 " +
                ") " +
                "SELECT " +
                "    MONTH(d.ngayGioLap) AS monthNumber, " +
                "    YEAR(d.ngayGioLap) AS yearNumber, " +
                "    COALESCE(COUNT(DISTINCT v.maKH), 0) AS soLuongKhachHang " +
                "FROM " +
                "    DateRange d " +
                "LEFT JOIN " +
                "    Ve v ON CAST(v.ngayGioXuatVe AS DATE) = d.ngayGioLap " +
                "LEFT JOIN " +
                "    HoaDon h ON v.maHD = h.maHD AND h.ngayGioLapHD BETWEEN ?3 AND ?4 " +
                "GROUP BY " +
                "    MONTH(d.ngayGioLap), YEAR(d.ngayGioLap) " +
                "ORDER BY " +
                "    yearNumber, monthNumber";

        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter(1, startDate);
            query.setParameter(2, endDate);
            query.setParameter(3, startDate);
            query.setParameter(4, endDate);

            List<Object[]> queryResult = query.getResultList();
            for (Object[] row : queryResult) {
                Object[] formattedRow = new Object[3];
                formattedRow[0] = row[0]; // Tháng
                formattedRow[1] = row[1]; // Năm
                formattedRow[2] = row[2]; // Số lượng khách hàng
                resultList.add(formattedRow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }
    @Override
    public List<Object[]> getCustomerCountByQuarter(Date startDate, Date endDate)throws RemoteException {
        List<Object[]> resultList = new ArrayList<>();

        String sql = "WITH DateRange AS ( " +
                "    SELECT CAST(?1 AS DATE) AS ngayGioLap " +
                "    UNION ALL " +
                "    SELECT DATEADD(DAY, 1, ngayGioLap) " +
                "    FROM DateRange " +
                "    WHERE ngayGioLap < ?2 " +
                ") " +
                "SELECT " +
                "    DATEPART(QUARTER, d.ngayGioLap) AS quarterNumber, " +
                "    YEAR(d.ngayGioLap) AS yearNumber, " +
                "    COALESCE(COUNT(DISTINCT v.maKH), 0) AS soLuongKhachHang " +
                "FROM " +
                "    DateRange d " +
                "LEFT JOIN " +
                "    Ve v ON CAST(v.ngayGioXuatVe AS DATE) = d.ngayGioLap " +
                "LEFT JOIN " +
                "    HoaDon h ON v.maHD = h.maHD AND h.ngayGioLapHD BETWEEN ?3 AND ?4 " +
                "GROUP BY " +
                "    DATEPART(QUARTER, d.ngayGioLap), YEAR(d.ngayGioLap) " +
                "ORDER BY " +
                "    yearNumber, quarterNumber";

        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter(1, startDate);
            query.setParameter(2, endDate);
            query.setParameter(3, startDate);
            query.setParameter(4, endDate);

            List<Object[]> queryResult = query.getResultList();
            for (Object[] row : queryResult) {
                Object[] formattedRow = new Object[3];
                formattedRow[0] = row[0]; // Quý
                formattedRow[1] = row[1]; // Năm
                formattedRow[2] = row[2]; // Số lượng khách hàng
                resultList.add(formattedRow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }

    // Việt
    @Override
    public List<Object[]> getCustomerCountByYear(Date startDate, Date endDate)throws RemoteException {
        try {
            String nativeQuery = "WITH DateRange AS ( " +
                    "    SELECT CAST(?1 AS DATE) AS ngayGioLap " +
                    "    UNION ALL " +
                    "    SELECT DATEADD(DAY, 1, ngayGioLap) " +
                    "    FROM DateRange " +
                    "    WHERE ngayGioLap < ?2 " +
                    ") " +
                    "SELECT " +
                    "    YEAR(d.ngayGioLap) AS yearNumber, " +
                    "    COALESCE(COUNT(DISTINCT v.maKH), 0) AS soLuongKhachHang " +
                    "FROM " +
                    "    DateRange d " +
                    "LEFT JOIN " +
                    "    Ve v ON CAST(v.ngayGioXuatVe AS DATE) = d.ngayGioLap " +
                    "LEFT JOIN " +
                    "    HoaDon h ON v.maHD = h.maHD AND h.ngayGioLapHD BETWEEN ?3 AND ?4 " +
                    "GROUP BY " +
                    "    YEAR(d.ngayGioLap) " +
                    "ORDER BY " +
                    "    YEAR(d.ngayGioLap) " +
                    "OPTION (MAXRECURSION 3660)"; // Added to handle large date ranges

            Query query = em.createNativeQuery(nativeQuery);
            query.setParameter(1, startDate);
            query.setParameter(2, endDate);
            query.setParameter(3, startDate);
            query.setParameter(4, endDate);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    @Override
    public  List<Object[]> getTyLeKhuyenMaiTheoDoiTuong(Date startDate, Date endDate)throws RemoteException {
        try {
            String nativeQuery = "SELECT " +
                    "    km.doiTuong, " +
                    "    COUNT(v.maVe) AS SoLuongVeDuocApDung, " +
                    "    (COUNT(v.maVe) * 100.0 / (SELECT COUNT(v2.maVe) FROM Ve v2 INNER JOIN KhuyenMai km2 ON v2.maKM = km2.maKM WHERE v2.ngayGioXuatVe BETWEEN ?1 AND ?2 AND km2.doiTuong <> N'Người lớn')) AS TiLeApDung " +
                    "FROM " +
                    "    Ve v " +
                    "INNER JOIN " +
                    "    KhuyenMai km ON v.maKM = km.maKM " +
                    "WHERE v.ngayGioXuatVe BETWEEN km.ngayApDung AND km.ngayKetThuc " +
                    "  AND v.ngayGioXuatVe BETWEEN ?3 AND ?4 " +
                    "  AND km.doiTuong <> N'Người lớn' " +
                    "GROUP BY " +
                    "    km.doiTuong";

            Query query = em.createNativeQuery(nativeQuery);
            query.setParameter(1, startDate);
            query.setParameter(2, endDate);
            query.setParameter(3, startDate);
            query.setParameter(4, endDate);

            List<Object[]> results = query.getResultList();

            // Convert NString to String in results if needed
            return results.stream()
                    .map(row -> new Object[] {
                            row[0].toString(),  // doiTuong (convert to String)
                            ((Number) row[1]).intValue(),  // SoLuongVeDuocApDung
                            ((Number) row[2]).doubleValue()  // TiLeApDung
                    })
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // Return empty list instead of null for better practice
        }
    }
    @Override
    public List<Object[]> getDoanhThuKhuyenMaiTheoDoiTuong(Date startDate, Date endDate)throws RemoteException {
        List<Object[]> resultList = new ArrayList<>();
        String sql = "SELECT km.doiTuong, " +
                "SUM(lc.giaCho + lc.giaCho * v.thue - (lc.giaCho + lc.giaCho * v.thue) * ISNULL(km.phanTramKM, 0)) AS TongTienKhuyenMai, " +
                "(SUM(lc.giaCho + lc.giaCho * v.thue - (lc.giaCho + lc.giaCho * v.thue) * ISNULL(km.phanTramKM, 0)) * 100.0 / " +
                "(SELECT SUM(lc2.giaCho + lc2.giaCho * v2.thue - (lc2.giaCho + lc2.giaCho * v2.thue) * ISNULL(km2.phanTramKM, 0)) " +
                " FROM Ve v2 INNER JOIN KhuyenMai km2 ON v2.maKM = km2.maKM " +
                " INNER JOIN ChoNgoi cn2 ON v2.maCho = cn2.maCho " +
                " INNER JOIN LoaiCho lc2 ON cn2.maLC = lc2.maLC " +
                " WHERE km2.doiTuong <> N'Người lớn' AND v2.ngayGioXuatVe BETWEEN ?1 AND ?2)) AS TiLeDoanhThu " +
                "FROM Ve v " +
                "INNER JOIN KhuyenMai km ON v.maKM = km.maKM " +
                "INNER JOIN ChoNgoi cn ON v.maCho = cn.maCho " +
                "INNER JOIN LoaiCho lc ON cn.maLC = lc.maLC " +
                "WHERE km.doiTuong <> N'Người lớn' AND v.ngayGioXuatVe BETWEEN ?3 AND ?4 " +
                "GROUP BY km.doiTuong";

        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter(1, startDate);
            query.setParameter(2, endDate);
            query.setParameter(3, startDate);
            query.setParameter(4, endDate);

            List<Object[]> results = query.getResultList();
            for (Object[] row : results) {
                // row[0]: doiTuong (String)
                // row[1]: TongTienKhuyenMai (Double)
                // row[2]: TiLeDoanhThu (Double)
                resultList.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi theo nhu cầu, ví dụ: throw hoặc log
            return null;
        }
        return resultList;
    }
    @Override
    public  List<Object[]> getTopCustomers(Date startDate, Date endDate)throws RemoteException {
        List<Object[]> result = new ArrayList<>();
        String sql = "SELECT TOP 100 kh.maKH, kh.tenKH, kh.sdt, kh.email, COUNT(*) AS so_lan_di, SUM(v.tongTien) AS tongTien " +
                "FROM View_VeTongTien v " +
                "JOIN KhachHang kh ON v.maKH = kh.maKH " +
                "WHERE v.ngayGioXuatVe BETWEEN ?1 AND ?2 " +
                "GROUP BY kh.maKH, kh.tenKH, kh.sdt, kh.email " +
                "ORDER BY so_lan_di DESC";

        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter(1, startDate);
            query.setParameter(2, endDate);

            List<Object[]> queryResult = query.getResultList();
            for (Object[] row : queryResult) {
                // row[0]: maKH, row[1]: tenKH, row[2]: sdt, row[3]: email, row[4]: so_lan_di, row[5]: tongTien
                result.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Có thể xử lý hiển thị lỗi tại đây nếu muốn
            return null;
        }
        return result;
    }
    @Override
    public  Object[] getTiLeDoiTraVe(Date startDate, Date endDate)throws RemoteException {
        Object[] result = new Object[4];
        String query = "WITH VeBan AS (" +
                "    SELECT COUNT(*) AS SoLuongVeDaBan " +
                "    FROM Ve " +
                "    WHERE trangThai = N'Đã bán' AND ngayGioXuatVe BETWEEN ?1 AND ?2" +
                "), " +
                "VeDoi AS (" +
                "    SELECT COUNT(*) AS SoLuongVeDaDoi " +
                "    FROM Ve " +
                "    WHERE trangThai IN (N'Đã đổi', N'Vé được đổi') AND ngayGioXuatVe BETWEEN ?3 AND ?4" +
                "), " +
                "VeTra AS (" +
                "    SELECT COUNT(*) AS SoLuongVeDaTra " +
                "    FROM Ve " +
                "    WHERE trangThai = N'Đã trả' AND ngayGioXuatVe BETWEEN ?5 AND ?6" +
                ") " +
                "SELECT " +
                "    ISNULL(CAST((VeDoi.SoLuongVeDaDoi * 100.0) AS DECIMAL(5,2)) / NULLIF(VeBan.SoLuongVeDaBan, 0), 0) AS TiLeDoi, " +
                "    VeDoi.SoLuongVeDaDoi AS SoLuongVeDoi, " +
                "    ISNULL(CAST((VeTra.SoLuongVeDaTra * 100.0) AS DECIMAL(5,2)) / NULLIF(VeBan.SoLuongVeDaBan, 0), 0) AS TiLeTra, " +
                "    VeTra.SoLuongVeDaTra AS SoLuongVeTra " +
                "FROM VeBan, VeDoi, VeTra;";

        try {
            Query q = em.createNativeQuery(query);
            // Set all date parameters (6 tham số)
            q.setParameter(1, startDate);
            q.setParameter(2, endDate);
            q.setParameter(3, startDate);
            q.setParameter(4, endDate);
            q.setParameter(5, startDate);
            q.setParameter(6, endDate);

            Object[] row = (Object[]) q.getSingleResult();
            // row[0]: TiLeDoi (Double), row[1]: SoLuongVeDoi (Integer), row[2]: TiLeTra (Double), row[3]: SoLuongVeTra (Integer)
            result[0] = row[0];
            result[1] = row[1];
            result[2] = row[2];
            result[3] = row[3];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }
    @Override
    public  List<Object[]> getThongKeVeDoiTheoNgay(Date startDate, Date endDate)throws RemoteException {
        List<Object[]> results = new ArrayList<>();
        String query =
                "WITH DateSeries AS ( " +
                        "    SELECT CAST(?1 AS DATE) AS DateValue " +
                        "    UNION ALL " +
                        "    SELECT DATEADD(day, 1, DateValue) " +
                        "    FROM DateSeries " +
                        "    WHERE DateValue < ?2 " +
                        "), " +
                        "VeDoiTheoNgay AS ( " +
                        "    SELECT " +
                        "        CAST(ngayGioXuatVe AS DATE) AS NgayDoiVe, " +
                        "        COUNT(*) AS SoLuongVeDaDoi " +
                        "    FROM Ve " +
                        "    WHERE trangThai IN (N'Đã đổi') " +
                        "      AND CAST(ngayGioXuatVe AS DATE) BETWEEN ?3 AND ?4 " +
                        "    GROUP BY CAST(ngayGioXuatVe AS DATE) " +
                        ") " +
                        "SELECT " +
                        "    ds.DateValue AS Ngay, " +
                        "    ISNULL(vdtn.SoLuongVeDaDoi, 0) AS SoLuongVeDoi " +
                        "FROM DateSeries ds " +
                        "LEFT JOIN VeDoiTheoNgay vdtn ON ds.DateValue = vdtn.NgayDoiVe " +
                        "ORDER BY ds.DateValue " +
                        "OPTION (MAXRECURSION 1000);"; // Tăng giới hạn đệ quy nếu cần

        try {
            Query q = em.createNativeQuery(query);
            q.setParameter(1, startDate);
            q.setParameter(2, endDate);
            q.setParameter(3, startDate);
            q.setParameter(4, endDate);

            @SuppressWarnings("unchecked")
            List<Object[]> queryResult = q.getResultList();
            for (Object[] row : queryResult) {
                // row[0]: Ngay (Date), row[1]: SoLuongVeDoi (Integer)
                results.add(row);
            }
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains("maximum recursion")) {
                System.err.println("Lỗi: Đạt giới hạn đệ quy tối đa trong CTE. " +
                        "Vui lòng kiểm tra khoảng thời gian thống kê hoặc điều chỉnh giới hạn đệ quy trong cơ sở dữ liệu.");
            } else {
                System.err.println("Lỗi SQL: " + e.getMessage());
            }
            e.printStackTrace();
            return null;
        }
        return results;
    }
    @Override
    public  List<Object[]> getThongKeVeDoiTheoTuan(Date startDate, Date endDate)throws RemoteException {
        List<Object[]> results = new ArrayList<>();
        String query =
                "WITH WeekSeries AS ( " +
                        "    SELECT DATEPART(WEEK, ?1) AS Tuan " +
                        "    UNION ALL " +
                        "    SELECT Tuan + 1 " +
                        "    FROM WeekSeries " +
                        "    WHERE Tuan < DATEPART(WEEK, ?2) " +
                        "), " +
                        "VeDoiTheoTuan AS ( " +
                        "    SELECT DATEPART(WEEK, ngayGioXuatVe) AS Tuan, COUNT(*) AS SoLuongVeDaDoi " +
                        "    FROM Ve " +
                        "    WHERE trangThai IN (N'Đã đổi') " +
                        "      AND ngayGioXuatVe BETWEEN ?3 AND ?4 " +
                        "    GROUP BY DATEPART(WEEK, ngayGioXuatVe) " +
                        ") " +
                        "SELECT ws.Tuan, ISNULL(vdtn.SoLuongVeDaDoi, 0) AS SoLuongVeDoi " +
                        "FROM WeekSeries ws " +
                        "LEFT JOIN VeDoiTheoTuan vdtn ON ws.Tuan = vdtn.Tuan " +
                        "ORDER BY ws.Tuan " +
                        "OPTION (MAXRECURSION 100);"; // Có thể tăng MAXRECURSION nếu thống kê nhiều tuần

        try {
            Query q = em.createNativeQuery(query);
            q.setParameter(1, startDate);
            q.setParameter(2, endDate);
            q.setParameter(3, startDate);
            q.setParameter(4, endDate);

            @SuppressWarnings("unchecked")
            List<Object[]> queryResult = q.getResultList();
            for (Object[] row : queryResult) {
                // row[0]: Tuan (Integer), row[1]: SoLuongVeDoi (Integer)
                results.add(row);
            }
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains("maximum recursion")) {
                System.err.println("Lỗi: Đạt giới hạn đệ quy tối đa trong CTE. " +
                        "Vui lòng kiểm tra khoảng thời gian thống kê hoặc điều chỉnh giới hạn đệ quy trong cơ sở dữ liệu.");
            } else {
                System.err.println("Lỗi SQL: " + e.getMessage());
            }
            e.printStackTrace();
            return null;
        }
        return results;
    }
    @Override
    public  List<Object[]> getThongKeVeDoiTheoThang(Date startDate, Date endDate)throws RemoteException {
        List<Object[]> results = new ArrayList<>();
        String query =
                "WITH DateSeries AS ( " +
                        "    SELECT MONTH(?1) AS Thang, YEAR(?2) AS Nam " +
                        "    UNION ALL " +
                        "    SELECT CASE WHEN Thang = 12 THEN 1 ELSE Thang + 1 END, CASE WHEN Thang = 12 THEN Nam + 1 ELSE Nam END " +
                        "    FROM DateSeries " +
                        "    WHERE (Thang < MONTH(?3) AND Nam <= YEAR(?4)) OR Nam < YEAR(?5) " +
                        "), " +
                        "VeDoiTheoThang AS ( " +
                        "    SELECT MONTH(ngayGioXuatVe) AS Thang, YEAR(ngayGioXuatVe) AS Nam, COUNT(*) AS SoLuongVeDaDoi " +
                        "    FROM Ve " +
                        "    WHERE trangThai IN (N'Đã đổi') " +
                        "      AND ngayGioXuatVe BETWEEN ?6 AND ?7 " +
                        "    GROUP BY MONTH(ngayGioXuatVe), YEAR(ngayGioXuatVe) " +
                        ") " +
                        "SELECT ds.Thang, ds.Nam, ISNULL(vdtt.SoLuongVeDaDoi, 0) AS SoLuongVeDoi " +
                        "FROM DateSeries ds " +
                        "LEFT JOIN VeDoiTheoThang vdtt ON ds.Thang = vdtt.Thang AND ds.Nam = vdtt.Nam " +
                        "ORDER BY ds.Nam, ds.Thang " +
                        "OPTION (MAXRECURSION 100);";

        try {
            Query q = em.createNativeQuery(query);
            q.setParameter(1, startDate); // MONTH(?1)
            q.setParameter(2, startDate); // YEAR(?2)
            q.setParameter(3, endDate);   // MONTH(?3)
            q.setParameter(4, endDate);   // YEAR(?4)
            q.setParameter(5, endDate);   // YEAR(?5)
            q.setParameter(6, startDate); // BETWEEN ?6
            q.setParameter(7, endDate);   // BETWEEN ?7

            @SuppressWarnings("unchecked")
            List<Object[]> queryResult = q.getResultList();
            for (Object[] row : queryResult) {
                // row[0]: Thang (Integer), row[1]: Nam (Integer), row[2]: SoLuongVeDoi (Integer)
                results.add(row);
            }
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains("maximum recursion")) {
                System.err.println("Lỗi: Đạt giới hạn đệ quy tối đa trong CTE. " +
                        "Vui lòng kiểm tra khoảng thời gian thống kê hoặc điều chỉnh giới hạn đệ quy trong cơ sở dữ liệu.");
            } else {
                System.err.println("Lỗi SQL: " + e.getMessage());
            }
            e.printStackTrace();
            return null;
        }
        return results;
    }
    @Override
    public  List<Object[]> getThongKeVeDoiTheoQuy(Date startDate, Date endDate)throws RemoteException {
        List<Object[]> results = new ArrayList<>();
        String query =
                "WITH DateSeries AS ( " +
                        "    SELECT DATEPART(QUARTER, ?1) AS Quy, YEAR(?2) AS Nam " +
                        "    UNION ALL " +
                        "    SELECT Quy + 1, Nam " +
                        "    FROM DateSeries " +
                        "    WHERE (Quy < DATEPART(QUARTER, ?3) AND Nam <= YEAR(?4)) OR Nam < YEAR(?5) " +
                        "), " +
                        "VeDoiTheoQuy AS ( " +
                        "    SELECT DATEPART(QUARTER, ngayGioXuatVe) AS Quy, YEAR(ngayGioXuatVe) AS Nam, COUNT(*) AS SoLuongVeDaDoi " +
                        "    FROM Ve " +
                        "    WHERE trangThai IN (N'Đã đổi') " +
                        "      AND ngayGioXuatVe BETWEEN ?6 AND ?7 " +
                        "    GROUP BY DATEPART(QUARTER, ngayGioXuatVe), YEAR(ngayGioXuatVe) " +
                        ") " +
                        "SELECT ds.Quy, ds.Nam, ISNULL(vdtt.SoLuongVeDaDoi, 0) AS SoLuongVeDoi " +
                        "FROM DateSeries ds " +
                        "LEFT JOIN VeDoiTheoQuy vdtt ON ds.Quy = vdtt.Quy AND ds.Nam = vdtt.Nam " +
                        "ORDER BY ds.Nam, ds.Quy " +
                        "OPTION (MAXRECURSION 100);";
        try {
            Query q = em.createNativeQuery(query);
            q.setParameter(1, startDate);
            q.setParameter(2, startDate);
            q.setParameter(3, endDate);
            q.setParameter(4, endDate);
            q.setParameter(5, endDate);
            q.setParameter(6, startDate);
            q.setParameter(7, endDate);

            @SuppressWarnings("unchecked")
            List<Object[]> queryResult = q.getResultList();
            for (Object[] row : queryResult) {
                // row[0]: Quy (Integer), row[1]: Nam (Integer), row[2]: SoLuongVeDoi (Integer)
                results.add(row);
            }
        } catch (Exception e) {
            System.err.println("Lỗi SQL: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        return results;
    }
    @Override
    public  List<Object[]> getThongKeVeDoiTheoNam(Date startDate, Date endDate)throws RemoteException {
        List<Object[]> results = new ArrayList<>();
        String query =
                "WITH DateSeries AS ( " +
                        "    SELECT YEAR(?1) AS Nam " +
                        "    UNION ALL " +
                        "    SELECT Nam + 1 " +
                        "    FROM DateSeries " +
                        "    WHERE Nam < YEAR(?2) " +
                        "), " +
                        "VeDoiTheoNam AS ( " +
                        "    SELECT YEAR(ngayGioXuatVe) AS Nam, COUNT(*) AS SoLuongVeDaDoi " +
                        "    FROM Ve " +
                        "    WHERE trangThai IN (N'Đã đổi') " +
                        "      AND ngayGioXuatVe BETWEEN ?3 AND ?4 " +
                        "    GROUP BY YEAR(ngayGioXuatVe) " +
                        ") " +
                        "SELECT ds.Nam, ISNULL(vdtn.SoLuongVeDaDoi, 0) AS SoLuongVeDoi " +
                        "FROM DateSeries ds " +
                        "LEFT JOIN VeDoiTheoNam vdtn ON ds.Nam = vdtn.Nam " +
                        "ORDER BY ds.Nam " +
                        "OPTION (MAXRECURSION 100);";
        try {
            Query q = em.createNativeQuery(query);
            q.setParameter(1, startDate);
            q.setParameter(2, endDate);
            q.setParameter(3, startDate);
            q.setParameter(4, endDate);

            @SuppressWarnings("unchecked")
            List<Object[]> queryResult = q.getResultList();
            for (Object[] row : queryResult) {
                // row[0]: Nam (Integer), row[1]: SoLuongVeDoi (Integer)
                results.add(row);
            }
        } catch (Exception e) {
            System.err.println("Lỗi SQL: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        return results;
    }
    @Override
    public  List<Object[]> getThongKeVeTraTheoNgay(Date startDate, Date endDate)throws RemoteException {
        List<Object[]> results = new ArrayList<>();
        String query =
                "WITH DateSeries AS ( " +
                        "    SELECT CAST(?1 AS DATE) AS DateValue " +
                        "    UNION ALL " +
                        "    SELECT DATEADD(day, 1, DateValue) " +
                        "    FROM DateSeries " +
                        "    WHERE DateValue < ?2 " +
                        "), " +
                        "VeTraTheoNgay AS ( " +
                        "    SELECT CAST(ngayGioXuatVe AS DATE) AS NgayTraVe, COUNT(*) AS SoLuongVeDaTra " +
                        "    FROM Ve " +
                        "    WHERE trangThai IN (N'Đã trả') " +
                        "      AND CAST(ngayGioXuatVe AS DATE) BETWEEN ?3 AND ?4 " +
                        "    GROUP BY CAST(ngayGioXuatVe AS DATE) " +
                        ") " +
                        "SELECT ds.DateValue AS Ngay, ISNULL(vttn.SoLuongVeDaTra, 0) AS SoLuongVeTra " +
                        "FROM DateSeries ds " +
                        "LEFT JOIN VeTraTheoNgay vttn ON ds.DateValue = vttn.NgayTraVe " +
                        "ORDER BY ds.DateValue " +
                        "OPTION (MAXRECURSION 100);";

        try {
            Query q = em.createNativeQuery(query);
            q.setParameter(1, startDate);
            q.setParameter(2, endDate);
            q.setParameter(3, startDate);
            q.setParameter(4, endDate);

            @SuppressWarnings("unchecked")
            List<Object[]> queryResult = q.getResultList();
            for (Object[] row : queryResult) {
                // row[0]: Ngay (Date), row[1]: SoLuongVeTra (Integer)
                results.add(row);
            }
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains("maximum recursion")) {
                System.err.println("Lỗi: Đạt giới hạn đệ quy tối đa trong CTE. " +
                        "Vui lòng kiểm tra khoảng thời gian thống kê hoặc điều chỉnh giới hạn đệ quy trong cơ sở dữ liệu.");
            } else {
                System.err.println("Lỗi SQL: " + e.getMessage());
            }
            e.printStackTrace();
            return null;
        }
        return results;
    }
    @Override
    public  List<Object[]> getThongKeVeTraTheoTuan(Date startDate, Date endDate)throws RemoteException {
        List<Object[]> results = new ArrayList<>();
        String query =
                "WITH DateSeries AS ( " +
                        "    SELECT DATEPART(WEEK, ?1) AS Tuan " +
                        "    UNION ALL " +
                        "    SELECT Tuan + 1 " +
                        "    FROM DateSeries " +
                        "    WHERE Tuan < DATEPART(WEEK, ?2) " +
                        "), " +
                        "VeTraTheoTuan AS ( " +
                        "    SELECT DATEPART(WEEK, ngayGioXuatVe) AS Tuan, COUNT(*) AS SoLuongVeDaTra " +
                        "    FROM Ve " +
                        "    WHERE trangThai IN (N'Đã trả') " +
                        "      AND ngayGioXuatVe BETWEEN ?3 AND ?4 " +
                        "    GROUP BY DATEPART(WEEK, ngayGioXuatVe) " +
                        ") " +
                        "SELECT ds.Tuan, ISNULL(vttn.SoLuongVeDaTra, 0) AS SoLuongVeTra " +
                        "FROM DateSeries ds " +
                        "LEFT JOIN VeTraTheoTuan vttn ON ds.Tuan = vttn.Tuan " +
                        "ORDER BY ds.Tuan " +
                        "OPTION (MAXRECURSION 100);";
        try {
            Query q = em.createNativeQuery(query);
            q.setParameter(1, startDate);
            q.setParameter(2, endDate);
            q.setParameter(3, startDate);
            q.setParameter(4, endDate);

            @SuppressWarnings("unchecked")
            List<Object[]> queryResult = q.getResultList();
            for (Object[] row : queryResult) {
                // row[0]: Tuan (Integer), row[1]: SoLuongVeTra (Integer)
                results.add(row);
            }
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains("maximum recursion")) {
                System.err.println("Lỗi: Đạt giới hạn đệ quy tối đa trong CTE. " +
                        "Vui lòng kiểm tra khoảng thời gian thống kê hoặc điều chỉnh giới hạn đệ quy trong cơ sở dữ liệu.");
            } else {
                System.err.println("Lỗi SQL: " + e.getMessage());
            }
            e.printStackTrace();
            return null;
        }
        return results;
    }
    @Override
    public  List<Object[]> getThongKeVeTraTheoThang(Date startDate, Date endDate)throws RemoteException {
        List<Object[]> results = new ArrayList<>();
        String query =
                "WITH DateSeries AS ( " +
                        "    SELECT MONTH(?1) AS Thang, YEAR(?2) AS Nam " +
                        "    UNION ALL " +
                        "    SELECT CASE WHEN Thang = 12 THEN 1 ELSE Thang + 1 END, " +
                        "           CASE WHEN Thang = 12 THEN Nam + 1 ELSE Nam END " +
                        "    FROM DateSeries " +
                        "    WHERE (Thang < MONTH(?3) AND Nam <= YEAR(?4)) OR Nam < YEAR(?5) " +
                        "), " +
                        "VeTraTheoThang AS ( " +
                        "    SELECT MONTH(ngayGioXuatVe) AS Thang, YEAR(ngayGioXuatVe) AS Nam, COUNT(*) AS SoLuongVeDaTra " +
                        "    FROM Ve " +
                        "    WHERE trangThai IN (N'Đã trả') " +
                        "      AND ngayGioXuatVe BETWEEN ?6 AND ?7 " +
                        "    GROUP BY MONTH(ngayGioXuatVe), YEAR(ngayGioXuatVe) " +
                        ") " +
                        "SELECT ds.Thang, ds.Nam, ISNULL(vtt.SoLuongVeDaTra, 0) AS SoLuongVeTra " +
                        "FROM DateSeries ds " +
                        "LEFT JOIN VeTraTheoThang vtt ON ds.Thang = vtt.Thang AND ds.Nam = vtt.Nam " +
                        "ORDER BY ds.Nam, ds.Thang " +
                        "OPTION (MAXRECURSION 100);";

        try {
            Query q = em.createNativeQuery(query);
            q.setParameter(1, startDate); // MONTH(?1)
            q.setParameter(2, startDate); // YEAR(?2)
            q.setParameter(3, endDate);   // MONTH(?3)
            q.setParameter(4, endDate);   // YEAR(?4)
            q.setParameter(5, endDate);   // YEAR(?5)
            q.setParameter(6, startDate); // BETWEEN ?6
            q.setParameter(7, endDate);   // BETWEEN ?7

            @SuppressWarnings("unchecked")
            List<Object[]> queryResult = q.getResultList();
            for (Object[] row : queryResult) {
                // row[0]: Thang (Integer), row[1]: Nam (Integer), row[2]: SoLuongVeTra (Integer)
                results.add(row);
            }
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains("maximum recursion")) {
                System.err.println("Lỗi: Đạt giới hạn đệ quy tối đa trong CTE. " +
                        "Vui lòng kiểm tra khoảng thời gian thống kê hoặc điều chỉnh giới hạn đệ quy trong cơ sở dữ liệu.");
            } else {
                System.err.println("Lỗi SQL: " + e.getMessage());
            }
            e.printStackTrace();
            return null;
        }
        return results;
    }
    @Override
    public List<Object[]> getThongKeVeTraTheoQuy(Date startDate, Date endDate)throws RemoteException {
        List<Object[]> results = new ArrayList<>();
        String query =
                "WITH DateSeries AS ( " +
                        "    SELECT DATEPART(QUARTER, ?1) AS Quy, YEAR(?2) AS Nam " +
                        "    UNION ALL " +
                        "    SELECT Quy + 1, Nam " +
                        "    FROM DateSeries " +
                        "    WHERE (Quy < DATEPART(QUARTER, ?3) AND Nam <= YEAR(?4)) OR Nam < YEAR(?5) " +
                        "), " +
                        "VeTraTheoQuy AS ( " +
                        "    SELECT DATEPART(QUARTER, ngayGioXuatVe) AS Quy, YEAR(ngayGioXuatVe) AS Nam, COUNT(*) AS SoLuongVeDaTra " +
                        "    FROM Ve " +
                        "    WHERE trangThai IN (N'Đã trả') " +
                        "      AND ngayGioXuatVe BETWEEN ?6 AND ?7 " +
                        "    GROUP BY DATEPART(QUARTER, ngayGioXuatVe), YEAR(ngayGioXuatVe) " +
                        ") " +
                        "SELECT ds.Quy, ds.Nam, ISNULL(vtt.SoLuongVeDaTra, 0) AS SoLuongVeTra " +
                        "FROM DateSeries ds " +
                        "LEFT JOIN VeTraTheoQuy vtt ON ds.Quy = vtt.Quy AND ds.Nam = vtt.Nam " +
                        "ORDER BY ds.Nam, ds.Quy " +
                        "OPTION (MAXRECURSION 100);";

        try {
            Query q = em.createNativeQuery(query);
            q.setParameter(1, startDate); // DATEPART(QUARTER, ?1)
            q.setParameter(2, startDate); // YEAR(?2)
            q.setParameter(3, endDate);   // DATEPART(QUARTER, ?3)
            q.setParameter(4, endDate);   // YEAR(?4)
            q.setParameter(5, endDate);   // YEAR(?5)
            q.setParameter(6, startDate); // BETWEEN ?6
            q.setParameter(7, endDate);   // BETWEEN ?7

            @SuppressWarnings("unchecked")
            List<Object[]> queryResult = q.getResultList();
            for (Object[] row : queryResult) {
                // row[0]: Quy (Integer), row[1]: Nam (Integer), row[2]: SoLuongVeTra (Integer)
                results.add(row);
            }
        } catch (Exception e) {
            System.err.println("Lỗi SQL: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        return results;
    }
    @Override
    public List<Object[]> getThongKeVeTraTheoNam(Date startDate, Date endDate)throws RemoteException {
        List<Object[]> results = new ArrayList<>();
        String query =
                "WITH DateSeries AS ( " +
                        "    SELECT YEAR(?1) AS Nam " +
                        "    UNION ALL " +
                        "    SELECT Nam + 1 " +
                        "    FROM DateSeries " +
                        "    WHERE Nam < YEAR(?2) " +
                        "), " +
                        "VeTraTheoNam AS ( " +
                        "    SELECT YEAR(ngayGioXuatVe) AS Nam, COUNT(*) AS SoLuongVeDaTra " +
                        "    FROM Ve " +
                        "    WHERE trangThai IN (N'Đã trả') " +
                        "      AND ngayGioXuatVe BETWEEN ?3 AND ?4 " +
                        "    GROUP BY YEAR(ngayGioXuatVe) " +
                        ") " +
                        "SELECT ds.Nam, ISNULL(vttn.SoLuongVeDaTra, 0) AS SoLuongVeTra " +
                        "FROM DateSeries ds " +
                        "LEFT JOIN VeTraTheoNam vttn ON ds.Nam = vttn.Nam " +
                        "ORDER BY ds.Nam " +
                        "OPTION (MAXRECURSION 100);";

        try {
            Query q = em.createNativeQuery(query);
            q.setParameter(1, startDate); // YEAR(?1)
            q.setParameter(2, endDate);   // YEAR(?2)
            q.setParameter(3, startDate); // BETWEEN ?3
            q.setParameter(4, endDate);   // BETWEEN ?4

            @SuppressWarnings("unchecked")
            List<Object[]> queryResult = q.getResultList();
            for (Object[] row : queryResult) {
                // row[0]: Nam (Integer), row[1]: SoLuongVeTra (Integer)
                results.add(row);
            }
        } catch (Exception e) {
            System.err.println("Lỗi SQL: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        return results;
    }
    @Override
    public List<Object> dsVeTheoNgay_MaVe_ListObject(Date ngayXuatVe, String maLoaiVe)throws RemoteException {
        List<Object> results = new ArrayList<>();
        String query = "SELECT * FROM Ve WHERE CAST(ngayGioXuatVe AS DATE) = ?1 AND maLoaiVe = ?2";
        try {
            Query q = em.createNativeQuery(query);
            q.setParameter(1, ngayXuatVe);
            q.setParameter(2, maLoaiVe);

            @SuppressWarnings("unchecked")
            List<Object[]> queryResult = q.getResultList();
            results.addAll(queryResult);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return results;
    }
}
