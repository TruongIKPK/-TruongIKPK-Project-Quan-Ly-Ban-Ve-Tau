package service;

import entity.ChuyenTau;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ChuyenTauService {

    private EntityManager em;

    public ChuyenTauService(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void persistChuyenTau(ChuyenTau chuyenTau) {
        generateMaChuyen(chuyenTau);
        generateMacTau(chuyenTau);
        System.out.println("Generated macTau: " + chuyenTau.getMacTau());
        em.persist(chuyenTau);
    }
    private void generateMaChuyen(ChuyenTau chuyenTau) {
        if (chuyenTau.getMaChuyen() == null || chuyenTau.getMaChuyen().isEmpty()) {
            String maTau = chuyenTau.getTau().getMaTau();
            String ngayKhoiHanh = chuyenTau.getNgayGioDi().format(DateTimeFormatter.ofPattern("yyMMdd"));

            // Đếm số lượng chuyến tàu hiện tại với prefix
            Long count = (Long) em.createQuery("SELECT COUNT(ct) FROM ChuyenTau ct WHERE ct.tau.maTau = :maTau AND FUNCTION('FORMAT', ct.ngayGioDi, 'yyMMdd') = :ngayKhoiHanh")
                    .setParameter("maTau", maTau)
                    .setParameter("ngayKhoiHanh", ngayKhoiHanh)
                    .getSingleResult();

            int soThuTu = count.intValue() + 1;
            String maChuyen = maTau.trim() + ngayKhoiHanh + String.format("%02d", soThuTu);

            while (em.createQuery("SELECT 1 FROM ChuyenTau ct WHERE ct.maChuyen = :maChuyen")
                    .setParameter("maChuyen", maChuyen)
                    .getResultList().size() > 0) {
                soThuTu++;
                maChuyen = maTau.trim() + ngayKhoiHanh + String.format("%02d", soThuTu);
            }

            chuyenTau.setMaChuyen(maChuyen);
        }
    }
    private void generateMacTau(ChuyenTau chuyenTau) {
        String maTau = chuyenTau.getTau().getMaTau();
        String prefix = maTau.length() >= 2 ? maTau.substring(0, 2) : maTau;
        LocalDateTime ngayGioKhoiHanh = chuyenTau.getNgayGioDi();

        // Xác định khoảng thời gian bắt đầu và kết thúc của ngày
        LocalDate date = ngayGioKhoiHanh.toLocalDate();
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        int base = chuyenTau.getGaDi().getMaGa() == 21 ? 0 : 1; // chẵn nếu 21, lẻ nếu 1
        int modValue = base;

        String jpql = "SELECT COALESCE(MAX(CAST(SUBSTRING(ct.macTau, LENGTH(:prefix) + 1) AS INTEGER)), :base) + 2 " +
                "FROM ChuyenTau ct " +
                "WHERE ct.macTau LIKE CONCAT(:prefix, '%') " +
                "AND ct.ngayGioDi BETWEEN :startOfDay AND :endOfDay " +
                "AND MOD(CAST(SUBSTRING(ct.macTau, LENGTH(:prefix) + 1) AS INTEGER), 2) = :modValue";

        Integer nextMacTau = (Integer) em.createQuery(jpql)
                .setParameter("prefix", prefix)
                .setParameter("base", base)
                .setParameter("modValue", modValue)
                .setParameter("startOfDay", startOfDay)
                .setParameter("endOfDay", endOfDay)
                .getSingleResult();

        // Gán mã chuyến tàu mới
        chuyenTau.setMacTau(prefix + String.format("%02d", nextMacTau));
        System.out.println("Generated macTau: " + chuyenTau.getMacTau());
    }
//    private void generateMacTau(ChuyenTau chuyenTau) {
////        if (chuyenTau.getMacTau() == null || chuyenTau.getMacTau().isEmpty()) {
//            String maTau = chuyenTau.getTau().getMaTau();
////          String prefix = maTau.substring(0, 3); // Giữ nguyên 3 ký tự đầu tiên
//            String prefix = maTau.length() >= 2 ? maTau.substring(0, 2) : maTau;
//            LocalDateTime ngayGioKhoiHanh = chuyenTau.getNgayGioDi();
//
//            int nextMacTau = 1;
//
//            // Kiểm tra ga đi để xác định số chẵn hoặc lẻ
//            if (chuyenTau.getGaDi().getMaGa() == 21) { // Nếu ga đi là 21, tìm số chẵn
//                nextMacTau = (Integer) em.createQuery("SELECT COALESCE(MAX(CAST(SUBSTRING(ct.macTau, LENGTH(:prefix) + 1, LENGTH(ct.macTau) - LENGTH(:prefix)) AS INTEGER)), 0) + 2 " +
//                                "FROM ChuyenTau ct " +
//                                "WHERE ct.macTau LIKE CONCAT(:prefix, '%') " +
//                                "AND DATE(ct.ngayGioDi) = :ngayGioKhoiHanh " +
//                                "AND MOD(CAST(SUBSTRING(ct.macTau, LENGTH(:prefix) + 1, LENGTH(ct.macTau) - LENGTH(:prefix)) AS INTEGER), 2) = 0")
//                        .setParameter("prefix", prefix)
//                        .setParameter("ngayGioKhoiHanh", ngayGioKhoiHanh.toLocalDate())
//                        .getSingleResult();
//            } else if (chuyenTau.getGaDi().getMaGa() == 1) { // Nếu ga đi là 1, tìm số lẻ
//                nextMacTau = (Integer) em.createQuery("SELECT COALESCE(MAX(CAST(SUBSTRING(ct.macTau, LENGTH(:prefix) + 1, LENGTH(ct.macTau) - LENGTH(:prefix)) AS INTEGER)), 1) + 2 " +
//                                "FROM ChuyenTau ct " +
//                                "WHERE ct.macTau LIKE CONCAT(:prefix, '%') " +
//                                "AND DATE(ct.ngayGioDi) = :ngayGioKhoiHanh " +
//                                "AND MOD(CAST(SUBSTRING(ct.macTau, LENGTH(:prefix) + 1, LENGTH(ct.macTau) - LENGTH(:prefix)) AS INTEGER), 2) = 1")
//                        .setParameter("prefix", prefix)
//                        .setParameter("ngayGioKhoiHanh", ngayGioKhoiHanh.toLocalDate())
//                        .getSingleResult();
//            }
//            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx-"+nextMacTau );
//            chuyenTau.setMacTau(prefix + String.format("%02d", nextMacTau));
//
////        }else {
////            System.out.println("MacTau da ton tai: " + chuyenTau.getMacTau());
////        }
//    }
}