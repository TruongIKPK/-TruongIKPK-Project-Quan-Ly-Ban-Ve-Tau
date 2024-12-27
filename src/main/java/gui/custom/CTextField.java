package gui.custom;

import enums.EColor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * @Dự án: tau-viet-express
 * @Class: CTextField
 * @Tạo vào ngày: 3/10/2024
 * @Tác giả: Huy
 */
public class CTextField extends JTextField {
    // Tạo ra một text field đẹp mắt
    public CTextField() {
        super();

        // Set màu nền
        this.setBackground(EColor.BG_COLOR.getColor());

        // Set cỡ chữ 16
        this.setFont(new Font("Arial", Font.PLAIN, 14));

        // Border
        LineBorder lineBorder = new LineBorder(EColor.BORDER_COLOR.getColor());
        EmptyBorder paddingBorder = new EmptyBorder(5, 10, 5, 10);

        // Đặt phần đệm cho CTextField
        this.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
    }

    @Override
    public void setEditable(boolean b) {
        super.setEditable(b);

        // Kiểm tra xem text field có thể chỉnh sửa hay không
        if (b) {
            this.setBackground(EColor.BG_COLOR.getColor());
        } else {
            this.setBackground(EColor.DISABLED_COLOR.getColor());
        }
    }
}
