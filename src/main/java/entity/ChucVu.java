package entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Dự án: tau-viet-express
 * @Class: ChucVu
 * @Tạo vào ngày: 30/9/2024
 * @Tác giả: Huy
 */
public class ChucVu implements Serializable {
    private final String maCV;
    private String tenCV;

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
