package control;

import connectDB.ConnectDB;
import entity.Tau;
import entity.Toa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Dự án: tau-viet-express
 * @Class: DAOTau
 * @Tạo vào ngày: 10/15/2024
 * @Tác giả: Thai
 */
public class DAOTau {
    private static ArrayList<Tau> dsTau;


    private static EntityManager em;

    public DAOTau(EntityManager entityManager) {
        this.em = entityManager;
    }

//    // Thêm tàu
//    public static boolean themTau(Tau tau) {
//        String sql = "INSERT INTO Tau(maTau, tenTau, trangThai) VALUES(?, ?, ?)";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, tau.getMaTau());
//            stmt.setString(2, tau.getTenTau());
//            stmt.setString(3, tau.getTrangThai());
//            return stmt.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    // Thêm tàu
    public static boolean themTau(Tau tau) {
        try {
            em.getTransaction().begin();
            em.persist(tau);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        return false;
    }
//    // Câp nhật trạng thái tàu
//    public static boolean capNhatTrangThaiTau(String maTau, String trangThai) {
//        String sql = "UPDATE Tau SET trangThai = ? WHERE maTau = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, trangThai);
//            stmt.setString(2, maTau);
//            return stmt.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }


    // Câp nhật trạng thái tàu
    public static boolean capNhatTrangThaiTau(String maTau, String trangThai) {
        try {
            em.getTransaction().begin();
            String jpql = "UPDATE Tau t SET t.trangThai = :trangThai WHERE t.maTau = :maTau";
            int updatedCount = em.createQuery(jpql)
                    .setParameter("trangThai", trangThai)
                    .setParameter("maTau", maTau)
                    .executeUpdate();
            em.getTransaction().commit();
            return updatedCount > 0;
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        return false;
    }

//    // Cập nhật tàu
//    public static boolean capNhatTau(Tau tau) {
//        String sql = "UPDATE Tau SET tenTau = ?, trangThai = ? WHERE maTau = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, tau.getTenTau());
//            stmt.setString(2, tau.getTrangThai());
//            stmt.setString(3, tau.getMaTau());
//            return stmt.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }

    // Cập nhật tàu
    public static boolean capNhatTau(Tau tau) {
        try {
            em.getTransaction().begin();
            String jpql = "UPDATE Tau t SET t.tenTau = :tenTau, t.trangThai = :trangThai WHERE t.maTau = :maTau";
            int updatedCount = em.createQuery(jpql)
                    .setParameter("tenTau", tau.getTenTau())
                    .setParameter("trangThai", tau.getTrangThai())
                    .setParameter("maTau", tau.getMaTau())
                    .executeUpdate();
            em.getTransaction().commit();
            return updatedCount > 0;
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        return false;
    }

//
//     //Get danh sach tàu
//    public static ArrayList<Tau> getDSTau() {
//        if (dsTau != null) {
//            // Nếu tàu đã được khởi tạo thì trả về danh sách tàu
//            return dsTau;
//        }
//
//        dsTau = new ArrayList<>(); // Nếu tàu chưa được khởi tạo thì khởi tạo mới
//        String sql = "SELECT * FROM Tau";
//
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//
//            while (rs.next()) {
//                String maTau = rs.getString("maTau");
//                String tenTau = rs.getString("tenTau");
//                String trangThai = rs.getString("trangThai");
//                ArrayList<Toa> dsToa = DAOToa.getToaTheoTau(maTau);
//                Tau tau = new Tau(maTau, tenTau, trangThai, dsToa);
//                dsTau.add(tau);
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return dsTau;
//    }
//

    //Get danh sach tàu
    public static ArrayList<Tau> getDSTau() {
        if (dsTau != null) {
            // Nếu tàu đã được khởi tạo thì trả về danh sách tàu
            return dsTau;
        }

        dsTau = new ArrayList<>(); // Nếu tàu chưa được khởi tạo thì khởi tạo mới
        try {
            // Truy vấn JPQL lấy danh sách tàu
            String jpql = "SELECT t FROM Tau t";
            TypedQuery<Tau> query = em.createQuery(jpql, Tau.class);

            // Lấy danh sách tàu từ cơ sở dữ liệu
            List<Tau> tauList = query.getResultList();

            // Duyệt qua từng tàu và lấy thêm thông tin về các toa
            for (Tau tau : tauList) {
                String maTau = tau.getMaTau();
                ArrayList<Toa> dsToa = DAOToa.getToaTheoTau(maTau);
                tau.setDanhSachToa(dsToa);
            }

            // Thêm tất cả tàu vào danh sách dsTau
            dsTau.addAll(tauList);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsTau;
    }


//    // Get tàu theo mã
//    public static Tau getTauTheoMa(String maTau) {
//        ArrayList<Tau> dsTau = getDSTau();
//        for (Tau tau : dsTau) {
//            if (tau.getMaTau().equals(maTau)) {
//                return tau;
//            }
//        }
//
//        return null;
//    }
//}

    // Get tàu theo mã
    public static Tau getTauTheoMa(String maTau) {
        try {
            String jpql = "SELECT t FROM Tau t WHERE t.maTau = :maTau";
            TypedQuery<Tau> query = em.createQuery(jpql, Tau.class);
            query.setParameter("maTau", maTau);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}