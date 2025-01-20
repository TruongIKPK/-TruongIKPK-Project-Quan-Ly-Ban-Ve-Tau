import connectDB.ConnectDB;
import control.DAOGa;
import control.DAONhanVien;
import control.DAOTaiKhoan;
import control.DAOTau;
import gui.FrmDangNhap;
import gui.FrmTrangChinh;
import gui.LoadingGUI;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.sql.SQLException;

public class TestApp {

    public static void main(String[] args) {
        // Kết nối cơ sở dữ liệu

        EntityManager em = Persistence.createEntityManagerFactory("mssql").createEntityManager();
        em.getTransaction().begin();
        DAOTaiKhoan daoTaiKhoan = new DAOTaiKhoan(em);
        DAONhanVien daoNhanVien = new DAONhanVien(em);
        DAOGa daoGa = new DAOGa(em);
        DAOTau daoTau = new DAOTau(em);
        System.out.println("Hello");
//        try {
//            ConnectDB.connect();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Không thể kết nối CSDL!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            e.printStackTrace();
//            System.exit(1);
//        }

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
