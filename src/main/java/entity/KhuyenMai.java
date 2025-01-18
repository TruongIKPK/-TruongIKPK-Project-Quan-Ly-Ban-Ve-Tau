package entity;

import com.sun.istack.NotNull;
import jakarta.persistence.*;
import org.hibernate.annotations.Check;
import utils.Validation;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "KhuyenMai")
@Check(constraints = "phanTramKM >= 0 AND phanTramKM <= 1")
@Check(constraints = "ngayApDung >= CURRENT_DATE")
@Check(constraints = "ngayKetThuc > ngayApDung")

public class KhuyenMai implements Serializable {

    @Id
    @Column(columnDefinition = "varchar(15)", unique = true, nullable = false)
    private final String maKM;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate ngayBD;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate ngayKT;

    @Column(columnDefinition = "nvarchar(30)")
    private String doiTuong;

    @Column(columnDefinition = "FLOAT")
    private double phanTramKM;

    @Column(columnDefinition = "BIT")
    private boolean daGuiThongBao;

    @OneToMany(mappedBy = "khuyenMai", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ve> ves;

    public KhuyenMai() {
        super();
        this.maKM = "";
    }

    public KhuyenMai(String maKM) {
        this.maKM = maKM;
    }

    public KhuyenMai(String maKM, LocalDate ngayBD, LocalDate ngayKT, String doiTuong, double phanTramKM, boolean daGuiThongBao) {
        this.maKM = maKM;
        setNgayBD(ngayBD);
        setNgayKT(ngayKT);
        setDoiTuong(doiTuong);
        setPhanTramKM(phanTramKM);
        this.daGuiThongBao = daGuiThongBao;
    }
    public KhuyenMai(String maKM, LocalDate ngayBD, LocalDate ngayKT, String doiTuong, double phanTramKM) {
        this.maKM = maKM;
        setNgayBD(ngayBD);
        setNgayKT(ngayKT);
        setDoiTuong(doiTuong);
        setPhanTramKM(phanTramKM);
    }

    public KhuyenMai(LocalDate ngayBD, LocalDate ngayKT, String doiTuong, double phanTramKM) {
        this.maKM = "";
        setNgayBD(ngayBD);
        setNgayKT(ngayKT);
        setDoiTuong(doiTuong);
        setPhanTramKM(phanTramKM);
    }

    public String getMaKM() {
        return maKM;
    }

    public LocalDate getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(LocalDate ngayKT) {
        // Kiểm tra ngày kết thúc phải sau hoặc bằng ngày bắt đầu
        if (ngayKT.isBefore(ngayBD)) {
            throw new IllegalArgumentException("Ngày kết thúc không hợp lệ");
        }

        this.ngayKT = ngayKT;
    }

    public double getPhanTramKM() {
        return phanTramKM;
    }

    public void setPhanTramKM(double phanTramKM) {
        // Kiểm tra phần trăm khuyến mãi phải từ 0 đến 1
        if (phanTramKM < 0 || phanTramKM > 1) {
            throw new IllegalArgumentException("Phần trăm khuyến mãi không hợp lệ");
        }

        this.phanTramKM = phanTramKM;
    }

    public LocalDate getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(LocalDate ngayBD) {
        // Kiểm tra ngày bắt đầu phải sau hoặc bằng ngày hiện tại
//        if (ngayBD.isBefore(LocalDate.now())) {
//            throw new IllegalArgumentException("Ngày bắt đầu không hợp lệ");
//        }

        this.ngayBD = ngayBD;
    }
    // doi tuong
    public String getDoiTuong() {
        return doiTuong;}
    public void setDoiTuong(String doiTuong) {
        this.doiTuong = doiTuong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KhuyenMai khuyenMai = (KhuyenMai) o;
        return Objects.equals(maKM, khuyenMai.maKM);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maKM);
    }

    @Override
    public String toString() {
        return "KhuyenMai{" +
                "maKM='" + maKM + '\'' +
                ", ngayBD=" + ngayBD +
                ", ngayKT=" + ngayKT +
                ", phanTramKM='" + phanTramKM + '\'' +
                '}';
    }
    //get
    public boolean isDaGuiThongBao() {
        return daGuiThongBao;
    }
}