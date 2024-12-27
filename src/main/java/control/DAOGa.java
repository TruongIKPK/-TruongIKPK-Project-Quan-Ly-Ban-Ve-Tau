package control;

import connectDB.ConnectDB;
import entity.Ga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Dự án: tau-viet-express
 * @Class: DAOGa
 * @Tạo vào ngày: 10/18/2024
 * @Tác giả: Huy
 */
public class DAOGa {
    // Get danh sách ga
    public static ArrayList<Ga> getDsGa() {
        System.out.println("DAO: Get danh sách ga");
        ArrayList<Ga> dsGa = new ArrayList<>();
        try {
            Connection connection = ConnectDB.getConnection();
            String sql = "SELECT * FROM Ga";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next()) {
                int maGa = rs.getInt("maGa");
                String tenGa = rs.getString("tenGa");
                Ga ga = new Ga(maGa, tenGa);
                dsGa.add(ga);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dsGa;
    }

    // Get Ga theo mã ga
    public static Ga getGaTheoMaGa(int maGa) {
        Ga ga = null;

        String sql = "SELECT * FROM Ga WHERE maGa = ?";

        try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {

            ps.setInt(1, maGa);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String tenGa = rs.getString("tenGa");
                ga = new Ga(maGa, tenGa);

                return ga;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ga;
    }

    // them ga
    public static boolean themGa(Ga ga) {
        String sql = "INSERT INTO Ga(maGa, tenGa) VALUES(?, ?)";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, ga.getMaGa());
            stmt.setString(2, ga.getTenGa());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // sua ga tra ve doi tuong ga
    public static Ga suaGa(Ga ga) {
        String sql = "UPDATE Ga SET tenGa = ? WHERE maGa = ?";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, ga.getTenGa());
            stmt.setInt(2, ga.getMaGa());
            if (stmt.executeUpdate() > 0) {
                return ga;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
