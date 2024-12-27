package entity;

import enums.ETrangThaiTau;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @Dự án: tau-viet-express
 * @Class: Tau
 * @Tạo vào ngày: 30/9/2024
 * @Tác giả: Huy
 */
public class Tau implements Serializable {
    private final String maTau;
    private String tenTau;
    private String trangThai;
    private ArrayList<Toa> danhSachToa;

    public Tau() {
        super();
        this.maTau = "";
    }

    public Tau(String maTau) {
        this.maTau = maTau;
    }

    public Tau(String maTau, String tenTau, String trangThai, ArrayList<Toa> danhSachToa) {
        // Kiểm tra mã tàu không được để trống
//        if (maTau.trim().isEmpty()) {
//            throw new IllegalArgumentException("Mã tàu không được để trống");
//        }

        this.maTau = maTau;
        setTenTau(tenTau);
        setTrangThai(trangThai);
        this.danhSachToa = danhSachToa;
    }

    public Tau(String tenTau, String trangThai, ArrayList<Toa> danhSachToa) {
        this.maTau = "";
        setTenTau(tenTau);
        setTrangThai(trangThai);
        this.danhSachToa = danhSachToa;
    }

    public String getMaTau() {
        return maTau;
    }

    public String getTenTau() {
        return tenTau;
    }

    public void setTenTau(String tenTau) {
        // Kiểm tra tên tàu không được để trống
        if (tenTau.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên tàu không được để trống");
        }

        this.tenTau = tenTau;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        // Kiểm tra trạng thái tàu không được để trống
        if (trangThai == null) {
            throw new IllegalArgumentException("Trạng thái tàu không được để trống");
        }

        this.trangThai = trangThai;
    }

    public ArrayList<Toa> getDanhSachToa() {
        return danhSachToa;
    }

    public void setDanhSachToa(ArrayList<Toa> danhSachToa) {
        this.danhSachToa = danhSachToa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tau tau = (Tau) o;
        return Objects.equals(maTau, tau.maTau);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maTau);
    }

    @Override
    public String toString() {
        return "Tau{" +
                "maTau='" + maTau + '\'' +
                ", tenTau='" + tenTau + '\'' +
                ", trangThai=" + trangThai +
                '}';
    }
}



