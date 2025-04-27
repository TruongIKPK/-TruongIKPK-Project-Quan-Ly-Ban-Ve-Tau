package control.impl;

import connectDB.ConnectDB;
import connectDB.connectDB_1;
import control.IDAOVe;
import entity.*;
import enums.ETrangThaiVe;
import jakarta.persistence.*;
import remote.ISeatUpdateListener;
import server.SeatLockManager;
import service.VeService;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DAOVe extends UnicastRemoteObject implements IDAOVe {
    private final EntityManager em = connectDB_1.getEntityManager();
    private final ConcurrentHashMap<String, ISeatUpdateListener> listeners = new ConcurrentHashMap<>();
    private final SeatLockManager seatLockManager = new SeatLockManager();
    public DAOVe() throws RemoteException {
    }

    // Thêm các phương thức quản lý listener
    @Override
    public void registerSeatUpdateListener(String username, ISeatUpdateListener listener) throws RemoteException {
        System.out.println("Client đăng ký listener: " + username);
        listeners.put(username, listener);
    }

    @Override
    public void unregisterSeatUpdateListener(String username) throws RemoteException {
        System.out.println("Client hủy đăng ký listener: " + username);
        listeners.remove(username);
    }

    @Override
    public void updateSeatStatus(String seatId, String status) throws RemoteException {

    }


    @Override
    public void unlockSeat(String trainId, String seatId, String userId) throws RemoteException {
        seatLockManager.unlockSeat(trainId, seatId, userId);
    }

    // Phương thức để thông báo cho tất cả listeners
    private void notifyAllListeners(Ve ve) {
        // Tạo một bản sao của map để tránh ConcurrentModificationException
        new HashMap<>(listeners).forEach((username, listener) -> {
            try {
                listener.onSeatStatusChanged(ve);
            } catch (RemoteException e) {
                System.out.println("Lỗi khi thông báo đến client " + username);
                listeners.remove(username);
                e.printStackTrace();
            }
        });
    }

    //version1 : xử lý bất đồng bộ
    //lock danh sách ghế
    @Override
    public boolean lockSeatsForBooking(String trainId, List<String> seatIds, String userId) throws RemoteException {
//        System.out.println("Attempting to lock seats for train: " + trainId + ", user: " + userId);
        seatLockManager.printLocks(); // Debug

        boolean allLocked = true;
        List<String> lockedSeats = new ArrayList<>();

        // Thử khóa từng ghế
        for (String seatId : seatIds) {
            if (seatLockManager.lockSeat(trainId, seatId, userId)) {
                lockedSeats.add(seatId);
//                System.out.println("Successfully locked seat: " + seatId);
            } else {
                allLocked = false;
//                System.out.println("Failed to lock seat: " + seatId);
                break;
            }
        }

        // Nếu không khóa được tất cả, giải phóng các ghế đã khóa
        if (!allLocked) {
            System.out.println("Rolling back locks due to failure");
            for (String seatId : lockedSeats) {
                seatLockManager.unlockSeat(trainId, seatId, userId);
            }
            return false;
        }

        System.out.println("All seats locked successfully");
        seatLockManager.printLocks(); // Debug
        return true;
    }

    @Override
    public synchronized boolean themVeCoKhuyenMai(Ve ve) throws RemoteException {
//        System.out.println("Thêm vé: " + ve);
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            VeService veService = new VeService(em);
            veService.persistVe(ve);
            transaction.commit();
            
            // Thông báo cho tất cả clients khác
            notifyAllListeners(ve);
            
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
//        System.out.println("Veeeeeeeeeeeeeeeeeeeee"+ve);
        try {
            transaction.begin();

                VeService veService = new VeService(em);
                veService.persistVe(ve);
            transaction.commit();
            notifyAllListeners(ve);
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
                notifyAllListeners(ve);
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
    //    System.out.println("VE--------------------------------------------------------------------------------------------------------Ve: " + ve);
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
    public synchronized ArrayList<Ve> layDSVeDaBanTheoMaChuyen(String maChuyen)throws RemoteException {
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
    public String layDoanhThuTheoNgayHienTai()throws RemoteException {
        try {
            String jpql = "SELECT SUM(lc.giaCho) FROM Ve v " +
                    "JOIN v.choNgoi cn " +
                    "JOIN cn.loaiCho lc " +
                    "WHERE CAST(v.ngayGioXuatVe AS date) = CURRENT_DATE";
            Object result = em.createQuery(jpql).getSingleResult();
            return result != null ? result.toString() : "0";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }
    @Override
    public String layDoanhThuTuanHienTai() {
        String jpql = "SELECT SUM(lc.giaCho) " +
                "FROM Ve v " +
                "JOIN v.choNgoi cn " +  // Sử dụng quan hệ JPA (nếu có mapping đúng)
                "JOIN cn.loaiCho lc " +  // Quan hệ JPA giữa ChoNgoi và LoaiCho
                "WHERE v.ngayGioXuatVe >= :startOfWeek " +
                "AND v.ngayGioXuatVe < :endOfWeek";

        // Lấy ngày bắt đầu tuần và kết thúc tuần theo hệ thống
        LocalDate today = LocalDate.now();
        LocalDate startOfWeekDate = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeekDate = startOfWeekDate.plusDays(7);
        LocalDateTime startOfWeek = startOfWeekDate.atStartOfDay();
        LocalDateTime endOfWeek = endOfWeekDate.atStartOfDay();

        try {
            Query query = em.createQuery(jpql);
            query.setParameter("startOfWeek", startOfWeek);
            query.setParameter("endOfWeek", endOfWeek);

            // Lấy kết quả
            Object result = query.getSingleResult();
            if (result != null) {
                return result.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }
    @Override
    public String layDoanhThuThangHienTai()throws RemoteException {
        try {
            LocalDate now = LocalDate.now();
            LocalDateTime startOfMonth = now.with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay();
            LocalDateTime endOfMonth = now.with(TemporalAdjusters.lastDayOfMonth()).plusDays(1).atStartOfDay(); // cộng thêm 1 ngày

            TypedQuery<BigDecimal> query = em.createQuery(
                    "SELECT SUM(lc.giaCho) FROM Ve v " +
                            "JOIN v.choNgoi cn " +
                            "JOIN cn.loaiCho lc " +
                            "WHERE v.ngayGioXuatVe >= :startOfMonth " +
                            "AND v.ngayGioXuatVe < :endOfMonth", BigDecimal.class);

            query.setParameter("startOfMonth", startOfMonth);
            query.setParameter("endOfMonth", endOfMonth);

            Object result = query.getSingleResult();
            if (result instanceof Double) {
                // Chuyển đổi từ Double sang BigDecimal
                return BigDecimal.valueOf((Double) result).toString();
            } else if (result instanceof BigDecimal) {
                // Trả về nếu là BigDecimal
                return ((BigDecimal) result).toString();
            } else {
                return "0";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }
    @Override
    public String layDoanhThuQuyHienTai() throws RemoteException {
        try {
            LocalDate now = LocalDate.now();
            int quarter = (now.getMonthValue() - 1) / 3 + 1;
            LocalDate startOfQuarter = now.withMonth((quarter - 1) * 3 + 1).with(TemporalAdjusters.firstDayOfMonth());
            LocalDate endOfQuarter = startOfQuarter.plusMonths(3);

            // Chuyển LocalDate thành LocalDateTime
            LocalDateTime startOfQuarterDate = startOfQuarter.atStartOfDay();
            LocalDateTime endOfQuarterDate = endOfQuarter.atStartOfDay();

            TypedQuery<Double> query = em.createQuery(
                    "SELECT SUM(lc.giaCho) FROM Ve v " +
                            "JOIN v.choNgoi cn " +
                            "JOIN cn.loaiCho lc " +
                            "WHERE v.ngayGioXuatVe >= :startOfQuarter " +
                            "AND v.ngayGioXuatVe < :endOfQuarter", Double.class);

            // Thiết lập tham số với kiểu LocalDateTime
            query.setParameter("startOfQuarter", startOfQuarterDate);
            query.setParameter("endOfQuarter", endOfQuarterDate);

            Double totalRevenue = query.getSingleResult();

            // Chuyển đổi Double thành BigDecimal nếu cần
            if (totalRevenue != null) {
                return BigDecimal.valueOf(totalRevenue).toString();
            } else {
                return "0";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }
    @Override
    public String layDoanhThuNamHienTai() throws RemoteException {
        try {
            // Lấy ngày hiện tại
            LocalDate now = LocalDate.now();

            // Tính toán ngày đầu năm và cuối năm
            LocalDate startOfYear = now.with(TemporalAdjusters.firstDayOfYear());
            LocalDate endOfYear = now.with(TemporalAdjusters.lastDayOfYear());

            // Chuyển đổi LocalDate thành LocalDateTime (với giờ bắt đầu là 00:00:00 cho ngày đầu năm và 23:59:59 cho ngày cuối năm)
            LocalDateTime startOfYearDateTime = startOfYear.atStartOfDay();
            LocalDateTime endOfYearDateTime = endOfYear.atTime(23, 59, 59);

            // Sử dụng TypedQuery để thực hiện câu truy vấn JPA
            TypedQuery<Double> query = em.createQuery(
                    "SELECT SUM(lc.giaCho) FROM Ve v " +
                            "JOIN v.choNgoi cn " +
                            "JOIN cn.loaiCho lc " +
                            "WHERE v.ngayGioXuatVe >= :startOfYear " +
                            "AND v.ngayGioXuatVe <= :endOfYear", Double.class);

            // Cài đặt tham số ngày bắt đầu và ngày kết thúc năm
            query.setParameter("startOfYear", startOfYearDateTime);
            query.setParameter("endOfYear", endOfYearDateTime);

            // Thực hiện truy vấn và lấy kết quả
            Double totalRevenue = query.getSingleResult();

            // Chuyển đổi từ Double sang BigDecimal
            BigDecimal totalRevenueBigDecimal = totalRevenue != null ? BigDecimal.valueOf(totalRevenue) : BigDecimal.ZERO;

            // Trả về kết quả
            return totalRevenueBigDecimal.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }
    @Override
    public String layDoanhThuNgayHomQua()throws RemoteException  {
        String nativeSql = "SELECT SUM(lc.giaCho) AS totalRevenue " +
                "FROM Ve v " +
                "JOIN ChoNgoi cn ON v.maCho = cn.maCho " +
                "JOIN LoaiCho lc ON cn.maLC = lc.maLC " +
                "WHERE CONVERT(date, v.ngayGioXuatVe) = CONVERT(date, DATEADD(day, -1, GETDATE()))";

        try {
            Object result = em.createNativeQuery(nativeSql).getSingleResult();

            if (result != null) {
                return result.toString(); // Vì SUM trả về BigDecimal hoặc Double, ép ra String
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }
    @Override
    public String layDoanhThuTuanVuaRoi() throws RemoteException {
        try {
            LocalDate today = LocalDate.now();
            // Tính ngày đầu tuần trước (Monday tuần trước)
            LocalDate startOfLastWeekDate = today.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
            // Ngày đầu tuần hiện tại (Monday tuần này) -> để làm mốc kết thúc tuần trước
            LocalDate endOfLastWeekDate = startOfLastWeekDate.plusDays(7);

            // Chuyển LocalDate thành LocalDateTime để so sánh chính xác
            LocalDateTime startOfLastWeek = startOfLastWeekDate.atStartOfDay();
            LocalDateTime endOfLastWeek = endOfLastWeekDate.atStartOfDay();

            TypedQuery<Object> query = em.createQuery(
                    "SELECT SUM(lc.giaCho) FROM Ve v " +
                            "JOIN v.choNgoi cn " +
                            "JOIN cn.loaiCho lc " +
                            "WHERE v.ngayGioXuatVe >= :startOfLastWeek " +
                            "AND v.ngayGioXuatVe < :endOfLastWeek", Object.class);

            query.setParameter("startOfLastWeek", startOfLastWeek);
            query.setParameter("endOfLastWeek", endOfLastWeek);

            Object result = query.getSingleResult();
            if (result instanceof Double) {
                // Chuyển đổi từ Double sang BigDecimal
                return BigDecimal.valueOf((Double) result).toString();
            } else if (result instanceof BigDecimal) {
                // Trả về nếu là BigDecimal
                return ((BigDecimal) result).toString();
            } else {
                return "0";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }
    @Override
    public String layDoanhThuThangVuaRoi()throws RemoteException {
        try {
            LocalDate now = LocalDate.now();
            LocalDateTime startOfLastMonth = now.with(TemporalAdjusters.firstDayOfMonth()).minusMonths(1).atStartOfDay();
            LocalDateTime endOfLastMonth = now.with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay(); // đầu tháng này

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

            // Chuyển đổi LocalDate thành LocalDateTime (thêm giờ mặc định 00:00:00)
            LocalDateTime startOfLastQuarterDateTime = startOfLastQuarter.atStartOfDay();
            LocalDateTime endOfLastQuarterDateTime = endOfLastQuarter.atStartOfDay();

            TypedQuery<BigDecimal> query = em.createQuery(
                    "SELECT SUM(lc.giaCho) FROM Ve v " +
                            "JOIN v.choNgoi cn " +
                            "JOIN cn.loaiCho lc " +
                            "WHERE v.ngayGioXuatVe >= :startOfLastQuarter " +
                            "AND v.ngayGioXuatVe < :endOfLastQuarter", BigDecimal.class);

            query.setParameter("startOfLastQuarter", startOfLastQuarterDateTime);
            query.setParameter("endOfLastQuarter", endOfLastQuarterDateTime);

            BigDecimal totalRevenue = query.getSingleResult();
            return totalRevenue != null ? totalRevenue.toString() : "0";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }
    @Override
    public String layDoanhThuNamVuaRoi() throws RemoteException {
        try {
            // Lấy ngày hiện tại
            LocalDate now = LocalDate.now();

            // Tính toán ngày đầu năm và cuối năm của năm trước
            LocalDate startOfLastYear = now.with(TemporalAdjusters.firstDayOfYear()).minusYears(1);
            LocalDate endOfLastYear = now.with(TemporalAdjusters.lastDayOfYear()).minusYears(1);

            // Chuyển đổi LocalDate thành LocalDateTime (với giờ bắt đầu là 00:00:00 cho ngày đầu năm và 23:59:59 cho ngày cuối năm)
            LocalDateTime startOfLastYearDateTime = startOfLastYear.atStartOfDay();
            LocalDateTime endOfLastYearDateTime = endOfLastYear.atTime(23, 59, 59);

            // Sử dụng TypedQuery để thực hiện câu truy vấn JPA
            TypedQuery<BigDecimal> query = em.createQuery(
                    "SELECT SUM(lc.giaCho) FROM Ve v " +
                            "JOIN v.choNgoi cn " +
                            "JOIN cn.loaiCho lc " +
                            "WHERE v.ngayGioXuatVe >= :startOfLastYear " +
                            "AND v.ngayGioXuatVe <= :endOfLastYear", BigDecimal.class);

            // Cài đặt tham số ngày bắt đầu và ngày kết thúc năm trước
            query.setParameter("startOfLastYear", startOfLastYearDateTime);
            query.setParameter("endOfLastYear", endOfLastYearDateTime);

            // Thực hiện truy vấn và lấy kết quả
            BigDecimal totalRevenue = query.getSingleResult();

            // Trả về kết quả nếu có, hoặc trả về "0" nếu không có doanh thu
            return totalRevenue != null ? totalRevenue.toString() : "0";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }
    @Override
    public double getBestSellingHourOfDay() throws RemoteException{
        String nativeSql = "SELECT TOP 1 " +
                "   DATEPART(HOUR, ngayGioXuatVe) AS gio, " +
                "   COUNT(maVe) AS soVeBan, " +
                "   SUM(tongTien) AS doanhThu " +
                "FROM View_VeTongTien " +
                "WHERE CONVERT(DATE, ngayGioXuatVe) = CONVERT(DATE, GETDATE()) " +
                "GROUP BY DATEPART(HOUR, ngayGioXuatVe) " +
                "ORDER BY soVeBan DESC";

        try {
            List<Object[]> resultList = em.createNativeQuery(nativeSql).getResultList();
            if (!resultList.isEmpty()) {
                Object[] row = resultList.get(0);
                Number gio = (Number) row[0];
                return gio.doubleValue();
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

            // Tính thời gian đầu tuần và cuối tuần (LocalDateTime)
            LocalDate today = LocalDate.now();
            LocalDateTime startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).atStartOfDay();
            LocalDateTime endOfWeek = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).plusDays(1).atStartOfDay();

            query.setParameter("startOfWeek", startOfWeek);
            query.setParameter("endOfWeek", endOfWeek);

            List<Object[]> results = query.setMaxResults(1).getResultList();

            if (!results.isEmpty()) {
                Object[] result = results.get(0);
                Integer hour = (Integer) result[0];  // EXTRACT(HOUR) trả về Integer
                return hour.doubleValue();
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
            LocalDateTime startOfMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay();
            LocalDateTime endOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()).atTime(23, 59, 59);

            query.setParameter("startOfMonth", startOfMonth);
            query.setParameter("endOfMonth", endOfMonth);

            List<Object[]> results = query.setMaxResults(1).getResultList();

            if (!results.isEmpty()) {
                Object[] result = results.get(0);
                Integer hour = (Integer) result[0];  // EXTRACT(HOUR) trả về Integer
                return hour.doubleValue();  // Chuyển Integer thành double
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    @Override
    public double getAverageBestSellingHourOfQuarter() throws RemoteException {
        try {
            String jpql = "SELECT EXTRACT(HOUR FROM v.ngayGioXuatVe) AS hour, COUNT(v.maVe) AS total_sales " +
                    "FROM Ve v " +
                    "JOIN v.hoaDon h " +
                    "WHERE h.ngayGioLapHD >= :startOfQuarter " +
                    "AND h.ngayGioLapHD < :endOfQuarter " +
                    "GROUP BY EXTRACT(HOUR FROM v.ngayGioXuatVe) " +
                    "ORDER BY total_sales DESC";

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);

            // Tính toán ngày bắt đầu và kết thúc của quý
            LocalDate now = LocalDate.now();
            int quarter = (now.getMonthValue() - 1) / 3 + 1;
            LocalDate startOfQuarter = now.withMonth((quarter - 1) * 3 + 1).with(TemporalAdjusters.firstDayOfMonth());
            LocalDate endOfQuarter = startOfQuarter.plusMonths(3);

            // Chuyển LocalDate thành LocalDateTime
            LocalDateTime startOfQuarterDate = startOfQuarter.atStartOfDay();
            LocalDateTime endOfQuarterDate = endOfQuarter.atStartOfDay();

            // Thiết lập tham số ngày bắt đầu và kết thúc quý
            query.setParameter("startOfQuarter", startOfQuarterDate);
            query.setParameter("endOfQuarter", endOfQuarterDate);

            // Thực hiện truy vấn và lấy kết quả
            List<Object[]> results = query.setMaxResults(1).getResultList();

            if (!results.isEmpty()) {
                // Lấy giá trị total_sales và chuyển sang kiểu double
                Object totalSalesObj = results.get(0)[1];
                if (totalSalesObj instanceof Long) {  // Sửa lại để kiểm tra Long thay vì Integer
                    return ((Long) totalSalesObj).doubleValue();
                }
                // Nếu không có kết quả hợp lệ, trả về -1
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    @Override
    public double getAverageBestSellingHourOfYear() throws RemoteException {
        try {
            String jpql = "SELECT EXTRACT(HOUR FROM v.ngayGioXuatVe) AS hour, COUNT(v.maVe) AS total_sales " +
                    "FROM Ve v " +
                    "JOIN v.hoaDon h " +
                    "WHERE h.ngayGioLapHD >= :startOfYear " +
                    "AND h.ngayGioLapHD < :endOfYear " +
                    "GROUP BY EXTRACT(HOUR FROM v.ngayGioXuatVe) " +
                    "ORDER BY total_sales DESC";

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);

            // Tính toán ngày đầu và cuối năm
            LocalDate now = LocalDate.now();
            LocalDate startOfYear = now.with(TemporalAdjusters.firstDayOfYear());
            LocalDate endOfYear = now.with(TemporalAdjusters.lastDayOfYear());

            // Chuyển LocalDate thành LocalDateTime
            LocalDateTime startOfYearDateTime = startOfYear.atStartOfDay(); // 00:00:00
            LocalDateTime endOfYearDateTime = endOfYear.atTime(23, 59, 59); // 23:59:59

            // Cài đặt tham số ngày bắt đầu và ngày kết thúc năm
            query.setParameter("startOfYear", startOfYearDateTime);
            query.setParameter("endOfYear", endOfYearDateTime);

            List<Object[]> results = query.setMaxResults(1).getResultList();

            if (!results.isEmpty()) {
                // Lấy giờ bán chạy nhất (cast về Integer trước khi chuyển sang Double)
                Integer bestSellingHour = (Integer) results.get(0)[0];
                return bestSellingHour != null ? bestSellingHour.doubleValue() : -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    @Override
    public String getSlowestSalesTimeLastWeek() throws RemoteException {
        String nativeSql =
                "WITH HourlySales AS ( " +
                        "    SELECT DATEPART(HOUR, v.ngayGioXuatVe) AS hour, " +
                        "           COUNT(*) AS total_sales " +
                        "    FROM HoaDon AS h " +
                        "    JOIN Ve AS v ON h.maHD = v.maHD " +
                        "    WHERE h.ngayGioLapHD >= DATEADD(DAY, -7, CAST(GETDATE() AS DATE)) " +
                        "      AND h.ngayGioLapHD < DATEADD(DAY, -6, CAST(GETDATE() AS DATE)) " +
                        "    GROUP BY DATEPART(HOUR, v.ngayGioXuatVe) " +
                        "), " +
                        "CompleteHours AS ( " +
                        "    SELECT h.hour, COALESCE(s.total_sales, 0) AS total_sales " +
                        "    FROM (SELECT 7 AS hour UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 " +
                        "          UNION ALL SELECT 11 UNION ALL SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14 " +
                        "          UNION ALL SELECT 15 UNION ALL SELECT 16 UNION ALL SELECT 17) AS h " +
                        "    LEFT JOIN HourlySales s ON h.hour = s.hour " +
                        "), " +
                        "SlidingWindow AS ( " +
                        "    SELECT h1.hour AS start_hour, " +
                        "           h1.hour + 2 AS end_hour, " +
                        "           SUM(h2.total_sales) AS total_sales_in_range " +
                        "    FROM CompleteHours h1 " +
                        "    JOIN CompleteHours h2 ON h2.hour BETWEEN h1.hour AND h1.hour + 2 " +
                        "    GROUP BY h1.hour " +
                        ") " +
                        "SELECT TOP 1 " +
                        "       CAST(start_hour AS VARCHAR) + ':00 - ' + CAST(end_hour AS VARCHAR) + ':00' AS time_range, " +
                        "       total_sales_in_range " +
                        "FROM SlidingWindow " +
                        "ORDER BY total_sales_in_range ASC;";

        try {
            Object result = em.createNativeQuery(nativeSql)
                    .getSingleResult();

            if (result != null) {
                Object[] row = (Object[]) result;
                return (String) row[0]; // Cột 0 là time_range
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "N/A"; // Nếu lỗi hoặc không có dữ liệu
    }
    @Override
    public String getSlowestSalesTimeLastMonth() throws RemoteException {
        try {
            // Tính toán ngày bắt đầu và kết thúc của tháng trước
            LocalDateTime startOfLastMonth = LocalDate.now().minusMonths(1).with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay();
            LocalDateTime endOfLastMonth = LocalDate.now().minusMonths(1).with(TemporalAdjusters.lastDayOfMonth()).atTime(23, 59, 59);

            // Truy vấn JPA để lấy dữ liệu bán hàng theo giờ trong tháng trước
            String jpql = "SELECT EXTRACT(HOUR FROM v.ngayGioXuatVe) AS hour, COUNT(v.maVe) AS total_sales " +
                    "FROM Ve v " +
                    "JOIN v.hoaDon h " +
                    "WHERE h.ngayGioLapHD >= :startOfLastMonth " +
                    "AND h.ngayGioLapHD < :endOfLastMonth " +
                    "GROUP BY EXTRACT(HOUR FROM v.ngayGioXuatVe)";

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.setParameter("startOfLastMonth", startOfLastMonth);
            query.setParameter("endOfLastMonth", endOfLastMonth);

            // Lấy kết quả bán hàng theo giờ
            List<Object[]> results = query.getResultList();

            // Tạo danh sách các giờ và doanh thu cho từng khung giờ (sử dụng sliding window)
            Map<Integer, Integer> hourSalesMap = new HashMap<>();
            for (Object[] result : results) {
                int hour = ((Number) result[0]).intValue();
                int totalSales = ((Number) result[1]).intValue();
                hourSalesMap.put(hour, totalSales);
            }

            // Khởi tạo danh sách khung giờ với 3 giờ liên tiếp
            Map<String, Integer> salesByRange = new HashMap<>();
            for (int hour = 7; hour <= 15; hour++) {
                int totalSales = hourSalesMap.getOrDefault(hour, 0)
                        + hourSalesMap.getOrDefault(hour + 1, 0)
                        + hourSalesMap.getOrDefault(hour + 2, 0);
                salesByRange.put(hour + ":00 - " + (hour + 2) + ":00", totalSales);
            }

            // Tìm khung giờ có doanh thu thấp nhất
            return salesByRange.entrySet().stream()
                    .min(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse("N/A");

        } catch (Exception e) {
            e.printStackTrace();
            return "N/A";
        }
    }
    @Override
    public String getSlowestSalesTimeLastQuarter() {
        String query =
                "WITH HourlySales AS (" +
                        "    SELECT " +
                        "        DATEPART(HOUR, v.ngayGioXuatVe) AS hour, " +
                        "        COUNT(*) AS total_sales " +
                        "    FROM HoaDon AS h " +
                        "    JOIN Ve AS v ON h.maHD = v.maHD " +
                        "    WHERE h.ngayGioLapHD >= DATEADD(QUARTER, -1, DATEFROMPARTS(YEAR(GETDATE()), MONTH(GETDATE()), 1)) " +
                        "          AND h.ngayGioLapHD < DATEFROMPARTS(YEAR(GETDATE()), MONTH(GETDATE()), 1) " +
                        "    GROUP BY DATEPART(HOUR, v.ngayGioXuatVe) " +
                        "), " +
                        "CompleteHours AS (" +
                        "    SELECT h.hour, COALESCE(s.total_sales, 0) AS total_sales " +
                        "    FROM (SELECT 7 AS hour UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 " +
                        "          UNION ALL SELECT 11 UNION ALL SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14 " +
                        "          UNION ALL SELECT 15 UNION ALL SELECT 16 UNION ALL SELECT 17) AS h " +
                        "    LEFT JOIN HourlySales s ON h.hour = s.hour " +
                        "), " +
                        "SlidingWindow AS (" +
                        "    SELECT " +
                        "        h1.hour AS start_hour, " +
                        "        h1.hour + 2 AS end_hour, " +
                        "        SUM(h2.total_sales) AS total_sales_in_range " +
                        "    FROM CompleteHours h1 " +
                        "    JOIN CompleteHours h2 ON h2.hour BETWEEN h1.hour AND h1.hour + 2 " +
                        "    GROUP BY h1.hour " +
                        ") " +
                        "SELECT TOP 1 " +
                        "    CAST(start_hour AS VARCHAR) + ':00 - ' + CAST(end_hour AS VARCHAR) + ':00' AS time_range, " +
                        "    total_sales_in_range " +
                        "FROM SlidingWindow " +
                        "ORDER BY total_sales_in_range ASC";

        try {
            Query nativeQuery = em.createNativeQuery(query);
            Object[] result = (Object[]) nativeQuery.getSingleResult();

            if (result != null && result.length > 0) {
                // Trả về khoảng thời gian bán chậm nhất (cột 0 trong mảng)
                return result[0] != null ? result[0].toString() : "N/A";
            } else {
                return "N/A";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "N/A";
    }
    @Override
    public String getSlowestSalesTimeLastYear() throws RemoteException {
        try {
            // Get the start and end dates for last year
            LocalDate now = LocalDate.now();
            LocalDate startOfLastYear = now.minusYears(1).with(TemporalAdjusters.firstDayOfYear());
            LocalDate endOfLastYear = now.minusYears(1).with(TemporalAdjusters.lastDayOfYear());

            // Convert LocalDate to LocalDateTime for precision
            LocalDateTime startOfLastYearDateTime = startOfLastYear.atStartOfDay();
            LocalDateTime endOfLastYearDateTime = endOfLastYear.atTime(23, 59, 59);

            String jpql = "SELECT EXTRACT(HOUR FROM v.ngayGioXuatVe) AS hour, COUNT(v.maVe) AS total_sales " +
                    "FROM Ve v " +
                    "JOIN v.hoaDon h " +
                    "WHERE h.ngayGioLapHD >= :startOfLastYear " +
                    "AND h.ngayGioLapHD <= :endOfLastYear " +
                    "GROUP BY EXTRACT(HOUR FROM v.ngayGioXuatVe) " +
                    "ORDER BY total_sales ASC";

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);

            // Set the start and end date parameters
            query.setParameter("startOfLastYear", startOfLastYearDateTime);
            query.setParameter("endOfLastYear", endOfLastYearDateTime);

            List<Object[]> results = query.getResultList();

            if (!results.isEmpty()) {
                // Get the slowest sales time (i.e., the hour with the least sales)
                Object[] row = results.get(0); // The first row will have the least sales
                Integer hour = (Integer) row[0]; // Hour
                return hour != null ? hour + ":00 - " + (hour + 2) + ":00" : "N/A";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "N/A";
    }
    @Override
    public ArrayList<Ve> getDanhSachVeDaTra() throws RemoteException {
        try {
            TypedQuery<Ve> query = em.createQuery(
                "SELECT v FROM Ve v WHERE v.trangThai = :trangThai", Ve.class);
            query.setParameter("trangThai", ETrangThaiVe.DA_TRA.name());
            return new ArrayList<>(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
