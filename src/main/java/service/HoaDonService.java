package service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import entity.HoaDon;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HoaDonService {

    private static EntityManager em;

    public HoaDonService(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void persistHoaDon(HoaDon hd) {
        generateMaHD(hd);
        em.persist(hd);
    }

    private static void generateMaHD(HoaDon hd) {
        if (hd.getMaHD() == null || hd.getMaHD().isEmpty()) {
            String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
            String prefix = "HD" + currentDate;

            // Lấy giá trị số thứ tự lớn nhất và tăng nó lên 1
            Integer maxSeq = (Integer) em.createQuery("SELECT COALESCE(MAX(CAST(SUBSTRING(hd.maHD, 9, 6) AS INTEGER)), 0) + 1 FROM HoaDon hd WHERE hd.maHD LIKE :prefix")
                    .setParameter("prefix", prefix + "%")
                    .getSingleResult();

            String newMaHD = prefix + String.format("%06d", maxSeq);

            hd.setMaHD(newMaHD);
        }
    }
}