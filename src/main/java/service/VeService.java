package service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import entity.Ve;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VeService {
    private EntityManager em;

    public VeService(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void persistVe(Ve ve) {
        generateMaVe(ve);
        em.persist(ve);
    }

    private void generateMaVe(Ve ve) {
        if (ve.getMaVe() == null || ve.getMaVe().isEmpty()) {
            LocalDateTime now = LocalDateTime.now();
            String prefix = "VE" + now.format(DateTimeFormatter.ofPattern("yyMMdd"));

            // Đếm số lượng vé hiện tại với prefix
            Integer maxVeNum = (Integer) em.createQuery("SELECT COALESCE(MAX(CAST(SUBSTRING(v.maVe, 9, 6) AS INTEGER)), 0) FROM Ve v WHERE LEFT(v.maVe, 8) = :prefix")
                    .setParameter("prefix", prefix)
                    .getSingleResult();

            int nextVeNum = maxVeNum + 1;

            // Tạo mã vé
            String newMaVe = prefix + String.format("%06d", nextVeNum);

            ve.setMaVe(newMaVe);
        }
    }
}