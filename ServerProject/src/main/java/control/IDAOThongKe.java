package control;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IDAOThongKe extends Remote{
    //Thanh Hien
    int getTongSoNhanVien()throws RemoteException;

    List<Object[]> getNhanVienTheoChucVu()throws RemoteException;

    List<Object[]> getDoanhThuTheoThang(int year)throws RemoteException;

    List<Object[]> getTopDoanhThuNhanVien()throws RemoteException;

    List<Object[]> getDoanhThuTheoNgay(int year, int month)throws RemoteException;

    List<Object[]> getTopDoanhThuNhanVienTrongNgay()throws RemoteException;

    List<Object[]> getTopDoanhThuNhanVienTheoTuan()throws RemoteException;

    List<Object[]> getTopDoanhThuNhanVienTheoThang()throws RemoteException;

    List<Object[]> getTopDoanhThuNhanVienTheoQuy()throws RemoteException;

    List<Object[]> getTopDoanhThuNhanVienTheoNam()throws RemoteException;

    List<Object[]> getDailyRevenueChange1()throws RemoteException;

    List<Object[]> getDailyRevenueChange()throws RemoteException;

    List<Object[]> getWeeklyRevenueChange()throws RemoteException;

    List<Object[]> getMonthlyRevenueChange1()throws RemoteException;

    List<Object[]> getQuarterlyRevenueChange()throws RemoteException;

    List<Object[]> getYearlyRevenueChange()throws RemoteException;

    //    Trường
    Object[] getDoanhThuVaSoVeTrongKhoangThoiGian(Date startDate, Date endDate)throws RemoteException;

    Map<Integer, Double> getRevenueByHour()throws RemoteException;

    Map<LocalDate, Double> getCurrentWeekRevenue()throws RemoteException;

    Map<LocalDate, Double> getDoanhThuTheoNgay_TuNgay_DenNgay(Date startOfWeek, Date endOfWeek, String maLoaiVe)throws RemoteException;

    Map<Integer, Double> getTuan_TuNgay_DenNgay(Date startDate, Date endDate, String maLoaiVe)throws RemoteException;

    Map<Integer, Double> getThangVe_NgayBatDau_NgayKetThuc(Date startDate, Date endDate, String maLoaiVe)throws RemoteException;

    Map<Integer, Double> getQuarterlyRevenue(Date startDate, Date endDate, String maLoaiVe)throws RemoteException;

    Map<Integer, Double> getYearlyRevenue(Date startDate, Date endDate, String maLoaiVe)throws RemoteException;

    Map<Integer, Double> getWeeklyRevenue()throws RemoteException;

    Map<String, Double> getYearlyQuarterRevenue(int year)throws RemoteException;

    Map<String, Double> getMonthlyRevenue(int year)throws RemoteException;

    Object[] getThongKeLoaiVe(Date startDate, Date endDate, String maLoaiVe)throws RemoteException;

    List<Object[]> getChiTietVeTheoNgay(String maLoaiVe, Date startDate, Date endDate)throws RemoteException;

    Object[] getThongKeTheoNhanVien(Date startDate, Date endDate, String maNhanVien)throws RemoteException;

    //Khoe
    List<Object[]> getThongKeForAllNhanVien(Date startDate, Date endDate)throws RemoteException;

    Map<LocalDate, Map<String, Double>> getDoanhThuTheoNgay_VaNhanVien(
            Date startDate, Date endDate, String maNhanVien)throws RemoteException;

    Map<Integer, Map<String, Double>> getDoanhThuTheoTuan_VaNhanVien(
            Date startDate, Date endDate, String maNhanVien)throws RemoteException;

    Map<Integer, Map<String, Double>> getDoanhThuTheoThang_VaNhanVien(
            Date startDate, Date endDate, String maNhanVien)throws RemoteException;

    Map<Integer, Map<String, Double>> getDoanhThuTheoQuy_VaNhanVien(
            Date startDate, Date endDate, String maNhanVien)throws RemoteException;

    Map<Integer, Map<String, Double>> getDoanhThuTheoNam_VaNhanVien(
            Date startDate, Date endDate, String maNhanVien)throws RemoteException;

    List<Object[]> getDoanhThuChiTietTheoNgay()throws RemoteException;

    List<Object[]> getDoanhThuChiTietTheoKhoangNgay(Date startDate, Date endDate)throws RemoteException;

    List<Object[]> getDoanhThuNhanVienTheoNgay(Date startDate, Date endDate, String maNhanVien)throws RemoteException;

    List<Object> getLoyaltyStats()throws RemoteException;

    String getOverallAverageTransactionInterval()throws RemoteException;

    int getTotalCustomersByDateRange(Date startDate, Date endDate)throws RemoteException;

    List<Object[]> getCustomerCountByDateRange(Date startDate, Date endDate)throws RemoteException;

    List<Object[]> getCustomerCountByWeek(Date startDate, Date endDate)throws RemoteException;

    List<Object[]> getCustomerCountByMonth(Date startDate, Date endDate)throws RemoteException;

    List<Object[]> getCustomerCountByQuarter(Date startDate, Date endDate)throws RemoteException;

    // Việt
    List<Object[]> getCustomerCountByYear(Date startDate, Date endDate)throws RemoteException;

    List<Object[]> getTyLeKhuyenMaiTheoDoiTuong(Date startDate, Date endDate)throws RemoteException;

    List<Object[]> getDoanhThuKhuyenMaiTheoDoiTuong(Date startDate, Date endDate)throws RemoteException;

    List<Object[]> getTopCustomers(Date startDate, Date endDate)throws RemoteException;

    Object[] getTiLeDoiTraVe(Date startDate, Date endDate)throws RemoteException;

    List<Object[]> getThongKeVeDoiTheoNgay(Date startDate, Date endDate)throws RemoteException;

    List<Object[]> getThongKeVeDoiTheoTuan(Date startDate, Date endDate)throws RemoteException;

    List<Object[]> getThongKeVeDoiTheoThang(Date startDate, Date endDate)throws RemoteException;

    List<Object[]> getThongKeVeDoiTheoQuy(Date startDate, Date endDate)throws RemoteException;

    List<Object[]> getThongKeVeDoiTheoNam(Date startDate, Date endDate)throws RemoteException;

    List<Object[]> getThongKeVeTraTheoNgay(Date startDate, Date endDate)throws RemoteException;

    List<Object[]> getThongKeVeTraTheoTuan(Date startDate, Date endDate)throws RemoteException;

    List<Object[]> getThongKeVeTraTheoThang(Date startDate, Date endDate)throws RemoteException;

    List<Object[]> getThongKeVeTraTheoQuy(Date startDate, Date endDate)throws RemoteException;

    List<Object[]> getThongKeVeTraTheoNam(Date startDate, Date endDate)throws RemoteException;

    List<Object> dsVeTheoNgay_MaVe_ListObject(Date ngayXuatVe, String maLoaiVe)throws RemoteException;
}
