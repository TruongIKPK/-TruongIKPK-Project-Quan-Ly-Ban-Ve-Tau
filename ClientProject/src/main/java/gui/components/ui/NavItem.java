package gui.components.ui;

import gui.custom.CImage;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * @Dự án: tau-viet-express
 * @Class: NavItem
 * @Tạo vào ngày: 3/10/2024
 * @Tác giả: Huy
 */
public class NavItem extends JPanel {
    private JPanel pnlNavigate;

    // Constructor chung cho tất cả các trường hợp
    public NavItem(JPanel pnlNavigate, String icon, String title, boolean hasBorder) {
        this.pnlNavigate = pnlNavigate;

        // Tạo icon
        JLabel image = createLabelWithIcon(icon);

        // Thiết lập border nếu cần
        if (hasBorder) {
            setBorder(new LineBorder(Color.BLACK));
        }

        // Cấu hình layout
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(100, 80));
        add(image, BorderLayout.CENTER);

        // Tạo title nếu có
        if (title != null && !title.isEmpty()) {
            JLabel label = new JLabel(title, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 16));
            add(label, BorderLayout.SOUTH);
        }
    }

    // Các constructor khác sẽ gọi constructor chính với các giá trị mặc định
    public NavItem(JPanel pnlNavigate, String icon) {
        this(pnlNavigate, icon, null, false);
    }

    public NavItem(JPanel pnlNavigate, String icon, boolean hasBorder) {
        this(pnlNavigate, icon, null, hasBorder);
    }

    public NavItem(JPanel pnlNavigate, String icon, String title) {
        this(pnlNavigate, icon, title, false);
    }

    // Tạo label có chứa icon
    private JLabel createLabelWithIcon(String iconPath) {
        JLabel label = new JLabel(new CImage(iconPath, 40, 40));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    public JPanel getPnlNavigate() {
        return pnlNavigate;
    }
}
