package entity;

import enums.ETrangThaiTaiKhoan;
import org.mindrot.jbcrypt.BCrypt;
import utils.Regex;
import utils.Validation;

import javax.swing.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @Dự án: tau-viet-express
 * @Class: TaiKhoan
 * @Tạo vào ngày: 30/9/2024
 * @Tác giả: Huy
 */
public class TaiKhoan implements Serializable {
    private final String maTK;
    private String matKhauHash;
    private String trangThai;

    public TaiKhoan() {
        super();
        this.maTK = "";
    }

    public TaiKhoan(String maTK) {
        this.maTK = maTK;
    }

    // dung khi read from db
    public TaiKhoan(String maTK, String matKhauHash, String trangThai) {
        this.maTK = maTK;
        this.matKhauHash = matKhauHash;
        setTrangThai(ETrangThaiTaiKhoan.KICH_HOAT.getTrangThai());
    }

    // khi tao doi tuong
    public TaiKhoan(String matKhau, String trangThai) {
        this.maTK = "";
        setMatKhau(matKhau);
        setTrangThai(trangThai);
    }

    public String getMaTK() {
        return maTK;
    }

    public String getMatKhauHash() {
        return matKhauHash;
    }

    public void setMatKhau(String matKhau) {
        if (!Validation.password(matKhau)) {
            throw new IllegalArgumentException("Mật khẩu không hợp lệ!");
        }
    //neu hop le thi ma hoa
        this.matKhauHash = BCrypt.hashpw(matKhau, BCrypt.gensalt());
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
                ", matKhau='" + matKhauHash + '\'' +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}
