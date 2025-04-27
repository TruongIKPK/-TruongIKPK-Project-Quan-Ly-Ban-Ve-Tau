package utils;

import entity.HoaDon;

import java.time.LocalDate;
import java.util.List;

public class GenerateBillTempId {
    // Mã của hóa đơn dạng HDTAM + năm + tháng + ngày + %04d
    public static String generateBillTempId() {
        // Lấy danh sách hóa đơn tạm hiện có
        List<HoaDon> danhSachHoaDonTam = HoaDonTamHandler.getDanhSachHoaDonTam();

        // Lấy ngày hiện tại
        LocalDate today = LocalDate.now();
        String prefix = String.format("HDTAM%04d%02d%02d", today.getYear(), today.getMonthValue(), today.getDayOfMonth());

        // Tìm số thứ tự cao nhất trong các mã hóa đơn tạm đã tồn tại
        int maxNumber = 0;
        for (HoaDon hoaDon : danhSachHoaDonTam) {
            String maHoaDon = hoaDon.getMaHD();
            if (maHoaDon.startsWith(prefix)) {
                // Lấy phần số thứ tự cuối cùng trong mã
                String numberPart = maHoaDon.substring(prefix.length());
                try {
                    int number = Integer.parseInt(numberPart);
                    maxNumber = Math.max(maxNumber, number);
                } catch (NumberFormatException e) {
                    System.err.println("Mã hóa đơn không hợp lệ: " + maHoaDon);
                }
            }
        }

        // Số thứ tự mới (tăng 1)
        int nextNumber = maxNumber + 1;

        // Tạo mã hóa đơn tạm mới
        return String.format("%s%04d", prefix, nextNumber);
    }
}
