/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import ServiceLocator.RemoteLookup;
import control.IDAOChuyenTau;
import control.IDAOGa;
import control.IDAOTau;
import entity.ChuyenTau;
import entity.Ga;
import entity.NhanVien;
import entity.Tau;
import enums.EChucVu;
import enums.EColor;

import enums.ETrangThaiChuyenTau;
import gui.components.DatePicker;
import gui.components.TimeField;
import gui.custom.CButton;
import gui.custom.CImage;
import gui.custom.CTable;
import gui.custom.CTextField;
import org.jdatepicker.impl.JDatePickerImpl;
import utils.FormatDate;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Huy
 */
public class PnlChuyenTau extends JPanel {
    /**
     * Constructor chứa thông tin nhân viên
     * @param nhanVien Nhân viên
     */

    private IDAOGa daoGa;
    private IDAOTau daoTau;
    private IDAOChuyenTau daoChuyenTau;


    public PnlChuyenTau(NhanVien nhanVien) throws RemoteException {
        this.nhanVien = nhanVien;
        this.daoTau = RemoteLookup.getRemote("tauDao",IDAOTau.class);
        this.daoGa = RemoteLookup.getRemote("gaDao",IDAOGa.class);
        this.daoChuyenTau = RemoteLookup.getRemote("chuyenTauDao",IDAOChuyenTau.class);
        setBackground(EColor.BG_COLOR.getColor());
        initComponents();
        readDataFromDB();
        initFilterAction();

        // Nếu là nhân viên thì ẩn nút thêm và cập nhật disabled các trường nhập liệu
        if (nhanVien.getChucVu().getTenCV().equals(EChucVu.NV.getChucVu())) {
            cboGaDi.setEnabled(false);
            cboGaDen.setEnabled(false);
            cboTau.setEnabled(false);
            txtMacTau.setEnabled(false);
            datePickerNgayDi.getJFormattedTextField().setEnabled(false);
            txtGioDi.setEnabled(false);
            datePickerNgayDen.getJFormattedTextField().setEnabled(false);
            txtGioDen.setEnabled(false);
            cboTrangThai.setEnabled(false);
            btnThem.setEnabled(false);
            btnCapNhat.setEnabled(false);
        }
    }

    /**
     * Hàm đọc dữ liệu từ cơ sở dữ liệu
     */

