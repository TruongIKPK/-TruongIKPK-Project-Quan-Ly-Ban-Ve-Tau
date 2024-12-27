package gui.components;

import enums.EColor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

/**
 * @Dự án: tau-viet-express
 * @Class: NgaySinhTextField
 * @Tạo vào ngày: 10/23/2024
 * @Tác giả: Huy
 */
public class NgaySinhTextField extends JFormattedTextField {
    public NgaySinhTextField() {
        MaskFormatter mask = null;
        try {
            mask = new MaskFormatter("##-##-####");
            mask.setPlaceholderCharacter('x');
        } catch (ParseException e) {
            e.printStackTrace();
        }

        setFormatterFactory(new DefaultFormatterFactory(mask));
        setBackground(EColor.BG_COLOR.getColor());

        // Set cỡ chữ 16
        setFont(new Font("Arial", Font.PLAIN, 14));

        // Border
        LineBorder lineBorder = new LineBorder(EColor.BORDER_COLOR.getColor());
        EmptyBorder paddingBorder = new EmptyBorder(5, 10, 5, 10);

        // Đặt phần đệm cho CTextField
        setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
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
