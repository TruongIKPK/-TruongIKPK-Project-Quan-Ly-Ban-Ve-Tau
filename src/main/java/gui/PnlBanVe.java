package gui;

import control.*;
import entity.*;
import enums.*;
import gui.components.DatePicker;
import gui.custom.*;
import org.jdatepicker.impl.JDatePickerImpl;
import utils.FormatDate;
import utils.FormatMoney;
import utils.HoaDonTamHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class PnlBanVe extends JPanel implements ActionListener, KeyListener {
    private NhanVien nhanVien;

    private JPanel pnlLeft;
    private JPanel pnlCenter;
    private JPanel pnlRight;
    private JPanel pnlDanhSachChoNgoi;
    private JPanel pnlGioVe;
    private JPanel pnlDanhSachChuyenTau;
    private JPanel pnlDanhSachToa;
    private JPanel pnlThongTinToa;
    private JPanel pnlLocTheoLoaiToa;

    private JLabel lblNgayDi;
    private JLabel lblNgayVe;
    private JLabel lblGaDi;
    private JLabel lblGaDen;
    private JLabel lblTongTienVe;
    private JLabel lblSoVe;
    private JLabel lblSoChuyenHienThi;
    private JLabel lblLoaiToa;
    private JLabel lblDsChuyenTau;
    private JLabel lblSapXepChuyen;

    private CTextField txtNgayVe;

    private JComboBox<String> cboGaDi;
    private JComboBox<String> cboGaDen;
    private JComboBox<String> cboSapXepChuyen;

    private JRadioButton rdoMotChieu;
    private JRadioButton rdoKhuHoi;

    private CButton btnTraCuuChuyenTau;
    private CButton btnTraChuyenSapChay;
    private CButton btnXinVe;
    private CButton btnChonNhanhTheoLoaiToa;
    private CButton btnTuDongChonCho;
    private CButton btnChonCaToa;
    private CButton btnBoChonCaToa;
    private CButton btnChieuDi;
    private CButton btnChieuVe;
    private CButton btnXuLyDonTam;
    private CButton btnXoaDonTam;
    private CButton btnTruVe;

    private JComboBox<String> cboLoaiToa;

    private ArrayList<ChuyenTau> listChuyenDi;
    private ArrayList<ChuyenTau> listChuyenVe;
    private ArrayList<ChoNgoi> listChoNgoi;
    private ArrayList<ChoNgoi> listChoNgoiDaChonChieuDi;
    private ArrayList<ChoNgoi> listChoNgoiDaChonChieuVe;
    private ArrayList<Ve> listVeDaBan;
    private ArrayList<Ga> listGa;
    private ArrayList<LoaiToa> listLoaiToa;
    private ArrayList<HoaDon> listHoaDonTam;

    private String loaiVe;

    private DefaultTableModel tblModelGioVeChieuDi;
    private DefaultTableModel tblModelGioVeChieuVe;
    private DefaultTableModel tblModelHoaDonTam;

    private CTable tblGioVeChieuDi;
    private CTable tblGioVeChieuVe;
    private CTable tblHoaDonTam;

    private ChuyenTau chuyenDangChon;
    private ChuyenTau chuyenDangChonChieuDi;
    private ChuyenTau chuyenDangChonChieuVe;

    private Tau tauDangChon;
    private Toa toaDangChon;

    private JDatePickerImpl ngayDiPicker;
    private JDatePickerImpl ngayVePicker;

    /**
     * Khởi tạo giao diện Panel Bán vé.
     *
     * @param nhanVien Nhân viên đăng nhập.
     */
    public PnlBanVe(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
        this.loaiVe = ELoaiVe.MOT_CHIEU.getValue();

        setLayout(new BorderLayout());
        setBackground(EColor.BG_COLOR.getColor());

        setFocusable(true);
        requestFocusInWindow();

        initKeyBindings();
        initComponents();
        initTable();
        initPnlLeft();
        initPnlCenter();
        initPnlRight();

        add(pnlLeft, BorderLayout.WEST);
        add(pnlCenter, BorderLayout.CENTER);
        add(pnlRight, BorderLayout.EAST);

        readDataFromDB();
        getCacChuyenTauSapKhoiHanh();
        capNhatLuuTamMoiPhut();

    }

    /**
     * Khởi tạo các thành phần giao diện chính.
     */
    public void initComponents() {
        pnlDanhSachChoNgoi = new JPanel();
        pnlThongTinToa = new JPanel();
        pnlLeft = new JPanel();

        lblNgayDi = new JLabel("Ngày đi");
        lblNgayVe = new JLabel("Ngày về");
        lblGaDi = new JLabel("Ga đi");
        lblGaDen = new JLabel("Ga đến");
        lblSoChuyenHienThi = new JLabel("Số chuyến: 0");
        lblTongTienVe = new JLabel("Tổng tiền vé:");
        lblTongTienVe.setFont(new Font("Arial", Font.BOLD, 14));
        lblSoVe = new JLabel("Số vé:");
        lblSoVe.setFont(new Font("Arial", Font.BOLD, 14));
        lblLoaiToa = new JLabel("Loại toa:");
        lblSapXepChuyen = new JLabel("Sắp xếp chuyến:");

        ngayDiPicker = DatePicker.getDatePicker();
        ngayDiPicker.setTextEditable(true);

        ngayVePicker = DatePicker.getDatePicker();
        ngayVePicker.setTextEditable(true);

        txtNgayVe = new CTextField();
        txtNgayVe.setEditable(false);

        cboGaDi = new JComboBox<>();
        cboGaDi.setPreferredSize(new Dimension(150, 25));

        cboGaDen = new JComboBox<>();
        cboGaDen.setPreferredSize(new Dimension(150, 25));

        cboLoaiToa = new JComboBox<>();
        cboLoaiToa.setPreferredSize(new Dimension(220, 30));
        cboLoaiToa.setFont(new Font("Arial", Font.BOLD, 14));
        cboLoaiToa.setBackground(EColor.BG_COLOR.getColor());
        cboLoaiToa.addActionListener(e -> {
            hienThiDanhSachToa();
            hienThiThongTinToa();
        });

        cboSapXepChuyen = new JComboBox<>(new String[]{
                "Mặc định",
                "Thời gian tăng dần",
                "Thời gian giảm dần",
        });
        cboSapXepChuyen.setPreferredSize(new Dimension(220, 30));
        cboSapXepChuyen.setFont(new Font("Arial", Font.BOLD, 14));
        cboSapXepChuyen.setBackground(EColor.BG_COLOR.getColor());
        cboSapXepChuyen.addActionListener(e -> {
            sapXepChuyen();
        });

        rdoMotChieu = new JRadioButton("Một chiều");
        rdoMotChieu.setBackground(EColor.BG_COLOR.getColor());
        rdoKhuHoi = new JRadioButton("Khứ hồi");
        rdoKhuHoi.setBackground(EColor.BG_COLOR.getColor());
        rdoMotChieu.setSelected(true);

        ButtonGroup bgLoaiVe = new ButtonGroup();
        bgLoaiVe.add(rdoMotChieu);
        bgLoaiVe.add(rdoKhuHoi);

        btnTraCuuChuyenTau = new CButton("Tra cứu", CButton.PRIMARY);
        btnTraCuuChuyenTau.addActionListener(this);
        btnTraCuuChuyenTau.setIcon(new CImage("images/icons/search.png", 16, 16));

        btnTraChuyenSapChay = new CButton("Chuyến sắp chạy", CButton.SECONDARY);
        btnTraChuyenSapChay.addActionListener(this);

        btnTuDongChonCho = new CButton("Tự động (F2)", CButton.PRIMARY);
        btnTuDongChonCho.addActionListener(this);
        btnTuDongChonCho.setPreferredSize(new Dimension(100, 20));
        btnTuDongChonCho.setFont(new Font("Arial", Font.BOLD, 12));

        btnChonNhanhTheoLoaiToa = new CButton("Chọn theo loại (F3)", CButton.SECONDARY);
        btnChonNhanhTheoLoaiToa.addActionListener(this);

        btnXinVe = new CButton("Xin vé (F1)");
        btnXinVe.setPreferredSize(new Dimension(120, 40));
        btnXinVe.setIcon(new CImage("images/icons/ticket.png", 20, 20));
        btnXinVe.addActionListener(this);
        btnChonCaToa = new CButton("Chọn cả toa (F4)", CButton.SECONDARY);
        btnChonCaToa.setPreferredSize(new Dimension(100, 20));
        btnChonCaToa.setFont(new Font("Arial", Font.BOLD, 12));
        btnChonCaToa.addActionListener(this);

        btnBoChonCaToa = new CButton("Bỏ chọn (F6)", CButton.SECONDARY);
        btnBoChonCaToa.setBackground(EColor.WARNING_COLOR.getColor());
        btnBoChonCaToa.setPreferredSize(new Dimension(100, 20));
        btnBoChonCaToa.setFont(new Font("Arial", Font.BOLD, 12));
        btnBoChonCaToa.addActionListener(this);

        btnTruVe = new CButton("Trừ vé (DEL)", CButton.SECONDARY);
        btnTruVe.setIcon(new CImage("images/icons/delete.png", 18, 18));
        btnTruVe.setFont(new Font("Arial", Font.BOLD, 12));
        btnTruVe.addActionListener(this);

        btnChieuDi = new CButton("Chiều đi", CButton.SECONDARY);
        btnChieuDi.setPreferredSize(new Dimension(100, 20));
        btnChieuDi.setFont(new Font("Arial", Font.BOLD, 14));
        btnChieuDi.addActionListener(this);

        btnChieuVe = new CButton("Chiều về", CButton.SECONDARY);
        btnChieuVe.setPreferredSize(new Dimension(100, 20));
        btnChieuVe.setFont(new Font("Arial", Font.BOLD, 14));
        btnChieuVe.addActionListener(this);
        btnChieuVe.setEnabled(false);

        btnXuLyDonTam = new CButton("Xử lý (F5)", CButton.PRIMARY);
        btnXuLyDonTam.setPreferredSize(new Dimension(0, 30));
        btnXuLyDonTam.setFont(new Font("Arial", Font.BOLD, 14));
        btnXuLyDonTam.addActionListener(this);

        btnXoaDonTam = new CButton("Xóa", CButton.SECONDARY);
        btnXoaDonTam.setPreferredSize(new Dimension(0, 30));
        btnXoaDonTam.setFont(new Font("Arial", Font.BOLD, 14));
        btnXoaDonTam.addActionListener(this);
    }

    /**
     * Khởi tạo giao diện phần bên trái của panel.
     */
    public void initPnlLeft() {
        // Thiết lập layout và màu nền cho panel bên trái
        pnlLeft.setLayout(new BorderLayout());
        pnlLeft.setBackground(EColor.BG_COLOR.getColor());
        pnlLeft.setPreferredSize(new Dimension(350, 0));  // Thiết lập kích thước ưa thích cho panel
        pnlLeft.setBorder(BorderFactory.createLineBorder(EColor.BORDER_COLOR.getColor()));  // Thiết lập viền cho panel

        // Tạo panel bên trên của panel bên trái
        JPanel pnlLeftTop = new JPanel();
        pnlLeftTop.setBackground(EColor.BG_COLOR.getColor());
        pnlLeftTop.setLayout(new BorderLayout());

        // Tạo và thiết lập các thành phần cho ngày đi và ngày về
        Box boxNgayDi = createHorizontalBoxWithComponents(lblNgayDi, ngayDiPicker);
        Box boxNgayVe = createHorizontalBoxWithComponents(lblNgayVe, txtNgayVe);

        // Thêm listener cho các nút radio một chiều và khứ hồi
        rdoMotChieu.addActionListener(e -> {
            // Nếu chọn "Một chiều", ẩn picker ngày về và sử dụng text field
            boxNgayVe.remove(ngayVePicker);
            boxNgayVe.add(txtNgayVe);
            txtNgayVe.setEditable(false);  // Ngày về không thể chỉnh sửa
            txtNgayVe.setPreferredSize(ngayDiPicker.getPreferredSize());  // Đảm bảo kích thước của ngày về giống ngày đi
            boxNgayVe.revalidate();
            boxNgayVe.repaint();
        });

        rdoKhuHoi.addActionListener(e -> {
            // Nếu chọn "Khứ hồi", ẩn text field và sử dụng picker cho ngày về
            boxNgayVe.remove(txtNgayVe);
            boxNgayVe.add(ngayVePicker);
            txtNgayVe.setEditable(true);  // Cho phép chỉnh sửa ngày về
            ngayVePicker.setPreferredSize(ngayDiPicker.getPreferredSize());  // Đảm bảo kích thước của picker giống ngày đi
            boxNgayVe.revalidate();
            boxNgayVe.repaint();
        });

        // Tạo các hộp chứa ga đi và ga đến
        Box boxGaDi = createHorizontalBoxWithComponents(lblGaDi, cboGaDi);
        Box boxGaDen = createHorizontalBoxWithComponents(lblGaDen, cboGaDen);

        // Đảm bảo các nhãn có kích thước giống nhau
        lblNgayDi.setPreferredSize(lblNgayVe.getPreferredSize());
        lblGaDi.setPreferredSize(lblNgayVe.getPreferredSize());
        lblGaDen.setPreferredSize(lblNgayVe.getPreferredSize());

        // Tạo hộp chứa các lựa chọn về loại vé và chức năng tìm chuyến
        Box boxLoaiVe = createHorizontalBoxWithComponents(rdoMotChieu, rdoKhuHoi);
        Box boxChucNang = createHorizontalBoxWithComponents(btnTraCuuChuyenTau, btnTraChuyenSapChay);

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Tra cứu chuyến tàu");
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 14)); // Đặt font cho tiêu đề

        // Tạo hộp chứa các thành phần chính ở panel bên trái
        Box boxLeftTop = createVerticalBoxWithComponents(boxNgayDi, boxNgayVe, boxGaDi, boxGaDen,
                boxLoaiVe, boxChucNang);
        boxLeftTop.setBorder(BorderFactory.createCompoundBorder(
                titledBorder,  // Tiêu đề cho phần tìm chuyến tàu
                BorderFactory.createEmptyBorder(10, 10, 10, 10)  // Khoảng cách xung quanh
        ));

        pnlLeftTop.add(boxLeftTop, BorderLayout.NORTH);  // Thêm vào phần trên của panel bên trái

        TitledBorder titledBorderHdTam = BorderFactory.createTitledBorder("Danh sách hóa đơn tạm");
        titledBorderHdTam.setTitleFont(new Font("Arial", Font.BOLD, 14)); // Đặt font cho tiêu đề

        // Tạo panel dưới của panel bên trái cho phần tìm vé gần nhất
        JPanel pnlLeftBot = new JPanel();
        pnlLeftBot.setBackground(EColor.BG_COLOR.getColor());
        pnlLeftBot.setLayout(new BoxLayout(pnlLeftBot, BoxLayout.Y_AXIS));  // Layout dọc cho panel dưới
        pnlLeftBot.setBorder(BorderFactory.createCompoundBorder(
                titledBorderHdTam,  // Tiêu đề cho phần tìm vé gần nhất
                BorderFactory.createEmptyBorder(10, 10, 10, 10)  // Khoảng cách xung quanh
        ));

        Box boxXuLyDonTam = createHorizontalBoxWithComponents(btnXuLyDonTam, btnXoaDonTam);

        pnlLeftBot.add(new JScrollPane(tblHoaDonTam));  // Thêm bảng vé gần nhất vào panel dưới
        pnlLeftBot.add(Box.createVerticalStrut(10));  // Khoảng cách giữa bảng và các nút chức năng
        pnlLeftBot.add(boxXuLyDonTam);

        pnlLeft.add(pnlLeftTop, BorderLayout.NORTH);  // Thêm panel trên vào panel bên trái
        pnlLeft.add(pnlLeftBot, BorderLayout.CENTER);  // Thêm panel dưới vào panel bên trái
    }

    /**
     * Khởi tạo giao diện phần trung tâm của panel.
     */
    public void initPnlCenter() {
        // Tạo và thiết lập panel trung tâm
        pnlCenter = new JPanel();
        pnlCenter.setLayout(new BorderLayout());
        pnlCenter.setBackground(EColor.BG_COLOR.getColor());

        // Tạo panel danh sách chuyến tàu
        pnlDanhSachChuyenTau = new JPanel();
        pnlDanhSachChuyenTau.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlDanhSachChuyenTau.setBackground(EColor.BG_COLOR.getColor());

        // Tạo panel thông tin toa
        pnlThongTinToa.setLayout(new BorderLayout());
        pnlThongTinToa.setBackground(EColor.BG_COLOR.getColor());

        // Tạo panel lọc toa
        pnlLocTheoLoaiToa = new JPanel();
        pnlLocTheoLoaiToa.setLayout(new BorderLayout());
        pnlLocTheoLoaiToa.setBackground(EColor.BG_COLOR.getColor());
        pnlLocTheoLoaiToa.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlLocTheoLoaiToa.add(createHorizontalBoxWithComponents(lblSapXepChuyen, cboSapXepChuyen), BorderLayout.WEST);
        pnlLocTheoLoaiToa.add(createHorizontalBoxWithComponents(lblLoaiToa, cboLoaiToa, btnChonNhanhTheoLoaiToa), BorderLayout.EAST);

        // Đặt layout cho panel danh sách toa
        pnlDanhSachToa = new JPanel();
        pnlDanhSachToa.setBackground(EColor.BG_COLOR.getColor());
        pnlDanhSachToa.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Tạo panel danh sách chỗ ngồi
        pnlDanhSachChoNgoi.setLayout(new BoxLayout(pnlDanhSachChoNgoi, BoxLayout.X_AXIS));
        pnlDanhSachChoNgoi.setBackground(EColor.BG_COLOR.getColor());
        pnlDanhSachChoNgoi.setPreferredSize(new Dimension(500, 170)); // Thiết lập kích thước cho danh sách chỗ ngồi

        // Thêm thanh cuộn ngang cho danh sách chuyến tàu
        JScrollPane scrDsChuyenTau = new JScrollPane(pnlDanhSachChuyenTau);
        scrDsChuyenTau.setBorder(BorderFactory.createEmptyBorder());
        scrDsChuyenTau.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Ẩn thanh cuộn ngang

        // Tăng tốc độ cuộn ngang
        scrDsChuyenTau.getHorizontalScrollBar().setUnitIncrement(16);

        // Thêm sự kiện cho cuộn chuột ngang khi giữ phím Shift
        scrDsChuyenTau.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                // Kiểm tra nếu phím Shift được giữ, thì cuộn ngang
                JScrollBar horizontalScrollBar = scrDsChuyenTau.getHorizontalScrollBar();
                int scrollAmount = e.getWheelRotation() * horizontalScrollBar.getUnitIncrement();
                horizontalScrollBar.setValue(horizontalScrollBar.getValue() + scrollAmount);
            }
        });

        pnlThongTinToa.add(pnlDanhSachChoNgoi, BorderLayout.CENTER);  // Thêm danh sách chỗ ngồi vào thông tin toa

        // Tạo box chứa danh sách chuyến tàu
        Box boxDsChuyenTau = Box.createHorizontalBox();
        boxDsChuyenTau.add(Box.createHorizontalStrut(10));  // Khoảng cách giữa các thành phần
        boxDsChuyenTau.add(scrDsChuyenTau);
        boxDsChuyenTau.add(Box.createHorizontalStrut(10));  // Khoảng cách giữa các thành phần

        // Tạo box chứa phần đầu của panel trung tâm
        Box boxCenterTop = Box.createVerticalBox();
        boxCenterTop.setBackground(EColor.BG_COLOR.getColor());

        // Tạo panel tiêu đề
        JPanel pnlTitle = new JPanel();
        pnlTitle.setLayout(new BorderLayout());
        pnlTitle.setBackground(EColor.BG_COLOR.getColor());
        pnlTitle.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Thiết lập nhãn cho danh sách chuyến tàu
        lblDsChuyenTau = new JLabel("Danh sách chuyến tàu");
        lblDsChuyenTau.setHorizontalAlignment(SwingConstants.CENTER);
        lblDsChuyenTau.setFont(new Font("Arial", Font.BOLD, 18));

        // Thiết lập các nút chuyến đi và chuyến về
        btnChieuDi.setBackground(EColor.BTN_BG_COLOR.getColor());

        // Tạo box chứa các nút chuyến đi và chuyến về
        Box boxChieu = Box.createHorizontalBox();
        boxChieu.add(btnChieuDi);
        boxChieu.add(Box.createHorizontalStrut(10));
        boxChieu.add(btnChieuVe);
        boxChieu.setPreferredSize(new Dimension(200, 30));

        // Tạo panel hiển thị số chuyến
        JPanel pnlSoChuyen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblSoChuyenHienThi.setFont(new Font("Arial", Font.BOLD, 14));
        pnlSoChuyen.add(lblSoChuyenHienThi);
        pnlSoChuyen.setBackground(EColor.BG_COLOR.getColor());
        pnlSoChuyen.setPreferredSize(new Dimension(200, 30));

        pnlTitle.add(boxChieu, BorderLayout.WEST);
        pnlTitle.add(lblDsChuyenTau, BorderLayout.CENTER);
        pnlTitle.add(pnlSoChuyen, BorderLayout.EAST);

        // Thêm các thành phần vào box trung tâm
        boxCenterTop.add(pnlTitle);
        boxCenterTop.add(Box.createVerticalStrut(10));  // Khoảng cách giữa các phần
        boxCenterTop.add(scrDsChuyenTau);
        boxCenterTop.add(Box.createVerticalStrut(10));  // Khoảng cách giữa các phần
        boxCenterTop.add(pnlLocTheoLoaiToa);
        boxCenterTop.add(pnlDanhSachToa);  // Thêm danh sách toa
        boxCenterTop.add(Box.createVerticalStrut(20));  // Khoảng cách dưới cùng

        // Thêm phần đầu vào panel trung tâm và thông tin toa vào panel dưới
        pnlCenter.add(boxCenterTop, BorderLayout.NORTH);
        pnlCenter.add(pnlThongTinToa, BorderLayout.SOUTH);
    }

    /**
     * Khởi tạo giao diện phần bên phải của panel.
     */
    public void initPnlRight() {
        // Khởi tạo panel bên phải và thiết lập thuộc tính
        pnlRight = new JPanel();
        pnlRight.setLayout(new BorderLayout());
        pnlRight.setPreferredSize(new Dimension(250, 0));
        pnlRight.setBackground(EColor.BG_COLOR.getColor());
        pnlRight.setBorder(BorderFactory.createLineBorder(EColor.BORDER_COLOR.getColor()));

        // Khởi tạo panel Giỏ vé và thiết lập thuộc tính
        pnlGioVe = new JPanel();
        pnlGioVe.setLayout(new BoxLayout(pnlGioVe, BoxLayout.Y_AXIS));
        pnlGioVe.setPreferredSize(new Dimension(150, 500));
        pnlGioVe.setBackground(EColor.BG_COLOR.getColor());

        // Tạo panel Giỏ vé chiều đi
        TitledBorder titledBorderGioVeDi = BorderFactory.createTitledBorder("Giỏ vé chiều đi");
        titledBorderGioVeDi.setTitleFont(new Font("Arial", Font.BOLD, 14));

        JPanel pnlGioVeChieuDi = new JPanel(new BorderLayout());
        pnlGioVeChieuDi.setBorder(titledBorderGioVeDi);
        pnlGioVeChieuDi.setBackground(EColor.BG_COLOR.getColor());
        pnlGioVeChieuDi.add(new JScrollPane(tblGioVeChieuDi));

        // Tạo panel Giỏ vé chiều về
        TitledBorder titledBorderGioVeVe = BorderFactory.createTitledBorder("Giỏ vé chiều về");
        titledBorderGioVeVe.setTitleFont(new Font("Arial", Font.BOLD, 14));

        JPanel pnlGioVeChieuVe = new JPanel(new BorderLayout());
        pnlGioVeChieuVe.setBorder(titledBorderGioVeVe);
        pnlGioVeChieuVe.setBackground(EColor.BG_COLOR.getColor());
        pnlGioVeChieuVe.add(new JScrollPane(tblGioVeChieuVe));

        // Tạo panel Chức năng giỏ vé và thêm các nút chức năng
        JPanel pnlChucNangGioVe = new JPanel();
        pnlChucNangGioVe.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnlChucNangGioVe.setBackground(EColor.BG_COLOR.getColor());
        pnlChucNangGioVe.add(btnTruVe);

        // Thêm các panel Giỏ vé vào panel chính pnlGioVe
        pnlGioVe.add(pnlGioVeChieuDi);
        pnlGioVe.add(pnlGioVeChieuVe);
        pnlGioVe.add(pnlChucNangGioVe);

        // Khởi tạo panel phía dưới bên phải
        JPanel pnlRightBot = new JPanel();
        pnlRightBot.setLayout(new BorderLayout());
        pnlRightBot.setBackground(EColor.BG_COLOR.getColor());

        // Tạo panel Giá vé
        JPanel pnlGiaVe = new JPanel();
        pnlGiaVe.setLayout(new BoxLayout(pnlGiaVe, BoxLayout.Y_AXIS));
        pnlGiaVe.setBackground(EColor.BG_COLOR.getColor());

        // Tạo Box chứa thông tin giá vé
        TitledBorder titledBorderGiaVe = BorderFactory.createTitledBorder("Giá vé");
        titledBorderGiaVe.setTitleFont(new Font("Arial", Font.BOLD, 14));

        Box boxGiaVe = Box.createVerticalBox();
        boxGiaVe.setBorder(BorderFactory.createCompoundBorder(
                titledBorderGiaVe,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        boxGiaVe.setBackground(EColor.BG_COLOR.getColor());
        boxGiaVe.add(lblTongTienVe); // Thêm nhãn tổng tiền vé
        boxGiaVe.add(Box.createVerticalStrut(10)); // Khoảng cách giữa các phần tử
        boxGiaVe.add(lblSoVe); // Thêm nhãn số vé
        boxGiaVe.add(Box.createVerticalStrut(10)); // Khoảng cách giữa các phần tử

        pnlGiaVe.add(boxGiaVe); // Thêm box vào panel Giá vé

        // Thêm panel Giá vé vào panel phía dưới bên phải
        pnlRightBot.add(pnlGiaVe, BorderLayout.NORTH);
        pnlRightBot.add(btnXinVe, BorderLayout.SOUTH); // Thêm nút xin vé vào phía dưới

        // Thêm các panel vào panel chính pnlRight
        pnlRight.add(pnlGioVe, BorderLayout.NORTH); // Thêm giỏ vé vào phía trên
        pnlRight.add(pnlRightBot, BorderLayout.SOUTH); // Thêm phần phía dưới vào panel chính
    }

    /**
     * Thiết lập các phím tắt (Key Bindings) để hỗ trợ thao tác nhanh.
     */
    public void initKeyBindings() {
        // Lấy InputMap và ActionMap của JComponent
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        // Đặt phím tắt cho phím Enter
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enterPressed");
        actionMap.put("enterPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Thực hiện tìm kiếm chuyến tàu khi nhấn Enter
                traCuuChuyenTau();
            }
        });

        // Đặt phím tắt cho phím Ctrl + F để tra cứu
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK), "ctrlFPressed");
        actionMap.put("ctrlFPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Thực hiện tìm kiếm chuyến tàu khi nhấn Ctrl + F
                traCuuChuyenTau();
            }
        });

        // Đặt phím tắt cho phím ESC
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escapePressed");
        actionMap.put("escapePressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Thực hiện reset và hỏi xác nhận
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn reset không?", "Thoát",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    resetAll();
                }
            }
        });

        // Đặt phím F1 - Xin vé
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "f1Pressed");
        actionMap.put("f1Pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Thực hiện xin vé khi nhấn phím F1
                xinVe();
            }
        });

        // Đặt phím F2 - Tự động chọn
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "f2Pressed");
        actionMap.put("f2Pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Thực hiện tự động chọn khi nhấn phím F2
                xuLyTuDongChonCho();
            }
        });

        // Đặt phím F3 - Chọn nhanh theo loại toa
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), "f3Pressed");
        actionMap.put("f3Pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Thực hiện chọn nhanh theo loại toa khi nhấn phím F3
                chonNhanhTheoLoaiToa();
            }
        });

        // Đặt phím F4 - Chọn cả toa
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), "f4Pressed");
        actionMap.put("f4Pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chonCaToa();
            }
        });

        // Đặt phím F5 - Xử lý đơn tạm
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "f5Pressed");
        actionMap.put("f5Pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyDonTam();
            }
        });

        // Đặt phím F6 - Bỏ chọn cả toa
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), "f6Pressed");
        actionMap.put("f6Pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boChonCaToa();
            }
        });

        // Đặt phím trái - Chọn chuyến tàu trước
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "leftPressed");
        actionMap.put("leftPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Chuyển đến chuyến tàu trước khi nhấn phím trái
                chuyenTauTruoc();
            }
        });

        // Đặt phím phải - Chọn chuyến tàu sau
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "rightPressed");
        actionMap.put("rightPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Chuyển đến chuyến tàu sau khi nhấn phím phải
                chuyenTauSau();
            }
        });

        // Đặt phím DEL - Trừ vé
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "deletePressed");
        actionMap.put("deletePressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Trừ vé khi nhấn phím DEL
                truVe();
            }
        });
    }

    /**
     * Thiết lập cấu hình bảng (JTable) hiển thị thông tin vé.
     */
    public void initTable() {
        // Khai báo tên cột cho bảng
        Object[] columnsName = new Object[]{
                "STT",
                "Chỗ ngồi",
                "Loại chỗ",
                "Giá vé"
        };

        // Khởi tạo bảng cho vé chiều đi
        tblModelGioVeChieuDi = new DefaultTableModel(columnsName, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblGioVeChieuDi = new CTable(tblModelGioVeChieuDi);
        tblGioVeChieuDi.setCellEditor(null);
        tblGioVeChieuDi.setFont(new Font("Arial", Font.PLAIN, 12));
        // Cho cột STT nhỏ
        tblGioVeChieuDi.getColumnModel().getColumn(0).setPreferredWidth(40);

        // Khởi tạo bảng cho vé chiều về
        tblModelGioVeChieuVe = new DefaultTableModel(columnsName, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblGioVeChieuVe = new CTable(tblModelGioVeChieuVe);
        tblGioVeChieuVe.setCellEditor(null);
        tblGioVeChieuVe.setFont(new Font("Arial", Font.PLAIN, 12));
        // Cho cột STT nhỏ
        tblGioVeChieuVe.getColumnModel().getColumn(0).setPreferredWidth(40);

        // Khởi tạo bảng cho vé gần nhất
        tblModelHoaDonTam = new DefaultTableModel(new Object[]{
                "STT",
                "Mã HD",
                "Khách hàng",
                "TG Xuất vé",
                "Tổng tiền"
        }, 0);
        tblHoaDonTam = new CTable(tblModelHoaDonTam);
        tblHoaDonTam.setCellEditor(null);
        tblHoaDonTam.setFont(new Font("Arial", Font.PLAIN, 12));
        tblHoaDonTam.getColumnModel().getColumn(0).setPreferredWidth(30);

        // Thêm sự kiện click chuột cho bảng vé gần nhất
        tblHoaDonTam.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Lấy dòng được chọn trong bảng
                int row = tblHoaDonTam.getSelectedRow();
                if (row == -1) return; // Nếu không có dòng nào được chọn thì thoát

                // TODO: Hiển thị thông tin
            }
        });
    }

    /**
     * Lấy danh sách các vé đã bán từ cơ sở dữ liệu.
     *
     * @return Danh sách các vé đã bán.
     */
    public ArrayList<Ve> getListVeDaBan() {
        return listVeDaBan;
    }

    /**
     * Lấy danh sách các hóa đơn tạm.
     *
     * @return Danh sách các hóa đơn tạm.
     */
    public ArrayList<HoaDon> getListHoaDonTam() {
        return listHoaDonTam;
    }

    /**
     * Lấy danh sách vé từ hóa đơn tạm.
     *
     * @return Danh sách vé từ hóa đơn tạm.
     */
    public ArrayList<Ve> getListVeTuHoaDonTam() {
        ArrayList<Ve> listVe = new ArrayList<>();
        for (HoaDon hoaDon : listHoaDonTam) {
            listVe.addAll(hoaDon.getDanhSachVe());
        }
        return listVe;
    }

    /**
     * Lấy danh sách các chuyến tàu sắp khởi hành.
     */
    public void getCacChuyenTauSapKhoiHanh() {
        // Lấy danh sách các chuyến tàu sắp khởi hành
        listChuyenDi = DAOChuyenTau.getDanhSachChuyenTauSapKhoiHanh();
        System.out.println("Số chuyến đi:*********************************************************** " + listChuyenDi);
        // Lặp qua từng chuyến tàu trong danh sách và lấy vé đã bán theo mã chuyến
        for (ChuyenTau chuyenTau : listChuyenDi) {
            listVeDaBan.addAll(DAOVe.layDSVeDaBanTheoMaChuyen(chuyenTau.getMaChuyen()));
        }
        System.out.println(")))))))))))))))))))))))))))))))))))))))))))))))))))))LISTVEDABAN"+listVeDaBan);
        // Hiển thị danh sách chuyến tàu
        hienThiDanhSachChuyenTau("Chiều đi");

        // Cập nhật nhãn hiển thị số lượng chuyến tàu sắp chạy
        lblDsChuyenTau.setText("Danh sách chuyến sắp chạy");
    }

    // Vọòng lặp chạy mỗi 1 phút thì xóa các hóa đơn quá hạn
    public void capNhatLuuTamMoiPhut() {
        Timer timer = new Timer(
                1000 * 60,
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Xóa các hóa đơn tạm quá hạn
                        HoaDonTamHandler.xoaHoaDonTamHetHan();

                        listHoaDonTam = HoaDonTamHandler.getDanhSachHoaDonTam();
                    }
                }
        );
        timer.start();
    }

    /**
     * Đọc dữ liệu từ cơ sở dữ liệu và cập nhật giao diện.
     */
    public void readDataFromDB() {
        // Khởi tạo danh sách
        listChuyenDi = new ArrayList<>();
        listChuyenVe = new ArrayList<>();
        listChoNgoiDaChonChieuDi = new ArrayList<>();
        listChoNgoiDaChonChieuVe = new ArrayList<>();
        listChoNgoi = new ArrayList<>();
        listGa = DAOGa.getDsGa();
        listVeDaBan = new ArrayList<>();
        listLoaiToa = DAOLoaiToa.getDSLoaiToa();
        listHoaDonTam = HoaDonTamHandler.getDanhSachHoaDonTam();

        // Thêm ga vào combobox Ga đi và Ga đến
        for (Ga ga : listGa) {
            cboGaDi.addItem(ga.getTenGa());
            cboGaDen.addItem(ga.getTenGa());
        }

        // Xóa hết các dòng hiện tại trong bảng vé gần nhất
        tblModelHoaDonTam.setRowCount(0);

        // Thêm dữ liệu vào bảng vé gần nhất
        for (int i = 0; i < listHoaDonTam.size(); i++) {
            HoaDon hoaDon = listHoaDonTam.get(i);
            tblModelHoaDonTam.addRow(new Object[]{
                    i + 1,  // Thứ tự
                    hoaDon.getMaHD(),  // Mã hóa đơn
                    hoaDon.getKhachHang().getTenKH(),  // Tên người mua
                    FormatDate.formatLocalDateTimeToHM(hoaDon.getNgayGioLapHD()), // Ngày giờ xuất vé
                    FormatMoney.format(hoaDon.getThanhTien())  // Tổng tiền
            });
        }

        // Thêm "Tất cả" vào combobox loại toa
        cboLoaiToa.addItem("Tất cả");

        // Thêm các loại toa vào combobox loại toa
        listLoaiToa.forEach(loaiToa -> {
            cboLoaiToa.addItem(loaiToa.getTenLT());
        });
    }

    /**
     * Tạo một Box ngang (Horizontal Box) với các thành phần được chỉ định.
     *
     * @param components Các thành phần cần thêm vào Box.
     * @return Box ngang chứa các thành phần.
     */
    public Box createHorizontalBoxWithComponents(JComponent... components) {
        // Tạo Box nằm ngang
        Box box = Box.createHorizontalBox();

        // Duyệt qua tất cả các component để thêm vào Box
        for (int i = 0; i < components.length; i++) {
            // Thêm component vào Box
            box.add(components[i]);

            // Thêm khoảng cách nếu không phải là component cuối cùng
            if (i < components.length - 1) {
                box.add(Box.createHorizontalStrut(10));  // Khoảng cách 10 đơn vị giữa các component
            }
        }

        // Trả về Box đã chứa các component và khoảng cách
        return box;
    }

    /**
     * Tạo một Box dọc (Vertical Box) với các thành phần được chỉ định.
     *
     * @param components Các thành phần cần thêm vào Box.
     * @return Box dọc chứa các thành phần.
     */
    public Box createVerticalBoxWithComponents(JComponent... components) {
        // Tạo Box dọc
        Box box = Box.createVerticalBox();

        // Duyệt qua tất cả các component để thêm vào Box
        for (int i = 0; i < components.length; i++) {
            // Thêm component vào Box
            box.add(components[i]);

            // Thêm khoảng cách nếu không phải là component cuối cùng
            if (i < components.length - 1) {
                box.add(Box.createVerticalStrut(10));  // Khoảng cách 10 đơn vị giữa các component
            }
        }

        // Trả về Box đã chứa các component và khoảng cách
        return box;
    }

    /**
     * Tạo JPanel hiển thị thông tin chuyến tàu.
     *
     * @param chuyenTau Thông tin chuyến tàu cần hiển thị.
     * @return JPanel chứa thông tin chuyến tàu.
     */
    public JPanel getPnlChuyenTau(ChuyenTau chuyenTau) {
        JPanel pnlChuyenTau = new JPanel();
        pnlChuyenTau.setLayout(new BoxLayout(pnlChuyenTau, BoxLayout.Y_AXIS));
        pnlChuyenTau.setBackground(EColor.BG_COLOR.getColor());
        pnlChuyenTau.setPreferredSize(new Dimension(250, 200));



        long soLuongChoTrong = DAOChuyenTau.getTongSoLuongChoCuaChuyen(chuyenTau.getMaChuyen())
                - listVeDaBan.stream().filter(ve -> ve.getChuyenTau().equals(chuyenTau)).count();

        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%SOLUONGCHOTRONG%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+soLuongChoTrong);
        // Tạo Box chứa mác tàu
        JPanel boxMacTau = new JPanel();
        boxMacTau.setBackground(EColor.BG_COLOR.getColor());
        JLabel lblMacTau = new JLabel(chuyenTau.getMacTau());
        lblMacTau.setFont(new Font("Arial", Font.BOLD, 18));
        lblMacTau.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa

        boxMacTau.add(lblMacTau, BorderLayout.CENTER);
        boxMacTau.setBackground(EColor.BG_COLOR.getColor());

        // Tạo Button chuyến tàu
        JPanel pnlTau = new JPanel();
        pnlTau.setBackground(EColor.BG_COLOR.getColor());
        JLabel lblChuyenTau = new JLabel();
        if (soLuongChoTrong > 0) {
            lblChuyenTau.setIcon(new CImage("images/icons/train_2.png", 80, 80)); // Icon cho button
        } else {
            lblChuyenTau.setIcon(new CImage("images/icons/train_2-full.png", 80, 80)); // Icon khi hết chỗ
        }

        lblChuyenTau.setBackground(EColor.BG_COLOR.getColor());
        pnlTau.add(lblChuyenTau, BorderLayout.CENTER);

        // Tạo các label thông tin chuyến tàu
        JLabel lblMaChuyen = new JLabel("Mã chuyến: " + chuyenTau.getMaChuyen());
        JLabel lblNgayDiNgayDen = new JLabel("Thời gian: " + FormatDate.formatLocalDateTimeToHM(chuyenTau.getNgayGioDi())
                + " - " + FormatDate.formatLocalDateTimeToHM(chuyenTau.getNgayGioDen()));
        JLabel lblHanhTrinh = new JLabel("Hành trình: "
                + chuyenTau.getGaDi().getTenGa().replace("Ga Tàu", "").toUpperCase()
                + " - "
                + chuyenTau.getGaDen().getTenGa().replace("Ga Tàu", "").toUpperCase());
        JLabel lblSoLuongChoTrong = new JLabel("Số chỗ trống: " + soLuongChoTrong); // Số lượng chỗ trống

        // Tạo panel chứa các thông tin chuyến tàu
        JPanel pnlThongTin = new JPanel();
        pnlThongTin.setBackground(EColor.BG_COLOR.getColor());
        Box boxThongTinChuyen = Box.createVerticalBox();
        boxThongTinChuyen.add(lblMaChuyen);
        boxThongTinChuyen.add(lblNgayDiNgayDen);
        boxThongTinChuyen.add(lblHanhTrinh);
        boxThongTinChuyen.add(lblSoLuongChoTrong);
        pnlThongTin.add(boxThongTinChuyen);

        // Thêm các thành phần vào panel chính
        pnlChuyenTau.add(boxMacTau);
        pnlChuyenTau.add(pnlTau);
        pnlChuyenTau.add(pnlThongTin);

        // Nếu chuyến tàu được chọn, thay đổi màu nền
        if (chuyenDangChon != null && chuyenDangChon.equals(chuyenTau)) {
            pnlChuyenTau.setBackground(EColor.BTN_ACTIVE_BG_COLOR.getColor());
            boxMacTau.setBackground(EColor.BTN_ACTIVE_BG_COLOR.getColor());
            pnlTau.setBackground(EColor.BTN_ACTIVE_BG_COLOR.getColor());
            pnlThongTin.setBackground(EColor.BTN_ACTIVE_BG_COLOR.getColor());
        }

        pnlChuyenTau.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Nếu số chỗ trống <= 0 thì thông báo chuyến tàu hết chỗ
                if (soLuongChoTrong <= 0) {
                    JOptionPane.showMessageDialog(null, "Chuyến tàu đã hết chỗ trống");
                    return;
                }

                // Nếu chuyến tàu đã chọn thì không làm gì
                if (chuyenDangChon != null && chuyenDangChon.equals(chuyenTau)) return;

                // Nếu đã chọn chuyến
                if (chuyenDangChon != null) {
                    // Kiểm tra nếu 2 chuyến có khác chiều không
                    if (chuyenDangChon.getGaDi().equals(chuyenTau.getGaDi())
                            && chuyenDangChon.getGaDen().equals(chuyenTau.getGaDen())) {
                        // Chuyến đang chọn là chiều đi, chuyến tàu chọn là chiều về
                        chuyenDangChon = chuyenTau;
                        tauDangChon = chuyenDangChon.getTau();
                        toaDangChon = tauDangChon.getDanhSachToa().get(0);

                        hienThiDanhSachChuyenTau("Chiều đi");
                        hienThiDanhSachToa();
                        hienThiThongTinToa();
                    } else {
                        // Nếu trùng chiều thì yêu cầu xác nhận trước khi chuyển chuyến
                        if (listChuyenDi.contains(chuyenTau)) {
                            if (!listChoNgoiDaChonChieuDi.isEmpty()) {
                                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn chuyển chuyến tàu không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                                if (confirm == JOptionPane.YES_OPTION) {
                                    tblModelGioVeChieuDi.setRowCount(0);
                                    listChoNgoiDaChonChieuDi.clear();
                                } else {
                                    return;
                                }
                            }
                        } else {
                            if (!listChoNgoiDaChonChieuVe.isEmpty()) {
                                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn chuyển chuyến tàu không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                                if (confirm == JOptionPane.YES_OPTION) {
                                    tblModelGioVeChieuVe.setRowCount(0);
                                    listChoNgoiDaChonChieuVe.clear();
                                } else {
                                    return;
                                }
                            }
                        }

                        chuyenDangChon = chuyenTau;
                        tauDangChon = chuyenDangChon.getTau();
                        toaDangChon = tauDangChon.getDanhSachToa().get(0);

                        if (listChuyenDi.contains(chuyenDangChon)) {
                            chuyenDangChonChieuDi = chuyenDangChon;
                            hienThiDanhSachChuyenTau("Chiều đi");
                        } else {
                            chuyenDangChonChieuVe = chuyenDangChon;
                            hienThiDanhSachChuyenTau("Chiều về");
                        }

                        hienThiDanhSachToa();
                        hienThiThongTinToa();
                    }
                } else {
                    // Nếu chưa chọn chuyến tàu, thực hiện hiển thị thông tin chuyến
                    chuyenDangChon = chuyenTau;
                    tauDangChon = chuyenDangChon.getTau();
                    toaDangChon = tauDangChon.getDanhSachToa().get(0);

                    if (listChuyenDi.contains(chuyenDangChon)) {
                        chuyenDangChonChieuDi = chuyenDangChon;
                        hienThiDanhSachChuyenTau("Chiều đi");
                    } else {
                        chuyenDangChonChieuVe = chuyenDangChon;
                        hienThiDanhSachChuyenTau("Chiều về");
                    }

                    hienThiDanhSachToa();
                    hienThiThongTinToa();
                }
            }
        });

        return pnlChuyenTau;
    }

    /**
     * Tạo JPanel hiển thị thông tin toa.
     *
     * @param toa     Thông tin toa cần hiển thị.
     * @param soThuTu Số thứ tự của toa.
     * @return JPanel chứa thông tin toa.
     */
    public JPanel getPnlToa(Toa toa, int soThuTu) {
        // Tạo panel chứa thông tin toa
        JPanel pnlToa = new JPanel();
        pnlToa.setLayout(new BoxLayout(pnlToa, BoxLayout.Y_AXIS));

        // Panel chứa biểu tượng chuyến tàu
        JPanel pnlToaIcon = new JPanel();
        JLabel lblChuyenTau = new JLabel();
        pnlToaIcon.setBackground(EColor.BG_COLOR.getColor());

        // Panel chứa thông tin STT của toa
        JPanel pnlStt = new JPanel();
        pnlStt.setLayout(new BorderLayout());
        JLabel lblSTT = new JLabel((soThuTu + 1) + " - " + toa.getLoaiToa().getMaLT());
        lblSTT.setHorizontalAlignment(SwingConstants.CENTER);
        lblSTT.setVerticalAlignment(SwingConstants.CENTER);
        pnlStt.add(lblSTT, BorderLayout.NORTH);
        pnlStt.setBackground(EColor.BG_COLOR.getColor());

        // Lấy loại toa và số lượng chỗ trống
        String loaiToa = cboLoaiToa.getSelectedItem().toString();
        long soLuongChoTrong = getSoLuongChoTrongCuaToa(toa);

        // Kiểm tra và thiết lập biểu tượng cho toa
        if (soLuongChoTrong <= 0) {
            // Toa đầy
            lblChuyenTau.setIcon(new CImage("images/icons/train_carriage-full.png", 48, 48));
        } else if (toaDangChon != null && toaDangChon.equals(toa)) {
            // Toa đang được chọn
            lblChuyenTau.setIcon(new CImage("images/icons/train_carriage-active.png", 48, 48));
        } else if (toa.getLoaiToa().getTenLT().equals(loaiToa) && toaDangChon == null) {
            // Loại toa được đề xuất
            toaDangChon = toa;
            lblChuyenTau.setIcon(new CImage("images/icons/train_carriage-active.png", 48, 48));
        } else if (toa.getLoaiToa().getTenLT().equals(loaiToa) && toaDangChon != null) {
            // Loại toa được đề xuất nhưng có toa đã chọn khác
            lblChuyenTau.setIcon(new CImage("images/icons/train_carriage-recommend.png", 48, 48));
        } else {
            // Biểu tượng mặc định
            lblChuyenTau.setIcon(new CImage("images/icons/train_carriage.png", 48, 48));
        }

        lblChuyenTau.setBackground(EColor.BG_COLOR.getColor());
        pnlToaIcon.add(lblChuyenTau, BorderLayout.CENTER);

        // Panel chứa số chỗ trống
        JPanel pnlSoChoTrong = new JPanel();
        pnlSoChoTrong.setLayout(new BorderLayout());
        pnlSoChoTrong.setBackground(EColor.BG_COLOR.getColor());

        JLabel lblSoChoTrong = new JLabel(String.valueOf(soLuongChoTrong));
        lblSoChoTrong.setHorizontalAlignment(SwingConstants.CENTER);
        lblSoChoTrong.setVerticalAlignment(SwingConstants.CENTER);
        lblSoChoTrong.setFont(new Font("Arial", Font.BOLD, 14));

        // Tùy thuộc vào % chỗ trống mà hiển thị màu sắc khác nhau cho lblSoChoTrong
        if (soLuongChoTrong <= 0) {
            lblSoChoTrong.setForeground(Color.RED);  // Chỗ trống hết
        } else if (soLuongChoTrong <= toa.getSoLuongCho() * 0.2) {
            lblSoChoTrong.setForeground(EColor.IT_CHO_TRONG.getColor());  // Chỗ trống ít
        } else {
            lblSoChoTrong.setForeground(EColor.SL_CHO_TRONG.getColor());  // Chỗ trống nhiều
        }

        pnlSoChoTrong.add(lblSoChoTrong, BorderLayout.CENTER);

        // Thêm các panel con vào pnlToa
        pnlToa.add(pnlStt);
        pnlToa.add(pnlToaIcon);
        pnlToa.add(pnlSoChoTrong);

        // Thêm sự kiện click chuột để chọn toa
        pnlToa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toaDangChon = toa;
                hienThiDanhSachToa();  // Hiển thị lại danh sách toa
                hienThiThongTinToa();  // Hiển thị thông tin toa
            }
        });

        // Trả về panel đã được tạo
        return pnlToa;
    }

    /**
     * Tạo JPanel hiển thị thông tin chỗ ngồi.
     *
     * @param choNgoi Thông tin chỗ ngồi cần hiển thị.
     * @param index   Số thứ tự của chỗ ngồi.
     * @return JPanel chứa thông tin chỗ ngồi.
     */
    public JPanel getPnlChoNgoi(ChoNgoi choNgoi, int index) {
        JPanel pnlChoNgoi = new JPanel();
        pnlChoNgoi.setLayout(new BorderLayout());

        // Kiểm tra chỗ ngồi này thuộc chiều đi hay chiều về và đã chọn chưa
        boolean isDangChon;
        if (listChuyenDi.contains(chuyenDangChon)) {
            // Chuyến chiều đi
            isDangChon = listChoNgoiDaChonChieuDi.contains(choNgoi);
        } else {
            // Chuyến chiều về
            isDangChon = listChoNgoiDaChonChieuVe.contains(choNgoi);
        }

        // Kiểm tra chỗ ngồi này đã bán chưa
        boolean isDaBan = listVeDaBan.stream().anyMatch(
                ve -> ve.getChoNgoi().equals(choNgoi) &&
                        ve.getChuyenTau().equals(chuyenDangChon) &&
                        ve.getChoNgoi().getToa().equals(toaDangChon)
        );

        // Kiểm tra chỗ ngồi này phải là vé tạm không
        boolean isVeTam = getListVeTuHoaDonTam().stream().anyMatch(
                veTam -> veTam.getChoNgoi().equals(choNgoi) &&
                        veTam.getChuyenTau().equals(chuyenDangChon) &&
                        veTam.getChoNgoi().getToa().equals(toaDangChon)
        );

        JLabel lblChoNgoi = new JLabel(String.valueOf(index + 1));

        // Kiểm tra loại ghế và thiết lập icon cho ghế
        if (choNgoi.getLoaiCho().getTenLC().equals(ELoaiToa.ANL.getLoaiToa())
                || choNgoi.getLoaiCho().getTenLC().equals(ELoaiToa.BNL.getLoaiToa())
                || choNgoi.getLoaiCho().getTenLC().equals(ELoaiToa.VIP.getLoaiToa())) {
            lblChoNgoi.setIcon(new CImage("images/icons/bed.png", 16, 16));
        }

        lblChoNgoi.setFont(new Font("Aria", Font.BOLD, 12));
        lblChoNgoi.setVerticalAlignment(SwingConstants.CENTER);
        lblChoNgoi.setHorizontalAlignment(SwingConstants.CENTER);

        // Xác định trạng thái ghế
        String trangThai = isDaBan ? ETrangThaiChoNgoi.DA_DAT.getTrangThai() :
                isDangChon ? ETrangThaiChoNgoi.DANG_CHON.getTrangThai() :
                        isVeTam ? ETrangThaiChoNgoi.VE_TAM.getTrangThai() :
                                ETrangThaiChoNgoi.CON_TRONG.getTrangThai();

        // Set màu cho chỗ ngồi theo trạng thái
        if (trangThai.equals(ETrangThaiChoNgoi.CON_TRONG.getTrangThai())) {
            pnlChoNgoi.setBackground(EColor.GHE_TRONG.getColor());
        } else if (trangThai.equals(ETrangThaiChoNgoi.DA_DAT.getTrangThai())) {
            pnlChoNgoi.setBackground(EColor.GHE_DA_BAN.getColor());
        } else if (trangThai.equals(ETrangThaiChoNgoi.VE_TAM.getTrangThai())) {
            pnlChoNgoi.setBackground(EColor.GHE_VE_TAM.getColor());
        } else {
            pnlChoNgoi.setBackground(EColor.GHE_DANG_CHON.getColor());
        }

        // Xử lý sự kiện khi click vào chỗ ngồi
        pnlChoNgoi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Nếu click chuột trái
                if (SwingUtilities.isLeftMouseButton(e)) {
                    // Nếu ghế đã bán thì không chọn được
                    if (isDaBan) {
                        return;
                    }

                    if (isVeTam && !isDangChon) {
                        return;
                    }

                    // Tùy theo chuyến đi hay chuyến về mà chọn chỗ ngồi
                    if (listChuyenDi.contains(chuyenDangChon)) {
                        if (listChoNgoiDaChonChieuDi.contains(choNgoi)) {
                            listChoNgoiDaChonChieuDi.remove(choNgoi);
                        } else {
                            listChoNgoiDaChonChieuDi.add(choNgoi);
                        }
                    } else {
                        if (listChoNgoiDaChonChieuVe.contains(choNgoi)) {
                            listChoNgoiDaChonChieuVe.remove(choNgoi);
                        } else {
                            // Kiểm tra số chỗ ngồi chiều về phải bằng chiều đi
                            if (listChoNgoiDaChonChieuDi.size() == listChoNgoiDaChonChieuVe.size()) {
                                JOptionPane.showMessageDialog(null, "Số chỗ ngồi chiều đi và chiều về phải bằng nhau");
                                return;
                            }
                            listChoNgoiDaChonChieuVe.add(choNgoi);
                        }
                    }

                    updateGioVe();
                    hienThiDanhSachChoNgoi();
                }
            }
        });

        lblChoNgoi.setPreferredSize(new Dimension(10, 20));
        lblChoNgoi.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnlChoNgoi.setPreferredSize(new Dimension(10, 20));
        pnlChoNgoi.add(lblChoNgoi, BorderLayout.CENTER);

        // Tạo một box hình chữ nhật màu nâu dài tựa như cái tường nhà ở phía WEST của pnlChoNgoi
        JPanel pnlWest = new JPanel();
        pnlWest.setPreferredSize(new Dimension(10, 20));
        pnlWest.setBackground(EColor.BORDER_COLOR.getColor());
        pnlChoNgoi.add(pnlWest, BorderLayout.WEST);

        return pnlChoNgoi;
    }

    /**
     * Cập nhật giờ vé dựa trên thời gian thực.
     */
    public void updateGioVe() {
        // Xóa tất cả dữ liệu hiện có trong bảng
        tblModelGioVeChieuDi.setRowCount(0);
        tblModelGioVeChieuVe.setRowCount(0);

        // Hiển thị danh sách chỗ ngồi chiều đi
        listChoNgoiDaChonChieuDi.forEach(choNgoi -> {
            tblModelGioVeChieuDi.addRow(new Object[]{
                    listChoNgoiDaChonChieuDi.indexOf(choNgoi) + 1, // Chỉ mục ghế (tăng 1 để bắt đầu từ 1)
                    choNgoi.getMaCho(), // Mã chỗ ngồi
                    choNgoi.getLoaiCho().getTenLC(), // Loại chỗ ngồi
                    FormatMoney.format(choNgoi.getGiaCho()) // Giá chỗ ngồi
            });
        });

        // Kiểm tra nếu vé thuộc loại "Khu Hồi" (chuyến về)
        if (Objects.equals(loaiVe, ELoaiVe.KHU_HOI.getValue())) {
            listChoNgoiDaChonChieuVe.forEach(choNgoi -> {
                tblModelGioVeChieuVe.addRow(new Object[]{
                        listChoNgoiDaChonChieuVe.indexOf(choNgoi) + 1, // Chỉ mục ghế (tăng 1 để bắt đầu từ 1)
                        choNgoi.getMaCho(), // Mã chỗ ngồi
                        choNgoi.getLoaiCho().getTenLC(), // Loại chỗ ngồi
                        FormatMoney.format(choNgoi.getGiaCho()) // Giá chỗ ngồi
                });
            });
        }

        // Tính tổng tiền vé (tổng của tất cả các vé đã chọn)
        double tongTien = listChoNgoiDaChonChieuDi.stream()
                .mapToDouble(choNgoi -> choNgoi.getLoaiCho().getGiaCho())
                .sum() + listChoNgoiDaChonChieuVe.stream()
                .mapToDouble(choNgoi -> choNgoi.getLoaiCho().getGiaCho())
                .sum();

        // Hiển thị tổng tiền và số lượng vé
        lblTongTienVe.setText("Tổng tiền vé: " + FormatMoney.format(tongTien));
        lblSoVe.setText("Số vé: " + (listChoNgoiDaChonChieuDi.size() + listChoNgoiDaChonChieuVe.size()));
    }

    /**
     * Giảm số lượng vé khả dụng.
     */
    public void truVe() {
        int[] rowsChieuDi = tblGioVeChieuDi.getSelectedRows();
        int[] rowsChieuVe = tblGioVeChieuVe.getSelectedRows();

        // Kiểm tra nếu không chọn vé nào thì thông báo
        if (rowsChieuDi.length == 0 && rowsChieuVe.length == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn vé cần xóa");
            return;
        }

        // Xóa vé đã chọn khỏi danh sách vé đã chọn
        for (int i = rowsChieuDi.length - 1; i >= 0; i--) {
            listChoNgoiDaChonChieuDi.remove(rowsChieuDi[i]);
        }

        // Xóa vé đã chọn khỏi danh sách vé đã chọn
        for (int i = rowsChieuVe.length - 1; i >= 0; i--) {
            listChoNgoiDaChonChieuVe.remove(rowsChieuVe[i]);
        }

        // Cập nhật lại
        updateGioVe();
        hienThiDanhSachChoNgoi();
    }

    /**
     * Đặt lại toàn bộ giao diện và dữ liệu.
     */
    public void resetAll() {
        // Xóa tất cả các danh sách và đối tượng đã chọn
        listChuyenDi.clear();
        listChuyenVe.clear();
        listChoNgoi.clear();
        listChoNgoiDaChonChieuDi.clear();
        listChoNgoiDaChonChieuVe.clear();
        listVeDaBan.clear();

        // Đặt lại các đối tượng đang được chọn thành null
        chuyenDangChon = null;
        tauDangChon = null;
        toaDangChon = null;

        // Xóa các thành phần trong các panel và cập nhật lại giao diện
        pnlDanhSachChuyenTau.removeAll();
        pnlDanhSachChuyenTau.revalidate();
        pnlDanhSachChuyenTau.repaint();

        pnlDanhSachToa.removeAll();
        pnlDanhSachToa.revalidate();
        pnlDanhSachToa.repaint();

        pnlThongTinToa.removeAll();
        pnlThongTinToa.revalidate();
        pnlThongTinToa.repaint();

        pnlDanhSachChoNgoi.removeAll();
        pnlDanhSachChoNgoi.revalidate();
        pnlDanhSachChoNgoi.repaint();
    }

    /**
     * Xóa toàn bộ dữ liệu trong bảng giờ vé.
     */
    public void clearTblGioVe() {
        // Xóa tất cả các dòng trong bảng Chuyến đi và Chuyến về
        tblModelGioVeChieuDi.setRowCount(0);
        tblModelGioVeChieuVe.setRowCount(0);

        // Cập nhật thông tin tổng tiền và số vé về giá trị mặc định
        lblTongTienVe.setText("Tổng tiền vé: 0");
        lblSoVe.setText("Số vé: 0");

        // Xóa các lựa chọn chỗ ngồi đã chọn cho cả 2 chiều
        listChoNgoiDaChonChieuDi.clear();
        listChoNgoiDaChonChieuVe.clear();

        // Hiển thị lại danh sách chỗ ngồi
        hienThiDanhSachChoNgoi();
    }

    public void updateHoaDonTam() {
        listHoaDonTam = HoaDonTamHandler.getDanhSachHoaDonTam();
        tblModelHoaDonTam.setRowCount(0);
        for (int i = 0; i < listHoaDonTam.size(); i++) {
            HoaDon hoaDon = listHoaDonTam.get(i);

            tblModelHoaDonTam.addRow(new Object[]{
                    i + 1,  // Thứ tự
                    hoaDon.getMaHD(),  // Mã hóa đơn
                    hoaDon.getKhachHang().getTenKH(),  // Tên người mua
                    FormatDate.formatLocalDateTimeToHM(hoaDon.getNgayGioLapHD()), // Ngày giờ xuất vé
                    FormatMoney.format(hoaDon.getThanhTien())  // Tổng tiền
            });
        }
    }

    /**
     * Hiển thị danh sách các chuyến tàu dựa trên chiều.
     *
     * @param chieu Từ khóa tìm kiếm.
     */
    public void hienThiDanhSachChuyenTau(String chieu) {
        // Xóa toàn bộ các component hiện tại trong panel danh sách chuyến tàu
        pnlDanhSachChuyenTau.removeAll();

        // Nếu danh sách chuyến đi (listChuyenDi) trống thì không làm gì
        if (listChuyenDi.isEmpty()) {
            return;
        }

        // Kiểm tra nếu chiều chuyến là chiều đi
        if (chieu.equalsIgnoreCase(ChieuChuyenTau.CHIEU_DI.getValue())) {
            // Đặt màu nền cho nút chiều đi và chiều về
            btnChieuDi.setBackground(EColor.BTN_BG_COLOR.getDecodeColor());
            btnChieuVe.setBackground(EColor.BTN_ACTIVE_BG_COLOR.getDecodeColor());

            // Duyệt qua tất cả chuyến đi và hiển thị từng chuyến tàu
            listChuyenDi.forEach(chuyenTau -> {
                JPanel btnChuyenTau = getPnlChuyenTau(chuyenTau);
                pnlDanhSachChuyenTau.add(btnChuyenTau);
            });

            // Hiển thị số chuyến tàu trong chiều đi
            lblSoChuyenHienThi.setText("Số chuyến: " + listChuyenDi.size());
        } else {
            // Nếu chiều chuyến là chiều về
            btnChieuVe.setBackground(EColor.BTN_BG_COLOR.getColor());
            btnChieuDi.setBackground(EColor.BTN_ACTIVE_BG_COLOR.getColor());

            // Duyệt qua tất cả chuyến về và hiển thị từng chuyến tàu
            listChuyenVe.forEach(chuyenTau -> {
                JPanel btnChuyenTau = getPnlChuyenTau(chuyenTau);
                pnlDanhSachChuyenTau.add(btnChuyenTau);
            });

            // Hiển thị số chuyến tàu trong chiều về
            lblSoChuyenHienThi.setText("Số chuyến: " + listChuyenVe.size());
        }

        // Cập nhật lại panel sau khi thay đổi
        pnlDanhSachChuyenTau.revalidate();
        pnlDanhSachChuyenTau.repaint();
    }

    /**
     * Xử lý sắp xếp
     */
    public void sapXepChuyen() {
        // Nếu danh sách chuyến tàu rỗng thì không làm gì
        if (listChuyenDi.isEmpty()) {
            return;
        }

        // Lấy từ khóa sắp xếp
        String sapXep = cboSapXepChuyen.getSelectedItem().toString();

        /*
        * "Mặc định",
          "Thời gian tăng dần",
          "Thời gian giảm dần",
          "Số chỗ trống tăng dần",
          "Số chỗ trống giảm dần"
        * */

        // Sắp xếp danh sách chuyến tàu theo từ khóa
        switch (sapXep) {
            case "Mặc định":
                break;
            case "Thời gian tăng dần":
                listChuyenDi.sort(Comparator.comparing(ChuyenTau::getNgayGioDi));
                break;
            case "Thời gian giảm dần":
                listChuyenDi.sort(Comparator.comparing(ChuyenTau::getNgayGioDi).reversed());
                break;
            default:
                break;
        }

        // Hiển thị lại danh sách chuyến tàu
        hienThiDanhSachChuyenTau("Chiều đi");
    }

    /**
     * Hiển thị danh sách các toa trong chuyến tàu.
     */
    public void hienThiDanhSachToa() {
        // Nếu không có chuyến tàu hoặc tàu được chọn, không làm gì và thoát ra
        if (chuyenDangChon == null || tauDangChon == null) return;

        // Xóa toàn bộ các component hiện tại trong panel danh sách toa
        pnlDanhSachToa.removeAll();

        // Tạo danh sách toa từ danh sách toa của tàu đang chọn

        ArrayList<Toa> dsToa = new ArrayList<>(chuyenDangChon.getTau().getDanhSachToa());
        java.util.List<Toa> dsToaCopy = chuyenDangChon.getTau().getDanhSachToa();
        //System.out.println(dsToa);

        // Nếu danh sách toa rỗng, không làm gì và thoát ra
        if (dsToa.isEmpty()) return;

        // Đảo ngược danh sách toa
        Collections.reverse(dsToa);

        // Tạo panel cho đầu tàu
        JPanel pnlDauTau = new JPanel();
        pnlDauTau.setLayout(new BoxLayout(pnlDauTau, BoxLayout.Y_AXIS));

        // Tạo panel và icon cho chuyến tàu
        JPanel pnlToaIcon = new JPanel();
        pnlToaIcon.setBackground(EColor.BG_COLOR.getColor());
        JLabel lblChuyenTau = new JLabel();

        // Tạo panel cho số thứ tự chuyến tàu
        JPanel pnlStt = new JPanel();
        pnlStt.setLayout(new BorderLayout());
        JLabel lblSTT = new JLabel(String.valueOf(chuyenDangChon.getMacTau()));
        lblSTT.setHorizontalAlignment(SwingConstants.CENTER);
        lblSTT.setVerticalAlignment(SwingConstants.CENTER);
        pnlStt.add(lblSTT, BorderLayout.NORTH);
        pnlStt.setBackground(EColor.BG_COLOR.getColor());

        // Đặt icon cho chuyến tàu
        lblChuyenTau.setIcon(new CImage("images/icons/train_3.png", 48, 48));
        lblChuyenTau.setBackground(EColor.BG_COLOR.getColor());
        pnlToaIcon.add(lblChuyenTau, BorderLayout.CENTER);

        // Tạo panel cho số chỗ trống
        JPanel pnlSoChoTrong = new JPanel();
        pnlSoChoTrong.setLayout(new BorderLayout());
        pnlSoChoTrong.setBackground(EColor.BG_COLOR.getColor());

        JLabel lblSoChoTrong = new JLabel("Số chỗ trống");
        lblSoChoTrong.setFont(new Font("Arial", Font.BOLD, 14));
        lblSoChoTrong.setHorizontalAlignment(SwingConstants.CENTER);
        lblSoChoTrong.setVerticalAlignment(SwingConstants.CENTER);
        pnlSoChoTrong.add(lblSoChoTrong, BorderLayout.CENTER);

        // Thêm các panel vào đầu tàu
        pnlDauTau.add(pnlStt);
        pnlDauTau.add(pnlToaIcon);
        pnlDauTau.add(pnlSoChoTrong);

        // Duyệt qua danh sách toa và thêm từng toa vào danh sách
        dsToa.forEach(toa -> {
            int index = dsToaCopy.indexOf(toa);
            JPanel pnlToa = getPnlToa(toa, index);
            pnlDanhSachToa.add(pnlToa);
        });

        // Thêm panel đầu tàu vào cuối danh sách
        pnlDanhSachToa.add(pnlDauTau);

        // Thêm khoảng trống giữa các toa
        pnlDanhSachToa.add(Box.createHorizontalStrut(20));

        // Cập nhật lại panel sau khi thay đổi
        pnlDanhSachToa.revalidate();
        pnlDanhSachToa.repaint();
    }

    /**
     * Hiển thị danh sách các chỗ ngồi trong toa.
     */
    public void hienThiDanhSachChoNgoi() {
        if (toaDangChon == null) {
            return;
        }

        pnlDanhSachChoNgoi.removeAll();
        pnlDanhSachChoNgoi.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                new EmptyBorder(10, 10, 10, 10)
        ));

        listChoNgoi = DAOChoNgoi.getDSChoNgoiTheoToa(toaDangChon.getMaToa());

        System.out.println("1---------------------------------------------------------"+listChoNgoi);

        LoaiToa loaiToa = toaDangChon.getLoaiToa();

        System.out.println("2---------------------------------------------------------"+loaiToa);

        // Tạo ArrayList hai chiều
        ArrayList<ArrayList<ChoNgoi>> mangHaiChieu = new ArrayList<>();

        // Hiển thị các chỗ ngồi theo loại ghế dạng ngồi (Loại NC, NM, NCL, NML)
        if (loaiToa.getTenLT().equals(ELoaiToa.NC.getLoaiToa()) ||
                loaiToa.getTenLT().equals(ELoaiToa.NM.getLoaiToa()) ||
                loaiToa.getTenLT().equals(ELoaiToa.NCL.getLoaiToa()) ||
                loaiToa.getTenLT().equals(ELoaiToa.NML.getLoaiToa())) {
            // Tách ArrayList một chiều thành ArrayList hai chiều
            for (int i = 0; i < 15; i++) {
                ArrayList<ChoNgoi> hang = new ArrayList<>();
                for (int j = 0; j < 4; j++) {
                    hang.add(listChoNgoi.get(i * 4 + j));
                }
                mangHaiChieu.add(hang);
            }
            // In ra ArrayList hai chiều
            for (int i = 0; i < mangHaiChieu.size(); i++) {
                ArrayList<ChoNgoi> hangChoNgoi = mangHaiChieu.get(i);
                JPanel boxHangChoNgoi = new JPanel();
                boxHangChoNgoi.setLayout(new BoxLayout(boxHangChoNgoi, BoxLayout.Y_AXIS));
                boxHangChoNgoi.setBackground(EColor.BG_COLOR.getColor());

                // Nếu là hàng giữa thì thêm khoảng trống
                if (i == Math.floor((double) mangHaiChieu.size() / 2)) {
                    pnlDanhSachChoNgoi.add(Box.createHorizontalStrut(20));
                }

                hangChoNgoi.forEach(choNgoi -> {
                    int indexCuaToanBoChoNgoi = listChoNgoi.indexOf(choNgoi);
                    int indexCuaHang = hangChoNgoi.indexOf(choNgoi);
                    final int VI_TRI_GIUA = 1; // Vị trí ghế ở giữa

                    boxHangChoNgoi.add(getPnlChoNgoi(choNgoi, indexCuaToanBoChoNgoi));

                    // Nếu vị trí ghế ở hàng giữa sẽ thêm khoảng trống
                    if (indexCuaHang == VI_TRI_GIUA) {
                        boxHangChoNgoi.add(Box.createVerticalStrut(20)); // Khoảng trống giữa các hàng ghế
                    } else {
                        boxHangChoNgoi.add(Box.createVerticalStrut(10)); // Khoảng trống giữa các hàng ghế
                    }
                });

                pnlDanhSachChoNgoi.add(boxHangChoNgoi);

                // Nếu không phải hàng cuối thì thêm khoảng trống
                if (i != mangHaiChieu.size() - 1) {
                    pnlDanhSachChoNgoi.add(Box.createHorizontalStrut(10));
                }
            }
        }
        // Hiển thị chỗ ngồi với loại giường nằm khoang 6 gồm 30 chỗ
        else if (loaiToa.getTenLT().equals(ELoaiToa.ANL.getLoaiToa())) {
            hienThiChoNgoiLoaiGiuongNam(mangHaiChieu, 2, 15, 30); // Gọi hàm riêng biệt để xử lý
        }
        // Hiển thị chỗ ngồi với loại giường nằm khoang 6 gồm 40 chỗ
        else if (loaiToa.getTenLT().equals(ELoaiToa.BNL.getLoaiToa())) {
            hienThiChoNgoiLoaiGiuongNam(mangHaiChieu, 3, 13, 40); // Gọi hàm riêng biệt để xử lý
        }
        // Hiển thị chỗ ngồi với loại VIP là 10 chỗ
        else if (loaiToa.getTenLT().equals(ELoaiToa.VIP.getLoaiToa())) {
            hienThiChoNgoiLoaiVIP(mangHaiChieu, 2, 5, 10); // Gọi hàm riêng biệt để xử lý
        }

        pnlDanhSachChoNgoi.revalidate();
        pnlDanhSachChoNgoi.repaint();
    }

    /**
     * Hiển thị chỗ ngồi với loại giường nằm.
     *
     * @param mangHaiChieu
     * @param soLuongCho
     * @param soHang
     * @param soCho
     */
    private void hienThiChoNgoiLoaiGiuongNam(ArrayList<ArrayList<ChoNgoi>> mangHaiChieu, int soLuongCho, int soHang, int soCho) {
        mangHaiChieu.clear();
        // Tách ArrayList một chiều thành ArrayList hai chiều
        for (int i = 0; i < soHang; i++) {
            ArrayList<ChoNgoi> hang = new ArrayList<>();
            for (int j = 0; j < soLuongCho; j++) {
                if (listChoNgoi.get(i * soLuongCho + j) != null) {
                    hang.add(listChoNgoi.get(i * soLuongCho + j));
                }
            }
            mangHaiChieu.add(hang);
        }

        // In ra ArrayList hai chiều
        for (int i = 0; i < mangHaiChieu.size(); i++) {
            ArrayList<ChoNgoi> hangChoNgoi = mangHaiChieu.get(i);
            JPanel boxHangChoNgoi = new JPanel();
            boxHangChoNgoi.setLayout(new BoxLayout(boxHangChoNgoi, BoxLayout.Y_AXIS));
            boxHangChoNgoi.setBackground(EColor.BG_COLOR.getColor());

            hangChoNgoi.forEach(choNgoi -> {
                int indexCuaToanBoChoNgoi = listChoNgoi.indexOf(choNgoi);

                boxHangChoNgoi.add(getPnlChoNgoi(choNgoi, indexCuaToanBoChoNgoi));
                boxHangChoNgoi.add(Box.createVerticalStrut(10)); // Khoảng trống giữa các hàng ghế
            });

            pnlDanhSachChoNgoi.add(boxHangChoNgoi);

            if (i % 2 != 0) {
                pnlDanhSachChoNgoi.add(Box.createHorizontalStrut(10));
            }

            // Nếu không phải hàng cuối thì thêm khoảng trống
            if (i != mangHaiChieu.size() - 1) {
                pnlDanhSachChoNgoi.add(Box.createHorizontalStrut(10));
            }
        }
    }

    /**
     * Hiển thị chỗ ngồi với loại VIP.
     *
     * @param mangHaiChieu
     * @param soLuongCho
     * @param soHang
     * @param soCho
     */
    private void hienThiChoNgoiLoaiVIP(ArrayList<ArrayList<ChoNgoi>> mangHaiChieu, int soLuongCho, int soHang, int soCho) {
        hienThiChoNgoiLoaiGiuongNam(mangHaiChieu, soLuongCho, soHang, soCho); // Gọi lại phương thức chung cho VIP
    }

    /**
     * Hiển thị thông tin chi tiết của toa.
     */
    public void hienThiThongTinToa() {
        // Xóa tất cả các thành phần hiện tại trong panel thông tin toa
        pnlThongTinToa.removeAll();

        // Nếu chưa chọn toa, thoát phương thức
        if (toaDangChon == null) {
            return;
        }

        // Tạo panel cho phần trên của toa
        JPanel pnlToaTop = new JPanel();
        pnlToaTop.setLayout(new BorderLayout());
        pnlToaTop.setBackground(EColor.BG_COLOR.getColor());
        pnlToaTop.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tạo box chứa các nút chức năng
        Box boxChucNang = Box.createHorizontalBox();
        boxChucNang.add(btnTuDongChonCho);
        boxChucNang.add(Box.createHorizontalStrut(10));
        boxChucNang.add(btnChonCaToa);
        boxChucNang.add(Box.createHorizontalStrut(10));
        boxChucNang.add(btnBoChonCaToa);
        boxChucNang.setPreferredSize(new Dimension(400, 0));

        // Tạo nhãn hiển thị tên toa
        JLabel lblTenToa = new JLabel("Toa " + toaDangChon.getMaToa() + ": " + toaDangChon.getLoaiToa().getTenLT());
        lblTenToa.setFont(new Font("Arial", Font.BOLD, 16));
        lblTenToa.setHorizontalAlignment(SwingConstants.CENTER);
        lblTenToa.setVerticalAlignment(SwingConstants.CENTER);
        lblTenToa.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Tạo panel hướng dẫn
        JPanel pnlHuongDan = new JPanel();
        pnlHuongDan.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnlHuongDan.setBackground(EColor.BG_COLOR.getColor());

        // Tạo các panel cho từng trạng thái chỗ ngồi
        JPanel pnlChoNgoiTrong = new JPanel();
        pnlChoNgoiTrong.setLayout(new BorderLayout());
        pnlChoNgoiTrong.setBackground(EColor.GHE_TRONG.getColor());

        JPanel pnlChoNgoiVeTam = new JPanel();
        pnlChoNgoiVeTam.setLayout(new BorderLayout());
        pnlChoNgoiVeTam.setBackground(EColor.GHE_VE_TAM.getColor());

        JPanel pnlChoNgoiDangChon = new JPanel();
        pnlChoNgoiDangChon.setLayout(new BorderLayout());
        pnlChoNgoiDangChon.setBackground(EColor.GHE_DANG_CHON.getColor());

        JPanel pnlChoNgoiDaDat = new JPanel();
        pnlChoNgoiDaDat.setLayout(new BorderLayout());
        pnlChoNgoiDaDat.setBackground(EColor.GHE_DA_BAN.getColor());

        // Tạo nhãn cho từng loại chỗ ngồi
        JLabel lblChoNgoiTrong = createSeatLabel(EColor.GHE_TRONG.getColor(), "1");
        JPanel pnlWest1 = createBorderPanel();
        pnlChoNgoiTrong.add(lblChoNgoiTrong, BorderLayout.CENTER);
        pnlChoNgoiTrong.add(pnlWest1, BorderLayout.WEST);

        JLabel lblChoNgoiVeTam = createSeatLabel(EColor.GHE_VE_TAM.getColor(), "1");
        JPanel pnlWest2 = createBorderPanel();
        pnlChoNgoiVeTam.add(lblChoNgoiVeTam, BorderLayout.CENTER);
        pnlChoNgoiVeTam.add(pnlWest2, BorderLayout.WEST);

        JLabel lblChoNgoiDangChon = createSeatLabel(EColor.GHE_DANG_CHON.getColor(), "1");
        JPanel pnlWest3 = createBorderPanel();
        pnlChoNgoiDangChon.add(lblChoNgoiDangChon, BorderLayout.CENTER);
        pnlChoNgoiDangChon.add(pnlWest3, BorderLayout.WEST);

        JLabel lblChoNgoiDaDat = createSeatLabel(EColor.GHE_DA_BAN.getColor(), "1");
        JPanel pnlWest4 = createBorderPanel();
        pnlChoNgoiDaDat.add(lblChoNgoiDaDat, BorderLayout.CENTER);
        pnlChoNgoiDaDat.add(pnlWest4, BorderLayout.WEST);

        // Thêm các chỗ ngồi vào panel hướng dẫn
        pnlHuongDan.add(pnlChoNgoiTrong);
        pnlHuongDan.add(new JLabel("Trống"));
        pnlHuongDan.add(Box.createHorizontalStrut(10));

        pnlHuongDan.add(pnlChoNgoiDangChon);
        pnlHuongDan.add(new JLabel("Đang chọn"));
        pnlHuongDan.add(Box.createHorizontalStrut(10));

        pnlHuongDan.add(pnlChoNgoiVeTam);
        pnlHuongDan.add(new JLabel("Vé tạm"));
        pnlHuongDan.add(Box.createHorizontalStrut(10));

        pnlHuongDan.add(pnlChoNgoiDaDat);
        pnlHuongDan.add(new JLabel("Đã đặt"));
        pnlHuongDan.add(Box.createHorizontalStrut(10));

        // Thêm các thành phần vào panel toa
        pnlToaTop.add(boxChucNang, BorderLayout.WEST);
        pnlToaTop.add(lblTenToa, BorderLayout.NORTH);
        pnlToaTop.add(pnlHuongDan, BorderLayout.EAST);

        // Thêm các panel vào panel thông tin toa
        pnlThongTinToa.add(pnlToaTop, BorderLayout.NORTH);
        pnlThongTinToa.add(Box.createHorizontalStrut(10), BorderLayout.WEST);
        pnlThongTinToa.add(pnlDanhSachChoNgoi, BorderLayout.CENTER);
        pnlThongTinToa.add(Box.createHorizontalStrut(10), BorderLayout.EAST);

        // Hiển thị danh sách chỗ ngồi
        hienThiDanhSachChoNgoi();
    }

    /**
     * Tạo label chỗ ngồi.
     * @param color
     * @param text
     * @return
     */
    private JLabel createSeatLabel(Color color, String text) {
        JLabel label = new JLabel(text);
        label.setBackground(color);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(40, 20));
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return label;
    }

    /**
     * Tạo panel có màu nền là màu viền.
     *
     * @return
     */
    private JPanel createBorderPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(EColor.BORDER_COLOR.getColor());
        return panel;
    }

    /**
     * Lấy số lượng chỗ trống trong một toa.
     *
     * @param toa Toa cần kiểm tra.
     * @return Số lượng chỗ trống.
     */
    public long getSoLuongChoTrongCuaToa(Toa toa) {
        if (toa == null || chuyenDangChon == null) {
            return 0;
        }

        // Lọc và đếm số lượng vé đã bán cho toa cụ thể trong chuyến tàu đang chọn
        return toa.getSoLuongCho() - listVeDaBan.stream()
                .filter(ve -> ve.getChuyenTau().equals(chuyenDangChon) && ve.getChoNgoi().getToa().equals(toa))
                .count();
    }

    /**
     * Chọn nhanh toa dựa trên loại toa (ví dụ: hạng thường, hạng sang).
     */
    public void chonNhanhTheoLoaiToa() {
        String tenLoaiToa = cboLoaiToa.getSelectedItem().toString();

        // Lọc toa dựa trên loại toa và tình trạng ghế ngồi
        toaDangChon = chuyenDangChon.getTau().getDanhSachToa().stream()
                .filter(toa -> tenLoaiToa.equals("Tất cả") || toa.getLoaiToa().getTenLT().equalsIgnoreCase(tenLoaiToa))
                .filter(toa -> toa.getDanhSachChoNgoi().stream().anyMatch(choNgoi -> {
                    // Nếu ghế ngồi chưa được chọn hoặc đã bán vé, thì bỏ qua
                    return !listChoNgoiDaChonChieuDi.contains(choNgoi) &&
                            listVeDaBan.stream().noneMatch(ve -> ve.getChoNgoi().equals(choNgoi));
                }))
                .findFirst()
                .orElse(null);

        // Nếu không có toa phù hợp
        if (toaDangChon == null) {
            return;
        }

        // Hiển thị danh sách toa và thông tin toa
        hienThiDanhSachToa();
        hienThiThongTinToa();

        // Nhập số lượng vé từ người dùng
        String soLuongVeStr = JOptionPane.showInputDialog(null, "Nhập số lượng vé", "Tự động chọn chỗ ngồi", JOptionPane.INFORMATION_MESSAGE);
        int soLuongVe = 0;
        try {
            soLuongVe = Integer.parseInt(soLuongVeStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Số lượng vé không hợp lệ");
            return;
        }

        // Tự động chọn ghế ngồi cho số lượng vé đã nhập
        tuDongChonChoNgoi(soLuongVe);
    }

    /**
     * Tự động chọn chỗ ngồi trong toa.
     */
    public void xuLyTuDongChonCho() {
        // Kiểm tra nếu danh sách chỗ ngồi trống
        if (listChoNgoi.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Chưa chọn toa");
            return;
        }

        // Kiểm tra xem có phải chuyến về không
        boolean isChieuVe = listChuyenVe.contains(chuyenDangChon);

        String soLuongVeStr;
        if (!isChieuVe) {
            // Nếu là chiều đi, yêu cầu người dùng nhập số lượng vé
            soLuongVeStr = JOptionPane.showInputDialog(null, "Nhập số lượng vé", "Tự động chọn chỗ ngồi", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Nếu là chiều về, mặc định số lượng vé bằng số vé đã chọn cho chiều đi
            soLuongVeStr = String.valueOf(listChoNgoiDaChonChieuDi.size());
        }

        System.out.println("soLuongVeStr = " + soLuongVeStr);

        // Kiểm tra nếu người dùng không nhập gì
        if (soLuongVeStr == null || soLuongVeStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Chưa nhập số lượng vé");
            return;
        }

        try {
            int soLuongVe = Integer.parseInt(soLuongVeStr);

            // Kiểm tra nếu loại vé là KHU_HOI, và nếu là chuyến về thì số lượng vé phải bằng vé chiều đi
            if (loaiVe.equalsIgnoreCase(ELoaiVe.KHU_HOI.getValue()) && listChuyenVe.contains(chuyenDangChon)) {
                if (soLuongVe != listChoNgoiDaChonChieuDi.size()) {
                    JOptionPane.showMessageDialog(null, "Số lượng vé phải bằng số lượng vé chiều đi");
                    return;
                }
            }

            // Kiểm tra số lượng vé không vượt quá số chỗ ngồi trong toa
            if (soLuongVe > listChoNgoi.size()) {
                JOptionPane.showMessageDialog(null, "Số lượng vé không được vượt quá số lượng chỗ ngồi");
                return;
            }

            // Tiến hành tự động chọn chỗ ngồi
            tuDongChonChoNgoi(soLuongVe);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Số lượng vé không hợp lệ");
        }
    }

    /**
     * Xử lý chọn cả toa
     */
    public void chonCaToa() {
        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn chọn cả toa không?", "Chọn cả toa",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            // Thực hiện chọn cả toa khi nhấn phím F4
            if (toaDangChon != null) {
                tuDongChonChoNgoi(toaDangChon.getSoLuongCho());
            }
        }
    }

    /**
     * Xử lý chọn chỗ ngồi theo số lượng vé
     * @param soLuongVe Số lượng vé cần chọn
     */
    public void tuDongChonChoNgoi(int soLuongVe) {
        // Map các danh sách vé của các hóa đơn tạm thành một danh sách
        ArrayList<Ve> listVeTam = new ArrayList<>();
        listHoaDonTam.forEach(hoaDonTam -> listVeTam.addAll(hoaDonTam.getDanhSachVe()));

        // Kiêm tra chiều đi hay chiều về
        if (listChuyenDi.contains(chuyenDangChon)) {
            // Xóa tất cả các chỗ ngồi đã chọn của toa đang chọn
            listChoNgoiDaChonChieuDi.removeIf(choNgoi -> choNgoi.getToa().equals(toaDangChon));

            // Đếm số lượng chỗ ngồi đã chọn để không vượt quá số vé
            int soLuongDaChon = 0;

            // Lặp qua các chỗ ngồi trong toa
            for (ChoNgoi choNgoi : listChoNgoi) {
                // Nếu chỗ ngồi đó chưa được chọn
                if (!listChoNgoiDaChonChieuDi.contains(choNgoi)) {
                    // Nếu số lượng chỗ ngồi đã chọn chưa vượt quá số lượng vé
                    if (soLuongDaChon < soLuongVe) {

                        // Nếu chỗ ngồi đó còn trống khi chưa có vé nào đã đặt trong chuyến
                        boolean isDaBan = listVeDaBan.stream().anyMatch(ve -> ve.getChuyenTau().equals(chuyenDangChon) && ve.getChoNgoi().equals(choNgoi));
                        // Kiểm tra chỗ ngồi này có trong danh sách các hóa đơn tạm không
                        boolean isVeTam = listVeTam.stream().anyMatch(ve -> ve.getChuyenTau().equals(chuyenDangChon) && ve.getChoNgoi().equals(choNgoi));

                        if (!isVeTam && !isDaBan) {
                            listChoNgoiDaChonChieuDi.add(choNgoi);
                            soLuongDaChon++;
                        }
                    }
                }
            }
        } else {
            // Xóa tất cả các chỗ ngồi đã chọn của toa đang chọn
            listChoNgoiDaChonChieuVe.removeIf(choNgoi -> choNgoi.getToa().equals(toaDangChon));

            // Đếm số lượng chỗ ngồi đã chọn để không vượt quá số vé
            int soLuongDaChon = 0;

            // Lặp qua các chỗ ngồi trong toa
            for (ChoNgoi choNgoi : listChoNgoi) {
                // Nếu chỗ ngồi đó chưa được chọn
                if (!listChoNgoiDaChonChieuVe.contains(choNgoi)) {
                    // Nếu chỗ ngồi đó còn trống khi chưa có vé nào đã đặt trong chuyến
                    boolean isDaBan = listVeDaBan.stream().anyMatch(ve -> ve.getChuyenTau().equals(chuyenDangChon) && ve.getChoNgoi().equals(choNgoi));
                    // Kiểm tra chỗ ngồi này có trong danh sách các hóa đơn tạm không
                    boolean isVeTam = listVeTam.stream().anyMatch(ve -> ve.getChuyenTau().equals(chuyenDangChon) && ve.getChoNgoi().equals(choNgoi));

                    if (!isVeTam && !isDaBan) {
                        listChoNgoiDaChonChieuVe.add(choNgoi);
                        soLuongDaChon++;

                        if (soLuongDaChon == soLuongVe) {
                            break;
                        }
                    }
                }
            }
        }

        // Cập nhật lại giao diện
        updateGioVe();
        hienThiThongTinToa();
    }

    /**
     * Kiểm tra tính hợp lệ của dữ liệu đầu vào.
     *
     * @return True nếu dữ liệu hợp lệ, ngược lại là False.
     */
    public boolean validateInput() {
        // Kiểm tra ngày đi
        String ngayDiStr = ngayDiPicker.getJFormattedTextField().getText();
        if (ngayDiStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Chưa nhập ngày đi");
            ngayDiPicker.setButtonFocusable(true);
            return false;
        }

        // Kiểm tra ngày đi phải sau ngày hiện tại
        LocalDate ngayDi = LocalDate.parse(ngayDiStr, FormatDate.formatter);
        if (ngayDi.isBefore(LocalDate.now())) {
            JOptionPane.showMessageDialog(null, "Ngày đi phải sau ngày hiện tại");
            ngayDiPicker.setButtonFocusable(true);
            return false;
        }

        // Kiểm tra ngày về nếu là loại vé Khứ hồi
        if (rdoKhuHoi.isSelected()) {
            String ngayVeStr = ngayVePicker.getJFormattedTextField().getText();
            if (ngayVeStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Chưa nhập ngày về");
                ngayVePicker.setButtonFocusable(true);
                return false;
            }

            LocalDate ngayVe = LocalDate.parse(ngayVeStr, FormatDate.formatter);

            // Kiểm tra ngày về phải sau ngày đi
            if (ngayVe.isBefore(ngayDi)) {
                JOptionPane.showMessageDialog(null, "Ngày về phải sau ngày đi");
                ngayVePicker.setButtonFocusable(true);
                return false;
            }
        }

        // Kiểm tra chọn ga đi và ga đến khác nhau
        if (cboGaDi.getSelectedItem().equals(cboGaDen.getSelectedItem())) {
            JOptionPane.showMessageDialog(null, "Ga đi và ga đến phải khác nhau");
            return false;
        }

        return true;
    }

    /**
     * Tra cứu thông tin chuyến tàu
     */
    public void traCuuChuyenTau() {
        lblDsChuyenTau.setText("Danh sách chuyến tàu");

        // Xác nhận trước khi reset dữ liệu
        if (!listChoNgoiDaChonChieuDi.isEmpty() || !listChoNgoiDaChonChieuVe.isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(null, "Dữ liệu chưa được lưu, bạn có muốn tiếp tục không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.NO_OPTION) {
                return;
            }
        }

        if (!validateInput()) return;

        String ngayDiStr = ngayDiPicker.getJFormattedTextField().getText();
        LocalDate ngayDi = LocalDate.parse(ngayDiStr, FormatDate.formatter);

        // Lấy chuyến tàu từ dữ liệu đã chọn
        loaiVe = rdoMotChieu.isSelected() ? "Một chiều" : "Khứ hồi";
        String tenGaDi = cboGaDi.getSelectedItem().toString();
        String tenGaDen = cboGaDen.getSelectedItem().toString();

        Ga gaDi = listGa.stream().filter(ga -> ga.getTenGa().equals(tenGaDi)).findFirst().orElse(null);
        Ga gaDen = listGa.stream().filter(ga -> ga.getTenGa().equals(tenGaDen)).findFirst().orElse(null);

        if (gaDi == null || gaDen == null) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy ga đi hoặc ga đến");
            return;
        }

        lblDsChuyenTau.setText("Danh sách chuyến từ " + gaDi.getTenGa().replace("Ga Tàu", "") + " - " + gaDen.getTenGa().replace("Ga Tàu", ""));
        resetAll();

        // Lấy danh sách chuyến tàu theo các trường
        listChuyenDi = DAOChuyenTau.getDanhSachChuyenTauTheoNgaymaGaDimaGaDen(ngayDi, gaDi.getMaGa(), gaDen.getMaGa());
        System.out.println("DS Chuyến đi:---###############################################################################################################################################################33 " + listChuyenDi);
        if (listChuyenDi.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không có chuyến tàu lượt đi");
            pnlLocTheoLoaiToa.setVisible(false);
            return;
        } else {
            pnlLocTheoLoaiToa.setVisible(true);
        }

        // Lấy danh sách vé của chuyến
        listChuyenDi.forEach(chuyenTau -> {
            System.out.println("Chuyến đi:---###############################################################################################################################################################33 " + chuyenTau.getMaChuyen());
            ArrayList<Ve> dsVeDaBan = DAOVe.layDSVeDaBanTheoMaChuyen(chuyenTau.getMaChuyen());
            listVeDaBan.addAll(dsVeDaBan);
        });

        // Disabled button chiều về khi loaiVe là một chiều
        if (loaiVe.equalsIgnoreCase("Một chiều")) {
            btnChieuVe.setBackground(EColor.DISABLED_COLOR.getColor());
            btnChieuVe.setEnabled(false);
        } else {
            // Bật button chiều về khi loaiVe là khứ hồi
            btnChieuVe.setBackground(EColor.BTN_BG_COLOR.getColor());
            btnChieuVe.setEnabled(true);

            String ngayVeStr = ngayVePicker.getJFormattedTextField().getText();
            LocalDate ngayVe = LocalDate.parse(ngayVeStr, FormatDate.formatter);

            listChuyenVe = DAOChuyenTau.getDanhSachChuyenTauTheoNgaymaGaDimaGaDen(ngayVe, gaDen.getMaGa(), gaDi.getMaGa());

            if (listChuyenVe.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Không có chuyến tàu lượt về");
                return;
            }

            listChuyenVe.forEach(chuyenTau -> {
                ArrayList<Ve> dsVeDaBan = DAOVe.layDSVeDaBanTheoMaChuyen(chuyenTau.getMaChuyen());
                listVeDaBan.addAll(dsVeDaBan);
            });
        }

        // Hiển thị danh sách chuyến tàu
        hienThiDanhSachChuyenTau(ChieuChuyenTau.CHIEU_DI.getValue());
    }


    /**
     * Xử lý đơn tạm.
     */
    public void xuLyDonTam() {
        int row = tblHoaDonTam.getSelectedRow();

        // Kiểm tra nếu chưa chọn đơn tạm
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Chưa chọn đơn tạm");
            return;
        }

        // Lấy mã đơn tạm từ bảng
        String maDonTam = tblHoaDonTam.getValueAt(row, 1).toString();
        HoaDon hoaDonTam = listHoaDonTam.stream()
                .filter(hoaDon -> hoaDon.getMaHD().equals(maDonTam))
                .findFirst()
                .orElse(null);

        // Mở cửa sổ xin vé
        JdXinVe jdXinVe = new JdXinVe(this, nhanVien, hoaDonTam);
        jdXinVe.setVisible(true);
    }

    /**
     * Xử lý xóa đơn tạm.
     */
    public void xoaDonTam() {
        int row = tblHoaDonTam.getSelectedRow();

        // Kiểm tra nếu chưa chọn đơn tạm
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Chưa chọn đơn tạm");
            return;
        }

        // Lấy mã đơn tạm từ bảng
        String maDonTam = tblHoaDonTam.getValueAt(row, 1).toString();
        HoaDon hoaDonTam = listHoaDonTam.stream()
                .filter(hoaDon -> hoaDon.getMaHD().equals(maDonTam))
                .findFirst()
                .orElse(null);

        // Xác nhận xóa đơn tạm
        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa đơn tạm này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.NO_OPTION) {
            return;
        }

        // Xóa đơn tạm
        HoaDonTamHandler.xoaHoaDonTam(hoaDonTam);

        // Cập nhật lại danh sách đơn tạm
        listHoaDonTam = HoaDonTamHandler.getDanhSachHoaDonTam();
        updateHoaDonTam();

        JOptionPane.showMessageDialog(null, "Xóa đơn tạm thành công");
    }

    /**
     * Xử lý logic khi xin vé
     */
    public void xinVe() {
        // Kiểm tra số lượng vé chiều đi
        if (listChoNgoiDaChonChieuDi.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Chưa chọn vé");
            return;
        }

        // Kiểm tra số lượng vé chiều về nếu chọn loại vé khứ hồi
        if (loaiVe.equalsIgnoreCase(ELoaiVe.KHU_HOI.getValue())) {
            if (listChoNgoiDaChonChieuVe.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Chưa chọn vé chiều về");
                return;
            }

            // Kiểm tra số lượng vé chiều đi và chiều về phải bằng nhau
            if (listChoNgoiDaChonChieuDi.size() != listChoNgoiDaChonChieuVe.size()) {
                JOptionPane.showMessageDialog(null, "Số lượng vé chiều đi và chiều về phải bằng nhau");
                return;
            }
        }

        // Mở cửa sổ xin vé
        if (loaiVe.equalsIgnoreCase(ELoaiVe.MOT_CHIEU.getValue())) {
            // Chọn vé một chiều
            JdXinVe jdXinVe = new JdXinVe(this, nhanVien, chuyenDangChonChieuDi, listChoNgoiDaChonChieuDi);
            jdXinVe.setVisible(true);
        } else {
            // Chọn vé khứ hồi
            JdXinVe jdXinVe = new JdXinVe(this, nhanVien, chuyenDangChonChieuDi, chuyenDangChonChieuVe, listChoNgoiDaChonChieuDi, listChoNgoiDaChonChieuVe);
            jdXinVe.setVisible(true);
        }
    }

    /*
     * Xử lý chuyển chuyến tàu trước
     * */
    public void chuyenTauTruoc() {
        // Kiểm tra giỏ vé, yêu cầu xóa vé nếu có vé chiều đi và chiều về
        if (!listChoNgoiDaChonChieuDi.isEmpty() && !listChoNgoiDaChonChieuVe.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng xóa vé trước khi chuyển chuyến tàu");
            return;
        }

        // Kiểm tra chuyến đi có trong danh sách chuyến đi không
        if (listChuyenDi.contains(chuyenDangChon)) {
            int index = listChuyenDi.indexOf(chuyenDangChon);
            if (index > 0) {
                // Cập nhật chuyến tàu cho chiều đi
                chuyenDangChon = listChuyenDi.get(index - 1);
                tauDangChon = chuyenDangChon.getTau();
                toaDangChon = tauDangChon.getDanhSachToa().get(0);
                hienThiDanhSachChuyenTau("Chiều đi");
                hienThiDanhSachToa();
                hienThiThongTinToa();
            }
        } else {
            // Kiểm tra chuyến về có trong danh sách chuyến về không
            int index = listChuyenVe.indexOf(chuyenDangChon);
            if (index > 0) {
                // Cập nhật chuyến tàu cho chiều về
                chuyenDangChon = listChuyenVe.get(index - 1);
                tauDangChon = chuyenDangChon.getTau();
                toaDangChon = tauDangChon.getDanhSachToa().get(0);
                hienThiDanhSachChuyenTau("Chiều về");
                hienThiDanhSachToa();
                hienThiThongTinToa();
            }
        }
    }

    /**
     * Xử lý chuyển chuyến tàu sau
     */
    public void chuyenTauSau() {
        // Kiểm tra chuyến đi có trong danh sách chuyến đi không
        if (listChuyenDi.contains(chuyenDangChon)) {
            int index = listChuyenDi.indexOf(chuyenDangChon);
            // Nếu không phải chuyến cuối cùng trong danh sách, chuyển sang chuyến tiếp theo
            if (index < listChuyenDi.size() - 1) {
                chuyenDangChon = listChuyenDi.get(index + 1);
                tauDangChon = chuyenDangChon.getTau();
                toaDangChon = tauDangChon.getDanhSachToa().get(0);
                hienThiDanhSachChuyenTau("Chiều đi");
                hienThiDanhSachToa();
                hienThiThongTinToa();
            }
        } else {
            // Kiểm tra chuyến về có trong danh sách chuyến về không
            int index = listChuyenVe.indexOf(chuyenDangChon);
            // Nếu không phải chuyến cuối cùng trong danh sách, chuyển sang chuyến tiếp theo
            if (index < listChuyenVe.size() - 1) {
                chuyenDangChon = listChuyenVe.get(index + 1);
                tauDangChon = chuyenDangChon.getTau();
                toaDangChon = tauDangChon.getDanhSachToa().get(0);
                hienThiDanhSachChuyenTau("Chiều về");
                hienThiDanhSachToa();
                hienThiThongTinToa();
            }
        }
    }

    /**
     * Xử lý bỏ chọn cả toa
     */
    public void boChonCaToa() {
        // Kiểm tra nếu không có chỗ ngồi nào đã chọn cho chiều đi
        if (listChoNgoiDaChonChieuDi.isEmpty()) {
            return;
        }

        // Hiển thị hộp thoại xác nhận trước khi bỏ chọn
        int confirm = JOptionPane.showConfirmDialog(
                null,
                "Bạn có chắc chắn muốn bỏ chọn cả toa không?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION
        );

        // Nếu chọn 'Không', thoát khỏi phương thức
        if (confirm == JOptionPane.NO_OPTION) {
            return;
        }

        // Xóa tất cả các chỗ ngồi trong toa đang được chọn
        listChoNgoiDaChonChieuDi.removeIf(choNgoi -> choNgoi.getToa().equals(toaDangChon));

        // Cập nhật lại giờ vé
        updateGioVe();
        // Hiển thị lại thông tin toa sau khi thay đổi
        hienThiThongTinToa();
    }

    /**
     * Xử lý các hành động khi người dùng nhấn các nút trên giao diện.
     * Phương thức này được gọi khi có một sự kiện ActionEvent.
     *
     * @param e sự kiện ActionEvent chứa thông tin về nguồn sự kiện.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Lấy đối tượng nguồn của sự kiện
        Object obj = e.getSource();

        // Xử lý tra cứu chuyến tàu khi người dùng nhấn nút "Tra Cứu Chuyến Tàu"
        if (obj.equals(btnTraCuuChuyenTau)) {
            traCuuChuyenTau();
        }

        // Xử lý chuyến tàu sắp chạy khi người dùng nhấn nút "Tra Chuyến Sắp Chạy"
        if (obj.equals(btnTraChuyenSapChay)) {
            getCacChuyenTauSapKhoiHanh();
        }

        // Chọn nhanh theo loại toa khi người dùng nhấn nút "Chọn Nhanh Theo Loại Toa"
        if (obj.equals(btnChonNhanhTheoLoaiToa)) {
            chonNhanhTheoLoaiToa();
        }

        // Xử lý xin vé khi người dùng nhấn nút "Xin Vé"
        if (obj.equals(btnXinVe)) {
            xinVe();
        }

        // Xử lý tự động chọn chỗ khi người dùng nhấn nút "Tự Động Chọn Chỗ"
        if (obj.equals(btnTuDongChonCho)) {
            xuLyTuDongChonCho();
        }

        // Xử lý chọn cả toa
        if (obj.equals(btnChonCaToa)) {
            chonCaToa();
        }

        // Xử lý bỏ chọn tất cả toa khi người dùng nhấn nút "Bỏ Chọn Cả Toa"
        if (obj.equals(btnBoChonCaToa)) {
            boChonCaToa();
        }

        // Trừ vé khi người dùng nhấn nút "Trừ Vé"
        if (obj.equals(btnTruVe)) {
            truVe();
        }

        // Chọn chiều đi khi người dùng nhấn nút "Chiều Đi"
        if (obj.equals(btnChieuDi)) {
            hienThiDanhSachChuyenTau("Chiều đi");
        }

        // Chọn chiều về khi người dùng nhấn nút "Chiều Về"
        if (obj.equals(btnChieuVe)) {
            hienThiDanhSachChuyenTau("Chiều về");
        }

        // Xử lý đơn tạm
        if (obj.equals(btnXuLyDonTam)) {
            xuLyDonTam();
        }

        // Xử lý xóa đơn tạm
        if (obj.equals(btnXoaDonTam)) {
            xoaDonTam();
        }
    }

    /**
     * Xử lý sự kiện khi người dùng nhập văn bản vào trường nhập liệu.
     * Phương thức này không thực hiện hành động gì trong trường hợp này.
     *
     * @param e sự kiện KeyEvent chứa thông tin về phím đã được nhấn.
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Xử lý sự kiện khi người dùng nhấn phím trên bàn phím.
     * Phương thức này không thực hiện hành động gì trong trường hợp này.
     *
     * @param e sự kiện KeyEvent chứa thông tin về phím đã được nhấn.
     */
    @Override
    public void keyPressed(KeyEvent e) {
    }

    /**
     * Xử lý sự kiện khi người dùng thả phím trên bàn phím.
     * Phương thức này không thực hiện hành động gì trong trường hợp này.
     *
     * @param e sự kiện KeyEvent chứa thông tin về phím đã được thả.
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }
}


