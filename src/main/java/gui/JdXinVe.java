package gui;

import control.*;
import entity.*;
import enums.EColor;
import enums.ETrangThaiVe;
import gui.components.NgaySinhTextField;
import gui.custom.CButton;
import gui.custom.CDialog;
import gui.custom.CTable;
import gui.custom.CTextField;
import service.HoaDonService;
import utils.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JComponent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @Dự án: tau-viet-express
 * @Class: JdXinVe
 * @Tạo vào ngày: 10/12/2024
 * @Tác giả: Huy
 */
public class JdXinVe extends CDialog implements MouseListener {
    private NhanVien nhanVien;
    private HoaDon hoaDon;
    private ArrayList<Ve> listVe;
    private ArrayList<KhachHang> listKhachHang;
    private KhachHang nguoiMuaVe;
    private boolean isHoaDonTam;

    private JTabbedPane tabbedPane;

    private DefaultTableModel tblModelVeXinChieuDi;
    private DefaultTableModel tblModelVeXinChieuVe;
    private CTable tblVeXinChieuDi;
    private CTable tblVeXinChieuVe;

    private JPanel pnlNorth;
    private JPanel pnlMain;
    private JPanel pnlSouth;

    private PnlBanVe pnlParent;
    private ChuyenTau chuyenChieuDi;
    private ChuyenTau chuyenChieuVe;
    private ArrayList<ChoNgoi> listChoNgoiChieuDi;
    private ArrayList<ChoNgoi> listChoNgoiChieuVe;
    private ArrayList<KhuyenMai> listKhuyenMai;

    private JLabel lblTimKh;
    private JLabel lblTenKh;
    private JLabel lblCCCD;
    private JLabel lblSDT;
    private JLabel lblEmail;
    private JLabel lblNgaySinh;
    private JLabel lblDoiTuong;
    private JLabel lblKhuyenMai;
    private JLabel lblTenKhNguoiMua;
    private JLabel lblCCCDNguoiMua;
    private JLabel lblSDTNguoiMua;
    private JLabel lblEmailNguoiMua;
    private JLabel lblNgaySinhNguoiMua;
    private JLabel lblTongTienGiam;
    private JLabel lblTongTienVe;
    private JLabel lblThanhTien;

    private CTextField txtTimKh;
    private CTextField txtTenKh;
    private CTextField txtCCCD;
    private CTextField txtSDT;
    private CTextField txtEmail;
    private CTextField txtKhuyenMai;
    private CTextField txtTenNguoiMua;
    private CTextField txtCCCDNguoiMua;
    private CTextField txtSDTNguoiMua;
    private CTextField txtEmailNguoiMua;
    private CTextField txtTienGiam;
    private CTextField txtTongTienVe;
    private CTextField txtThanhTien;
    private NgaySinhTextField txtNgaySinh;
    private NgaySinhTextField txtNgaySinhNguoiMua;
    private CTextField txtDoiTuongNguoiMua;
    private JComboBox<String> cboDoiTuong;

    private JCheckBox chkXuatHoaDon;

    private CButton btnTimKh;
    private CButton btnLuuKhLenBang;
    private CButton btnXoaTrang;
    private CButton btnInVe;
    private CButton btnThanhToan;
    private CButton btnLuuTam;
    private CButton btnNguoiDat;

    /**
     * Constructor không có chuyến chiều về
     *
     * @param pnlParent Panel cha
     * @param nhanVien  Nhân viên
     * @param hoaDonTam Hoá đơn tạm
     */
    public JdXinVe(PnlBanVe pnlParent, NhanVien nhanVien, HoaDon hoaDonTam) {
        this.nhanVien = nhanVien;
        this.pnlParent = pnlParent;
        this.hoaDon = hoaDonTam;
        this.listVe = hoaDonTam.getDanhSachVe();
        this.nguoiMuaVe = hoaDonTam.getKhachHang();
        this.listKhachHang = new ArrayList<>(hoaDonTam.getDanhSachVe().stream().map(Ve::getKhachHang).toList());
        this.chuyenChieuDi = hoaDonTam.getDanhSachVe().get(0).getChuyenTau();
        this.listChoNgoiChieuDi = new ArrayList<>(hoaDonTam.getDanhSachVe()
                .stream().filter(ve -> ve.getChuyenTau().getGaDi().equals(chuyenChieuDi.getGaDi())).map(Ve::getChoNgoi).toList());

        // Kiểm tra có chuyến chiều về không
        boolean isChieuVe = hoaDonTam.getDanhSachVe()
                .stream().filter(ve -> ve.getChuyenTau().getGaDi().equals(chuyenChieuDi.getGaDen())).count() > 0;
        if (isChieuVe) {
            System.out.println("Co chieu ve");
            this.chuyenChieuVe = hoaDonTam.getDanhSachVe()
                    .stream().filter(ve -> ve.getChuyenTau().getGaDi().equals(chuyenChieuDi.getGaDen())).findFirst().get().getChuyenTau();
            this.listChoNgoiChieuVe = new ArrayList<>(hoaDonTam.getDanhSachVe()
                    .stream().filter(ve -> ve.getChuyenTau().getGaDi().equals(chuyenChieuDi.getGaDen())).map(Ve::getChoNgoi).toList());
        }

        init();
    }

    /**
     * Constructor có chuyến chiều về
     *
     * @param pnlParent          Panel cha
     * @param nhanVien           Nhân viên
     * @param chuyenChieuDi      Chuyến chiều đi
     * @param listChoNgoiChieuDi Danh sách chỗ ngồi chiều đi
     */
    public JdXinVe(PnlBanVe pnlParent, NhanVien nhanVien, ChuyenTau chuyenChieuDi, ArrayList<ChoNgoi> listChoNgoiChieuDi) {
        this.nhanVien = nhanVien;
        this.pnlParent = pnlParent;
        this.chuyenChieuDi = chuyenChieuDi;
        this.listChoNgoiChieuDi = listChoNgoiChieuDi;

        init();
    }

    /**
     * Constructor có chuyến chiều về
     *
     * @param pnlParent          Panel cha
     * @param nhanVien           Nhân viên
     * @param chuyenChieuDi      Chuyến chiều đi
     * @param chuyenChieuVe      Chuyến chiều về
     * @param listChoNgoiChieuDi Danh sách chỗ ngồi chiều đi
     * @param listChoNgoiChieuVe Danh sách chỗ ngồi chiều về
     */
    public JdXinVe(PnlBanVe pnlParent, NhanVien nhanVien, ChuyenTau chuyenChieuDi, ChuyenTau chuyenChieuVe, ArrayList<ChoNgoi> listChoNgoiChieuDi, ArrayList<ChoNgoi> listChoNgoiChieuVe) {
        this.nhanVien = nhanVien;
        this.pnlParent = pnlParent;
        this.chuyenChieuDi = chuyenChieuDi;
        this.chuyenChieuVe = chuyenChieuVe;
        this.listChoNgoiChieuDi = listChoNgoiChieuDi;
        this.listChoNgoiChieuVe = listChoNgoiChieuVe;

        init();
    }

    /**
     * Constructor có chuyến chiều về
     *
     * @return true nếu lưu thành công, ngược lại false
     */
    public boolean isHoaDonTam() {
        return isHoaDonTam;
    }

    /**
     * Set hoá đơn tạm
     *
     * @param hoaDonTam Hoá đơn tạm
     */
    public void setHoaDonTam(boolean hoaDonTam) {
        isHoaDonTam = hoaDonTam;
    }

