/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import control.DAOKhuyenMai;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;
import enums.EChucVu;
import enums.EColor;
import gui.components.DatePicker;
import gui.custom.CButton;
import gui.custom.CTable;
import gui.custom.CTextField;
import gui.custom.CImage;
import org.jdatepicker.impl.JDatePickerImpl;
import utils.EmailService;
import utils.FormatDate;
import utils.PromotionNotifier;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PnlKhuyenMai extends JPanel {
    private ArrayList<String> listDoiTuong;
    private ArrayList<KhuyenMai> listKhuyenMai;
    private JButton btnReset;
    private NhanVien nhanVien;
    private PromotionNotifier promotionNotifier;

    /**
     * Creates new form PnlChuyenTau
     */
    public PnlKhuyenMai(NhanVien nhanVien) throws RemoteException {
        this.nhanVien = nhanVien;
        setBackground(EColor.BG_COLOR.getColor());
        initComponents();
        readDataFromDb();
        filterAction();
        // Initialize and start the PromotionNotifier
        promotionNotifier = new PromotionNotifier();
        promotionNotifier.schedulePromotionNotifications();
    }

    private void filterAction() {
        filterAction = () -> {
            System.out.println("Filter action");
            List<RowFilter<Object, Object>> filters = new ArrayList<>();

            // Lọc theo đối tượng
            String doiTuong = cboLocDoiTuong.getSelectedItem().toString();
            if (!doiTuong.equals("Không")) {
                filters.add(RowFilter.regexFilter(doiTuong, 3));
            }

            // Lọc theo trạng thái
            String trangThai = cboLocTrangThai.getSelectedItem().toString();
            if (!trangThai.equals("Không")) {
                filters.add(RowFilter.regexFilter(trangThai, 5));
            }

            // Lọc theo mã
            String maKM = txtTimMa.getText();
            if (!maKM.isEmpty()) {
                filters.add(RowFilter.regexFilter(".*" + maKM + ".*", 0));
            }

            RowFilter<Object, Object> combinedFilter = filters.isEmpty() ? null : RowFilter.andFilter(filters);
            tableSorter.setRowFilter(combinedFilter);
        };
    }

    private void readDataFromDb() {
        listDoiTuong = DAOKhuyenMai.getDSDoiTuongKhuyenMai();

        // Đổ dữ liệu đối tượng vào cbo box
        listDoiTuong.forEach((item) -> {
            cboDoiTuong.addItem(item);
            cboLocDoiTuong.addItem(item);
        });
        // Đổ dữ liệu khuyến mãi vào bảng
        updateModel();
    }

    private boolean validateInput() {
        String ngayAD = txtNgayAD.getJFormattedTextField().getText();
        String ngayKT = txtNgayKT.getJFormattedTextField().getText();
        String doiTuong = cboDoiTuong.getSelectedItem().toString();
        String phanTram = txtPhanTram.getText();

        if (ngayAD.isEmpty() || ngayKT.isEmpty() || doiTuong.isEmpty() || phanTram.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        LocalDate ngayADLocal = FormatDate.formatStrToLocalDate(ngayAD);
        LocalDate ngayKTLocal = FormatDate.formatStrToLocalDate(ngayKT);

        // Ngày áp dụng trước ngày kết thúc
        if (ngayADLocal.isAfter(ngayKTLocal)) {
            JOptionPane.showMessageDialog(this, "Ngày áp dụng phải trước ngày kết thúc", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra định dạng phần trăm
        try {
            int phanTramInt = Integer.parseInt(phanTram);
            if (phanTramInt < 0 || phanTramInt > 100) {
                JOptionPane.showMessageDialog(this, "Phần trăm phải nằm trong khoảng 0 - 100", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Phần trăm phải là số", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void initComponents() {
        pnlTop = new JPanel();
        lblTitle = new JLabel();
        pnlLeft = new JPanel();
        pnlLeftTop = new JPanel();
        boxNgayAD = new JPanel();
        lblNgayAD = new JLabel();
        txtNgayAD = DatePicker.getDatePicker();
        filler1 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        boxNgayKT = new JPanel();
        lblNgayKT = new JLabel();
        txtNgayKT = DatePicker.getDatePicker();
        filler2 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        boxDoiTuong = new JPanel();
        lblDoiTuong = new JLabel();
        cboDoiTuong = new JComboBox<>();
        filler5 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        boxPhanTram = new JPanel();
        lblPhanTram = new JLabel();
        txtPhanTram = new JFormattedTextField();
        filler7 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        filler4 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        lblTrangThai = new JLabel();
        txtTrangThai = new CTextField();
        boxChucNang = new JPanel();
        btnThem = new CButton("Thêm", CButton.PRIMARY);
        btnCapNhat = new CButton("Cập nhật", CButton.SECONDARY);
        lblLocTheoDoiTuong = new JLabel();
        cboLocDoiTuong = new JComboBox<>();
        cboLocDoiTuong.addItem("Không");
        lblLocTheoTrangThai = new JLabel();
        cboLocTrangThai = new JComboBox<>();
        cboLocTrangThai.addItem("Không");
        cboLocTrangThai.addItem("Đang áp dụng");
        cboLocTrangThai.addItem("Hết hạn");
        pnlRight = new JPanel();
        jScrollPane1 = new JScrollPane();
        Object[] columnsName = new Object[]{
                "Mã khuyến mãi", "Ngày áp dụng", "Ngày kết thúc", "Đối tượng", "Phần trăm", "Trạng thái"
        };
        tblModelKhuyenMai = new DefaultTableModel(columnsName, 0);
        tblKhuyenMai = new CTable(tblModelKhuyenMai);
        tblKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                super.mouseClicked(evt);

                int selectedRow = tblKhuyenMai.getSelectedRow();
                if (selectedRow == -1) return;

                String maKM = tblKhuyenMai.getValueAt(selectedRow, 0).toString();
                String ngayAD = tblKhuyenMai.getValueAt(selectedRow, 1).toString();
                String ngayKT = tblKhuyenMai.getValueAt(selectedRow, 2).toString();
                String doiTuong = tblKhuyenMai.getValueAt(selectedRow, 3).toString();
                String trangThai = tblKhuyenMai.getValueAt(selectedRow, 5).toString();

                // loai bo phan tram phia sau
                String phanTram = tblKhuyenMai.getValueAt(selectedRow, 4).toString().replace("%", "");

                txtNgayAD.getJFormattedTextField().setText(ngayAD);
                txtNgayKT.getJFormattedTextField().setText(ngayKT);
                cboDoiTuong.setSelectedItem(doiTuong);
                txtPhanTram.setText(phanTram);
                txtTrangThai.setText(trangThai);
            }
        });
        tableSorter = new TableRowSorter<>(tblModelKhuyenMai);

        // Sắp xếp theo phần trăm
        tableSorter.setComparator(4, (o1, o2) -> {
            String s1 = o1.toString().replace("%", "");
            String s2 = o2.toString().replace("%", "");
            return Double.compare(Double.parseDouble(s1), Double.parseDouble(s2));
        });

        // Sắp xếp theo ngày bắt đầu
        tableSorter.setComparator(1, (o1, o2) -> {
            LocalDate d1 = FormatDate.formatStrToLocalDate(o1.toString());
            LocalDate d2 = FormatDate.formatStrToLocalDate(o2.toString());
            return d1.compareTo(d2);
        });

        // Sắp xếp theo ngày kết thúc
        tableSorter.setComparator(2, (o1, o2) -> {
            LocalDate d1 = FormatDate.formatStrToLocalDate(o1.toString());
            LocalDate d2 = FormatDate.formatStrToLocalDate(o2.toString());
            return d1.compareTo(d2);
        });

        tblKhuyenMai.setRowSorter(tableSorter);

        pnlRightNorth = new JPanel();
        lblTimMa = new JLabel();
        filler6 = new Box.Filler(new Dimension(10, 0), new Dimension(10, 0), new Dimension(10, 32767));
        txtTimMa = new CTextField();
        filler3 = new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(0, 0));
        btnReset = new CButton("Xóa bộ lọc", CButton.SECONDARY);

        setPreferredSize(new Dimension(1920, 800));
        setLayout(new BorderLayout());

        pnlTop.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlTop.setFont(new Font("Arial", 1, 12)); // NOI18N
        pnlTop.setLayout(new BorderLayout());
        pnlTop.setBackground(EColor.BG_COLOR.getColor());

        lblTitle.setFont(new Font("Arial", 1, 24)); // NOI18N
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setText("Quản lý khuyến mãi");
        pnlTop.add(lblTitle, BorderLayout.CENTER);

        add(pnlTop, BorderLayout.NORTH);

        pnlLeft.setBorder(BorderFactory.createTitledBorder(null, "Thông tin khuyến mãi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new Font("Arial", 0, 12))); // NOI18N
        pnlLeft.setFont(new Font("Arial", 1, 12)); // NOI18N
        pnlLeft.setPreferredSize(new Dimension(400, 182));
        pnlLeft.setBackground(EColor.BG_COLOR.getColor());
        pnlLeft.setLayout(new BorderLayout());
        pnlLeft.setBackground(EColor.BG_COLOR.getColor());

        pnlLeftTop.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlLeftTop.setBackground(EColor.BG_COLOR.getColor());
        pnlLeftTop.setLayout(new BoxLayout(pnlLeftTop, BoxLayout.Y_AXIS));

        boxNgayAD.setLayout(new BoxLayout(boxNgayAD, BoxLayout.X_AXIS));
        boxNgayAD.setBackground(EColor.BG_COLOR.getColor());

        lblNgayAD.setText("Ngày áp dụng");
        lblNgayAD.setPreferredSize(new Dimension(100, 0));
        txtNgayAD.setPreferredSize(new Dimension(200, 30));
        txtNgayAD.getJFormattedTextField().setFont(new Font("Arial", Font.BOLD, 14));
        boxNgayAD.add(lblNgayAD);
        boxNgayAD.add(txtNgayAD);

        pnlLeftTop.add(boxNgayAD);
        pnlLeftTop.add(filler1);

        boxNgayKT.setLayout(new BoxLayout(boxNgayKT, BoxLayout.X_AXIS));
        boxNgayKT.setBackground(EColor.BG_COLOR.getColor());

        lblNgayKT.setText("Ngày kết thúc");
        lblNgayKT.setPreferredSize(new Dimension(100, 0));
        txtNgayKT.setPreferredSize(new Dimension(200, 30));
        txtNgayKT.getJFormattedTextField().setFont(new Font("Arial", Font.BOLD, 14));
        boxNgayKT.add(lblNgayKT);
        boxNgayKT.add(txtNgayKT);

        pnlLeftTop.add(boxNgayKT);
        pnlLeftTop.add(filler2);

        boxDoiTuong.setLayout(new BoxLayout(boxDoiTuong, BoxLayout.X_AXIS));
        boxDoiTuong.setBackground(EColor.BG_COLOR.getColor());

        lblDoiTuong.setText("Đối tượng");
        lblDoiTuong.setPreferredSize(new Dimension(100, 0));
        boxDoiTuong.add(lblDoiTuong);

        cboDoiTuong.setPreferredSize(txtNgayAD.getPreferredSize());
        boxDoiTuong.add(cboDoiTuong);

        pnlLeftTop.add(boxDoiTuong);
        pnlLeftTop.add(filler5);

        boxPhanTram.setLayout(new BoxLayout(boxPhanTram, BoxLayout.X_AXIS));
        boxPhanTram.setBackground(EColor.BG_COLOR.getColor());

        lblPhanTram.setText("Phần trăm (%)");
        lblPhanTram.setPreferredSize(new Dimension(100, 0));
        txtPhanTram.setPreferredSize(txtNgayAD.getPreferredSize());

        boxPhanTram.add(lblPhanTram);
        boxPhanTram.add(txtPhanTram);

        pnlLeftTop.add(boxPhanTram);
        pnlLeftTop.add(filler7);


        Box boxTrangThai = Box.createHorizontalBox();
        boxTrangThai.setBackground(EColor.BG_COLOR.getColor());
        lblTrangThai.setText("Trạng thái");
        lblTrangThai.setPreferredSize(new Dimension(100, 0));
        txtTrangThai.setPreferredSize(txtNgayAD.getPreferredSize());
        txtTrangThai.setEditable(false);
        boxTrangThai.add(lblTrangThai);
        boxTrangThai.add(txtTrangThai);
        pnlLeftTop.add(boxTrangThai);
        pnlLeftTop.add(filler4);
        pnlLeftTop.add(filler4);

        btnThem.setIcon(new CImage("images/icons/plus.png", 16, 16));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        boxChucNang.setBackground(EColor.BG_COLOR.getColor());
        boxChucNang.add(btnThem);

        btnCapNhat.setIcon(new CImage("images/icons/loop.png", 16, 16));
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        boxChucNang.add(btnCapNhat);

        // Ẩn nút cập nhật nếu là nhân viên
        if (nhanVien.getChucVu().getTenCV().equals(EChucVu.NV.getChucVu())) {
            System.out.println("Nhan vien");
            btnThem.setEnabled(false);
            btnCapNhat.setEnabled(false);
        }

        pnlLeftTop.add(boxChucNang);

        pnlLeft.add(pnlLeftTop, BorderLayout.NORTH);


        add(pnlLeft, BorderLayout.LINE_START);

        pnlRight.setFont(new Font("Arial", 1, 12)); // NOI18N
        pnlRight.setLayout(new BorderLayout());
        pnlRight.setBackground(EColor.BG_COLOR.getColor());

        tblKhuyenMai.setModel(tblModelKhuyenMai);
        tblKhuyenMai.setCellEditor(null);

        jScrollPane1.setViewportView(tblKhuyenMai);

        pnlRight.add(jScrollPane1, BorderLayout.CENTER);

        pnlRightNorth.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlRightNorth.setBackground(EColor.BG_COLOR.getColor());
        pnlRightNorth.setBorder(new EmptyBorder(10, 10, 10, 10));

        lblTimMa.setText("Mã khuyến mãi");
        pnlRightNorth.add(lblTimMa);
        pnlRightNorth.add(filler6);

        txtTimMa.setPreferredSize(new Dimension(200, 30));
        pnlRightNorth.add(txtTimMa);
        txtTimMa.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filterAction.run(); // Gọi hàm khi có văn bản được thêm
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filterAction.run(); // Gọi hàm khi có văn bản bị xóa
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filterAction.run(); // Gọi hàm khi văn bản thay đổi kiểu (thường dùng cho định dạng văn bản)
            }
        });
        pnlRightNorth.add(filler3);
        btnReset.setPreferredSize(new Dimension(120, 30));
        btnReset.addActionListener(e -> {
            txtTimMa.setText("");
            cboLocDoiTuong.setSelectedIndex(0);
            cboLocTrangThai.setSelectedIndex(0);
            updateModel();
        });

        pnlRightNorth.add(Box.createHorizontalStrut(30));
        lblLocTheoDoiTuong.setText("Đối tượng");
        pnlRightNorth.add(lblLocTheoDoiTuong);
        pnlRightNorth.add(Box.createHorizontalStrut(10));
        pnlRightNorth.add(cboLocDoiTuong);
        cboLocDoiTuong.setPreferredSize(txtTimMa.getPreferredSize());
        pnlRightNorth.add(Box.createHorizontalStrut(10));

        lblLocTheoTrangThai.setText("Trạng thái");
        pnlRightNorth.add(lblLocTheoTrangThai);
        pnlRightNorth.add(Box.createHorizontalStrut(10));
        pnlRightNorth.add(cboLocTrangThai);
        cboLocTrangThai.setPreferredSize(txtTimMa.getPreferredSize());
        cboLocDoiTuong.addActionListener(e -> filterAction.run());
        cboLocTrangThai.addActionListener(e -> filterAction.run());
        pnlRightNorth.add(Box.createHorizontalStrut(10));
        pnlRightNorth.add(btnReset);

        pnlRight.add(pnlRightNorth, BorderLayout.PAGE_START);

        add(pnlRight, BorderLayout.CENTER);
    }

    // Xử lý sự kiện cập nhật khuyến mãi
    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {
        // Cập nhật khuyến mãi
        String maKM = tblModelKhuyenMai.getValueAt(tblKhuyenMai.getSelectedRow(), 0).toString();
        String ngayAD = txtNgayAD.getJFormattedTextField().getText();
        String ngayKT = txtNgayKT.getJFormattedTextField().getText();
        String doiTuong = cboDoiTuong.getSelectedItem().toString();
        String phanTram = txtPhanTram.getText();

        if (!validateInput()) return;

        LocalDate ngayADLocal = FormatDate.formatStrToLocalDate(ngayAD);
        LocalDate ngayKTLocal = FormatDate.formatStrToLocalDate(ngayKT);

        double phanTramDouble = Double.parseDouble(phanTram) / 100;

        KhuyenMai khuyenMaiObj = new KhuyenMai(maKM, ngayADLocal, ngayKTLocal, doiTuong, phanTramDouble, false);

        // Phần trăm 100% sang 1
        double newPhanTram = phanTramDouble / 100;
        KhuyenMai km = DAOKhuyenMai.suaKhuyenMai(khuyenMaiObj);
        if (km != null) {
            // Cập nhật lên bảng
            int selectedRow = tblKhuyenMai.getSelectedRow();
            tblModelKhuyenMai.setValueAt(ngayADLocal, selectedRow, 1);
            tblModelKhuyenMai.setValueAt(ngayKTLocal, selectedRow, 2);
            tblModelKhuyenMai.setValueAt(doiTuong, selectedRow, 3);
            tblModelKhuyenMai.setValueAt(phanTram + "%", selectedRow, 4);
            JOptionPane.showMessageDialog(this, "Cập nhật khuyến mãi thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật khuyến mãi thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {
        // Thêm khuyến mãi
        String ngayAD = txtNgayAD.getJFormattedTextField().getText();
        String ngayKT = txtNgayKT.getJFormattedTextField().getText();
        String doiTuong = cboDoiTuong.getSelectedItem().toString();
        String phanTram = txtPhanTram.getText();

        if (!validateInput()) return;

        LocalDate ngayADLocal = FormatDate.formatStrToLocalDate(ngayAD);
        LocalDate ngayKTLocal = FormatDate.formatStrToLocalDate(ngayKT);

        double phanTramDouble = Double.parseDouble(phanTram);

        // Phần trăm 100% sang 1
        double newPhanTram = phanTramDouble / 100;
        KhuyenMai khuyenMaiObj = new KhuyenMai(ngayADLocal, ngayKTLocal, doiTuong, newPhanTram);

        // TODO: Xử lý thêm khuyến mãi
        if (DAOKhuyenMai.themKhuyenMai(khuyenMaiObj)) {
            // Thêm vào bảng bang cach goi lai ham lay danh sach khuyen mai
            updateModel();

            JOptionPane.showMessageDialog(this, "Thêm khuyến mãi thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Thêm khuyến mãi thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }


    }

    public void updateModel() {
        tblModelKhuyenMai.setRowCount(0);
        listKhuyenMai = DAOKhuyenMai.getDSKhuyenMai();
        listKhuyenMai.forEach((khuyenMai) -> {
            tblModelKhuyenMai.addRow(new Object[]{
                    khuyenMai.getMaKM(),
                    FormatDate.formatLocalDateToDMY(khuyenMai.getNgayBD()),
                    FormatDate.formatLocalDateToDMY(khuyenMai.getNgayKT()),
                    khuyenMai.getDoiTuong(),
                    String.format("%.0f", khuyenMai.getPhanTramKM() * 100) + "%",
                    LocalDate.now().isAfter(khuyenMai.getNgayKT()) ? "Hết hạn" : "Đang áp dụng"
            });
        });
    }




    private JPanel boxChucNang;
    private JPanel boxDoiTuong;
    private JPanel boxNgayAD;
    private JPanel boxNgayKT;
    private JPanel boxPhanTram;
    private JButton btnCapNhat;
    private JButton btnThem;
    private JComboBox<String> cboDoiTuong;
    private Box.Filler filler1;
    private Box.Filler filler2;
    private Box.Filler filler3;
    private Box.Filler filler4;
    private Box.Filler filler5;
    private Box.Filler filler6;
    private Box.Filler filler7;
    private JScrollPane jScrollPane1;
    private JLabel lblDoiTuong;
    private JLabel lblNgayAD;
    private JLabel lblNgayKT;
    private JLabel lblPhanTram;
    private JLabel lblTimMa;
    private JLabel lblTitle;
    private JPanel pnlLeft;
    private JPanel pnlRight;
    private JPanel pnlRightNorth;
    private JPanel pnlLeftTop;
    private JPanel pnlTop;
    private CTable tblKhuyenMai;
    private DefaultTableModel tblModelKhuyenMai;
    private JDatePickerImpl txtNgayAD;
    private JDatePickerImpl txtNgayKT;
    private JFormattedTextField txtPhanTram;
    private JLabel lblTrangThai;
    private CTextField txtTrangThai;
    private CTextField txtTimMa;
    private TableRowSorter<DefaultTableModel> tableSorter;
    private JLabel lblLocTheoDoiTuong;
    private JComboBox<String> cboLocDoiTuong;
    private JLabel lblLocTheoTrangThai;
    private JComboBox<String> cboLocTrangThai;
    private Runnable filterAction;
}