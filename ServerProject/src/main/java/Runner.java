import connectDB.connectDB_1;
import entity.*;
import jakarta.persistence.EntityManager;
import server.DaoRegistry;
import server.SeatLockManager;
import server.SessionManager;
import service.ChoNgoiService;
import service.KhuyenMaiService;
import service.NhanVienService;
import service.ToaService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static server.SessionManager.cleanup;

public class Runner {
    private static EntityManager em;
    private static ToaService toaService;
    private static ChoNgoiService persistChoNgoi;

    public static void main(String[] args) throws RemoteException, NamingException {
        System.out.println("Hello");

        String host = "localhost";
        int port = 9090;

        connectDB_1.connect();
        em = connectDB_1.getEntityManager();
        em.getTransaction().begin();
        insertData(em);
        createViewIfNotExists(em);
        em.getTransaction().commit();

        Context context = new InitialContext();
        LocateRegistry.createRegistry(port);

        Map<String, Object> daoMap = DaoRegistry.getDaoMap();

        for (Map.Entry<String, Object> entry : daoMap.entrySet()) {
            String name = entry.getKey();
            Object dao = entry.getValue();
            context.bind("rmi://" + host + ":" + port + "/" + name, dao);
        }

        System.out.println(">> RMI Server is ready on port " + port);

        new Thread(() -> {
            while (true) {
                cleanup();
                try {
                    Thread.sleep(60_000); // 1 phút
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        //version1
        // Thêm thread dọn dẹp khóa hết hạn
        SeatLockManager seatLockManager = new SeatLockManager();
        new Thread(() -> {
            while (true) {
                seatLockManager.cleanupExpiredLocks();
                try {
                    Thread.sleep(60_000); // Chạy mỗi phút
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // Đóng EntityManager và EntityManagerFactory khi không còn sử dụng
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (em != null && em.isOpen()) {
                em.close();
            }
            connectDB_1.close();
        }));
    }

    private static void createViewIfNotExists(EntityManager em) {
        try {
            // Kiểm tra xem view đã tồn tại chưa
            boolean viewExists = false;

            try {
                String dbName = em.getTransaction().getClass().getPackageName().toLowerCase();

                if (dbName.contains("mssql")) {
                    Object result = em.createNativeQuery(
                                    "SELECT COUNT(*) FROM sys.views WHERE name = 'View_VeTongTien'")
                            .getSingleResult();
                    viewExists = ((Number) result).intValue() > 0;
                } else {
                    Object result = em.createNativeQuery(
                                    "SELECT COUNT(*) FROM information_schema.views WHERE table_name = 'View_VeTongTien'")
                            .getSingleResult();
                    viewExists = ((Number) result).intValue() > 0;
                }
            } catch (Exception e) {
                System.err.println("Không kiểm tra được view: " + e.getMessage());
            }

            if (!viewExists) {
                // Tạo view nếu chưa tồn tại
                String createViewSQL = "CREATE VIEW View_VeTongTien AS\n" +
                        "SELECT \n" +
                        "    v.maVe,\n" +
                        "    v.maHD,\n" +
                        "    v.maLV,\n" +
                        "    v.ngayGioXuatVe,\n" +
                        "    v.maCho,\n" +
                        "    v.maChuyen,\n" +
                        "    v.maKH,\n" +
                        "    v.trangThai,\n" +
                        "    c.maToa,\n" +
                        "    lc.tenLC,\n" +
                        "    lc.giaCho,\n" +
                        "    v.thue,\n" +
                        "    km.phanTramKM,\n" +
                        "    CASE \n" +
                        "        WHEN km.phanTramKM IS NULL THEN (lc.giaCho + (lc.giaCho * v.thue / 100))\n" +
                        "        ELSE (lc.giaCho + (lc.giaCho * v.thue / 100)) - (lc.giaCho + (lc.giaCho * v.thue / 100)) * km.phanTramKM\n" +
                        "    END AS tongTien\n" +
                        "FROM Ve v\n" +
                        "INNER JOIN ChoNgoi c ON v.maCho = c.maCho\n" +
                        "INNER JOIN LoaiCho lc ON c.maLC = lc.maLC\n" +
                        "LEFT JOIN KhuyenMai km ON v.maKM = km.maKM;";

                em.createNativeQuery(createViewSQL).executeUpdate();
                System.out.println("Đã tạo thành công View_VeTongTien");
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi tạo view: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private static void insertData(EntityManager em) {
        CaLam caLam1 = null;
        CaLam caLam2 = null;
        // Thêm dữ liệu vào bảng CaLam
        if (em.find(CaLam.class, "CA1") == null) {
            caLam1 = new CaLam();
            caLam1.setMaCL("CA1");
            caLam1.setTenCL("Ca làm sáng");
            caLam1.setGioBD(LocalTime.parse("07:30:00"));
            caLam1.setGioKetThuc(LocalTime.parse("11:30:00"));
            em.persist(caLam1);
        }

        if (em.find(CaLam.class, "CA2") == null) {
            caLam2 = new CaLam();
            caLam2.setMaCL("CA2");
            caLam2.setTenCL("Ca làm chiều");
            caLam2.setGioBD(LocalTime.parse("13:00:00"));
            caLam2.setGioKetThuc(LocalTime.parse("17:00:00"));
            em.persist(caLam2);
        }
        ChucVu chucVu1 = null;
        ChucVu chucVu2 = null;
        // Thêm dữ liệu vào bảng ChucVu
        if (em.find(ChucVu.class, "NV") == null) {
            chucVu1 = new ChucVu();
            chucVu1.setMaCV("NV");
            chucVu1.setTenCV("Nhân viên");
            em.persist(chucVu1);
        }

        if (em.find(ChucVu.class, "QL") == null) {
            chucVu2 = new ChucVu();
            chucVu2.setMaCV("QL");
            chucVu2.setTenCV("Quản lý");
            em.persist(chucVu2);
        }

        // Thêm dữ liệu vào bảng Ga
        if (em.find(Ga.class, 1) == null) {
            Ga ga1 = new Ga();
            ga1.setTenGa("Ga Tau Ha Noi");
            em.persist(ga1);
        }
        if (em.find(Ga.class, 2) == null) {
            Ga ga2 = new Ga();
            ga2.setTenGa("Ga Tau Dong Van");
            em.persist(ga2);
        }
        if (em.find(Ga.class, 3) == null) {
            Ga ga3 = new Ga();
            ga3.setTenGa("Ga Tau Nam Dinh");
            em.persist(ga3);
        }
        if (em.find(Ga.class, 4) == null) {
            Ga ga4 = new Ga();
            ga4.setTenGa("Ga Tau Ninh Binh");
            em.persist(ga4);
        }
        if (em.find(Ga.class, 5) == null) {
            Ga ga5 = new Ga();
            ga5.setTenGa("Ga Tau Thanh Hoa");
            em.persist(ga5);
        }
        if (em.find(Ga.class, 6) == null) {
            Ga ga6 = new Ga();
            ga6.setTenGa("Ga Tau Vinh");
            em.persist(ga6);
        }
        if (em.find(Ga.class, 7) == null) {
            Ga ga7 = new Ga();
            ga7.setTenGa("Ga Tau Yen Trung");
            em.persist(ga7);
        }
        if (em.find(Ga.class, 8) == null) {
            Ga ga8 = new Ga();
            ga8.setTenGa("Ga Tau Dong Hoi");
            em.persist(ga8);
        }
        if (em.find(Ga.class, 9) == null) {
            Ga ga9 = new Ga();
            ga9.setTenGa("Ga Tau Dong Ha");
            em.persist(ga9);
        }
        if (em.find(Ga.class, 10) == null) {
            Ga ga10 = new Ga();
            ga10.setTenGa("Ga Tau Hue");
            em.persist(ga10);
        }
        if (em.find(Ga.class, 11) == null) {
            Ga ga11 = new Ga();
            ga11.setTenGa("Ga Tau Da Nang");
            em.persist(ga11);
        }
        if (em.find(Ga.class, 12) == null) {
            Ga ga12 = new Ga();
            ga12.setTenGa("Ga Tau Tam Ky");
            em.persist(ga12);
        }
        if (em.find(Ga.class, 13) == null) {
            Ga ga13 = new Ga();
            ga13.setTenGa("Ga Tau Quang Ngai");
            em.persist(ga13);
        }
        if (em.find(Ga.class, 14) == null) {
            Ga ga14 = new Ga();
            ga14.setTenGa("Ga Tau Dieu Tri");
            em.persist(ga14);
        }
        if (em.find(Ga.class, 15) == null) {
            Ga ga15 = new Ga();
            ga15.setTenGa("Ga Tau Tuy Hoa");
            em.persist(ga15);
        }
        if (em.find(Ga.class, 16) == null) {
            Ga ga16 = new Ga();
            ga16.setTenGa("Ga Tau Nha Trang");
            em.persist(ga16);
        }
        if (em.find(Ga.class, 17) == null) {
            Ga ga17 = new Ga();
            ga17.setTenGa("Ga Tau Thap Cham");
            em.persist(ga17);
        }
        if (em.find(Ga.class, 18) == null) {
            Ga ga18 = new Ga();
            ga18.setTenGa("Ga Tau Binh Thuan (Muong Man)");
            em.persist(ga18);
        }
        if (em.find(Ga.class, "19") == null) {
            Ga ga19 = new Ga();
            ga19.setTenGa("Ga Tau Bien Hoa");
            em.persist(ga19);
        }
        if (em.find(Ga.class, "20") == null) {
            Ga ga20 = new Ga();
            ga20.setTenGa("Ga Tau Di An");
            em.persist(ga20);
        }
        if (em.find(Ga.class, "21") == null) {
            Ga ga21 = new Ga();
            ga21.setTenGa("Ga Tau Sai Gon");
            em.persist(ga21);
        }
        // Thêm dữ liệu vào bảng LoaiCho
        LoaiCho loaiCho1 = null;
        LoaiCho loaiCho2 = null;
        LoaiCho loaiCho3 = null;
        LoaiCho loaiCho4 = null;
        LoaiCho loaiCho5 = null;
        LoaiCho loaiCho6 = null;
        LoaiCho loaiCho7 = null;
        if (em.find(LoaiCho.class, "NC") == null) {
            loaiCho1 = new LoaiCho("NC", "Ghế cứng", "Ghế ngồi cứng không điều hòa", 100000);
            em.persist(loaiCho1);
        }
        if (em.find(LoaiCho.class, "NCL") == null) {
            loaiCho2 = new LoaiCho("NCL", "Ghế cứng điều hòa", "Ghế ngồi cứng có điều hòa", 120000);
            em.persist(loaiCho2);
        }
        if (em.find(LoaiCho.class, "NM") == null) {
            loaiCho3 = new LoaiCho("NM", "Ghế mềm", "Ghế ngồi mềm không điều hòa", 150000);
            em.persist(loaiCho3);
        }
        if (em.find(LoaiCho.class, "NML") == null) {
            loaiCho4 = new LoaiCho("NML", "Ghế mềm điều hòa", "Ghế ngồi mềm có điều hòa", 170000);
            em.persist(loaiCho4);
        }
        if (em.find(LoaiCho.class, "ANL") == null) {
            loaiCho5 = new LoaiCho("ANL", "Khoang 4 giường", "Giường nằm khoang 4 người", 250000);
            em.persist(loaiCho5);
        }
        if (em.find(LoaiCho.class, "BNL") == null) {
            loaiCho6 = new LoaiCho("BNL", "Khoang 6 giường", "Giường nằm khoang 6 người", 220000);
            em.persist(loaiCho6);
        }
        if (em.find(LoaiCho.class, "VIP") == null) {
            loaiCho7 = new LoaiCho("VIP", "Khoang VIP", "Khoang 2 giường nằm VIP", 500000);
            em.persist(loaiCho7);
        }
        LoaiToa loaiToa1 = null;
        LoaiToa loaiToa2 = null;
        LoaiToa loaiToa3 = null;
        LoaiToa loaiToa4 = null;
        LoaiToa loaiToa5 = null;
        LoaiToa loaiToa6 = null;
        LoaiToa loaiToa7 = null;
        // Thêm dữ liệu vào bảng LoaiToa
        if (em.find(LoaiToa.class, "NC") == null) {
            loaiToa1 = new LoaiToa("NC", "Ghe ngoi cung thuong");
            em.persist(loaiToa1);
        }
        if (em.find(LoaiToa.class, "NCL") == null) {
            loaiToa2 = new LoaiToa("NCL", "Ghe ngoi cung co dieu hoa");
            em.persist(loaiToa2);
        }
        if (em.find(LoaiToa.class, "NM") == null) {
            loaiToa3 = new LoaiToa("NM", "Ghe ngoi mem thuong");
            em.persist(loaiToa3);
        }
        if (em.find(LoaiToa.class, "NML") == null) {
            loaiToa4 = new LoaiToa("NML", "Ghe ngoi mem dieu hoa");
            em.persist(loaiToa4);
        }
        if (em.find(LoaiToa.class, "ANL") == null) {
            loaiToa5 = new LoaiToa("ANL", "Giuong nam khoang 4");
            em.persist(loaiToa5);
        }
        if (em.find(LoaiToa.class, "BNL") == null) {
            loaiToa6 = new LoaiToa("BNL", "Giuong nam khoang 6");
            em.persist(loaiToa6);
        }
        if (em.find(LoaiToa.class, "VIP") == null) {
            loaiToa7 = new LoaiToa("VIP", "Giuong nam khoang 2 VIP");
            em.persist(loaiToa7);
        }
        LoaiVe loaiVe1;
        LoaiVe loaiVe2;
        // Thêm dữ liệu vào bảng LoaiVe
        if (em.find(LoaiVe.class, "LV1") == null) {
            loaiVe1 = new LoaiVe("LV1", "Vé 1 chiều");
            em.persist(loaiVe1);
        }
        if (em.find(LoaiVe.class, "LV2") == null) {
            loaiVe2 = new LoaiVe("LV2", "Vé khứ hồi");
            em.persist(loaiVe2);
        }
        Tau tau1 = null;
        Tau tau2 = null;
        Tau tau3 = null;
        Tau tau4 = null;
        // Thêm dữ liệu vào bảng Tau
        if (em.find(Tau.class, "SE") == null) {
            tau1 = new Tau("SE", "Tàu chạy tuyến Bắc - Nam", "Hoạt động");
            em.persist(tau1);
        }
        if (em.find(Tau.class, "SP") == null) {
            tau2 = new Tau("SP", "Tàu cao tốc", "Đang chạy");
            em.persist(tau2);
        }
        if (em.find(Tau.class, "SPT") == null) {
            tau3 = new Tau("SPT", "Tàu Phan Thiết - Sài Gòn", "Đã dừng");
            em.persist(tau3);
        }
        if (em.find(Tau.class, "SNT") == null) {
            tau4 = new Tau("SNT", "Tàu chạy tuyến Sài Gòn - Nha Trang", "Hoạt động");
            em.persist(tau4);
        }
        toaService = new ToaService(em);
        Toa toa1 = null;
        Toa toa2 = null;
        Toa toa3 = null;
        Toa toa4 = null;
        Toa toa5 = null;
        Toa toa6 = null;
        Toa toa7 = null;
        Toa toa8 = null;
        Toa toa9 = null;
        Toa toa10 = null;
        Toa toa11 = null;
        Toa toa12 = null;
        Toa toa13 = null;
        Toa toa14 = null;
        Toa toa15 = null;
        Toa toa16 = null;
        Toa toa17 = null;
        Toa toa18 = null;
        Toa toa19 = null;
        Toa toa20 = null;
        Toa toa21 = null;
        Toa toa22 = null;
        Toa toa23 = null;
        Toa toa24 = null;
        Toa toa25 = null;
        Toa toa26 = null;
        Toa toa27 = null;
        Toa toa28 = null;
        Toa toa29 = null;
        Toa toa30 = null;
        Toa toa31 = null;
        Toa toa32 = null;
        Toa toa33 = null;
        Toa toa34 = null;
        Toa toa35 = null;
        Toa toa36 = null;
        Toa toa37 = null;
        Toa toa38 = null;
        Toa toa39 = null;
        Toa toa40 = null;

        if (tau1 != null && loaiToa1 != null && loaiToa2 != null && loaiToa3 != null && loaiToa4 != null && loaiToa5 != null && loaiToa6 != null && loaiToa7 != null) {
            // Thêm dữ liệu vào bảng Toa
            toa1 = new Toa(60, tau1, loaiToa1);
            toaService.persistToa(toa1);

            toa2 = new Toa(60, tau1, loaiToa1);
            toaService.persistToa(toa2);

            toa3 = new Toa(60, tau1, loaiToa2);
            toaService.persistToa(toa3);

            toa4 = new Toa(60, tau1, loaiToa2);
            toaService.persistToa(toa4);

            toa5 = new Toa(60, tau1, loaiToa3);
            toaService.persistToa(toa5);

            toa6 = new Toa(60, tau1, loaiToa3);
            toaService.persistToa(toa6);

            toa7 = new Toa(60, tau1, loaiToa4);
            toaService.persistToa(toa7);

            toa8 = new Toa(30, tau1, loaiToa5);
            toaService.persistToa(toa8);

            toa9 = new Toa(40, tau1, loaiToa6);
            toaService.persistToa(toa9);

            toa10 = new Toa(10, tau1, loaiToa7);
            toaService.persistToa(toa10);

            toa11 = new Toa(60, tau2, loaiToa1);
            toaService.persistToa(toa11);

            toa12 = new Toa(60, tau2, loaiToa1);
            toaService.persistToa(toa12);

            toa13 = new Toa(60, tau2, loaiToa2);
            toaService.persistToa(toa13);

            toa14 = new Toa(60, tau2, loaiToa2);
            toaService.persistToa(toa14);

            toa15 = new Toa(60, tau2, loaiToa3);
            toaService.persistToa(toa15);

            toa16 = new Toa(60, tau2, loaiToa3);
            toaService.persistToa(toa16);

            toa17 = new Toa(60, tau2, loaiToa4);
            toaService.persistToa(toa17);

            toa18 = new Toa(30, tau2, loaiToa5);
            toaService.persistToa(toa18);

            toa19 = new Toa(40, tau2, loaiToa6);
            toaService.persistToa(toa19);

            toa20 = new Toa(10, tau2, loaiToa7);
            toaService.persistToa(toa20);

            toa21 = new Toa(60, tau3, loaiToa1);
            toaService.persistToa(toa21);

            toa22 = new Toa(60, tau3, loaiToa1);
            toaService.persistToa(toa22);

            toa23 = new Toa(60, tau3, loaiToa2);
            toaService.persistToa(toa23);

            toa24 = new Toa(60, tau3, loaiToa2);
            toaService.persistToa(toa24);

            toa25 = new Toa(60, tau3, loaiToa3);
            toaService.persistToa(toa25);

            toa26 = new Toa(60, tau3, loaiToa3);
            toaService.persistToa(toa26);

            toa27 = new Toa(60, tau3, loaiToa4);
            toaService.persistToa(toa27);

            toa28 = new Toa(30, tau3, loaiToa5);
            toaService.persistToa(toa28);

            toa29 = new Toa(40, tau3, loaiToa6);
            toaService.persistToa(toa29);

            toa30 = new Toa(10, tau3, loaiToa7);
            toaService.persistToa(toa30);

            toa31 = new Toa(60, tau4, loaiToa1);
            toaService.persistToa(toa31);

            toa32 = new Toa(60, tau4, loaiToa1);
            toaService.persistToa(toa32);

            toa33 = new Toa(60, tau4, loaiToa2);
            toaService.persistToa(toa33);

            toa34 = new Toa(60, tau4, loaiToa2);
            toaService.persistToa(toa34);

            toa35 = new Toa(60, tau4, loaiToa3);
            toaService.persistToa(toa35);

            toa36 = new Toa(60, tau4, loaiToa3);
            toaService.persistToa(toa36);

            toa37 = new Toa(60, tau4, loaiToa4);
            toaService.persistToa(toa37);

            toa38 = new Toa(30, tau4, loaiToa5);
            toaService.persistToa(toa38);

            toa39 = new Toa(40, tau4, loaiToa6);
            toaService.persistToa(toa39);

            toa40 = new Toa(10, tau4, loaiToa7);
            toaService.persistToa(toa40);
        }
        KhuyenMaiService khuyenMaiService = new KhuyenMaiService(em);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        KhuyenMai km1 = new KhuyenMai(LocalDate.of(2025, 5, 20), LocalDate.of(2025, 12, 30), "Người hoạt động cách mạng", 0.10);
        System.out.println("KM" +km1.getNgayBD().format(formatter) + "001");
        if(em.find(KhuyenMai.class, "KM" +km1.getNgayBD().format(formatter) + "001")==null) {
            khuyenMaiService.persistKhuyenMai(km1);
            System.out.println(km1.getMaKM());
        }
        KhuyenMai km2 = new KhuyenMai(LocalDate.of(2025, 5, 20), LocalDate.of(2025, 12, 30), "Thương binh", 0.15);
        System.out.println("KM" +km2.getNgayBD().format(formatter) + "002");
        if(em.find(KhuyenMai.class, "KM" +km2.getNgayBD().format(formatter) + "002")==null) {
            khuyenMaiService.persistKhuyenMai(km2);
            System.out.println(km2.getMaKM());
        }
        KhuyenMai km3 = new KhuyenMai(LocalDate.of(2025, 12, 27), LocalDate.of(2025, 12, 30), "Người khuyết tật", 0.20);
        System.out.println("KM" +km3.getNgayBD().format(formatter) + "003");
        if(em.find(KhuyenMai.class, "KM" +km3.getNgayBD().format(formatter) + "003")==null) {
            khuyenMaiService.persistKhuyenMai(km3);
            System.out.println(km3.getMaKM());
        }
        KhuyenMai km4 = new KhuyenMai(LocalDate.of(2025, 12, 27), LocalDate.of(2025, 12, 30), "Trẻ em", 0.05);
        System.out.println("KM" +km4.getNgayBD().format(formatter) + "004");
        if(em.find(KhuyenMai.class, "KM" +km4.getNgayBD().format(formatter) + "004")==null) {
            khuyenMaiService.persistKhuyenMai(km4);
            System.out.println(km4.getMaKM());
        }
        KhuyenMai km5 = new KhuyenMai(LocalDate.of(2025, 12, 27), LocalDate.of(2025, 12, 30), "Người cao tuổi", 0.30);
        if(em.find(KhuyenMai.class, "KM" +km5.getNgayBD().format(formatter) + "005")==null) {
            khuyenMaiService.persistKhuyenMai(km5);
        }
        KhuyenMai km6 = new KhuyenMai(LocalDate.of(2025, 12, 27), LocalDate.of(2025, 12, 30), "Học sinh, sinh viên", 0.30);
        if(em.find(KhuyenMai.class, "KM" +km6.getNgayBD().format(formatter) + "006")==null) {
            khuyenMaiService.persistKhuyenMai(km6);
        }
        KhuyenMai km7 = new KhuyenMai(LocalDate.of(2025, 12, 27), LocalDate.of(2025, 12, 30), "Tất cả", 0.30);
        if(em.find(KhuyenMai.class, "KM" +km7.getNgayBD().format(formatter) + "007")==null) {
            khuyenMaiService.persistKhuyenMai(km7);
        }
        KhuyenMai km8 = new KhuyenMai(LocalDate.of(2025, 12, 27), LocalDate.of(2025, 12, 30), "Người lớn", 0.00);
        if(em.find(KhuyenMai.class, "KM" +km8.getNgayBD().format(formatter) + "008")==null) {
            khuyenMaiService.persistKhuyenMai(km8);
        }
        persistChoNgoi = new ChoNgoiService(em);
        if (loaiCho1 != null && toa1 != null && loaiCho2 != null && loaiCho3 != null && loaiCho4 != null && loaiCho5 != null && loaiCho6 != null && loaiCho7 != null && toa2 != null && toa3 != null
                && toa4 != null && toa5 != null && toa6 != null && toa7 != null && toa8 != null && toa9 != null && toa10 != null && toa11 != null && toa12 != null && toa13 != null && toa14 != null && toa15 != null && toa16 != null
                && toa17 != null && toa18 != null && toa19 != null && toa20 != null && toa21 != null && toa22 != null && toa23 != null && toa24 != null && toa25 != null && toa26 != null && toa27 != null
                && toa28 != null && toa29 != null && toa30 != null && toa31 != null && toa32 != null && toa33 != null && toa34 != null && toa35 != null && toa36 != null && toa37 != null && toa38 != null && toa39 != null && toa40 != null) {
            // Thêm dữ liệu ChoNgoi
            for (int i = 1; i <= 60; i++) {
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho1, toa1));
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho1, toa2));
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho2, toa3));
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho2, toa4));
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho3, toa5));
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho3, toa6));
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho4, toa7));
                if (i <= 40) {
                    persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho5, toa9));
                    if (i <= 30) {
                        persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho6, toa8));
                        if (i <= 10) {
                            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho7, toa10));
                        }
                    }
                }
            }

            for (int i = 1; i <= 60; i++) {
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho1, toa11));
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho1, toa12));
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho2, toa13));
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho2, toa14));
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho3, toa15));
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho3, toa16));
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho4, toa17));
                if (i <= 40) {
                    persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho6, toa19));
                    if (i <= 30) {
                        persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho5, toa18));
                        if (i <= 10) {
                            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho7, toa20));
                        }
                    }
                }
            }
            for (int i = 1; i <= 60; i++) {
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho1, toa21));
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho1, toa22));
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho2, toa23));
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho2, toa24));
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho3, toa25));
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho3, toa26));
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho4, toa27));
                if (i <= 40) {
                    persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho6, toa29));
                    if (i <= 30) {
                        persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho5, toa28));
                        if (i <= 10) {
                            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho7, toa30));
                        }
                    }
                }
            }
            for (int i = 1; i <= 60; i++) {
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho1, toa31));
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho1, toa32));
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho2, toa33));
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho2, toa34));
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho3, toa35));
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho3, toa36));
                persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho4, toa37));
                if (i <= 40) {
                    persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho6, toa39));
                    if (i <= 30) {
                        persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho5, toa38));
                        if (i <= 10) {
                            persistChoNgoi.persistChoNgoi(new ChoNgoi(loaiCho7, toa40));
                        }
                    }
                }
            }
        }
        NhanVienService nhanVienService = new NhanVienService(em);
        if(caLam1 != null && caLam2 != null) {
            // Thêm dữ liệu vào bảng NhanVien
            NhanVien nhanVien1 = new NhanVien();
            nhanVien1.setTenNV("Lê Nguyễn Phi Trường");
            nhanVien1.setGioiTinh("Nam");
            nhanVien1.setNgaySinh(LocalDate.parse("1990-01-01"));
            nhanVien1.setNgayVaoLam(LocalDate.parse("2023-01-01"));
            nhanVien1.setCCCD("123456789012");
            nhanVien1.setSdt("0912345678");
            nhanVien1.setEmail("nguyenvana@example.com");
            nhanVien1.setDiaChi("Hà Nội");
            nhanVien1.setTrangThai("Làm việc");
            nhanVien1.setCaLam(caLam1);
            nhanVien1.setChucVu(chucVu2);
            nhanVien1.setDuongDanAnh("images/employees/nv-1.jpg");
            nhanVienService.persistNhanVien(nhanVien1);

            NhanVien nhanVien2 = new NhanVien();
            nhanVien2.setTenNV("Hồ Quốc Huy");
            nhanVien2.setGioiTinh("Nữ");
            nhanVien2.setNgaySinh(LocalDate.parse("1995-05-10"));
            nhanVien2.setNgayVaoLam(LocalDate.parse("2023-01-10"));
            nhanVien2.setCCCD("234567890123");
            nhanVien2.setSdt("0987654321");
            nhanVien2.setEmail("tranthib@example.com");
            nhanVien2.setDiaChi("Hồ Chí Minh");
            nhanVien2.setTrangThai("Làm việc");
            nhanVien2.setCaLam(caLam2);
            nhanVien2.setChucVu(chucVu1);
            nhanVien2.setDuongDanAnh("images/employees/nv-2.jpg");
            nhanVienService.persistNhanVien(nhanVien2);
        }
    }
}
