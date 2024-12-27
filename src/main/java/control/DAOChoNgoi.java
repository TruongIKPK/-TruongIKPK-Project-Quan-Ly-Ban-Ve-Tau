package control;

import connectDB.ConnectDB;
import entity.*;

import java.sql.*;
import java.util.ArrayList;

import entity.ChoNgoi;
import enums.ETrangThaiChoNgoi;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @Dự án: tau-viet-express
 * @Class: DAOChoNgoi
 * @Tạo vào ngày: 10/15/2024
 * @Tác giả: Thai
 */
public class DAOChoNgoi {
    private static ArrayList<ChoNgoi> dsChoNgoi;

    public static ArrayList<ChoNgoi> getDanhSachChoNgoi() {
        if (dsChoNgoi != null) {
            return dsChoNgoi;
        }

        dsChoNgoi = new ArrayList<>();
        String sql = "SELECT * FROM ChoNgoi";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maCho = rs.getString("maCho");
                String maLoaiCho = rs.getString("maLoaiCho");
                String maToa = rs.getString("maToa");
                LoaiCho loaiCho = DAOLoaiCho.getLoaiChoByID(maLoaiCho);
                Toa toa = DAOToa.getToaTheoMa(maToa);
                ChoNgoi choNgoi = new ChoNgoi(maCho, loaiCho, toa);
                dsChoNgoi.add(choNgoi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsChoNgoi;
    }

    // get ds cho ngoi theo ma toa
    public static ArrayList<ChoNgoi> getDSChoNgoiTheoToa(String maToa) {
        System.out.println("getDSChoNgoiTheoToa");
        ArrayList<ChoNgoi> dsChoNgoi = getDanhSachChoNgoi();
        ArrayList<ChoNgoi> dsChoNgoiTheoToa = new ArrayList<>();
        for (ChoNgoi choNgoi : dsChoNgoi) {
            if (choNgoi.getToa().getMaToa().equals(maToa)) {
                dsChoNgoiTheoToa.add(choNgoi);
            }
        }

        System.out.println("dsChoNgoiTheoToa: " + maToa + " - " + dsChoNgoiTheoToa.size());

        return dsChoNgoiTheoToa;
    }


    //get ds cho ngoi theo ma toa va trang thai trong
    public static ArrayList<ChoNgoi> getDSChoNgoiTheoToaTrangThai(String maToa, String trangThai) {
        ArrayList<ChoNgoi> dsChoNgoi = new ArrayList<>();
        String sql = "SELECT * FROM ChoNgoi WHERE maToa = ? AND trangThai = ?";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, maToa);
            stmt.setString(2, trangThai);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String maCho = rs.getString("maCho");
                    String strMaLoaiCho = rs.getString("maLoaiCho");
                    LoaiCho loaiCho = DAOLoaiCho.getLoaiChoByID(strMaLoaiCho);
                    String maToa1 = rs.getString("maToa");
                    Toa toa = new Toa(maToa1);//
                    ChoNgoi choNgoi = new ChoNgoi(maCho, loaiCho, toa);
                    dsChoNgoi.add(choNgoi);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return dsChoNgoi;
    }


    // get cho ngoi theo ma
    public static ChoNgoi getChoNgoiTheoMa(String maCho) {
        ArrayList<ChoNgoi> dsChoNgoi = getDanhSachChoNgoi();
        for (ChoNgoi choNgoi : dsChoNgoi) {
            if (choNgoi.getMaCho().equals(maCho)) {
                return choNgoi;
            }
        }
        return null;
    }

    // THEM CHO NGOI [maCho], [maloaiCho], [maToa], [trangThai]
    public static boolean themChoNgoi(ChoNgoi choNgoi) {
        String sql = "INSERT INTO ChoNgoi(maCho, maloaiCho, maToa) VALUES(?, ?, ?)";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, choNgoi.getMaCho());
            stmt.setString(2, choNgoi.getLoaiCho().getMaLC());
            stmt.setString(3, choNgoi.getToa().getMaToa());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

