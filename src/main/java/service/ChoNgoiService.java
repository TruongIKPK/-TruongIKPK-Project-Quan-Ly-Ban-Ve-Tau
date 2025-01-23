package service;

import connectDB.connectDB_1;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import entity.ChoNgoi;

public class ChoNgoiService {

    @PersistenceContext
//    public EntityManager em = connectDB_1.getEntityManager();
    public EntityManager em;

    public ChoNgoiService(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void persistChoNgoi(ChoNgoi choNgoi) {
        generateMaCho(choNgoi);
        em.persist(choNgoi);
    }

    private void generateMaCho(ChoNgoi choNgoi) {
        if (choNgoi.getMaCho() == null || choNgoi.getMaCho().isEmpty()) {
            String maToa = choNgoi.getToa().getMaToa();

            // Đếm số lượng chỗ ngồi hiện tại của toa
            Integer count = (Integer) em.createQuery("SELECT COALESCE(MAX(CAST(SUBSTRING(c.maCho, LENGTH(c.maCho) - 1, 2) AS INTEGER)), 0) FROM ChoNgoi c WHERE c.toa.maToa = :maToa")
                    .setParameter("maToa", maToa)
                    .getSingleResult();

            int maxSeatNum = count.intValue() + 1;

            // Tạo mã chỗ ngồi
            String newMaCho = maToa.trim() + String.format("%02d", maxSeatNum);

            choNgoi.setMaCho(newMaCho);
        }
    }

}