    /**
     * Khởi tạo giao diện
     */
    public void init() {
        setTitle("Xin vé");

        initTable();
        initComponents();
        setContentPane(pnlMain);

        pnlMain.setBackground(EColor.BG_COLOR.getColor());

        initPnlNorth();
        initPnlSouth();

        pnlMain.add(pnlNorth, BorderLayout.CENTER);
        pnlMain.add(pnlSouth, BorderLayout.SOUTH);

        readDataFromDB();
        themCacChoNgoiVaoTable();
        capNhatThanhToan();
        initKeyBindings();

        // Select dòng đầu tiên của 2 bảng
        tblVeXinChieuDi.setRowSelectionInterval(0, 0);
        if (tblVeXinChieuVe != null) {
            tblVeXinChieuVe.setRowSelectionInterval(0, 0);
        }
    }

    /**
     * Khởi tạo panel phía dưới
     */
    public void initPnlSouth() {
        pnlSouth.setLayout(new BorderLayout());
        pnlSouth.setBackground(EColor.BG_COLOR.getColor());
        pnlSouth.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel pnlThongTinVe = getPnlThongTinVe();
        JPanel pnlThongTinNguoiMua = getPnlThongTinNguoiMua();
        JPanel pnlThanhToan = getPnlThanhToan();

        Box boxLeft = Box.createVerticalBox();
        boxLeft.add(pnlThongTinVe);
        boxLeft.add(Box.createVerticalStrut(10));
        boxLeft.add(pnlThongTinNguoiMua);

        pnlSouth.add(boxLeft, BorderLayout.CENTER);
        pnlSouth.add(pnlThanhToan, BorderLayout.EAST);
    }

    /**
     * Khởi tạo panel phía trên
     */
    public void initPnlNorth() {
        pnlNorth.setLayout(new BorderLayout());
        pnlNorth.setBackground(EColor.BG_COLOR.getColor());
        pnlNorth.setPreferredSize(new Dimension(0, 500));
        pnlNorth.add(tabbedPane, BorderLayout.CENTER);
    }

    /**
     * Khởi tạo bảng
     */
    public void initTable() {
        String[] columnsName = {
                "STT", "Thông tin chỗ", "Họ tên", "CCCD", "SĐT", "Email", "Ngày sinh", "Đối tượng", "Thuế", "Khuyến mãi", "Thành tiền"
        };

        tblModelVeXinChieuDi = new DefaultTableModel(columnsName, 0);
        tblVeXinChieuDi = new CTable(tblModelVeXinChieuDi) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblVeXinChieuDi.setCellEditor(null);
        tblVeXinChieuDi.addMouseListener(this);

        // Set kích thước ô thông tin chỗ to hơn
        tblVeXinChieuDi.getColumnModel().getColumn(1).setPreferredWidth(300);

        if (chuyenChieuVe != null) {
            tblModelVeXinChieuVe = new DefaultTableModel(columnsName, 0);
            tblVeXinChieuVe = new CTable(tblModelVeXinChieuDi) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tblVeXinChieuVe.setCellEditor(null);
            tblVeXinChieuVe.addMouseListener(this);

            // Set kích thước ô thông tin chỗ to hơn
            tblVeXinChieuVe.getColumnModel().getColumn(1).setPreferredWidth(300);
        }
    }

