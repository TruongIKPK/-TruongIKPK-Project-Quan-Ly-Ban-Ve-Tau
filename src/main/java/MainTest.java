//import entity.*;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityTransaction;
//import jakarta.persistence.Persistence;
//import service.ChoNgoiService;
//import service.KhuyenMaiService;
//import service.NhanVienService;
//import service.ToaService;
//import java.time.LocalTime;
//import java.time.LocalDate;
//
//
//public class MainTest {
//    private static EntityManager em;
//    private static ToaService toaService;
//    private static ChoNgoiService persistChoNgoi;
//
//    public static void main(String[] args) {
//        System.out.println("Hello");
//        em = Persistence.createEntityManagerFactory("mssql").createEntityManager();
//        EntityTransaction transaction = em.getTransaction();
//        try {
//            transaction.begin(); // Bắt đầu giao dịch
//            insertData(em);
//            transaction.commit();// Commit giao dịch
//        } catch (RuntimeException e) {
//            // Nếu có lỗi thì rollback giao dịch
//            if (transaction.isActive()) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            // Đóng EntityManager
//            em.close();
//        }
//    }
//
//    private static void insertData(EntityManager em) {
//        // Thêm dữ liệu vào bảng CaLam
//        CaLam caLam1 = new CaLam();
//        caLam1.setMaCL("CA1");
//        caLam1.setTenCL("Ca làm sáng");
//        caLam1.setGioBD(LocalTime.parse("07:30:00"));
//        caLam1.setGioKetThuc(LocalTime.parse("11:30:00"));
//        em.persist(caLam1);
//
//        CaLam caLam2 = new CaLam();
//        caLam2.setMaCL("CA2");
//        caLam2.setTenCL("Ca làm chiều");
//        caLam2.setGioBD(LocalTime.parse("13:00:00"));
//        caLam2.setGioKetThuc(LocalTime.parse("17:00:00"));
//        em.persist(caLam2);
//
//        // Thêm dữ liệu vào bảng ChucVu
//        ChucVu chucVu1 = new ChucVu();
//        chucVu1.setMaCV("NV");
//        chucVu1.setTenCV("Nhân viên");
//        em.persist(chucVu1);
//
//        ChucVu chucVu2 = new ChucVu();
//        chucVu2.setMaCV("QL");
//        chucVu2.setTenCV("Quản lý");
//        em.persist(chucVu2);
//
//        // Thêm dữ liệu vào bảng Ga
//        Ga ga1 = new Ga();
//        ga1.setTenGa("Ga Tàu Hà Nội");
//        em.persist(ga1);
//
//        Ga ga2 = new Ga();
//        ga2.setTenGa("Ga Tàu Đồng Văn");
//        em.persist(ga2);
//
//        Ga ga3 = new Ga();
//        ga3.setTenGa("Ga Tàu Nam Định");
//        em.persist(ga3);
//
//        Ga ga4 = new Ga();
//        ga4.setTenGa("Ga Tàu Ninh Bình");
//        em.persist(ga4);
//
//        Ga ga5 = new Ga();
//        ga5.setTenGa("Ga Tàu Thanh Hóa");
//        em.persist(ga5);
//
//        Ga ga6 = new Ga();
//        ga6.setTenGa("Ga Tàu Vinh");
//        em.persist(ga6);
//
//        Ga ga7 = new Ga();
//        ga7.setTenGa("Ga Tàu Yên Trung");
//        em.persist(ga7);
//
//        Ga ga8 = new Ga();
//        ga8.setTenGa("Ga Tàu Đồng Hới");
//        em.persist(ga8);
//
//        Ga ga9 = new Ga();
//        ga9.setTenGa("Ga Tàu Đông Hà");
//        em.persist(ga9);
//
//        Ga ga10 = new Ga();
//        ga10.setTenGa("Ga Tàu Huế");
//        em.persist(ga10);
//
//        Ga ga11 = new Ga();
//        ga11.setTenGa("Ga Tàu Đà Nẵng");
//        em.persist(ga11);
//
//        Ga ga12 = new Ga();
//        ga12.setTenGa("Ga Tàu Tam Kỳ");
//        em.persist(ga12);
//
//        Ga ga13 = new Ga();
//        ga13.setTenGa("Ga Tàu Quảng Ngãi");
//        em.persist(ga13);
//
//        Ga ga14 = new Ga();
//        ga14.setTenGa("Ga Tàu Diêu Trì");
//        em.persist(ga14);
//
//        Ga ga15 = new Ga();
//        ga15.setTenGa("Ga Tàu Tuy Hòa");
//        em.persist(ga15);
//
//        Ga ga16 = new Ga();
//        ga16.setTenGa("Ga Tàu Nha Trang");
//        em.persist(ga16);
//
//        Ga ga17 = new Ga();
//        ga17.setTenGa("Ga Tàu Tháp Chàm");
//        em.persist(ga17);
//
//        Ga ga18 = new Ga();
//        ga18.setTenGa("Ga Tàu Bình Thuận (Mương Mán)");
//        em.persist(ga18);
//
//        Ga ga19 = new Ga();
//        ga19.setTenGa("Ga Tàu Biên Hòa");
//        em.persist(ga19);
//
//        Ga ga20 = new Ga();
//        ga20.setTenGa("Ga Tàu Dĩ An");
//        em.persist(ga20);
//
//        Ga ga21 = new Ga();
//        ga21.setTenGa("Ga Tàu Sài Gòn");
//        em.persist(ga21);
//
//        // Thêm dữ liệu vào bảng LoaiCho
//        LoaiCho loaiCho1 = new LoaiCho("NC", "Ghế cứng", "Ghế ngồi cứng không điều hòa", 100000);
//        em.persist(loaiCho1);
//
//        LoaiCho loaiCho2 = new LoaiCho("NCL", "Ghế cứng điều hòa", "Ghế ngồi cứng có điều hòa", 120000);
//        em.persist(loaiCho2);
//
//        LoaiCho loaiCho3 = new LoaiCho("NM", "Ghế mềm", "Ghế ngồi mềm không điều hòa", 150000);
//        em.persist(loaiCho3);
//
//        LoaiCho loaiCho4 = new LoaiCho("NML", "Ghế mềm điều hòa", "Ghế ngồi mềm có điều hòa", 170000);
//        em.persist(loaiCho4);
//
//        LoaiCho loaiCho5 = new LoaiCho("ANL", "Khoang 4 giường", "Giường nằm khoang 4 người", 250000);
//        em.persist(loaiCho5);
//
//        LoaiCho loaiCho6 = new LoaiCho("BNL", "Khoang 6 giường", "Giường nằm khoang 6 người", 220000);
//        em.persist(loaiCho6);
//
//        LoaiCho loaiCho7 = new LoaiCho("VIP", "Khoang VIP", "Khoang 2 giường nằm VIP", 500000);
//        em.persist(loaiCho7);
//
//        // Thêm dữ liệu vào bảng LoaiToa
//        LoaiToa loaiToa1 = new LoaiToa("NC", "Ghe ngoi cung thuong");
//        em.persist(loaiToa1);
//
//        LoaiToa loaiToa2 = new LoaiToa("NCL", "Ghe ngoi cung co dieu hoa");
//        em.persist(loaiToa2);
//
//        LoaiToa loaiToa3 = new LoaiToa("NM", "Ghe ngoi mem thuong");
//        em.persist(loaiToa3);
//
//        LoaiToa loaiToa4 = new LoaiToa("NML", "Ghe ngoi mem dieu hoa");
//        em.persist(loaiToa4);
//
//        LoaiToa loaiToa5 = new LoaiToa("ANL", "Giuong nam khoang 4");
//        em.persist(loaiToa5);
//
//        LoaiToa loaiToa6 = new LoaiToa("BNL", "Giuong nam khoang 6");
//        em.persist(loaiToa6);
//
//        LoaiToa loaiToa7 = new LoaiToa("VIP", "Giuong nam khoang 2 VIP");
//        em.persist(loaiToa7);
//
//        LoaiVe loaiVe1 = new LoaiVe("LV1", "Vé 1 chiều");
//        em.persist(loaiVe1);
//
//        LoaiVe loaiVe2 = new LoaiVe("LV2", "Vé khứ hồi");
//        em.persist(loaiVe2);
//
//        // Thêm dữ liệu vào bảng Tau
//        Tau tau1 = new Tau("SE", "Tàu chạy tuyến Bắc - Nam", "Hoạt động");
//        em.persist(tau1);
//
//        Tau tau2 = new Tau("SP", "Tàu cao tốc", "Đang chạy");
//        em.persist(tau2);
//
//        Tau tau3 = new Tau("SPT", "Tàu Phan Thiết - Sài Gòn", "Đã dừng");
//        em.persist(tau3);
//
//        Tau tau4 = new Tau("SNT", "Tàu chạy tuyến Sài Gòn - Nha Trang", "Hoạt động");
//        em.persist(tau4);
//
//        toaService = new ToaService(em);
//
//        // Thêm dữ liệu vào bảng Toa
//        Toa toa1 = new Toa(60, tau1, loaiToa1);
//        toaService.persistToa(toa1);
//
//        Toa toa2 = new Toa(60, tau1, loaiToa1);
//        toaService.persistToa(toa2);
//
//        Toa toa3 = new Toa(60, tau1, loaiToa2);
//        toaService.persistToa(toa3);
//
//        Toa toa4 = new Toa(60, tau1, loaiToa2);
//        toaService.persistToa(toa4);
//
//        Toa toa5 = new Toa(60, tau1, loaiToa3);
//        toaService.persistToa(toa5);
//
//        Toa toa6 = new Toa(60, tau1, loaiToa3);
//        toaService.persistToa(toa6);
//
//        Toa toa7 = new Toa(60, tau1, loaiToa4);
//        toaService.persistToa(toa7);
//
//        Toa toa8 = new Toa(30, tau1, loaiToa5);
//        toaService.persistToa(toa8);
//
//        Toa toa9 = new Toa(40, tau1, loaiToa6);
//        toaService.persistToa(toa9);
//
//        Toa toa10 = new Toa(10, tau1, loaiToa7);
//        toaService.persistToa(toa10);
//
//        Toa toa11 = new Toa(60, tau2, loaiToa1);
//        toaService.persistToa(toa11);
//
//        Toa toa12 = new Toa(60, tau2, loaiToa1);
//        toaService.persistToa(toa12);
//
//        Toa toa13 = new Toa(60, tau2, loaiToa2);
//        toaService.persistToa(toa13);
//
//        Toa toa14 = new Toa(60, tau2, loaiToa2);
//        toaService.persistToa(toa14);
//
//        Toa toa15 = new Toa(60, tau2, loaiToa3);
//        toaService.persistToa(toa15);
//
//        Toa toa16 = new Toa(60, tau2, loaiToa3);
//        toaService.persistToa(toa16);
//
//        Toa toa17 = new Toa(60, tau2, loaiToa4);
//        toaService.persistToa(toa17);
//
//        Toa toa18 = new Toa(30, tau2, loaiToa5);
//        toaService.persistToa(toa18);
//
//        Toa toa19 = new Toa(40, tau2, loaiToa6);
//        toaService.persistToa(toa19);
//
//        Toa toa20 = new Toa(10, tau2, loaiToa7);
//        toaService.persistToa(toa20);
//
//        Toa toa21 = new Toa(60, tau3, loaiToa1);
//        toaService.persistToa(toa21);
//
//        Toa toa22 = new Toa(60, tau3, loaiToa1);
//        toaService.persistToa(toa22);
//
//        Toa toa23 = new Toa(60, tau3, loaiToa2);
//        toaService.persistToa(toa23);
//
//        Toa toa24 = new Toa(60, tau3, loaiToa2);
//        toaService.persistToa(toa24);
//
//        Toa toa25 = new Toa(60, tau3, loaiToa3);
//        toaService.persistToa(toa25);
//
//        Toa toa26 = new Toa(60, tau3, loaiToa3);
//        toaService.persistToa(toa26);
//
//        Toa toa27 = new Toa(60, tau3, loaiToa4);
//        toaService.persistToa(toa27);
//
//        Toa toa28 = new Toa(30, tau3, loaiToa5);
//        toaService.persistToa(toa28);
//
//        Toa toa29 = new Toa(40, tau3, loaiToa6);
//        toaService.persistToa(toa29);
//
//        Toa toa30 = new Toa(10, tau3, loaiToa7);
//        toaService.persistToa(toa30);
//
//        Toa toa31 = new Toa(60, tau4, loaiToa1);
//        toaService.persistToa(toa31);
//
//        Toa toa32 = new Toa(60, tau4, loaiToa1);
//        toaService.persistToa(toa32);
//
//        Toa toa33 = new Toa(60, tau4, loaiToa2);
//        toaService.persistToa(toa33);
//
//        Toa toa34 = new Toa(60, tau4, loaiToa2);
//        toaService.persistToa(toa34);
//
//        Toa toa35 = new Toa(60, tau4, loaiToa3);
//        toaService.persistToa(toa35);
//
//        Toa toa36 = new Toa(60, tau4, loaiToa3);
//        toaService.persistToa(toa36);
//
//        Toa toa37 = new Toa(60, tau4, loaiToa4);
//        toaService.persistToa(toa37);
//
//        Toa toa38 = new Toa(30, tau4, loaiToa5);
//        toaService.persistToa(toa38);
//
//        Toa toa39 = new Toa(40, tau4, loaiToa6);
//        toaService.persistToa(toa39);
//
//        Toa toa40 = new Toa(10, tau4, loaiToa7);
//        toaService.persistToa(toa40);
//
//        KhuyenMaiService khuyenMaiService = new KhuyenMaiService(em);
//
//        KhuyenMai km1 = new KhuyenMai(LocalDate.of(2025, 1, 27), LocalDate.of(2025, 12, 30), "Người hoạt động cách mạng", 0.10);
//        khuyenMaiService.persistKhuyenMai(km1);
//
//        KhuyenMai km2 = new KhuyenMai(LocalDate.of(2025, 1, 27), LocalDate.of(2025, 12, 30), "Thương binh", 0.15);
//        khuyenMaiService.persistKhuyenMai(km2);
//
//        KhuyenMai km3 = new KhuyenMai(LocalDate.of(2025, 1, 27), LocalDate.of(2025, 12, 30), "Người khuyết tật", 0.20);
//        khuyenMaiService.persistKhuyenMai(km3);
//
//        KhuyenMai km4 = new KhuyenMai(LocalDate.of(2025, 1, 27), LocalDate.of(2025, 12, 30), "Trẻ em", 0.05);
//        khuyenMaiService.persistKhuyenMai(km4);
//
//        KhuyenMai km5 = new KhuyenMai(LocalDate.of(2025, 1, 27), LocalDate.of(2025, 12, 30), "Người cao tuổi", 0.30);
//        khuyenMaiService.persistKhuyenMai(km5);
//
//        KhuyenMai km6 = new KhuyenMai(LocalDate.of(2025, 1, 27), LocalDate.of(2025, 12, 30), "Học sinh, sinh viên", 0.30);
//        khuyenMaiService.persistKhuyenMai(km6);
//
//        KhuyenMai km7 = new KhuyenMai(LocalDate.of(2025, 1, 27), LocalDate.of(2025, 12, 30), "Tất cả", 0.30);
//        khuyenMaiService.persistKhuyenMai(km7);
//
//        KhuyenMai km8 = new KhuyenMai(LocalDate.of(2025, 1, 27), LocalDate.of(2025, 12, 30), "Người lớn", 0.00);
//        khuyenMaiService.persistKhuyenMai(km8);
//
//        persistChoNgoi = new ChoNgoiService(em);
//
//        // Thêm dữ liệu ChoNgoi
//        for (int i = 1; i <= 60; i++) {
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho1, toa1));
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho1, toa2));
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho2, toa3));
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho2, toa4));
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho3, toa5));
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho3, toa6));
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho4, toa7));
//            if (i <= 40) {
//                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho5, toa9));
//                if (i <= 30) {
//                    persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho6, toa8));
//                    if (i <= 10) {
//                        persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho7, toa10));
//                    }
//                }
//            }
//        }
//
//        for (int i = 1; i <= 60; i++) {
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho1, toa11));
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho1, toa12));
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho2, toa13));
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho2, toa14));
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho3, toa15));
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho3, toa16));
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho4, toa17));
//            if (i <= 40) {
//                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho6, toa19));
//                if (i <= 30) {
//                    persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho5, toa18));
//                    if (i <= 10) {
//                        persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho7, toa20));
//                    }
//                }
//            }
//        }
//        for (int i = 1; i <= 60; i++) {
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho1, toa21));
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho1, toa22));
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho2, toa23));
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho2, toa24));
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho3, toa25));
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho3, toa26));
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho4, toa27));
//            if (i <= 40) {
//                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho6, toa29));
//                if (i <= 30) {
//                    persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho5, toa28));
//                    if (i <= 10) {
//                        persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho7, toa30));
//                    }
//                }
//            }
//        }
//        for (int i = 1; i <= 60; i++) {
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho1, toa31));
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho1, toa32));
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho2, toa33));
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho2, toa34));
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho3, toa35));
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho3, toa36));
//            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho4, toa37));
//            if (i <= 40) {
//                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho6, toa39));
//                if (i <= 30) {
//                    persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho5, toa38));
//                    if (i <= 10) {
//                        persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho7, toa40));
//                    }
//                }
//            }
//        }
//        NhanVienService nhanVienService = new NhanVienService(em);
//
//        // Thêm dữ liệu vào bảng NhanVien
//        NhanVien nhanVien1 = new NhanVien();
//        nhanVien1.setTenNV("Lê Nguyễn Phi Trường");
//        nhanVien1.setGioiTinh("Nam");
//        nhanVien1.setNgaySinh(LocalDate.parse("1990-01-01"));
//        nhanVien1.setNgayVaoLam(LocalDate.parse("2023-01-01"));
//        nhanVien1.setCCCD("123456789012");
//        nhanVien1.setSdt("0912345678");
//        nhanVien1.setEmail("nguyenvana@example.com");
//        nhanVien1.setDiaChi("Hà Nội");
//        nhanVien1.setTrangThai("Làm việc");
//        nhanVien1.setCaLam(caLam1);
//        nhanVien1.setChucVu(chucVu2);
//        nhanVien1.setDuongDanAnh("images/employees/nv-1.jpg");
//        nhanVienService.persistNhanVien(nhanVien1);
//
//        NhanVien nhanVien2 = new NhanVien();
//        nhanVien2.setTenNV("Hồ Quốc Huy");
//        nhanVien2.setGioiTinh("Nữ");
//        nhanVien2.setNgaySinh(LocalDate.parse("1995-05-10"));
//        nhanVien2.setNgayVaoLam(LocalDate.parse("2023-01-10"));
//        nhanVien2.setCCCD("234567890123");
//        nhanVien2.setSdt("0987654321");
//        nhanVien2.setEmail("tranthib@example.com");
//        nhanVien2.setDiaChi("Hồ Chí Minh");
//        nhanVien2.setTrangThai("Làm việc");
//        nhanVien2.setCaLam(caLam2);
//        nhanVien2.setChucVu(chucVu1);
//        nhanVien2.setDuongDanAnh("images/employees/nv-2.jpg");
//        nhanVienService.persistNhanVien(nhanVien2);
//    }
//}
