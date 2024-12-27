package entity;

import enums.ETrangThaiNhanVien;
import utils.Validation;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @Dự án: tau-viet-express
 * @Class: NhanVien
 * @Tạo vào ngày: 30/9/2024
 * @Tác giả: Huy
 */
public class NhanVien implements Serializable {
    private final String maNV;
    private String tenNV;
    private String gioiTinh;
    private LocalDate ngaySinh;
    private String sdt;
    private String email;
    private String diaChi;
    private String CCCD;
    private LocalDate ngayVaoLam;
    private ChucVu chucVu;
    private TaiKhoan taiKhoan;
    private String trangThai;
    private CaLam caLam;

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
