package entity;

import enums.ETrangThaiChoNgoi;
import utils.Validation;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Dự án: tau-viet-express
 * @Class: ChoNgoi
 * @Tạo vào ngày: 30/9/2024
 * @Tác giả: Huy
 */
public class ChoNgoi implements Serializable {
    private final String maCho;
    private LoaiCho loaiCho;
    private Toa toa;

    public ChoNgoi() {
        super();
        this.maCho = "";
    }

    public ChoNgoi(String maCho) {
        this.maCho = maCho;
    }

    public ChoNgoi(String maCho, LoaiCho loaiCho, Toa toa) {
        // Kiểm tra mã chỗ
//        if (!Validation.maCho(maCho)) {
//            throw new IllegalArgumentException("Mã chỗ không hợp lệ");
//        }

        this.maCho = maCho;
        setLoaiCho(loaiCho);
        setToa(toa);
    }

    public ChoNgoi(LoaiCho loaiCho, Toa toa) {
        this.maCho = "";
        setLoaiCho(loaiCho);
        setToa(toa);
    }

    public String getMaCho() {
        return maCho;
    }

    public LoaiCho getLoaiCho() {
        return loaiCho;
    }

    public double getGiaCho() {
        return loaiCho.getGiaCho();
    }

    public void setLoaiCho(LoaiCho loaiCho) {
        // Kiểm tra loại chỗ
        if (loaiCho == null) {
            throw new IllegalArgumentException("Loại chỗ không được để trống");
        }

        this.loaiCho = loaiCho;
    }

    public Toa getToa() {
        return toa;
    }

    public void setToa(Toa toa) {
        // Kiểm tra toa
        if (toa == null) {
            throw new IllegalArgumentException("Toa không được để trống");
        }

        this.toa = toa;
    }

    public int getSoThuTu() {
        return Integer.parseInt(maCho.substring(maCho.length() - 2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChoNgoi choNgoi = (ChoNgoi) o;
        return Objects.equals(maCho, choNgoi.maCho);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maCho);
    }

    @Override
    public String toString() {
        return "ChoNgoi{" +
                "maCho='" + maCho + '\'' +
                ", loaiCho=" + loaiCho +
                ", toa=" + toa +
                '}';
    }
}
