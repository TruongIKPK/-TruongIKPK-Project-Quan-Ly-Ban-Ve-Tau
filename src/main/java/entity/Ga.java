package entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Ga")
public class Ga implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maGa", nullable = false, unique = true)
    private final int maGa;

    @Column(name = "tenGa")
    private String tenGa;

    // Mối quan hệ một-nhiều với ChuyenTau (chuyến tàu đi và đến)
    @OneToMany(mappedBy = "gaDi", fetch = FetchType.LAZY)
    private List<ChuyenTau> chuyenTauDi = new ArrayList<>();

    @OneToMany(mappedBy = "gaDen", fetch = FetchType.LAZY)
    private List<ChuyenTau> chuyenTauDen = new ArrayList<>();

    public Ga() {
        super();
        this.maGa = 0;
    }

    public Ga(int maGa) {
        this.maGa = maGa;
    }

    public Ga(int maGa, String tenGa) {
        this.maGa = maGa;
        this.tenGa = tenGa;
    }

    public int getMaGa() {
        return maGa;
    }

    public String getTenGa() {
        return tenGa;
    }

    public void setTenGa(String tenGa) {
        this.tenGa = tenGa;
    }

    public List<ChuyenTau> getChuyenTauDi() {
        return chuyenTauDi;
    }

    public void setChuyenTauDi(List<ChuyenTau> chuyenTauDi) {
        this.chuyenTauDi = chuyenTauDi;
    }

    public List<ChuyenTau> getChuyenTauDen() {
        return chuyenTauDen;
    }

    public void setChuyenTauDen(List<ChuyenTau> chuyenTauDen) {
        this.chuyenTauDen = chuyenTauDen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ga ga = (Ga) o;
        return maGa == ga.maGa;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maGa);
    }

    @Override
    public String toString() {
        return "Ga{" +
                "maGa=" + maGa +
                ", tenGa='" + tenGa + '\'' +
                '}';
    }
}
