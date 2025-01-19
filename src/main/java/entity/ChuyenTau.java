package entity;

import utils.Validation;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ChuyenTau")
public class ChuyenTau implements Serializable {
    @Id
    @Column(name = "maChuyen", nullable = false, unique = true, columnDefinition = "varchar(20)")
    private final String maChuyen;

    @Column(name = "macTau", nullable = false)
    private String macTau;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maTau", referencedColumnName = "maTau", nullable = false)
    private Tau tau;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gaDi", referencedColumnName = "maGa", nullable = false)
    private Ga gaDi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gaDen", referencedColumnName = "maGa", nullable = false)
    private Ga gaDen;

    @Column(name = "ngayGioDi", nullable = false)
    private LocalDateTime ngayGioDi;

    @Column(name = "ngayGioDen", nullable = false)
    private LocalDateTime ngayGioDen;

    @Column(name = "trangThai", nullable = false)
    private String trangThai;

    @OneToMany(mappedBy = "chuyenTau", fetch = FetchType.LAZY)
    private Set<Ve> danhSachVe;

    public ChuyenTau() {
        super();
        this.maChuyen = "";
    }

    public ChuyenTau(String maChuyen) {
        this.maChuyen = maChuyen;
    }

    public ChuyenTau(String maChuyen, String macTau, Tau tau, Ga gaDi, Ga gaDen,
                     LocalDateTime ngayGioDi, LocalDateTime ngayGioDen, String trangThai) {
        this.maChuyen = maChuyen;
        setMacTau(macTau);
        setTau(tau);
        setGaDi(gaDi);
        setGaDen(gaDen);
        setNgayGioDi(ngayGioDi);
        setNgayGioDen(ngayGioDen);
        setTrangThai(trangThai);
    }

    public ChuyenTau(String macTau, Tau tau, Ga gaDi, Ga gaDen, LocalDateTime ngayGioDi,
                     LocalDateTime ngayGioDen, String trangThai) {
        this.maChuyen = "";
        setMacTau(macTau);
        setTau(tau);
        setGaDi(gaDi);
        setGaDen(gaDen);
        setNgayGioDi(ngayGioDi);
        setNgayGioDen(ngayGioDen);
        setTrangThai(trangThai);
    }

    public ChuyenTau(Tau tau, Ga gaDi, Ga gaDen, LocalDateTime ngayGioDi,
                     LocalDateTime ngayGioDen, String trangThai) {
        this.maChuyen = "";
        setTau(tau);
        setGaDi(gaDi);
        setGaDen(gaDen);
        setNgayGioDi(ngayGioDi);
        setNgayGioDen(ngayGioDen);
        setTrangThai(trangThai);
    }

    public String getMaChuyen() {
        return maChuyen;
    }

    public String getMacTau() {
        return macTau;
    }

    public void setMacTau(String macTau) {
        if (macTau.trim().isEmpty()) {
            throw new IllegalArgumentException("Mác tàu không hợp lệ");
        }
        this.macTau = macTau;
    }

    public Tau getTau() {
        return tau;
    }

    public void setTau(Tau tau) {
        if (tau == null) {
            throw new IllegalArgumentException("Tàu không hợp lệ");
        }
        this.tau = tau;
    }

    public Ga getGaDi() {
        return gaDi;
    }

    public void setGaDi(Ga gaDi) {
        if (gaDi == null) {
            throw new IllegalArgumentException("Ga đi không hợp lệ");
        }
        this.gaDi = gaDi;
    }

    public Ga getGaDen() {
        return gaDen;
    }

    public void setGaDen(Ga gaDen) {
        if (gaDen == null) {
            throw new IllegalArgumentException("Ga đến không hợp lệ");
        }
        this.gaDen = gaDen;
    }

    public LocalDateTime getNgayGioDi() {
        return ngayGioDi;
    }

    public void setNgayGioDi(LocalDateTime ngayGioDi) {
        this.ngayGioDi = ngayGioDi;
    }

    public LocalDateTime getNgayGioDen() {
        return ngayGioDen;
    }

    public void setNgayGioDen(LocalDateTime ngayGioDen) {
        if (ngayGioDen.isBefore(ngayGioDi)) {
            throw new IllegalArgumentException("Ngày giờ đến không hợp lệ");
        }
        this.ngayGioDen = ngayGioDen;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChuyenTau chuyenTau = (ChuyenTau) o;
        return Objects.equals(maChuyen, chuyenTau.maChuyen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maChuyen);
    }

    @Override
    public String toString() {
        return "ChuyenTau{" +
                "maChuyen='" + maChuyen + '\'' +
                ", macTau='" + macTau + '\'' +
                ", tau=" + tau +
                ", gaDi='" + gaDi + '\'' +
                ", gaDen='" + gaDen + '\'' +
                ", ngayGioDi=" + ngayGioDi +
                ", ngayGioDen=" + ngayGioDen +
                '}';
    }
}
