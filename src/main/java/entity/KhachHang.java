package entity;

import utils.Validation;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @Dự án: tau-viet-express
 * @Class: KhachHang
 * @Tạo vào ngày: 30/9/2024
 * @Tác giả: Huy
 */
public class KhachHang implements Serializable {
    private final String maKH;
    private String tenKH;
    private String CCCD;
    private String sdt;
    private String email;
    private LocalDate ngaySinh;
    private String doiTuong;

    public KhachHang() {
        super();
        this.maKH = "";
    }

    public KhachHang(String maKH) {
        this.maKH = maKH;
    }

    public KhachHang(String maKH, String tenKH, String CCCD, String sdt, String email, LocalDate ngaySinh, String doiTuong) {
        // Kiểm tra tên khách hàng không được rỗng
//        if (!Validation.maKH(maKH)) {
//            throw new IllegalArgumentException("Mã khách hàng không hợp lệ");
//        }

        this.maKH = maKH;
        setTenKH(tenKH);
        setCCCD(CCCD);
        setSdt(sdt);
        setEmail(email);
        setNgaySinh(ngaySinh);
        this.doiTuong = doiTuong;
    }

    public KhachHang(String tenKH, String CCCD, String sdt, String email, LocalDate ngaySinh, String doiTuong) {
        this.maKH = "";
        setTenKH(tenKH);
        setCCCD(CCCD);
        setSdt(sdt);
        setEmail(email);
        setNgaySinh(ngaySinh);
        this.doiTuong = doiTuong;
    }

    public String getMaKH() {
        return maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        // Kiểm tra tên khách hàng không được rỗng
        if (tenKH.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên khách hàng không được rỗng");
        }

        this.tenKH = tenKH;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
//        // Kiểm tra CCCD phải có 12 số
//        if (CCCD!= null && !Validation.CCCD(CCCD)) {
//            throw new IllegalArgumentException("CCCD không hợp lệ");
//        }

        this.CCCD = CCCD;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        // Kiểm tra số điện thoại không được rỗng, phải bắt đầu bằng 0 và có 10 chữ số
        if (!Validation.sdt(sdt)) {
            throw new IllegalArgumentException("Số điện thoại không hợp lệ");
        }

        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
//        // Kiểm tra email hợp lệ
//        if (email != null && !Validation.email(email)) {
//            throw new IllegalArgumentException("Email không hợp lệ");
//        }

        this.email = email;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        // Kiểm tra ngày sinh không được sau ngày hiện tại
        if (ngaySinh != null && ngaySinh.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Ngày sinh không được sau ngày hiện tại");
        }

        this.ngaySinh = ngaySinh;
    }

    public String getDoiTuong() {
        return doiTuong;
    }

    public void setDoiTuong(String doiTuong) {
        this.doiTuong = doiTuong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KhachHang khachHang = (KhachHang) o;
        return Objects.equals(maKH, khachHang.maKH);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maKH);
    }

    @Override
    public String toString() {
        return "KhachHang{" +
                "maKH='" + maKH + '\'' +
                ", tenKH='" + tenKH + '\'' +
                ", CCCD='" + CCCD + '\'' +
                ", sdt='" + sdt + '\'' +
                ", email='" + email + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", doiTuong='" + doiTuong + '\'' +
                '}';
    }
}
