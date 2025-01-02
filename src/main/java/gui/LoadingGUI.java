package gui;

import enums.EColor;
import gui.custom.CImage;

import javax.swing.*;
import java.awt.*;

/**
 * @Dự án: tau-viet-express
 * @Class: Loading
 * @Tạo vào ngày: 10/15/2024
 * @Tác giả: Huy
 */
public class LoadingGUI {
    private int COUNT;

    // Giao diện này sẽ bao gồm một hình ảnh vè ga tàu và logo tên ga tàu, ở dưới là thanh loading chạy ngang
    public static final JWindow SPLASH_SCREEN;
    private static JProgressBar progressBar;

    static {
        SPLASH_SCREEN = new JWindow();
    }

    public static void main(String[] args) {
        show();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        close();
    }
    public static void show() {
        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new BorderLayout());
        pnlMain.setBackground(EColor.BG_COLOR.getColor());

        // Tạo hình ảnh ga tàu
        JLabel lblGaTau = new JLabel();
        lblGaTau.setIcon(new CImage("images/background.jpg", 800, 400));
        pnlMain.add(lblGaTau, BorderLayout.CENTER);

        // Tạo logo tên ga tàu
        JLabel lblLogo = new JLabel();
        lblLogo.setIcon(new CImage("images/icons/train.png", 300, 50));
//        pnlMain.add(lblLogo, BorderLayout.NORTH);

        // Thêm chữ đè lên image
        JLabel lblText = new JLabel("Tàu Việt Express");
        lblText.setFont(new Font("Arial", Font.BOLD, 40));
        lblText.setForeground(Color.WHITE);
        lblText.setHorizontalAlignment(SwingConstants.CENTER);
        lblText.setVerticalAlignment(SwingConstants.CENTER);
        lblText.setBounds(0, 0, 800, 400);
        lblGaTau.add(lblText);

        // Cho một background mờ ở dưới chữ đè lên
        JPanel pnlBg = new JPanel();
        pnlBg.setBackground(new Color(50, 130, 246, 50));
        pnlBg.setBounds(0, 0, 800, 400);
        lblGaTau.add(pnlBg);

        // Tạo thanh loading
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        pnlMain.add(progressBar, BorderLayout.SOUTH);

        // Đếm 1s
        new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(10);
                    progressBar.setValue(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        SPLASH_SCREEN.add(pnlMain);
        SPLASH_SCREEN.setAlwaysOnTop(true);
        SPLASH_SCREEN.setSize(800, 400);
        SPLASH_SCREEN.setLocationRelativeTo(null);
        SPLASH_SCREEN.setVisible(true);
        SPLASH_SCREEN.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }

    public static void close() {
        SPLASH_SCREEN.setVisible(false);
        SPLASH_SCREEN.dispose();
    }
}
