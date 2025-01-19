import gui.FrmDangNhap;
import gui.LoadingGUI;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class MainTest {
    public static void main(String[] args) {
        System.out.println("Hello");

        EntityManager em = Persistence.createEntityManagerFactory("mssql").createEntityManager();
        EntityTransaction transaction = em.getTransaction();
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
