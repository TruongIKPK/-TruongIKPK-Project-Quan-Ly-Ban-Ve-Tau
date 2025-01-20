package service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import entity.NhanVien;
import entity.TaiKhoan;

import java.time.ZoneId;
import java.util.Date;

public class NhanVienService {

    private EntityManager em;

    public NhanVienService(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void persistNhanVien(NhanVien nhanVien) {
        generateMaNV(nhanVien);

        // Tạo đối tượng TaiKhoan với giá trị matKhau mặc định không null
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setMaTK(nhanVien.getMaNV());
        taiKhoan.setMatKhau("$2a$10$hx.v7Xiy7I8Rpql8ONmMF.WZY3d6pfQmfpp2EgeXJajNJdUa9KVSa");
        taiKhoan.setTrangThai("Kích hoạt");
        em.persist(taiKhoan);
        nhanVien.setTaiKhoan(taiKhoan);

        em.persist(nhanVien);
    }

    @Transactional
    public void updateNhanVien(NhanVien nhanVien) {
        em.merge(nhanVien);
        updateTaiKhoanStatus(nhanVien);
    }

    private void generateMaNV(NhanVien nhanVien) {
        if (nhanVien.getMaNV() == null || nhanVien.getMaNV().isEmpty()) {
            String maChucVu = nhanVien.getChucVu().getMaCV();
            java.util.Date ngayVaoLam = Date.from(nhanVien.getNgayVaoLam()
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant());

            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.setTime(ngayVaoLam);
            String currentYear = String.format("%02d", cal.get(java.util.Calendar.YEAR) % 100);
            String currentMonth = String.format("%02d", cal.get(java.util.Calendar.MONTH) + 1);
            String currentDay = String.format("%02d", cal.get(java.util.Calendar.DAY_OF_MONTH));
            String prefix = maChucVu + currentYear + currentMonth + currentDay;

            // Đếm số lượng nhân viên hiện tại với prefix
            Integer count = (Integer) em.createQuery("SELECT COALESCE(MAX(CAST(SUBSTRING(nv.maNV, LENGTH(nv.maNV) - 2, 3) AS INTEGER)), 0) FROM NhanVien nv WHERE nv.maNV LIKE :prefix")
                    .setParameter("prefix", prefix + "%")
                    .getSingleResult();

            int nextID = count + 1;

            // Tạo mã nhân viên
            String newMaNV = prefix + String.format("%03d", nextID);

            nhanVien.setMaNV(newMaNV);
        }
    }

    private void updateTaiKhoanStatus(NhanVien nhanVien) {
        TaiKhoan taiKhoan = nhanVien.getTaiKhoan();
        if (nhanVien.getTrangThai().equals("Nghỉ làm")) {
            taiKhoan.setTrangThai("Bị khóa");
        } else if (nhanVien.getTrangThai().equals("Làm việc")) {
            taiKhoan.setTrangThai("Kích hoạt");
        }
        em.merge(taiKhoan);
    }
}