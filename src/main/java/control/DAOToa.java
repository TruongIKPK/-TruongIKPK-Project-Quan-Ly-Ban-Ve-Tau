package control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import connectDB.ConnectDB;
import entity.ChoNgoi;
import entity.LoaiToa;
import entity.Tau;
import entity.Toa;

/**
 * @Dự án: tau-viet-express
 * @Class: DAOToa
 * @Tạo vào ngày: 10/15/2024
 * @Tác giả: Thai
 */
public class DAOToa {

    private static ArrayList<Toa> danhSachToa;

    /**
     * Lấy danh sách tất cả các toa.
     * @return danh sách các toa.
     */
    public static ArrayList<Toa> getDanhSachToa() {
        if (danhSachToa != null) {
            return danhSachToa;
        }

        danhSachToa = new ArrayList<>();
        String sql = "SELECT * FROM Toa ORDER BY RIGHT(maToa, 2)";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Toa toa = taoToaTuResultSet(rs);
                danhSachToa.add(toa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachToa;
    }

    /**
     * Thêm một toa mới vào cơ sở dữ liệu.
     * @param toa đối tượng toa cần thêm.
     * @return true nếu thêm thành công, ngược lại false.
     */
    public static boolean themToa(Toa toa) {
        String sql = "INSERT INTO Toa(maToa, soLuongCho, maTau, maLoaiToa) VALUES(?, ?, ?, ?)";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, toa.getMaToa());
            stmt.setInt(2, toa.getSoLuongCho());
            stmt.setString(3, toa.getTau().getMaTau());
            stmt.setString(4, toa.getLoaiToa().getMaLT());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Xóa toa khỏi cơ sở dữ liệu theo mã.
     * @param maToa mã của toa cần xóa.
     * @return true nếu xóa thành công, ngược lại false.
     */
    public static boolean xoaToa(String maToa) {
        String sql = "DELETE FROM Toa WHERE maToa = ?";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, maToa);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Sửa thông tin một toa trong cơ sở dữ liệu.
     * @param toa đối tượng toa chứa thông tin cần cập nhật.
     * @return đối tượng toa đã được sửa nếu thành công, ngược lại null.
     */
    public static Toa suaToa(Toa toa) {
        String sql = "UPDATE Toa SET soLuongCho = ?, maTau = ?, maLoaiToa = ? WHERE maToa = ?";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, toa.getSoLuongCho());
            stmt.setString(2, toa.getTau().getMaTau());
            stmt.setString(3, toa.getLoaiToa().getMaLT());
            stmt.setString(4, toa.getMaToa());

            if (stmt.executeUpdate() > 0) {
                return toa;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Lấy danh sách toa theo mã tàu.
     * @param maTau mã tàu cần lấy danh sách toa.
     * @return danh sách các toa của tàu.
     */
    public static ArrayList<Toa> getToaTheoTau(String maTau) {
        ArrayList<Toa> dsToaTheoTau = new ArrayList<>();

        for (Toa t : getDanhSachToa()) {
            if (t.getTau().getMaTau().equals(maTau)) {
                dsToaTheoTau.add(t);
            }
        }

        return dsToaTheoTau;
    }

    /**
     * Lấy danh sách toa theo mã loại toa.
     * @param maLoaiToa mã loại toa cần lấy danh sách toa.
     * @return danh sách các toa thuộc loại toa đó.
     */
    public static ArrayList<Toa> getToaTheoLoaiToa(String maLoaiToa) {
        ArrayList<Toa> dsToa = new ArrayList<>();
        String sql = "SELECT * FROM Toa WHERE maLoaiToa = ?";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, maLoaiToa);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Toa toa = taoToaTuResultSet(rs);
                dsToa.add(toa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dsToa;
    }

    /**
     * Lấy toa theo mã toa.
     * @param maToa mã toa cần tìm.
     * @return đối tượng toa nếu tìm thấy, ngược lại null.
     */
    public static Toa getToaTheoMa(String maToa) {
        String sql = "SELECT * FROM Toa WHERE maToa = ?";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, maToa);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return taoToaTuResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Tạo đối tượng Toa từ ResultSet.
     * @param rs ResultSet chứa thông tin toa.
     * @return đối tượng Toa.
     * @throws SQLException nếu xảy ra lỗi khi đọc dữ liệu từ ResultSet.
     */
    private static Toa taoToaTuResultSet(ResultSet rs) throws SQLException {
        String maToa = rs.getString("maToa");
        String maTau = rs.getString("maTau");
        String maLoaiToa = rs.getString("maLoaiToa");
        int soLuongCho = rs.getInt("soLuongCho");

        Tau tau = new Tau(maTau);
        LoaiToa loaiToa = DAOLoaiToa.getLoaiToaTheoMa(maLoaiToa);
        ArrayList<ChoNgoi> danhSachChoNgoi = DAOChoNgoi.getDSChoNgoiTheoToa(maToa);

        return new Toa(maToa, tau, loaiToa, soLuongCho, danhSachChoNgoi);
    }
}
