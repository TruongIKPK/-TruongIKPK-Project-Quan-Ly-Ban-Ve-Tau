package gui;

import gui.custom.CImage;

import javax.swing.*;
import java.awt.*;

public class AboutDialog extends JDialog {
    public AboutDialog(JFrame parent) {
        super(parent, "About Tàu Việt Express", true);

        // Thiết lập bố cục
        setLayout(new BorderLayout());
        setSize(700, 500); // Điều chỉnh kích thước tổng thể của dialog
        setLocationRelativeTo(parent);

        // Phần hiển thị icon (bên trái)
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(new CImage("images/icons/logo.png")); // Thay đường dẫn bằng icon của bạn
        // set size cho phu hop
        iconLabel.setPreferredSize(new Dimension(90, 90));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel iconPanel = new JPanel(new BorderLayout());
        iconPanel.add(iconLabel, BorderLayout.CENTER);
        iconPanel.setPreferredSize(new Dimension(100, 0)); // Chiếm 2/10 chiều rộng
        //tao padding left cho icon
        iconPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        // Phần hiển thị thông tin (bên phải) với HTML
        String aboutText = "<html>"
                + "<h2>Tàu Việt Express</h2>"
                + "<p><strong>Phiên bản:</strong> 1.0.0</p>"
                + "<p><strong>Build:</strong> #TVE-2024.001, hoàn thành ngày 10/12/2024</p>"
                + "<p><strong>Ứng dụng cấp phép cho mục đích thương mại.</strong></p>"
                + "<p><strong>Runtime:</strong> Java 17, Swing</p>"
                + "<p><strong>Copyright</strong> © 2024 Tàu Việt Express. All rights reserved.</p>"
                + "<hr>"
                + "<h3>Thông tin ứng dụng</h3>"
                + "<p>Tàu Việt Express hỗ trợ quản lý vé tàu, từ bán vé đến hoàn vé, giúp tối ưu hóa quy trình vận hành tàu với giao diện dễ sử dụng.</p>"
                + "<h3>Nhóm phát triển</h3>"
                + "<ul>"
                + "<li>Lê Nguyễn Phi Trường</li>"
                + "<li>Ngô Nhựt Huy</li>"
                + "<li>Nguyễn Hữu Sang</li>"
                + "<li>Vũ Quốc Thái</li>"
                + "</ul>"
                + "<h3>Lời cảm ơn</h3>"
                + "<p>Cảm ơn các thầy cô và các bạn đồng môn đã hỗ trợ trong quá trình phát triển. Mọi góp ý xin liên hệ:</p>"
                + "<p><strong>Email:</strong> tauvietexpress@gmail.com</p>"
                + "<p><strong>SĐT:</strong> 0377 180 010</p>"
                + "<p>Chúc bạn trải nghiệm tuyệt vời với Tàu Việt Express!</p>"
                + "</html>";

        JEditorPane aboutArea = new JEditorPane("text/html", aboutText);
        aboutArea.setEditable(false);
        aboutArea.setBackground(getBackground());
        aboutArea.setFont(new Font("Arial", Font.PLAIN, 13));

        JScrollPane scrollPane = new JScrollPane(aboutArea);
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.add(scrollPane, BorderLayout.CENTER);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Nút đóng
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(closeButton);

        // Tổ chức các phần vào dialog
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(iconPanel, BorderLayout.WEST);
        mainPanel.add(infoPanel, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(false); // Ẩn frame chính

        AboutDialog aboutDialog = new AboutDialog(frame);
        aboutDialog.setVisible(true);
    }
}
