package gui;

import control.DAONhanVien;
import control.DAOTaiKhoan;
import entity.NhanVien;
import entity.TaiKhoan;
import enums.EColor;
import gui.custom.CImage;
import utils.EmailService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class FrmDangNhap extends JFrame {
    private JPasswordField txtPass;
    private JComboBox<String> cmbMaTK;
    private JLabel lblBg;
    private AbstractButton btnDangNhap;
    private final String LOGIN_FILE = "/last_logins.txt"; // File lưu tài khoản

    public FrmDangNhap() {
        setTitle("Đăng nhập");
        // Sử dụng JLayeredPane để đặt nền và panel đăng nhập
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);

        // Thiết lập ảnh nền cho background
        ImageIcon bg = new CImage("images/bgDangNhap.jpg", 1920, 1080);
        lblBg = new JLabel(bg);
        lblBg.setBounds(0, 0, 1920, 1080); // Kích thước ảnh nền
        layeredPane.add(lblBg, Integer.valueOf(0));

        // Tạo panel đăng nhập và cấu hình
        JPanel pnlDangNhap = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
                g2d.setColor(getBackground());
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
            }
        };
        pnlDangNhap.setOpaque(false);
        pnlDangNhap.setBackground(new Color(110, 112, 114)); // Màu nền trắng nhạt panel
        pnlDangNhap.setBounds(100, 150, 400, 400); // Kích thước và vị trí ban đầu

        // Sử dụng GridBagConstraints để bố trí các thành phần
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Label Đăng nhập
        JLabel lblDangNhap = new JLabel("Đăng nhập", SwingConstants.CENTER);
        lblDangNhap.setFont(new Font("Arial", Font.BOLD, 40));
        lblDangNhap.setForeground(EColor.TITLE_COLOR.getColor());
        pnlDangNhap.add(lblDangNhap, gbc);

        // Khoảng cách giữa các thành phần
        gbc.gridy++;
        JPanel spacer2 = new JPanel();
        spacer2.setPreferredSize(new Dimension(1, 20)); // Adjust the height as needed
        spacer2.setOpaque(false);
        pnlDangNhap.add(spacer2, gbc);

        // Label Tên tài khoản
        gbc.gridy++;
        JLabel lblMaTK = new JLabel("Tên tài khoản:");
        lblMaTK.setFont(new Font("Arial", Font.PLAIN, 16));
        lblMaTK.setForeground(Color.WHITE);
        pnlDangNhap.add(lblMaTK, gbc);

        // Field tên tài khoản
        gbc.gridy++;
        cmbMaTK = new JComboBox<>() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(super.getPreferredSize().width, 35);
            }
        };
        // Đọc các tài khoản đã đăng nhập từ file
        ArrayList<String> logins = loadLastLogins();
        for (String login : logins) {
            cmbMaTK.addItem(login);
        }
        cmbMaTK.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        cmbMaTK.setEditable(true);
        cmbMaTK.setForeground(Color.WHITE);
        cmbMaTK.setFont(new Font("Arial", Font.PLAIN, 16));
        pnlDangNhap.add(cmbMaTK, gbc);

        // Khoảng cách giữa các thành phần
        gbc.gridy++;
        JPanel spacer1 = new JPanel();
        spacer1.setPreferredSize(new Dimension(1, 10)); // Adjust the height as needed
        spacer1.setOpaque(false);
        pnlDangNhap.add(spacer1, gbc);

        // Label Mật khẩu
        gbc.gridy++;
        JLabel lblMatKhau = new JLabel("Mật khẩu:");
        lblMatKhau.setFont(new Font("Arial", Font.PLAIN, 16));
        lblMatKhau.setForeground(Color.WHITE);
        pnlDangNhap.add(lblMatKhau, gbc);

        // Field mật khẩu
        gbc.gridy++;
        txtPass = new JPasswordField() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(super.getPreferredSize().width, 30);
            }

            @Override
            public Dimension getMinimumSize() {
                return getPreferredSize();
            }

            @Override
            public Dimension getMaximumSize() {
                return getPreferredSize();
            }
        };
        txtPass.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        txtPass.setFont(new Font("Arial", Font.PLAIN, 16));
        txtPass.addActionListener(e -> btnDangNhap.doClick());
        pnlDangNhap.add(txtPass, gbc);

        // Hiện mật khẩu
        gbc.gridy++;
        JCheckBox cbShowPass = new JCheckBox("Hiện mật khẩu");
        cbShowPass.setFont(new Font("Arial", Font.ITALIC, 14));
        cbShowPass.setForeground(Color.WHITE);
        cbShowPass.setOpaque(false);
        cbShowPass.addActionListener(e -> {
            txtPass.setEchoChar(cbShowPass.isSelected() ? '\u0000' : '\u2022');
        });
        pnlDangNhap.add(cbShowPass, gbc);

        // Quên mật khẩu
        gbc.gridy++;
        JLabel lblForgotPassword = new JLabel("Quên mật khẩu");
        lblForgotPassword.setFont(new Font("Arial", Font.BOLD, 16));
        lblForgotPassword.setForeground(Color.WHITE);
        lblForgotPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        lblForgotPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String maTK = cmbMaTK.getSelectedItem().toString();
                if (maTK.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập tên tài khoản!");
                } else {
                    handleForgotPassword(maTK);
                }
            }
        });
        pnlDangNhap.add(lblForgotPassword, gbc);

        gbc.gridy++;
        btnDangNhap = new JButton("Đăng nhập");
        btnDangNhap.setFont(new Font("Arial", Font.PLAIN, 20));
        btnDangNhap.setBackground(EColor.BTN_BG_COLOR.getColor());
        btnDangNhap.setForeground(Color.WHITE);
        btnDangNhap.addActionListener(e -> {
            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    String maTK = cmbMaTK.getSelectedItem().toString();
                    String matKhau = new String(txtPass.getPassword());

                    TaiKhoan tk = DAOTaiKhoan.login(maTK, matKhau);
                    if (tk != null) {
                        NhanVien nv = DAONhanVien.getNhanVien(tk.getMaTK());
                        if (nv != null) {
                            // Đăng nhập thành công
                            SwingUtilities.invokeLater(() -> {
                                saveLogin(maTK); // Ghi tài khoản vào file
                                dispose();
                                FrmTrangChinh frmTrangChinh = new FrmTrangChinh(tk, nv);
                                frmTrangChinh.setVisible(true);
                            });
                        } else {
                            JOptionPane.showMessageDialog(null, "Nhân viên không tồn tại");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Đăng nhập thất bại");
                    }
                    return null;
                }
            }.execute();
        });


        pnlDangNhap.add(btnDangNhap, gbc);

        layeredPane.add(pnlDangNhap, Integer.valueOf(1));

        // Thêm JLayeredPane vào JFrame
        add(layeredPane);

        // Thiết lập kích thước và vị trí JFrame
        ImageIcon icoLogo = new CImage("images/icons/logo.png", 48, 48);
        setIconImage(icoLogo.getImage());
        setSize(800, 600); // Kích thước cố định
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Mở rộng cửa sổ
        setLocationRelativeTo(null); // Căn giữa cửa sổ

        // Đảm bảo hình nền luôn chiếm toàn bộ không gian
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                lblBg.setSize(getWidth(), getHeight());
                pnlDangNhap.setLocation((getWidth() - pnlDangNhap.getWidth()) / 2,
                        (getHeight() - pnlDangNhap.getHeight()) / 2); // Căn giữa panel
            }
        });

        // Confirm khi thoát chương trình
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn thoát chương trình?", "Thoát chương trình",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    // Số lượng tài khoản tối đa được lưu
    private final int MAX_LOGINS = 10;

    // Phương thức ghi tài khoản vào file (giới hạn số lượng tài khoản)
    private void saveLogin(String maTK) {
        ArrayList<String> logins = loadLastLogins(); // Đọc danh sách hiện tại

        // Xóa tài khoản nếu đã tồn tại (để tránh trùng lặp)
        logins.remove(maTK);

        // Thêm tài khoản mới vào đầu danh sách
        logins.add(0, maTK);

        // Giữ lại tối đa MAX_LOGINS tài khoản
        if (logins.size() > MAX_LOGINS) {
            logins.remove(logins.size() - 1); // Xóa phần tử cuối cùng

        }

        // Ghi danh sách đã cập nhật vào file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOGIN_FILE))) {
            for (String login : logins) {
                writer.write(login);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Phương thức đọc tài khoản từ file
    private ArrayList<String> loadLastLogins() {
        ArrayList<String> logins = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(LOGIN_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!logins.contains(line)) {
                    logins.add(line); // Đảm bảo không trùng lặp
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logins;
    }

    private void handleForgotPassword(String maTK) {
        // Generate a 6-digit OTP
        int otp = (int) (Math.random() * 900000) + 100000;



        // Get the user's email or phone number from the database
        TaiKhoan tk = DAOTaiKhoan.getTaiKhoanTheoMa(maTK);
        if (tk == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy tài khoản này.");
            return;
        }

        // Ask the user to choose between email or phone
        JDialog dialog = new JDialog(this, "Chọn phương thức liên hệ", true);
        dialog.setLayout(new FlowLayout());
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this);

        // Email button action
        JButton btnEmail = new JButton("Gửi mã qua Email");
        btnEmail.addActionListener(e -> {
            String exampleEmail = DAONhanVien.getNhanVien(tk.getMaTK()).getEmail();
            if (exampleEmail != null) {
                // Obfuscate email (show only the first 4 characters)
                String emailHide = exampleEmail.substring(0, 4) + "******";

                // Send OTP via Email
                EmailService emailService = new EmailService();
                boolean emailSent = emailService.sendOTPEmail(exampleEmail, otp);
                if (emailSent) {
                    JOptionPane.showMessageDialog(this, "Liên kết đặt lại mật khẩu đã được gửi vào " + emailHide);
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể gửi liên kết đặt lại mật khẩu. Vui lòng thử lại sau.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Email không hợp lệ.");
            }
            dialog.dispose(); // Close the dialog
            showOTPDialog(maTK, otp);  // Show the dialog for entering OTP and new password
        });
        dialog.add(btnEmail);

        // SMS button action (this part remains as is for future SMS functionality)
        JButton btnSMS = new JButton("Gửi mã vào SĐT");
        btnSMS.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Chức năng gửi mã qua SĐT chưa được triển khai.");
            dialog.dispose(); // Close the dialog
        });
        dialog.add(btnSMS);

        dialog.setVisible(true); // Display the dialog
    }

    private void showOTPDialog(String maTK, int otpSent) {
        JDialog otpDialog = new JDialog(this, "Nhập mã OTP và mật khẩu mới", true);
        otpDialog.setSize(400, 300);
        otpDialog.setLocationRelativeTo(this);
        otpDialog.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margin
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Label and TextField for OTP
        JLabel lblOTP = new JLabel("Nhập mã OTP:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        otpDialog.add(lblOTP, gbc);

        JTextField txtOTP = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        otpDialog.add(txtOTP, gbc);

        // Label and TextField for New Password
        JLabel lblNewPassword = new JLabel("Mật khẩu mới:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        otpDialog.add(lblNewPassword, gbc);

        JPasswordField txtNewPassword = new JPasswordField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        otpDialog.add(txtNewPassword, gbc);

        // Label and TextField for Confirm Password
        JLabel lblConfirmPassword = new JLabel("Xác nhận mật khẩu:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        otpDialog.add(lblConfirmPassword, gbc);

        JPasswordField txtConfirmPassword = new JPasswordField(10);
        gbc.gridx = 1;
        gbc.gridy = 2;
        otpDialog.add(txtConfirmPassword, gbc);

        // Countdown timer label
        JLabel lblTimer = new JLabel("Thời gian còn lại: 60 giây");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        otpDialog.add(lblTimer, gbc);

        // Submit button
        JButton btnSubmit = new JButton("Xác nhận");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        otpDialog.add(btnSubmit, gbc);

        // Timer to disable fields after 1 minute and update countdown label
        // Timer to disable OTP field after 1 minute and update countdown label
        Timer timer = new Timer(1000, new ActionListener() {
            int timeRemaining = 60;

            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                lblTimer.setText("Thời gian còn lại: " + timeRemaining + " giây");

                if (timeRemaining <= 0) {
                    txtOTP.setEnabled(false); // Disable OTP input
                    lblTimer.setText("Mã OTP đã hết hạn."); // Update message to notify user
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.setRepeats(true);
        timer.start();


        // Button ActionListener
        btnSubmit.addActionListener(e -> {
            String inputOTP = txtOTP.getText();
            String newPassword = new String(txtNewPassword.getPassword());
            String confirmPassword = new String(txtConfirmPassword.getPassword());

            // Check if OTP is enabled and validate it
            if (txtOTP.isEnabled() && !inputOTP.equals(String.valueOf(otpSent))) {
                JOptionPane.showMessageDialog(otpDialog, "Mã OTP không chính xác.");
                return;
            }

            // Check if passwords match
            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(otpDialog, "Mật khẩu xác nhận không khớp.");
                return;
            }

            // Update password in the database
            TaiKhoan tk = DAOTaiKhoan.getTaiKhoanTheoMa(maTK);
            tk.setMatKhau(newPassword);
            TaiKhoan tkNew = DAOTaiKhoan.suaTaiKhoan(tk);

            if (tkNew != null) {
                JOptionPane.showMessageDialog(otpDialog, "Mật khẩu đã được thay đổi thành công.");
            } else {
                JOptionPane.showMessageDialog(otpDialog, "Không thể thay đổi mật khẩu. Vui lòng thử lại.");
            }

            timer.stop(); // Stop the timer if the process is completed within the time limit
            otpDialog.dispose(); // Close dialog
        });

        otpDialog.setVisible(true); // Show dialog
    }


}






