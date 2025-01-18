package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Check;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "LoaiCho")
@Check(constraints = "giaCho > 0")
public class LoaiCho implements Serializable {

    @Id
    @Column(columnDefinition = "varchar(10)", unique = true, nullable = false)
    private final String maLC;

    @Column(columnDefinition = "nvarchar(50)", nullable = false)
    private String tenLC;

    @Column(columnDefinition = "nvarchar(200)", nullable = false)
    private String moTa;

    @Column(columnDefinition = "FLOAT", nullable = false)
    private double giaCho;

    @OneToMany(mappedBy = "loaiCho", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<ChoNgoi> choNgois;


    public LoaiCho() {
        super();
        this.maLC = "";
    }

    public LoaiCho(String maLC) {
        this.maLC = maLC;
    }

    public LoaiCho(String maLC, String tenLC, String moTa, double giaCho) {
        // Kiểm tra mã loại chỗ
//        if (maLC.trim().isEmpty()) {
//            throw new IllegalArgumentException("Mã loại chỗ không hợp lệ");
//        }

        this.maLC = maLC;
        setTenLC(tenLC);
        setMoTa(moTa);
        setGiaCho(giaCho);
    }

    public String getMaLC() {
        return maLC;
    }

    public String getTenLC() {
        return tenLC;
    }

    public void setTenLC(String tenLC) {
        // Kiểm tra tên loại chỗ
        if (tenLC.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên loại chỗ không hợp lệ");
        }

        this.tenLC = tenLC;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        // Kiểm tra mô tả
        if (moTa.trim().isEmpty()) {
            throw new IllegalArgumentException("Mô tả không hợp lệ");
        }

        this.moTa = moTa;
    }

    public double getGiaCho() {
        return giaCho;
    }

    public void setGiaCho(double giaCho) {
        // Giá chỗ lớn hơn 0
        if (giaCho <= 0) {
            throw new IllegalArgumentException("Giá chỗ phải lớn hơn 0");
        }

        this.giaCho = giaCho;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoaiCho loaiCho = (LoaiCho) o;
        return Objects.equals(maLC, loaiCho.maLC);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maLC);
    }

    @Override
    public String toString() {
        return "LoaiCho{" +
                "maLC='" + maLC + '\'' +
                ", tenLC='" + tenLC + '\'' +
                ", moTa='" + moTa + '\'' +
                ", giaCho=" + giaCho +
                '}';
    }
}
