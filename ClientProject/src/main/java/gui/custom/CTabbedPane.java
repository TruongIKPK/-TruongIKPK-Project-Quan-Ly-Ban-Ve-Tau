package gui.custom;

import enums.EColor;

import javax.swing.*;
import java.awt.*;

/**
 * @Dự án: tau-viet-express
 * @Class: CTabbedPane
 * @Tạo vào ngày: 3/10/2024
 * @Tác giả: Huy
 */
public class CTabbedPane extends JTabbedPane {
    public CTabbedPane() {
        setFont(new Font("Arial", Font.BOLD, 16));
        setBackground(EColor.BG_COLOR.getDecodeColor());
        setForeground(EColor.TEXT_COLOR.getDecodeColor());

    }

    public CTabbedPane(String title, Component c) {
        this();
        this.setBackground(EColor.BG_COLOR_2.getColor());
        this.setFont(new Font("Arial", Font.BOLD, 12));
        this.setBackground(EColor.TAB_BG_COLOR.getColor());
        this.setForeground(EColor.TEXT_COLOR.getColor());
        this.setBorder(null);
        this.addTab(title, c);
    }

}
