package gui.custom;

import enums.EColor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * @Dự án: tau-viet-express
 * @Class: CMenu
 * @Tạo vào ngày: 3/10/2024
 * @Tác giả: Huy
 */
public class CMenu extends JMenu {
    public CMenu(String text) {
        super(text);

        this.setFont(new Font("Arial", Font.BOLD, 12));
        this.setForeground(Color.BLACK);

        // Set border với lineborder và empty border
        LineBorder lineBorder = new LineBorder(EColor.BORDER_COLOR.getColor(), 1);
        EmptyBorder emptyBorder = new EmptyBorder(5, 10, 5, 10);
        this.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
    }
}
