package entity;

import utils.Validation;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @Dự án: tau-viet-express
 * @Class: KhuyenMai
 * @Tạo vào ngày: 30/9/2024
 * @Tác giả: Huy
 */
public class KhuyenMai implements Serializable {
    private final String maKM;
    private LocalDate ngayBD;
    private LocalDate ngayKT;
    private String doiTuong;
    private double phanTramKM;
    private boolean daGuiThongBao;

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