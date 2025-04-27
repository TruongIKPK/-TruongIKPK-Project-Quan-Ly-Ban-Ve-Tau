package gui.components;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

/**
 * @Dự án: tau-viet-express
 * @Class: TimeField
 * @Tạo vào ngày: 10/19/2024
 * @Tác giả: Huy
 */
public class TimeField extends JFormattedTextField {
    public TimeField() {
        MaskFormatter mask = null;
        try {
            mask = new MaskFormatter("##:##");
            mask.setPlaceholderCharacter('x');
        } catch (ParseException e) {
            e.printStackTrace();
        }

        setFormatterFactory(new DefaultFormatterFactory(mask));
    }
}
