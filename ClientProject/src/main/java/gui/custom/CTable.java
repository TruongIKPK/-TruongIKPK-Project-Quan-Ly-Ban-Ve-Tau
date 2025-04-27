package gui.custom;

import enums.EColor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 * @Dự án: tau-viet-express
 * @Class: CTable
 * @Tạo vào ngày: 3/10/2024
 * @Tác giả: Huy
 */
public class CTable extends JTable {
    public CTable() {
        super();

        JTableHeader header = getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(EColor.BG_COLOR_2.getColor());
        header.setForeground(Color.BLACK);

        setRowHeight(30);
        setFont(new Font("Arial", Font.PLAIN, 14));

        // Set cho các cột tự tăng chiều dài theo nội dung
        setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    public CTable(DefaultTableModel model) {
        super(model);

        JTableHeader header = getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(EColor.BG_COLOR_2.getColor());
        header.setForeground(Color.BLACK);

        setRowHeight(30);
        setFont(new Font("Arial", Font.PLAIN, 14));

        setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }
}
