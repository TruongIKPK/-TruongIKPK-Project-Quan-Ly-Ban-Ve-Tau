package utils;

/**
 * @Dự án: tau-viet-express
 * @Class: Pro
 * @Tạo vào ngày: 12/13/2024
 * @Tác giả: Thai
 */
import control.impl.DAOKhachHang;
import control.impl.DAOKhuyenMai;
import entity.KhachHang;
import entity.KhuyenMai;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PromotionNotifier {

    private EmailService emailService = new EmailService();
    private DAOKhachHang daoKhachHang;
    private DAOKhuyenMai daoKhuyenMai;

    public PromotionNotifier() throws RemoteException {
        this.daoKhachHang = new DAOKhachHang();
        this.daoKhuyenMai = new DAOKhuyenMai();
    }
    //ham nay de lam gi: gui email thong bao cho khach hang khi khuyen mai sap ket thuc
    public void schedulePromotionNotifications() {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    checkAndSendNotifications();
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 0, 24 * 60 * 60 * 1000); // Run once a day
    }

    // Phương thức kiểm tra và gửi thông báo
    private void checkAndSendNotifications() throws RemoteException {
        ArrayList<KhuyenMai> promotions = daoKhuyenMai.getDSKhuyenMai();
        LocalDate today = LocalDate.now();
        LocalDate notificationDate = today.plusDays(10); // Ngày cần thông báo trước

        for (KhuyenMai promotion : promotions) {
            LocalDate ngayKT = promotion.getNgayKT(); // Ngày kết thúc khuyến mãi
            boolean daGuiThongBao = promotion.isDaGuiThongBao();

            // Nếu khuyến mãi đã hết hạn hoặc gần hết hạn và chưa gửi thông báo, thì gửi
            if (!daGuiThongBao) {
                if (ngayKT.isBefore(notificationDate) || ngayKT.isEqual(notificationDate)) {
                    sendNotification(promotion);
                }
            }
        }
    }


    // ham nay de lam gi: gui thong bao
    private void sendNotification(KhuyenMai promotion) throws RemoteException {
        //neu la tat ca thi gui het cho tat ca khach hang
        if (promotion.getDoiTuong().equals("Tất cả")) {
            sendToAllCustomers(promotion);
        } else {
            sendToTargetCustomers(promotion);
        }

        //cap nhat la da gui thong bao
        daoKhuyenMai.capNhatTrangThaiDaGuiThongBao(promotion.getMaKM());
    }
    private void sendToAllCustomers(KhuyenMai promotion) throws RemoteException {
        ArrayList<KhachHang> customers = daoKhachHang.layDanhSachKhachHang();
        String content = "Đừng bỏ lỡ cơ hội ưu đãi vé tàu hấp dẫn!  \n" +
                "\n" +
                "Kính gửi Quý khách hàng,  \n" +
                "\n" +
                "Chương trình khuyến mãi hấp dẫn dành cho tất cả khách hàng sẽ kết thúc vào ngày " + promotion.getNgayKT() + " Đây là cơ hội tuyệt vời để trải nghiệm những chuyến đi tiết kiệm và thoải mái nhất!  \n" +
                "\n" +
                "Nhanh tay đặt vé ngay hôm nay để không bỏ lỡ ưu đãi có hạn này.  \n" +
                "\n" +
                "Trân trọng,  \n" +
                "Công ty Tàu Việt";

        for (KhachHang customer : customers) {
            emailService.sendPromotionNotification(customer.getEmail(), content);
        }
    }

    private  void sendToTargetCustomers(KhuyenMai promotion) throws RemoteException {
        ArrayList<KhachHang> customers = daoKhachHang.layKhachHangTheoDoiTuong(promotion.getDoiTuong());
        String content = "Đừng bỏ lỡ cơ hội ưu đãi vé tàu hấp dẫn!  \n" +
                "\n" +
                "Kính gửi Quý khách hàng,  \n" +
                "\n" +
                "Chương trình khuyến mãi hấp dẫn dành cho đối tượng " + promotion.getDoiTuong() + " sẽ kết thúc vào ngày " + promotion.getNgayKT() + " Đây là cơ hội tuyệt vời để trải nghiệm những chuyến đi tiết kiệm và thoải mái nhất!  \n" +
                "\n" +
                "Nhanh tay đặt vé ngay hôm nay để không bỏ lỡ ưu đãi có hạn này.  \n" +
                "\n" +
                "Trân trọng,  \n" +
                "Công ty Tàu Việt";

        for (KhachHang customer : customers) {
            emailService.sendPromotionNotification(customer.getEmail(), content);
        }
    }
}