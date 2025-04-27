package gui;

import ServiceLocator.RemoteLookup;
import control.IDAOChuyenTau;
import control.IDAOVe;
import entity.ChoNgoi;
import entity.ChuyenTau;
import entity.Ve;
import enums.ETrangThaiVe;
import gui.custom.CButton;
import gui.custom.CTextField;
import utils.FormatDate;
import utils.FormatMoney;
import utils.FormatNumber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class JdDoiVe extends JDialog {
    private static final double PHI_DOI_VE = 20000;
    private PnlDoiVe pnlDoiVe;
    private ChuyenTau chuyenTau;
    private ChoNgoi choNgoi;
    private Ve veCu;

    private JPanel pnlMain, pnlTitle, pnlContent, pnlFooter;
    private JLabel lblTitle,
            lblMaVeCu,
            lblChuyenTauCu, lblChoNgoiVeCu, lblThoiGianDiCu, lblGiaVeCu,
            lblChuyenTauMoi, lblChoNgoiMoi, lblThoiGianDiMoi, lblGiaVeMoi,
            lblKhachHang,
            lblKhuyenMai,
            lblPhiDoiVe, lblTienThu;

    private CTextField
            txtMaVeCu,
            txtChuyenTauCu, txtChoNgoiVeCu, txtThoiGianDiCu, txtGiaVeCu,
            txtChuyenTauMoi, txtChoNgoiMoi, txtThoiGianDiMoi, txtGiaVeMoi,
            txtKhachHang,
            txtKhuyenMai,
            txtPhiDoiVe, txtTienThu;
    private CButton btnXacNhan, btnHuy;
    private IDAOVe daoVe;
    private IDAOChuyenTau daoChuyenTau;

    public JdDoiVe(PnlDoiVe pnlDoiVe, Ve veCu, ChuyenTau chuyenTau, ChoNgoi choNgoi) throws RemoteException {
        this.veCu = veCu;
        this.pnlDoiVe = pnlDoiVe;
        this.chuyenTau = chuyenTau;
        this.choNgoi = choNgoi;
        this.daoVe = RemoteLookup.getRemote("veDao", IDAOVe.class);

        setTitle("Đổi vé");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setModal(true);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initComponents();
        initPnlTitle();
        initPnlContent();
        initPnlFooter();

        setContentPane(pnlMain);

        pnlMain.add(pnlTitle, BorderLayout.NORTH);
        pnlMain.add(pnlContent, BorderLayout.CENTER);
        pnlMain.add(pnlFooter, BorderLayout.SOUTH);
        
        docDuLieuLenField();
    }

    private void docDuLieuLenField() throws RemoteException {
        // Đọc dữ liệu vé cũ
//        ChuyenTau chuyenTauVeCu = DAOChuyenTau.getChuyenTauTheoMa(veCu.getChuyenTau().getMaChuyen());
        daoChuyenTau = RemoteLookup.getRemote("chuyenTauDao", IDAOChuyenTau.class);
        ChuyenTau chuyenTauVeCu = daoChuyenTau.getChuyenTauTheoMa(veCu.getChuyenTau().getMaChuyen());

        if (chuyenTauVeCu == null) {
            JOptionPane.showMessageDialog(this, "Chuyến tàu không tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        txtMaVeCu.setText(veCu.getMaVe());
        txtChuyenTauCu.setText(veCu.getChuyenTau().getMaChuyen() + " - " + veCu.getChuyenTau().getGaDi().getTenGa().replace("Ga Tàu", "") + " -> " + veCu.getChuyenTau().getGaDen().getTenGa().replace("Ga Tàu", ""));
        txtChoNgoiVeCu.setText("Toa " + veCu.getChoNgoi().getToa().getSoThuTu() + " - " + "Chỗ " + veCu.getChoNgoi().getSoThuTu()                                + " - " + veCu.getChoNgoi().getLoaiCho().getTenLC());
        txtThoiGianDiCu.setText(FormatDate.formatLocaldatetimeToString(veCu.getChuyenTau().getNgayGioDi()));
        txtGiaVeCu.setText(FormatMoney.format(veCu.getThanhTien()));

        // Đọc dữ liệu vé mới
        txtChuyenTauMoi.setText(chuyenTau.getMaChuyen() + " - " + chuyenTau.getGaDi().getTenGa().replace("Ga Tàu", "") + " -> " + chuyenTau.getGaDen().getTenGa().replace("Ga Tàu", ""));
        txtChoNgoiMoi.setText("Toa " + choNgoi.getToa().getSoThuTu() + " - " + "Chỗ " + choNgoi.getSoThuTu() + " - " + choNgoi.getLoaiCho().getTenLC());
        txtThoiGianDiMoi.setText(FormatDate.formatLocaldatetimeToString(chuyenTau.getNgayGioDi()));

        // Tính giá vé mới
        double tienVeMoi = choNgoi.getGiaCho()
                + (choNgoi.getGiaCho() * 0.1)
                - (choNgoi.getGiaCho() * veCu.getKhuyenMai().getPhanTramKM());
        txtGiaVeMoi.setText(FormatMoney.format(tienVeMoi));

        // Khuyến mãi
        txtKhuyenMai.setText(veCu.getKhuyenMai().getMaKM() + " - " + veCu.getKhuyenMai().getDoiTuong() + " - " + FormatNumber.formatNumberToPercent(veCu.getKhuyenMai().getPhanTramKM()));

        // Khách hàng
        txtKhachHang.setText(veCu.getKhachHang().getTenKH() + " - " + veCu.getKhachHang().getSdt());

        // Tiền thu bao gồm giá vé của chỗ ngồi và khuyến mãi
        double tienThu = tienVeMoi - veCu.getThanhTien() + PHI_DOI_VE;

        // Nếu tiền thu âm thì sửa label thành tiền trả
        if (tienThu < 0) {
            lblTienThu.setText("Tiền trả:");
            txtTienThu.setText(FormatMoney.format(-tienThu));
        } else {
            lblTienThu.setText("Tiền thu:");
            txtTienThu.setText(FormatMoney.format(tienThu));
        }
    }

    private void initPnlTitle() {
        pnlTitle = new JPanel();
        pnlTitle.setLayout(new BorderLayout());
        pnlTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Box boxMaVeCu = createHorizontalBox(lblMaVeCu, txtMaVeCu);

        pnlTitle.add(boxMaVeCu, BorderLayout.WEST);
        pnlTitle.add(lblTitle, BorderLayout.NORTH);
    }

    private void initPnlContent() {
        pnlContent = new JPanel();
        pnlContent.setLayout(new BoxLayout(pnlContent, BoxLayout.Y_AXIS));
        pnlContent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel pnlThongTinVeCu = new JPanel();
        pnlThongTinVeCu.setLayout(new BorderLayout());
        pnlThongTinVeCu.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Thông tin vé cũ"),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                )
        );

        Box boxThongTinVeCu = Box.createVerticalBox();
        boxThongTinVeCu.add(createHorizontalBox(lblChuyenTauCu, txtChuyenTauCu));
        boxThongTinVeCu.add(Box.createVerticalStrut(10));
        boxThongTinVeCu.add(createHorizontalBox(lblChoNgoiVeCu, txtChoNgoiVeCu));
        boxThongTinVeCu.add(Box.createVerticalStrut(10));
        boxThongTinVeCu.add(createHorizontalBox(lblThoiGianDiCu, txtThoiGianDiCu));
        boxThongTinVeCu.add(Box.createVerticalStrut(10));
        boxThongTinVeCu.add(createHorizontalBox(lblGiaVeCu, txtGiaVeCu));
        pnlThongTinVeCu.add(boxThongTinVeCu, BorderLayout.NORTH);

        JPanel pnlThongTinVeMoi = new JPanel();
        pnlThongTinVeMoi.setLayout(new BorderLayout());
        pnlThongTinVeMoi.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Thông tin vé mới"),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                )
        );

        Box boxThongTinVeMoi = Box.createVerticalBox();
        boxThongTinVeMoi.add(createHorizontalBox(lblChuyenTauMoi, txtChuyenTauMoi));
        boxThongTinVeMoi.add(Box.createVerticalStrut(10));
        boxThongTinVeMoi.add(createHorizontalBox(lblChoNgoiMoi, txtChoNgoiMoi));
        boxThongTinVeMoi.add(Box.createVerticalStrut(10));
        boxThongTinVeMoi.add(createHorizontalBox(lblThoiGianDiMoi, txtThoiGianDiMoi));
        boxThongTinVeMoi.add(Box.createVerticalStrut(10));
        boxThongTinVeMoi.add(createHorizontalBox(lblGiaVeMoi, txtGiaVeMoi));

        pnlThongTinVeMoi.add(boxThongTinVeMoi, BorderLayout.NORTH);

        lblThoiGianDiMoi.setPreferredSize(new Dimension(100, 0));
        lblChuyenTauCu.setPreferredSize(lblThoiGianDiMoi.getPreferredSize());
        lblChoNgoiVeCu.setPreferredSize(lblThoiGianDiMoi.getPreferredSize());
        lblThoiGianDiCu.setPreferredSize(lblThoiGianDiMoi.getPreferredSize());
        lblGiaVeCu.setPreferredSize(lblThoiGianDiMoi.getPreferredSize());
        lblChuyenTauMoi.setPreferredSize(lblThoiGianDiMoi.getPreferredSize());
        lblChoNgoiMoi.setPreferredSize(lblThoiGianDiMoi.getPreferredSize());
        lblThoiGianDiMoi.setPreferredSize(lblThoiGianDiMoi.getPreferredSize());
        lblGiaVeMoi.setPreferredSize(lblThoiGianDiMoi.getPreferredSize());
        lblKhuyenMai.setPreferredSize(lblThoiGianDiMoi.getPreferredSize());
        lblKhachHang.setPreferredSize(lblThoiGianDiMoi.getPreferredSize());

        JPanel pnlThongTinChung = new JPanel();
        pnlThongTinChung.setLayout(new BorderLayout());
        pnlThongTinChung.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Thông tin chung"),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                )
        );

        Box boxThongTinChung = Box.createVerticalBox();
        boxThongTinChung.add(createHorizontalBox(lblKhachHang, txtKhachHang));
        boxThongTinChung.add(Box.createVerticalStrut(10));
        boxThongTinChung.add(createHorizontalBox(lblKhuyenMai, txtKhuyenMai));
        pnlThongTinChung.add(boxThongTinChung, BorderLayout.NORTH);

        Box thongTinVeCuMoi = createHorizontalBox(pnlThongTinVeCu, pnlThongTinVeMoi);

        pnlContent.add(thongTinVeCuMoi);
        pnlContent.add(pnlThongTinChung);
    }

    private void initPnlFooter() {
        pnlFooter = new JPanel();
        pnlFooter.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnlFooter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tính phí đổi vé
        txtPhiDoiVe.setText(FormatMoney.format(PHI_DOI_VE));

        pnlFooter.add(lblPhiDoiVe);
        pnlFooter.add(txtPhiDoiVe);
        pnlFooter.add(Box.createHorizontalStrut(20));
        pnlFooter.add(lblTienThu);
        pnlFooter.add(txtTienThu);
        pnlFooter.add(Box.createHorizontalStrut(20));
        pnlFooter.add(btnXacNhan);
        pnlFooter.add(btnHuy);
    }

    private Box createHorizontalBox(JComponent... components) {
        Box box = Box.createHorizontalBox();
        for (int i = 0; i < components.length; i++) {
            box.add(components[i]);
            if (i < components.length - 1) {
                box.add(Box.createHorizontalStrut(10));
            }
        }
        return box;
    }

    private void initComponents() {
        pnlMain = new JPanel();
        pnlMain.setLayout(new BorderLayout());

        lblTitle = new JLabel("Đổi vé");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

        lblMaVeCu = new JLabel("Mã vé cũ:");
        lblGiaVeMoi = new JLabel("Giá vé mới:");
        lblGiaVeCu = new JLabel("Giá vé cũ:");
        lblChuyenTauCu = new JLabel("Chuyến tàu cũ:");
        lblChoNgoiVeCu = new JLabel("Chỗ ngồi cũ:");
        lblThoiGianDiCu = new JLabel("Thời gian đi cũ:");
        lblChuyenTauMoi = new JLabel("Chuyến tàu mới:");
        lblChoNgoiMoi = new JLabel("Chỗ ngồi mới:");
        lblThoiGianDiMoi = new JLabel("Thời gian đi mới:");
        lblKhuyenMai = new JLabel("Khuyến mãi:");
        lblKhachHang = new JLabel("Khách hàng:");
        lblPhiDoiVe = new JLabel("Phí đổi vé:");
        lblTienThu = new JLabel("Tiền thu:");

        txtMaVeCu = new CTextField();
        txtChuyenTauCu = new CTextField();
        txtChoNgoiVeCu = new CTextField();
        txtThoiGianDiCu = new CTextField();
        txtChuyenTauMoi = new CTextField();
        txtChoNgoiMoi = new CTextField();
        txtThoiGianDiMoi = new CTextField();
        txtGiaVeMoi = new CTextField();
        txtGiaVeCu = new CTextField();
        txtMaVeCu = new CTextField();
        txtKhuyenMai = new CTextField();
        txtKhachHang = new CTextField();
        txtPhiDoiVe = new CTextField();
        txtTienThu = new CTextField();

        txtMaVeCu.setEditable(false);
        txtChuyenTauCu.setEditable(false);
        txtChoNgoiVeCu.setEditable(false);
        txtThoiGianDiCu.setEditable(false);
        txtChuyenTauMoi.setEditable(false);
        txtChoNgoiMoi.setEditable(false);
        txtThoiGianDiMoi.setEditable(false);
        txtGiaVeMoi.setEditable(false);
        txtGiaVeCu.setEditable(false);
        txtMaVeCu.setEditable(false);
        txtKhuyenMai.setEditable(false);
        txtKhachHang.setEditable(false);
        txtPhiDoiVe.setEditable(false);
        txtTienThu.setEditable(false);

        btnXacNhan = new CButton("Xác nhận");
        btnHuy = new CButton("Hủy", CButton.SECONDARY);

        btnXacNhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    doiVe();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void doiVe() throws RemoteException {
        // Đổi vé bằng cách tạo ra vé mới có chuyến mới chỗ ngồi mới
        // HoaDon hoaDon, LoaiVe loaiVe, LocalDateTime ngayGioXuatVe, ChoNgoi choNgoi, ChuyenTau chuyenTau, KhachHang khachHang, double thue, KhuyenMai khuyenMai, String trangThai)
        Ve veMoi = new Ve(veCu.getHoaDon(), veCu.getLoaiVe(), veCu.getNgayGioXuatVe(), choNgoi,
                veCu.getChuyenTau(), veCu.getKhachHang(), veCu.getThue(), veCu.getKhuyenMai(),
                ETrangThaiVe.DA_DOI.name());

        // Cập nhật vé cũ
        veCu.setTrangThai(ETrangThaiVe.VE_DUOC_DOI.name());

        // Thêm vé mới vào CSDL
        if (daoVe.themVeCoKhuyenMai(veMoi)) {
            JOptionPane.showMessageDialog(null, "Đổi vé thành công");
            pnlDoiVe.hienThiDanhSachChoNgoi();
        } else {
            JOptionPane.showMessageDialog(null, "Đổi vé thất bại");
        }

        // Cập nhật vé cũ
        if (!daoVe.suaVe(veCu) ) {
            JOptionPane.showMessageDialog(null, "Cập nhật vé cũ thất bại");
        }

        pnlDoiVe.getListVeDaBan().add(veMoi);
        pnlDoiVe.getListVeDaBan().remove(veCu);
        pnlDoiVe.hienThiDanhSachChoNgoi();

        dispose();
    }
}
