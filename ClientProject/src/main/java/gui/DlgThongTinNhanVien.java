package gui;

import entity.NhanVien;
import javax.swing.*;
import java.awt.*;

/**
 * @Dự án: tau-viet-express
 * @Class: DlgThongTinNhanVien
 * @Tạo vào ngày: 12/10/2024
 * @Tác giả: Thai
 */
public class DlgThongTinNhanVien extends JDialog {

    // Constructor
    public DlgThongTinNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
        initComponents();
        fillData();
    }

    private void fillData() {
        txtMaNV.setText(nhanVien.getMaNV());
        txtTenNV.setText(nhanVien.getTenNV());
        txtGioiTinh.setText(nhanVien.getGioiTinh());
        txtNgaySinh.setText(nhanVien.getNgaySinh().toString());
        txtSDT.setText(nhanVien.getSdt());
        txtEmail.setText(nhanVien.getEmail());
        txtDiaChi.setText(nhanVien.getDiaChi());
        txtCCCD.setText(nhanVien.getCCCD());
        txtNgayVaoLam.setText(nhanVien.getNgayVaoLam().toString());
        txtChucVu.setText(nhanVien.getChucVu().getTenCV());
        txtTrangThai.setText(nhanVien.getTrangThai());
        txtCaLam.setText(nhanVien.getCaLam().getMaCL());
    }

    private void initComponents() {
        // Set dialog properties
        setTitle("Thông tin nhân viên");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 500);
        setResizable(false);
        setLocationRelativeTo(null);

        // Create panel for displaying employee information
        JPanel panel = new JPanel(new GridLayout(12, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add labels and text fields for employee information
        panel.add(new JLabel("Mã NV:"));
        txtMaNV = createTextField();
        panel.add(txtMaNV);

        panel.add(new JLabel("Tên NV:"));
        txtTenNV = createTextField();
        panel.add(txtTenNV);

        panel.add(new JLabel("Giới tính:"));
        txtGioiTinh = createTextField();
        panel.add(txtGioiTinh);

        panel.add(new JLabel("Ngày sinh:"));
        txtNgaySinh = createTextField();
        panel.add(txtNgaySinh);

        panel.add(new JLabel("SĐT:"));
        txtSDT = createTextField();
        panel.add(txtSDT);

        panel.add(new JLabel("Email:"));
        txtEmail = createTextField();
        panel.add(txtEmail);

        panel.add(new JLabel("Địa chỉ:"));
        txtDiaChi = createTextField();
        panel.add(txtDiaChi);

        panel.add(new JLabel("CCCD:"));
        txtCCCD = createTextField();
        panel.add(txtCCCD);

        panel.add(new JLabel("Ngày vào làm:"));
        txtNgayVaoLam = createTextField();
        panel.add(txtNgayVaoLam);

        panel.add(new JLabel("Chức vụ:"));
        txtChucVu = createTextField();
        panel.add(txtChucVu);

        panel.add(new JLabel("Trạng thái:"));
        txtTrangThai = createTextField();
        panel.add(txtTrangThai);

        panel.add(new JLabel("Ca làm:"));
        txtCaLam = createTextField();
        panel.add(txtCaLam);

        // Add panel to dialog
        getContentPane().add(panel, BorderLayout.CENTER);

        // Add close button
        JButton btnClose = new JButton("Đóng");
        btnClose.addActionListener(e -> dispose());
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnClose);
        getContentPane().add(btnPanel, BorderLayout.SOUTH);
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setEditable(false);
        return textField;
    }

    private NhanVien nhanVien;
    private JTextField txtMaNV;
    private JTextField txtTenNV;
    private JTextField txtGioiTinh;
    private JTextField txtNgaySinh;
    private JTextField txtSDT;
    private JTextField txtEmail;
    private JTextField txtDiaChi;
    private JTextField txtCCCD;
    private JTextField txtNgayVaoLam;
    private JTextField txtChucVu;
    private JTextField txtTrangThai;
    private JTextField txtCaLam;


}
