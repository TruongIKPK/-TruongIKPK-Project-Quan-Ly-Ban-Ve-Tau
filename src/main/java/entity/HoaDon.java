package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Check;
import utils.Validation;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;
@Entity
public class HoaDon implements Serializable {

    @Id
    @Column(columnDefinition = "varchar(20)")
    private String maHD;

    @Column(columnDefinition = "datetime", nullable = true)
    private LocalDateTime ngayGioLapHD = LocalDateTime.now();

    @Column(nullable = true)
    private int soLuong;

    @ManyToOne
    @JoinColumn(name = "maNV", nullable = false, columnDefinition = "VARCHAR(11)")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "maKH", nullable = false,columnDefinition = "VARCHAR(20)")
    private KhachHang khachHang;

    @Transient
    private ArrayList<Ve> danhSachVe;

    public HoaDon() {
        super();
        this.maHD = "";
    }

    public HoaDon(String maHD) {
        this.maHD = maHD;
    }

    public HoaDon(String maHD, LocalDateTime ngayGioLapHD, NhanVien nhanVien, KhachHang khachHang, int soLuong, ArrayList<Ve> danhSachVe) {
        // Kiểm tra mã hóa đơn
//        if (!Validation.maHD(maHD)) {
//            throw new IllegalArgumentException("Mã hóa đơn không hợp lệ");
//        }

        this.maHD = maHD;
        setNgayGioLapHD(ngayGioLapHD);
        setNhanVien(nhanVien);
        setKhachHang(khachHang);
        setSoLuong(soLuong);
        this.danhSachVe = danhSachVe;
    }

    public HoaDon(LocalDateTime ngayGioLapHD, NhanVien nhanVien, KhachHang khachHang, int soLuong, ArrayList<Ve> danhSachVe) {
        this.maHD = "";
        setNgayGioLapHD(ngayGioLapHD);
        setNhanVien(nhanVien);
        setKhachHang(khachHang);
        setSoLuong(soLuong);
        this.danhSachVe = danhSachVe;
    }
    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaHD() {
        return maHD;
    }

    public LocalDateTime getNgayGioLapHD() {
        return ngayGioLapHD;
    }

    public void setNgayGioLapHD(LocalDateTime ngayGioLapHD) {
        // Kiểm tra ngày giờ lập hóa đơn không được sau ngày giờ hiện tại
        if (ngayGioLapHD.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Ngày giờ lập hóa đơn không được sau ngày giờ hiện tại");
        }

        this.ngayGioLapHD = ngayGioLapHD;
    }

    public ArrayList<Ve> getDanhSachVe() {
        return danhSachVe;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        // Kiểm tra nhân viên không được rỗng
        if (nhanVien == null) {
            throw new IllegalArgumentException("Nhân viên không được rỗng");
        }

        this.nhanVien = nhanVien;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        // Kiểm tra khách hàng không được rỗng
        if (khachHang == null) {
            throw new IllegalArgumentException("Khách hàng không được rỗng");
        }

        this.khachHang = khachHang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        if (soLuong <= 0) {
            throw new IllegalArgumentException("Số lượng phải lớn hơn 0");
        }
        this.soLuong = soLuong;
    }

    public double getThanhTien() {
        double thanhTien = 0;
        for (Ve ve : danhSachVe) {
            thanhTien += ve.getThanhTien();
        }
        return thanhTien;
    }

    public void setDanhSachVe(ArrayList<Ve> danhSachVe) {
        this.danhSachVe = danhSachVe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HoaDon hoaDon = (HoaDon) o;
        return Objects.equals(maHD, hoaDon.maHD);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maHD);
    }

    @Override
    public String toString() {
        return "HoaDon{" +
                "maHD='" + maHD + '\'' +
                ", ngayGioLapHD=" + ngayGioLapHD +
                ", nhanVien=" + nhanVien +
                ", khachHang=" + khachHang +
                ", soLuong=" + soLuong +
                '}';
    }
}
