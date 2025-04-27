package gui;

import ServiceLocator.RemoteLookup;
import control.IDAOThongKe;
import control.IDAOVe;
import entity.NhanVien;
import enums.EColor;
import gui.components.DatePicker;
import gui.custom.CTable;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import utils.FormatMoney;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.List;
import java.util.*;

public class PnlThongKe extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnl_ve;
	private JPanel pnl_nhanVien;
	private JPanel pnl_khachHang;
	private JPanel pnl_soLuong;
	private JPanel center_SL;
	private JPanel center_KH;
	private JPanel center_NV;
	private JPanel center_Ve;
	private JTable table;
	private JLabel lbl_loaiThoiGian;
	private JLabel lbl_LoaiKhachHang_KH;
	private JLabel lbl_HinhThucThongKe_SL;
	private JLabel lbl_loaiThoiGian_VeBan;
	private JLabel lblLocThem;
	private JLabel lbl_LoaiThongKe_KH;
	private JLabel lblLocThem_NhanVien;
	private JLabel lblLocThem_VeBan;
	private JLabel lbl_thangBatDau;
	private JLabel lbl_thangBatDau_KH;
	private JLabel lbl_thangBatDau_SL;
	private JLabel lbl_thangBatDau_NhanVien;
	private JLabel lbl_thangBatDau_VeBan;
	private JLabel lbl_thangKetThuc;
	private JLabel lbl_thangKetThuc_KH;
	private JLabel lbl_thangKetThuc_SL;
	private JLabel lbl_thangKetThuc_NhanVien;
	private JLabel lbl_thangKetThuc_VeBan;
	private JComboBox cboLoaiThoiGian_KH;
	private JComboBox cboLoaiThoiGian_SL;
	private JComboBox cboLoaiVe;
	private DefaultTableModel model_tab;
	private JButton btn_Tim_Loc_Ve;
	private JButton btn_Tim_Loc_KH;
	private JButton btn_Tim_Loc_SL;
	private ChartPanel chartPanel;
	private JPanel pnl_bieu_Do_Ve;
	private JTable table_2;
	private JTable table_3;
	private JLabel lbl_hinhThucThongKe_NhanVien;
	private JComboBox cbohinhThucThongKe;
	private JTextField txtMaNhanVien_NV;
	private JDatePickerImpl thangBatDauPicker_NV;
	private JDatePickerImpl thangKetThucPicker_NV;
	private JDatePickerImpl thangBatDauPicker_Ve;
	private JDatePickerImpl thangKetThucPicker_Ve;
	private JDatePickerImpl thangBatDauPicker_KH;
	private JDatePickerImpl thangKetThucPicker_KH;
	private JComboBox cbohinhThucThongKe_NV;
	private JDatePickerImpl thangBatDauPicker_SL;
	private JDatePickerImpl thangKetThucPicker_SL;
	private DefaultTableModel modelTOPNHANVIEN;
	private JTable table_TOPNHANVIEN;
	private JTable table_LoaiGheUaThich;
	private DefaultTableModel model_LoaiGheUaThich;
	private gui.components.DatePicker DatePicker;
	private JLabel lbl_tongDT_TQ;
	private control.impl.DAOVe DAOVe;
	private JComboBox comboBox_TQ;
	private JLabel lbl_phapTram_TQ;
	private JLabel lbl_hinhTangGiam;
	private JLabel lbl_tg_Tb_CaoNhat;
	private JLabel lbl_TGBanChamNhat_TQ;
	private JPanel panel_17;
	private JLabel tongDoanhThu_Ve;
	private JLabel tongve_VE;
	private Date startDate_Ve;
	private Date endDate_Ve;
	private JLabel lbl_doanhThuLoaiVe;
	private JLabel lbl_tenDoanhThuLoaiVe;
	private JLabel lbl_tongSLLoaiVe;
	private JLabel lbl_tenTongSLLoaiVe;
	private JComboBox cboLoaiThoiGian_Ve;
	private JTabbedPane tabbedPane_Ve;
	private CTable table_ChiTiet_Ve;
	private DefaultTableModel model_ChiTiet_Ve;
	private JPanel pnl_Bang_Ve;
	private JPanel pnl_BieuDo_UD;

	private JLabel tongVe_NV;
	private JLabel tongDoanhThu_NV;
	private JLabel lbl_TongVe_NV;
	private JLabel tongDoanhThuofCaNhan_NV;
	private JLabel lbl_tongDoanhThuofCaNhan_NV;
	private JLabel tongVeofCaNhan_NV;
	private JLabel lbl_tongVeofCaNhan_NV;
	private JButton btn_Tim_Loc_NV;
	private JPanel pnl_bieu_Do_NV;
	private JPanel pnl_Bang_NV;
	private DefaultTableModel model_table_NV;
	private CTable table_NV;
	private JLabel tiLeKHQuayLai_KH;
	private JLabel tiLeKHQuayLai_KH1;
	private JPanel pnl_bieu_Do_KH;
	private JLabel tgTrungBinhquayLai_KH;
	private JLabel lbl_tgTrungBinhquayLai_KH;
	private JLabel lblChinh_KH;
	private JLabel lbl_ChuChinh_KH;
	private JLabel lblChinh1_KH;
	private JLabel lbl_ChuChinh1_KH;
	private JComboBox cbo_LoaiThongKe_KH;
	private JLabel doanhThuSauThue;
	private JPanel pnl_Bang_KH;
	private DefaultTableModel model_KH;
	private CTable table_KH;
	private JTabbedPane tabbedPane_KH;
	private JComboBox cboLoaiThongKe_SL
			;
	private JLabel tileDoi_Tra_SL;
	private JLabel lbl_tiLeDoiTra_Sl;
	private JLabel soLuongDoiTra;
	private JLabel lbl_soLuongDoiTra;
	private JPanel pnl_bieu_Do_SL;
	private JPanel pnl_Bang_SL;
	private DefaultTableModel model_SL;
	private CTable table_SL;
	private NhanVien nv;
	private IDAOVe daoVe;
	private IDAOThongKe daoThongKe;
	/**
	 * Create the panel.
	 */

	public PnlThongKe(NhanVien nhanVien) throws RemoteException {
		this.nv = nhanVien;
		this.daoVe = RemoteLookup.getRemote("veDao",IDAOVe.class);
		this.daoThongKe = RemoteLookup.getRemote("thongKeDao", IDAOThongKe.class);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(957, 622));
		setBackground(EColor.BG_COLOR.getColor());

		if(nv.getChucVu().getMaCV().equals("QL")){
			JTabbedPane tabbedPane = new JTabbedPane() {};
			tabbedPane.setBackground(new Color(255, 255, 255));
			tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
			tabbedPane.setUI(new BasicTabbedPaneUI() {
				@Override
				protected void installDefaults() {
					super.installDefaults();
					contentBorderInsets = new Insets(0, 0, 0, 0);  // Loại bỏ viền của tab nội dung
					tabAreaInsets = new Insets(0, 0, 0, 0);  // Loại bỏ viền xung quanh tab
				}

				@Override
				protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
					if (isSelected) {
						g.setColor(new Color(39, 186, 224));  // Màu nền khi tab được chọn
					} else {
						g.setColor(Color.LIGHT_GRAY);  // Màu nền cho các tab không được chọn
					}
					g.fillRect(x, y, w, h);  // Vẽ nền tab
				}

				@Override
				protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
					// Không vẽ viền tab
				}

				@Override
				protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
					// Không vẽ viền xung quanh nội dung tab
				}

				@Override
				protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
					// Tăng cỡ chữ cho tiêu đề tab
					if (isSelected) {
						g.setFont(new Font("Tahoma", Font.BOLD, 15));
						g.setColor(Color.WHITE);  // Màu chữ khi tab được chọn
					} else {
						g.setFont(new Font("Tahoma", Font.PLAIN, 15));
						g.setColor(Color.BLACK);  // Màu chữ khi tab không được chọn
					}
					g.drawString(title, textRect.x, textRect.y + metrics.getAscent());

				}
				@Override
				protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
					return super.calculateTabWidth(tabPlacement, tabIndex, metrics) + 40;  // Tăng chiều rộng thêm 30px
				}

				@Override
				protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
					return super.calculateTabHeight(tabPlacement, tabIndex, fontHeight) + 10;  // Tăng chiều cao thêm 10px (nếu muốn)
				}

				@Override
				protected int getTabRunOverlay(int tabPlacement) {
					return 15;  // Tăng khoảng cách giữa các dòng tab (trường hợp nhiều dòng tab)
				}
			});
			add(tabbedPane, BorderLayout.CENTER);
			tabbedPane.addTab("Thống Kê Chung", null, BoLocChung(), null);

			pnl_ve = new JPanel();
			pnl_ve.setLayout(new BorderLayout(0, 0));
			pnl_ve.add(BoLoc_Ve(), BorderLayout.NORTH);
			center_Ve =  Center_Ve();
			pnl_ve.add(center_Ve, BorderLayout.CENTER);
			tabbedPane.addTab("Vé Bán", null, pnl_ve, null);

			pnl_nhanVien = new JPanel();
			pnl_nhanVien.setLayout(new BorderLayout(0, 0));
			pnl_nhanVien.add(BoLocNhanVien(), BorderLayout.NORTH);
			center_NV = CenterNhanVien();
			pnl_nhanVien.add(center_NV, BorderLayout.CENTER);
			tabbedPane.addTab("Nhân viên", null, pnl_nhanVien, null);

			pnl_khachHang = new JPanel();
			pnl_khachHang.setLayout(new BorderLayout(0, 0));
			pnl_khachHang.add(BoLocKhachHang(), BorderLayout.NORTH);
			center_KH = CenterKhachHang();
			pnl_khachHang.add(center_KH, BorderLayout.CENTER);
			tabbedPane.addTab("Khách hàng", null, pnl_khachHang, null);

			pnl_soLuong = new JPanel();
			pnl_soLuong.setLayout(new BorderLayout(0, 0));
			pnl_soLuong.setLayout(new BorderLayout(0, 0));
			pnl_soLuong.add(BoLocDoiTra(), BorderLayout.NORTH);
			center_SL = CenterSL();
			pnl_soLuong.add(center_SL, BorderLayout.CENTER);
			tabbedPane.addTab("Đổi/trả", null, pnl_soLuong, null);
			comboBox_TQ.addActionListener(this);
		}
		if(nv.getChucVu().getMaCV().equals("NV")){
			pnl_nhanVien = new JPanel(new BorderLayout());
			pnl_nhanVien.add(BoLocNhanVien(), BorderLayout.NORTH);
			center_NV = CenterNhanVien();
			pnl_nhanVien.add(center_NV, BorderLayout.CENTER);
			add(pnl_nhanVien, BorderLayout.CENTER);
		}
	}

	public JPanel BoLocChung() throws RemoteException {
		JPanel pnl_thongKeChung = new JPanel();
		pnl_thongKeChung.setBackground(new Color(255, 255, 255));
		pnl_thongKeChung.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panel = new JPanel();
		pnl_thongKeChung.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		panel_2.add(rigidArea, BorderLayout.SOUTH);

		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		panel_2.add(rigidArea_1, BorderLayout.NORTH);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_2.add(panel_3, BorderLayout.WEST);
		panel_3.setLayout(new BorderLayout(0, 0));

		Component rigidArea_2 = Box.createRigidArea(new Dimension(30, 20));
		panel_3.add(rigidArea_2, BorderLayout.WEST);

		comboBox_TQ = new JComboBox<>();
		comboBox_TQ.setModel(new DefaultComboBoxModel(new String[] {"Theo ngày", "Theo tuần", "Theo tháng", "Theo quý", "Theo năm"}));
		comboBox_TQ.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(comboBox_TQ);

		Component rigidArea_3 = Box.createRigidArea(new Dimension(250, 20));
		panel_3.add(rigidArea_3, BorderLayout.NORTH);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_4.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BorderLayout(0, 0));

		Component rigidArea_8 = Box.createRigidArea(new Dimension(20, 20));
		panel_5.add(rigidArea_8, BorderLayout.WEST);

		Component rigidArea_9 = Box.createRigidArea(new Dimension(20, 20));
		panel_5.add(rigidArea_9, BorderLayout.NORTH);

		Component rigidArea_10 = Box.createRigidArea(new Dimension(20, 20));
		panel_5.add(rigidArea_10, BorderLayout.SOUTH);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		panel_5.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BorderLayout(0, 0));

		//Component rigidArea_11 = Box.createRigidArea(new Dimension(20, 20));
		//panel_7.add(rigidArea_11, BorderLayout.WEST);

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.WHITE);
		panel_7.add(panel_8, BorderLayout.SOUTH);
		panel_8.setLayout(new BorderLayout(0, 0));

		JPanel panel_12 = new JPanel();
		panel_12.setBackground(Color.WHITE);
		FlowLayout flowLayout_1 = (FlowLayout) panel_12.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_8.add(panel_12, BorderLayout.NORTH);

		JLabel lblNewLabel_3 = new JLabel("Thời gian trung bình bán chạy nhất trong ngày:");
		lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel_12.add(lblNewLabel_3);

		lbl_tg_Tb_CaoNhat = new JLabel("11:40");
		lbl_tg_Tb_CaoNhat.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_12.add(lbl_tg_Tb_CaoNhat);

		JPanel panel_13 = new JPanel();
		panel_13.setBackground(Color.WHITE);
		FlowLayout flowLayout_2 = (FlowLayout) panel_13.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_8.add(panel_13, BorderLayout.SOUTH);

		JLabel lblNewLabel_4 = new JLabel("Thời gian trung bình bán chậm nhất trong ngày:");
		lblNewLabel_4.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel_13.add(lblNewLabel_4);

		lbl_TGBanChamNhat_TQ = new JLabel("7:30");
		lbl_TGBanChamNhat_TQ.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_13.add(lbl_TGBanChamNhat_TQ);

		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panel_10.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_7.add(panel_10, BorderLayout.NORTH);

		JLabel lblNewLabel_2 = new JLabel("Tổng doanh thu:");
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 20));
		panel_10.add(lblNewLabel_2);

		lbl_tongDT_TQ = new JLabel("0");
		lbl_tongDT_TQ.setFont(new Font("Dialog", Font.BOLD, 20));
		panel_10.add(lbl_tongDT_TQ);

		lbl_hinhTangGiam = new JLabel("");
		lbl_hinhTangGiam.setFont(new Font("Dialog", Font.BOLD, 20));
		panel_10.add(lbl_hinhTangGiam);

		lbl_phapTram_TQ = new JLabel("0%");
		lbl_phapTram_TQ.setFont(new Font("Dialog", Font.BOLD, 20));
		panel_10.add(lbl_phapTram_TQ);
//
		JPanel panel_18 = new JPanel();
		panel_18.setBackground(Color.WHITE);
		panel_7.add(panel_18, BorderLayout.CENTER);
		panel_18.setLayout(new BorderLayout(0, 0));

		JPanel panel_19 = new JPanel();
		panel_19.setBackground(Color.WHITE);
		FlowLayout flowLayout_4 = (FlowLayout) panel_19.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_18.add(panel_19, BorderLayout.NORTH);

		JLabel lblNewLabel_2_1 = new JLabel("Tổng doanh thu sau thuế:");
		lblNewLabel_2_1.setFont(new Font("Dialog", Font.BOLD, 20));
		panel_19.add(lblNewLabel_2_1);

		doanhThuSauThue = new JLabel("");
		doanhThuSauThue.setFont(new Font("Dialog", Font.BOLD, 20));
		panel_19.add(doanhThuSauThue);

		Component rigidArea_112 = Box.createRigidArea(new Dimension(20, 20));
		panel_18.add(rigidArea_112, BorderLayout.SOUTH);
