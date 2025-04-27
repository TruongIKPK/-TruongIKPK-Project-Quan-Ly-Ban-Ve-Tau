package service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import entity.KhachHang;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class KhachHangService {

    private EntityManager em;

    public KhachHangService(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void persistKhachHang(KhachHang khachHang) {
        generateMaKH(khachHang);
        em.persist(khachHang);
    }

    private void generateMaKH(KhachHang khachHang) {
        if (khachHang.getMaKH() == null || khachHang.getMaKH().isEmpty()) {
            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
            String prefix = "KH" + currentDate;

            // Lấy giá trị số thứ tự lớn nhất và tăng nó lên 1
            Integer maxSeq = (Integer) em.createQuery("SELECT COALESCE(MAX(CAST(SUBSTRING(kh.maKH, 9, 6) AS INTEGER)), 0) + 1 FROM KhachHang kh WHERE kh.maKH LIKE :prefix")
                    .setParameter("prefix", prefix + "%")
                    .getSingleResult();

            String newMaKH = prefix + String.format("%06d", maxSeq);

            khachHang.setMaKH(newMaKH);
        }
    }
}