package control;

import connectDB.ConnectDB;
import connectDB.connectDB_1;
import entity.Ga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

/**
 * @Dự án: tau-viet-express
 * @Class: DAOGa
 * @Tạo vào ngày: 10/18/2024
 * @Tác giả: Huy
 */
public class DAOGa {
//     //Get danh sách ga
//    public static ArrayList<Ga> getDsGa() {
//        System.out.println("DAO: Get danh sách ga");
//        ArrayList<Ga> dsGa = new ArrayList<>();
//        try {
//            Connection connection = ConnectDB.getConnection();
//            String sql = "SELECT * FROM Ga";
//            ResultSet rs = connection.createStatement().executeQuery(sql);
//            while (rs.next()) {
//                int maGa = rs.getInt("maGa");
//                String tenGa = rs.getString("tenGa");
//                Ga ga = new Ga(maGa, tenGa);
//                dsGa.add(ga);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return dsGa;
//    }\

    //Get danh sách ga
    public static ArrayList<Ga> getDsGa() {
        System.out.println("DAO: Get danh sách ga");
        ArrayList<Ga> dsGa = new ArrayList<>();
        try {
            // Tạo truy vấn JPQL để lấy tất cả Ga từ cơ sở dữ liệu
            String jpql = "SELECT g FROM Ga g"; // Truy vấn JPQL
            TypedQuery<Ga> query = em.createQuery(jpql, Ga.class);

            // Thực thi truy vấn và lấy kết quả
            dsGa = new ArrayList<>(query.getResultList()); // Danh sách Ga trả về từ truy vấn

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsGa;
    }


    private static EntityManager em = connectDB_1.getEntityManager();


    // Get Ga theo mã ga
//    public static Ga getGaTheoMaGa(int maGa) {
//        Ga ga = null;
//
//        String sql = "SELECT * FROM Ga WHERE maGa = ?";
//
//        try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
//
//            ps.setInt(1, maGa);
//
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                String tenGa = rs.getString("tenGa");
//                ga = new Ga(maGa, tenGa);
//
//                return ga;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return ga;
//    }

    // Get Ga theo mã ga
    public static Ga getGaTheoMaGa(int maGa) {
        try {
            String jpql = "SELECT g FROM Ga g WHERE g.maGa = :maGa";
            TypedQuery<Ga> query = em.createQuery(jpql, Ga.class);
            query.setParameter("maGa", maGa);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


//    // them ga
//    public static boolean themGa(Ga ga) {
//        String sql = "INSERT INTO Ga(maGa, tenGa) VALUES(?, ?)";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setInt(1, ga.getMaGa());
//            stmt.setString(2, ga.getTenGa());
//            return stmt.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    // them ga
    public static boolean themGa(Ga ga) {
        try {
            em.getTransaction().begin();
            em.persist(ga);
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

//    // sua ga tra ve doi tuong ga
//    public static Ga suaGa(Ga ga) {
//        String sql = "UPDATE Ga SET tenGa = ? WHERE maGa = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, ga.getTenGa());
//            stmt.setInt(2, ga.getMaGa());
//            if (stmt.executeUpdate() > 0) {
//                return ga;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//

    // sua ga tra ve doi tuong ga
    public static Ga suaGa(Ga ga) {
        try {
            em.getTransaction().begin();
            String jpql = "UPDATE Ga g SET g.tenGa = :tenGa WHERE g.maGa = :maGa";
            int updatedCount = em.createQuery(jpql)
                    .setParameter("tenGa", ga.getTenGa())
                    .setParameter("maGa", ga.getMaGa())
                    .executeUpdate();
            em.getTransaction().commit();
            return updatedCount > 0 ? ga : null;
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        return null;
    }
}
