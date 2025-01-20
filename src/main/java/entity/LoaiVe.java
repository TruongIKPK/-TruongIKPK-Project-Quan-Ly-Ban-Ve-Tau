package entity;

import jakarta.persistence.*;
import utils.Validation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class LoaiVe implements Serializable {

    @Id
    @Column(columnDefinition = "varchar(10)", unique = true, nullable = false)
    private final String maLV;

    @Column(columnDefinition = "nvarchar(50)", nullable = false)
    private String tenLV;

    @OneToMany(mappedBy = "loaiVe", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Ve> ves;

    public LoaiVe() {
        super();
        this.maLV = "";
    }

    public LoaiVe(String maLV) {
        this.maLV = maLV;
    }

    public LoaiVe(String maLV, String tenLV) {
        // Kiểm tra mã loại vé
//        if (!Validation.maLV(maLV)) {
//            throw new IllegalArgumentException("Mã loại vé không hợp lệ");
//        }

        this.maLV = maLV;
        setTenLV(tenLV);
    }

    public String getMaLV() {
        return maLV;
    }

    public String getTenLV() {
        return tenLV;
    }

    public void setTenLV(String tenLV) {
        if (tenLV.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên loại vé không được để trống");
        }

        this.tenLV = tenLV;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoaiVe loaiVe = (LoaiVe) o;
        return Objects.equals(maLV, loaiVe.maLV);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maLV);
    }

    @Override
    public String toString() {
        return "LoaiVe{" +
                "maLV='" + maLV + '\'' +
                ", tenLV='" + tenLV + '\'' +
                '}';
    }
}
