package service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import entity.KhuyenMai;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class KhuyenMaiService {

    private EntityManager em;

    public KhuyenMaiService(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void persistKhuyenMai(KhuyenMai khuyenMai) {
        generateMaKM(khuyenMai);
        em.persist(khuyenMai);
    }

    private void generateMaKM(KhuyenMai khuyenMai) {
        if (khuyenMai.getMaKM() == null || khuyenMai.getMaKM().isEmpty()) {
            LocalDate ngayApDung = khuyenMai.getNgayBD();
            String prefix = "KM" + ngayApDung.format(DateTimeFormatter.ofPattern("yyMMdd"));

            // Lấy giá trị số thứ tự lớn nhất và tăng nó lên 1
            Integer maxNum = (Integer) em.createQuery("SELECT COALESCE(MAX(CAST(SUBSTRING(km.maKM, 8, 3) AS INTEGER)), 0) FROM KhuyenMai km WHERE km.maKM LIKE :prefix")
                    .setParameter("prefix", prefix + "%")
                    .getSingleResult();

            int nextNum = maxNum + 1;

            // Tạo mã khuyến mãi mới
            String newMaKM = prefix + String.format("%03d", nextNum);

            khuyenMai.setMaKM(newMaKM);
        }
    }
}