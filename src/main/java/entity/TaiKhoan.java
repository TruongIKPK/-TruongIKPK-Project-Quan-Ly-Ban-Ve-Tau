package entity;

import enums.ETrangThaiTaiKhoan;
import jakarta.persistence.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;
import utils.Validation;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TaiKhoan")
@Check(constraints = "trangThai IN (N'Kích hoạt', N'Bị khóa')")
public class TaiKhoan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "varchar(11)")
    private String maTK;

    @Column(columnDefinition = "varchar(60)", nullable = false)
    @ColumnDefault("'$2a$10$hx.v7Xiy7I8Rpql8ONmMF.WZY3d6pfQmfpp2EgeXJajNJdUa9KVSa'")
    private String matKhau;

    @Column(columnDefinition = "nvarchar(20)", nullable = false)
    private String trangThai;

    @OneToOne(mappedBy = "taiKhoan")
    private NhanVien nhanVien;

    public TaiKhoan() {
        super();
        this.maTK = "";
    }

    public TaiKhoan(String maTK) {
        this.maTK = maTK;
    }

    // khi đọc từ DB
    public TaiKhoan(String maTK, String matKhau, String trangThai) {
        this.maTK = maTK;
        this.matKhau = matKhau;
        setTrangThai(trangThai != null ? trangThai : ETrangThaiTaiKhoan.KICH_HOAT.getTrangThai());
    }

    // khi tạo đối tượng mới
    public TaiKhoan(String matKhau, String trangThai) {
        this.maTK = "";
        setMatKhau(matKhau);
        setTrangThai(trangThai);
    }

    public String getMaTK() {
        return maTK;
    }

    public String getMatKhauHash() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        if (!Validation.password(matKhau)) {
            throw new IllegalArgumentException("Mật khẩu không hợp lệ!");
        }
        this.matKhau = matKhau;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaiKhoan taiKhoan = (TaiKhoan) o;
        return Objects.equals(maTK, taiKhoan.maTK);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maTK);
    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "maTK='" + maTK + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}
