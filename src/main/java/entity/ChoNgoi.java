package entity;

import enums.ETrangThaiChoNgoi;
import jakarta.persistence.*;
import org.hibernate.annotations.Check;
import utils.Validation;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ChoNgoi")
@Check(constraints = "trangThai IN (N'Còn trống', N'Đã đặt')")
public class ChoNgoi implements Serializable {

    @Id
    @Column(columnDefinition = "varchar(20)", unique = true, nullable = false)
    private final String maCho;

    @ManyToOne
    @JoinColumn(name = "maLC", nullable = false)
    @Column(columnDefinition = "varchar(10)", nullable = false)
    private LoaiCho loaiCho;

    @ManyToOne
    @JoinColumn(name = "maToa", nullable = false)
    @Column(columnDefinition = "varchar(20)", nullable = false)
    private Toa toa;

    @OneToOne(mappedBy = "choNgoi")
    private Set<Ve> ves;

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
                '}';
    }
}
