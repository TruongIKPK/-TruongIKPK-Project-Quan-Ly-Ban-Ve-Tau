package control.impl;

import connectDB.ConnectDB;
import connectDB.connectDB_1;
import control.IDAOVe;
import entity.*;
import enums.ETrangThaiVe;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import service.VeService;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAOVe extends UnicastRemoteObject implements IDAOVe {

    private EntityManager em = connectDB_1.getEntityManager();

    public DAOVe() throws RemoteException {
    }
    @Override
    public boolean themVeCoKhuyenMai(Ve ve)throws RemoteException {
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
    @Override
    public boolean themVeKhongKhuyenMai(Ve ve)throws RemoteException {
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
    @Override
    public boolean suaVe(Ve ve)throws RemoteException {
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
    @Override
    public ArrayList<Ve> layDSVeTheoMaHD(String maHD)throws RemoteException {
        ArrayList<Ve> dsVe = new ArrayList<>();
        String sql = "SELECT v FROM Ve v WHERE v.hoaDon.maHD = :maHD";
        try {
            dsVe = (ArrayList<Ve>) em.createQuery(sql, Ve.class).setParameter("maHD", maHD).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsVe;
    }
    @Override
    public ArrayList<Ve> layDSVeDaBanTheoMaHD(String maHD)throws RemoteException {
        ArrayList<Ve> dsVe = new ArrayList<>();
        String sql = "SELECT v FROM Ve v WHERE v.hoaDon.maHD = :maHD AND v.trangThai = :trangThai";
        try {
            dsVe = (ArrayList<Ve>) em.createQuery(sql, Ve.class).setParameter("maHD", maHD).setParameter("trangThai", ETrangThaiVe.DA_BAN.name()).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsVe;
    }
    @Override
    public ArrayList<Ve> layDSVeTheoMaChuyen(String maChuyen)throws RemoteException {
        ArrayList<Ve> dsVe = new ArrayList<>();
        String sql = "SELECT v FROM Ve v WHERE v.chuyenTau.maChuyen = :maChuyen";
        try {
            dsVe = (ArrayList<Ve>) em.createQuery(sql, Ve.class).setParameter("maChuyen", maChuyen).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsVe;
    }
    @Override
    public ArrayList<Ve> layDSVeTheoMaKH(String maKH)throws RemoteException {
        ArrayList<Ve> dsVe = new ArrayList<>();
        String sql = "SELECT v FROM Ve v WHERE v.khachHang.maKH = :maKH";
        try {
            dsVe = (ArrayList<Ve>) em.createQuery(sql, Ve.class).setParameter("maKH", maKH).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsVe;
    }
    @Override
    public Ve layVeTheoMa(String maVe)throws RemoteException {
        Ve ve = null;
        try {
            ve = em.find(Ve.class, maVe);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("VE--------------------------------------------------------------------------------------------------------Ve: " + ve);
        return ve;
    }
    @Override
    public Ve layVeTheoMaChuyenVaMaKH(String maChuyen, String maKH)throws RemoteException {
        Ve ve = null;
        String sql = "SELECT v FROM Ve v WHERE v.chuyenTau.maChuyen = :maChuyen AND v.khachHang.maKH = :maKH";
        try {
            ve = (Ve) em.createQuery(sql, Ve.class).setParameter("maChuyen", maChuyen).setParameter("maKH", maKH).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ve;
    }
    @Override
    public ArrayList<Ve> layDSVeTheoSDT(String sdt) throws RemoteException{
        ArrayList<Ve> dsVe = new ArrayList<>();
        String sql = "SELECT v FROM Ve v WHERE v.khachHang.sdt = :sdt";
        try {
            dsVe = (ArrayList<Ve>) em.createQuery(sql, Ve.class).setParameter("sdt", sdt).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsVe;
    }
    @Override
    public ArrayList<Ve> layDSVeGanNhat() throws RemoteException{
        ArrayList<Ve> dsVe = new ArrayList<>();
        String sql = "SELECT v FROM Ve v ORDER BY v.ngayGioXuatVe DESC";
        try {
            dsVe = (ArrayList<Ve>) em.createQuery(sql, Ve.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsVe;
    }
    @Override
    public ArrayList<Ve> layDSVeDaBanTheoMaChuyen(String maChuyen)throws RemoteException {
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
    @Override
    public String layDoanhThuTheoNgay(Date ngay)throws RemoteException {
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
    @Override
    public String layDoanhThuTheoNgayHienTai() throws RemoteException{
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
    @Override
    public String layDoanhThuTuanHienTai()throws RemoteException {
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
    @Override
    public String layDoanhThuThangHienTai()throws RemoteException {
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
    @Override
    public String layDoanhThuQuyHienTai()throws RemoteException {
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
    @Override
    public String layDoanhThuNamHienTai()throws RemoteException {
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
    @Override
    public String layDoanhThuNgayHomQua()throws RemoteException {
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
    @Override
    public String layDoanhThuTuanVuaRoi()throws RemoteException {
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
    @Override
    public String layDoanhThuThangVuaRoi()throws RemoteException {
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
    @Override
    public String layDoanhThuQuyVuaRoi()throws RemoteException {
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
    @Override
    public String layDoanhThuNamVuaRoi()throws RemoteException {
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
    @Override
    public double getBestSellingHourOfDay()throws RemoteException {
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
    @Override
    public double getAverageBestSellingHourOfWeek()throws RemoteException {
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
    @Override
    public double getAverageBestSellingHourOfMonth()throws RemoteException {
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
    @Override
    public double getAverageBestSellingHourOfQuarter()throws RemoteException {
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
    @Override
    public double getAverageBestSellingHourOfYear()throws RemoteException {
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
    @Override
    public String getSlowestSalesTimeLastWeek()throws RemoteException {
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
    @Override
    public String getSlowestSalesTimeLastMonth()throws RemoteException {
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
    @Override
    public String getSlowestSalesTimeLastQuarter()throws RemoteException {
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
    @Override
    public String getSlowestSalesTimeLastYear()throws RemoteException {
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
    @Override
    public ArrayList<Ve> getDanhSachVeDaTra()throws RemoteException {
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
