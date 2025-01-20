package control;

import connectDB.ConnectDB;
import entity.ChucVu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOChucVu {

    // Get list of ChucVu
    public static ArrayList<ChucVu> getDanhSachChucVu() {
        ArrayList<ChucVu> dsChucVu = new ArrayList<>();
        String sql = "SELECT * FROM ChucVu";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String maCV = rs.getString("MaCV");
                String tenCV = rs.getString("TenCV");

                ChucVu cv = new ChucVu(maCV, tenCV);
                dsChucVu.add(cv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsChucVu;
    }

//     Get ChucVu by maCV
    public static ChucVu getChucVuTheoMa(String maCV) {
        ChucVu cv = null;
        String sql = "SELECT * FROM ChucVu WHERE MaCV = ?";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {

            stmt.setString(1, maCV);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String ma = rs.getString("MaCV");
                    String ten = rs.getString("TenCV");
                    cv = new ChucVu(ma, ten);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cv;
    }

    // them chuc vu
    public static boolean themChucVu(ChucVu cv) {
        String sql = "INSERT INTO ChucVu(maCV, tenCV) VALUES(?, ?)";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, cv.getMaCV());
            stmt.setString(2, cv.getTenCV());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //sua chuc vu tra ve doi tuong chuc vu
    public static ChucVu suaChucVu(ChucVu cv) {
        String sql = "UPDATE ChucVu SET tenCV = ? WHERE maCV = ?";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, cv.getTenCV());
            stmt.setString(2, cv.getMaCV());
            if (stmt.executeUpdate() > 0) {
                return cv;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}