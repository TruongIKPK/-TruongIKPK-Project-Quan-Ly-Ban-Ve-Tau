package gui.custom;

import enums.EColor;

import javax.swing.*;
import java.awt.*;

/**
 * @Dự án: tau-viet-express
 * @Class: CMenuBar
 * @Tạo vào ngày: 3/10/2024
 * @Tác giả: Huy
 */
public class CMenuBar extends JMenuBar {
    public CMenuBar() {
        super();
        this.setBackground(EColor.BG_COLOR_3.getColor());
        this.setPreferredSize(new Dimension(0, 35));
    }
}
