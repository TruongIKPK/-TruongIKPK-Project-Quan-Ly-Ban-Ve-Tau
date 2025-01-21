package entity;

import enums.ETrangThaiChoNgoi;
import jakarta.persistence.*;
import org.hibernate.annotations.Check;
import utils.Validation;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
public class ChoNgoi implements Serializable {

    @Id
    @Column(columnDefinition = "varchar(20)")
    private String maCho;

    @ManyToOne
    @JoinColumn(name = "maLC", nullable = false,columnDefinition = "varchar(10)")
    private LoaiCho loaiCho;

    @ManyToOne
    @JoinColumn(name = "maToa", nullable = false,columnDefinition = "varchar(20)")
    private Toa toa;

    @Enumerated(EnumType.STRING)
    @Column(name = "trangThai", nullable = false)
    private ETrangThaiChoNgoi trangThai;

    @OneToOne(mappedBy = "choNgoi")
    private Ve ve;

    public ChoNgoi() {
        super();
        this.maCho = "";
    }

    public ChoNgoi(String maCho) {
        this.maCho = maCho;
    }

    public ChoNgoi(String maCho, LoaiCho loaiCho, Toa toa) {
        // Kiểm tra mã chỗ
//        if (!Validation.maCho(maCho)) {
//            throw new IllegalArgumentException("Mã chỗ không hợp lệ");
//        }
        this.maCho = maCho;
        setLoaiCho(loaiCho);
        setToa(toa);
    }

    public ChoNgoi(LoaiCho loaiCho, Toa toa) {
        this.maCho = "";
        setLoaiCho(loaiCho);
        setToa(toa);
        this.trangThai = ETrangThaiChoNgoi.CON_TRONG;
    }

    public ChoNgoi(String maCho, LoaiCho loaiCho, Toa toa, Ve ve) {

        this.maCho = maCho;
        this.loaiCho = loaiCho;
        this.toa = toa;
        this.ve = ve;
    }

    public void setMaCho(String maCho) {
        this.maCho = maCho;
    }

    public String getMaCho() {
        return maCho;
    }

    public LoaiCho getLoaiCho() {
        return loaiCho;
    }

    public double getGiaCho() {
        return loaiCho.getGiaCho();
    }

    public void setLoaiCho(LoaiCho loaiCho) {
        // Kiểm tra loại chỗ
        if (loaiCho == null) {
            throw new IllegalArgumentException("Loại chỗ không được để trống");
        }

        this.loaiCho = loaiCho;
    }

    public Toa getToa() {
        return toa;
    }

    public void setToa(Toa toa) {
        // Kiểm tra toa
        if (toa == null) {
            throw new IllegalArgumentException("Toa không được để trống");
        }

        this.toa = toa;
    }

    public int getSoThuTu() {
        return Integer.parseInt(maCho.substring(maCho.length() - 2));
    }

    public ETrangThaiChoNgoi getTrangThai() {
        return trangThai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChoNgoi choNgoi = (ChoNgoi) o;
        return Objects.equals(maCho, choNgoi.maCho);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maCho);
    }

    @Override
    public String toString() {
        return "ChoNgoi{" +
                "maCho='" + maCho + '\'' +
                ", loaiCho=" + loaiCho +
                ", toa=" + toa +
                ", trangThai=" + trangThai +
                '}';
    }

    public Ve getVe() {
        return ve;
    }
}
