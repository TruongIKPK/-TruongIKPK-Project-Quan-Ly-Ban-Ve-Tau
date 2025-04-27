package gui.custom;

import enums.EColor;

import javax.swing.*;
import java.awt.*;

/**
 * @Dự án: tau-viet-express
 * @Class: CButton
 * @Tạo vào ngày: 3/10/2024
 * @Tác giả: Huy
 */
public class CButton extends JButton {
    public static String PRIMARY = "primary";
    public static String SECONDARY = "secondary";

    public CButton() {
        super();

        setMaximumSize(new Dimension(200, 40));
        setBackground(EColor.BTN_BG_COLOR.getColor());
        setForeground(EColor.BTN_TEXT_COLOR.getColor());
        setFont(new Font("Arial", Font.BOLD, 14));
    }

    public CButton(String text) {
        super(text);

        setMaximumSize(new Dimension(200, 40));
        setBackground(EColor.BTN_BG_COLOR.getColor());
        setForeground(EColor.BTN_TEXT_COLOR.getColor());
        setFont(new Font("Arial", Font.BOLD, 14));
    }

    public CButton(String text, String type) {
        super(text);

        setMaximumSize(new Dimension(200, 40));
        setBackground(type.equals(PRIMARY) ? EColor.BTN_BG_COLOR.getColor() : EColor.BG_COLOR_3.getColor());
        setForeground(type.equals(PRIMARY) ? EColor.BTN_TEXT_COLOR.getColor() : Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 14));
    }

    public CButton(String text, int width, int height) {
        super(text);

        setPreferredSize(new Dimension(width, height));
        setBackground(EColor.BTN_BG_COLOR.getColor());
        setForeground(EColor.BTN_TEXT_COLOR.getColor());
        setFont(new Font("Arial", Font.BOLD, 14));
    }

    public CButton(String text, String type, int width, int height) {
        super(text);

        setPreferredSize(new Dimension(width, height));
        setBackground(type.equals(PRIMARY) ? EColor.BTN_BG_COLOR.getColor() : EColor.BG_COLOR_3.getColor());
        setForeground(type.equals(PRIMARY) ? EColor.BTN_TEXT_COLOR.getColor() : Color.BLACK);
        setFont(new Font("Arial", Font.BOLD, 14));
    }

    @Override
    public void setEnabled(boolean b) {
        super.setEnabled(b);

        if (b) {
            setBackground(EColor.BTN_BG_COLOR.getColor());
            setForeground(EColor.BTN_TEXT_COLOR.getColor());
        } else {
            setBackground(EColor.DISABLED_COLOR.getColor());
            setForeground(Color.WHITE);
        }
    }
}
