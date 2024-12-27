package utils;

import entity.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @Dự án: tau-viet-express
 * @Class: HoaDonTamHandler
 * @Tạo vào ngày: 12/12/2024
 * @Tác giả: Huy
 */
public class HoaDonTamHandler {
    // Đường dẫn file
    private static final String FILE_PATH = "/hoa_don_tam_data.dat";

    // Lưu hóa đơn tạm vào file lưu nhiều hóa đơn
    public static void luuHoaDonTam(HoaDon hoaDonTam) {
        try {
            // Đọc danh sách hiện có từ file
            ArrayList<HoaDon> danhSachHoaDonTam = getDanhSachHoaDonTam();

            // Thêm hóa đơn mới
            danhSachHoaDonTam.add(hoaDonTam);

            // Ghi toàn bộ danh sách hóa đơn vào file
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
                oos.writeObject(danhSachHoaDonTam);
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu hóa đơn tạm: " + e.getMessage());
        }
    }

    // Lấy danh sách tất cả các vé tạm
    public static ArrayList<Ve> getDanhSachCacVeTam() {
        ArrayList<Ve> danhSachVeTam = new ArrayList<>();
        ArrayList<HoaDon> danhSachHoaDonTam = getDanhSachHoaDonTam();
        for (HoaDon hoaDon : danhSachHoaDonTam) {
            danhSachVeTam.addAll(hoaDon.getDanhSachVe());
        }
        return danhSachVeTam;
    }

    // Đọc danh sách hóa đơn tạm từ file
    @SuppressWarnings("unchecked")
    public static ArrayList<HoaDon> getDanhSachHoaDonTam() {
        ArrayList<HoaDon> danhSachHoaDonTam = new ArrayList<>();
        File file = new File(FILE_PATH);

        // Kiểm tra file tồn tại trước khi đọc
        if (!file.exists()) {
            return danhSachHoaDonTam; // Trả về danh sách rỗng nếu file chưa tồn tại
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            danhSachHoaDonTam = (ArrayList<HoaDon>) ois.readObject();

            // Xóa những hóa đơn tạm đã hết hạn ( quá 10 phút )
            danhSachHoaDonTam.removeIf(hoaDon -> {
                LocalDateTime thoiGianTao = hoaDon.getNgayGioLapHD();
                LocalDateTime thoiGianHienTai = LocalDateTime.now();
                return thoiGianHienTai.minusMinutes(10).isAfter(thoiGianTao);
            });

            // Ghi lại danh sách hóa đơn tạm sau khi xóa
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
                oos.writeObject(danhSachHoaDonTam);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Lỗi khi đọc hóa đơn tạm: " + e.getMessage());
        }
        return danhSachHoaDonTam;
    }

    // Xóa một hóa đơn tạm
    public static void xoaHoaDonTam(HoaDon hoaDonTam) {
        // Đọc danh sách hóa đơn tạm từ file
        ArrayList<HoaDon> danhSachHoaDonTam = getDanhSachHoaDonTam();

        // Xóa hóa đơn tạm
        danhSachHoaDonTam.remove(hoaDonTam);

        // Ghi lại danh sách hóa đơn tạm sau khi xóa
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(danhSachHoaDonTam);
        } catch (IOException e) {
            System.err.println("Lỗi khi xóa hóa đơn tạm: " + e.getMessage());
        }
    }

    // Xóa các hóa đơn tạm đã hết hạn
    public static void xoaHoaDonTamHetHan() {
        // Đọc danh sách hóa đơn tạm từ file
        ArrayList<HoaDon> danhSachHoaDonTam = getDanhSachHoaDonTam();

        // Xóa hóa đơn tạm hết hạn
        danhSachHoaDonTam.removeIf(hoaDon -> {
            LocalDateTime thoiGianTao = hoaDon.getNgayGioLapHD();
            LocalDateTime thoiGianHienTai = LocalDateTime.now();
            return thoiGianHienTai.minusMinutes(10).isAfter(thoiGianTao);
        });

        // Ghi lại danh sách hóa đơn tạm sau khi xóa
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(danhSachHoaDonTam);
        } catch (IOException e) {
            System.err.println("Lỗi khi xóa hóa đơn tạm hết hạn: " + e.getMessage());
        }
    }
}
