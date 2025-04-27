package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Check;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Check(constraints = "trangThai IN ('DA_BAN', 'DA_DOI', 'DA_TRA', 'VE_DUOC_DOI', 'VE_DUOC_TRA')")
public class Ve implements Serializable {
    @Id
    @Column(columnDefinition = "varchar(20)")
    private String maVe;

    @Column(columnDefinition = "datetime")
    private LocalDateTime ngayGioXuatVe;

    @Column(columnDefinition = "float", nullable = true)
    private double thue = 0.1;

    @Column(name = "trangThai", nullable = false)
    private String trangThai;

    @ManyToOne
    @JoinColumn(name = "maHD", nullable = false,columnDefinition = "varchar(20)")
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "maLV", nullable = false, columnDefinition = "varchar(10)")
    private LoaiVe loaiVe;

    @ManyToOne
    @JoinColumn(name = "maKM", nullable = true, columnDefinition = "varchar(15)")
    private KhuyenMai khuyenMai;

//    @OneToOne
    @ManyToOne
    @JoinColumn(name = "maCho", nullable = false,columnDefinition = "varchar(20)")
    private ChoNgoi choNgoi;

    @ManyToOne
    @JoinColumn(name = "maChuyen", nullable = false,columnDefinition = "varchar(20)")
    private ChuyenTau chuyenTau;

    @ManyToOne
    @JoinColumn(name = "maKH", nullable = false,columnDefinition = "varchar(20)")
    private KhachHang khachHang;

    public Ve() {
        super();
        this.maVe = "";
    }

    public Ve(String maVe) {
        this.maVe = maVe;
    }

    public Ve(String maVe, HoaDon hoaDon, LoaiVe loaiVe, LocalDateTime ngayGioXuatVe, ChoNgoi choNgoi, ChuyenTau chuyenTau, KhachHang khachHang, double thue, KhuyenMai khuyenMai, String trangThai) {
//        if (!Validation.maVe(maVe)) {
//            throw new IllegalArgumentException("Mã vé không hợp lệ");
//        }

        this.maVe = maVe;
        setHoaDon(hoaDon);
        setLoaiVe(loaiVe);
        setNgayGioXuatVe(ngayGioXuatVe);
        setChoNgoi(choNgoi);
        setChuyenTau(chuyenTau);
        setKhachHang(khachHang);
        setThue(thue);
        setKhuyenMai(khuyenMai);
        setTrangThai(trangThai);
    }

    public Ve(HoaDon hoaDon, LoaiVe loaiVe, LocalDateTime ngayGioXuatVe, ChoNgoi choNgoi, ChuyenTau chuyenTau, KhachHang khachHang, double thue, KhuyenMai khuyenMai, String trangThai) {
        this.maVe = "";
        setHoaDon(hoaDon);
        setLoaiVe(loaiVe);
        setNgayGioXuatVe(ngayGioXuatVe);
        setChoNgoi(choNgoi);
        setChuyenTau(chuyenTau);
        setKhachHang(khachHang);
        setThue(thue);
        setKhuyenMai(khuyenMai);
        setTrangThai(trangThai);
    }
    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }
    public String getMaVe() {
        return maVe;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        // Hóa đơn không thể null
        if (hoaDon == null) {
            throw new IllegalArgumentException("Hóa đơn không hợp lệ");
        }

        this.hoaDon = hoaDon;
    }

    public LoaiVe getLoaiVe() {
        return loaiVe;
    }

    public void setLoaiVe(LoaiVe loaiVe) {
        // Loại vé không thể null
        if (loaiVe == null) {
            throw new IllegalArgumentException("Loại vé không hợp lệ");
        }

        this.loaiVe = loaiVe;
    }

    public LocalDateTime getNgayGioXuatVe() {
        return ngayGioXuatVe;
    }

    public void setNgayGioXuatVe(LocalDateTime ngayGioXuatVe) {
        // Ngày giờ xuất vé phải sau ngày giờ hiện tại
        if (ngayGioXuatVe.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Ngày giờ xuất vé không hợp lệ");
        }

        this.ngayGioXuatVe = ngayGioXuatVe;
    }

    public ChoNgoi getChoNgoi() {
        return choNgoi;
    }

    public void setChoNgoi(ChoNgoi choNgoi) {
        // Chỗ ngồi không thể null
        if (choNgoi == null) {
            throw new IllegalArgumentException("Chỗ ngồi không hợp lệ");
        }

        this.choNgoi = choNgoi;
    }

    public ChuyenTau getChuyenTau() {
        return chuyenTau;
    }

    public void setChuyenTau(ChuyenTau chuyenTau) {
        // Chuyến tàu không thể null
        if (chuyenTau == null) {
            throw new IllegalArgumentException("Chuyến tàu không hợp lệ");
        }

        this.chuyenTau = chuyenTau;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        // Khách hàng không thể null
        if (khachHang == null) {
            throw new IllegalArgumentException("Khách hàng không hợp lệ");
        }

        this.khachHang = khachHang;
    }

    public double getThue() {
        return thue;
    }

    public void setThue(double thue) {
        this.thue = thue;
    }

    public KhuyenMai getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(KhuyenMai khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        if (trangThai == null) {
            throw new IllegalArgumentException("Trạng thái không hợp lệ");
        }
        this.trangThai = trangThai;
    }

    public double getTienKM () {
        if (khuyenMai != null) {
            return (choNgoi.getLoaiCho().getGiaCho() * thue + choNgoi.getLoaiCho().getGiaCho()) * khuyenMai.getPhanTramKM();
        }

        return 0;
    }

    public double getThanhTien () {
        return choNgoi.getLoaiCho().getGiaCho() + choNgoi.getLoaiCho().getGiaCho() * thue - getTienKM();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ve ve = (Ve) o;
        return Objects.equals(maVe, ve.maVe);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maVe);
    }

    @Override
    public String toString() {
        return "Ve{" +
                "maVe='" + maVe + '\'' +
                ", hoaDon=" + hoaDon +
                ", loaiVe=" + loaiVe +
                ", ngayGioXuatVe=" + ngayGioXuatVe +
                ", choNgoi=" + choNgoi +
                ", chuyenTau=" + chuyenTau +
                ", khachHang=" + khachHang +
                ", thue=" + thue +
                ", khuyenMai=" + khuyenMai +
                ", trangThai=" + trangThai +
                ", tienKM=" + getTienKM() +
                ", thanhTien=" + getThanhTien() +
                '}';
    }
}
