package enums;

/**
 * @Dự án: tau-viet-express
 * @Enum: ETrangThaiNhanVien
 * @Tạo vào ngày: 4/10/2024
 * @Tác giả: Huy
 */
public enum ETrangThaiNhanVien {
    LAM_VIEC("Làm việc"),
    NGHI_LAM("Nghỉ làm");

    private String trangThai;

    private ETrangThaiNhanVien(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTrangThai() {
        return trangThai;
    }
}
