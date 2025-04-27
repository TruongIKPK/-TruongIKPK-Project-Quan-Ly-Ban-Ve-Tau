package entity;

import enums.ETrangThaiNhanVien;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.Check;
import utils.Validation;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity

@Check(constraints = "gioiTinh IN (N'Nam', N'Nữ')")
@Check(constraints = "trangThai IN (N'Làm việc', N'Nghỉ làm')")
public class NhanVien implements Serializable {
    //new
    @Id
    @Column(columnDefinition = "varchar(11)")
    private String maNV;

    @Column(columnDefinition = "nvarchar(100)", nullable = false)
    private String tenNV;

    @Column(columnDefinition = "varchar(355)", nullable = true)
    private String duongDanAnh;

    @Column(columnDefinition = "nvarchar(3)", nullable = false)
    private String gioiTinh;

    @Column(columnDefinition = "date", nullable = false)
    private LocalDate ngaySinh;

    @Column(columnDefinition = "date", nullable = false)
    private LocalDate ngayVaoLam;

    @Column(columnDefinition = "varchar(12)", nullable = false, unique = true)
    private String CCCD;

    @Column(columnDefinition = "varchar(12)", nullable = false, unique = true)
    private String sdt;

    @Column(columnDefinition = "varchar(255)", nullable = false, unique = true)
    private String email;

    @Column(columnDefinition = "nvarchar(255)", nullable = false)
    private String diaChi;

    @Column(columnDefinition = "nvarchar(20)", nullable = false)
    private String trangThai;

    @ManyToOne
    @JoinColumn(name = "maChucVu", nullable = false,columnDefinition = "varchar(2)")
    private ChucVu chucVu;

