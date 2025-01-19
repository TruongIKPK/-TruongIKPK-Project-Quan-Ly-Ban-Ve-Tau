package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Check;
import utils.Validation;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Check(constraints = "sdt LIKE '0%' AND LEN(sdt) = 10")
@Check(constraints = "email LIKE '%@%.%' OR email IS NULL")
@Check(constraints = "ngaySinh < GETDATE()")
public class KhachHang implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "varchar(20)")
    private String maKH;

    @Column(columnDefinition = "nvarchar(50)", nullable = false)
    private String tenKH;

    @Column(columnDefinition = "char(12)", unique = true, nullable = true)
    private String CCCD;

    @Column(columnDefinition = "char(10)", nullable = false, unique = true)
    private String sdt;

    @Column(columnDefinition = "varchar(100)", nullable = true)
    private String email;

    @Column(columnDefinition = "date", nullable = true)
    private LocalDate ngaySinh;

    @Column(columnDefinition = "nvarchar(30)", nullable = false)
    private String doiTuong;

    @OneToMany(mappedBy = "khachHang")
    private Set<HoaDon> hoaDons;

    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ve> veList;

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public KhachHang() {
        super();
        this.maKH = "";
    }

    public KhachHang(String maKH) {
        this.maKH = maKH;
    }

    public KhachHang(String maKH, String tenKH, String CCCD, String sdt, String email, LocalDate ngaySinh, String doiTuong) {
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