//
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_4.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));

		Component rigidArea_4 = Box.createRigidArea(new Dimension(20, 20));
		panel_6.add(rigidArea_4, BorderLayout.SOUTH);

		Component rigidArea_5 = Box.createRigidArea(new Dimension(20, 20));
		panel_6.add(rigidArea_5, BorderLayout.WEST);

		Component rigidArea_6 = Box.createRigidArea(new Dimension(20, 20));
		panel_6.add(rigidArea_6, BorderLayout.EAST);

		Component rigidArea_7 = Box.createRigidArea(new Dimension(20, 20));
		panel_6.add(rigidArea_7, BorderLayout.NORTH);

		JPanel panel_14 = new JPanel();
		panel_14.setBackground(new Color(240, 240, 240));
		panel_6.add(panel_14, BorderLayout.CENTER);
		panel_14.setLayout(new BorderLayout(0, 0));

		JPanel panel_15 = new JPanel();
		panel_15.setBackground(Color.WHITE);
		FlowLayout flowLayout_3 = (FlowLayout) panel_15.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_14.add(panel_15, BorderLayout.NORTH);

		JLabel lblNewLabel_11 = new JLabel("Nhân viên danh sách bán tốt nhất:  ");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel_15.add(lblNewLabel_11);


		String[] colHeader = {"Top","Mã nhân viên","Họ tên","Tổng tiền"};
		modelTOPNHANVIEN = new DefaultTableModel(colHeader,0);
		table_TOPNHANVIEN = new CTable(modelTOPNHANVIEN);
		table_TOPNHANVIEN.setRowHeight(30);
		panel_14.add(new JScrollPane(table_TOPNHANVIEN), BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		pnl_thongKeChung.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 2, 0, 0));

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.WHITE);
		panel_1.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));

		Component rigidArea_12 = Box.createRigidArea(new Dimension(20, 20));
		panel_9.add(rigidArea_12, BorderLayout.WEST);

		Component rigidArea_13 = Box.createRigidArea(new Dimension(20, 20));
		panel_9.add(rigidArea_13, BorderLayout.SOUTH);

		Component rigidArea_14 = Box.createRigidArea(new Dimension(20, 20));
		panel_9.add(rigidArea_14, BorderLayout.NORTH);

		Component rigidArea_15 = Box.createRigidArea(new Dimension(20, 20));
		panel_9.add(rigidArea_15, BorderLayout.EAST);

		JPanel panel_16 = new JPanel();
		panel_16.setBackground(Color.WHITE);
		panel_9.add(panel_16, BorderLayout.CENTER);
		panel_16.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_12 = new JLabel("Top loại ghế được ưu chuộng:\r\n");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel_16.add(lblNewLabel_12, BorderLayout.NORTH);

		String[] colHeader_GheUaThich = {"Top","Mã loại ghế","Tên loại ghế","Tổng vé loại ghế cũ","Tổng vé loại ghế mới","Biến động"};
		model_LoaiGheUaThich = new DefaultTableModel(colHeader_GheUaThich,0);
		table_LoaiGheUaThich = new CTable(model_LoaiGheUaThich);
		table_LoaiGheUaThich.setRowHeight(30);
		panel_16.add(new JScrollPane(table_LoaiGheUaThich), BorderLayout.CENTER);

		JPanel panel_11 = new JPanel();
		panel_11.setBackground(Color.WHITE);
		panel_1.add(panel_11);
		panel_11.setLayout(new BorderLayout(0, 0));

		Component rigidArea_16 = Box.createRigidArea(new Dimension(20, 20));
		panel_11.add(rigidArea_16, BorderLayout.WEST);

		Component rigidArea_18 = Box.createRigidArea(new Dimension(20, 20));
		panel_11.add(rigidArea_18, BorderLayout.SOUTH);

		Component rigidArea_19 = Box.createRigidArea(new Dimension(20, 20));
		panel_11.add(rigidArea_19, BorderLayout.EAST);

		panel_17 = new JPanel();
		panel_17.setBackground(Color.WHITE);
		panel_11.add(panel_17, BorderLayout.CENTER);
		panel_17.setLayout(new BorderLayout(0, 0));

		table_3 = new CTable();
        LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate lastDayOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        Date startDate = Date.from(firstDayOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(lastDayOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Map<Integer, Double> revenueByHour = daoThongKe.getRevenueByHour();
		panel_17.add(createChartNgayChung(revenueByHour), BorderLayout.CENTER);

		CapNhatTongDoanhThu_TQ();
		CapNhatTBBanChayVaKhongChay();
		CapNhatBanChamNhat();
		updateTableTopNhanVien();
		updateTableLoaiGheUaThich();
		return pnl_thongKeChung;
	}
	public JPanel BoLoc_Ve() throws RemoteException {
		JPanel pnl_boLoc_Ve = new JPanel();
		pnl_boLoc_Ve.setBackground(Color.WHITE);

		pnl_boLoc_Ve.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		pnl_boLoc_Ve.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		Component rigidArea_10 = Box.createRigidArea(new Dimension(20, 20));
		panel_2.add(rigidArea_10, BorderLayout.NORTH);

		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		pnl_boLoc_Ve.add(rigidArea, BorderLayout.WEST);

		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		pnl_boLoc_Ve.add(rigidArea_1, BorderLayout.EAST);

		Box verticalBox = Box.createVerticalBox();
		pnl_boLoc_Ve.add(verticalBox, BorderLayout.CENTER);

		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);

		Component rigidArea_6 = Box.createRigidArea(new Dimension(50, 30));
		horizontalBox.add(rigidArea_6);

		lbl_loaiThoiGian_VeBan = new JLabel("Loại thời gian:");
		lbl_loaiThoiGian_VeBan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		horizontalBox.add(lbl_loaiThoiGian_VeBan);

		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		horizontalBox.add(rigidArea_2);

		cboLoaiThoiGian_Ve = new JComboBox();
		cboLoaiThoiGian_Ve.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cboLoaiThoiGian_Ve.setModel(new DefaultComboBoxModel(new String[] {"Theo ngày", "Theo tuần", "Theo tháng", "Theo quý", "Theo năm"}));
		horizontalBox.add(cboLoaiThoiGian_Ve);

		Component rigidArea_16 = Box.createRigidArea(new Dimension(500, 20));
		horizontalBox.add(rigidArea_16);

		lblLocThem_VeBan = new JLabel("Loại vé:");
		lblLocThem_VeBan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		horizontalBox.add(lblLocThem_VeBan);

		Component rigidArea_4 = Box.createRigidArea(new Dimension(20, 20));
		horizontalBox.add(rigidArea_4);

		cboLoaiVe = new JComboBox();
		cboLoaiVe.setModel(new DefaultComboBoxModel(new String[] {"Tất cả", "Vé một chiều", "Vé khứ hồi"}));
		cboLoaiVe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		horizontalBox.add(cboLoaiVe);

		Component rigidArea_8 = Box.createRigidArea(new Dimension(50, 20));
		horizontalBox.add(rigidArea_8);

		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		horizontalBox_1.add(verticalStrut_2);

		Box horizontalBox_2 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_2);

		Component rigidArea_7 = Box.createRigidArea(new Dimension(50, 30));
		horizontalBox_2.add(rigidArea_7);

		lbl_thangBatDau_VeBan = new JLabel("Thời gian bắt đầu:");
		lbl_thangBatDau_VeBan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		horizontalBox_2.add(lbl_thangBatDau_VeBan);

		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		horizontalBox_2.add(rigidArea_3);

		Calendar calendar = Calendar.getInstance();
		Date endDate = calendar.getTime();

		calendar.add(Calendar.DAY_OF_YEAR, -7);
		Date startDate = calendar.getTime();

		thangBatDauPicker_Ve = DatePicker.getDatePicker();
		thangBatDauPicker_Ve.setFont(new Font("Tahoma", Font.PLAIN, 12));
		thangBatDauPicker_Ve.getModel().setDate(startDate.getYear() + 1900, startDate.getMonth(), startDate.getDate());
		thangBatDauPicker_Ve.getModel().setSelected(true);
		horizontalBox_2.add(thangBatDauPicker_Ve);

		Component rigidArea_16_1 = Box.createRigidArea(new Dimension(480, 20));
		horizontalBox_2.add(rigidArea_16_1);

		lbl_thangKetThuc_VeBan = new JLabel("Thời gian kết thúc:");
		lbl_thangKetThuc_VeBan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		horizontalBox_2.add(lbl_thangKetThuc_VeBan);

		lblLocThem_VeBan.setPreferredSize(lbl_thangBatDau_VeBan.getPreferredSize());
		lbl_loaiThoiGian_VeBan.setPreferredSize(lbl_thangBatDau_VeBan.getPreferredSize());

		Component rigidArea_5 = Box.createRigidArea(new Dimension(20, 20));
		horizontalBox_2.add(rigidArea_5);

		thangKetThucPicker_Ve = DatePicker.getDatePicker();
		thangKetThucPicker_Ve.setFont(new Font("Tahoma", Font.PLAIN, 12));
		thangKetThucPicker_Ve.getModel().setDate(endDate.getYear() + 1900, endDate.getMonth(), endDate.getDate());
		thangKetThucPicker_Ve.getModel().setSelected(true);
		horizontalBox_2.add(thangKetThucPicker_Ve);

		thangBatDauPicker_Ve.setPreferredSize(cboLoaiThoiGian_Ve.getPreferredSize());
		thangKetThucPicker_Ve.setPreferredSize(cboLoaiThoiGian_Ve.getPreferredSize());
		cboLoaiVe.setPreferredSize(cboLoaiThoiGian_Ve.getPreferredSize());

		Component rigidArea_9 = Box.createRigidArea(new Dimension(50, 20));
		horizontalBox_2.add(rigidArea_9);

		JPanel panel_3 = new JPanel();
		pnl_boLoc_Ve.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_3.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel_4.add(panel, BorderLayout.WEST);

		Component rigidArea_13 = Box.createRigidArea(new Dimension(20, 20));
		panel.add(rigidArea_13);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.WHITE);
		panel.add(panel_1_1);
		panel_1_1.setLayout(new BorderLayout(0, 0));

		tongDoanhThu_Ve = new JLabel("");
		tongDoanhThu_Ve.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_1_1.add(tongDoanhThu_Ve, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Tổng doanh thu");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_1_1.add(lblNewLabel, BorderLayout.SOUTH);

		Component rigidArea_13_1 = Box.createRigidArea(new Dimension(20, 20));
		panel.add(rigidArea_13_1);

		JPanel panel_9_1 = new JPanel();
		panel_9_1.setBackground(Color.WHITE);
		panel.add(panel_9_1);
		panel_9_1.setLayout(new BorderLayout(0, 0));

		tongve_VE = new JLabel("");
		tongve_VE.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_9_1.add(tongve_VE, BorderLayout.NORTH);

		JLabel lblNewLabel_1_1 = new JLabel("Tổng số vé bán");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_9_1.add(lblNewLabel_1_1, BorderLayout.SOUTH);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_4.add(panel_1, BorderLayout.EAST);

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.WHITE);
		panel_1.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));

		lbl_doanhThuLoaiVe = new JLabel("");
		lbl_doanhThuLoaiVe.setBackground(Color.WHITE);
		lbl_doanhThuLoaiVe.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_9.add(lbl_doanhThuLoaiVe, BorderLayout.NORTH);

		lbl_tenDoanhThuLoaiVe = new JLabel("");
		lbl_tenDoanhThuLoaiVe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_9.add(lbl_tenDoanhThuLoaiVe, BorderLayout.SOUTH);

		Component rigidArea_13_1_2 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_13_1_2);

		JPanel panel_9_1_1 = new JPanel();
		panel_9_1_1.setBackground(Color.WHITE);
		panel_1.add(panel_9_1_1);
		panel_9_1_1.setLayout(new BorderLayout(0, 0));

		lbl_tongSLLoaiVe = new JLabel("");
		lbl_tongSLLoaiVe.setBackground(Color.WHITE);
		lbl_tongSLLoaiVe.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_9_1_1.add(lbl_tongSLLoaiVe, BorderLayout.NORTH);

		lbl_tenTongSLLoaiVe = new JLabel("");
		lbl_tenTongSLLoaiVe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_9_1_1.add(lbl_tenTongSLLoaiVe, BorderLayout.SOUTH);

		Component rigidArea_13_1_1 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_13_1_1);

		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BorderLayout(0, 0));

		JSeparator separator_1 = new JSeparator();
		panel_5.add(separator_1, BorderLayout.SOUTH);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_5.add(panel_6, BorderLayout.NORTH);
		panel_6.setLayout(new BorderLayout(0, 0));

		Component rigidArea_15_1 = Box.createRigidArea(new Dimension(20, 10));
		panel_6.add(rigidArea_15_1, BorderLayout.SOUTH);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		panel_6.add(panel_7, BorderLayout.WEST);
		panel_7.setLayout(new BorderLayout(0, 0));

		Component rigidArea_11 = Box.createRigidArea(new Dimension(70, 30));
		panel_7.add(rigidArea_11, BorderLayout.WEST);

		btn_Tim_Loc_Ve = new JButton("      Tìm      ");
		btn_Tim_Loc_Ve.setFont(new Font("Tahoma", Font.BOLD, 15));

		panel_7.add(btn_Tim_Loc_Ve);

		Component rigidArea_15 = Box.createRigidArea(new Dimension(20, 10));
		panel_6.add(rigidArea_15, BorderLayout.NORTH);

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.WHITE);
		panel_3.add(panel_8, BorderLayout.SOUTH);
		panel_8.setLayout(new BorderLayout(0, 0));

		JSeparator separator = new JSeparator();
		panel_8.add(separator, BorderLayout.NORTH);

		Component rigidArea_12 = Box.createRigidArea(new Dimension(20, 10));
		panel_8.add(rigidArea_12, BorderLayout.SOUTH);
		btn_Tim_Loc_Ve.addActionListener(this);

		CapNhatDoanhThu_Ve_Ve();

		return pnl_boLoc_Ve;
	}
	public JPanel BoLocNhanVien() throws RemoteException {
		JPanel pnl_boLoc_NV = new JPanel();
		pnl_boLoc_NV.setBackground(Color.WHITE);

		pnl_boLoc_NV.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		pnl_boLoc_NV.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		Component rigidArea_10 = Box.createRigidArea(new Dimension(20, 20));
		panel_2.add(rigidArea_10, BorderLayout.NORTH);

		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		pnl_boLoc_NV.add(rigidArea, BorderLayout.WEST);

		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		pnl_boLoc_NV.add(rigidArea_1, BorderLayout.EAST);

		Box verticalBox = Box.createVerticalBox();
		pnl_boLoc_NV.add(verticalBox, BorderLayout.CENTER);

		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);

		Component rigidArea_6 = Box.createRigidArea(new Dimension(50, 30));
		horizontalBox.add(rigidArea_6);

		lbl_hinhThucThongKe_NhanVien = new JLabel("Hình thức thống kê:");
		lbl_hinhThucThongKe_NhanVien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		horizontalBox.add(lbl_hinhThucThongKe_NhanVien);

		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		horizontalBox.add(rigidArea_2);

		cbohinhThucThongKe_NV = new JComboBox();
		cbohinhThucThongKe_NV.setFont(new Font("Dialog", Font.PLAIN, 15));
		cbohinhThucThongKe_NV.setModel(new DefaultComboBoxModel(new String[] {"Theo ngày", "Theo tuần", "Theo tháng", "Theo quý", "Theo năm"}));
		horizontalBox.add(cbohinhThucThongKe_NV);

		Component rigidArea_16 = Box.createRigidArea(new Dimension(430, 20));
		horizontalBox.add(rigidArea_16);

		lblLocThem_NhanVien = new JLabel("Mã nhân viên:");
		lblLocThem_NhanVien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		horizontalBox.add(lblLocThem_NhanVien);

		Component rigidArea_4 = Box.createRigidArea(new Dimension(20, 20));
		horizontalBox.add(rigidArea_4);

		txtMaNhanVien_NV = new JTextField();
		txtMaNhanVien_NV.setFont(new Font("Dialog", Font.PLAIN, 15));
		horizontalBox.add(txtMaNhanVien_NV);
		txtMaNhanVien_NV.setColumns(50);

		if(nv.getChucVu().getMaCV().equals("NV")) {
			txtMaNhanVien_NV.setText(nv.getMaNV());
			txtMaNhanVien_NV.setEditable(false);
		}else{
			txtMaNhanVien_NV.setEditable(true);
		}

		Component rigidArea_8 = Box.createRigidArea(new Dimension(50, 20));
		horizontalBox.add(rigidArea_8);

		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		horizontalBox_1.add(verticalStrut_2);

		Box horizontalBox_2 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_2);

		Component rigidArea_7 = Box.createRigidArea(new Dimension(50, 30));
		horizontalBox_2.add(rigidArea_7);

		lbl_thangBatDau_NhanVien = new JLabel("Thời gian bắt đầu:");
		lbl_thangBatDau_NhanVien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		horizontalBox_2.add(lbl_thangBatDau_NhanVien);

		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		horizontalBox_2.add(rigidArea_3);

		Calendar calendar = Calendar.getInstance();
		Date endDate = calendar.getTime();

		// Calculate the date one week before the current date
		calendar.add(Calendar.DAY_OF_YEAR, -7);
		Date startDate = calendar.getTime();

		thangBatDauPicker_NV = new DatePicker().getDatePicker();
		thangBatDauPicker_NV.setFont(new Font("Tahoma", Font.PLAIN, 12));
		thangBatDauPicker_NV.getModel().setDate(startDate.getYear() + 1900, startDate.getMonth(), startDate.getDate());
		thangBatDauPicker_NV.getModel().setSelected(true);
		horizontalBox_2.add(thangBatDauPicker_NV);

		Component rigidArea_16_1 = Box.createRigidArea(new Dimension(400, 20));
		horizontalBox_2.add(rigidArea_16_1);

		lbl_thangKetThuc_NhanVien = new JLabel("Thời gian kết thúc:");
		lbl_thangKetThuc_NhanVien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		horizontalBox_2.add(lbl_thangKetThuc_NhanVien);

		lblLocThem_NhanVien.setPreferredSize(lbl_hinhThucThongKe_NhanVien.getPreferredSize());
		lbl_thangBatDau_NhanVien.setPreferredSize(lbl_hinhThucThongKe_NhanVien.getPreferredSize());
		lbl_thangKetThuc_NhanVien.setPreferredSize(lbl_hinhThucThongKe_NhanVien.getPreferredSize());

		Component rigidArea_5 = Box.createRigidArea(new Dimension(20, 20));
		horizontalBox_2.add(rigidArea_5);

		thangKetThucPicker_NV = DatePicker.getDatePicker();
		thangKetThucPicker_NV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		thangKetThucPicker_NV.getModel().setDate(endDate.getYear() + 1900, endDate.getMonth(), endDate.getDate());
		thangKetThucPicker_NV.getModel().setSelected(true);
		horizontalBox_2.add(thangKetThucPicker_NV);

		cbohinhThucThongKe_NV.setPreferredSize(new Dimension(450,20));
		thangBatDauPicker_NV.setPreferredSize(cbohinhThucThongKe_NV.getPreferredSize());
		txtMaNhanVien_NV.setPreferredSize(cbohinhThucThongKe_NV.getPreferredSize());
		thangKetThucPicker_NV.setPreferredSize(cbohinhThucThongKe_NV.getPreferredSize());

		Component rigidArea_9 = Box.createRigidArea(new Dimension(50, 20));
		horizontalBox_2.add(rigidArea_9);

		JPanel panel_3 = new JPanel();
		pnl_boLoc_NV.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_3.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel_4.add(panel, BorderLayout.WEST);

		Component rigidArea_13 = Box.createRigidArea(new Dimension(20, 20));
		panel.add(rigidArea_13);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.WHITE);
		panel.add(panel_1_1);
		panel_1_1.setLayout(new BorderLayout(0, 0));

		tongDoanhThu_NV = new JLabel();
		tongDoanhThu_NV.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_1_1.add(tongDoanhThu_NV, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Tổng doanh thu");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_1_1.add(lblNewLabel, BorderLayout.SOUTH);

		Component rigidArea_13_1 = Box.createRigidArea(new Dimension(20, 20));
		panel.add(rigidArea_13_1);

		JPanel panel_9_1 = new JPanel();
		panel_9_1.setBackground(Color.WHITE);
		panel.add(panel_9_1);
		panel_9_1.setLayout(new BorderLayout(0, 0));

		tongVe_NV = new JLabel();
		tongVe_NV.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_9_1.add(tongVe_NV, BorderLayout.NORTH);

		lbl_TongVe_NV = new JLabel("Tổng vé bán");
		lbl_TongVe_NV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_9_1.add(lbl_TongVe_NV, BorderLayout.SOUTH);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_4.add(panel_1, BorderLayout.EAST);

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.WHITE);
		panel_1.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));

		tongDoanhThuofCaNhan_NV = new JLabel("");
		tongDoanhThuofCaNhan_NV.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_9.add(tongDoanhThuofCaNhan_NV, BorderLayout.NORTH);

		lbl_tongDoanhThuofCaNhan_NV = new JLabel(""); // Tổng doanh thu của XXX
		lbl_tongDoanhThuofCaNhan_NV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_9.add(lbl_tongDoanhThuofCaNhan_NV, BorderLayout.SOUTH);

		Component rigidArea_13_1_2 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_13_1_2);

		JPanel panel_9_1_1 = new JPanel();
		panel_9_1_1.setBackground(Color.WHITE);
		panel_1.add(panel_9_1_1);
		panel_9_1_1.setLayout(new BorderLayout(0, 0));

		tongVeofCaNhan_NV= new JLabel("");
		tongVeofCaNhan_NV.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_9_1_1.add(tongVeofCaNhan_NV, BorderLayout.NORTH);

		lbl_tongVeofCaNhan_NV = new JLabel(""); //Vé bán được
		lbl_tongVeofCaNhan_NV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_9_1_1.add(lbl_tongVeofCaNhan_NV, BorderLayout.SOUTH);

		Component rigidArea_13_1_1 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_13_1_1);

		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BorderLayout(0, 0));

		JSeparator separator_1 = new JSeparator();
		panel_5.add(separator_1, BorderLayout.SOUTH);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_5.add(panel_6, BorderLayout.NORTH);
		panel_6.setLayout(new BorderLayout(0, 0));

		Component rigidArea_15_1 = Box.createRigidArea(new Dimension(20, 10));
		panel_6.add(rigidArea_15_1, BorderLayout.SOUTH);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		panel_6.add(panel_7, BorderLayout.WEST);
		panel_7.setLayout(new BorderLayout(0, 0));

		Component rigidArea_11 = Box.createRigidArea(new Dimension(70, 25));
		panel_7.add(rigidArea_11, BorderLayout.WEST);

		btn_Tim_Loc_NV = new JButton("      Tìm      ");
		btn_Tim_Loc_NV.setFont(new Font("Tahoma", Font.BOLD, 15));

		panel_7.add(btn_Tim_Loc_NV);

		Component rigidArea_15 = Box.createRigidArea(new Dimension(20, 10));
		panel_6.add(rigidArea_15, BorderLayout.NORTH);

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.WHITE);
		panel_3.add(panel_8, BorderLayout.SOUTH);
		panel_8.setLayout(new BorderLayout(0, 0));

		JSeparator separator = new JSeparator();
		panel_8.add(separator, BorderLayout.NORTH);

		Component rigidArea_12 = Box.createRigidArea(new Dimension(20, 10));
		panel_8.add(rigidArea_12, BorderLayout.SOUTH);
		btn_Tim_Loc_NV.addActionListener(this);

		CapNhatDoanhThu_BanHang_NV();
		return pnl_boLoc_NV;
	}
	public JPanel BoLocKhachHang() throws RemoteException {
		JPanel pnl_boLoc_KH = new JPanel();
		pnl_boLoc_KH.setBackground(Color.WHITE);

		pnl_boLoc_KH.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		pnl_boLoc_KH.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		Component rigidArea_10 = Box.createRigidArea(new Dimension(20, 20));
		panel_2.add(rigidArea_10, BorderLayout.NORTH);

		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		pnl_boLoc_KH.add(rigidArea, BorderLayout.WEST);

		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		pnl_boLoc_KH.add(rigidArea_1, BorderLayout.EAST);

		Box verticalBox = Box.createVerticalBox();
		pnl_boLoc_KH.add(verticalBox, BorderLayout.CENTER);

		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);

		Component rigidArea_6 = Box.createRigidArea(new Dimension(50, 30));
		horizontalBox.add(rigidArea_6);

		lbl_LoaiKhachHang_KH = new JLabel("Đối tượng khách hàng:");
		lbl_LoaiKhachHang_KH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		horizontalBox.add(lbl_LoaiKhachHang_KH);

		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		horizontalBox.add(rigidArea_2);

		cboLoaiThoiGian_KH = new JComboBox();
		cboLoaiThoiGian_KH.setFont(new Font("Dialog", Font.PLAIN, 15));
		cboLoaiThoiGian_KH.setModel(new DefaultComboBoxModel(new String[] {"Theo ngày", "Theo tuần", "Theo tháng", "Theo quý", "Theo năm"}));
		horizontalBox.add(cboLoaiThoiGian_KH);

		Component rigidArea_16 = Box.createRigidArea(new Dimension(450, 20));
		horizontalBox.add(rigidArea_16);

		lbl_LoaiThongKe_KH = new JLabel("Loại thống kê:");
		lbl_LoaiThongKe_KH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		horizontalBox.add(lbl_LoaiThongKe_KH);

		Component rigidArea_4 = Box.createRigidArea(new Dimension(20, 20));
		horizontalBox.add(rigidArea_4);

		cbo_LoaiThongKe_KH = new JComboBox();
		cbo_LoaiThongKe_KH.setFont(new Font("Dialog", Font.PLAIN, 15));
		cbo_LoaiThongKe_KH.setModel(new DefaultComboBoxModel(new String[] {"Số lượng khách hàng", "Tỉ lệ áp dụng khuyến mãi", "Top khách hàng đi nhiều nhất"}));
		horizontalBox.add(cbo_LoaiThongKe_KH);

		Component rigidArea_8 = Box.createRigidArea(new Dimension(50, 20));
		horizontalBox.add(rigidArea_8);

		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		horizontalBox_1.add(verticalStrut_2);

		Box horizontalBox_2 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_2);

		Component rigidArea_7 = Box.createRigidArea(new Dimension(50, 30));
		horizontalBox_2.add(rigidArea_7);

		lbl_thangBatDau_KH = new JLabel("Thời gian bắt đầu:");
		lbl_thangBatDau_KH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		horizontalBox_2.add(lbl_thangBatDau_KH);

		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		horizontalBox_2.add(rigidArea_3);

		Calendar calendar = Calendar.getInstance();
		Date endDate = calendar.getTime();

		// Calculate the date one week before the current date
		calendar.add(Calendar.DAY_OF_YEAR, -7);
		Date startDate = calendar.getTime();

		thangBatDauPicker_KH = new DatePicker().getDatePicker();
		thangBatDauPicker_KH.setFont(new Font("Tahoma", Font.PLAIN, 12));
		thangBatDauPicker_KH.getModel().setDate(startDate.getYear() + 1900, startDate.getMonth(), startDate.getDate());
		thangBatDauPicker_KH.getModel().setSelected(true);
		horizontalBox_2.add(thangBatDauPicker_KH);

		Component rigidArea_16_1 = Box.createRigidArea(new Dimension(450, 20));
		horizontalBox_2.add(rigidArea_16_1);

		lbl_thangKetThuc_KH = new JLabel("Thời gian kết thúc:");
		lbl_thangKetThuc_KH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		horizontalBox_2.add(lbl_thangKetThuc_KH);

		lbl_LoaiThongKe_KH.setPreferredSize(lbl_LoaiKhachHang_KH.getPreferredSize());
		lbl_thangKetThuc_KH.setPreferredSize(lbl_LoaiKhachHang_KH.getPreferredSize());
		lbl_thangBatDau_KH.setPreferredSize(lbl_LoaiKhachHang_KH.getPreferredSize());


		Component rigidArea_5 = Box.createRigidArea(new Dimension(20, 20));
		horizontalBox_2.add(rigidArea_5);

		thangKetThucPicker_KH = DatePicker.getDatePicker();
		thangKetThucPicker_KH.setFont(new Font("Tahoma", Font.PLAIN, 12));
		thangKetThucPicker_KH.getModel().setDate(endDate.getYear() + 1900, endDate.getMonth(), endDate.getDate());
		thangKetThucPicker_KH.getModel().setSelected(true);
		horizontalBox_2.add(thangKetThucPicker_KH);

		cbo_LoaiThongKe_KH.setPreferredSize(cboLoaiThoiGian_KH.getPreferredSize());
		thangBatDauPicker_KH.setPreferredSize(cboLoaiThoiGian_KH.getPreferredSize());
		thangKetThucPicker_KH.setPreferredSize(cboLoaiThoiGian_KH.getPreferredSize());

		Component rigidArea_9 = Box.createRigidArea(new Dimension(50, 20));
		horizontalBox_2.add(rigidArea_9);

		JPanel panel_3 = new JPanel();
		pnl_boLoc_KH.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_3.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel_4.add(panel, BorderLayout.WEST);

		Component rigidArea_13 = Box.createRigidArea(new Dimension(20, 20));
		panel.add(rigidArea_13);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.WHITE);
		panel.add(panel_1_1);
		panel_1_1.setLayout(new BorderLayout(0, 0));

		lblChinh_KH = new JLabel("");
		lblChinh_KH.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_1_1.add(lblChinh_KH, BorderLayout.NORTH);

		lbl_ChuChinh_KH= new JLabel("");
		lbl_ChuChinh_KH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_1_1.add(lbl_ChuChinh_KH, BorderLayout.SOUTH);

		Component rigidArea_13_1 = Box.createRigidArea(new Dimension(20, 20));
		panel.add(rigidArea_13_1);

		JPanel panel_9_1 = new JPanel();
		panel_9_1.setBackground(Color.WHITE);
		panel.add(panel_9_1);
		panel_9_1.setLayout(new BorderLayout(0, 0));

		lblChinh1_KH = new JLabel("");
		lblChinh1_KH.setBackground(Color.WHITE);
		lblChinh1_KH.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_9_1.add(lblChinh1_KH, BorderLayout.NORTH);

		lbl_ChuChinh1_KH = new JLabel("");
		lbl_ChuChinh1_KH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_9_1.add(lbl_ChuChinh1_KH, BorderLayout.SOUTH);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_4.add(panel_1, BorderLayout.EAST);

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.WHITE);
		panel_1.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));

		tiLeKHQuayLai_KH = new JLabel("");
		tiLeKHQuayLai_KH.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_9.add(tiLeKHQuayLai_KH, BorderLayout.NORTH);

		tiLeKHQuayLai_KH1 = new JLabel("");
		tiLeKHQuayLai_KH1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_9.add(tiLeKHQuayLai_KH1, BorderLayout.SOUTH);

		Component rigidArea_13_1_2 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_13_1_2);

		JPanel panel_9_1_1 = new JPanel();
		panel_9_1_1.setBackground(Color.WHITE);
		panel_1.add(panel_9_1_1);
		panel_9_1_1.setLayout(new BorderLayout(0, 0));

		tgTrungBinhquayLai_KH = new JLabel("");
		tgTrungBinhquayLai_KH.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_9_1_1.add(tgTrungBinhquayLai_KH, BorderLayout.NORTH);

		lbl_tgTrungBinhquayLai_KH = new JLabel("");
		lbl_tgTrungBinhquayLai_KH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_9_1_1.add(lbl_tgTrungBinhquayLai_KH, BorderLayout.SOUTH);

		Component rigidArea_13_1_1 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_13_1_1);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_3.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BorderLayout(0, 0));

		JSeparator separator_1 = new JSeparator();
		panel_5.add(separator_1, BorderLayout.SOUTH);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_5.add(panel_6, BorderLayout.NORTH);
		panel_6.setLayout(new BorderLayout(0, 0));

		Component rigidArea_15_1 = Box.createRigidArea(new Dimension(20, 10));
		panel_6.add(rigidArea_15_1, BorderLayout.SOUTH);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		panel_6.add(panel_7, BorderLayout.WEST);
		panel_7.setLayout(new BorderLayout(0, 0));

		Component rigidArea_11 = Box.createRigidArea(new Dimension(70, 25));
		panel_7.add(rigidArea_11, BorderLayout.WEST);

		btn_Tim_Loc_KH = new JButton("      Tìm      ");
		btn_Tim_Loc_KH.setFont(new Font("Tahoma", Font.BOLD, 15));

		panel_7.add(btn_Tim_Loc_KH);

		Component rigidArea_15 = Box.createRigidArea(new Dimension(20, 10));
		panel_6.add(rigidArea_15, BorderLayout.NORTH);

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.WHITE);
		panel_3.add(panel_8, BorderLayout.SOUTH);
		panel_8.setLayout(new BorderLayout(0, 0));

		JSeparator separator = new JSeparator();
		panel_8.add(separator, BorderLayout.NORTH);

		Component rigidArea_12 = Box.createRigidArea(new Dimension(20, 10));
		panel_8.add(rigidArea_12, BorderLayout.SOUTH);
		btn_Tim_Loc_KH.addActionListener(this);
		cbo_LoaiThongKe_KH.addActionListener(this);
		CapNhatTiLeKhachHanQuayLai();
		return pnl_boLoc_KH;
	}
	public JPanel BoLocDoiTra() {
		JPanel pnl_boLoc_KH = new JPanel();
		pnl_boLoc_KH.setBackground(Color.WHITE);

		pnl_boLoc_KH.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		pnl_boLoc_KH.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		Component rigidArea_10 = Box.createRigidArea(new Dimension(20, 20));
		panel_2.add(rigidArea_10, BorderLayout.NORTH);

		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		pnl_boLoc_KH.add(rigidArea, BorderLayout.WEST);

		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		pnl_boLoc_KH.add(rigidArea_1, BorderLayout.EAST);

		Box verticalBox = Box.createVerticalBox();
		pnl_boLoc_KH.add(verticalBox, BorderLayout.CENTER);

		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);

		Component rigidArea_6 = Box.createRigidArea(new Dimension(50, 30));
		horizontalBox.add(rigidArea_6);

		lbl_HinhThucThongKe_SL = new JLabel("Hình thức thống kê:");
		lbl_HinhThucThongKe_SL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		horizontalBox.add(lbl_HinhThucThongKe_SL);

		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		horizontalBox.add(rigidArea_2);

		cboLoaiThoiGian_SL = new JComboBox();
		cboLoaiThoiGian_SL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cboLoaiThoiGian_SL.setModel(new DefaultComboBoxModel(new String[] {"Theo ngày", "Theo tuần", "Theo tháng", "Theo quý", "Theo năm"}));
		horizontalBox.add(cboLoaiThoiGian_SL);

		Component rigidArea_16 = Box.createRigidArea(new Dimension(400, 20));
		horizontalBox.add(rigidArea_16);

		JLabel lbl_LoaiThongKe_SL = new JLabel("Loại thống kê:");
		lbl_LoaiThongKe_SL.setPreferredSize(new Dimension(148, 19));
		lbl_LoaiThongKe_SL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		horizontalBox.add(lbl_LoaiThongKe_SL);

		cboLoaiThongKe_SL = new JComboBox();
		cboLoaiThongKe_SL.setFont(new Font("Dialog", Font.PLAIN, 15));
		cboLoaiThongKe_SL.setModel(new DefaultComboBoxModel(new String[]{"Đổi vé", "Trả vé"}));
		horizontalBox.add(cboLoaiThongKe_SL);
		cboLoaiThongKe_SL.setPreferredSize(cboLoaiThoiGian_SL.getPreferredSize());

		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		horizontalBox_1.add(verticalStrut_2);

		Box horizontalBox_2 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_2);

		Component rigidArea_7 = Box.createRigidArea(new Dimension(50, 30));
		horizontalBox_2.add(rigidArea_7);

		lbl_thangBatDau_SL = new JLabel("Thời gian bắt đầu:");
		lbl_thangBatDau_SL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		horizontalBox_2.add(lbl_thangBatDau_SL);

		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		horizontalBox_2.add(rigidArea_3);

		Calendar calendar = Calendar.getInstance();
		Date endDate = calendar.getTime();

		// Calculate the date one week before the current date
		calendar.add(Calendar.DAY_OF_YEAR, -7);
		Date startDate = calendar.getTime();

		thangBatDauPicker_SL = new DatePicker().getDatePicker();
		thangBatDauPicker_SL.setFont(new Font("Tahoma", Font.PLAIN, 12));
		thangBatDauPicker_SL.getModel().setDate(startDate.getYear() + 1900, startDate.getMonth(), startDate.getDate());
		thangBatDauPicker_SL.getModel().setSelected(true);
		horizontalBox_2.add(thangBatDauPicker_SL);

		Component rigidArea_16_1 = Box.createRigidArea(new Dimension(450, 20));
		horizontalBox_2.add(rigidArea_16_1);

		lbl_thangKetThuc_SL = new JLabel("Thời gian kết thúc:");
		lbl_thangKetThuc_SL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		horizontalBox_2.add(lbl_thangKetThuc_SL);
		lbl_thangKetThuc_SL.setPreferredSize(lbl_HinhThucThongKe_SL.getPreferredSize());
		lbl_thangBatDau_SL.setPreferredSize(lbl_HinhThucThongKe_SL.getPreferredSize());


		Component rigidArea_5 = Box.createRigidArea(new Dimension(20, 20));
		horizontalBox_2.add(rigidArea_5);

		thangKetThucPicker_SL = DatePicker.getDatePicker();
		thangKetThucPicker_SL.setFont(new Font("Tahoma", Font.PLAIN, 12));
		thangKetThucPicker_SL.getModel().setDate(endDate.getYear() + 1900, endDate.getMonth(), endDate.getDate());
		thangKetThucPicker_SL.getModel().setSelected(true);
		horizontalBox_2.add(thangKetThucPicker_SL);
		thangBatDauPicker_SL.setPreferredSize(cboLoaiThoiGian_SL.getPreferredSize());
		thangKetThucPicker_SL.setPreferredSize(cboLoaiThoiGian_SL.getPreferredSize());

		Component rigidArea_9 = Box.createRigidArea(new Dimension(50, 20));
		horizontalBox_2.add(rigidArea_9);

		JPanel panel_3 = new JPanel();
		pnl_boLoc_KH.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_3.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel_4.add(panel, BorderLayout.WEST);

		Component rigidArea_13 = Box.createRigidArea(new Dimension(20, 20));
		panel.add(rigidArea_13);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.WHITE);
		panel.add(panel_1_1);
		panel_1_1.setLayout(new BorderLayout(0, 0));
