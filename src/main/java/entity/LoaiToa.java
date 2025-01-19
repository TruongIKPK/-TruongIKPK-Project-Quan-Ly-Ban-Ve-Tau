package entity;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.*;

@Entity
@Table(name = "LoaiToa")
public class LoaiToa implements Serializable {

    @Id
    @Column(name = "maLT")
    private final String maLT;

    @Column(name = "tenLT", nullable = false)
    private String tenLT;

    public LoaiToa() {
        super();
        this.maLT = "";
    }

    public LoaiToa(String maLT) {
        this.maLT = maLT;
    }

    public LoaiToa(String maLT, String tenLT) {
        this.maLT = maLT;
        setTenLT(tenLT);
    }

    public String getMaLT() {
        return maLT;
    }

    public String getTenLT() {
        return tenLT;
    }

    public void setTenLT(String tenLT) {
        // Kiểm tra tên loại toa không được rỗng
        if (tenLT.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên loại toa không được rỗng");
        }

        this.tenLT = tenLT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoaiToa loaiToa = (LoaiToa) o;
        return Objects.equals(maLT, loaiToa.maLT);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maLT);
    }

    @Override
    public String toString() {
        return "LoaiToa{" +
                "maLT='" + maLT + '\'' +
                ", tenLT='" + tenLT + '\'' +
                '}';
    }
}