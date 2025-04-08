package gui;

import control.DAONhanVien;
import entity.NhanVien;
import entity.TaiKhoan;
import enums.EColor;
import gui.custom.CImage;
import utils.FormatString;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.Objects;

public class FrmTrangChinh extends JFrame {
    private JPanel pnlMain, pnlTop;
    private TaiKhoan taiKhoan;
    private NhanVien nhanVien;

    // Khai báo các pnl
    private PnlBanVe pnlBanVe;
    private PnlDoiVe pnlDoiVe;
    private PnlTraVe pnlTraVe;
    private PnlChuyenTau pnlChuyenTau;
    private PnlKhuyenMai pnlKhuyenMai;
    private PnlKhachHang pnlKhachHang;
    private PnlNhanVien pnlNhanVien;
    private PnlThongKe pnlThongKe;
    private PnlQuyDinh pnlQuyDinh;
    private PnlHuongDan pnlHuongDan;
    private AboutDialog aboutDialog;

    private JPanel pnlTopCenter;
    private JPanel pnlDangChon;
    private JPanel pnlLoading;

    public FrmTrangChinh(TaiKhoan taiKhoan, NhanVien nhanVien) throws RemoteException {
        this.taiKhoan = taiKhoan;
        this.nhanVien = nhanVien;
        this.pnlDangChon = pnlBanVe;

        // Thiết lập kích thước cửa sổ tối đa
        setTitle("Tàu Việt Express");
        setSize(1400, 720);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null); // Căn giữa cửa sổ

