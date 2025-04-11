package gui;

import control.impl.DAOTaiKhoan;
import entity.TaiKhoan;
import org.mindrot.jbcrypt.BCrypt;
import utils.Validation;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;

public class DlgDoiMatKhau extends JDialog {
    private JPasswordField txtOldPassword;
    private JPasswordField txtNewPassword;
    private JPasswordField txtConfirmPassword;
    private JButton btnChangePassword;
    private JButton btnCancel;
    private TaiKhoan taiKhoan;
    private  DAOTaiKhoan daoTaiKhoan;

    public DlgDoiMatKhau(TaiKhoan taiKhoan) throws RemoteException {
        this.daoTaiKhoan = new DAOTaiKhoan();
        setTitle("Đổi Mật Khẩu");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.taiKhoan = taiKhoan;  // Lưu thông tin tài khoản được truyền vào
        initComponents();          // Khởi tạo giao diện
    }

    private void initComponents() {
        // Panel chính chứa các trường nhập liệu
        JPanel pnlMain = new JPanel(new GridLayout(4, 2, 10, 10));
        pnlMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Các thành phần giao diện
        JLabel lblOldPassword = new JLabel("Mật khẩu cũ:");
        txtOldPassword = new JPasswordField();
        JLabel lblNewPassword = new JLabel("Mật khẩu mới:");
        txtNewPassword = new JPasswordField();
        JLabel lblConfirmPassword = new JLabel("Xác nhận mật khẩu:");
        txtConfirmPassword = new JPasswordField();

        pnlMain.add(lblOldPassword);
        pnlMain.add(txtOldPassword);
        pnlMain.add(lblNewPassword);
        pnlMain.add(txtNewPassword);
        pnlMain.add(lblConfirmPassword);
        pnlMain.add(txtConfirmPassword);

        // Panel chứa các nút
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnChangePassword = new JButton("Đổi mật khẩu");
        btnCancel = new JButton("Hủy");
        pnlButtons.add(btnChangePassword);
        pnlButtons.add(btnCancel);

        add(pnlMain, BorderLayout.CENTER);
        add(pnlButtons, BorderLayout.SOUTH);

        // Thêm sự kiện cho các nút
        btnChangePassword.addActionListener(e -> {
            try {
                changePassword();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnCancel.addActionListener(e -> dispose());
    }

    private void changePassword() throws RemoteException {
        String oldPassword = new String(txtOldPassword.getPassword());
        String newPassword = new String(txtNewPassword.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());

        // Kiểm tra mật khẩu cũ có đúng không
        if (!verifyOldPassword(oldPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Mật khẩu cũ không đúng.",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Kiểm tra mật khẩu mới và xác nhận có khớp không
        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Mật khẩu mới và xác nhận mật khẩu không khớp.",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }



        // Cập nhật mật khẩu mới nếu hợp lệ
        if (updatePassword(newPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Đổi mật khẩu thành công.",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            dispose();  // Đóng dialog sau khi đổi mật khẩu thành công
        }
    }

    private boolean verifyOldPassword(String oldPassword) {
        // So sánh mật khẩu cũ với mật khẩu đã mã hóa
        return BCrypt.checkpw(oldPassword, taiKhoan.getMatKhauHash());
    }

    private boolean updatePassword(String newPassword) throws RemoteException {
        // Kiểm tra tính hợp lệ của mật khẩu mới
        if (!Validation.password(newPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Mật khẩu mới không hợp lệ.",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Kieemr tra mật khẩu mới có khác Abc1234. không?
        if (newPassword.equals("Abc1234.")) {
            JOptionPane.showMessageDialog(this,
                    "Mật khẩu mới không được trùng với mật khẩu mặc định.",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // set mật khẩu mới và cập nhật vào tài khoản
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        taiKhoan.setMatKhau(hashedPassword);

        // Cập nhật tài khoản trong cơ sở dữ liệu
        if (daoTaiKhoan.suaTaiKhoan(taiKhoan) != null) {
            System.out.println("Đổi mật khẩu thành công");
            return true;
        } else {
            System.out.println("Đổi mật khẩu thất bại");
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Tạo đối tượng tài khoản để thử nghiệm
            TaiKhoan tk = new TaiKhoan("Abc1234.", BCrypt.hashpw("Abc1234.", BCrypt.gensalt()));
            try {
                new DlgDoiMatKhau(tk).setVisible(true);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
