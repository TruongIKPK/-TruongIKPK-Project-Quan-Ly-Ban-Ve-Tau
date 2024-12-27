import connectDB.ConnectDB;
import gui.FrmDangNhap;
import gui.FrmTrangChinh;
import gui.LoadingGUI;

import javax.swing.*;
import java.sql.SQLException;

/**
    * @Dự án: tau-viet-express
    * @Class: TestApp
    * @Tạo vào ngày: 10/6/2024
    * @Tác giả: Huy
*/
public class TestApp {
    public static void main(String[] args) {
        // Kết nối cơ sở dữ liệu
        try {
            ConnectDB.connect();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không thể kết nối CSDL!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(1);
        }

        LoadingGUI.show(); // Hiển thị loading screen

        // Chờ 1 giây để hiển thị loading screen
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LoadingGUI.close(); // Đóng loa                 ding screen
        FrmDangNhap frmDangNhap = new FrmDangNhap();
        frmDangNhap.setVisible(true);
        frmDangNhap.toFront();
    }
}