    public void readDataFromDB() throws RemoteException {
        listGa = daoGa.getDsGa();
        listTau = daoTau.getDSTau();
        listChuyen = daoChuyenTau.getDanhSachChuyenTau();

        // Đổ dữ liệu ga vào combobox
        listGa.forEach(ga -> {
            String tenGa = ga.getTenGa();
            cboGaDi.addItem(tenGa);
            cboGaDen.addItem(tenGa);
            cboTimTheoGaDi.addItem(tenGa);
            cboTimTheoGaDen.addItem(tenGa);
        });

        // Đổ dữ liệu tàu vào combobox
        listTau.forEach(tau -> {
            cboTau.addItem(tau.getMaTau());
            cboTimTheoMaTau.addItem(tau.getMaTau());
        });

        // Đổ dữ liệu chuyến vào bảng
        listChuyen.forEach(chuyen -> {
            // Kiểm tra nếu ngày đến đã qua thì cập nhật trạng thái chuyến tàu
            if (chuyen.getNgayGioDen().isBefore(LocalDateTime.now())) {
                chuyen.setTrangThai(ETrangThaiChuyenTau.DA_DUNG.getTrangThai());
                try {
                    daoChuyenTau.capNhatChuyenTau(chuyen);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }

            tblModelChuyenTau.addRow(new Object[]{
                    chuyen.getMaChuyen(),
                    chuyen.getMacTau(),
                    chuyen.getTau().getMaTau(),
                    chuyen.getGaDi().getTenGa(),
                    chuyen.getGaDen().getTenGa(),
                    FormatDate.formatLocaldatetimeToString(chuyen.getNgayGioDi()),
                    FormatDate.formatLocaldatetimeToString(chuyen.getNgayGioDen()),
                    chuyen.getTrangThai()
            });
        });
    }

    /**
     * Khởi tạo các thành phần giao diện
     */
    private void initComponents() {
        pnlTop = new JPanel();
        lblTitle = new JLabel();
        pnlLeft = new JPanel();
        pnlLeftTop = new JPanel();
        boxGaDi = new JPanel();
        lblGaDi = new JLabel();
        cboGaDi = new JComboBox<>();
        filler1 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        boxGaDen = new JPanel();
        lblGaDen = new JLabel();
        cboGaDen = new JComboBox<>();
        filler2 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        boxTau = new JPanel();
        lblTau = new JLabel();
        cboTau = new JComboBox<>();
        filler5 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        lblMacTau = new JLabel();
        txtMacTau = new JTextField();
        txtMacTau.setEnabled(false);
        boxNgayDi = new JPanel();
        lblNgayDi = new JLabel();
        datePickerNgayDi = DatePicker.getDatePicker();
        filler6 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        boxGioDi = new JPanel();
        lblGioDi = new JLabel();
        txtGioDi = new TimeField();
        boxNgayDen = new JPanel();
        lblNgayDen = new JLabel();
        datePickerNgayDen = DatePicker.getDatePicker();
        filler7 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        boxGioDen = new JPanel();
        lblGioDen = new JLabel();
        txtGioDen = new TimeField();
        lblTrangThai = new JLabel();
        cboTrangThai = new JComboBox<>(
                new String[]{
                        ETrangThaiChuyenTau.HOAT_DONG.getTrangThai(),
                        ETrangThaiChuyenTau.DA_DUNG.getTrangThai(),
                        ETrangThaiChuyenTau.TAM_NGUNG.getTrangThai()}
        );
        filler4 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        boxChucNang = new JPanel();
        btnThem = new CButton("Thêm", CButton.PRIMARY);
        btnCapNhat = new CButton("Cập nhật", CButton.SECONDARY);
        jLayeredPane1 = new JLayeredPane();
        pnlRight = new JPanel();
        jScrollPane1 = new JScrollPane();
        tblChuyenTau = new CTable();
        pnlRightTop = new JPanel();
        txtTimKiemTheoMaChuyen = new CTextField();
        datePickerNgayDenLoc = DatePicker.getDatePicker();
        datePickerNgayDiLoc = DatePicker.getDatePicker();
        lblTimTheoGaDi = new JLabel("Ga đi");
        lblTimTheoGaDen = new JLabel("Ga đến");
        lblTimTheoMaChuyen = new JLabel("Mã chuyến");
        lblTimTheoMaTau = new JLabel("Mã tàu");
        lblTimTheoTrangThai = new JLabel("Trạng thái");
        lblTimTheoNgayDi = new JLabel("Ngày đi");
        lblTimTheoNgayDen = new JLabel("Ngày đến");
        cboTimTheoGaDi = new JComboBox<>();
        cboTimTheoGaDen = new JComboBox<>();
        cboTimTheoGaDi.addItem("Tất cả");
        cboTimTheoGaDen.addItem("Tất cả");
        cboTimTheoMaTau = new JComboBox<>();
        cboTimTheoMaTau.addItem("Tất cả");
        cboTimTheoMaTau.addActionListener(
                new java.awt.event.ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        filterAction.run();
                    }
                }
        );
        cboTimTheoTrangThai = new JComboBox<>(
                new String[]{"Tất cả", ETrangThaiChuyenTau.HOAT_DONG.getTrangThai(), ETrangThaiChuyenTau.DA_DUNG.getTrangThai(), ETrangThaiChuyenTau.TAM_NGUNG.getTrangThai()}
        );
        cboTimTheoTrangThai.addActionListener(
                new java.awt.event.ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        filterAction.run();
                    }
                }
        );
        btnXoaBoLoc = new CButton("Xóa bộ lọc", CButton.SECONDARY);

        setPreferredSize(new Dimension(1920, 800));
        setLayout(new BorderLayout());

        pnlTop.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlTop.setFont(new Font("Arial", 1, 12)); // NOI18N
        pnlTop.setLayout(new BorderLayout());
        pnlTop.setBackground(EColor.BG_COLOR.getColor());

        lblTitle.setFont(new Font("Arial", 1, 24)); // NOI18N
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setText("Quản lý chuyến tàu");
        pnlTop.add(lblTitle, BorderLayout.CENTER);

        add(pnlTop, BorderLayout.NORTH);

        pnlLeft.setBorder(BorderFactory.createTitledBorder(null, "Thông tin chuyến tàu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new Font("Arial", 0, 12))); // NOI18N
        pnlLeft.setFont(new Font("Arial", 1, 12)); // NOI18N
        pnlLeft.setPreferredSize(new Dimension(400, 182));
        pnlLeft.setLayout(new BorderLayout());
        pnlLeft.setBackground(EColor.BG_COLOR.getColor());

        pnlLeftTop.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlLeftTop.setLayout(new BoxLayout(pnlLeftTop, BoxLayout.Y_AXIS));
        pnlLeftTop.setBackground(EColor.BG_COLOR.getColor());

        boxGaDi.setLayout(new BoxLayout(boxGaDi, BoxLayout.X_AXIS));
        boxGaDi.setBackground(EColor.BG_COLOR.getColor());

        lblGaDi.setText("Ga đi");
        lblGaDi.setPreferredSize(new Dimension(80, 0));
        boxGaDi.add(lblGaDi);

        cboGaDi.setEditable(true);
        cboGaDi.setPreferredSize(new Dimension(200, 30));
        boxGaDi.add(cboGaDi);

        pnlLeftTop.add(boxGaDi);
        pnlLeftTop.add(filler1);

        boxGaDen.setLayout(new BoxLayout(boxGaDen, BoxLayout.X_AXIS));
        boxGaDen.setBackground(EColor.BG_COLOR.getColor());

        lblGaDen.setText("Ga đến");
        lblGaDen.setPreferredSize(new Dimension(80, 0));
        boxGaDen.add(lblGaDen);

        cboGaDen.setEditable(true);
        cboGaDen.setPreferredSize(cboGaDi.getPreferredSize());
        boxGaDen.add(cboGaDen);

        pnlLeftTop.add(boxGaDen);
        pnlLeftTop.add(filler2);

        boxTau.setLayout(new BoxLayout(boxTau, BoxLayout.X_AXIS));
        boxTau.setBackground(EColor.BG_COLOR.getColor());

        lblTau.setText("Mã tàu");
        lblTau.setPreferredSize(new Dimension(80, 0));
        boxTau.add(lblTau);
        cboTau.setPreferredSize(new Dimension(70, 30));
        boxTau.add(cboTau);

        pnlLeftTop.add(boxTau);
        pnlLeftTop.add(filler5);

        boxNgayDi.setLayout(new BoxLayout(boxNgayDi, BoxLayout.X_AXIS));
        boxNgayDi.setBackground(EColor.BG_COLOR.getColor());

        lblNgayDi.setText("Ngày đi");
        lblNgayDi.setPreferredSize(new Dimension(80, 0));
        boxNgayDi.add(lblNgayDi);
        datePickerNgayDi.getJFormattedTextField().setFont(new Font("Arial", Font.BOLD, 14));
        datePickerNgayDi.setPreferredSize(cboGaDi.getPreferredSize());
        boxNgayDi.add(datePickerNgayDi);

        pnlLeftTop.add(boxNgayDi);
        pnlLeftTop.add(filler6);

        boxGioDi.setLayout(new BoxLayout(boxGioDi, BoxLayout.X_AXIS));
        boxGioDi.setBackground(EColor.BG_COLOR.getColor());

        lblGioDi.setText("Giờ đi");
        lblGioDi.setPreferredSize(new Dimension(80, 0));
        txtGioDi.setPreferredSize(cboGaDi.getPreferredSize());
        txtGioDi.setFont(new Font("Arial", Font.BOLD, 14));
        boxGioDi.add(lblGioDi);
        boxGioDi.add(txtGioDi);

        pnlLeftTop.add(Box.createVerticalStrut(10));
        pnlLeftTop.add(boxGioDi);
        pnlLeftTop.add(Box.createVerticalStrut(10));

        boxNgayDen.setLayout(new BoxLayout(boxNgayDen, BoxLayout.X_AXIS));
        boxNgayDen.setBackground(EColor.BG_COLOR.getColor());

        lblNgayDen.setText("Ngày đến");
        lblNgayDen.setPreferredSize(new Dimension(80, 0));
        boxNgayDen.add(lblNgayDen);

        lblGioDen.setText("Giờ đến");
        lblGioDen.setPreferredSize(new Dimension(80, 0));
        txtGioDen.setFont(new Font("Arial", Font.BOLD, 14));
        boxNgayDen.add(lblGioDen);
        datePickerNgayDen.getJFormattedTextField().setFont(new Font("Arial", Font.BOLD, 14));
        datePickerNgayDen.getJFormattedTextField().setPreferredSize(cboGaDi.getPreferredSize());

        boxNgayDen.add(datePickerNgayDen);

        pnlLeftTop.add(boxNgayDen);
        pnlLeftTop.add(filler7);

        boxGioDen.setLayout(new BoxLayout(boxGioDen, BoxLayout.X_AXIS));
        boxGioDen.setBackground(EColor.BG_COLOR.getColor());

        lblGioDen.setText("Giờ đến");
        lblGioDen.setPreferredSize(new Dimension(80, 0));
        txtGioDen.setPreferredSize(cboGaDi.getPreferredSize());
        txtGioDen.setFont(new Font("Arial", Font.BOLD, 14));
        boxGioDen.add(lblGioDen);
        boxGioDen.add(txtGioDen);

        pnlLeftTop.add(boxGioDen);
        pnlLeftTop.add(filler4);

        // Trạng thái
        lblTrangThai.setText("Trạng thái");
        lblTrangThai.setPreferredSize(new Dimension(80, 0));
        cboTrangThai.setPreferredSize(cboGaDi.getPreferredSize());

        Box boxTrangThai = Box.createHorizontalBox();
        boxTrangThai.add(lblTrangThai);
        boxTrangThai.add(cboTrangThai);
        pnlLeftTop.add(boxTrangThai);

        btnThem.setText("Thêm");
        btnThem.setBackground(EColor.BTN_BG_COLOR.getColor());
        btnThem.setIcon(new CImage("images/icons/plus.png", 16, 16));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    btnThemActionPerformed(evt);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        boxChucNang.setBackground(EColor.BG_COLOR.getColor());
        boxChucNang.add(btnThem);

        btnCapNhat.setText("Cập nhật");
        btnCapNhat.setIcon(new CImage("images/icons/loop.png", 16, 16));
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    btnCapNhatActionPerformed(evt);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        boxChucNang.add(btnCapNhat);

        pnlLeftTop.add(Box.createVerticalStrut(20));
        pnlLeftTop.add(boxChucNang);

        // Ẩn nút cập nhật nếu là nhân viên
        if (nhanVien.getChucVu().getTenCV().equals(EChucVu.NV.getChucVu())) {
            btnThem.setEnabled(false);
            btnCapNhat.setEnabled(false);
        }

        pnlLeft.add(pnlLeftTop, BorderLayout.NORTH);
        pnlLeft.add(jLayeredPane1, BorderLayout.CENTER);

        add(pnlLeft, BorderLayout.LINE_START);

        pnlRight.setFont(new Font("Arial", 1, 12)); // NOI18N
        pnlRight.setLayout(new BorderLayout());
        pnlRight.setBackground(EColor.BG_COLOR.getColor());

        String[] columnNames = {"Mã chuyến", "Mác tàu", "Mã tàu", "Ga đi", "Ga đến", "Thời gian đi", "Thời gian đến", "Trạng thái"};
        tblModelChuyenTau = new DefaultTableModel(columnNames, 0);
        tblChuyenTau.setModel(tblModelChuyenTau);
        tblChuyenTau.setCellEditor(null);
        tblChuyenTau.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int row = tblChuyenTau.getSelectedRow();

                if (row != -1) {
                    String maTau = tblChuyenTau.getValueAt(row, 2).toString();
                    String gaDi = tblChuyenTau.getValueAt(row, 3).toString();
                    String gaDen = tblChuyenTau.getValueAt(row, 4).toString();
                    String ngayGioDi = tblChuyenTau.getValueAt(row, 5).toString();
                    String ngayGioDen = tblChuyenTau.getValueAt(row, 6).toString();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm dd/MM/yyyy");
                    LocalDateTime ngayGioDiLDT = LocalDateTime.parse(ngayGioDi, formatter);
                    LocalDateTime ngayGioDenLDT = LocalDateTime.parse(ngayGioDen, formatter);

                    LocalDate ngayDi = ngayGioDiLDT.toLocalDate();
                    LocalDate ngayDen = ngayGioDenLDT.toLocalDate();
                    LocalTime gioDi = ngayGioDiLDT.toLocalTime();
                    LocalTime gioDen = ngayGioDenLDT.toLocalTime();

                    cboTau.setSelectedItem(maTau);
                    cboGaDi.setSelectedItem(gaDi);
                    cboGaDen.setSelectedItem(gaDen);
                    datePickerNgayDi.getModel().setDate(ngayDi.getYear(), ngayDi.getMonthValue() - 1, ngayDi.getDayOfMonth());
                    datePickerNgayDi.getModel().setSelected(true);
                    txtGioDi.setText(gioDi.toString());
                    datePickerNgayDen.getModel().setDate(ngayDen.getYear(), ngayDen.getMonthValue() - 1, ngayDen.getDayOfMonth());
                    datePickerNgayDen.getModel().setSelected(true);
                    txtGioDen.setText(gioDen.toString());
                    txtMacTau.setText(tblChuyenTau.getValueAt(row, 1).toString());
                }
            }
        });

        tableSorter = new TableRowSorter<>(tblModelChuyenTau);
        tableSorter.setComparator(5, dateTimeComparator); // Cột Ngày đi
        tableSorter.setComparator(6, dateTimeComparator);  // Cột Thời gian đến
        tblChuyenTau.setRowSorter(tableSorter);
        jScrollPane1.setViewportView(tblChuyenTau);

        // Panel Right
        pnlRight.add(jScrollPane1, BorderLayout.CENTER);

        // Panel Right Top
        pnlRightTop.setLayout(new BoxLayout(pnlRightTop, BoxLayout.X_AXIS));
        pnlRightTop.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlRightTop.setBackground(EColor.BG_COLOR.getColor());

        // Box tìm kiếm
        Box boxTimKiem = Box.createVerticalBox();
        boxTimKiem.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Tìm kiếm"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        Box boxGaDi = createHorizontalBoxWithComponents(lblTimTheoGaDi, cboTimTheoGaDi);
        Box boxGaDen = createHorizontalBoxWithComponents(lblTimTheoGaDen, cboTimTheoGaDen);
        Box boxNgayDi = createHorizontalBoxWithComponents(lblTimTheoNgayDi, datePickerNgayDiLoc);
        Box boxNgayDen = createHorizontalBoxWithComponents(lblTimTheoNgayDen, datePickerNgayDenLoc);
        Box boxMaTau = createHorizontalBoxWithComponents(lblTimTheoMaTau, cboTimTheoMaTau);
        Box boxMaChuyen = createHorizontalBoxWithComponents(lblTimTheoMaChuyen, txtTimKiemTheoMaChuyen);
        Box boxLocTheoTrangThai = createHorizontalBoxWithComponents(lblTimTheoTrangThai, cboTimTheoTrangThai);

        Box boxTim1 = createHorizontalBoxWithComponents(boxMaChuyen, boxGaDi, boxGaDen, boxLocTheoTrangThai);
        Box boxTim2 = createHorizontalBoxWithComponents(boxMaTau, boxNgayDi, boxNgayDen);
        Box boxTim3 = createHorizontalBoxWithComponents(btnXoaBoLoc);

        lblTimTheoMaChuyen.setPreferredSize(new Dimension(80, 0));
        lblTimTheoMaTau.setPreferredSize(lblTimTheoMaChuyen.getPreferredSize());

        boxTimKiem.add(boxTim1);
        boxTimKiem.add(Box.createVerticalStrut(10));
        boxTimKiem.add(boxTim2);
        boxTimKiem.add(Box.createVerticalStrut(10));
        boxTimKiem.add(boxTim3);

        cboTimTheoGaDi.addActionListener(
                new java.awt.event.ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        cboTimTheoGaDiActionPerformed(evt);
                    }
                }
        );
        cboTimTheoGaDen.addActionListener(
                new java.awt.event.ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        cboTimTheoGaDenActionPerformed(evt);
                    }
                }
        );
        datePickerNgayDiLoc.addActionListener(
                new java.awt.event.ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        datePickerNgayDiLocActionPerformed(evt);
                    }
                }
        );
        datePickerNgayDenLoc.addActionListener(
                new java.awt.event.ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        datePickerNgayDenLocActionPerformed(evt);
                    }
                }
        );
        cboTimTheoMaTau.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        filterAction.run();
                    }
                });
        txtTimKiemTheoMaChuyen.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
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
        btnXoaBoLoc.addActionListener(
                new java.awt.event.ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        btnXoaBoLocActionPerformed(evt);
                    }
                }
        );

        pnlRightTop.add(boxTimKiem);
        pnlRight.add(pnlRightTop, BorderLayout.NORTH);

        add(pnlRight, BorderLayout.CENTER);
    }

    /**
     * Hàm khởi tạo bộ lọc
     */
    public void initFilterAction() {
        // Xử lý lọc
        filterAction = () -> {
            System.out.println("Lọc dữ liệu");
            List<RowFilter<Object, Object>> filters = new ArrayList<>();

            // Lọc theo Ga Đi
            String gaDi = cboTimTheoGaDi.getSelectedItem().toString();
            if (!"Tất cả".equals(gaDi)) {
                filters.add(RowFilter.regexFilter(gaDi, 3));
            }

            // Lọc theo Ga Đến
            String gaDen = cboTimTheoGaDen.getSelectedItem().toString();
            if (!"Tất cả".equals(gaDen)) {
                filters.add(RowFilter.regexFilter(gaDen, 4));
            }

            // Lọc theo ngày đi
            String ngayDiStr = datePickerNgayDiLoc.getJFormattedTextField().getText();
            if (!ngayDiStr.isEmpty()) {
                try {
                    LocalDate ngayDi = FormatDate.formatStrToLocalDate(ngayDiStr);
                    ngayDiStr = ngayDi.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    filters.add(RowFilter.regexFilter(ngayDiStr, 5));
                } catch (DateTimeParseException e) {
                    e.printStackTrace(); // Debug lỗi khi định dạng không hợp lệ
                }
            }

            // Lọc theo ngày đến
            String ngayDenStr = datePickerNgayDenLoc.getJFormattedTextField().getText();
            if (!ngayDenStr.isEmpty()) {
                try {
                    LocalDate ngayDen = FormatDate.formatStrToLocalDate(ngayDenStr);
                    ngayDenStr = ngayDen.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    filters.add(RowFilter.regexFilter(ngayDenStr, 6));
                } catch (DateTimeParseException e) {
                    e.printStackTrace(); // Debug lỗi khi định dạng không hợp lệ
                }
            }

            // Lọc theo mã tàu
            String maTau = cboTimTheoMaTau.getSelectedItem().toString();
            if (!"Tất cả".equals(maTau)) {
                filters.add(RowFilter.regexFilter(maTau, 2));
            }

            // Lọc theo mã chuyến
            String maChuyen = txtTimKiemTheoMaChuyen.getText();
            if (!maChuyen.isEmpty()) {
                filters.add(RowFilter.regexFilter(".*" + maChuyen + ".*", 0));
            }

            // Lọc theo trạng thái
            String trangThai = cboTimTheoTrangThai.getSelectedItem().toString();
            if (!"Tất cả".equals(trangThai)) {
                filters.add(RowFilter.regexFilter(trangThai, 7));
            }

            // Kết hợp tất cả bộ lọc
            RowFilter<Object, Object> combinedFilter = filters.isEmpty() ? null : RowFilter.andFilter(filters);
            tableSorter.setRowFilter(combinedFilter);
        };
    }

    /**
     * Comparator so sánh ngày giờ
     */
    Comparator<String> dateTimeComparator = (dateTime1, dateTime2) -> {
        try {
            LocalDateTime dt1 = LocalDateTime.parse(dateTime1, formatterDateTime);
            LocalDateTime dt2 = LocalDateTime.parse(dateTime2, formatterDateTime);
            return dt1.compareTo(dt2);
        } catch (Exception e) {
            // Nếu có lỗi, xem như bằng nhau
            return 0;
        }
    };

    /**
     * Tạo box chứa các component theo chiều ngang
     * @param components Các component
     * @return Box
     */
    public Box createHorizontalBoxWithComponents(JComponent... components) {
        Box box = Box.createHorizontalBox();
        for (int i = 0; i < components.length; i++) {
            box.add(components[i]);

            if (i < components.length - 1) {
                box.add(Box.createHorizontalStrut(10));
            }
        }
        return box;
    }

    /**
     * Tạo box chứa các component theo chiều dọc
     * @param components Các component
     * @return Box
     */
    public Box createVerticalBoxWithComponents(JComponent... components) {
        Box box = Box.createVerticalBox();
        for (int i = 0; i < components.length; i++) {
            box.add(components[i]);

            if (i < components.length - 1) {
                box.add(Box.createVerticalStrut(10));
            }
        }
        return box;
    }

    /**
     * Hàm xử lý sự kiện nút xóa bộ lọc
     * @param evt Sự kiện
     */
    public void btnXoaBoLocActionPerformed(ActionEvent evt) {
        datePickerNgayDiLoc.getJFormattedTextField().setText("");
        datePickerNgayDenLoc.getJFormattedTextField().setText("");
        cboTimTheoGaDi.setSelectedIndex(0);
        cboTimTheoGaDen.setSelectedIndex(0);
        cboTimTheoMaTau.setSelectedIndex(0);
        cboTimTheoTrangThai.setSelectedIndex(0);
        filterAction.run();
    }

    /**
     * Hàm xử lý sự kiện của date picker ngày đến
     * @param evt Sự kiện
     */
    public void datePickerNgayDenLocActionPerformed(ActionEvent evt) {
        filterAction.run();
    }

    /**
     * Hàm xử lý sự kiện của date picker ngày đi
     * @param evt Sự kiện
     */
    public void datePickerNgayDiLocActionPerformed(ActionEvent evt) {
        filterAction.run();
    }

    /**
     * Hàm xử lý sự kiện tìm 
     * @param evt
     */
    public void cboTimTheoGaDenActionPerformed(ActionEvent evt) {
        filterAction.run();
    }

    /**
     * Hàm xử lý sự kiện tìm theo ga đi
     * @param evt
     */
    public void cboTimTheoGaDiActionPerformed(ActionEvent evt) {
        filterAction.run();
    }

    /**
     * Hàm xử lý sự kiện nút cập nhật
     * @param evt
     */
    public void btnCapNhatActionPerformed(ActionEvent evt) throws RemoteException {
        capNhatChuyenTau();
    }

    /**
     * Hàm kiểm tra dữ liệu nhập vào
     * @return true nếu dữ liệu hợp lệ, ngược lại false
     */
    public boolean validateInput() {
        // TODO add your handling code here:
        String maTau = cboTau.getSelectedItem().toString();
        String gaDi = cboGaDi.getSelectedItem().toString();
        String gaDen = cboGaDen.getSelectedItem().toString();

        String ngayDiStr = datePickerNgayDi.getJFormattedTextField().getText();
        String gioDi = txtGioDi.getText();
        String ngayDenStr = datePickerNgayDen.getJFormattedTextField().getText();
        String gioDen = txtGioDen.getText();

        // Ga đi khác ga đến
        if (gaDi.equals(gaDen)) {
            JOptionPane.showMessageDialog(this, "Ga đi phải khác ga đến", "Lỗi", JOptionPane.ERROR_MESSAGE);
            cboGaDen.requestFocus();
            return false;
        }

        if (maTau.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã tàu không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (ngayDiStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ngày đi không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            datePickerNgayDi.getJFormattedTextField().requestFocus();
            return false;
        }

        if (gioDi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giờ đi không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtGioDi.requestFocus();
            return false;
        }

        if (ngayDenStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ngày đến không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            datePickerNgayDen.getJFormattedTextField().requestFocus();
            return false;
        }

        if (gioDen.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giờ đến không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtGioDen.requestFocus();
            return false;
        }

        LocalDateTime ngayGioDi;
        LocalDateTime ngayGioDen;

        // Kiểm tra ngày, giờ đi
        try {
            LocalDate ngayDi = FormatDate.formatStrToLocalDate(ngayDiStr);
            LocalTime gioDiLT = LocalTime.parse(gioDi);

            ngayGioDi = LocalDateTime.of(ngayDi, gioDiLT);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ngày giờ đi không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtGioDi.requestFocus();
            return false;
        }

        // Kiểm tra ngày, giờ đến
        try {
            LocalDate ngayDen = FormatDate.formatStrToLocalDate(ngayDenStr);
            LocalTime gioDenLT = LocalTime.parse(gioDen);

            ngayGioDen = LocalDateTime.of(ngayDen, gioDenLT);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ngày giờ đến không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtGioDen.requestFocus();
            return false;
        }

        // Thời gian đi phải trước thời gian đến
        if (ngayGioDi.isAfter(ngayGioDen)) {
            JOptionPane.showMessageDialog(this, "Thời gian đi phải trước thời gian đến", "Lỗi", JOptionPane.ERROR_MESSAGE);
            datePickerNgayDen.getJFormattedTextField().requestFocus();
            return false;
        }

        // Thời gian đi phải sau thời gian hiện tại
        if (ngayGioDi.isBefore(LocalDateTime.now())) {
            JOptionPane.showMessageDialog(this, "Thời gian đi phải sau thời gian hiện tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            datePickerNgayDi.getJFormattedTextField().requestFocus();
            return false;
        }

        return true;
    }

    /**
     * Hàm xử lý sự kiện nút cập nhật
     * @param evt Sự kiện
     */
    public void btnThemActionPerformed(ActionEvent evt) throws RemoteException {
        String maTau = cboTau.getSelectedItem().toString();
        String gaDi = cboGaDi.getSelectedItem().toString();
        String gaDen = cboGaDen.getSelectedItem().toString();

        String ngayDiStr = datePickerNgayDi.getJFormattedTextField().getText();
        String gioDi = txtGioDi.getText();
        String ngayDenStr = datePickerNgayDen.getJFormattedTextField().getText();
        String gioDen = txtGioDen.getText();

        // Kiểm tra dữ liệu nhập vào
        if (!validateInput()) return;

        LocalDate ngayDi = FormatDate.formatStrToLocalDate(ngayDiStr);
        LocalDate ngayDen = FormatDate.formatStrToLocalDate(ngayDenStr);

        LocalTime gioDiLT = LocalTime.parse(gioDi);
        LocalTime gioDenLT = LocalTime.parse(gioDen);

        LocalDateTime ngayGioDi = LocalDateTime.of(ngayDi, gioDiLT);
        LocalDateTime ngayGioDen = LocalDateTime.of(ngayDen, gioDenLT);

        int maGaDi = listGa.stream().filter(g -> g.getTenGa().equals(gaDi)).findFirst().get().getMaGa();
        int maGaDen = listGa.stream().filter(g -> g.getTenGa().equals(gaDen)).findFirst().get().getMaGa();

        if (daoChuyenTau.getChuyenTauTheoMaTauMaGaDiMaGaDenNgayGioDiNgayGioDen(
                maTau, maGaDi, maGaDen, ngayGioDi, ngayGioDen) != null) {
            JOptionPane.showMessageDialog(this, "Chuyến tàu đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Lấy đối tượng tàu và ga
        Tau tau = listTau.stream().filter(t -> t.getMaTau().equals(maTau)).findFirst().get();
        Ga gaDiObj = listGa.stream().filter(g -> g.getTenGa().equals(gaDi)).findFirst().get();
        Ga gaDenObj = listGa.stream().filter(g -> g.getTenGa().equals(gaDen)).findFirst().get();

        ChuyenTau chuyen = new ChuyenTau(maTau, tau, gaDiObj, gaDenObj, ngayGioDi, ngayGioDen, ETrangThaiChuyenTau.HOAT_DONG.getTrangThai());

        try {
            // Thêm chuyến tàu vào cơ sở dữ liệu
            ChuyenTau chuyenTauMoi = daoChuyenTau.themChuyenTau(chuyen);
            JOptionPane.showMessageDialog(this, "Thêm chuyến tàu thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            tblModelChuyenTau.addRow(new Object[]{
                    chuyenTauMoi.getMaChuyen(),
                    chuyenTauMoi.getMacTau(),
                    chuyenTauMoi.getTau().getMaTau(),
                    chuyenTauMoi.getGaDi().getTenGa(),
                    chuyenTauMoi.getGaDen().getTenGa(),
                    FormatDate.formatLocaldatetimeToString(chuyen.getNgayGioDi()),
                    FormatDate.formatLocaldatetimeToString(chuyen.getNgayGioDen()),
                    chuyenTauMoi.getTrangThai()
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm chuyến tàu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Hàm cập nhật chuyến tàu
     */
    public void capNhatChuyenTau() throws RemoteException {
        int row = tblChuyenTau.getSelectedRow();

        if (row == -1) return;

        String maChuyen = tblModelChuyenTau.getValueAt(row, 0).toString();
        String maTau = cboTau.getSelectedItem().toString();
        String gaDi = cboGaDi.getSelectedItem().toString();
        String gaDen = cboGaDen.getSelectedItem().toString();

        String ngayDiStr = datePickerNgayDi.getJFormattedTextField().getText();
        String gioDi = txtGioDi.getText();
        String ngayDenStr = datePickerNgayDen.getJFormattedTextField().getText();
        String gioDen = txtGioDen.getText();

        // Kiểm tra dữ liệu nhập vào
        if (!validateInput()) return;

        LocalDate ngayDi = FormatDate.formatStrToLocalDate(ngayDiStr);
        LocalDate ngayDen = FormatDate.formatStrToLocalDate(ngayDenStr);

        LocalTime gioDiLT = LocalTime.parse(gioDi);
        LocalTime gioDenLT = LocalTime.parse(gioDen);

        LocalDateTime ngayGioDi = LocalDateTime.of(ngayDi, gioDiLT);
        LocalDateTime ngayGioDen = LocalDateTime.of(ngayDen, gioDenLT);

        String macTau = txtMacTau.getText();

        // Lấy đối tượng tàu và ga
        Tau tau = listTau.stream().filter(t -> t.getMaTau().equals(maTau)).findFirst().get();
        Ga gaDiObj = listGa.stream().filter(g -> g.getTenGa().equals(gaDi)).findFirst().get();
        Ga gaDenObj = listGa.stream().filter(g -> g.getTenGa().equals(gaDen)).findFirst().get();
        String trangThai = cboTrangThai.getSelectedItem().toString();

        ChuyenTau chuyen = new ChuyenTau(maChuyen, macTau, tau, gaDiObj, gaDenObj, ngayGioDi, ngayGioDen, trangThai);

        // Thêm chuyến tàu vào cơ sở dữ liệu
        if (daoChuyenTau.capNhatChuyenTau(chuyen)) {
            JOptionPane.showMessageDialog(this, "Cập nhật chuyến tàu thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);

            tblModelChuyenTau.setValueAt(maChuyen, row, 0);
            tblModelChuyenTau.setValueAt(macTau, row, 1);
            tblModelChuyenTau.setValueAt(maTau, row, 2);
            tblModelChuyenTau.setValueAt(gaDi, row, 3);
            tblModelChuyenTau.setValueAt(gaDen, row, 4);
            tblModelChuyenTau.setValueAt(FormatDate.formatLocaldatetimeToString(ngayGioDi), row, 5);
            tblModelChuyenTau.setValueAt(FormatDate.formatLocaldatetimeToString(ngayGioDen), row, 6);
            tblModelChuyenTau.setValueAt(trangThai, row, 7);
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật chuyến tàu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private NhanVien nhanVien;
    private JPanel boxChucNang;
    private JPanel boxGaDen;
    private JPanel boxGaDi;
    private JPanel boxGioDen;
    private JPanel boxGioDi;
    private JPanel boxNgayDen;
    private JPanel boxNgayDi;
    private JPanel boxTau;
    private JButton btnCapNhat;
    private JButton btnThem;
    private JButton btnXoaBoLoc;
    private JComboBox<String> cboGaDen;
    private JComboBox<String> cboGaDi;
    private JComboBox<String> cboTau;
    private Box.Filler filler1;
    private Box.Filler filler2;
    private Box.Filler filler4;
    private Box.Filler filler5;
    private Box.Filler filler6;
    private Box.Filler filler7;
    private JLayeredPane jLayeredPane1;
    private JScrollPane jScrollPane1;
    private JLabel lblGaDen;
    private JLabel lblGaDi;
    private JLabel lblGioDen;
    private JLabel lblGioDi;
    private JLabel lblNgayDen;
    private JLabel lblNgayDi;
    private JLabel lblTau;
    private JLabel lblTrangThai;
    private JLabel lblTitle;
    private JPanel pnlLeft;
    private JPanel pnlRight;
    private JPanel pnlRightTop;
    private JPanel pnlLeftTop;
    private JPanel pnlTop;
    private DefaultTableModel tblModelChuyenTau;
    private CTable tblChuyenTau;
    private TimeField txtGioDen;
    private TimeField txtGioDi;
    private JComboBox<String> cboTrangThai;
    private CTextField txtTimKiemTheoMaChuyen;
    private JLabel lblTimTheoGaDi;
    private JLabel lblTimTheoGaDen;
    private JLabel lblTimTheoNgayDi;
    private JLabel lblTimTheoNgayDen;
    private JLabel lblTimTheoMaChuyen;
    private JLabel lblTimTheoMaTau;
    private JLabel lblTimTheoTrangThai;
    private JComboBox<String> cboTimTheoGaDi;
    private JComboBox<String> cboTimTheoGaDen;
    private JComboBox<String> cboTimTheoMaTau;
    private JComboBox<String> cboTimTheoTrangThai;
    private TableRowSorter<DefaultTableModel> tableSorter;
    private Runnable filterAction;
    private JLabel lblMacTau;
    private JTextField txtMacTau;
    private ArrayList<Ga> listGa;
    private ArrayList<Tau> listTau;
    private ArrayList<ChuyenTau> listChuyen;
    private JDatePickerImpl datePickerNgayDi, datePickerNgayDiLoc;
    private JDatePickerImpl datePickerNgayDen, datePickerNgayDenLoc;
    private static DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("H:mm:ss");
    private static DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("H:mm dd/MM/yyyy");
    // End of variables declaration
}
