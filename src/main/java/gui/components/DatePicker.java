package gui.components;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import utils.DateLabelFormatter;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

/**
 * @Dự án: tau-viet-express
 * @Class: DatePicker
 * @Tạo vào ngày: 10/15/2024
 * @Tác giả: Huy
 */
public class DatePicker {
    public static JDatePickerImpl getDatePicker() {
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

        return new JDatePickerImpl(datePanel, new DateLabelFormatter());
    }
}
