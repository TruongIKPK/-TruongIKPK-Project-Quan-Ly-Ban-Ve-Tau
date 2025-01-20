package service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import entity.Toa;

import java.util.List;

public class ToaService {

    @PersistenceContext
    public EntityManager em;

    @Transactional
    public void persistToa(Toa toa) {
        generateMaToa(toa);
        em.persist(toa);
    }

    private void generateMaToa(Toa toa) {
        if (toa.getMaToa() == null || toa.getMaToa().isEmpty()) {
            String tauId = toa.getTau().getMaTau();
            String loaiToaId = toa.getLoaiToa().getMaLT();

            // Đếm số lượng toa hiện tại của tàu
            Long count = (Long) em.createQuery("SELECT COUNT(t) FROM Toa t WHERE t.tau.maTau = :tauId")
                    .setParameter("tauId", tauId)
                    .getSingleResult();

            int maxToaNum = count.intValue() + 1;

            // Tạo mã toa
            String newMaToa;
            do {
                newMaToa = tauId.trim() + loaiToaId.trim() + String.format("%02d", maxToaNum);
                maxToaNum++;
            } while (!em.createQuery("SELECT t FROM Toa t WHERE t.maToa = :newMaToa")
                    .setParameter("newMaToa", newMaToa)
                    .getResultList().isEmpty());

            toa.setMaToa(newMaToa);
        }
    }
}