        // Confirm khi thoát chương trình
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn thoát chương trình?", "Thoát chương trình",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        initPnlTop();
        initPnlMain();
        add(pnlTop, BorderLayout.NORTH);
        add(pnlMain, BorderLayout.CENTER);
        //hàm check nếu mật khẩu của tài khoản là Abc1234.
        //nếu đăng nhâp lần đầu thì hiển thị dialog đổi mật khẩu
        // Hiển thị dialog đổi mật khẩu sau khi FrmTrangChinh đã hiển thị
        SwingUtilities.invokeLater(() -> {
            if (taiKhoan.getMatKhauHash().equals("$2a$10$hx.v7Xiy7I8Rpql8ONmMF.WZY3d6pfQmfpp2EgeXJajNJdUa9KVSa")) {
                new DlgDoiMatKhau(taiKhoan).setVisible(true);
            }
        });
    }

    private void initPnlTop() {
        pnlTop = new JPanel();
        pnlTop.setLayout(new BorderLayout());
        pnlTop.setBackground(EColor.TITLE_BAR_COLOR.getColor());
        pnlTop.setPreferredSize(new Dimension(0, 80));

        // Left
        JPanel pnlTopLeft = new JPanel();
        pnlTopLeft.setLayout(new BorderLayout());
        pnlTopLeft.setPreferredSize(new Dimension(180, 80));
        ImageIcon icoLogo = new CImage("images/icons/logo.png", 48, 48);
        setIconImage(icoLogo.getImage()); // Thay đổi logo mặc định của JFrame

        Box boxIcon = Box.createHorizontalBox();
        boxIcon.add(Box.createHorizontalStrut(10));
        boxIcon.add(new JLabel(icoLogo));
        boxIcon.add(Box.createHorizontalStrut(10));

        JLabel lblTitle = new JLabel("Tàu Việt Express");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 12));
        pnlTopLeft.add(boxIcon, BorderLayout.WEST);
        pnlTopLeft.add(lblTitle, BorderLayout.CENTER);
        pnlTopLeft.setBackground(EColor.TITLE_BAR_COLOR.getColor());

        pnlTopCenter = new JPanel();
        initCenter();

        // Right
        JPanel pnlTopRight = new JPanel();
        pnlTopRight.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        pnlTopRight.setBackground(EColor.TITLE_BAR_COLOR.getColor());
        pnlTopRight.setPreferredSize(new Dimension(280, 80));

        JLabel lblLogoNhanVien = new JLabel();
        // toi muon lay anh cua nhan vien do duoi db
        lblLogoNhanVien.setIcon(new CImage(DAONhanVien.getDuongDanAnh(nhanVien.getMaNV()), 48, 48));
        lblLogoNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showInfoNhanVien();
            }
        });

        JLabel lblTenNhanVien = new JLabel(FormatString.tomTatTen(nhanVien.getTenNV()));
        lblTenNhanVien.setFont(new Font("Arial", Font.BOLD, 16));
        lblTenNhanVien.setForeground(Color.BLACK);

        JLabel lblChucVu = new JLabel(nhanVien.getChucVu().getTenCV());
        lblChucVu.setFont(new Font("Arial", Font.BOLD, 14));
        lblChucVu.setForeground(Color.BLACK);

        JLabel lblDoiMatKhau = new JLabel();
        lblDoiMatKhau.setIcon(new CImage("images/icons/change-password.png", 30, 30));
        lblDoiMatKhau.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn đổi mật khẩu?", "Đổi mật khẩu",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    new DlgDoiMatKhau(taiKhoan).setVisible(true);
                }
            }
        });

        JLabel lblDangXuat = new JLabel();
        lblDangXuat.setIcon(new CImage("images/icons/log-out.png", 30, 30));
        lblDangXuat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn đăng xuất?", "Đăng xuất",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    dispose();
                    new FrmDangNhap().setVisible(true);
                }
            }
        });

        Box boxTenNhanVien = Box.createVerticalBox();
        boxTenNhanVien.add(lblTenNhanVien);
        boxTenNhanVien.add(lblChucVu);

        Box boxPnlTopRight = Box.createHorizontalBox();
        boxPnlTopRight.add(lblLogoNhanVien);
        boxPnlTopRight.add(Box.createHorizontalStrut(10));
        boxPnlTopRight.add(boxTenNhanVien); // TODO: Thay thế bằng thông tin nhân viên thực tế
        boxPnlTopRight.add(Box.createHorizontalStrut(10));
        boxPnlTopRight.add(lblDoiMatKhau);
        boxPnlTopRight.add(Box.createHorizontalStrut(10));
        boxPnlTopRight.add(lblDangXuat);

        JPanel pnlNhanVien = new JPanel();
        pnlNhanVien.setLayout(new BorderLayout());
        pnlNhanVien.add(boxPnlTopRight, BorderLayout.EAST);
        pnlNhanVien.setPreferredSize(new Dimension(300, 80));
        pnlNhanVien.setBackground(EColor.TITLE_BAR_COLOR.getColor());

        pnlTopRight.add(pnlNhanVien);

        pnlTop.add(pnlTopLeft, BorderLayout.WEST);
        pnlTop.add(pnlTopCenter, BorderLayout.CENTER);
        pnlTop.add(pnlTopRight, BorderLayout.EAST);
    }

    public void initPnlMain() throws RemoteException {
        pnlMain = new JPanel();
        pnlMain.setLayout(new BorderLayout());
        pnlMain.setBackground(EColor.BG_COLOR.getColor());

        if (nhanVien.getChucVu().getMaCV().equals("NV")) {
            pnlBanVe = new PnlBanVe(nhanVien);
            pnlMain.add(pnlBanVe, BorderLayout.CENTER);
            pnlDangChon = pnlBanVe;
        } else if (nhanVien.getChucVu().getMaCV().equals("QL")) {
            pnlChuyenTau = new PnlChuyenTau(nhanVien);
            pnlMain.add(pnlChuyenTau, BorderLayout.CENTER);
            pnlDangChon = pnlChuyenTau;
        }
        add(pnlMain, BorderLayout.CENTER);
    }

    // Set pnl Main mới
    public void setPnlMain(JPanel pnlMain) {
        this.pnlMain.removeAll();
        this.pnlMain.add(pnlMain, BorderLayout.CENTER);
        this.pnlMain.revalidate();
        this.pnlMain.repaint();

        pnlTopCenter.removeAll();
        pnlTopCenter.revalidate();
        pnlTopCenter.repaint();

        initCenter();
    }

    private JPanel createNavItem(String iconPath, String title, boolean dangChon) {
        JPanel navItem = new JPanel();
        // Tạo icon
        JLabel image = new JLabel(new CImage(iconPath, 40, 40));
        image.setHorizontalAlignment(SwingConstants.CENTER);

        // Thiết lập border nếu cần
        navItem.setBorder(new LineBorder(Color.BLACK));

        // Cấu hình layout
        navItem.setLayout(new BorderLayout());
        navItem.setPreferredSize(new Dimension(90, 80));
        navItem.add(image, BorderLayout.CENTER);

        if (dangChon) {
            navItem.setBackground(EColor.BTN_ACTIVE_BG_COLOR.getColor());
        }

        // Tạo title nếu có
        if (title != null && !title.isEmpty()) {
            JLabel lblTitle = new JLabel(title, SwingConstants.CENTER);
            lblTitle.setFont(new Font("Arial", Font.PLAIN, 14));
            lblTitle.setForeground(Color.BLACK);
            navItem.add(lblTitle, BorderLayout.SOUTH);  // Thêm title vào dưới icon
        }

        navItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));  // Thêm con trỏ hình bàn tay khi rê chuột

        return navItem;
    }

    public void initCenter() {
        boolean isNhanVien = nhanVien.getChucVu().getMaCV().equals("NV");
        boolean isQuanLy = nhanVien.getChucVu().getMaCV().equals("QL");

        // Center
        pnlTopCenter.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlTopCenter.setBackground(EColor.TITLE_BAR_COLOR.getColor());

        pnlLoading = createLoadingPanel(); // Tạo panel loading

        JPanel navBanVe = createNavItem("images/icons/ban-ve.png", "Bán vé", pnlDangChon != null && pnlDangChon.equals(pnlBanVe));
        navBanVe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setPnlWithLoading(pnlBanVe);
            }
        });

        JPanel navDoiVe = createNavItem("images/icons/doi-ve.png", "Đổi vé", pnlDangChon != null && pnlDangChon.equals(pnlDoiVe) );
        navDoiVe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (pnlDoiVe == null) {
                    try {
                        pnlDoiVe = new PnlDoiVe(nhanVien);
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                setPnlWithLoading(pnlDoiVe); // Gọi phương thức với hiệu ứng loading
            }
        });

        JPanel navTraVe = createNavItem("images/icons/tra-ve.png", "Trả vé", pnlDangChon != null && pnlDangChon.equals(pnlTraVe));
        navTraVe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (pnlTraVe == null) {
                    try {
                        pnlTraVe = new PnlTraVe();
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                setPnlWithLoading(pnlTraVe); // Gọi phương thức với hiệu ứng loading
            }
        });

        JPanel navChuyenTau = createNavItem("images/icons/train.png", "Chuyến tàu", pnlDangChon != null && pnlDangChon.equals(pnlChuyenTau));
        navChuyenTau.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (pnlChuyenTau == null) {
//                    pnlChuyenTau = new PnlChuyenTau(nhanVien);
                    try {
                        pnlChuyenTau = new PnlChuyenTau(nhanVien);
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                setPnlWithLoading(pnlChuyenTau);
            }
        });

        JPanel navKhuyenMai = createNavItem("images/icons/khuyen-mai.png", "Khuyến mãi", pnlDangChon != null && pnlDangChon.equals(pnlKhuyenMai));
        navKhuyenMai.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (pnlKhuyenMai == null) {
                    try {
                        pnlKhuyenMai = new PnlKhuyenMai(nhanVien);
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                setPnlWithLoading(pnlKhuyenMai);
            }
        });

        JPanel navKhachHang = createNavItem("images/icons/khach-hang.png", "Khách hàng", pnlDangChon != null && pnlDangChon.equals(pnlKhachHang));
        navKhachHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (pnlKhachHang == null) {
                    try {
                        pnlKhachHang = new PnlKhachHang(nhanVien);
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                setPnlWithLoading(pnlKhachHang);
            }
        });

        JPanel navNhanVien = createNavItem("images/icons/nhan-vien.png", "Nhân viên", pnlDangChon != null && pnlDangChon.equals(pnlNhanVien));
        navNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (pnlNhanVien == null) {
                    pnlNhanVien = new PnlNhanVien();
                }
                setPnlWithLoading(pnlNhanVien);
            }
        });

        JPanel navThongKe = createNavItem("images/icons/thong-ke.png", "Thống kê",pnlDangChon != null && pnlDangChon.equals(pnlThongKe));
        navThongKe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                if (pnlThongKe == null) {
//                    pnlThongKe = new PnlThongKe(nhanVien);
//                }
//                setPnlWithLoading(pnlThongKe);
                JOptionPane.showMessageDialog(null, "Chức năng đang được phát triển!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JPanel navQuyDinh = createNavItem("images/icons/quy-dinh.png", "Quy định", pnlDangChon != null && pnlDangChon.equals(pnlQuyDinh));
        navQuyDinh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (pnlQuyDinh == null) {
                    pnlQuyDinh = new PnlQuyDinh();
                }
                setPnlWithLoading(pnlQuyDinh);
            }
        });

        JPanel navHuongDan = createNavItem("images/icons/huong-dan.png", "Hướng dẫn", pnlDangChon != null && pnlDangChon.equals(pnlHuongDan));
        navHuongDan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (pnlHuongDan == null) {
                    pnlHuongDan = new PnlHuongDan();
                }
                setPnlWithLoading(pnlHuongDan);
            }
        });

        JPanel navAbout = createNavItem("images/icons/about.png", "About", false);
        navAbout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (aboutDialog == null) {
                    aboutDialog = new AboutDialog(FrmTrangChinh.this);
                }
                aboutDialog.setVisible(true);
            }
        });

        if (isNhanVien) {
            pnlTopCenter.add(navBanVe);
            pnlTopCenter.add(navDoiVe);
            pnlTopCenter.add(navTraVe);
        }

        pnlTopCenter.add(navChuyenTau);
        pnlTopCenter.add(navKhachHang);
        pnlTopCenter.add(navKhuyenMai);

        if (isQuanLy) {
            pnlTopCenter.add(navNhanVien);
        }

        pnlTopCenter.add(navThongKe);
        pnlTopCenter.add(navQuyDinh);
        pnlTopCenter.add(navHuongDan);
        pnlTopCenter.add(navAbout);
    }

    private JPanel createLoadingPanel() {
        JPanel pnlLoading = new JPanel(new BorderLayout());
        pnlLoading.setBackground(EColor.BG_COLOR.getColor());

        JLabel lblLoading = new JLabel();
        lblLoading.setHorizontalAlignment(SwingConstants.CENTER);
        lblLoading.setFont(new Font("Arial", Font.BOLD, 16));
        lblLoading.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/train-loading_2.gif")))); // Thêm ảnh GIF loading
        pnlLoading.add(lblLoading, BorderLayout.CENTER);
        return pnlLoading;
    }

    private void setPnlWithLoading(JPanel targetPanel) {
        // Hiển thị panel loading trước
        setPnlMain(pnlLoading);

        // Sử dụng SwingWorker để tải nội dung trong nền
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Giả lập quá trình tải (thực hiện hành động load ở đây)
                Thread.sleep(1000); // Giả lập thời gian tải
                return null;
            }

            @Override
            protected void done() {
                // Sau khi hoàn thành, hiển thị panel mục tiêu
                pnlDangChon = targetPanel;
                setPnlMain(targetPanel);
            }
        };
        worker.execute();
    }

    private void showInfoNhanVien() {
        // Hiển thị dialog thông tin nhân viên
        DlgThongTinNhanVien dlgNhanVien =  new DlgThongTinNhanVien(nhanVien);
        dlgNhanVien.setVisible(true);
    }
}
