package entity;

import utils.Validation;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @Dự án: tau-viet-express
 * @Class: ChuyenTau
 * @Tạo vào ngày: 30/9/2024
 * @Tác giả: Huy
 */
public class ChuyenTau implements Serializable {
    private final String maChuyen;
    private String macTau;
    private Tau tau;
    private Ga gaDi;
    private Ga gaDen;
    private LocalDateTime ngayGioDi;
    private LocalDateTime ngayGioDen;
    private String trangThai;

    public ChuyenTau() {
        super();
        this.maChuyen = "";
    }

    public ChuyenTau(String maChuyen) {
        this.maChuyen = maChuyen;
    }

    public ChuyenTau(String maChuyen, String macTau, Tau tau, Ga gaDi, Ga gaDen,
                     LocalDateTime ngayGioVe, LocalDateTime ngayGioDen, String trangThai) {
        // Kiểm tra mã chuyến tàu
//        if (!Validation.maChuyen(maChuyen)) {
//            throw new IllegalArgumentException("Mã chuyến tàu không hợp lệ");
//        }

        this.maChuyen = maChuyen;
        setMacTau(macTau);
        setTau(tau);
        setGaDi(gaDi);
        setGaDen(gaDen);
        setNgayGioDi(ngayGioVe);
        setNgayGioDen(ngayGioDen);
        setTrangThai(trangThai);
    }

    public ChuyenTau(String macTau, Tau tau, Ga gaDi, Ga gaDen, LocalDateTime ngayGioVe,
                     LocalDateTime ngayGioDen, String trangThai) {
        this.maChuyen = "";
        setMacTau(macTau);
        setTau(tau);
        setGaDi(gaDi);
        setGaDen(gaDen);
        setNgayGioDi(ngayGioVe);
        setNgayGioDen(ngayGioDen);
        setTrangThai(trangThai);
    }

    public ChuyenTau(Tau tau, Ga gaDi, Ga gaDen, LocalDateTime ngayGioVe,
                     LocalDateTime ngayGioDen, String trangThai) {
        this.maChuyen = "";
        setTau(tau);
        setGaDi(gaDi);
        setGaDen(gaDen);
        setNgayGioDi(ngayGioVe);
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
        // Kiểm tra mã tàu
        if (macTau.trim().isEmpty()) {
            throw new IllegalArgumentException("Mác tàu không hợp lệ");
        }

        this.macTau = macTau;
    }

    public Tau getTau() {
        return tau;
    }

    public void setTau(Tau tau) {
        // Kiểm tra tàu
        if (tau == null) {
            throw new IllegalArgumentException("Tàu không hợp lệ");
        }

        this.tau = tau;
    }

    public Ga getGaDi() {
        return gaDi;
    }

    public void setGaDi(Ga gaDi) {
        // Kiểm tra ga đi
        if (gaDi == null) {
            throw new IllegalArgumentException("Ga đi không hợp lệ");
        }

        this.gaDi = gaDi;
    }

    public Ga getGaDen() {
        return gaDen;
    }

    public void setGaDen(Ga gaDen) {
        // Kiểm tra ga đến
        if (gaDen == null) {
            throw new IllegalArgumentException("Ga đến không hợp lệ");
        }

        this.gaDen = gaDen;
    }

    public LocalDateTime getNgayGioDi() {
        return ngayGioDi;
    }

    public void setNgayGioDi(LocalDateTime ngayGioDi) {
        // Kiểm tra ngày giờ khởi hành không được sau ngày hiện tại
//        if (ngayGioDi.isAfter(LocalDateTime.now())) {
//            throw new IllegalArgumentException("Ngày giờ khởi hành không hợp lệ");
//        }

        this.ngayGioDi = ngayGioDi;
    }

    public LocalDateTime getNgayGioDen() {
        return ngayGioDen;
    }

    public void setNgayGioDen(LocalDateTime ngayGioDen) {
        // Kiểm tra ngày giờ đến không được sau ngày giờ khởi hành
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
        return Objects.hashCode(maChuyen);
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
