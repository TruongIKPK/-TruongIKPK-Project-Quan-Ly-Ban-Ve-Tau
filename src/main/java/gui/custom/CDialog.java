package gui.custom;

import entity.ChoNgoi;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @Dự án: tau-viet-express
 * @Class: CDialog
 * @Tạo vào ngày: 10/12/2024
 * @Tác giả: Huy
 */
public class CDialog extends JDialog {
    public CDialog() {
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setModal(true);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
