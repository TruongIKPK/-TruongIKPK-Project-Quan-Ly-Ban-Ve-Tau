package gui.custom;

import enums.EColor;

import javax.swing.*;
import java.awt.*;

/**
 * @Dự án: tau-viet-express
 * @Class: CMenuItem
 * @Tạo vào ngày: 3/10/2024
 * @Tác giả: Huy
 */
public class CMenuItem extends JMenuItem {
    public CMenuItem(String text) {
        super(text);
        this.setFont(new Font("Arial", Font.PLAIN, 12));
        this.setPreferredSize(new Dimension(150, 25));
    }
}
