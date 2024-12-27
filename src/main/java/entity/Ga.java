package entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Dự án: tau-viet-express
 * @Class: Ga
 * @Tạo vào ngày: 10/17/2024
 * @Tác giả: Huy
 */
public class Ga implements Serializable {
    private final int maGa;
    private String tenGa;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ga ga = (Ga) o;
        return Objects.equals(maGa, ga.maGa);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maGa);
    }

    @Override
    public String toString() {
        return "Ga{" +
                "maGa='" + maGa + '\'' +
                ", tenGa='" + tenGa + '\'' +
                '}';
    }
}
