package enums;

/**
 * @Dự án: tau-viet-express
 * @Enum: ETrangThaiTaiKhoan
 * @Tạo vào ngày: 4/10/2024
 * @Tác giả: Huy
 */
public enum ETrangThaiTaiKhoan {
    KICH_HOAT("Kích hoạt"),
    BI_KHOA("Bị khóa");

    private String trangThai;

    ETrangThaiTaiKhoan(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTrangThai() {
        return trangThai;
    }
}
