package entity;

import jakarta.persistence.*;
import utils.Validation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

@Entity
public class Toa implements Serializable {
    @Id
    @Column(columnDefinition = "varchar(20)")
    private final String maToa;

    @ManyToOne
    @JoinColumn(name = "tau")
    private Tau tau;

    @ManyToOne
    @JoinColumn(name = "loaiToa")
    private LoaiToa loaiToa;

    private int soLuongCho;

    @OneToMany(mappedBy = "toa")
    private ArrayList<ChoNgoi> danhSachChoNgoi;

    @Transient
    private int soThuTu;

    public Toa() {
        super();
        this.maToa = "";
    }

    public Toa(String maToa) {
        this.maToa = maToa;
    }

    public Toa(String maToa, Tau tau, LoaiToa loaiToa, int soLuongCho, ArrayList<ChoNgoi> danhSachChoNgoi) {
        this.maToa = maToa;
        this.tau = tau;
        this.loaiToa = loaiToa;
        this.soLuongCho = soLuongCho;
        this.danhSachChoNgoi = danhSachChoNgoi;
    }

    public String getMaToa() {
        return maToa;
    }

    public Tau getTau() {
        return tau;
    }

    public void setTau(Tau tau) {
        this.tau = tau;
    }

    public LoaiToa getLoaiToa() {
        return loaiToa;
    }

    public void setLoaiToa(LoaiToa loaiToa) {
        this.loaiToa = loaiToa;
    }

    public int getSoLuongCho() {
        return soLuongCho;
    }

    public void setSoLuongCho(int soLuongCho) {
        this.soLuongCho = soLuongCho;
    }

    public ArrayList<ChoNgoi> getDanhSachChoNgoi() {
        return danhSachChoNgoi;
    }

    public void setDanhSachChoNgoi(ArrayList<ChoNgoi> danhSachChoNgoi) {
        this.danhSachChoNgoi = danhSachChoNgoi;
    }

    public int getSoThuTu() {
        return Integer.parseInt(maToa.substring(maToa.length() - 2));
    }

    public void setSoThuTu(int soThuTu) {
        this.soThuTu = soThuTu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Toa toa = (Toa) o;
        return Objects.equals(maToa, toa.maToa);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maToa);
    }

    @Override
    public String toString() {
        return "Toa{" +
                "maToa='" + maToa + '\'' +
                ", tau=" + tau +
                ", loaiToa=" + loaiToa +
                ", soLuongCho=" + soLuongCho +
                '}';
    }
}
