package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Check;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ChucVu")
@Check(constraints = "maCV IN ('QL', 'NV')")
public class ChucVu implements Serializable {

    @Id
    @Column(columnDefinition = "varchar(2)")
    private String maCV;

    @Column(columnDefinition = "nvarchar(20)", nullable = false)
    private String tenCV;

    @OneToMany(mappedBy = "chucVu")
    private Set<NhanVien> nhanViens;

    public ChucVu() {
        super();
        this.maCV = "";
    }

    public ChucVu(String maCV) {
        this.maCV = maCV;
    }

    public ChucVu(String maCV, String tenCV) {
//        if (maCV.trim().isEmpty()) {
//            throw new IllegalArgumentException("Mã chức vụ không hợp lệ");
//        }

        this.maCV = maCV;
        setTenCV(tenCV);
    }

    public String getMaCV() {
        return maCV;
    }

    public String getTenCV() {
        return tenCV;
    }

    public void setTenCV(String tenCV) {
        if (tenCV.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên chức vụ không hợp lệ");
        }

        this.tenCV = tenCV;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChucVu chucVu = (ChucVu) o;
        return Objects.equals(maCV, chucVu.maCV);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maCV);
    }

    @Override
    public String toString() {
        return "ChucVu{" +
                "maCV='" + maCV + '\'' +
                ", tenCV='" + tenCV + '\'' +
                '}';
    }
}