    @ManyToOne
    @JoinColumn(name = "macaLam", nullable = false,columnDefinition = "varchar(3)")
    private CaLam caLam;
//
    @OneToMany(mappedBy = "nhanVien")
    private Set<HoaDon> hoaDons;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "maTaiKhoan", nullable = false)
    private TaiKhoan taiKhoan;

    //end

    public NhanVien() {
        super();
        this.maNV = "";
    }

    public NhanVien(String maNV) {
        this.maNV = maNV;
    }

    public NhanVien(String maNV, String tenNV, String gioiTinh, LocalDate ngaySinh, String sdt, String email, String diaChi, String CCCD, LocalDate ngayVaoLam, ChucVu chucVu, TaiKhoan taiKhoan, String trangThai, CaLam caLam) {
//        if (!Validation.maNV(maNV)) {
//            throw new IllegalArgumentException("Mã nhân viên không hợp lệ");
//        }
        this.maNV = maNV;
        setTenNV(tenNV);
        setGioiTinh(gioiTinh);
        setNgaySinh(ngaySinh);
        setSdt(sdt);
        setEmail(email);
        setDiaChi(diaChi);
        setCCCD(CCCD);
        setNgayVaoLam(ngayVaoLam);
        setChucVu(chucVu);
        setTaiKhoan(taiKhoan);
        setTrangThai(trangThai);
        setCaLam(caLam);
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public NhanVien(String maNV, String tenNV, String gioiTinh, LocalDate ngaySinh, LocalDate ngayVaoLam, String CCCD, String sdt, String email, String diaChi, String trangThai, CaLam caLam, ChucVu chucVu, String duongDanAnh ) {
        this.maNV = maNV;
        setTenNV(tenNV);
        setGioiTinh(gioiTinh);
        setNgaySinh(ngaySinh);
        setSdt(sdt);
        setEmail(email);
        setDiaChi(diaChi);
        setCCCD(CCCD);
        setNgayVaoLam(ngayVaoLam);
        setChucVu(chucVu);
        setTrangThai(trangThai);
        setCaLam(caLam);
        setDuongDanAnh(duongDanAnh);
    }
    public NhanVien(String tenNV, String gioiTinh, LocalDate ngaySinh, String sdt, String email, String diaChi, String CCCD, LocalDate ngayVaoLam, ChucVu chucVu, TaiKhoan taiKhoan, String trangThai, CaLam caLam) {
        this.maNV = "";
        setTenNV(tenNV);
        setGioiTinh(gioiTinh);
        setNgaySinh(ngaySinh);
        setSdt(sdt);
        setEmail(email);
        setDiaChi(diaChi);
        setCCCD(CCCD);
        setNgayVaoLam(ngayVaoLam);
        setChucVu(chucVu);
        setTaiKhoan(taiKhoan);
        setTrangThai(trangThai);
        setCaLam(caLam);
    }

    public String getDuongDanAnh() {
        return duongDanAnh;
    }

    public void setDuongDanAnh(String duongDanAnh) {
        this.duongDanAnh = duongDanAnh;
    }

    public String getMaNV() {
        return maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        // Tên nhân viên không được để trống
        if (tenNV.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên nhân viên không được để trống");
        }

        this.tenNV = tenNV;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        // Giới tính chỉ có thể là Nam hoặc Nữ
        if (!gioiTinh.equalsIgnoreCase("Nam") && !gioiTinh.equalsIgnoreCase("Nữ")) {
            throw new IllegalArgumentException("Giới tính không hợp lệ");
        }

        this.gioiTinh = gioiTinh;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        // Ngày sinh nhân viên phải lớn hơn 18 tuổi
        if (ngaySinh.isAfter(LocalDate.now().minusYears(18))) {
            throw new IllegalArgumentException("Nhân viên phải trên 18 tuổi");
        }

        this.ngaySinh = ngaySinh;
    }

    public String getSdt() {
        return sdt;
    }

    @Pattern(regexp = "^0[0-9]{9}$", message = "Số điện thoại phải bắt đầu bằng 0 và có 10 chữ số")
    public void setSdt(String sdt) {
        // Số điện thoại không được để trống và đúng định dạng
        if (sdt.trim().isEmpty() || !Validation.sdt(sdt)) {
            throw new IllegalArgumentException("Số điện thoại không hợp lệ");
        }

        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // Email không được để trống và đúng định dạng
        if (email.trim().isEmpty() || !Validation.email(email)) {
            throw new IllegalArgumentException("Email không hợp lệ");
        }

        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getCCCD() {
        return CCCD;
    }

    @Pattern(regexp = "^[0-9]{12}$", message = "CCCD phải có 12 chữ số")
    public void setCCCD(String CCCD) {
        if (!Validation.CCCD(CCCD)) {
            throw new IllegalArgumentException("CCCD không hợp lệ");
        }

        this.CCCD = CCCD;
    }

    public LocalDate getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(LocalDate ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public ChucVu getChucVu() {
        return chucVu;
    }

    public void setChucVu(ChucVu chucVu) {
        // Chức vụ không được để trống
        if (chucVu == null) {
            throw new IllegalArgumentException("Chức vụ không hợp lệ");
        }

        this.chucVu = chucVu;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        // Tài khoản không được để trống
        if (taiKhoan == null) {
            throw new IllegalArgumentException("Tài khoản không hợp lệ");
        }

        this.taiKhoan = taiKhoan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public CaLam getCaLam() {
        return caLam;
    }

    public void setCaLam(CaLam caLam) {
        // Ca làm không được để trống
        if (caLam == null) {
            throw new IllegalArgumentException("Ca làm không hợp lệ");
        }

        this.caLam = caLam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NhanVien nhanVien = (NhanVien) o;
        return Objects.equals(maNV, nhanVien.maNV);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maNV);
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "maNV='" + maNV + '\'' +
                ", tenNV='" + tenNV + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", sdt='" + sdt + '\'' +
                ", email='" + email + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", CCCD='" + CCCD + '\'' +
                ", ngayVaoLam=" + ngayVaoLam +
                ", chucVu=" + chucVu +
                ", taiKhoan=" + taiKhoan +
                ", trangThai=" + trangThai +
                ", caLam=" + caLam +
                '}';
    }
}
