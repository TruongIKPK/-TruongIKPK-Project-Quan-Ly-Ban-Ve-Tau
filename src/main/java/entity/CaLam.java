package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Check;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

@Entity
public class CaLam implements Serializable {
    @Id
    @Column(columnDefinition = "varchar(3)")
    private String maCL;

    @Column(columnDefinition = "nvarchar(20)", nullable = false)
    private String tenCL;

    @Column(columnDefinition = "time(6)", nullable = false)
    private LocalTime gioBD;

    @Column(columnDefinition = "time(6)", nullable = false)
    private LocalTime gioKetThuc;

    @OneToMany(mappedBy = "caLam")
    private Set<NhanVien> nhanViens;

    public CaLam() {
        super();
        this.maCL = "";
    }

    public CaLam(String maCL) {
        setMaCL(maCL);
    }

    public CaLam(String maCL, String tenCL, LocalTime gioBD, LocalTime gioKetThuc) {
        setMaCL(maCL);
        this.tenCL = tenCL;
        this.gioBD = gioBD;
        this.gioKetThuc = gioKetThuc;
    }

    public void setMaCL(String maCL) {
        if (!"CA1".equals(maCL) && !"CA2".equals(maCL)) {
            throw new IllegalArgumentException("maCL phải là 'CA1' hoặc 'CA2'");
        }
        this.maCL = maCL;
    }

    public String getMaCL() {
        return maCL;
    }

    public String getTenCL() {
        return tenCL;
    }

    public void setTenCL(String tenCL) {
        this.tenCL = tenCL;
    }

    public LocalTime getGioBD() {
        return gioBD;
    }

    public void setGioBD(LocalTime gioBD) {
        this.gioBD = gioBD;
    }

    public LocalTime getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(LocalTime gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaLam caLam = (CaLam) o;
        return Objects.equals(maCL, caLam.maCL);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maCL);
    }

    @Override
    public String toString() {
        return "CaLam{" +
                "maCL='" + maCL + '\'' +
                ", tenCL='" + tenCL + '\'' +
                ", gioBD=" + gioBD +
                ", gioKetThuc=" + gioKetThuc +
                '}';
    }
}