////////////////
		tileDoi_Tra_SL = new JLabel("");
		tileDoi_Tra_SL.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_1_1.add(tileDoi_Tra_SL, BorderLayout.NORTH);

		lbl_tiLeDoiTra_Sl = new JLabel("");
		lbl_tiLeDoiTra_Sl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_1_1.add(lbl_tiLeDoiTra_Sl, BorderLayout.SOUTH);

		Component rigidArea_13_1 = Box.createRigidArea(new Dimension(20, 20));
		panel.add(rigidArea_13_1);

		JPanel panel_9_1 = new JPanel();
		panel_9_1.setBackground(Color.WHITE);
		panel.add(panel_9_1);
		panel_9_1.setLayout(new BorderLayout(0, 0));

		soLuongDoiTra = new JLabel("");
		soLuongDoiTra.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_9_1.add(soLuongDoiTra, BorderLayout.NORTH);

		lbl_soLuongDoiTra = new JLabel("VND(Tổng số vé bán)");
		lbl_soLuongDoiTra.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_9_1.add(lbl_soLuongDoiTra, BorderLayout.SOUTH);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_4.add(panel_1, BorderLayout.EAST);

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.WHITE);
		panel_1.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));

		JLabel caiGiDo = new JLabel("");
		caiGiDo.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_9.add(caiGiDo, BorderLayout.NORTH);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_9.add(lblNewLabel_1, BorderLayout.SOUTH);

		Component rigidArea_13_1_2 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_13_1_2);

		JPanel panel_9_1_1 = new JPanel();
		panel_9_1_1.setBackground(Color.WHITE);
		panel_1.add(panel_9_1_1);
		panel_9_1_1.setLayout(new BorderLayout(0, 0));

		JLabel caiGiDo_1_1 = new JLabel("");
		caiGiDo_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_9_1_1.add(caiGiDo_1_1, BorderLayout.NORTH);

		JLabel lblNewLabel_1_1_1 = new JLabel("");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_9_1_1.add(lblNewLabel_1_1_1, BorderLayout.SOUTH);

		Component rigidArea_13_1_1 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_13_1_1);

		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BorderLayout(0, 0));

		JSeparator separator_1 = new JSeparator();
		panel_5.add(separator_1, BorderLayout.SOUTH);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_5.add(panel_6, BorderLayout.NORTH);
		panel_6.setLayout(new BorderLayout(0, 0));

		Component rigidArea_15_1 = Box.createRigidArea(new Dimension(20, 10));
		rigidArea_15_1.setBackground(Color.WHITE);
		panel_6.add(rigidArea_15_1, BorderLayout.SOUTH);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		panel_6.add(panel_7, BorderLayout.WEST);
		panel_7.setLayout(new BorderLayout(0, 0));

		Component rigidArea_11 = Box.createRigidArea(new Dimension(70, 25));
		panel_7.add(rigidArea_11, BorderLayout.WEST);

		btn_Tim_Loc_SL = new JButton("      Tìm      ");
		btn_Tim_Loc_SL.setFont(new Font("Tahoma", Font.BOLD, 15));

		panel_7.add(btn_Tim_Loc_SL);

		Component rigidArea_15 = Box.createRigidArea(new Dimension(20, 10));
		panel_6.add(rigidArea_15, BorderLayout.NORTH);

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.WHITE);
		panel_3.add(panel_8, BorderLayout.SOUTH);
		panel_8.setLayout(new BorderLayout(0, 0));

		JSeparator separator = new JSeparator();
		panel_8.add(separator, BorderLayout.NORTH);

		Component rigidArea_12 = Box.createRigidArea(new Dimension(20, 10));
		rigidArea_12.setBackground(Color.WHITE);
		panel_8.add(rigidArea_12, BorderLayout.SOUTH);
		btn_Tim_Loc_SL.addActionListener(this);

		return pnl_boLoc_KH;
	}
	public JPanel CenterSL() throws RemoteException {
		JPanel pnl_center = new JPanel();
		pnl_center.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBackground(Color.WHITE);
		tabbedPane_1.setUI(new BasicTabbedPaneUI() {
			@Override
			protected void installDefaults() {
				super.installDefaults();
				contentBorderInsets = new Insets(0, 0, 0, 0);  // Loại bỏ viền của tab nội dung
				tabAreaInsets = new Insets(0, 0, 0, 0);  // Loại bỏ viền xung quanh tab
			}

			@Override
			protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
				if (isSelected) {
					g.setColor(new Color(24, 133, 231));  // Màu nền khi tab được chọn
				} else {
					g.setColor(new Color(188, 187, 190));  // Màu nền cho các tab không được chọn
				}
				g.fillRect(x, y, w, h);  // Vẽ nền tab
			}

			@Override
			protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
				// Không vẽ viền tab
			}

			@Override
			protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
				// Không vẽ viền xung quanh nội dung tab
			}

			@Override
			protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
				// Tăng cỡ chữ cho tiêu đề tab
				if (isSelected) {
					g.setFont(new Font("Tahoma", Font.BOLD, 15));
					g.setColor(Color.WHITE);  // Màu chữ khi tab được chọn
				} else {
					g.setFont(new Font("Tahoma", Font.PLAIN, 15));
					g.setColor(Color.WHITE);  // Màu chữ khi tab không được chọn
				}
				g.drawString(title, textRect.x, textRect.y + metrics.getAscent());

			}
			@Override
			protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
				return super.calculateTabWidth(tabPlacement, tabIndex, metrics) + 100;  // Tăng chiều rộng thêm 30px
			}

			@Override
			protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
				return super.calculateTabHeight(tabPlacement, tabIndex, fontHeight) + 10;  // Tăng chiều cao thêm 10px (nếu muốn)
			}

			@Override
			protected int getTabRunOverlay(int tabPlacement) {
				return 10;  // Tăng khoảng cách giữa các dòng tab (trường hợp nhiều dòng tab)
			}
		});
		pnl_center.add(tabbedPane_1);

		pnl_bieu_Do_SL = new JPanel(); // SL
		pnl_bieu_Do_SL.setBackground(Color.WHITE);
		tabbedPane_1.addTab("Tổng quan", null, pnl_bieu_Do_SL, null);

		pnl_Bang_SL = new JPanel();
		tabbedPane_1.addTab("Chi tiết", null, pnl_Bang_SL, null);
		pnl_Bang_SL.setLayout(new BorderLayout(0, 0));

		String tencot[] = {"Cột 1"};
		model_SL = new DefaultTableModel(tencot,0);
		table_SL = new CTable(model_SL);
		pnl_Bang_SL.add(new JScrollPane(table_SL));
		CapNhatDoiTra();
		return pnl_center;
	}
	public JPanel Center_Ve() throws RemoteException {

		JPanel pnl_center_Ve = new JPanel();
		pnl_center_Ve.setLayout(new BorderLayout(0, 0));
		tabbedPane_Ve = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_Ve.setBackground(Color.WHITE);
		tabbedPane_Ve.setUI(new BasicTabbedPaneUI() {
            @Override
            protected void installDefaults() {
                super.installDefaults();
                contentBorderInsets = new Insets(0, 0, 0, 0);  // Loại bỏ viền của tab nội dung
                tabAreaInsets = new Insets(0, 0, 0, 0);  // Loại bỏ viền xung quanh tab
            }

            @Override
            protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
            	if (isSelected) {
                    g.setColor(new Color(24, 133, 231));  // Màu nền khi tab được chọn
                } else {
                    g.setColor(new Color(188, 187, 190));  // Màu nền cho các tab không được chọn
                }
                g.fillRect(x, y, w, h);  // Vẽ nền tab
            }

            @Override
            protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
                // Không vẽ viền tab
            }

            @Override
            protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
                // Không vẽ viền xung quanh nội dung tab
            }

            @Override
            protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
                // Tăng cỡ chữ cho tiêu đề tab
                if (isSelected) {
                    g.setFont(new Font("Tahoma", Font.BOLD, 15));
                    g.setColor(Color.WHITE);  // Màu chữ khi tab được chọn
                } else {
                    g.setFont(new Font("Tahoma", Font.PLAIN, 15));
                    g.setColor(Color.WHITE);  // Màu chữ khi tab không được chọn
                }
                g.drawString(title, textRect.x, textRect.y + metrics.getAscent());

            }
            @Override
            protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
                return super.calculateTabWidth(tabPlacement, tabIndex, metrics) + 100;  // Tăng chiều rộng thêm 30px
            }

            @Override
            protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
                return super.calculateTabHeight(tabPlacement, tabIndex, fontHeight) + 10;  // Tăng chiều cao thêm 10px (nếu muốn)
            }

            @Override
            protected int getTabRunOverlay(int tabPlacement) {
                return 10;  // Tăng khoảng cách giữa các dòng tab (trường hợp nhiều dòng tab)
            }
        });
		pnl_center_Ve.add(tabbedPane_Ve);

		pnl_bieu_Do_Ve = new JPanel();
		pnl_bieu_Do_Ve.setLayout(new BorderLayout(0, 0));
		pnl_bieu_Do_Ve.setBackground(Color.WHITE);
		tabbedPane_Ve.addTab("Tổng quan", null, pnl_bieu_Do_Ve, null);
		pnl_BieuDo_UD = new JPanel();
		pnl_bieu_Do_Ve.add(pnl_BieuDo_UD);

		pnl_Bang_Ve = new JPanel();
		tabbedPane_Ve.addTab("Chi tiết", null, pnl_Bang_Ve, null);
		pnl_Bang_Ve.setLayout(new BorderLayout(0, 0));

		String[] Chuoi = {
				" Ng\u00E0y", "Lo\u1EA1i v\u00E9", "S\u1ED1 v\u00E9 b\u00E1n \u0111\u01B0\u1EE3c", "T\u1ED5ng ti\u1EC1n"
		};
		model_ChiTiet_Ve = new DefaultTableModel(Chuoi,0);
		table_ChiTiet_Ve = new CTable(model_ChiTiet_Ve);
		pnl_Bang_Ve.add(new JScrollPane(table_ChiTiet_Ve));
		CapNhatBieuDoVe_Chung();

		return pnl_center_Ve;
	}
	public JPanel CenterKhachHang() throws RemoteException {
		JPanel pnl_center = new JPanel();
		pnl_center.setLayout(new BorderLayout(0, 0));

		tabbedPane_KH = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_KH.setUI(new BasicTabbedPaneUI() {
			@Override
			protected void installDefaults() {
				super.installDefaults();
				contentBorderInsets = new Insets(0, 0, 0, 0);  // Loại bỏ viền của tab nội dung
				tabAreaInsets = new Insets(0, 0, 0, 0);  // Loại bỏ viền xung quanh tab
			}

			@Override
			protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
				if (isSelected) {
					g.setColor(new Color(24, 133, 231));  // Màu nền khi tab được chọn
				} else {
					g.setColor(new Color(188, 187, 190));  // Màu nền cho các tab không được chọn
				}
				g.fillRect(x, y, w, h);  // Vẽ nền tab
			}

			@Override
			protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
				// Không vẽ viền tab
			}

			@Override
			protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
				// Không vẽ viền xung quanh nội dung tab
			}

			@Override
			protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
				// Tăng cỡ chữ cho tiêu đề tab
				if (isSelected) {
					g.setFont(new Font("Tahoma", Font.BOLD, 15));
					g.setColor(Color.WHITE);  // Màu chữ khi tab được chọn
				} else {
					g.setFont(new Font("Tahoma", Font.PLAIN, 15));
					g.setColor(Color.WHITE);  // Màu chữ khi tab không được chọn
				}
				g.drawString(title, textRect.x, textRect.y + metrics.getAscent());

			}
			@Override
			protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
				return super.calculateTabWidth(tabPlacement, tabIndex, metrics) + 100;  // Tăng chiều rộng thêm 30px
			}

			@Override
			protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
				return super.calculateTabHeight(tabPlacement, tabIndex, fontHeight) + 10;  // Tăng chiều cao thêm 10px (nếu muốn)
			}

			@Override
			protected int getTabRunOverlay(int tabPlacement) {
				return 10;  // Tăng khoảng cách giữa các dòng tab (trường hợp nhiều dòng tab)
			}
		});
		pnl_center.add(tabbedPane_KH);

		pnl_bieu_Do_KH = new JPanel(); // KhachHANG
		tabbedPane_KH.addTab("Tổng quan", null, pnl_bieu_Do_KH, null);

		pnl_Bang_KH = new JPanel();
		tabbedPane_KH.addTab("Chi tiết", null, pnl_Bang_KH, null);
		pnl_Bang_KH.setLayout(new BorderLayout(0, 0));

		String tencot[] = {"Top","Tên khách hàng", "Số điện thoại", "Email", "Số lượt đi", "Tổng tiền"};
		model_KH = new DefaultTableModel(tencot,0);
		table_KH = new CTable(model_KH);
		pnl_Bang_KH.add(new JScrollPane(table_KH));
		CapNhatBieuDoKH_Chung();
		return pnl_center;
	}
	public JPanel CenterNhanVien() throws RemoteException {
		JPanel pnl_center = new JPanel();
		pnl_center.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBackground(Color.WHITE);
		tabbedPane_1.setUI(new BasicTabbedPaneUI() {
			@Override
			protected void installDefaults() {
				super.installDefaults();
				contentBorderInsets = new Insets(0, 0, 0, 0);  // Loại bỏ viền của tab nội dung
				tabAreaInsets = new Insets(0, 0, 0, 0);  // Loại bỏ viền xung quanh tab
			}

			@Override
			protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
				if (isSelected) {
					g.setColor(new Color(24, 133, 231));  // Màu nền khi tab được chọn
				} else {
					g.setColor(new Color(188, 187, 190));  // Màu nền cho các tab không được chọn
				}
				g.fillRect(x, y, w, h);  // Vẽ nền tab
			}

			@Override
			protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
				// Không vẽ viền tab
			}

			@Override
			protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
				// Không vẽ viền xung quanh nội dung tab
			}

			@Override
			protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
				// Tăng cỡ chữ cho tiêu đề tab
				if (isSelected) {
					g.setFont(new Font("Tahoma", Font.BOLD, 15));
					g.setColor(Color.WHITE);  // Màu chữ khi tab được chọn
				} else {
					g.setFont(new Font("Tahoma", Font.PLAIN, 15));
					g.setColor(Color.WHITE);  // Màu chữ khi tab không được chọn
				}
				g.drawString(title, textRect.x, textRect.y + metrics.getAscent());

			}
			@Override
			protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
				return super.calculateTabWidth(tabPlacement, tabIndex, metrics) + 100;  // Tăng chiều rộng thêm 30px
			}

			@Override
			protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
				return super.calculateTabHeight(tabPlacement, tabIndex, fontHeight) + 10;  // Tăng chiều cao thêm 10px (nếu muốn)
			}

			@Override
			protected int getTabRunOverlay(int tabPlacement) {
				return 10;  // Tăng khoảng cách giữa các dòng tab (trường hợp nhiều dòng tab)
			}
		});
		pnl_center.add(tabbedPane_1);

		pnl_bieu_Do_NV = new JPanel(); // NHANVIEN
		pnl_bieu_Do_NV.setBackground(Color.WHITE);
		tabbedPane_1.addTab("Tổng quan", null, pnl_bieu_Do_NV, null);

		pnl_Bang_NV = new JPanel();
		tabbedPane_1.addTab("Chi tiết", null, pnl_Bang_NV, null);
		pnl_Bang_NV.setLayout(new BorderLayout(0, 0));

		String[] chuoi = {"Ngày", "Mã mã nhân viên", "Họ tên nhân viên", "Số lượng vé đã bán","Tổng Tiền"};
		model_table_NV = new DefaultTableModel(chuoi,0);
		table_NV = new CTable(model_table_NV);
		pnl_Bang_NV.add(new JScrollPane(table_NV));
		CapNhatBieuDoNV_Chung();
		return pnl_center;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj.equals(comboBox_TQ)){
            try {
                CapNhatTongDoanhThu_TQ();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            try {
                CapNhatTBBanChayVaKhongChay();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            try {
                CapNhatBanChamNhat();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            try {
                updateTableTopNhanVien();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            updateTableLoaiGheUaThich();

			if (comboBox_TQ.getSelectedIndex() == 0) {
				panel_17.removeAll(); // Xóa biểu đồ cũ
                Map<Integer, Double> revenueByHour = null;
                try {
                    revenueByHour = daoThongKe.getRevenueByHour();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
                panel_17.add(createChartNgayChung(revenueByHour), BorderLayout.CENTER);
				panel_17.revalidate();
				panel_17.repaint();

			} else if (comboBox_TQ.getSelectedIndex() == 1) {
				panel_17.removeAll(); // Xóa biểu đồ cũ
                Map<LocalDate, Double> revenueData = null;
                try {
                    revenueData = daoThongKe.getCurrentWeekRevenue();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
                panel_17.add(createChartPanelChung_Tuan(revenueData), BorderLayout.CENTER);
				panel_17.revalidate();
				panel_17.repaint();
			} else if (comboBox_TQ.getSelectedIndex() == 2) {
				panel_17.removeAll(); // Xóa biểu đồ cũ
                Map<Integer, Double> revenueData = null;
                try {
                    revenueData = daoThongKe.getWeeklyRevenue();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
                panel_17.add(createWeeklyRevenueChart(revenueData), BorderLayout.CENTER);
				panel_17.revalidate();
				panel_17.repaint();
			} else if (comboBox_TQ.getSelectedIndex() == 3) {
                Map<String, Double> quarterlyRevenue = null;
                try {
                    quarterlyRevenue = daoThongKe.getYearlyQuarterRevenue(LocalDate.now().getYear());
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
                panel_17.removeAll(); // Xóa biểu đồ cũ
				panel_17.add(createChartPanelChung_Quy(quarterlyRevenue), BorderLayout.CENTER);
				panel_17.revalidate();
				panel_17.repaint();
			} else if (comboBox_TQ.getSelectedIndex() == 4) {
                Map<String, Double> monthlyRevenue = null;
                try {
                    monthlyRevenue = daoThongKe.getMonthlyRevenue(LocalDate.now().getYear());
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
                panel_17.removeAll(); // Xóa biểu đồ cũ
				panel_17.add(createChartPanelMonthly(monthlyRevenue), BorderLayout.CENTER);
				panel_17.revalidate();
				panel_17.repaint();
			}
		}
		if (obj.equals(btn_Tim_Loc_Ve)) {
            try {
                CapNhatDoanhThu_Ve_Ve();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            try {
                CapNhatDoanhSoCacLoaiVe();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            SwingUtilities.invokeLater(() -> {
				pnl_ve.remove(center_Ve);
                try {
                    center_Ve = Center_Ve();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
                pnl_ve.add(center_Ve, BorderLayout.CENTER); // Thêm thành phần mới
				pnl_ve.revalidate(); // Cập nhật bố cục
				pnl_ve.repaint(); // Vẽ lại giao diện
			});
		}
		if(obj.equals(btn_Tim_Loc_NV)){
            try {
                CapNhatDoanhSo_ofNV();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            SwingUtilities.invokeLater(() -> {
				pnl_nhanVien.remove(center_NV);
                try {
                    center_NV = CenterNhanVien();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
                pnl_nhanVien.add(center_NV, BorderLayout.CENTER); // Thêm thành phần mới
				pnl_nhanVien.revalidate(); // Cập nhật bố cục
				pnl_nhanVien.repaint(); // Vẽ lại giao diện
			});
		}
		if(obj.equals(btn_Tim_Loc_KH)){
            try {
                CapNhatTiLeKhachHanQuayLai();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            SwingUtilities.invokeLater(() -> {
				pnl_khachHang.remove(center_KH);
                try {
                    center_KH = CenterKhachHang();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
                pnl_khachHang.add(center_KH, BorderLayout.CENTER); // Thêm thành phần mới
				pnl_khachHang.revalidate(); // Cập nhật bố cục
				pnl_khachHang.repaint(); // Vẽ lại giao diện
			});
		}
		if(obj.equals(btn_Tim_Loc_SL)){
            try {
                CapNhatTiLeKhachHanQuayLai();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            SwingUtilities.invokeLater(() -> {
				pnl_soLuong.remove(center_SL);
                try {
                    center_SL = CenterSL();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
                pnl_soLuong.add(center_SL, BorderLayout.CENTER); // Thêm thành phần mới
				pnl_soLuong.revalidate(); // Cập nhật bố cục
				pnl_soLuong.repaint(); // Vẽ lại giao diện
			});
		}
		if(obj.equals(cbo_LoaiThongKe_KH)){
			if(cbo_LoaiThongKe_KH.getSelectedIndex() == 1){
				cboLoaiThoiGian_KH.setEnabled(false);
			}else{
				cboLoaiThoiGian_KH.setEnabled(true);
			}
		}
	}
	public void CapNhatBieuDoVe_Chung() throws RemoteException {
		pnl_bieu_Do_Ve.removeAll();
		if(cboLoaiThoiGian_Ve.getSelectedIndex() == 0){
			String tenBieuDo = "BIỂU ĐỒ DOANH THU VÉ THEO NGÀY";
			String donVi = "Ngày";
			if(cboLoaiVe.getSelectedIndex() == 0){
				DefaultCategoryDataset dataset1 = createDataset_BIEUDOVE_Ngay((Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue(), "");
				pnl_bieu_Do_Ve.add(capNhatBieuDo_Ve(tenBieuDo,donVi,dataset1));
				loadDataToTable_Ve("",(Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue());
			}else if(cboLoaiVe.getSelectedIndex() == 1){
				DefaultCategoryDataset dataset1 = createDataset_BIEUDOVE_Ngay((Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue(), "LV1");
				pnl_bieu_Do_Ve.add(capNhatBieuDo_Ve(tenBieuDo,donVi,dataset1));
				loadDataToTable_Ve("LV1",(Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue());
			}else{
				DefaultCategoryDataset dataset1 = createDataset_BIEUDOVE_Ngay((Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue(), "LV2");
				pnl_bieu_Do_Ve.add(capNhatBieuDo_Ve(tenBieuDo,donVi,dataset1));
				loadDataToTable_Ve("LV2",(Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue());
			}
		}
		else if(cboLoaiThoiGian_Ve.getSelectedIndex() == 1){
			String tenBieuDo = "BIỂU ĐỒ DOANH THU VÉ THEO TUẦN";
			String donVi = "Tuần";
			if(cboLoaiVe.getSelectedIndex() == 0){
				DefaultCategoryDataset dataset2 = createDataset_BIEUDOVE_Tuan((Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue(), "");
				pnl_bieu_Do_Ve.add(capNhatBieuDo_Ve(tenBieuDo,donVi,dataset2));
				loadDataToTable_Ve("",(Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue());
			}else if(cboLoaiVe.getSelectedIndex() == 1){
				DefaultCategoryDataset dataset2 = createDataset_BIEUDOVE_Tuan((Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue(),"LV1");
				pnl_bieu_Do_Ve.add(capNhatBieuDo_Ve(tenBieuDo,donVi,dataset2));
				loadDataToTable_Ve("LV1",(Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue());
			}else{
				DefaultCategoryDataset dataset2 = createDataset_BIEUDOVE_Tuan((Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue(), "LV2");
				pnl_bieu_Do_Ve.add(capNhatBieuDo_Ve(tenBieuDo,donVi,dataset2));
				loadDataToTable_Ve("LV2",(Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue());
			}
		}
		else if(cboLoaiThoiGian_Ve.getSelectedIndex() == 2){
			String tenBieuDo = "BIỂU ĐỒ DOANH THU VÉ THEO THÁNG";
			String donVi = "Tháng";
			if(cboLoaiVe.getSelectedIndex() == 0){
				DefaultCategoryDataset dataset3 = createDataset_BIEUDOVE_Thang((Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue(),"");
				pnl_bieu_Do_Ve.add(capNhatBieuDo_Ve(tenBieuDo,donVi,dataset3));
				loadDataToTable_Ve("",(Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue());
			}else if(cboLoaiVe.getSelectedIndex() == 1){
				DefaultCategoryDataset dataset3 = createDataset_BIEUDOVE_Thang((Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue(),"LV1");
				pnl_bieu_Do_Ve.add(capNhatBieuDo_Ve(tenBieuDo,donVi,dataset3));
				loadDataToTable_Ve("LV1",(Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue());
			}else{
				DefaultCategoryDataset dataset3 = createDataset_BIEUDOVE_Thang((Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue(),"LV2");
				pnl_bieu_Do_Ve.add(capNhatBieuDo_Ve(tenBieuDo,donVi,dataset3));
				loadDataToTable_Ve("LV2",(Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue());
			}
		}
		else if(cboLoaiThoiGian_Ve.getSelectedIndex() == 3){
			String tenBieuDo = "BIỂU ĐỒ DOANH THU VÉ THEO QUÝ";
			String donVi = "Quý";
			if(cboLoaiVe.getSelectedIndex() == 0){
				DefaultCategoryDataset dataset4 = createDataset_BIEUDOVE_Quy((Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue(),"");
				pnl_bieu_Do_Ve.add(capNhatBieuDo_Ve(tenBieuDo,donVi,dataset4));
				loadDataToTable_Ve("",(Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue());
			}else if(cboLoaiVe.getSelectedIndex() == 1){
				DefaultCategoryDataset dataset4 = createDataset_BIEUDOVE_Quy((Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue(),"LV1");
				pnl_bieu_Do_Ve.add(capNhatBieuDo_Ve(tenBieuDo,donVi,dataset4));
				loadDataToTable_Ve("LV1",(Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue());
			}else{
				DefaultCategoryDataset dataset4 = createDataset_BIEUDOVE_Quy((Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue(),"LV2");
				pnl_bieu_Do_Ve.add(capNhatBieuDo_Ve(tenBieuDo,donVi,dataset4));
				loadDataToTable_Ve("LV2",(Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue());
			}

		}
		else if(cboLoaiThoiGian_Ve.getSelectedIndex() == 4){
			String tenBieuDo = "BIỂU ĐỒ DOANH THU VÉ THEO NĂM";
			String donVi = "Năm";
			if(cboLoaiVe.getSelectedIndex() == 0){
				DefaultCategoryDataset dataset5 = createDataset_BIEUDOVE_Nam((Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue(),"");
				pnl_bieu_Do_Ve.add(capNhatBieuDo_Ve(tenBieuDo,donVi,dataset5));
				loadDataToTable_Ve("",(Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue());
			}else if(cboLoaiVe.getSelectedIndex() == 1){
				DefaultCategoryDataset dataset5 = createDataset_BIEUDOVE_Nam((Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue(),"LV1");
				pnl_bieu_Do_Ve.add(capNhatBieuDo_Ve(tenBieuDo,donVi,dataset5));
				loadDataToTable_Ve("LV1",(Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue());
			}else{
				DefaultCategoryDataset dataset5 = createDataset_BIEUDOVE_Nam((Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue(),"LV2");
				pnl_bieu_Do_Ve.add(capNhatBieuDo_Ve(tenBieuDo,donVi,dataset5));
				loadDataToTable_Ve("LV2",(Date) thangBatDauPicker_Ve.getModel().getValue(), (Date) thangKetThucPicker_Ve.getModel().getValue());
			}
		}
		pnl_bieu_Do_Ve.revalidate();
		pnl_bieu_Do_Ve.repaint();
	}
	public ChartPanel capNhatBieuDo_Ve(String tenBieuDo, String donVi ,DefaultCategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createBarChart(
				tenBieuDo,
				donVi,
				"Doanh thu",
				dataset,
				PlotOrientation.VERTICAL, false, false, false
		);
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(1400, 400));
		chartPanel.setChart(chart);
		return chartPanel;
	}
	private DefaultCategoryDataset createDataset_BIEUDOVE_Ngay(Date startDate, Date endDate, String maLoaiVe) throws RemoteException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		// Lấy dữ liệu doanh thu
		Map<LocalDate, Double> revenueData = daoThongKe.getDoanhThuTheoNgay_TuNgay_DenNgay(new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime()), maLoaiVe);

		// Lặp qua các ngày và thêm dữ liệu vào dataset
		LocalDate current = start;
		while (!current.isAfter(end)) {
			double revenue = revenueData.getOrDefault(current, 0.0); // Doanh thu mặc định là 0 nếu không có dữ liệu
			dataset.addValue(revenue, "Doanh thu", current.toString()); // Thêm vào dataset
			current = current.plusDays(1);
		}
		return dataset;
	}
	private DefaultCategoryDataset createDataset_BIEUDOVE_Tuan(Date startDate, Date endDate, String maLoaiVe) throws RemoteException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		// Lấy dữ liệu doanh thu từ DAO
		Map<Integer, Double> revenueData = daoThongKe.getTuan_TuNgay_DenNgay(
				new java.sql.Date(startDate.getTime()),
				new java.sql.Date(endDate.getTime()),
				maLoaiVe
		);

		for (Map.Entry<Integer, Double> entry : revenueData.entrySet()) {
			int weekNumber = entry.getKey();
			double revenue = entry.getValue();
			dataset.addValue(revenue, "Doanh thu", "Tuần " + weekNumber);
		}

		return dataset;
	}
	private DefaultCategoryDataset createDataset_BIEUDOVE_Thang(Date startDate, Date endDate, String maLoaiVe) throws RemoteException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		// Lấy dữ liệu doanh thu từ DAO
		Map<Integer, Double> revenueData = daoThongKe.getThangVe_NgayBatDau_NgayKetThuc(
				new java.sql.Date(startDate.getTime()),
				new java.sql.Date(endDate.getTime()),
				maLoaiVe
		);

		for (Map.Entry<Integer, Double> entry : revenueData.entrySet()) {
			int weekNumber = entry.getKey();
			double revenue = entry.getValue();
			dataset.addValue(revenue, "Doanh thu", "Thứ tháng " + weekNumber);
		}

		return dataset;
	}
	private DefaultCategoryDataset createDataset_BIEUDOVE_Quy(Date startDate, Date endDate, String maLoaiVe) throws RemoteException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		// Lấy dữ liệu doanh thu từ DAO
		Map<Integer, Double> revenueData = daoThongKe.getQuarterlyRevenue(
				new java.sql.Date(startDate.getTime()),
				new java.sql.Date(endDate.getTime()),
				maLoaiVe
		);

		for (Map.Entry<Integer, Double> entry : revenueData.entrySet()) {
			int weekNumber = entry.getKey();
			double revenue = entry.getValue();
			dataset.addValue(revenue, "Doanh thu", "Thứ quý " + weekNumber);
		}

		return dataset;
	}
	private DefaultCategoryDataset createDataset_BIEUDOVE_Nam(Date startDate, Date endDate, String maLoaiVe) throws RemoteException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Map<Integer, Double> revenueData = daoThongKe.getYearlyRevenue(
				new java.sql.Date(startDate.getTime()),
				new java.sql.Date(endDate.getTime()),
				maLoaiVe
		);

		for (Map.Entry<Integer, Double> entry : revenueData.entrySet()) {
			int weekNumber = entry.getKey();
			double revenue = entry.getValue();
			dataset.addValue(revenue, "Doanh thu", "Năm" + weekNumber);
		}

		return dataset;
	}
	public void loadDataToTable_Ve(String maLoaiVe, Date startDate, Date endDate) throws RemoteException {
		// Chuyển đổi java.util.Date sang java.sql.Date
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

		// Gọi DAO với java.sql.Date
		List<Object[]> danhSachChiTietVe = daoThongKe.getChiTietVeTheoNgay(maLoaiVe, sqlStartDate, sqlEndDate);

		// Xóa dữ liệu cũ
		model_ChiTiet_Ve.setRowCount(0);

		// Thêm dữ liệu mới vào bảng
		for (Object[] row : danhSachChiTietVe) {
			System.out.println(row[1].toString());
			model_ChiTiet_Ve.addRow(row);
		}
	}
	public void CapNhatBieuDoNV_Chung() throws RemoteException {
		String maNV = txtMaNhanVien_NV.getText().trim();
		Date[] date1 = CapNhatNgayBatDau_NV();
		if (maNV.isEmpty()) {
			capNhatBangNhanVien(date1[0],date1[1]);
			pnl_bieu_Do_NV.removeAll();
			if(cbohinhThucThongKe_NV.getSelectedIndex() == 0){
				String tenBieuDo = "BIỂU ĐỒ DOANH THU NHÂN VIÊN THEO NGÀY";
				String donVi = "Ngày";
				DefaultCategoryDataset dataset1 = createDataset_BIEUDOVE_Ngay((Date) thangBatDauPicker_NV.getModel().getValue(), (Date) thangKetThucPicker_NV.getModel().getValue(), "LV1");
				pnl_bieu_Do_NV.add(CapNhatBieuDo_NV(tenBieuDo,donVi,dataset1));
		//		loadDataToTable_Ve("",(Date) thangBatDauPicker_NV.getModel().getValue(), (Date) thangKetThucPicker_NV.getModel().getValue());
			}
			else if(cbohinhThucThongKe_NV.getSelectedIndex() == 1){
				String tenBieuDo = "BIỂU ĐỒ DOANH THU NHÂN VIÊN THEO TUẦN";
				String donVi = "Tuần";
				DefaultCategoryDataset dataset2 = createDataset_BIEUDOVE_Tuan((Date) thangBatDauPicker_NV.getModel().getValue(), (Date) thangKetThucPicker_NV.getModel().getValue(),"LV1");
				pnl_bieu_Do_NV.add(CapNhatBieuDo_NV(tenBieuDo,donVi,dataset2));
		//		loadDataToTable_Ve("",(Date) thangBatDauPicker_NV.getModel().getValue(), (Date) thangKetThucPicker_NV.getModel().getValue());
			}
			else if(cbohinhThucThongKe_NV.getSelectedIndex() == 2){
				String tenBieuDo = "BIỂU ĐỒ DOANH THU NHÂN VIÊN THEO THÁNG";
				String donVi = "Tháng";
				DefaultCategoryDataset dataset3 = createDataset_BIEUDOVE_Thang((Date) thangBatDauPicker_NV.getModel().getValue(), (Date) thangKetThucPicker_NV.getModel().getValue(),"");
				pnl_bieu_Do_NV.add(CapNhatBieuDo_NV(tenBieuDo,donVi,dataset3));
		//		loadDataToTable_Ve("",(Date) thangBatDauPicker_NV.getModel().getValue(), (Date) thangKetThucPicker_NV.getModel().getValue());
			}
			else if(cbohinhThucThongKe_NV.getSelectedIndex() == 3){
				String tenBieuDo = "BIỂU ĐỒ DOANH THU NHÂN VIÊN THEO QUÝ";
				String donVi = "Quý";
				DefaultCategoryDataset dataset4 = createDataset_BIEUDOVE_Quy((Date) thangBatDauPicker_NV.getModel().getValue(), (Date) thangKetThucPicker_NV.getModel().getValue(),"LV1");
				pnl_bieu_Do_NV.add(CapNhatBieuDo_NV(tenBieuDo,donVi,dataset4));
		//		loadDataToTable_Ve("",(Date) thangBatDauPicker_NV.getModel().getValue(), (Date) thangKetThucPicker_NV.getModel().getValue());
			}
			else if(cbohinhThucThongKe_NV.getSelectedIndex() == 4){
				String tenBieuDo = "BIỂU ĐỒ DOANH THU NHÂN VIÊN THEO NĂM";
				String donVi = "Năm";
				DefaultCategoryDataset dataset5 = createDataset_BIEUDOVE_Nam((Date) thangBatDauPicker_NV.getModel().getValue(), (Date) thangKetThucPicker_NV.getModel().getValue(),"");
				pnl_bieu_Do_NV.add(CapNhatBieuDo_NV(tenBieuDo,donVi,dataset5));
		//		loadDataToTable_Ve("",(Date) thangBatDauPicker_NV.getModel().getValue(), (Date) thangKetThucPicker_NV.getModel().getValue());
			}
			pnl_bieu_Do_NV.revalidate();
			pnl_bieu_Do_NV.repaint();
		} else {
			DefaultCategoryDataset dataset = null;
			if(cbohinhThucThongKe_NV.getSelectedIndex() == 0){
				dataset = createDataset_BIEUDONV_Ngay(maNV);

			}else if(cbohinhThucThongKe_NV.getSelectedIndex() == 1){
				dataset = createDataset_BIEUDONV_Tuan(maNV);

			}else if(cbohinhThucThongKe_NV.getSelectedIndex() == 2){
				dataset = createDataset_BIEUDONV_Thang(maNV);

			}else if(cbohinhThucThongKe_NV.getSelectedIndex() == 3){
				dataset = createDataset_BIEUDONV_Quy(maNV);

			}else if(cbohinhThucThongKe_NV.getSelectedIndex() == 4){
				dataset = createDataset_BIEUDONV_Nam(maNV);

			}
			JFreeChart chart = ChartFactory.createLineChart(
					"Revenue by Day",    // Tiêu đề biểu đồ
					"Date",              // Trục X
					"Revenue",           // Trục Y
					dataset,             // Dữ liệu
					PlotOrientation.VERTICAL, // Hướng biểu đồ
					true,                // Hiển thị chú thích (legend)
					true,                // Hiển thị tooltips
					false                // Không cần URLs
			);

			// Tùy chỉnh hiển thị biểu đồ (nếu cần)
			CategoryPlot plot = chart.getCategoryPlot();
			plot.setBackgroundPaint(Color.WHITE);
			plot.setDomainGridlinePaint(Color.GRAY);
			plot.setRangeGridlinePaint(Color.GRAY);

			// Tùy chỉnh trục X và trục Y (nếu cần)
			plot.getDomainAxis().setCategoryLabelPositions(
					CategoryLabelPositions.UP_45 // Xoay nhãn trục X 45 độ
			);

			chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(1400, 400));
			pnl_bieu_Do_NV.add(chartPanel);

			pnl_bieu_Do_NV.revalidate();
			pnl_bieu_Do_NV.repaint();

			if(dataset == null){
				JOptionPane.showMessageDialog(this,"Không tìm thấy nhân viên");
				return;
			}
			java.sql.Date sqlStartDate = new java.sql.Date(date1[0].getTime());
			java.sql.Date sqlEndDate = new java.sql.Date(date1[1].getTime());
			List<Object[]> data = daoThongKe.getDoanhThuNhanVienTheoNgay(sqlStartDate, sqlEndDate, maNV);

			pnl_Bang_NV.removeAll();
			// Xóa dữ liệu cũ trong model_table_NV
			model_table_NV.setRowCount(0);
			String[] chuoi = {"Ngày", "Số lượng vé đã bán","Tổng Tiền"};
			model_table_NV = new DefaultTableModel(chuoi,0);
			table_NV = new CTable(model_table_NV);
			pnl_Bang_NV.add(new JScrollPane(table_NV));
			// Thêm từng dòng dữ liệu vào bảng
			for (Object[] row : data) {
				model_table_NV.addRow(row);
			}
			// Cập nhật lại bảng (tự động refresh giao diện)
			model_table_NV.fireTableDataChanged();
			pnl_Bang_NV.revalidate();
			pnl_Bang_NV.repaint();

		}
	}
	private ChartPanel CapNhatBieuDo_NV(String tenBieuDo, String donVi,DefaultCategoryDataset dataset){
		JFreeChart chart = ChartFactory.createLineChart(
				tenBieuDo,    // Tiêu đề biểu đồ
				"Date",              // Trục X
				"Revenue",           // Trục Y
				dataset,             // Dữ liệu
				PlotOrientation.VERTICAL, // Hướng biểu đồ
				true,                // Hiển thị chú thích (legend)
				true,                // Hiển thị tooltips
				false                // Không cần URLs
		);

		// Tùy chỉnh hiển thị biểu đồ (nếu cần)
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setDomainGridlinePaint(Color.GRAY);
		plot.setRangeGridlinePaint(Color.GRAY);

		// Tùy chỉnh trục X và trục Y (nếu cần)
		plot.getDomainAxis().setCategoryLabelPositions(
				CategoryLabelPositions.UP_45 // Xoay nhãn trục X 45 độ
		);

		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(1400, 400));
		return chartPanel;

	}
	private DefaultCategoryDataset createDataset_BIEUDONV_Ngay(String maNhanVien) throws RemoteException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Date[] date1 = CapNhatNgayBatDau_NV();
		java.sql.Date sqlStartDate = new java.sql.Date(date1[0].getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(date1[1].getTime());
		Map<LocalDate, Map<String, Double>> revenueData = daoThongKe.getDoanhThuTheoNgay_VaNhanVien(sqlStartDate, sqlEndDate, maNhanVien);
		for (Map.Entry<LocalDate, Map<String, Double>> entry : revenueData.entrySet()) {
			LocalDate date = entry.getKey();
			Map<String, Double> dayData = entry.getValue();
			dataset.addValue(dayData.get("TongDoanhThu"), "Tong Doanh Thu", date.toString());
			dataset.addValue(dayData.get("DoanhThuNhanVien"), "Doanh Thu Nhan Vien", date.toString());
		}
		return dataset;
	}
	private DefaultCategoryDataset createDataset_BIEUDONV_Tuan(String maNhanVien) throws RemoteException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Date[] date1 = CapNhatNgayBatDau_NV();
		java.sql.Date sqlStartDate = new java.sql.Date(date1[0].getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(date1[1].getTime());

		Map<Integer, Map<String, Double>> revenueData = daoThongKe.getDoanhThuTheoTuan_VaNhanVien(sqlStartDate, sqlEndDate, maNhanVien);

		for (Map.Entry<Integer, Map<String, Double>> entry : revenueData.entrySet()) {
			Integer weekNumber = entry.getKey();
			Map<String, Double> weekData = entry.getValue();

			dataset.addValue(weekData.get("TongDoanhThu"), "Tong Doanh Thu", "Tuan " + weekNumber);
			dataset.addValue(weekData.get("DoanhThuNhanVien"), "Doanh Thu Nhan Vien", "Tuan " + weekNumber);
		}
		return dataset;
	}
	private DefaultCategoryDataset createDataset_BIEUDONV_Thang(String maNhanVien) throws RemoteException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Date[] date1 = CapNhatNgayBatDau_NV();
		java.sql.Date sqlStartDate = new java.sql.Date(date1[0].getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(date1[1].getTime());

		Map<Integer, Map<String, Double>> revenueData = daoThongKe.getDoanhThuTheoThang_VaNhanVien(sqlStartDate, sqlEndDate, maNhanVien);

		for (Map.Entry<Integer, Map<String, Double>> entry : revenueData.entrySet()) {
			Integer monthNumber = entry.getKey();
			Map<String, Double> monthData = entry.getValue();

			dataset.addValue(monthData.get("TongDoanhThu"), "Tong Doanh Thu", "Thang " + monthNumber);
			dataset.addValue(monthData.get("DoanhThuNhanVien"), "Doanh Thu Nhan Vien", "Thang " + monthNumber);
		}
		return dataset;
	}
	private DefaultCategoryDataset createDataset_BIEUDONV_Quy(String maNhanVien) throws RemoteException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Date[] date1 = CapNhatNgayBatDau_NV();
		java.sql.Date sqlStartDate = new java.sql.Date(date1[0].getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(date1[1].getTime());

		Map<Integer, Map<String, Double>> revenueData = daoThongKe.getDoanhThuTheoQuy_VaNhanVien(sqlStartDate, sqlEndDate, maNhanVien);

		for (Map.Entry<Integer, Map<String, Double>> entry : revenueData.entrySet()) {
			Integer quarterNumber = entry.getKey();
			Map<String, Double> quarterData = entry.getValue();

			dataset.addValue(quarterData.get("TongDoanhThu"), "Tong Doanh Thu", "Quy " + quarterNumber);
			dataset.addValue(quarterData.get("DoanhThuNhanVien"), "Doanh Thu Nhan Vien", "Quy " + quarterNumber);
		}
		return dataset;
	}
	private DefaultCategoryDataset createDataset_BIEUDONV_Nam(String maNhanVien) throws RemoteException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Date[] date1 = CapNhatNgayBatDau_NV();
		java.sql.Date sqlStartDate = new java.sql.Date(date1[0].getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(date1[1].getTime());

		// Lấy dữ liệu doanh thu theo năm và nhân viên
		Map<Integer, Map<String, Double>> revenueData = daoThongKe.getDoanhThuTheoNam_VaNhanVien(sqlStartDate, sqlEndDate, maNhanVien);

		// Duyệt qua các năm và thêm dữ liệu vào dataset
		for (Map.Entry<Integer, Map<String, Double>> entry : revenueData.entrySet()) {
			Integer year = entry.getKey();
			Map<String, Double> yearData = entry.getValue();

			// Thêm doanh thu tổng và doanh thu của nhân viên vào dataset
			dataset.addValue(yearData.get("TongDoanhThu"), "Tong Doanh Thu", "Nam " + year);
			dataset.addValue(yearData.get("DoanhThuNhanVien"), "Doanh Thu Nhan Vien", "Nam " + year);
		}

		return dataset;
	}
	public Date[] CapNhatNgayBatDau_Ve(){
		int startYear = thangBatDauPicker_Ve.getModel().getYear() ;
		int startMonth = thangBatDauPicker_Ve.getModel().getMonth();
		int startDay = thangBatDauPicker_Ve.getModel().getDay();

		int endYear = thangKetThucPicker_Ve.getModel().getYear();
		int endMonth = thangKetThucPicker_Ve.getModel().getMonth();
		int endDay = thangKetThucPicker_Ve.getModel().getDay();

		Calendar calendar = Calendar.getInstance();
		Calendar calendar1 = Calendar.getInstance();

		calendar.set(startYear, startMonth, startDay);
		startDate_Ve = calendar.getTime();

		calendar.set(endYear, endMonth, endDay);
		endDate_Ve = calendar.getTime();
		Date date[] = new Date[2];
		date[0] = startDate_Ve;
		date[1] = endDate_Ve;
		return date;
	}
	public void CapNhatDoanhThu_Ve_Ve() throws RemoteException {
		Date date[] = CapNhatNgayBatDau_Ve();
		java.sql.Date sqlStartDate = new java.sql.Date(date[0].getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(date[1].getTime());

		Object[] result = daoThongKe.getDoanhThuVaSoVeTrongKhoangThoiGian(sqlStartDate, sqlEndDate);

		// Kiểm tra nếu result không null và có đủ 2 phần tử
		if (result != null && result.length == 2) {
			// Cập nhật tổng doanh thu và số vé
			tongDoanhThu_Ve.setText(FormatMoney.format(Double.parseDouble(result[0].toString()))); // Giả sử result[1] là tổng doanh thu
			tongve_VE.setText(result[1].toString());      // Giả sử result[0] là số vé
		} else {
			System.out.println("Dữ liệu không hợp lệ hoặc không đủ");
		}
	}
	public void CapNhatDoanhSoCacLoaiVe() throws RemoteException {
		Date date[] = CapNhatNgayBatDau_Ve();
		java.sql.Date sqlStartDate = new java.sql.Date(date[0].getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(date[1].getTime());
		Object[] result;

		if (cboLoaiVe.getSelectedIndex() == 0) {
			lbl_doanhThuLoaiVe.setText("");
			lbl_tenDoanhThuLoaiVe.setText("");
			lbl_tongSLLoaiVe.setText("");
			lbl_tenTongSLLoaiVe.setText("");
		} else if (cboLoaiVe.getSelectedIndex() == 1) {
			result = daoThongKe.getThongKeLoaiVe(sqlStartDate, sqlEndDate, "LV1");
			if (result == null) { // Kiểm tra null
				lbl_doanhThuLoaiVe.setText("0 VND");
				lbl_tenDoanhThuLoaiVe.setText("Doanh thu vé 1 chiều");
				lbl_tongSLLoaiVe.setText("0");
				lbl_tenTongSLLoaiVe.setText("Tổng số vé 1 chiều");
			} else {
				lbl_doanhThuLoaiVe.setText(FormatMoney.format(Double.parseDouble(result[1].toString())));
				lbl_tenDoanhThuLoaiVe.setText("Doanh thu vé 1 chiều");
				lbl_tongSLLoaiVe.setText(result[2].toString());
				lbl_tenTongSLLoaiVe.setText("Tổng số vé 1 chiều");
			}
		} else if (cboLoaiVe.getSelectedIndex() == 2) {
			result = daoThongKe.getThongKeLoaiVe(sqlStartDate, sqlEndDate, "LV2");
			if (result == null) { // Kiểm tra null
				lbl_doanhThuLoaiVe.setText("0 VND");
				lbl_tenDoanhThuLoaiVe.setText("Doanh thu vé khứ hồi");
				lbl_tongSLLoaiVe.setText("0");
				lbl_tenTongSLLoaiVe.setText("Tổng số vé khứ hồi");
				System.out.println("Không có dữ liệu vé khứ hồi");
			} else {
				lbl_doanhThuLoaiVe.setText(FormatMoney.format(Double.parseDouble(result[1].toString())));
				lbl_tenDoanhThuLoaiVe.setText("Doanh thu vé khứ hồi");
				lbl_tongSLLoaiVe.setText(result[2].toString());
				lbl_tenTongSLLoaiVe.setText("Tổng số vé khứ hồi");
			}
		}
	}
	public Date[] CapNhatNgayBatDau_NV(){
		int startYear = thangBatDauPicker_NV.getModel().getYear() ;
		int startMonth = thangBatDauPicker_NV.getModel().getMonth();
		int startDay = thangBatDauPicker_NV.getModel().getDay();

		int endYear = thangKetThucPicker_NV.getModel().getYear();
		int endMonth = thangKetThucPicker_NV.getModel().getMonth();
		int endDay = thangKetThucPicker_NV.getModel().getDay();

		Calendar calendar = Calendar.getInstance();
		Calendar calendar1 = Calendar.getInstance();

		calendar.set(startYear, startMonth, startDay);
		startDate_Ve = calendar.getTime();

		calendar.set(endYear, endMonth, endDay);
		endDate_Ve = calendar.getTime();
		Date date[] = new Date[2];
		date[0] = startDate_Ve;
		date[1] = endDate_Ve;
		return date;
	}
	public void CapNhatDoanhThu_BanHang_NV() throws RemoteException {
		Date date[] = CapNhatNgayBatDau_NV();
		java.sql.Date sqlStartDate = new java.sql.Date(date[0].getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(date[1].getTime());

		Object[] result = daoThongKe.getDoanhThuVaSoVeTrongKhoangThoiGian(sqlStartDate, sqlEndDate);
		// Kiểm tra nếu result không null và có đủ 2 phần tử
		if (result != null && result.length == 2) {
			// Cập nhật tổng doanh thu và số vé
			tongDoanhThu_NV.setText(FormatMoney.format(Double.parseDouble(result[0].toString())));
			// Giả sử result[1] là tổng doanh thu
			tongVe_NV.setText(result[1].toString());      // Giả sử result[0] là số vé
		} else {
			System.out.println("Dữ liệu không hợp lệ hoặc không đủ");
		}
	}
	public void CapNhatDoanhSo_ofNV() throws RemoteException {
		Date date[] = CapNhatNgayBatDau_NV();
		java.sql.Date sqlStartDate = new java.sql.Date(date[0].getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(date[1].getTime());
		Object[] result;
		String maNhanVien = txtMaNhanVien_NV.getText();
		System.out.println(maNhanVien);
		if (maNhanVien.isEmpty()) {
			tongDoanhThuofCaNhan_NV.setText("");
			lbl_tongDoanhThuofCaNhan_NV.setText("");
			tongVeofCaNhan_NV.setText("");
			lbl_tongVeofCaNhan_NV.setText("");
		}else{
			result = daoThongKe.getThongKeTheoNhanVien(sqlStartDate, sqlEndDate,txtMaNhanVien_NV.getText().trim());
			if (result == null) { // Kiểm tra null
				tongDoanhThuofCaNhan_NV.setText("");
				lbl_tongDoanhThuofCaNhan_NV.setText("");
				tongVeofCaNhan_NV.setText("");
				lbl_tongVeofCaNhan_NV.setText("");
				JOptionPane.showMessageDialog(this,"Không Tìm Thấy Nhân Viên");
			} else {
				tongDoanhThuofCaNhan_NV.setText(FormatMoney.format(Double.parseDouble(result[1].toString())));
				lbl_tongDoanhThuofCaNhan_NV.setText("Doanh thu của " +result[0].toString());
				tongVeofCaNhan_NV.setText(result[2].toString());
				lbl_tongVeofCaNhan_NV.setText("Tổng số vé");
			}
		}
	}
	private void capNhatBangNhanVien(Date startDate, Date endDate) throws RemoteException {
		// Lấy dữ liệu từ DAO
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

		List<Object[]> data = daoThongKe.getDoanhThuChiTietTheoKhoangNgay(sqlStartDate, sqlEndDate);
		pnl_Bang_NV.removeAll();
		// Xóa dữ liệu cũ trong model_table_NV
		model_table_NV.setRowCount(0);
		String[] chuoi = {"Ngày", "Mã mã nhân viên", "Họ tên nhân viên", "Số lượng vé đã bán","Tổng Tiền"};
		model_table_NV = new DefaultTableModel(chuoi,0);
		table_NV = new CTable(model_table_NV);
		pnl_Bang_NV.add(new JScrollPane(table_NV));
		// Thêm từng dòng dữ liệu vào bảng
		for (Object[] row : data) {
			model_table_NV.addRow(row);
		}
		// Cập nhật lại bảng (tự động refresh giao diện)
		model_table_NV.fireTableDataChanged();
		pnl_Bang_NV.revalidate();
		pnl_Bang_NV.repaint();
	}
	public static JPanel createChartPanelMonthly(Map<String, Double> data) {
		DefaultCategoryDataset dataset = createDatasetMonthly(data);

		JFreeChart chart = ChartFactory.createLineChart(
				"Doanh thu theo từng tháng trong năm",
				"Tháng",
				"Doanh thu (VNĐ)",
				dataset,
				PlotOrientation.VERTICAL,
				true,
				true,
				false
		);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(800, 400));
		return chartPanel;
	}
	private static DefaultCategoryDataset createDatasetMonthly(Map<String, Double> data) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (Map.Entry<String, Double> entry : data.entrySet()) {
			dataset.addValue(entry.getValue(), "Doanh thu", entry.getKey());
		}

		return dataset;
	}
	public static JPanel createChartPanelChung_Quy(Map<String, Double> data) {
		DefaultCategoryDataset dataset = createDatasetChung_Quy(data);

		JFreeChart chart = ChartFactory.createLineChart(
				"Doanh thu theo các quý trong năm", // Tiêu đề biểu đồ
				"Quý", // Trục X
				"Doanh thu (VNĐ)", // Trục Y
				dataset,
				PlotOrientation.VERTICAL,
				true, // Hiện legend
				true,
				false
		);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(800, 260));
		return chartPanel;
	}
	private static DefaultCategoryDataset createDatasetChung_Quy(Map<String, Double> data) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (Map.Entry<String, Double> entry : data.entrySet()) {
			dataset.addValue(entry.getValue(), "Doanh thu", entry.getKey());
		}

		return dataset;
	}
	public static JPanel createWeeklyRevenueChart(Map<Integer, Double> weeklyRevenue) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		// Thêm dữ liệu vào dataset
		for (Map.Entry<Integer, Double> entry : weeklyRevenue.entrySet()) {
			String weekLabel = "Tuần " + entry.getKey();
			dataset.addValue(entry.getValue(), "Doanh Thu", weekLabel);
		}

		JFreeChart chart = ChartFactory.createLineChart(
				"Biểu đồ Doanh thu theo Tuần",
				"Tuần",
				"Doanh thu",
				dataset,
				PlotOrientation.VERTICAL,
				true, true, false
		);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(800, 260));
		return chartPanel;
	}
	public static JPanel createChartPanelChung_Tuan(Map<LocalDate, Double> data) {
		DefaultCategoryDataset dataset = createDatasetChung_Tuan(data);

		JFreeChart chart = ChartFactory.createLineChart(
				"Doanh thu theo từng ngày trong tuần hiện tại", // Biểu đồ tiêu đề
				"Thứ", // Trục X (hiển thị các ngày trong tuần: Thứ 2, Thứ 3, ...)
				"Doanh thu (VNĐ)", // Trục Y
				dataset,
				PlotOrientation.VERTICAL,
				true, // Hiện legend
				true,
				false
		);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(800, 260));
		return chartPanel;
	}
	private static DefaultCategoryDataset createDatasetChung_Tuan(Map<LocalDate, Double> data) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		// Xác định ngày bắt đầu và ngày kết thúc trong tuần hiện tại
		LocalDate today = LocalDate.now();
		LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
		LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);

		// Duyệt qua các ngày từ Thứ 2 đến Chủ Nhật
		for (LocalDate date = startOfWeek; !date.isAfter(endOfWeek); date = date.plusDays(1)) {
			// Lấy tên ngày trong tuần bằng tiếng Việt
			String dayOfWeek = date.getDayOfWeek()
					.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("vi"));
			Double revenue = data.getOrDefault(date, 0.0); // Nếu không có dữ liệu thì mặc định là 0
			dataset.addValue(revenue, "Doanh thu", dayOfWeek);
		}

		return dataset;
	}
	public Component createChartNgayChung(Map<Integer, Double> data) {
		// Tạo dataset
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (Map.Entry<Integer, Double> entry : data.entrySet()) {
			dataset.addValue(entry.getValue(), "Doanh Thu", entry.getKey() + ":00");
		}

		// Tạo biểu đồ đường
		JFreeChart lineChart = ChartFactory.createLineChart(
				"Doanh Thu Theo Giờ",
				"Giờ",
				"Doanh Thu (VND)",
				dataset
		);
		ChartPanel chartPanel = new ChartPanel(lineChart);
		chartPanel.setPreferredSize(new Dimension(800, 260));
		return chartPanel;
	}
	public void CapNhatTongDoanhThu_TQ() throws RemoteException {
		String totalRevenue = "";
		String previousMonthRevenue = "";
		if (comboBox_TQ.getSelectedIndex() == 0) {
			totalRevenue = daoVe.layDoanhThuTheoNgayHienTai();
			previousMonthRevenue = daoVe.layDoanhThuNgayHomQua();
		} else if (comboBox_TQ.getSelectedIndex() == 1) {
			totalRevenue = daoVe.layDoanhThuTuanHienTai();
			previousMonthRevenue = daoVe.layDoanhThuTuanVuaRoi();
		} else if (comboBox_TQ.getSelectedIndex() == 2) {
			totalRevenue = daoVe.layDoanhThuThangHienTai();
			previousMonthRevenue = daoVe.layDoanhThuThangVuaRoi();
		} else if (comboBox_TQ.getSelectedIndex() == 3) {
			totalRevenue = daoVe.layDoanhThuQuyHienTai();
			previousMonthRevenue = daoVe.layDoanhThuQuyVuaRoi();
		} else if (comboBox_TQ.getSelectedIndex() == 4) {
			totalRevenue = daoVe.layDoanhThuNamHienTai();
			previousMonthRevenue = daoVe.layDoanhThuNamVuaRoi();
		}

		if(totalRevenue == null) previousMonthRevenue = "0";
		if(previousMonthRevenue == null) previousMonthRevenue = "0";

		if (totalRevenue == null || totalRevenue.trim().isEmpty()) {
			totalRevenue = "0";
		}
		if (previousMonthRevenue == null || previousMonthRevenue.trim().isEmpty()) {
			previousMonthRevenue = "0";
		}
		try {
			double x = Double.parseDouble(totalRevenue) - Double.parseDouble(totalRevenue)*0.1;
			doanhThuSauThue.setText(FormatMoney.format(x));
			lbl_tongDT_TQ.setText(FormatMoney.format(Double.parseDouble(totalRevenue)));

			double currentRevenue = Double.parseDouble(totalRevenue);
			double previousRevenue = Double.parseDouble(previousMonthRevenue);

			double percentageChange;
			if (previousRevenue == 0) {
				lbl_phapTram_TQ.setText("Không có dữ liệu");
				ImageIcon icon;
				icon = new ImageIcon("src/images/icons/trungbinh.png");
				Image img = icon.getImage();
				Image scaledImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
				lbl_hinhTangGiam.setIcon(new ImageIcon(scaledImg));
			} else {
				percentageChange = ((currentRevenue - previousRevenue) / previousRevenue) * 100;
				String formattedPercentageChange = String.format(Locale.US, "%.2f%%", percentageChange);
				lbl_phapTram_TQ.setText(formattedPercentageChange);
//				System.out.println(percentageChange);
				// Cập nhật hình ảnh biểu thị
				ImageIcon icon;
				if (percentageChange > 0) {
					icon = new ImageIcon("src/images/icons/tang.png");
				} else if (percentageChange < 0) {
					icon = new ImageIcon("src/images/icons/giam.png");
				} else {
					icon = new ImageIcon("src/images/icons/trungbinh.png");
				}
				Image img = icon.getImage();
				Image scaledImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
				lbl_hinhTangGiam.setIcon(new ImageIcon(scaledImg));
			}
		} catch (NumberFormatException e) {
			lbl_tongDT_TQ.setText("Dữ liệu không hợp lệ");
			lbl_phapTram_TQ.setText("Không có dữ liệu");
		}
	}
	public void CapNhatTBBanChayVaKhongChay() throws RemoteException {
		double bestSellingHour = -1;
		if (comboBox_TQ.getSelectedIndex() == 0) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.WEEK_OF_YEAR, -1);
			Date lastWeekDate = calendar.getTime();
			bestSellingHour = daoVe.getBestSellingHourOfDay();
			if (bestSellingHour == -1) {
				lbl_tg_Tb_CaoNhat.setText("Không xác định");
				return;
			}
			lbl_tg_Tb_CaoNhat.setText(formatHour(bestSellingHour));
		} else if (comboBox_TQ.getSelectedIndex() == 1) {
			bestSellingHour = daoVe.getAverageBestSellingHourOfWeek();
			if (bestSellingHour == -1) {
				lbl_tg_Tb_CaoNhat.setText("Không xác định");
				return;
			}
			lbl_tg_Tb_CaoNhat.setText(formatHour(bestSellingHour));
		} else if (comboBox_TQ.getSelectedIndex() == 2) {
			bestSellingHour = daoVe.getAverageBestSellingHourOfMonth();
			if (bestSellingHour == -1) {
				lbl_tg_Tb_CaoNhat.setText("Không xác định");
				return;
			}
			lbl_tg_Tb_CaoNhat.setText(formatHour(bestSellingHour));
		} else if (comboBox_TQ.getSelectedIndex() == 3) {
			bestSellingHour = daoVe.getAverageBestSellingHourOfQuarter();
			if (bestSellingHour == -1) {
				lbl_tg_Tb_CaoNhat.setText("Không xác định");
				return;
			}
			lbl_tg_Tb_CaoNhat.setText(formatHour(bestSellingHour));
		} else if (comboBox_TQ.getSelectedIndex() == 4) {
			bestSellingHour = daoVe.getAverageBestSellingHourOfYear();
			if (bestSellingHour == -1) {
				lbl_tg_Tb_CaoNhat.setText("Không xác định");
				return;
			}
			lbl_tg_Tb_CaoNhat.setText(formatHour(bestSellingHour));
		}
	}
	public void CapNhatBanChamNhat() throws RemoteException {
		String chuoi = "";

		if (comboBox_TQ.getSelectedIndex() == 0) {
			chuoi = daoVe.getSlowestSalesTimeLastWeek();
		} else if (comboBox_TQ.getSelectedIndex() == 1) {
			chuoi = daoVe.getSlowestSalesTimeLastWeek();
		} else if (comboBox_TQ.getSelectedIndex() == 2) {
			chuoi = daoVe.getSlowestSalesTimeLastMonth();
		} else if (comboBox_TQ.getSelectedIndex() == 3) {
			chuoi = daoVe.getSlowestSalesTimeLastQuarter();
		} else if (comboBox_TQ.getSelectedIndex() == 4) {
			chuoi = daoVe.getSlowestSalesTimeLastYear();
		}

		lbl_TGBanChamNhat_TQ.setText(chuoi);
	}
	public void updateTableTopNhanVien() throws RemoteException {
		ArrayList<Object[]> data = new ArrayList<>();
		if (comboBox_TQ.getSelectedIndex() == 0) {
			data = (ArrayList<Object[]>) daoThongKe.getTopDoanhThuNhanVienTrongNgay();
		} else if (comboBox_TQ.getSelectedIndex() == 1) {
			data = (ArrayList<Object[]>) daoThongKe.getTopDoanhThuNhanVienTheoTuan();
		} else if (comboBox_TQ.getSelectedIndex() == 2) {
			data = (ArrayList<Object[]>) daoThongKe.getTopDoanhThuNhanVienTheoThang();
		} else if (comboBox_TQ.getSelectedIndex() == 3) {
			data = (ArrayList<Object[]>) daoThongKe.getTopDoanhThuNhanVienTheoQuy();
		} else if (comboBox_TQ.getSelectedIndex() == 4) {
			data = (ArrayList<Object[]>) daoThongKe.getTopDoanhThuNhanVienTheoNam();
		}
		modelTOPNHANVIEN.setRowCount(0);
		for (Object[] row : data) {
			modelTOPNHANVIEN.addRow(row);
		}
	}
	public void updateTableLoaiGheUaThich() {
		// Clear dữ liệu cũ trước khi cập nhật
		model_LoaiGheUaThich.setRowCount(0);
		List<Object[]> data = new ArrayList<>();

		try {
			// Lấy dữ liệu từ DAO
			switch (comboBox_TQ.getSelectedIndex()) {
				case 0:
					data = daoThongKe.getDailyRevenueChange1();
					break;
				case 1:
					data = daoThongKe.getWeeklyRevenueChange();
					break;
				case 2:
					data = daoThongKe.getMonthlyRevenueChange1();
					break;
				case 3:
					data = daoThongKe.getQuarterlyRevenueChange();
					break;
				case 4:
					data = daoThongKe.getYearlyRevenueChange();
					break;
			}

			// Thêm dữ liệu vào model
			for (Object[] row : data) {
				model_LoaiGheUaThich.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu thống kê", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void CapNhatTiLeKhachHanQuayLai() throws RemoteException {
//		lblChinh_KH;
//		lbl_ChuChinh_KH;
//		lblChinh1_KH;
//		lbl_ChuChinh1_KH;
		Date date[] = CapNhatNgayBatDau_KH();
		java.sql.Date sqlStartDate = new java.sql.Date(date[0].getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(date[1].getTime());

		if(cbo_LoaiThongKe_KH.getSelectedIndex() == 0){
			lblChinh_KH.setText(daoThongKe.getTotalCustomersByDateRange(sqlStartDate,sqlEndDate)+"");
			lbl_ChuChinh_KH.setText("Tổng số lượng khách hàng trong thời gian");
		}

		List<Object> stats = daoThongKe.getLoyaltyStats();
		if (!stats.isEmpty()) {
			int soKHQuayLai = (int) stats.get(0);
			int tongSoKH = (int) stats.get(1);
//			double tiLeQuayLai = (double) stats.get(2);
			double tiLeQuayLai = ((BigDecimal) stats.get(2)).doubleValue();
			tiLeKHQuayLai_KH.setText("Tổng số khách hàng: " + tongSoKH);
			tiLeKHQuayLai_KH1.setText("Số khách hàng quay lại: "+ soKHQuayLai +"  Tỉ lệ "+ tiLeQuayLai+"%");
		} else {
			System.out.println("Không có dữ liệu.");
		}
		tgTrungBinhquayLai_KH.setText(daoThongKe.getOverallAverageTransactionInterval());
		lbl_tgTrungBinhquayLai_KH.setText("Thời gian trung bình quay lại");
	}
	public Date[] CapNhatNgayBatDau_KH(){
		int startYear = thangBatDauPicker_KH.getModel().getYear() ;
		int startMonth = thangBatDauPicker_KH.getModel().getMonth();
		int startDay = thangBatDauPicker_KH.getModel().getDay();

		int endYear = thangKetThucPicker_KH.getModel().getYear();
		int endMonth = thangKetThucPicker_KH.getModel().getMonth();
		int endDay = thangKetThucPicker_KH.getModel().getDay();

		Calendar calendar = Calendar.getInstance();
		Calendar calendar1 = Calendar.getInstance();

		calendar.set(startYear, startMonth, startDay);
		startDate_Ve = calendar.getTime();

		calendar.set(endYear, endMonth, endDay);
		endDate_Ve = calendar.getTime();
		Date date[] = new Date[2];
		date[0] = startDate_Ve;
		date[1] = endDate_Ve;
		return date;
	}
	public void CapNhatBieuDoKH_Chung() throws RemoteException {
		pnl_bieu_Do_KH.removeAll();
		Date[] dateRange = CapNhatNgayBatDau_KH();
		Date startDate = dateRange[0];
		Date endDate = dateRange[1];
		if(cbo_LoaiThongKe_KH.getSelectedIndex() == 0){
			if(cboLoaiThoiGian_KH.getSelectedIndex() == 0){
				String tenBieuDo = "BIỂU ĐỒ SỐ LƯỢNG KHÁCH HÀNG THEO NGÀY";
				String donVi = "Số lượng khách hàng";
				DefaultCategoryDataset dataset1 = SoLuongKhachHangNgay_KH(startDate,endDate);
				pnl_bieu_Do_KH.add(BieuDoSoLuongKhangHang_KH(tenBieuDo,donVi,dataset1));
			}
			else if(cboLoaiThoiGian_KH.getSelectedIndex() == 1){
				String tenBieuDo = "BIỂU ĐỒ SỐ LƯỢNG KHÁCH HÀNG THEO TUẦN";
				String donVi = "Tuần";
				DefaultCategoryDataset dataset1 = SoLuongKhachHangTuan_KH(startDate,endDate);
				pnl_bieu_Do_KH.add(BieuDoSoLuongKhangHang_KH(tenBieuDo,donVi,dataset1));
			}
			else if(cboLoaiThoiGian_KH.getSelectedIndex() == 2)
			{
				String tenBieuDo = "BIỂU ĐỒ SỐ LƯỢNG KHÁCH HÀNG THEO THÁNG";
				String donVi = "Tháng";
				DefaultCategoryDataset dataset1 = SoLuongKhachHangThang_KH(startDate,endDate);
				pnl_bieu_Do_KH.add(BieuDoSoLuongKhangHang_KH(tenBieuDo,donVi,dataset1));
			}
			else if(cboLoaiThoiGian_KH.getSelectedIndex() == 3){
//				System.out.println("SO 4");
				String tenBieuDo = "BIỂU ĐỒ SỐ LƯỢNG KHÁCH HÀNG THEO QUÝ";
				String donVi = "Quý";
				DefaultCategoryDataset dataset1 = SoLuongKhachHangQuy_KH(startDate,endDate);
				pnl_bieu_Do_KH.add(BieuDoSoLuongKhangHang_KH(tenBieuDo,donVi,dataset1));
			}
			else if(cboLoaiThoiGian_KH.getSelectedIndex() == 4){
				String tenBieuDo = "BIỂU ĐỒ SỐ LƯỢNG KHÁCH HÀNG THEO NĂM";
				String donVi = "Năm";
				DefaultCategoryDataset dataset1 = SoLuongKhachHangNam_KH(startDate,endDate);
				pnl_bieu_Do_KH.add(BieuDoSoLuongKhangHang_KH(tenBieuDo,donVi,dataset1));
			}
			String[] tencot1 = {"Top","Ngày", "Tổng khách hàng"};
			pnl_Bang_KH.removeAll();
			model_KH.setRowCount(0);
			model_KH = new DefaultTableModel(tencot1,0);
			table_KH = new CTable(model_KH);
			pnl_Bang_KH.add(new JScrollPane(table_KH));
			List<Object[]> data = daoThongKe.getCustomerCountByDateRange(new java.sql.Date(startDate.getTime()),new java.sql.Date(endDate.getTime()));
			for (Object[] row : data) {
				Object[] x = {row[2],row[0],row[1]};
				model_KH.addRow( x);
			}
			// Cập nhật lại bảng (tự động refresh giao diện)
			model_KH.fireTableDataChanged();
			pnl_Bang_KH.revalidate();
			pnl_Bang_KH.repaint();
		}else if(cbo_LoaiThongKe_KH.getSelectedIndex() == 1){
			DefaultPieDataset dataset1 = createTyLeKhuyenMaiPieDataset(startDate, endDate);
			DefaultPieDataset dataset2 = createDoanhThuKhuyenMaiDataset(startDate, endDate);
			pnl_bieu_Do_KH.add(displayTyLeKhuyenMaiPieChart(dataset1));
			pnl_bieu_Do_KH.add(VeBieuDoCotVaDuong(startDate,endDate));

			String[] tencot = {"Đối tượng", "Số lượng áp dụng","Tổng tiền", "Tỉ lệ"};
			pnl_Bang_KH.removeAll();
			model_KH.setRowCount(0);
			model_KH = new DefaultTableModel(tencot,0);
			table_KH = new CTable(model_KH);
			pnl_Bang_KH.add(new JScrollPane(table_KH));
			CapNhatBang1_KH(startDate, endDate);
			model_KH.fireTableDataChanged();
			pnl_Bang_KH.revalidate();
			pnl_Bang_KH.repaint();
		}else if(cbo_LoaiThongKe_KH.getSelectedIndex() == 2){
			tabbedPane_KH.setSelectedIndex(1);
			String[] tencot = {"Top","Tên khách hàng", "Số điện thoại", "Email", "Số lượt đi", "Tổng tiền"};
			pnl_Bang_KH.removeAll();
			model_KH.setRowCount(0);
			model_KH = new DefaultTableModel(tencot,0);
			table_KH = new CTable(model_KH);
			pnl_Bang_KH.add(new JScrollPane(table_KH));
			CapNhatBang_KH(startDate, endDate);
			model_KH.fireTableDataChanged();
			pnl_Bang_KH.revalidate();
			pnl_Bang_KH.repaint();
		}
		pnl_bieu_Do_KH.revalidate();
		pnl_bieu_Do_KH.repaint();
	}
	private void CapNhatBang1_KH(Date startDate, Date endDate) throws RemoteException {
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
		List<Object[]> data = daoThongKe.getTyLeKhuyenMaiTheoDoiTuong(sqlStartDate, sqlEndDate);
		List<Object[]> data1 = daoThongKe.getDoanhThuKhuyenMaiTheoDoiTuong(sqlStartDate, sqlEndDate);

		// Khởi tạo danh sách dsBang với kích thước phù hợp
		ArrayList<Object> dsBang = new ArrayList<>(data.size());

		for (int i = 0; i < data.size(); i++) {
			Object[] row = new Object[4]; // Tạo một mảng mới cho mỗi hàng
			row[0] = data.get(i)[0]; // Đối tượng
			row[1] = data.get(i)[1]; // Số lượng
			row[2] = data1.get(i)[1]; // Tổng tiền
			row[3] = data.get(i)[2]; // Tỉ lệ
			dsBang.add(row);
		}

		// Cập nhật bảng
		model_KH.setRowCount(0);
		for (Object row : dsBang) {
			model_KH.addRow((Object[]) row);
		}
		table_KH.revalidate();
		table_KH.repaint();
	}
	public ChartPanel BieuDoSoLuongKhangHang_KH(String tenBieuDo, String donVi,DefaultCategoryDataset dataset) {

		// Tạo biểu đồ với dòng (line chart)
		JFreeChart chart = ChartFactory.createLineChart(
				tenBieuDo,  // Tên biểu đồ
				"Ngày",                          // Trục X (Ngày)
				donVi,           // Trục Y (Số lượng khách hàng)
				dataset,                         // Dữ liệu biểu đồ
				PlotOrientation.VERTICAL,        // Hướng biểu đồ
				true,                            // Hiển thị chú thích
				true,                            // Hiển thị thông tin tooltip
				false                            // Không hiển thị URL
		);
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setDomainGridlinePaint(Color.GRAY);
		plot.setRangeGridlinePaint(Color.GRAY);
		plot.getDomainAxis().setCategoryLabelPositions(
				CategoryLabelPositions.UP_45
		);
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(1400, 400));
		return chartPanel;

	}
	public DefaultCategoryDataset SoLuongKhachHangNgay_KH(Date startDate, Date endDate) throws RemoteException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
		List<Object[]> data = daoThongKe.getCustomerCountByDateRange(sqlStartDate, sqlEndDate);
		for (Object obj : data) {
			Object[] row = (Object[]) obj; // Lấy mảng Object chứa ngày và số lượng khách hàng
			Date date = (Date) row[0];
			int customerCount = (int) row[1];
			// Thêm giá trị vào dataset:
			// date (x-axis), customerCount (y-axis), "Khách hàng" (Series name)
			dataset.addValue(customerCount, "Khách hàng", date.toString());
		}

		return dataset;
	}
	public DefaultCategoryDataset SoLuongKhachHangTuan_KH(Date startDate, Date endDate) throws RemoteException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
		List<Object[]> data = daoThongKe.getCustomerCountByWeek(sqlStartDate, sqlEndDate);

		for (Object obj : data) {
			Object[] row = (Object[]) obj;
			int weekNumber = (int) row[0];
			int customerCount = (int) row[1];

			dataset.addValue(customerCount, "Khách hàng", "Tuần " + weekNumber);
		}

		return dataset;
	}
	public DefaultCategoryDataset SoLuongKhachHangThang_KH(Date startDate, Date endDate) throws RemoteException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
		List<Object[]> data = daoThongKe.getCustomerCountByMonth(sqlStartDate, sqlEndDate);

		for (Object obj : data) {
			Object[] row = (Object[]) obj;
			int monthNumber = (int) row[0];
			int yearNumber = (int) row[1];
			int customerCount = (int) row[2];

			dataset.addValue(customerCount, "Khách hàng", "Tháng " + monthNumber + "/" + yearNumber);
		}

		return dataset;
	}
	public DefaultCategoryDataset SoLuongKhachHangQuy_KH(Date startDate, Date endDate) throws RemoteException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
		List<Object[]> data = daoThongKe.getCustomerCountByQuarter(sqlStartDate, sqlEndDate);

		for (Object obj : data) {
			Object[] row = (Object[]) obj;
			int quarterNumber = (int) row[0];
			int yearNumber = (int) row[1];
			int customerCount = (int) row[2];

			dataset.addValue(customerCount, "Khách hàng", "Quý " + quarterNumber + "/" + yearNumber);
		}

		return dataset;
	}
	public DefaultCategoryDataset SoLuongKhachHangNam_KH(Date startDate, Date endDate) throws RemoteException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
		List<Object[]> data = daoThongKe.getCustomerCountByYear(sqlStartDate, sqlEndDate);


		for (Object obj : data) {
			Object[] row = (Object[]) obj;
			int yearNumber = (int) row[0];
			int customerCount = (int) row[1];

			dataset.addValue(customerCount, "Khách hàng", String.valueOf(yearNumber)); // Chuyển năm sang String
		}

		return dataset;
	}
	public ChartPanel displayTyLeKhuyenMaiPieChart(DefaultPieDataset dataset) {
		if (dataset.getItemCount() == 0) { // Kiểm tra số lượng item trong PieDataset
			JOptionPane.showMessageDialog(null, "Không có dữ liệu trong khoảng thời gian này!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}

		JFreeChart chart = ChartFactory.createPieChart(
				"Tỉ lệ sử dụng khuyến mãi theo đối tượng",
				dataset,
				true, // include legend
				true,
				false);

		PiePlot plot = (PiePlot) chart.getPlot();

		// Định dạng nền trắng
		plot.setBackgroundPaint(Color.WHITE);
		chart.setBackgroundPaint(Color.WHITE);

		// Định dạng nhãn phần trăm
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2})", new DecimalFormat("0"), new DecimalFormat("0.00%")));
		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		plot.setLabelBackgroundPaint(null); // Loại bỏ nền của nhãn
		plot.setLabelOutlinePaint(null); // Loại bỏ viền của nhãn
		plot.setLabelShadowPaint(null); // Loại bỏ bóng của nhãn

		// Định dạng khác (tùy chọn)
		plot.setSectionOutlinesVisible(false); // Loại bỏ viền giữa các phần
		plot.setCircular(true); // Đảm bảo biểu đồ tròn thực sự
		plot.setSimpleLabels(false); // Hiển thị nhãn chi tiết hơn

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(700, 410));
		return chartPanel;
	}
	public ChartPanel VeBieuDoCotVaDuong(Date startDate, Date endDate) throws RemoteException {
		DefaultCategoryDataset datasetDoanhThu = new DefaultCategoryDataset();
		DefaultCategoryDataset datasetTyLe = new DefaultCategoryDataset();
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
		List<Object[]> dataDoanhThu = daoThongKe.getDoanhThuKhuyenMaiTheoDoiTuong(sqlStartDate, sqlEndDate);
		List<Object[]> dataTyLe = daoThongKe.getTyLeKhuyenMaiTheoDoiTuong(sqlStartDate, sqlEndDate);
		if(dataTyLe == null || dataDoanhThu == null){
			return null;
		}
		if(dataTyLe.isEmpty() || dataDoanhThu.isEmpty()){
			JOptionPane.showMessageDialog(null, "Không có dữ liệu trong khoảng thời gian này!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		try{
			for (Object[] row : dataDoanhThu) {
				String doiTuong = (String) row[0];
				double doanhThu = (double) row[1]; // Lấy TongTienKhuyenMai từ DAO
				datasetDoanhThu.addValue(doanhThu, "Doanh Thu", doiTuong);
			}

			for (Object[] row : dataTyLe) {
				String doiTuong = (String) row[0];
				double tiLe = (double) row[2];
				datasetTyLe.addValue(tiLe, "Tỉ Lệ (%)", doiTuong);
			}
		}catch(ClassCastException e){
			System.err.println("Lỗi ép kiểu dữ liệu từ CSDL: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Lỗi dữ liệu từ CSDL. Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		// Tạo biểu đồ đường trước
		JFreeChart chart = ChartFactory.createLineChart(
				"Thống kê Khuyến mãi",
				"Đối tượng",
				"Tỉ Lệ (%)", // Trục tung riêng cho đường
				datasetTyLe
		);

		CategoryPlot plot = chart.getCategoryPlot();

		// Tạo trục tung bên phải cho doanh thu
		NumberAxis axisRight = new NumberAxis("Doanh Thu");
		plot.setRangeAxis(1, axisRight);
		plot.mapDatasetToRangeAxis(1, 1);

		// Tạo renderer cho biểu đồ đường
		LineAndShapeRenderer rendererLine = new LineAndShapeRenderer();
		rendererLine.setSeriesShapesVisible(0, true);
		rendererLine.setSeriesShape(0, new Ellipse2D.Double(-4.0, -4.0, 8.0, 8.0));
		rendererLine.setDefaultShapesVisible(true);
		plot.setRenderer(0, rendererLine);

		// Thêm dataset doanh thu vào plot
		plot.setDataset(1, datasetDoanhThu);

		// Tạo renderer cho biểu đồ cột
		BarRenderer rendererBar = new BarRenderer();
		rendererBar.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", new DecimalFormat("#,##0")));
		rendererBar.setDefaultItemLabelsVisible(true);
		rendererBar.setDefaultItemLabelFont(new Font("Arial", Font.PLAIN, 10));
		plot.setRenderer(1, rendererBar);

		//Định dạng chung
		plot.setBackgroundPaint(Color.WHITE);
		chart.setBackgroundPaint(Color.WHITE);
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 10));
		domainAxis.setMaximumCategoryLabelLines(3);
		domainAxis.setCategoryMargin(0.2);
		domainAxis.setUpperMargin(0.02);
		domainAxis.setLowerMargin(0.02);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(700, 410));
		return chartPanel;
	}
	public DefaultPieDataset createTyLeKhuyenMaiPieDataset(Date startDate, Date endDate) throws RemoteException {
		DefaultPieDataset dataset = new DefaultPieDataset();
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
		List<Object[]> data = daoThongKe.getTyLeKhuyenMaiTheoDoiTuong(sqlStartDate, sqlEndDate);
		if (data == null) {
			System.err.println("Lỗi khi truy vấn dữ liệu từ CSDL.");
			JOptionPane.showMessageDialog(null, "Lỗi khi truy vấn dữ liệu từ CSDL.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return null; // Trả về null để báo hiệu lỗi
		}
		if (data.isEmpty()) { //Kiểm tra danh sách rỗng sau khi đã kiểm tra null
//			System.out.println("Không có dữ liệu trong khoảng thời gian này.");
			//Không hiển thị JOptionPane ở đây vì hàm display đã xử lý
			return dataset; // Trả về dataset rỗng
		}
		try {
			for (Object[] row : data) {
				String doiTuong = (String) row[0];
				double tiLe = (double) row[2];

				//Kiểm tra giá trị tiLe để tránh IllegalArgumentException do giá trị âm
				if(tiLe < 0){
//					System.err.println("Giá trị tỉ lệ âm cho đối tượng: " + doiTuong);
					continue; //Bỏ qua đối tượng này và tiếp tục vòng lặp
				}
				dataset.setValue(doiTuong, tiLe);
			}
		} catch (ClassCastException e) { // Bắt lỗi ép kiểu nếu có
//			System.err.println("Lỗi ép kiểu dữ liệu từ CSDL: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Lỗi dữ liệu từ CSDL. Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return dataset;
	}
	public DefaultPieDataset createDoanhThuKhuyenMaiDataset(Date startDate, Date endDate) throws RemoteException {
		DefaultPieDataset dataset = new DefaultPieDataset();
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
		List<Object[]> data = daoThongKe.getDoanhThuKhuyenMaiTheoDoiTuong(sqlStartDate, sqlEndDate);

		if (data == null) {
//			System.err.println("Lỗi khi truy vấn dữ liệu từ CSDL.");
			return null;
		}

		if(data.isEmpty()){
			System.out.println("Không có dữ liệu");
			return dataset;
		}

		try {
			for (Object[] row : data) {
				String doiTuong = (String) row[0];
				double tiLe = (double) row[2];
				if(tiLe >= 0) dataset.setValue(doiTuong, tiLe);
				else System.err.println("Giá trị âm được bỏ qua cho đối tượng: " + doiTuong);
			}
		} catch (ClassCastException e) {
			System.err.println("Lỗi ép kiểu dữ liệu: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Lỗi dữ liệu từ CSDL. Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return dataset;
	}
	public void CapNhatBang_KH(Date startDate,Date endDate) throws RemoteException {
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
		List<Object[]> data = daoThongKe.getTopCustomers(sqlStartDate, sqlEndDate);
		if(data == null){
			JOptionPane.showMessageDialog(this, "Không có danh sách khách hàng mua nhiều nhất");
		}
        if (data != null) {
            for (Object[] ds: data){
                model_KH.addRow(ds);
            }
        }
        model_KH.fireTableDataChanged();
	}
	public void CapNhatDoiTra() throws RemoteException {
		Date[] dateRange = CapNhatNgayBatDau_SL();
		Date startDate = dateRange[0];
		Date endDate = dateRange[1];
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
		Object[] ob = daoThongKe.getTiLeDoiTraVe(sqlStartDate, sqlEndDate);
		pnl_bieu_Do_SL.removeAll();
		if(cboLoaiThongKe_SL.getSelectedIndex()==0){
			tileDoi_Tra_SL.setText(String.format("%.2f", ob[0])+"%");
			lbl_tiLeDoiTra_Sl.setText("Tỉ lệ đổi vé");
			soLuongDoiTra.setText(ob[1].toString());;
			lbl_soLuongDoiTra.setText("Số lượng đổi vé");
			String nhan = "Số lượng vé đổi";
			if(cboLoaiThoiGian_SL.getSelectedIndex() == 0){
				String tenBieuDo = "BIỂU ĐỒ SỐ LƯỢNG ĐỔI VÉ THEO NGÀY";
				String donVi = "Ngày";
				DefaultCategoryDataset dataset1 = createDatasetVeDoiTheoNgay(startDate, endDate);
				pnl_bieu_Do_SL.add(createBarChartVeDoiTheoNgay(tenBieuDo,donVi,nhan,dataset1));
			}
			else if(cboLoaiThoiGian_SL.getSelectedIndex() == 1){
				String tenBieuDo = "BIỂU ĐỒ SỐ LƯỢNG ĐỔI VÉ THEO TUẦN";
				String donVi = "Tuần";
				DefaultCategoryDataset dataset1 = createDatasetVeDoiTheoTuan(startDate,endDate);
				pnl_bieu_Do_SL.add(createBarChartVeDoiTheoNgay(tenBieuDo,donVi,nhan,dataset1));
			}
			else if(cboLoaiThoiGian_SL.getSelectedIndex() == 2)
			{
				String tenBieuDo = "BIỂU ĐỒ SỐ LƯỢNG ĐỔI VÉ THEO THÁNG";
				String donVi = "Tháng";
				DefaultCategoryDataset dataset1 = createDatasetVeDoiTheoThang(startDate,endDate);
				pnl_bieu_Do_SL.add(createBarChartVeDoiTheoNgay(tenBieuDo,donVi,nhan,dataset1));
			}
			else if(cboLoaiThoiGian_SL.getSelectedIndex() == 3){
//				System.out.println("SO 4");
				String tenBieuDo = "BIỂU ĐỒ SỐ LƯỢNG ĐỔI VÉ THEO QUÝ";
				String donVi = "Quý";
				DefaultCategoryDataset dataset1 = createDatasetVeDoiTheoQuy(startDate,endDate);
				pnl_bieu_Do_SL.add(createBarChartVeDoiTheoNgay(tenBieuDo,donVi,nhan,dataset1));
			}
			else if(cboLoaiThoiGian_SL.getSelectedIndex() == 4){
				String tenBieuDo = "BIỂU ĐỒ SỐ LƯỢNG ĐỔI VÉ THEO NĂM";
				String donVi = "Năm";
				DefaultCategoryDataset dataset1 = createDatasetVeDoiTheoNam(startDate,endDate);
				pnl_bieu_Do_SL.add(createBarChartVeDoiTheoNgay(tenBieuDo,donVi,nhan,dataset1));
			}

			String[] tencot1 = {"Ngày", "Số lượng đổi vé"};
			pnl_Bang_SL.removeAll();
			model_SL.setRowCount(0);
			model_SL = new DefaultTableModel(tencot1,0);
			table_SL = new CTable(model_SL);
			pnl_Bang_SL.add(new JScrollPane(table_SL));
			List<Object[]> data = daoThongKe.getThongKeVeDoiTheoNgay(new java.sql.Date(startDate.getTime()),new java.sql.Date(endDate.getTime()));
			for (Object[] row : data) {
				model_SL.addRow(row);
			}
			model_SL.fireTableDataChanged();
			pnl_Bang_SL.revalidate();
			pnl_Bang_SL.repaint();

		}else if(cboLoaiThongKe_SL.getSelectedIndex()==1){
			String[] tencot1 = {"Ngày", "Số lượng vé trả"};
			pnl_Bang_SL.removeAll();
			model_SL.setRowCount(0);
			model_SL = new DefaultTableModel(tencot1,0);
			table_SL = new CTable(model_SL);
			pnl_Bang_SL.add(new JScrollPane(table_SL));
			List<Object[]> data = daoThongKe.getThongKeVeTraTheoNgay(new java.sql.Date(startDate.getTime()),new java.sql.Date(endDate.getTime()));
			for (Object[] row : data) {
				model_SL.addRow(row);
			}
			model_SL.fireTableDataChanged();
			pnl_Bang_SL.revalidate();
			pnl_Bang_SL.repaint();
			String nhan = "Số lượng vé trả";
			tileDoi_Tra_SL.setText(String.format("%.2f", ob[2])+"%");
			lbl_tiLeDoiTra_Sl.setText("Tỉ lệ trả vé");
			soLuongDoiTra.setText(ob[3].toString());
			lbl_soLuongDoiTra.setText("Số lượng trả vé");

			if(cboLoaiThoiGian_SL.getSelectedIndex() == 0){
				String tenBieuDo = "BIỂU ĐỒ SỐ LƯỢNG TRẢ VÉ THEO NGÀY";
				String donVi = "Ngày";
				DefaultCategoryDataset dataset1 = createDatasetVeTraTheoNgay(startDate,endDate);
				pnl_bieu_Do_SL.add(createBarChartVeDoiTheoNgay(tenBieuDo,donVi,nhan,dataset1));
			}
			else if(cboLoaiThoiGian_SL.getSelectedIndex() == 1){
				String tenBieuDo = "BIỂU ĐỒ SỐ LƯỢNG TRẢ VÉ THEO TUẦN";
				String donVi = "Tuần";
				DefaultCategoryDataset dataset1 = createDatasetVeTraTheoTuan(startDate,endDate);
				pnl_bieu_Do_SL.add(createBarChartVeDoiTheoNgay(tenBieuDo,donVi,nhan,dataset1));
			}
			else if(cboLoaiThoiGian_SL.getSelectedIndex() == 2)
			{
				String tenBieuDo = "BIỂU ĐỒ SỐ LƯỢNG TRẢ VÉ THEO THÁNG";
				String donVi = "Tháng";
				DefaultCategoryDataset dataset1 = createDatasetVeTraTheoThang(startDate,endDate);
				pnl_bieu_Do_SL.add(createBarChartVeDoiTheoNgay(tenBieuDo,donVi,nhan,dataset1));
			}
			else if(cboLoaiThoiGian_SL.getSelectedIndex() == 3){
				String tenBieuDo = "BIỂU ĐỒ SỐ LƯỢNG TRẢ VÉ THEO QUÝ";
				String donVi = "Quý";
				DefaultCategoryDataset dataset1 = createDatasetVeTraTheoQuy(startDate,endDate);
				pnl_bieu_Do_SL.add(createBarChartVeDoiTheoNgay(tenBieuDo,donVi,nhan,dataset1));
			}
			else if(cboLoaiThoiGian_SL.getSelectedIndex() == 4){
				String tenBieuDo = "BIỂU ĐỒ SỐ LƯỢNG TRẢ VÉ THEO NĂM";
				String donVi = "Năm";
				DefaultCategoryDataset dataset1 = createDatasetVeTraTheoNam(startDate,endDate);
				pnl_bieu_Do_SL.add(createBarChartVeDoiTheoNgay(tenBieuDo,donVi,nhan,dataset1));
			}
		}
		pnl_bieu_Do_SL.revalidate();
		pnl_bieu_Do_SL.repaint();
	}
	public ChartPanel createBarChartVeDoiTheoNgay(String tenBieuDo, String donVi,String nhan,DefaultCategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createBarChart(
				tenBieuDo, // Tiêu đề biểu đồ
				donVi, // Nhãn trục X
				nhan, // Nhãn trục Y
				dataset,
				PlotOrientation.VERTICAL, // Định hướng biểu đồ (dọc)
				true, // Hiển thị legend
				true, // Tooltips
				false // URLs
		);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(1400, 400));
		return chartPanel;
	}
	public DefaultCategoryDataset createDatasetVeDoiTheoNgay(Date startDate, Date endDate) throws RemoteException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
		List<Object[]> data = daoThongKe.getThongKeVeDoiTheoNgay(sqlStartDate, sqlEndDate);



		try {
			for (Object[] row : data) {
				Date ngay = (Date) row[0];
				Integer soLuongVeDoi = (Integer) row[1];
				dataset.addValue(soLuongVeDoi, "Số lượng vé đổi", "Ngày" + ngay);
			}
		} catch (ClassCastException e) {
			System.err.println("Lỗi ép kiểu dữ liệu: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Lỗi dữ liệu từ CSDL. Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		return dataset;
	}
	public DefaultCategoryDataset createDatasetVeDoiTheoTuan(Date startDate, Date endDate) throws RemoteException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
		List<Object[]> data = daoThongKe.getThongKeVeDoiTheoTuan(sqlStartDate, sqlEndDate);

		if (data == null) {
			System.err.println("Lỗi khi truy vấn dữ liệu từ CSDL.");
			return null;
		}

		if(data.isEmpty()){
			System.out.println("Không có dữ liệu");
			return dataset;
		}

		try {
			for (Object[] row : data) {
				int ngay = (int) row[0];
				Integer soLuongVeDoi = (Integer) row[1];
				dataset.addValue(soLuongVeDoi, "Số lượng vé đổi", "Tuần" + ngay);
			}
		} catch (ClassCastException e) {
			System.err.println("Lỗi ép kiểu dữ liệu: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Lỗi dữ liệu từ CSDL. Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		return dataset;
	}
	public DefaultCategoryDataset createDatasetVeDoiTheoThang(Date startDate, Date endDate) throws RemoteException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
		List<Object[]> data = daoThongKe.getThongKeVeDoiTheoThang(sqlStartDate, sqlEndDate);

		if (data == null) {
			System.err.println("Lỗi khi truy vấn dữ liệu từ CSDL.");
			return null;
		}

		if(data.isEmpty()){
			System.out.println("Không có dữ liệu");
			return dataset;
		}

		try {
			for (Object[] row : data) {
				int ngay = (int) row[0];
				Integer soLuongVeDoi = (Integer) row[1];
				dataset.addValue(soLuongVeDoi, "Số lượng vé đổi", "Tháng" + ngay);
			}
		} catch (ClassCastException e) {
			System.err.println("Lỗi ép kiểu dữ liệu: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Lỗi dữ liệu từ CSDL. Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		return dataset;
	}
	public DefaultCategoryDataset createDatasetVeDoiTheoQuy(Date startDate, Date endDate) throws RemoteException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
		List<Object[]> data = daoThongKe.getThongKeVeDoiTheoQuy(sqlStartDate, sqlEndDate);

		if (data == null) {
			System.err.println("Lỗi khi truy vấn dữ liệu từ CSDL.");
			return null;
		}

		if(data.isEmpty()){
			System.out.println("Không có dữ liệu");
			return dataset;
		}

		try {
			for (Object[] row : data) {
				int ngay = (int) row[0];
				Integer soLuongVeDoi = (Integer) row[1];
				dataset.addValue(soLuongVeDoi, "Số lượng vé đổi", "Quý" + ngay);
			}
		} catch (ClassCastException e) {
			System.err.println("Lỗi ép kiểu dữ liệu: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Lỗi dữ liệu từ CSDL. Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		return dataset;
	}
	public DefaultCategoryDataset createDatasetVeDoiTheoNam(Date startDate, Date endDate) throws RemoteException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
		List<Object[]> data = daoThongKe.getThongKeVeDoiTheoNam(sqlStartDate, sqlEndDate);

		if (data == null) {
			System.err.println("Lỗi khi truy vấn dữ liệu từ CSDL.");
			return null;
		}

		if(data.isEmpty()){
			System.out.println("Không có dữ liệu");
			return dataset;
		}

		try {
			for (Object[] row : data) {
				int ngay = (int) row[0];
				Integer soLuongVeDoi = (Integer) row[1];
				dataset.addValue(soLuongVeDoi, "Số lượng vé đổi", "Năm" + ngay);
			}
		} catch (ClassCastException e) {
			System.err.println("Lỗi ép kiểu dữ liệu: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Lỗi dữ liệu từ CSDL. Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		return dataset;
	}
	public DefaultCategoryDataset createDatasetVeTraTheoNgay(Date startDate, Date endDate) throws RemoteException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
		List<Object[]> data = daoThongKe.getThongKeVeTraTheoNgay(sqlStartDate, sqlEndDate);


		try {
			for (Object[] row : data) {
				Date ngay = (Date) row[0];
				Integer soLuongVeDoi = (Integer) row[1];
				dataset.addValue(soLuongVeDoi, "Số lượng vé đổi", "Ngày" + ngay);
			}
		} catch (ClassCastException e) {
			System.err.println("Lỗi ép kiểu dữ liệu: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Lỗi dữ liệu từ CSDL. Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		return dataset;
	}
	public DefaultCategoryDataset createDatasetVeTraTheoTuan(Date startDate, Date endDate) throws RemoteException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
		List<Object[]> data = daoThongKe.getThongKeVeTraTheoTuan(sqlStartDate, sqlEndDate);

		if (data == null) {
			System.err.println("Lỗi khi truy vấn dữ liệu từ CSDL.");
			return null;
		}

		if(data.isEmpty()){
			System.out.println("Không có dữ liệu");
			return dataset;
		}

		try {
			for (Object[] row : data) {
				int ngay = (int) row[0];
				Integer soLuongVeDoi = (Integer) row[1];
				dataset.addValue(soLuongVeDoi, "Số lượng vé đổi", "Tuần" + ngay);
			}
		} catch (ClassCastException e) {
			System.err.println("Lỗi ép kiểu dữ liệu: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Lỗi dữ liệu từ CSDL. Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		return dataset;
	}
	public DefaultCategoryDataset createDatasetVeTraTheoThang(Date startDate, Date endDate) throws RemoteException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
		List<Object[]> data = daoThongKe.getThongKeVeTraTheoThang(sqlStartDate, sqlEndDate);

		if (data == null) {
			System.err.println("Lỗi khi truy vấn dữ liệu từ CSDL.");
			return null;
		}

		if(data.isEmpty()){
			System.out.println("Không có dữ liệu");
			return dataset;
		}

		try {
			for (Object[] row : data) {
				int ngay = (int) row[0];
				Integer soLuongVeDoi = (Integer) row[1];
				dataset.addValue(soLuongVeDoi, "Số lượng vé đổi", "Tháng" + ngay);
			}
		} catch (ClassCastException e) {
			System.err.println("Lỗi ép kiểu dữ liệu: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Lỗi dữ liệu từ CSDL. Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		return dataset;
	}
	public DefaultCategoryDataset createDatasetVeTraTheoQuy(Date startDate, Date endDate) throws RemoteException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
		List<Object[]> data = daoThongKe.getThongKeVeTraTheoQuy(sqlStartDate, sqlEndDate);

		if (data == null) {
			System.err.println("Lỗi khi truy vấn dữ liệu từ CSDL.");
			return null;
		}

		if(data.isEmpty()){
			System.out.println("Không có dữ liệu");
			return dataset;
		}

		try {
			for (Object[] row : data) {
				int ngay = (int) row[0];
				Integer soLuongVeDoi = (Integer) row[1];
				dataset.addValue(soLuongVeDoi, "Số lượng vé đổi", "Quý" + ngay);
			}
		} catch (ClassCastException e) {
			System.err.println("Lỗi ép kiểu dữ liệu: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Lỗi dữ liệu từ CSDL. Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		return dataset;
	}
	public DefaultCategoryDataset createDatasetVeTraTheoNam(Date startDate, Date endDate) throws RemoteException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
		List<Object[]> data = daoThongKe.getThongKeVeTraTheoNam(sqlStartDate, sqlEndDate);
		try {
			for (Object[] row : data) {
				int ngay = (int) row[0];
				Integer soLuongVeDoi = (Integer) row[1];
				dataset.addValue(soLuongVeDoi, "Số lượng vé đổi", "Năm" + ngay);
			}
		} catch (ClassCastException e) {
			System.err.println("Lỗi ép kiểu dữ liệu: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Lỗi dữ liệu từ CSDL. Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		return dataset;
	}
	private Date[] CapNhatNgayBatDau_SL() {
		int startYear = thangBatDauPicker_SL.getModel().getYear() ;
		int startMonth = thangBatDauPicker_SL.getModel().getMonth();
		int startDay = thangBatDauPicker_SL.getModel().getDay();

		int endYear = thangKetThucPicker_SL.getModel().getYear();
		int endMonth = thangKetThucPicker_SL.getModel().getMonth();
		int endDay = thangKetThucPicker_SL.getModel().getDay();

		Calendar calendar = Calendar.getInstance();
		Calendar calendar1 = Calendar.getInstance();

		calendar.set(startYear, startMonth, startDay);
		startDate_Ve = calendar.getTime();

		calendar.set(endYear, endMonth, endDay);
		endDate_Ve = calendar.getTime();
		Date date[] = new Date[2];
		date[0] = startDate_Ve;
		date[1] = endDate_Ve;
		return date;
	}
	private String formatHour(double hour) {
		int h = (int) hour; // Phần nguyên là giờ
		int m = (int) Math.round((hour - h) * 60); // Phần thập phân chuyển đổi sang phút
		return String.format("%02d:%02d", h, m);
	}
}
