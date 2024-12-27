package control;

import connectDB.ConnectDB;
import entity.CaLam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * @Dự án: tau-viet-express
 * @Class: DAOCaLam
 * @Tạo vào ngày: 10/15/2024
 * @Tác giả: Thai
 */
public class DAOCaLam {
    /*
    * maCL	VARCHAR(3)	PRIMARY KEY, IN ('CA1', 'CA2')
tenCL	NVARCHAR(20)	NOT NULL
gioBD	TIME	NOT NULL
gioKetThuc	TIME	NOT NULL*/

    // lay danh sach ca lam
    public static ArrayList<CaLam> getDanhSachCaLam() {
        ArrayList<CaLam> dsCaLam = new ArrayList<>();
        String sql = "SELECT * FROM CaLam";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String maCL = rs.getString("MaCL");
                String tenCL = rs.getString("TenCL");
                LocalTime gioBD = rs.getTime("GioBD").toLocalTime();
                LocalTime gioKetThuc = rs.getTime("gioKetThuc").toLocalTime();
                CaLam cl = new CaLam(maCL, tenCL, gioBD, gioKetThuc);
                dsCaLam.add(cl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsCaLam;
    }

    // them ca lam
    public static boolean themCaLam(CaLam cl) {
        String sql = "INSERT INTO CaLam(maCL, tenCL, gioBD, gioKetThuc) VALUES(?, ?, ?, ?)";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, cl.getMaCL());
            stmt.setString(2, cl.getTenCL());
            stmt.setTime(3, Time.valueOf(cl.getGioBD()));
            stmt.setTime(4, Time.valueOf(cl.getGioKetThuc()));
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // sua ca lam tra ve doi tuong ca lam
    public static CaLam suaCaLam(CaLam cl) {
        String sql = "UPDATE CaLam SET TenCL = ?, GioBD = ?, GioKetThuc = ? WHERE MaCL = ?";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, cl.getTenCL());
            stmt.setTime(2, Time.valueOf(cl.getGioBD()));
            stmt.setTime(3, Time.valueOf(cl.getGioKetThuc()));
            stmt.setString(4, cl.getMaCL());
            if (stmt.executeUpdate() > 0) {
                return cl;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    // xoa ca lam
    public static boolean xoaCaLam(String maCL) {
        String sql = "DELETE FROM CaLam WHERE MaCL = ?";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, maCL);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // lay ca lam theo ma
    public static CaLam getCaLamTheoMa(String maCL) {
        CaLam cl = null;
        String sql = "SELECT * FROM CaLam WHERE MaCL = ?";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, maCL);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String ma = rs.getString("MaCL");
                    String ten = rs.getString("TenCL");
                    LocalTime gioBD = rs.getTime("GioBD").toLocalTime();
                    LocalTime gioKetThuc = rs.getTime("GioKetThuc").toLocalTime();
                    cl = new CaLam(ma, ten, gioBD, gioKetThuc);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cl;
    }
}
