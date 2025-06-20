package entity;

import enums.ETrangThaiTau;
import jakarta.persistence.*;

import jakarta.persistence.*;
import org.hibernate.annotations.Proxy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity
public class Tau implements Serializable {
    @Id
    @Column(name = "maTau", nullable = false, unique = true)
    private final String maTau;

    @Column(name = "tenTau", nullable = false)
    private String tenTau;

    @Column(name = "trangThai", nullable = false)
    private String trangThai;

    @OneToMany(mappedBy = "tau", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Toa> danhSachToa;

    @OneToMany(mappedBy = "tau", cascade = CascadeType.ALL)
    private Set<ChuyenTau> danhSachChuyenTau;

    public Tau() {
        super();
        this.maTau = "";
    }

    public Tau(String maTau) {
        this.maTau = maTau;
    }

    public Tau(String maTau, String tenTau, String trangThai, List<Toa> danhSachToa) {
        this.maTau = maTau;
        setTenTau(tenTau);
        setTrangThai(trangThai);
        this.danhSachToa = danhSachToa;
    }

    public Tau(String maTau, String tenTau, String trangThai) {
        this.maTau = maTau;
        setTenTau(tenTau);
        setTrangThai(trangThai);
    }

    public Tau(String tenTau, String trangThai, List<Toa> danhSachToa) {
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
        if (tenTau.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên tàu không được để trống");
        }
        this.tenTau = tenTau;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        if (trangThai == null) {
            throw new IllegalArgumentException("Trạng thái tàu không được để trống");
        }
        this.trangThai = trangThai;
    }

    public List<Toa> getDanhSachToa() {
        return danhSachToa;
    }

    public void setDanhSachToa(List<Toa> danhSachToa) {
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
