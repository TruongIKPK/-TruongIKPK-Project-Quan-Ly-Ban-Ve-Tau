//import connectDB.connectDB_1;
//import control.*;
//import gui.FrmDangNhap;
//import gui.LoadingGUI;
//import jakarta.persistence.EntityManager;
//
//public class TestApp {
//
//    public static EntityManager em;
//
//    public static void main(String[] args) {
//        // Kết nối cơ sở dữ liệu
//        connectDB_1.connect();
//        em = connectDB_1.getEntityManager();
//
//        // Bắt đầu giao dịch
//        em.getTransaction().begin();
//        System.out.println("Hello");
//        // Hiển thị loading screen
//        LoadingGUI.show();
//
//        // Chờ 1 giây để hiển thị loading screen
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        // Đóng loading screen
//        LoadingGUI.close();
//
//        // Hiển thị form đăng nhập
//        FrmDangNhap frmDangNhap = new FrmDangNhap();
//        frmDangNhap.setVisible(true);
//        frmDangNhap.toFront();
//
//        // Đóng EntityManager và EntityManagerFactory khi không còn sử dụng
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            if (em != null && em.isOpen()) {
//                em.close();
//            }
//            connectDB_1.close();
//        }));
//    }
//}