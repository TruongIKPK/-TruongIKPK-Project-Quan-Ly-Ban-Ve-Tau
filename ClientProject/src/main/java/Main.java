import gui.FrmDangNhap;
import gui.LoadingGUI;
import ServiceLocator.RemoteLookup;
import java.rmi.RemoteException;

public class Main {
    public static void main(String[] args) throws RemoteException {
        System.out.println("Hello");

        RemoteLookup.setIP("localhost");
        RemoteLookup.setPort(9090);

        LoadingGUI.show();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Đóng loading screen
        LoadingGUI.close();

        // Hiển thị form đăng nhập
        FrmDangNhap frmDangNhap = new FrmDangNhap();
        frmDangNhap.setVisible(true);
        frmDangNhap.toFront();
    }
}