    /**
     * Khởi tạo các thành phần
     */
    public void initComponents() {
        pnlMain = new JPanel();
        pnlMain.setLayout(new BorderLayout());

        pnlNorth = new JPanel();
        pnlNorth.setPreferredSize(new Dimension(0, 300));
        pnlSouth = new JPanel();

        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(EColor.BG_COLOR.getColor());
        tabbedPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        tabbedPane.addTab("Chuyến đi", new JScrollPane(tblVeXinChieuDi));
        if (tblVeXinChieuVe != null) {
            tabbedPane.addTab("Chuyến về", new JScrollPane(tblVeXinChieuVe));
        }

        lblTenKh = new JLabel("Họ tên");
        lblCCCD = new JLabel("CCCD");
        lblSDT = new JLabel("SĐT");
        lblEmail = new JLabel("Email");
        lblNgaySinh = new JLabel("Ngày sinh");
        lblDoiTuong = new JLabel("Đối tượng");
        lblKhuyenMai = new JLabel("Khuyến mãi");

        lblTimKh = new JLabel("Tìm khách: ");
        lblTenKhNguoiMua = new JLabel("Họ tên");
        lblCCCDNguoiMua = new JLabel("CCCD");
        lblSDTNguoiMua = new JLabel("SĐT");
        lblEmailNguoiMua = new JLabel("Email");
        lblNgaySinhNguoiMua = new JLabel("Ngày sinh");

        lblTongTienGiam = new JLabel("Tổng tiền giảm");
        lblTongTienVe = new JLabel("Tổng tiền vé");
        lblThanhTien = new JLabel("Thành tiền");

        txtTimKh = new CTextField();
        txtTenKh = new CTextField();
        txtCCCD = new CTextField();
        txtSDT = new CTextField();
        txtEmail = new CTextField();
        txtKhuyenMai = new CTextField();
        txtKhuyenMai.setEditable(false);

        txtTenNguoiMua = new CTextField();
        txtCCCDNguoiMua = new CTextField();
        txtSDTNguoiMua = new CTextField();
        txtEmailNguoiMua = new CTextField();
        txtNgaySinh = new NgaySinhTextField();
        txtDoiTuongNguoiMua = new CTextField();
        txtNgaySinhNguoiMua = new NgaySinhTextField();
        txtTienGiam = new CTextField();
        txtTienGiam.setEditable(false);
        txtTongTienVe = new CTextField();
        txtTongTienVe.setEditable(false);
        txtThanhTien = new CTextField();
        txtThanhTien.setEditable(false);

        cboDoiTuong = new JComboBox<>();
        cboDoiTuong.setEditable(false);

        // Disabled
        txtTenKh.setEditable(false);
        txtCCCD.setEditable(false);
        txtSDT.setEditable(false);
        txtEmail.setEditable(false);
        txtNgaySinh.setEditable(false);
        txtTenNguoiMua.setEditable(false);
        txtCCCDNguoiMua.setEditable(false);
        txtSDTNguoiMua.setEditable(false);
        txtEmailNguoiMua.setEditable(false);
        txtNgaySinhNguoiMua.setEditable(false);
        cboDoiTuong.setEnabled(false);

        btnTimKh = new CButton("Tìm (F6)");
        btnTimKh.setPreferredSize(new Dimension(100, 30));
        btnTimKh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timKhachHang();
            }
        });

        btnLuuKhLenBang = new CButton("Lưu");
        btnLuuKhLenBang.setPreferredSize(new Dimension(100, 30));
        btnLuuKhLenBang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                luuKhachHangLenBang();
            }
        });

        btnXoaTrang = new CButton("Xóa trắng", CButton.SECONDARY);
        btnXoaTrang.setPreferredSize(new Dimension(120, 30));
        btnXoaTrang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoaTrang();
            }
        });

        btnNguoiDat = new CButton("Người đặt");
        btnNguoiDat.setPreferredSize(new Dimension(120, 30));
        btnNguoiDat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                capNhatNguoiDat();
            }
        });

        btnThanhToan = new CButton("Thanh toán (F1)", CButton.PRIMARY);
        btnThanhToan.setPreferredSize(new Dimension(160, 30));
        btnThanhToan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                luuHoaDon();
            }
        });

        btnLuuTam = new CButton("Lưu tạm (F3)", CButton.SECONDARY);
        btnLuuTam.setPreferredSize(new Dimension(140, 30));
        btnLuuTam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                luuTam();
            }
        });

        btnInVe = new CButton("In vé (F2)", CButton.PRIMARY);
        btnInVe.setEnabled(false);
        btnInVe.setPreferredSize(new Dimension(100, 30));
        btnInVe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inVe();
            }
        });

        chkXuatHoaDon = new JCheckBox("Xuất hóa đơn (F4)");
        chkXuatHoaDon.setBackground(EColor.BG_COLOR.getColor());
    }

    /**
     * Khởi tạo key bindings
     */
    public void initKeyBindings() {
        // Lấy JRootPane của JDialog
        JRootPane rootPane = getRootPane();

        // Lấy InputMap và ActionMap của JRootPane
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = rootPane.getActionMap();

        // Đặt phím tắt F1 - Thanh toán
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "ThanhToan");
        actionMap.put("ThanhToan", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                luuHoaDon();
            }
        });

        // Đặt phím tắt F2 - In vé
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "InVe");
        actionMap.put("InVe", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inVe();
            }
        });

        // Đặt phím tắt F3 - Lưu tạm
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), "LuuTam");
        actionMap.put("LuuTam", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                luuTam();
            }
        });

        // Đặt phím tắt F4 - Xuất hóa đơn
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), "XuatHoaDon");
        actionMap.put("XuatHoaDon", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chkXuatHoaDon.setSelected(!chkXuatHoaDon.isSelected());
            }
        });

        // Đặt phím tắt F5 - Xóa trắng
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "XoaTrang");
        actionMap.put("XoaTrang", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoaTrang();
            }
        });

        // Đặt phím tắt F6 - Tìm khách hàng
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), "TimKhachHang");
        actionMap.put("TimKhachHang", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timKhachHang();
            }
        });

        // Đặt phím tắt Ctrl + F - Focus vào tìm khách hàng
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK), "FocusTimKhachHang");
        actionMap.put("FocusTimKhachHang", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtTimKh.requestFocus();
            }
        });

        // Đặt phím tắt Ctrl + S - Lưu khách hàng lên bảng
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), "LuuKhachHang");
        actionMap.put("LuuKhachHang", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                luuKhachHangLenBang();
            }
        });

        // Đặt các phím từ Ctrl + 1 đến Độ dài bảng để chọn dòng
        for (int i = 1; i <= 9; i++) {
            int finalI = i;
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_0 + i, InputEvent.CTRL_DOWN_MASK), "ChonDong" + i);
            actionMap.put("ChonDong" + i, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (finalI <= tblVeXinChieuDi.getRowCount()) {
                        tblVeXinChieuDi.setRowSelectionInterval(finalI - 1, finalI - 1);
                        docTuBangRaField();
                    }
                }
            });
        }

        // Đặt phím Ctrl + T - Focus vào họ tên
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK), "FocusHoTen");
        actionMap.put("FocusHoTen", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtTenKh.requestFocus();
            }
        });

        // Đặt phím ESC - Thoát
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Thoat");
        actionMap.put("Thoat", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(JdXinVe.this, "Bạn có muốn thoát không?", "Thoát", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
    }

    /**
     * Tạo panel thông tin vé
     * @return Panel thông tin vé
     */
    public JPanel getPnlThongTinVe() {
        JPanel pnlThongTinVe = new JPanel();
        pnlThongTinVe.setLayout(new BoxLayout(pnlThongTinVe, BoxLayout.Y_AXIS));
        pnlThongTinVe.setBackground(EColor.BG_COLOR.getColor());
        pnlThongTinVe.setBorder(BorderFactory.createTitledBorder("Thông tin vé"));

        JPanel pnlInput = new JPanel();
        pnlInput.setLayout(new GridLayout(4, 2, 10, 10));
        pnlInput.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlInput.setBackground(EColor.BG_COLOR.getColor());

        Box boxTenKh = createHozitonalBox(lblTenKh, txtTenKh);
        Box boxCCCD = createHozitonalBox(lblCCCD, txtCCCD);
        Box boxSDT = createHozitonalBox(lblSDT, txtSDT);
        Box boxEmail = createHozitonalBox(lblEmail, txtEmail);
        Box boxNgaySinh = createHozitonalBox(lblNgaySinh, txtNgaySinh);
        Box boxDoiTuong = createHozitonalBox(lblDoiTuong, cboDoiTuong);
        Box boxKhuyenMai = createHozitonalBox(lblKhuyenMai, txtKhuyenMai);

        lblTenKh.setPreferredSize(lblKhuyenMai.getPreferredSize());
        lblCCCD.setPreferredSize(lblKhuyenMai.getPreferredSize());
        lblSDT.setPreferredSize(lblKhuyenMai.getPreferredSize());
        lblEmail.setPreferredSize(lblKhuyenMai.getPreferredSize());
        lblNgaySinh.setPreferredSize(lblKhuyenMai.getPreferredSize());
        lblDoiTuong.setPreferredSize(lblKhuyenMai.getPreferredSize());

        pnlInput.add(boxTenKh);
        pnlInput.add(boxCCCD);
        pnlInput.add(boxSDT);
        pnlInput.add(boxEmail);
        pnlInput.add(boxNgaySinh);
        pnlInput.add(boxDoiTuong);
        pnlInput.add(boxKhuyenMai);

        JPanel pnlBtn = new JPanel();
        pnlBtn.setBackground(EColor.BG_COLOR.getColor());
        pnlBtn.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlBtn.add(btnLuuKhLenBang);
        pnlBtn.add(Box.createHorizontalStrut(10));
        pnlBtn.add(btnNguoiDat);
        pnlBtn.add(Box.createHorizontalStrut(10));
        pnlBtn.add(btnXoaTrang);

        JPanel pnlTimKh = new JPanel();
        pnlTimKh.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnlTimKh.setBackground(EColor.BG_COLOR.getColor());
        pnlTimKh.add(lblTimKh);
        pnlTimKh.add(txtTimKh);
        pnlTimKh.add(btnTimKh);

        pnlThongTinVe.add(pnlTimKh);
        pnlThongTinVe.add(pnlInput);
        pnlThongTinVe.add(pnlBtn);

        txtTimKh.setPreferredSize(new Dimension(200, 30));

        return pnlThongTinVe;
    }

    /**
     * Tạo panel thông tin người mua
     *
     * @return Panel thông tin người mua
     */
    public JPanel getPnlThongTinNguoiMua() {
        JPanel pnlThongTinNguoiMua = new JPanel();
        pnlThongTinNguoiMua.setLayout(new BoxLayout(pnlThongTinNguoiMua, BoxLayout.Y_AXIS));
        pnlThongTinNguoiMua.setBackground(EColor.BG_COLOR.getColor());
        pnlThongTinNguoiMua.setBorder(BorderFactory.createTitledBorder("Thông tin người mua"));

        JPanel pnlInput = new JPanel();
        pnlInput.setLayout(new GridLayout(3, 2, 10, 10));
        pnlInput.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlInput.setBackground(EColor.BG_COLOR.getColor());

        Box boxTenKh = createHozitonalBox(lblTenKhNguoiMua, txtTenNguoiMua);
        Box boxCCCD = createHozitonalBox(lblCCCDNguoiMua, txtCCCDNguoiMua);
        Box boxSDT = createHozitonalBox(lblSDTNguoiMua, txtSDTNguoiMua);
        Box boxEmail = createHozitonalBox(lblEmailNguoiMua, txtEmailNguoiMua);
        Box boxNgaySinh = createHozitonalBox(lblNgaySinhNguoiMua, txtNgaySinhNguoiMua);

        lblTenKhNguoiMua.setPreferredSize(lblNgaySinhNguoiMua.getPreferredSize());
        lblCCCDNguoiMua.setPreferredSize(lblNgaySinhNguoiMua.getPreferredSize());
        lblSDTNguoiMua.setPreferredSize(lblNgaySinhNguoiMua.getPreferredSize());
        lblEmailNguoiMua.setPreferredSize(lblNgaySinhNguoiMua.getPreferredSize());
        lblNgaySinhNguoiMua.setPreferredSize(lblNgaySinhNguoiMua.getPreferredSize());

        pnlInput.add(boxTenKh);
        pnlInput.add(boxCCCD);
        pnlInput.add(boxSDT);
        pnlInput.add(boxEmail);
        pnlInput.add(boxNgaySinh);

        pnlThongTinNguoiMua.add(pnlInput);

        return pnlThongTinNguoiMua;
    }

    /**
     * Tạo panel thanh toán
     *
     * @return Panel thanh toán
     */
    public JPanel getPnlThanhToan() {
        JPanel pnlThanhToan = new JPanel();
        pnlThanhToan.setBackground(EColor.BG_COLOR.getColor());
        pnlThanhToan.setLayout(new BorderLayout());
        pnlThanhToan.setPreferredSize(new Dimension(400, 0));
        pnlThanhToan.setBorder(BorderFactory.createCompoundBorder(
                new TitledBorder("Thanh toán"),
                new EmptyBorder(10, 10, 10, 10)
        ));

        // NORTH
        JPanel pnlThongTinThanhToan = new JPanel();
        pnlThongTinThanhToan.setLayout(new GridLayout(4, 1, 10, 10));
        pnlThongTinThanhToan.setBackground(EColor.BG_COLOR.getColor());

        Box boxTienGiam = createHozitonalBox(lblTongTienGiam, txtTienGiam);
        txtTienGiam.setText("0");

        Box boxTongTienVe = createHozitonalBox(lblTongTienVe, txtTongTienVe);
        txtTongTienVe.setText("0");


        Box boxThanhTien = createHozitonalBox(lblThanhTien, txtThanhTien);
        txtThanhTien.setText("0");

        pnlThongTinThanhToan.add(boxTienGiam);
        pnlThongTinThanhToan.add(boxTongTienVe);
        pnlThongTinThanhToan.add(boxThanhTien);

        lblTongTienVe.setPreferredSize(lblTongTienGiam.getPreferredSize());
        lblThanhTien.setPreferredSize(lblTongTienGiam.getPreferredSize());

        // SOUTH
        JPanel pnlBtn = new JPanel();
        pnlBtn.setLayout(new GridLayout(2, 2, 10, 10));
        pnlBtn.setBackground(EColor.BG_COLOR.getColor());
        pnlBtn.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlBtn.add(btnThanhToan);
        pnlBtn.add(btnLuuTam);
        pnlBtn.add(btnInVe);
        pnlBtn.add(chkXuatHoaDon);

        pnlThanhToan.add(pnlThongTinThanhToan, BorderLayout.NORTH);
        pnlThanhToan.add(pnlBtn, BorderLayout.SOUTH);

        return pnlThanhToan;
    }

    /**
     * Lấy thông tin từ bảng
     *
     * @param defaultTableModel DefaultTableModel
     * @param chuyenTau         Chuyến tàu
     * @param choNgoi           Chỗ ngồi
     * @return Mảng thông tin
     */
    public Object[] getObjectTuChuyen(DefaultTableModel defaultTableModel, ChuyenTau chuyenTau, ChoNgoi choNgoi) {
        if (chuyenTau == null) return null;
        Object[] objects = new Object[]{
                defaultTableModel.getRowCount() + 1, // STT
                chuyenTau.getMacTau() + " " +
                        chuyenTau.getGaDi().getTenGa().replace("Ga Tàu", "") + " - " +
                        chuyenTau.getGaDen().getTenGa().replace("Ga Tàu", "") + " - " +
                        FormatDate.formatLocalDateTimeToDMY(chuyenTau.getNgayGioDi()),  // Thông tin chỗ
                "",
                "",
                "",
                "",
                "",
                "Người lớn",
                "10%",
                "0%",
                FormatMoney.format(choNgoi.getGiaCho() + choNgoi.getGiaCho() * 0.1) // Thành tiền

        };
        return objects;
    }

    /**
     * Tạo box chứa các component
     *
     * @param components Các component
     * @return Box
     */
    public Box createHozitonalBox(JComponent... components) {
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
     * @return Box chứa các component
     */
    public Box createVerticalBox(JComponent... components) {
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
     * Đọc dữ liệu từ database
     */
    public void readDataFromDB() {
        listKhuyenMai = DAOKhuyenMai.getDSKhuyenMai();
        listKhuyenMai.forEach(khuyenMai -> {
            cboDoiTuong.addItem(khuyenMai.getDoiTuong());
        });

        if (cboDoiTuong.getItemCount() > 0) {
            try {
                cboDoiTuong.setSelectedIndex(0); // Chỉ số hợp lệ
            } catch (IllegalArgumentException e) {
                e.printStackTrace(); // Xử lý ngoại lệ nếu có
            }
        } else {
            System.out.println("ComboBox không có mục nào để chọn.");
        }

        cboDoiTuong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String doiTuong = cboDoiTuong.getSelectedItem().toString();

                for (KhuyenMai khuyenMai : listKhuyenMai) {
                    if (khuyenMai.getDoiTuong().equals(doiTuong)) {
                        txtKhuyenMai.setText(FormatNumber.formatNumberToPercent(khuyenMai.getPhanTramKM()));
                        break;
                    }
                }
            }
        });

        if (hoaDon != null) {
            txtTenNguoiMua.setText(hoaDon.getKhachHang().getTenKH());
            txtCCCDNguoiMua.setText(hoaDon.getKhachHang().getCCCD());
            txtSDTNguoiMua.setText(hoaDon.getKhachHang().getSdt());
            txtEmailNguoiMua.setText(hoaDon.getKhachHang().getEmail());
            txtNgaySinhNguoiMua.setText(FormatDate.formatLocalDateToDMY(hoaDon.getKhachHang().getNgaySinh()));
            txtDoiTuongNguoiMua.setText(hoaDon.getKhachHang().getDoiTuong());
        }

        if (listVe != null && !listVe.isEmpty()) {
            for (Ve ve : listVe) {
                System.out.println("VE: " + ve);
                // Lấy thông tin vé gắn lên bảng
                boolean isVeChieuVe = ve.getChuyenTau().getGaDi().equals(chuyenChieuDi.getGaDen());
                DefaultTableModel tblModel = isVeChieuVe ? tblModelVeXinChieuVe : tblModelVeXinChieuDi;

                Object[] objects = new Object[]{
                        tblModel.getRowCount() + 1, // STT
                        ve.getChuyenTau().getMacTau() + " " +
                                ve.getChuyenTau().getGaDi().getTenGa().replace("Ga Tàu", "") + " - " +
                                ve.getChuyenTau().getGaDen().getTenGa().replace("Ga Tàu", "") + " - " +
                                FormatDate.formatLocalDateTimeToDMY(ve.getChuyenTau().getNgayGioDi()),  // Thông tin chỗ
                        ve.getKhachHang().getTenKH(),
                        ve.getKhachHang().getCCCD(),
                        ve.getKhachHang().getSdt(),
                        ve.getKhachHang().getEmail(),
                        FormatDate.formatLocalDateToDMY(ve.getKhachHang().getNgaySinh()),
                        ve.getKhachHang().getDoiTuong(),
                        FormatNumber.formatNumberToPercent(ve.getThue()),
                        FormatNumber.formatNumberToPercent(ve.getKhuyenMai().getPhanTramKM()),
                        FormatMoney.format(ve.getThanhTien())
                };
                tblModel.addRow(objects);
            }
        }

        if (hoaDon != null) {
            btnLuuTam.setEnabled(false);
        }
    }

    /**
     * Thêm các chỗ ngồi vào bảng
     */
    public void themCacChoNgoiVaoTable() {
        if (listVe != null && !listVe.isEmpty()) return;

        // Kiểm tra có chuyến chiều đi không
        if (!listChoNgoiChieuDi.isEmpty()) {
            for (int i = 0; i < listChoNgoiChieuDi.size(); i++) {
                ChoNgoi choNgoi = listChoNgoiChieuDi.get(i);
                Object[] rowData = getObjectTuChuyen(tblModelVeXinChieuDi, chuyenChieuDi, choNgoi);
                tblModelVeXinChieuDi.addRow(rowData);
            }
        }

        // Kiểm tra có chuyến chiều về không
        if (chuyenChieuVe != null) {
            if (!listChoNgoiChieuVe.isEmpty()) {
                for (int i = 0; i < listChoNgoiChieuVe.size(); i++) {
                    ChoNgoi choNgoi = listChoNgoiChieuVe.get(i);
                    Object[] rowData = getObjectTuChuyen(tblModelVeXinChieuVe, chuyenChieuVe, choNgoi);
                    tblModelVeXinChieuVe.addRow(rowData);
                }

            }
        }
    }

    /**
     * Tìm khách hàng
     */
    public void timKhachHang() {
        String timKhachStr = txtTimKh.getText().trim();

        if (timKhachStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập thông tin!");
            return;
        }

        KhachHang khachHang;

        if (Validation.sdt(timKhachStr)) {
            khachHang = DAOKhachHang.layKhachHangTheoSdt(timKhachStr);
        } else if (Validation.CCCD(timKhachStr)) {
            khachHang = DAOKhachHang.layKhachHangTheoCCCD(timKhachStr);
        } else {
            JOptionPane.showMessageDialog(this, "Sai định dạng tìm!");
            return;
        }

        if (khachHang == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy khách");
            return;
        }

        // Disabled các field
        txtTenKh.setEditable(false);
        txtCCCD.setEditable(false);
        txtSDT.setEditable(false);
        txtEmail.setEditable(false);
        txtNgaySinh.setEditable(false);
        cboDoiTuong.setEnabled(false);

        // Thêm khách vào field
        themKhachVaoField(khachHang);
        luuKhachHangLenBang();
    }

    /**
     * Thêm khách hàng vào field
     *
     * @param khachHang Khách hàng
     */
    public void themKhachVaoField(KhachHang khachHang) {
        String hoTen = khachHang.getTenKH();
        String sdt = khachHang.getSdt();
        String cccd = khachHang.getCCCD();
        String email = khachHang.getEmail();
        LocalDate ngaySinh = khachHang.getNgaySinh();
        String doiTuong = khachHang.getDoiTuong();

        txtTenKh.setText(hoTen);
        txtSDT.setText(sdt);
        txtCCCD.setText(cccd);
        txtEmail.setText(email);
        txtNgaySinh.setText(FormatDate.formatLocalDateToDMY(ngaySinh));
        cboDoiTuong.setSelectedItem(doiTuong);
    }

    /**
     * Tính thành tiền
     *
     * @return Thành tiền
     */
    public double tinhThanhTien() {
        double tongTienVe = FormatMoney.format(txtTongTienVe.getText());
        double tienGiam = FormatMoney.format(txtTienGiam.getText());
        return tongTienVe - tienGiam;
    }

    /**
     * Xử lý cập nhật thanh toán
     */
    public void capNhatThanhToan() {
        double tongTienVe = tinhTongTienVe();
        double tongTienGiam = tinhTongTienGiam();

        txtTienGiam.setText(FormatMoney.format(tongTienGiam));
        txtTongTienVe.setText(FormatMoney.format(tongTienVe));
        txtThanhTien.setText(FormatMoney.format(tinhThanhTien()));
    }

    // Tính tổng tiền vé bao gồm thuế
    public double tinhTongTienVe() {
        double tongTienChieuDi = listChoNgoiChieuDi.stream().mapToDouble(ChoNgoi::getGiaCho).sum();
        double tongTienChieuVe = chuyenChieuVe != null
                ? listChoNgoiChieuVe.stream().mapToDouble(ChoNgoi::getGiaCho).sum()
                : 0;
        return (tongTienChieuDi + tongTienChieuVe) * 1.1; // Cộng thêm thuế 10%
    }

    // Tính tổng tiền giảm
    public double tinhTongTienGiam() {
        double tongTienGiamChieuDi = tinhTienGiam(tblModelVeXinChieuDi, listChoNgoiChieuDi);
        double tongTienGiamChieuVe = chuyenChieuVe != null
                ? tinhTienGiam(tblModelVeXinChieuVe, listChoNgoiChieuVe)
                : 0;
        return tongTienGiamChieuDi + tongTienGiamChieuVe;
    }

    // Tính tiền giảm cho một chiều
    public double tinhTienGiam(DefaultTableModel model, List<ChoNgoi> danhSachChoNgoi) {
        return IntStream.range(0, model.getRowCount())
                .mapToDouble(i -> {
                    double thanhTienVe = FormatMoney.format(model.getValueAt(i, 10).toString());
                    ChoNgoi choNgoi = danhSachChoNgoi.get(i);
                    return (choNgoi.getGiaCho() * 1.1) - thanhTienVe;
                })
                .sum();
    }


    /**
     * Lưu khách hàng lên bảng
     */
    public void luuKhachHangLenBang() {
        if (!validateInput()) return;

        String tenKh = txtTenKh.getText();
        String cccd = txtCCCD.getText();
        String sdt = txtSDT.getText();
        String email = txtEmail.getText();
        String ngaySinh = txtNgaySinh.getText();
        String doiTuong = cboDoiTuong.getSelectedItem().toString();
        String khuyenMaiStr = txtKhuyenMai.getText();

        double khuyenMai;

        try {
            khuyenMai = FormatNumber.formatPercentToNumber(khuyenMaiStr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Khuyến mãi không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Lưu thông tin lên bảng cả chiều đi và chiều về
        int row = tblVeXinChieuDi.getSelectedRow();
        if (row >= 0) {
            String thanhTien = FormatMoney.format(
                    listChoNgoiChieuDi.get(row).getGiaCho()
                            + listChoNgoiChieuDi.get(row).getGiaCho() * 0.1
                            - (listChoNgoiChieuDi.get(row).getGiaCho()
                            + listChoNgoiChieuDi.get(row).getGiaCho() * 0.1) * khuyenMai
            );

            tblModelVeXinChieuDi.setValueAt(tenKh, row, 2);
            tblModelVeXinChieuDi.setValueAt(cccd, row, 3);
            tblModelVeXinChieuDi.setValueAt(sdt, row, 4);
            tblModelVeXinChieuDi.setValueAt(email, row, 5);
            tblModelVeXinChieuDi.setValueAt(ngaySinh, row, 6);
            tblModelVeXinChieuDi.setValueAt(doiTuong, row, 7);
            tblModelVeXinChieuDi.setValueAt(khuyenMaiStr, row, 9);
            tblModelVeXinChieuDi.setValueAt(thanhTien, row, 10);

            if (tblVeXinChieuVe != null) {
                tblModelVeXinChieuVe.setValueAt(tenKh, row, 2);
                tblModelVeXinChieuVe.setValueAt(cccd, row, 3);
                tblModelVeXinChieuVe.setValueAt(sdt, row, 4);
                tblModelVeXinChieuVe.setValueAt(email, row, 5);
                tblModelVeXinChieuVe.setValueAt(ngaySinh, row, 6);
                tblModelVeXinChieuVe.setValueAt(doiTuong, row, 7);
                tblModelVeXinChieuVe.setValueAt(khuyenMaiStr, row, 9);
                tblModelVeXinChieuVe.setValueAt(thanhTien, row, 10);
            }
        }

        // Kiểm tra nếu row là dòng đầu tiên thì cập nhật thông tin người mua
        if (row == 0) {
            txtTenNguoiMua.setText(tenKh);
            txtCCCDNguoiMua.setText(cccd);
            txtSDTNguoiMua.setText(sdt);
            txtEmailNguoiMua.setText(email);
            txtNgaySinhNguoiMua.setText(ngaySinh);
            txtDoiTuongNguoiMua.setText(doiTuong);
        }

        capNhatThanhToan();
    }

    /**
     * Validate hóa đơn
     *
     * @return True nếu hợp lệ, ngược lại false
     */
    public boolean validateHoaDon() {
        for (int i = 0; i < tblModelVeXinChieuDi.getRowCount(); i++) {
            String tenKh = tblModelVeXinChieuDi.getValueAt(i, 2).toString();
            String cccd = tblModelVeXinChieuDi.getValueAt(i, 3).toString();
            String sdt = tblModelVeXinChieuDi.getValueAt(i, 4).toString();
            String email = tblModelVeXinChieuDi.getValueAt(i, 5).toString();
            String ngaySinh = tblModelVeXinChieuDi.getValueAt(i, 6).toString();
            String doiTuong = tblModelVeXinChieuDi.getValueAt(i, 7).toString();
            String khuyenMai = tblModelVeXinChieuDi.getValueAt(i, 9).toString();

            if (tenKh.isEmpty() || sdt.isEmpty() || doiTuong.isEmpty() || khuyenMai.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (!cccd.trim().isEmpty()) {
                if (!Validation.CCCD(cccd)) {
                    JOptionPane.showMessageDialog(this, "CCCD không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }

            if (!Validation.sdt(sdt)) {
                JOptionPane.showMessageDialog(this, "SĐT không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (!email.trim().isEmpty()) {
                if (!Validation.email(email)) {
                    JOptionPane.showMessageDialog(this, "Email không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }

            if (!ngaySinh.trim().isEmpty()) {
                try {
                    FormatDate.formatStrToLocalDate(ngaySinh);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }

            if (cccd.trim().isEmpty() && ngaySinh.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập CCCD hoặc ngày sinh", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        return true;
    }

    /**
     * Lấy danh sách khách hàng từ bảng
     *
     * @return Danh sách khách hàng
     */
    public List<KhachHang> getDanhSachKhachHangTuBang() {
        List<KhachHang> listKhachHang = new ArrayList<>();

        for (int i = 0; i < tblModelVeXinChieuDi.getRowCount(); i++) {
            String tenKh = tblModelVeXinChieuDi.getValueAt(i, 2).toString();
            String cccd = tblModelVeXinChieuDi.getValueAt(i, 3).toString();
            String sdt = tblModelVeXinChieuDi.getValueAt(i, 4).toString();
            String email = tblModelVeXinChieuDi.getValueAt(i, 5).toString();
            LocalDate ngaySinh = FormatDate.formatStrToLocalDate(tblModelVeXinChieuDi.getValueAt(i, 6).toString());
            String doiTuong = tblModelVeXinChieuDi.getValueAt(i, 7).toString();

            // Kiểm tra thông tin có đầy đủ không
            if (tenKh.isEmpty() || sdt.isEmpty() || doiTuong.isEmpty() || cccd.isEmpty() || email.isEmpty() || ngaySinh == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            KhachHang khachHang = new KhachHang(tenKh, cccd, sdt, email, ngaySinh, doiTuong);
            listKhachHang.add(khachHang);
        }

        return listKhachHang;
    }

    /**
     * Lấy thông tin người mua
     *
     * @return Khách hàng
     */
    public KhachHang getThongTinNguoiMua() {
        KhachHang khachHang = null;

        String tenNguoiMua = txtTenNguoiMua.getText();
        String cccdNguoiMua = txtCCCDNguoiMua.getText();
        String sdtNguoiMua = txtSDTNguoiMua.getText();
        String emailNguoiMua = txtEmailNguoiMua.getText();
        String ngaySinhNguoiMua = txtNgaySinhNguoiMua.getText();
        String doiTuongNguoiMua = txtDoiTuongNguoiMua.getText();

        khachHang = new KhachHang(tenNguoiMua, cccdNguoiMua, sdtNguoiMua, emailNguoiMua,
                FormatDate.formatStrToLocalDate(ngaySinhNguoiMua), doiTuongNguoiMua);

        // Kiểm tra khách hàng đã tồn tại chưa
        if (DAOKhachHang.timKhachHang(cccdNguoiMua, sdtNguoiMua) == null) {
            if (DAOKhachHang.themKhachHang(khachHang)) {
                JOptionPane.showMessageDialog(this, "Lưu người mua thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Lưu người mua thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }

        khachHang = DAOKhachHang.timKhachHang(cccdNguoiMua, sdtNguoiMua);
        return khachHang;
    }

    /**
     * Lưu thông tin khách hàng
     */
    public void luuThongTinCacKhachHang() {
        for (int i = 0; i < tblModelVeXinChieuDi.getRowCount(); i++) {
            String tenKh = tblModelVeXinChieuDi.getValueAt(i, 2).toString();
            String cccd = tblModelVeXinChieuDi.getValueAt(i, 3).toString();
            String sdt = tblModelVeXinChieuDi.getValueAt(i, 4).toString();
            String email = tblModelVeXinChieuDi.getValueAt(i, 5).toString();
            LocalDate ngaySinh = FormatDate.formatStrToLocalDate(tblModelVeXinChieuDi.getValueAt(i, 6).toString());
            String doiTuong = tblModelVeXinChieuDi.getValueAt(i, 7).toString();
            KhachHang khachHang = new KhachHang(tenKh, cccd, sdt, email, ngaySinh, doiTuong);

            // Kiểm tra nếu khách hàng chưa tồn tại thì thêm vào
            if (DAOKhachHang.timKhachHang(cccd, sdt) == null && !khachHang.getCCCD().equals(nguoiMuaVe.getCCCD())) {
                try {
                    DAOKhachHang.themKhachHang(khachHang);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Lưu khách hàng thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            khachHang = DAOKhachHang.timKhachHang(cccd, sdt);
            listKhachHang.add(khachHang);
        }
    }

    /**
     * Lưu thông tin vé
     */
    public void luuThongTinVe() {
        for (int i = 0; i < tblModelVeXinChieuDi.getRowCount(); i++) {
            Ve veChieuDi = null;
            Ve veChieuVe = null;
            KhachHang khachHang = listKhachHang.get(i);
            KhuyenMai khuyenMaiObj = listKhuyenMai.stream().filter(km -> km.getDoiTuong().equals(khachHang.getDoiTuong())).findFirst().orElse(null);

            if (chuyenChieuVe != null) {
                veChieuDi = new Ve(new HoaDon(hoaDon.getMaHD()), new LoaiVe("LV2"), LocalDateTime.now(), listChoNgoiChieuDi.get(i), chuyenChieuDi, khachHang, 10, khuyenMaiObj, ETrangThaiVe.DA_BAN.getTrangThai());
                veChieuVe = new Ve(new HoaDon(hoaDon.getMaHD()), new LoaiVe("LV2"), LocalDateTime.now(), listChoNgoiChieuVe.get(i), chuyenChieuVe, khachHang, 10, khuyenMaiObj, ETrangThaiVe.DA_BAN.getTrangThai());
            } else {
                veChieuDi = new Ve(new HoaDon(hoaDon.getMaHD()), new LoaiVe("LV1"), LocalDateTime.now(), listChoNgoiChieuDi.get(i), chuyenChieuDi, khachHang, 10, khuyenMaiObj, ETrangThaiVe.DA_BAN.getTrangThai());
            }

            try {
                boolean themVeChieuDiCoKhuyenMai = DAOVe.themVeCoKhuyenMai(veChieuDi);
                boolean themVeChieuVeCoKhuyenMai = veChieuVe == null ? true : DAOVe.themVeCoKhuyenMai(veChieuVe);
                if (!themVeChieuDiCoKhuyenMai) {
                    JOptionPane.showMessageDialog(this, "Lưu vé chiều đi thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!themVeChieuVeCoKhuyenMai) {
                    JOptionPane.showMessageDialog(this, "Lưu vé chiều về thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lưu vé thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return;
            }

            listVe.add(veChieuDi);
            pnlParent.getListVeDaBan().add(veChieuDi);
            if (veChieuVe != null) {
                listVe.add(veChieuVe);
                pnlParent.getListVeDaBan().add(veChieuVe);
            }
        }
    }

    /**
     * Lưu hóa đơn
     */
    public void luuHoaDon() {
        // Kiểm tra thông tin hóa đơn
        if (!validateHoaDon()) {
            JOptionPane.showMessageDialog(this, "Thông tin hóa đơn sai!");
            return;
        }

        // Hỏi xác nhận thanh toán
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thanh toán cho khách: " + FormatMoney.format(tinhThanhTien()), "Xác nhận thanh toán", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        // Kiểm tra nếu là đơn tạm thì xóa đơn tạm đi
        if (hoaDon != null && hoaDon.getMaHD().contains("TAM")) {
            try {
                HoaDonTamHandler.xoaHoaDonTam(hoaDon);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Xóa hóa đơn tạm thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        listKhachHang = new ArrayList<>();
        listVe = new ArrayList<>();
        nguoiMuaVe = getThongTinNguoiMua();

        hoaDon = new HoaDon(LocalDateTime.now(), nhanVien, nguoiMuaVe, tblModelVeXinChieuDi.getRowCount(), listVe);

        try {
            // Thêm hóa đơn
            DAOHoaDon.themHoaDon(hoaDon);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lưu hóa đơn thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Lấy hóa đơn vừa thêm
        hoaDon = DAOHoaDon.getHoaDonCuoiCung();

        // Lưu khách hàng
        luuThongTinCacKhachHang();

        // Lưu thông tin các vé
        luuThongTinVe();

        // Cập nhật danh sách vé
        listVe = DAOVe.layDSVeDaBanTheoMaHD(hoaDon.getMaHD());
        hoaDon.setDanhSachVe(listVe);

        // Xóa thông tin vé
        JOptionPane.showMessageDialog(this, "Đặt vé thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        pnlParent.clearTblGioVe();
        pnlParent.updateHoaDonTam();
        btnInVe.setEnabled(true);
        btnThanhToan.setEnabled(false);
        btnLuuTam.setEnabled(false);

        // Gửi email tới khách hàng
        guiEmailToiKhachHang();
    }

    /**
     * Gửi email tới khách hàng
     */
    public void guiEmailToiKhachHang() {
        // Tạo một SwingWorker để xử lý việc gửi email trong nền
        SwingWorker<Void, Void> emailWorker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Lấy danh sách vé theo hóa đơn
                ArrayList<Ve> listVe = DAOVe.layDSVeDaBanTheoMaHD(hoaDon.getMaHD());

                // Gửi email từng vé
                listVe.forEach(EmailService::sendTicketByEmail);

                // Gửi email hóa đơn
                EmailService.sendBillByEmail(hoaDon);

                return null;
            }

            @Override
            protected void done() {

            }
        };

        // Thực thi luồng
        emailWorker.execute();
    }

    /**
     * Lấy thông tin người mua từ field
     *
     * @return Khách hàng
     */
    public KhachHang getThongTinNguoiMuaTuField() {
        String tenNguoiMua = txtTenNguoiMua.getText();
        String cccdNguoiMua = txtCCCDNguoiMua.getText();
        String sdtNguoiMua = txtSDTNguoiMua.getText();
        String emailNguoiMua = txtEmailNguoiMua.getText();
        String ngaySinhNguoiMua = txtNgaySinhNguoiMua.getText();
        String doiTuongNguoiMua = txtDoiTuongNguoiMua.getText();

        KhachHang khachHang = new KhachHang(tenNguoiMua, cccdNguoiMua, sdtNguoiMua, emailNguoiMua,
                FormatDate.formatStrToLocalDate(ngaySinhNguoiMua), doiTuongNguoiMua);

        return khachHang;
    }

    /**
     * Lưu tạm hóa đơn
     */
    public void luuTam() {
        // Kiểm tra thông tin hóa đơn
        if (!validateHoaDon()) {
            JOptionPane.showMessageDialog(this, "Thông tin hóa đơn sai!");
            return;
        }

        // Hỏi xác nhận lưu tạm
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận lưu tạm", "Xác nhận lưu tạm", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        // Lưu thông tin người mua
        listKhachHang = new ArrayList<>();
        listVe = new ArrayList<>();
        nguoiMuaVe = getThongTinNguoiMuaTuField();

        String maHoaDonTam = GenerateBillTempId.generateBillTempId();
        hoaDon = new HoaDon(maHoaDonTam, LocalDateTime.now(), nhanVien, nguoiMuaVe, tblModelVeXinChieuDi.getRowCount(), listVe);

        for (int i = 0; i < tblModelVeXinChieuDi.getRowCount(); i++) {
            KhachHang khachHang = new KhachHang(
                    tblModelVeXinChieuDi.getValueAt(i, 2).toString(),
                    tblModelVeXinChieuDi.getValueAt(i, 3).toString(),
                    tblModelVeXinChieuDi.getValueAt(i, 4).toString(),
                    tblModelVeXinChieuDi.getValueAt(i, 5).toString(),
                    FormatDate.formatStrToLocalDate(tblModelVeXinChieuDi.getValueAt(i, 6).toString()),
                    tblModelVeXinChieuDi.getValueAt(i, 7).toString()
            );

            listKhachHang.add(khachHang);

            Ve veChieuDi = null;
            Ve veChieuVe = null;
            KhuyenMai khuyenMaiObj = listKhuyenMai.stream().filter(km -> km.getDoiTuong().equals(khachHang.getDoiTuong())).findFirst().orElse(null);

            if (chuyenChieuVe != null) {
                veChieuDi = new Ve(new HoaDon(hoaDon.getMaHD()), new LoaiVe("LV2"), LocalDateTime.now(), listChoNgoiChieuDi.get(i), chuyenChieuDi, khachHang, 0.1, khuyenMaiObj, ETrangThaiVe.DA_BAN.getTrangThai());
                veChieuVe = new Ve(new HoaDon(hoaDon.getMaHD()), new LoaiVe("LV2"), LocalDateTime.now(), listChoNgoiChieuVe.get(i), chuyenChieuVe, khachHang, 0.1, khuyenMaiObj, ETrangThaiVe.DA_BAN.getTrangThai());
            } else {
                veChieuDi = new Ve(new HoaDon(hoaDon.getMaHD()), new LoaiVe("LV1"), LocalDateTime.now(), listChoNgoiChieuDi.get(i), chuyenChieuDi, khachHang, 0.1, khuyenMaiObj, ETrangThaiVe.DA_BAN.getTrangThai());
            }

            listVe.add(veChieuDi);
            if (veChieuVe != null) {
                listVe.add(veChieuVe);
            }
        }

        // Lưu thông tin hóa đơn
        hoaDon = new HoaDon(maHoaDonTam, LocalDateTime.now(), nhanVien, nguoiMuaVe, tblModelVeXinChieuDi.getRowCount(), listVe);

        HoaDonTamHandler.luuHoaDonTam(hoaDon);

        JOptionPane.showMessageDialog(this, "Lưu tạm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        pnlParent.getListHoaDonTam().add(hoaDon);
        pnlParent.clearTblGioVe();
        pnlParent.updateHoaDonTam();
        btnLuuTam.setEnabled(false);
    }

    /**
     * In vé
     */
    public void inVe() {
        if (hoaDon == null) {
            JOptionPane.showMessageDialog(this, "Chưa có thông tin hóa đơn", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ArrayList<Ve> listVe = DAOVe.layDSVeDaBanTheoMaHD(hoaDon.getMaHD());
        if (listVe == null || listVe.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa có vé", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        listVe.forEach(ve -> {
            TicketPrinter printer = new TicketPrinter(ve);
            printer.printTicket();
        });

        // Nếu check xuất hóa đơn thì in hóa đơn
        if (chkXuatHoaDon.isSelected()) {
            inHoaDon();
        }
    }

    /**
     * In hóa đơn
     */
    public void inHoaDon() {
        if (hoaDon == null) {
            JOptionPane.showMessageDialog(this, "Chưa có thông tin hóa đơn", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }

        BillPrinter printer = new BillPrinter(hoaDon);
    }

    /**
     * Validate thông tin khách hàng
     *
     * @return True nếu hợp lệ, ngược lại false
     */
    public boolean validateInput() {
        String tenKh = txtTenKh.getText();
        String cccd = txtCCCD.getText();
        String sdt = txtSDT.getText();
        String email = txtEmail.getText();
        String ngaySinh = txtNgaySinh.getText();
        String doiTuong = cboDoiTuong.getSelectedItem().toString();
        String khuyenMai = txtKhuyenMai.getText();

        if (tenKh.isEmpty() || sdt.isEmpty() || doiTuong.isEmpty() || khuyenMai.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!cccd.trim().isEmpty()) {
            if (!Validation.CCCD(cccd)) {
                JOptionPane.showMessageDialog(this, "CCCD không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        if (!Validation.sdt(sdt)) {
            JOptionPane.showMessageDialog(this, "SĐT không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!email.trim().isEmpty()) {
            if (!Validation.email(email)) {
                JOptionPane.showMessageDialog(this, "Email không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        if (!ngaySinh.trim().isEmpty()) {
            try {
                FormatDate.formatStrToLocalDate(ngaySinh);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        if (cccd.trim().isEmpty() && ngaySinh.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập CCCD hoặc ngày sinh", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    /**
     * Cho phép chỉnh sửa thông tin khách hàng
     */
    public void choPhepEdit() {
        txtTenKh.setEditable(true);
        txtCCCD.setEditable(true);
        txtSDT.setEditable(true);
        txtEmail.setEditable(true);
        txtNgaySinh.setEditable(true);
        cboDoiTuong.setEnabled(true);
    }

    /**
     * Không cho phép chỉnh sửa thông tin khách hàng
     */
    public void xoaTrang() {
        txtTimKh.setText("");
        txtTenKh.setText("");
        txtCCCD.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        txtNgaySinh.setText("");
        cboDoiTuong.setSelectedIndex(0);
        txtKhuyenMai.setText("0%");

        txtTenKh.setEditable(true);
        txtCCCD.setEditable(true);
        txtSDT.setEditable(true);
        txtEmail.setEditable(true);
        txtNgaySinh.setEditable(true);
        cboDoiTuong.setEnabled(true);
    }

    /**
     * Cập nhật người đặt
     */
    public void capNhatNguoiDat() {
        int row = tblVeXinChieuDi.getSelectedRow();
        if (row >= 0) {
            txtTenNguoiMua.setText(tblModelVeXinChieuDi.getValueAt(row, 2).toString());
            txtCCCDNguoiMua.setText(tblModelVeXinChieuDi.getValueAt(row, 3).toString());
            txtSDTNguoiMua.setText(tblModelVeXinChieuDi.getValueAt(row, 4).toString());
            txtEmailNguoiMua.setText(tblModelVeXinChieuDi.getValueAt(row, 5).toString());
            txtNgaySinhNguoiMua.setText(tblModelVeXinChieuDi.getValueAt(row, 6).toString());
            txtDoiTuongNguoiMua.setText(tblModelVeXinChieuDi.getValueAt(row, 7).toString());
        }
    }

    /**
     * Đọc thông tin từ bảng ra field
     */
    public void docTuBangRaField() {
        int row = tblVeXinChieuDi.getSelectedRow();
        if (row >= 0) {
            txtTimKh.setText("");
            txtTenKh.setText(tblModelVeXinChieuDi.getValueAt(row, 2).toString());
            txtCCCD.setText(tblModelVeXinChieuDi.getValueAt(row, 3).toString());
            txtSDT.setText(tblModelVeXinChieuDi.getValueAt(row, 4).toString());
            txtEmail.setText(tblModelVeXinChieuDi.getValueAt(row, 5).toString());
            txtNgaySinh.setText(tblModelVeXinChieuDi.getValueAt(row, 6).toString());
            cboDoiTuong.setSelectedItem(tblModelVeXinChieuDi.getValueAt(row, 7).toString());
            txtKhuyenMai.setText(tblModelVeXinChieuDi.getValueAt(row, 9).toString());

            choPhepEdit();
        }
    }

    /**
     * Xử lý sự kiện click chuột
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        Object obj = e.getSource();

        if (obj.equals(tblVeXinChieuDi) || obj.equals(tblVeXinChieuVe)) {
            docTuBangRaField();
        }
    }

    /**
     * Xử lý sự kiện nhấn phím
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Xử lý sự kiện nhả chuột
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Xử lý sự kiện nhấn chuột
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Xử lý sự kiện rời chuột
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
