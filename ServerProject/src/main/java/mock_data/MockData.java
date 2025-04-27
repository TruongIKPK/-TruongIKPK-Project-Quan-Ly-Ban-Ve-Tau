package mock_data;

import entity.*;
import enums.ETrangThaiChoNgoi;
import enums.ETrangThaiNhanVien;
import enums.ETrangThaiTau;
import enums.ETrangThaiVe;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class MockData {
    // Mock dữ liệu ca làm
    public static ArrayList<CaLam> getDsCaLam() {
        ArrayList<CaLam> ds = new ArrayList<>();

        // CaLam(String maCa, String tenCa, LocalTime gioBatDau, LocalTime gioKetThuc)
        ds.add(new CaLam("CA1", "Ca làm sáng", LocalTime.of(7, 0), LocalTime.of(12,0 )));
        ds.add(new CaLam("CA2", "Ca làm chiều", LocalTime.of(12, 0), LocalTime.of(17,0 )));
        ds.add(new CaLam("CA3", "Ca làm tối", LocalTime.of(17, 0), LocalTime.of(22,0 )));
        return ds;
    }

    // Mock dữ liệu chức vụ
    public static ArrayList<ChucVu> getDsChucVu() {
        ArrayList<ChucVu> ds = new ArrayList<>();

        // ChucVu(String maCV, String tenCV)
        ds.add(new ChucVu("QL", "Quản lý"));
        ds.add(new ChucVu("NV", "Nhân viên"));
        return ds;
    }

    // Mock dữ liệu loại chỗ
    public static ArrayList<LoaiCho> getDsLoaiCho() {
        ArrayList<LoaiCho> ds = new ArrayList<>();

        // String maLC, String tenLC, String moTa, double giaCho
        ds.add(new LoaiCho("LC1", "Loại chỗ 1", "Loại chỗ 1", 100000));
        ds.add(new LoaiCho("LC2", "Loại chỗ 2", "Loại chỗ 2", 200000));
        ds.add(new LoaiCho("LC3", "Loại chỗ 3", "Loại chỗ 3", 300000));

        return ds;
    }

    // Mock dữ liệu loại toa
    public static ArrayList<LoaiToa> getDsLoaiToa() {
        ArrayList<LoaiToa> ds = new ArrayList<>();

        // LoaiToa(String maLT, String tenLT)
        ds.add(new LoaiToa("LT1", "Loại toa 1"));
        ds.add(new LoaiToa("LT2", "Loại toa 2"));
        ds.add(new LoaiToa("LT3", "Loại toa 3"));
        return ds;
    }

    // Mock dữ liệu tàu
    public static ArrayList<Tau> getDsTau() {
        ArrayList<Tau> dsTau = new ArrayList<>();

        // Khởi tạo tàu mà không cần khởi tạo danh sách toa ngay lập tức
        dsTau.add(new Tau("T001", "Tàu 1", ETrangThaiTau.HOAT_DONG.getTrangThai(), new ArrayList<>()));
        dsTau.add(new Tau("T002", "Tàu 2", ETrangThaiTau.HOAT_DONG.getTrangThai(), new ArrayList<>()));
        dsTau.add(new Tau("T003", "Tàu 3", ETrangThaiTau.HOAT_DONG.getTrangThai(), new ArrayList<>()));
        dsTau.add(new Tau("T004", "Tàu 4", ETrangThaiTau.HOAT_DONG.getTrangThai(), new ArrayList<>()));
        dsTau.add(new Tau("T005", "Tàu 5", ETrangThaiTau.HOAT_DONG.getTrangThai(), new ArrayList<>()));

        // Khởi tạo danh sách toa riêng biệt sau khi khởi tạo danh sách tàu
        ArrayList<Toa> dsToaTau1 = new ArrayList<>();
        dsToaTau1.add(new Toa("TOA001", dsTau.get(0), getDsLoaiToa().get(0), 100, new ArrayList<>()));
        dsToaTau1.add(new Toa("TOA002", dsTau.get(0), getDsLoaiToa().get(0), 100, new ArrayList<>()));
        dsToaTau1.add(new Toa("TOA003", dsTau.get(0), getDsLoaiToa().get(0), 100, new ArrayList<>()));
        dsToaTau1.add(new Toa("TOA004", dsTau.get(0), getDsLoaiToa().get(0), 100, new ArrayList<>()));
        dsToaTau1.add(new Toa("TOA005", dsTau.get(0), getDsLoaiToa().get(0), 100, new ArrayList<>()));

        ArrayList<Toa> dsToaTau2 = new ArrayList<>();
        dsToaTau2.add(new Toa("TOA006", dsTau.get(0), getDsLoaiToa().get(0), 100, new ArrayList<>()));
        dsToaTau2.add(new Toa("TOA007", dsTau.get(0), getDsLoaiToa().get(0), 100, new ArrayList<>()));
        dsToaTau2.add(new Toa("TOA008", dsTau.get(0), getDsLoaiToa().get(0), 100, new ArrayList<>()));
        dsToaTau2.add(new Toa("TOA009", dsTau.get(0), getDsLoaiToa().get(0), 100, new ArrayList<>()));
        dsToaTau2.add(new Toa("TOA010", dsTau.get(0), getDsLoaiToa().get(0), 100, new ArrayList<>()));

        // Gán danh sách toa cho các tàu tương ứng
        dsTau.get(0).setDanhSachToa(dsToaTau1);
        dsTau.get(1).setDanhSachToa(dsToaTau1);
        dsTau.get(2).setDanhSachToa(dsToaTau2);
        dsTau.get(3).setDanhSachToa(dsToaTau2);
        dsTau.get(4).setDanhSachToa(dsToaTau2);

        ArrayList<ChoNgoi> dsChoNgoi = new ArrayList<>();

        for (int i=1; i<=60; i++) {
            // Thêm 60 chỗ vào các toa
            dsChoNgoi.add(new ChoNgoi("A0" + i, getDsLoaiCho().get(0), dsToaTau1.get(0)));
        }

        // Làm sao để set Danh sach cho ngoi
        dsTau.get(0).getDanhSachToa().get(0).setDanhSachChoNgoi(dsChoNgoi);

        return dsTau;
    }

    // Mock dữ liệu khách hàng
    public static ArrayList<KhachHang> getDsKhachHang() {
        ArrayList<KhachHang> ds = new ArrayList<>();

        // KhachHang(String maKH, String tenKH, String CCCD, String sdt, String email, LocalDate ngaySinh, String doiTuong)
        ds.add(new KhachHang("KH12345678912345", "Nguyễn Văn A", "123456789111", "0123456789", "ngonhuthuy1234@gmail.com", LocalDate.of(2004, 8, 21), "Học sinh"));

        return ds;
    }

    // Mock dữ liệu khuyến mãi
//    public static ArrayList<KhuyenMai> getDsKhuyenMai() {
//        ArrayList<KhuyenMai> ds = new ArrayList<>();
//
//        // KhuyenMai(String maKM, LocalDate ngayBD, LocalDate ngayKT, String dieuKien, String loaiKM, double phanTramKM)
//        ds.add(new KhuyenMai("KM12345678912", LocalDate.now(), LocalDate.now(), 0.1));
//        ds.add(new KhuyenMai("KM12345678913", LocalDate.now(), LocalDate.now(), 0.2));
//        ds.add(new KhuyenMai("KM12345678914", LocalDate.now(), LocalDate.now(), 0.3));
//        ds.add(new KhuyenMai("KM12345678915", LocalDate.now(), LocalDate.now(), 0.4));
//        ds.add(new KhuyenMai("KM12345678916", LocalDate.now(), LocalDate.now(), 0.5));
//
//        return ds;
//    }

    // Mock dữ liệu loại vé
    public static ArrayList<LoaiVe> getDsLoaiVe() {
        ArrayList<LoaiVe> ds = new ArrayList<>();

        // LoaiVe(String maLV, String tenLV)
        ds.add(new LoaiVe("LV1", "Loại vé 1"));
        ds.add(new LoaiVe("LV2", "Loại vé 2"));
        ds.add(new LoaiVe("LV3", "Loại vé 3"));
        ds.add(new LoaiVe("LV4", "Loại vé 4"));
        ds.add(new LoaiVe("LV5", "Loại vé 5"));

        return ds;
    }

    // Mock dữ liệu vé
//    public static ArrayList<Ve> getDsVe() {
//        ArrayList<Ve> ds = new ArrayList<>();
//
//        // Ve(String maVe, HoaDon hoaDon, LoaiVe loaiVe, LocalDateTime ngayGioXuatVe, ChoNgoi choNgoi, ChuyenTau chuyenTau, KhachHang khachHang, double thue, KhuyenMai khuyenMai, ETrangThaiVe trangThai)
//        ds.add(new Ve("VE12345678912345", getDsHoaDon().get(0), getDsLoaiVe().get(0), LocalDateTime.of(2022, 10, 9, 9, 0), getDsChoNgoi().get(0), getDsChuyen().get(0), getDsKhachHang().get(0), 0, getDsKhuyenMai().get(0), ETrangThaiVe.DA_BAN.getTrangThai()));
//        ds.add(new Ve("VE12345678912346", getDsHoaDon().get(0), getDsLoaiVe().get(0), LocalDateTime.of(2022, 10, 9, 9, 0), getDsChoNgoi().get(0), getDsChuyen().get(0), getDsKhachHang().get(0), 0, getDsKhuyenMai().get(0), ETrangThaiVe.DA_BAN.getTrangThai()));
//        ds.add(new Ve("VE12345678912347", getDsHoaDon().get(0), getDsLoaiVe().get(0), LocalDateTime.of(2022, 10, 9, 9, 0), getDsChoNgoi().get(0), getDsChuyen().get(0), getDsKhachHang().get(0), 0, getDsKhuyenMai().get(0), ETrangThaiVe.DA_BAN.getTrangThai()));
//        ds.add(new Ve("VE12345678912348", getDsHoaDon().get(0), getDsLoaiVe().get(0), LocalDateTime.of(2022, 10, 9, 9, 0), getDsChoNgoi().get(0), getDsChuyen().get(0), getDsKhachHang().get(0), 0, getDsKhuyenMai().get(0), ETrangThaiVe.DA_BAN.getTrangThai()));
//        ds.add(new Ve("VE12345678912349", getDsHoaDon().get(0), getDsLoaiVe().get(0), LocalDateTime.of(2022, 10, 9, 9, 0), getDsChoNgoi().get(0), getDsChuyen().get(0), getDsKhachHang().get(0), 0, getDsKhuyenMai().get(0), ETrangThaiVe.DA_BAN.getTrangThai()));
//
//        return ds;
//    }

    // Mock dữ liệu hóa đơn
    public static ArrayList<HoaDon> getDsHoaDon() {
        ArrayList<HoaDon> ds = new ArrayList<>();

        // HoaDon(String maHD, LocalDateTime ngayGioLapHD, NhanVien nhanVien, KhachHang khachHang, int soLuong, ArrayList<Ve> danhSachVe)
        ds.add(new HoaDon("HD12345678912341", LocalDate.now().atStartOfDay(), getDsNhanVien().get(0), getDsKhachHang().get(0), 1, new ArrayList<>()));
        ds.add(new HoaDon("HD12345678912342", LocalDate.now().atStartOfDay(), getDsNhanVien().get(0), getDsKhachHang().get(0), 1, new ArrayList<>()));
        ds.add(new HoaDon("HD12345678912343", LocalDate.now().atStartOfDay(), getDsNhanVien().get(0), getDsKhachHang().get(0), 1, new ArrayList<>()));
        ds.add(new HoaDon("HD12345678912344", LocalDate.now().atStartOfDay(), getDsNhanVien().get(0), getDsKhachHang().get(0), 1, new ArrayList<>()));
        ds.add(new HoaDon("HD12345678912345", LocalDate.now().atStartOfDay(), getDsNhanVien().get(0), getDsKhachHang().get(0), 1, new ArrayList<>()));

        return ds;
    }

    // Mock dữ liệu chuyến tàu
    public static ArrayList<ChuyenTau> getDsChuyen() {
        ArrayList<ChuyenTau> ds = new ArrayList<>();

        // ChuyenTau(String maChuyen, String macTau, Tau tau, String gaDi, String gaDen, LocalDateTime ngayGioKhoiHanh, LocalDateTime ngayGioDen)
//        ds.add(new ChuyenTau("CT00000001", "T001", getDsTau().get(0), "Hà Nội", "Sài Gòn", LocalDate.of(2024, 10, 12).atStartOfDay(), LocalDate.of(2024, 10, 13).atStartOfDay(), ""));
//        ds.add(new ChuyenTau("CT00000002", "T002", getDsTau().get(1), "Hà Nội", "Sài Gòn", LocalDate.of(2024, 10, 12).atStartOfDay(), LocalDate.of(2024, 10, 13).atStartOfDay(), ""));
//        ds.add(new ChuyenTau("CT00000003", "T003", getDsTau().get(2), "Hà Nội", "Sài Gòn", LocalDate.of(2024, 10, 12).atStartOfDay(), LocalDate.of(2024, 10, 13).atStartOfDay(), ""));
//        ds.add(new ChuyenTau("CT00000004", "T004", getDsTau().get(3), "Sài Gòn", "Hà Nội", LocalDate.of(2024, 10, 12).atStartOfDay(), LocalDate.of(2024, 10, 13).atStartOfDay(), ""));
//        ds.add(new ChuyenTau("CT00000005", "T005", getDsTau().get(4), "Sài Gòn", "Hà Nội", LocalDate.of(2024, 10, 12).atStartOfDay(), LocalDate.of(2024, 10, 13).atStartOfDay(), ""));

        return ds;
    }

    // Mock dữ liệu nhân viên
    public static ArrayList<NhanVien> getDsNhanVien() {
        ArrayList<NhanVien> ds = new ArrayList<>();

        // NhanVien(String maNV, String tenNV, String gioiTinh, LocalDate ngaySinh, String sdt, String email, String diaChi, String CCCD, LocalDate ngayVaoLam, ChucVu chucVu, TaiKhoan taiKhoan, ETrangThaiNhanVien trangThai, CaLam caLam)
        ds.add(new NhanVien("NV00000000001", "Nguyễn Văn A", "Nam", LocalDate.of(2004, 8, 21), "0123456789", "ngonhuthuy1234@gmail.com", "Hà Nội", "123456789111", LocalDate.of(2024,10,1), new ChucVu("NV", "Nhân viên"), new TaiKhoan("NV000000001"), ETrangThaiNhanVien.LAM_VIEC.getTrangThai(), new CaLam("CA1", "Ca làm sáng", LocalTime.of(7, 0), LocalTime.of(12,0 ))));

        return ds;
    }
    //Mock dữ liệu chức vụ


    // Mock dữ liệu chỗ ngồi
    public static ArrayList<ChoNgoi> getDsChoNgoi() {
        ArrayList<ChoNgoi> ds = new ArrayList<>();

        // ChoNgoi(String maCho, LoaiCho loaiCho, Toa toa, ETrangThaiChoNgoi trangThai)
        for (int i=1; i<=60; i++) {
            ds.add(new ChoNgoi("A0" + i, getDsLoaiCho().get(0), getDsTau().get(0).getDanhSachToa().get(0)));
        }

        return ds;
    }

    // Lấy dữ liệu các ga
    public static ArrayList<String> getDsGa() {
        /*
        * Hà nội
1. Ga Tàu Hà Nội
2. Ga Tàu Giáp Bát
Hà nam
3. Ga Tàu Đồng Văn
4. Ga Tàu Phủ Lý
5. Ga Tàu Bình Lục
Nam Định
6. Ga Tàu Cầu Họ
7. Ga Tàu Đặng Xá
8. Ga Tàu Nam Định
9. Ga Tàu Núi Gôi
10. Ga Tàu Cát Đằng
Ninh Bình
11. Ga Tàu Ninh Bình
Thanh Hóa
12. Ga Tàu Bỉm Sơn
13. Ga Tàu Nghĩa Trang
14. Ga Tàu Thanh Hóa
15. Ga Tàu Yên Thái
16. Ga Tàu Minh Khôi
Nghệ An
17. Ga Tàu Hoàng Mai
18. Ga Tàu Cầu Giát
19. Ga Tàu Vinh
Hà Tĩnh
20. Ga Tàu Yên Trung
21. Ga Tàu Đức Lạc
22. Ga Tàu Hương Phố
23. Ga Tàu Phúc Trạch
Quảng Bình
24. Ga Tàu La Khê
25. Ga Tàu Tân Ấp
26. Ga Tàu Kim Lũ
27. Ga Tàu Đồng Lê
28. Ga Tàu Ngọc Lâm
29. Ga Tàu Thọ Lộc
30. Ga Phúc Tự
31. Ga Tàu Đồng Hới
32. Ga Tàu Lệ Kỳ
33. Ga Tàu Long Đại
34. Ga Tàu Mỹ Đức
35. Ga Tàu Phú Hòa
36. Ga Tàu Mỹ Trạch
Quảng Trị
37. Ga  TàuSa Lung
38. Ga Tàu Tiên An
39. Ga Tàu Hà Thanh
40. Ga Tàu Đông Hà
41. Ga Tàu Quảng Trị
42. Ga Tàu Diên Sanh
43. Ga Tàu Mỹ Chánh
        * */

        ArrayList<String> ds = new ArrayList<>();
        ds.add("Sài Gòn");
        ds.add("Hà Nội");

        // Thêm các ga tàu vào ds
        return ds;
    };

    // Get đối tượng khách hàng
    public static ArrayList<String> getDsDoiTuong() {
        ArrayList<String> ds = new ArrayList<>();
        ds.add("Tất cả");
        ds.add("Trẻ em dưới 6 tuổi"); // Miễn
        ds.add("Trẻ em từ 6 - 12 tuổi"); // Giảm 25% giá vé
        ds.add("Người cao tuổi (60 tuổi trở lên)"); // Giảm 15% giá vé
        ds.add("Học sinh, sinh viên"); // Giảm 10% giá vé
        ds.add("Người khuyết tật"); // Giảm 10% giá vé

        return ds;
    }
}



