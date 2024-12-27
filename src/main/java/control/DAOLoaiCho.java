package control;

import connectDB.ConnectDB;
import entity.LoaiCho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @Dự án: tau-viet-express
 * @Class: DAOLoaiCho
 * @Tạo vào ngày: 10/15/2024
 * @Tác giả: Thai
 */
public class DAOLoaiCho {
    // crud
    /*CREATE TABLE LoaiCho (
    maLC VARCHAR(10) PRIMARY KEY,
    tenLC NVARCHAR(50) NOT NULL UNIQUE,
    giaCho FLOAT CHECK (giaCho > 0),
    moTa NVARCHAR(200) NOT NULL
);*/

    // create loai cho
    public static boolean themLoaiCho(LoaiCho lc) {
        String sql = "INSERT INTO LoaiCho(maLC, tenLC, giaCho, moTa) VALUES(?, ?, ?, ?)";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, lc.getMaLC());
            stmt.setString(2, lc.getTenLC());
            stmt.setDouble(3, lc.getGiaCho());
            stmt.setString(4, lc.getMoTa());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // read loai cho
    public static ArrayList<LoaiCho> getDSLoaiCho() {
        ArrayList<LoaiCho> dsLoaiCho = new ArrayList<>();
        String sql = "SELECT * FROM LoaiCho";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String maLC = rs.getString("maLC");
                String tenLC = rs.getString("tenLC");
                double giaCho = rs.getDouble("giaCho");
                String moTa = rs.getString("moTa");
                LoaiCho lc = new LoaiCho(maLC, tenLC, moTa, giaCho);
                dsLoaiCho.add(lc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsLoaiCho;
    }

    // read loai cho by id
    public static LoaiCho getLoaiChoByID(String maLC) {
        String sql = "SELECT * FROM LoaiCho WHERE maLC = ?";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, maLC);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String tenLC = rs.getString("tenLC");
                    double giaCho = rs.getDouble("giaCho");
                    String moTa = rs.getString("moTa");
                    return new LoaiCho(maLC, tenLC, moTa, giaCho);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // update loai cho
    public static LoaiCho suaLoaiCho(LoaiCho lc) {
        String sql = "UPDATE LoaiCho SET tenLC = ?, giaCho = ?, moTa = ? WHERE maLC = ?";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, lc.getTenLC());
            stmt.setDouble(2, lc.getGiaCho());
            stmt.setString(3, lc.getMoTa());
            stmt.setString(4, lc.getMaLC());
            if (stmt.executeUpdate() > 0) {
                return lc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // delete loai cho
    public static boolean xoaLoaiCho(String maLC) {
        String sql = "DELETE FROM LoaiCho WHERE maLC = ?";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, maLC);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
